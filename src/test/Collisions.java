package test;

import domain.Collision;
import domain.RPCripto;
import domain.Sha256Cripto;
import utils.Helpers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Collisions {
    public static void main(String[] args) {

        Set<String> hashs = new HashSet<>();
        List<Collision> collisions = new ArrayList<>();
        int repeat = 0;

        for (int i = 0; i < 10000; i++) {
            String password = Helpers.generateRandomPassword();
            String hashedPassword = Sha256Cripto.hash(password);

            boolean add = hashs.add(hashedPassword);

            if (!add) {
                Collision collision = new Collision(password, hashedPassword);
                collisions.add(collision);
                repeat++;
            }
        }

        for (Collision item : collisions) {
            System.out.println(item.toString());
        }
        System.out.println("O Total de colisões foi: " + repeat);
    }
}
