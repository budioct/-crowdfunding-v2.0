package com.crowdfunding.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static com.crowdfunding.utils.GenerateJWT.validateToken;

@Component
public class CheckToken {


    public static Map<String, Object> checkToken(String token, String username) {

        Map<String, Object> response = new HashMap<String, Object>();

        // Start Check Token
        try {

            if (CheckUtil.isNullOrEmpty(token)) {

                response.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
                response.put(Constants.STATUS, "Token tidak boleh kosong");
                return response;

            } else if (CheckUtil.isNullOrEmpty(username)) {

                response.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
                response.put(Constants.STATUS, "Username tidak boleh kosong");
                return response;
            }

            Claims claims = validateToken(token);

            if (!claims.getId().equals(username)) {

                response.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
                response.put(Constants.STATUS, "Bukan Token dengan username :" + username);
                return response;
            }

        } catch (ExpiredJwtException expired) {

            response.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
            response.put(Constants.STATUS, "Token Expired");
            return response;

        } catch (SignatureException signature) {

            response.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
            response.put(Constants.STATUS, "Token Failed");
            return response;

        } catch (Exception e) {

            response.put(Constants.STATUS_CODE, Constants.FAILED_CODE);
            response.put(Constants.STATUS, "Token Failed");
            return response;
        }

        response.put(Constants.STATUS_CODE, Constants.SUCCESS_CODE);
//        response.put(Constants.STATUS, Constants.SUCCESS);

        return response;
        // End Check Token

    }

}
