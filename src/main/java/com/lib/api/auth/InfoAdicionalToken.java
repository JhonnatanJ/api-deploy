package com.lib.api.auth;

import com.lib.api.entities.Cuenta;
import com.lib.api.services.CuentaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class InfoAdicionalToken implements TokenEnhancer {

    @Autowired
    private CuentaServiceImpl cuentaServiceImpl;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Cuenta cuenta = null;
        try {
            cuenta = cuentaServiceImpl.findByEmail(authentication.getName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Map<String, Object> info = new HashMap<>();
        info.put("id_cuenta", cuenta.getIdCuenta());
        info.put("email", cuenta.getEmail());
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
        return accessToken;
    }
}
