package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        System.out.println("#### HelloBracket {} ####");

        FileManager manager = new FileManager("player.csv");

        try {
            System.out.println("try block");

            manager.createFile();
            manager.createPlayer("example#123,100,0.5");
            manager.readAll().forEach(System.out::println);
            manager.updateLine(3,"example#123,5,5");
            manager.readLine(3);
            manager.createPlayer("example#123,100,0.5");
            manager.delete(4);
            manager.readPlayers();

//        } catch (IOException e) {
//            System.out.println("IOException catch block");
//            throw new RuntimeException(e);
//        } catch (IndexNotFoundException inf) {
//            System.out.println("IndexNotFoundException catch block");
//            throw new RuntimeException(inf);
        } catch (IOException |IndexNotFoundException e) {
            System.out.println("catch block");
            throw new RuntimeException(e);
        } finally {
            System.out.println("Finally block");
        }


        System.out.println("#### End Bracket {} ####");


    }
}