package com.wideoapp.WideoAppSecurity.Service;

import com.wideoapp.WideoAppSecurity.Jwt.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class JwtTokenService {

    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.http.request.header}")
    private String tokenHeader;

    public JwtTokenService(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    public String findEmailFromTokenOfHeader(Map<String, String> header) {

        if (header.get(tokenHeader) == null || header.get(tokenHeader).equals("")) {
            throw new IllegalStateException("SECURITY Server Error - header have no token #1");
        }

        String jwtToken = header.get(tokenHeader).substring(7);

        if (jwtToken == null || jwtToken.equals("")) {
            throw new IllegalStateException("SECURITY Server Error - not found token from header #2");
        }

        String email = jwtTokenUtil.getUsernameFromToken(jwtToken);

        if (email == null || email.equals("")) {
            throw new IllegalStateException("SECURITY Server Error - not found email from token #3");
        }

        return email;
    }
}
