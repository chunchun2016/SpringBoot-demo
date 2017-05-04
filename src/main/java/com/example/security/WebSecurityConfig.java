package com.example.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * 以前都是使用xml来配置，但是spring-boot提倡Java Config的使用，
 折腾下来还是很多收获的。有关权限管理，行内大都秉承
 以角色为基础的访问控制（Role-based access control, RBAC）
 在Spring Security中，实现原理很简单，通过AOP对所要管理的资源（url或者method）进行拦截，
 在其内部维护了一条安全过滤链，
    有用户服务（UserDetailsService)、
    身份认证服务（AuthenticationProvider）、
    访问决策管理（AccessDecisionManager）、
    记住我(remember-me)等普世功能。
 当然，使用Spring-boot离不开Java Config，come on,just do it.
 */
@Configuration
@EnableWebSecurity
@ComponentScan("com.example")
@EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyAuthenticationProvider authenticationProvider;//自定义验证
    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    private MyPersistentTokenRepository tokenRepository;
    @Autowired
    private MyAccessDecisionManager accessDecisionManager;

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();

    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .headers()
                .frameOptions().sameOrigin().disable()//disable X-Frame-Options
                .authorizeRequests()
//                    .accessDecisionManager(accessDecisionManager)//用注解替换,如果不使用注解，取消注释
                .antMatchers("/", "/home").permitAll()
                .anyRequest().fullyAuthenticated()//其他url需要鉴权
                .and()
                    .formLogin()
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .loginProcessingUrl("/login")
                        .loginPage("/login")//指定登录页的路径
                        .failureUrl("/login?error")
                        .permitAll()//允许基于表单登录的所有的URL的所有用户的访问。
                        .successHandler(loginSuccessHandler()) //登录成功后可使用loginSuccessHandler()存储用户信息，可选。
                .and()
                    .logout()
                        .deleteCookies("JSESSIONID")
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/home")
                .and()
                    .rememberMe()
                        .tokenRepository(tokenRepository)
                        .rememberMeServices(rememberMeServices())
                        .rememberMeParameter("remember-me").key("key")
                        .tokenValiditySeconds(86400)
                .and()
                    .csrf().disable() //disable csrf
                    .sessionManagement().maximumSessions(1);
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
        auth.userDetailsService(userDetailsService);
    }

    @Bean
    public RememberMeServices rememberMeServices() {
        // Key must be equal to rememberMe().key()
        PersistentTokenBasedRememberMeServices rememberMeServices = new PersistentTokenBasedRememberMeServices("key", userDetailsService, tokenRepository);
        rememberMeServices.setCookieName("remember-me");
        rememberMeServices.setParameter("remember-me");
        rememberMeServices.setTokenValiditySeconds(864000);
        return rememberMeServices;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(4);
    }

    @Bean
    public LoginSuccessHandler loginSuccessHandler() {
        return new LoginSuccessHandler();
    }
}