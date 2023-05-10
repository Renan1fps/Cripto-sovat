package domain;

import utils.Helpers;

import java.nio.ByteBuffer;
import java.util.Random;

public class RPCripto {

    private static final int HASH_SIZE = 256;

    public static String hash(String input) {
        // Tamanho do hash em bits
        int hashSize = 256;

        // Define a semente para gerar o número pseudo-aleatório
        long seed = 123456789L;
        Random random = new Random(seed);

        // Inicializa o hash com um valor aleatório de 256 bits
        byte[] hashBytes = new byte[hashSize / 8];
        random.nextBytes(hashBytes);

        // Aplica a função de hash iterativamente para cada caractere da string
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

        // Retorna o hash em formato hexadecimal
        StringBuilder sb = new StringBuilder();
        for (byte b : hashBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
