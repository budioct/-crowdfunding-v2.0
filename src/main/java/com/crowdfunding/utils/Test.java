package com.crowdfunding.utils;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test {

    public static void main(String[] args) {

        String data = "budhi";
        String token = GenerateJWT.createToken(data);
        log.info("data: {}", data);
        log.info("token: {}", token);

        Claims claims = GenerateJWT.validateToken(token);
        log.info("claims ID == data : {}", claims.getId());
        log.info("claims Subject : {}", claims.getSubject());
        log.info("claims Issuer : {}", claims.getIssuer());
        log.info("claims Expiration : {}", claims.getExpiration());


        String data1 = "budhi";
        String encrypt = MD5.encrypt(data1);
        String decrypt = MD5.decrypt(encrypt);
        log.info("data: {}", data1);
        log.info("MD5 encrypt: {}", encrypt);
        log.info("MD5 decrypt: {}", decrypt);


    }

}
