package com.example.security;

import com.example.domain.RememberMe;
import com.example.domain.RememberMeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 记住我 持久化方式
 */
@Component
public class MyPersistentTokenRepository implements PersistentTokenRepository {

    protected final Logger LOGGER = LoggerFactory.getLogger(getClass());
    @Autowired
    private RememberMeMapper remembermeMapper;

    @Override
    public void createNewToken(PersistentRememberMeToken persistentRememberMeToken) {
        RememberMe rememberMe = new RememberMe();
        rememberMe.setUsername(persistentRememberMeToken.getUsername());
        rememberMe.setSeries(persistentRememberMeToken.getSeries());
        rememberMe.setDate(persistentRememberMeToken.getDate());
        rememberMe.setTokenValue(persistentRememberMeToken.getTokenValue());
        remembermeMapper.insert(rememberMe);
    }

    @Override
    public void updateToken(String s, String s1, Date date) {
        RememberMe rememberMe = new RememberMe();
        rememberMe.setUsername("");
        rememberMe.setSeries(s);
        rememberMe.setTokenValue(s1);
        rememberMe.setDate(date);
        remembermeMapper.updateByPK(rememberMe);
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String s) {
        RememberMe rememberMe = remembermeMapper.selectByPK(s);
        if (null != rememberMe) {
            PersistentRememberMeToken persistentRememberMeToken =
                    new PersistentRememberMeToken(rememberMe.getUsername(),
                            rememberMe.getSeries(),
                            rememberMe.getTokenValue(),
                            rememberMe.getDate()
                    );
            return persistentRememberMeToken;
        }
        return null;
    }

    @Override
    public void removeUserTokens(String s) {
        remembermeMapper.deleteByPK(s);
    }
}
