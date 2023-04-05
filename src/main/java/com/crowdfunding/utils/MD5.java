package com.crowdfunding.utils;

import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Component
public class MD5 {

    private static String md5(final String input) throws NoSuchAlgorithmException {

        final MessageDigest md = MessageDigest.getInstance("MD5");

        final byte[] messageDigest = md.digest(input.getBytes());

        final BigInteger number = new BigInteger(1, messageDigest);

        return String.format("%032x", number);
    }


    private static Cipher initCipher(final int mode, final String initialVectorString, final String secretKey)

            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {

        final SecretKeySpec skeySpec = new SecretKeySpec(md5(secretKey).getBytes(), "AES");

        final IvParameterSpec initialVector = new IvParameterSpec(initialVectorString.getBytes());

        final Cipher cipher = Cipher.getInstance("AES/CFB8/NoPadding");

        cipher.init(mode, skeySpec, initialVector);

        return cipher;

    }


    public static String encrypt(final String dataToEncrypt) {
        String initialVector = "0123456789123456";
        String secretKey = "Prim4jay4MultiSind0";
        String encryptedData = null;

        try {

            // Initialize the cipher

            final Cipher cipher = initCipher(Cipher.ENCRYPT_MODE, initialVector, secretKey);

            // Encrypt the data

            final byte[] encryptedByteArray = cipher.doFinal(dataToEncrypt.getBytes());

            // Encode using Base64

            //encryptedData = (new BASE64Encoder()).encode(encryptedByteArray);
            encryptedData = Base64.getEncoder().encodeToString(encryptedByteArray);



        } catch (Exception e) {

            System.err.println("Problem encrypting the data");

            e.printStackTrace();

        }

        return encryptedData;

    }


    public static String decrypt(final String encryptedData) {
        String initialVector = "0123456789123456"; //16 carakter (Block)
        String secretKey = "Prim4jay4MultiSind0";
        String decryptedData = null;

        try {

            // Initialize the cipher

            final Cipher cipher = initCipher(Cipher.DECRYPT_MODE, initialVector, secretKey);

            // Decode using Base64

            //final byte[] encryptedByteArray = (new BASE64Decoder()).decodeBuffer(encryptedData);
            final byte[] encryptedByteArray = new String(Base64.getDecoder().decode(encryptedData.getBytes())).getBytes();

            // Decrypt the data

            final byte[] decryptedByteArray = cipher.doFinal(encryptedByteArray);

            decryptedData = new String(decryptedByteArray, "UTF8");

        } catch (Exception e) {

            System.err.println("Problem decrypting the data");

            e.printStackTrace();

        }

        return decryptedData;

    }

}
