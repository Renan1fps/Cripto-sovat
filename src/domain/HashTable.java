package domain;

public class HashTable {

    private static final int TABLE_SIZE = 1000;
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
