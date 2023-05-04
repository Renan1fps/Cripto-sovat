package domain;

import java.nio.ByteBuffer;
import java.util.Random;


public class CriptoWithHash {

    private static final int TABLE_SIZE = 1000;
    private static final int HASH_SIZE = 256;

    private static class HashEntry {
        String key;
        String value;
        HashEntry next;

        public HashEntry(String key, String value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    private static class HashTable {
        HashEntry[] table;

        public HashTable() {
            table = new HashEntry[TABLE_SIZE];
            for (int i = 0; i < TABLE_SIZE; i++) {
                table[i] = null;
            }
        }

        private int getHash(String key) {
            int hash = 0;
            for (int i = 0; i < key.length(); i++) {
                hash = (hash << 4) + key.charAt(i);
            }
            return hash % TABLE_SIZE;
        }

        public void put(String key, String value) {
            int hash = getHash(key);
            if (table[hash] == null) {
                table[hash] = new HashEntry(key, value);
            } else {
                HashEntry entry = table[hash];
                while (entry.next != null && !entry.key.equals(key)) {
                    entry = entry.next;
                }
                if (entry.key.equals(key)) {
                    entry.value = value;
                } else {
                    entry.next = new HashEntry(key, value);
                }
            }
        }

        public String get(String key) {
            int hash = getHash(key);
            HashEntry entry = table[hash];
            while (entry != null && !entry.key.equals(key)) {
                entry = entry.next;
            }
            return (entry == null) ? null : entry.value;
        }
    }

    public static String hash(String input) {
        byte[] hashBytes = new byte[HASH_SIZE / 8];
        new Random().nextBytes(hashBytes);
        for (int i = 0; i < input.length(); i++) {
            byte[] byteValue = ByteBuffer.allocate(4).putInt(input.charAt(i)).array();
            for (int j = 0; j < byteValue.length; j++) {
                hashBytes[j % hashBytes.length] ^= byteValue[j];
                for (int k = 0; k < 8; k++) {
                    if ((hashBytes[j % hashBytes.length] & 0x01) == 1) {
                        hashBytes[j % hashBytes.length] >>>= 1;
                        hashBytes[j % hashBytes.length] ^= 0xEDB88320;
                    } else {
                        hashBytes[j % hashBytes.length] >>>= 1;
                    }
                }
            }
        }
        return bytesToHex(hashBytes);
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    private static String generateRandomPassword() {
        Random random = new Random();
        char[] password = new char[random.nextInt(10) + 6];
        for (int i = 0; i < password.length; i++) {
            password[i] = (char) (random.nextInt(94) + 33);
        }
        return new String(password);
    }


    public static void main(String[] args) {
        HashEntry[] hashTable = new HashEntry[TABLE_SIZE];
        int collisions = 0;

        for (int i = 0; i < 1000; i++) {
            String password = generateRandomPassword();
            String hashedPassword = hash(password);

            int index = getIndex(hashedPassword);

            if (hashTable[index] == null) {
                hashTable[index] = new HashEntry(password, hashedPassword);
            } else {
                HashEntry entry = hashTable[index];
                while (entry.next != null) {
                    entry = entry.next;
                }
                entry.next = new HashEntry(password, hashedPassword);
                collisions++;
            }
        }

        System.out.println("Hash table:");
        for (int i = 0; i < hashTable.length; i++) {
            System.out.print(i + ": ");
            if (hashTable[i] != null) {
                HashEntry entry = hashTable[i];
                while (entry != null) {
                    System.out.print("(" + "password: " + entry.key + " | " + "Hash: " + entry.value + ")");
                    entry = entry.next;
                    if (entry != null) {
                        System.out.print(" -> ");
                    }
                }
            }
            System.out.println();
        }

        System.out.println("Collisions: " + collisions);
    }

    private static int getIndex(String hashedPassword) {
        int index = 0;
        for (byte b : hashedPassword.getBytes()) {
            index += b;
        }
        return index % TABLE_SIZE;
    }
}

