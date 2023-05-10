package test;

import domain.RPCripto;

import java.util.Scanner;

public class CriptoSovat {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Digite a string que deseja criptografar: ");
        String originalString = sc.nextLine();
        System.out.println("String original: " + originalString);

        String hashedString = RPCripto.hash(originalString);
        System.out.println("Hash da string: " + hashedString);
    }
}