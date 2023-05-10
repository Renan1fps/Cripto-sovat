package utils;

import java.util.Random;

public class Helpers {

    private static final int TABLE_SIZE = 1000;

    public static String generateRandomPassword() {
        Random random = new Random();
        char[] password = new char[random.nextInt(10) + 6];
        for (int i = 0; i < password.length; i++) {
            password[i] = (char) (random.nextInt(94) + 33);
        }
        return new String(password);
    }

    public static String generateSimpleRandom(int comprimento){
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(comprimento);

        for (int i = 0; i < comprimento; i++) {
            int indice = random.nextInt(caracteres.length());
            char caracterAleatorio = caracteres.charAt(indice);
            sb.append(caracterAleatorio);
        }

        return sb.toString();
    }

    public static int getIndex(String hashedPassword) {
        int index = 0;
        for (byte b : hashedPassword.getBytes()) {
            index += b;
        }
        return index % TABLE_SIZE;
    }

    public static String parseHexToString(byte[] hashBytes){
        StringBuilder sb = new StringBuilder();
        for (byte b : hashBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
