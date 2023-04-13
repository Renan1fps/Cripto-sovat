package test;

import domain.Sha256Cripto;

import java.util.Scanner;

public class CriptoSha256 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Digite a string que deseja criptografar: ");
        String originalString = sc.nextLine();
        System.out.println("String original: " + originalString);

        byte[] hashedBytes = Sha256Cripto.hash(originalString);
        String hashedString = Sha256Cripto.bytesToHex(hashedBytes);
        System.out.println("Hash: " + hashedString);
    }
}

// 5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5
// 20f3765880a5c269b747e1e906054a4b4a3a991259f1e16b5dde4742cec2319a