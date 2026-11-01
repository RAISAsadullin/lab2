package org.example;

public class Main {
    public static void main(String[] args) {

        ConsoleInput input = new ConsoleInput();

        String query = input.readSearchQuery();

        System.out.println("Ваш запрос: " + query);

    }
}