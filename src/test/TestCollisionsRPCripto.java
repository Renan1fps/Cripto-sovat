package test;

import domain.HashEntry;
import domain.RPCripto;
import utils.Helpers;

public class TestCollisionsRPCripto {
    private static final int TABLE_SIZE = 1000;

    public static void main(String[] args) {
        HashEntry[] hashTable = new HashEntry[TABLE_SIZE];
        int collisions = 0;

        for (int i = 0; i < 1000; i++) {
            String password = Helpers.generateRandomPassword();
            String hashedPassword = RPCripto.hash(password);

            int index = Helpers.getIndex(hashedPassword);

            if (hashTable[index] == null) {
                hashTable[index] = new HashEntry(password, hashedPassword);
            } else {
                HashEntry entry = hashTable[index];
                while (entry.getNext() != null) {
                    entry = entry.getNext();
                }
                entry.setNext(new HashEntry(password, hashedPassword));
                collisions++;
            }
        }

        System.out.println("Hash table:");
        for (int i = 0; i < hashTable.length; i++) {
            System.out.print(i + ": ");
            if (hashTable[i] != null) {
                HashEntry entry = hashTable[i];
                while (entry != null) {
                    System.out.print("(" + "password: " + entry.getNext() + " | " + "Hash: " + entry.getValue() + ")");
                    entry = entry.getNext();
                    if (entry != null) {
                        System.out.print(" -> ");
                    }
                }
            }
            System.out.println();
        }

        System.out.println("Collisions: " + collisions);
    }

}
