package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Main {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Person warrior = new Person("Warrior", 150, 30);
        Person archer = new Person("Archer", 100, 70);

        try {
            List<Person> list = getStates();
            if (list.size() > 1) {
                warrior = list.get(0);
                archer = list.get(1);
                System.out.println("Data from file");
            }
        } catch (InvalidDataException e) {
            //e.printStackTrace();
            System.out.println("Started New Game");
        }


        System.out.println(warrior);
        System.out.println(archer);

        do {
            System.out.println("Press enter to continue");
            scanner.nextLine();
        } while (step(warrior, archer));
    }

    static boolean step(Person person1, Person person2) {
        if (!person1.isAlive() || !person2.isAlive())
            return false;
        person1.addDamage(person2.getDamage());
        person2.addDamage(person1.getDamage());

        if (!person1.isAlive())
            System.out.println(person1.getName() + " is dead :(");
        else
            System.out.println(person1);

        if (!person2.isAlive())
            System.out.println(person2.getName() + " is dead :(");
        else
            System.out.println(person2);

        updateStates(Arrays.asList(person1, person2));
        return true;
    }

    static void updateStates(List<Person> list) {
        try (OutputStream outputStream = new FileOutputStream("data.txt")) {

            String lines = "";
            lines += list.size() + "\n";
            for (int i = 0; i < list.size(); i++) {
                lines += list.get(i).getName() + "\n";
                lines += list.get(i).getHealth() + "\n";
                lines += list.get(i).getDamage() + "\n";
                lines += list.get(i).getArmour() + "\n";
            }

            outputStream.write(lines.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static List<Person> getStates() throws InvalidDataException {
        List<Person> list = new ArrayList<>();
        try (InputStream inputStream = new FileInputStream("data.txt")) {
            byte bytes[] = inputStream.readAllBytes();

            String lines[] = new String(bytes).split("\n");

            if (lines.length == 0)
                throw new InvalidDataException();

            int size;

            try {
                size = Integer.parseInt(lines[0]);
            } catch (NumberFormatException exception) {
                throw new InvalidDataException();
            }

            if (lines.length != size * 4 + 1)
                throw new InvalidDataException();

            for (int i = 0; i < size; i++) {
                String name = lines[4 * i + 1];
                double health;
                double damage;
                double armour;

                try {
                    health = Double.parseDouble(lines[4 * i + 2]);
                    damage = Double.parseDouble(lines[4 * i + 3]);
                    armour = Double.parseDouble(lines[4 * i + 4]);
                } catch (NumberFormatException exception) {
                    throw new InvalidDataException();
                }

                list.add(new Person(name, health, damage, armour));
            }


        } catch (FileNotFoundException e) {
            throw new InvalidDataException();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

}