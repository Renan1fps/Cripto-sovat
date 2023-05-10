package test;

import domain.Sha256Cripto;

import java.util.Scanner;

public class CriptoSha256 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Digite a string que deseja criptografar: ");
        String originalString = sc.nextLine();
        System.out.println("String original: " + originalString);

        String hashedString = Sha256Cripto.hash(originalString);
        System.out.println("Hash: " + hashedString);
    }
}
