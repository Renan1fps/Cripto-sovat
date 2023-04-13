package domain;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha256Cripto {

    public static byte[] hash(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = digest.digest(input.getBytes());
            return hashedBytes;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
