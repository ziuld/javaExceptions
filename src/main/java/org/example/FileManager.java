package org.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.*;

public class FileManager {
    private final String filename;

    public FileManager(String filename) {
        String FILE_PATH = "src/main/resources/";
        this.filename = FILE_PATH +filename;
    }

    public void createFile() throws IOException {
        try {
            File file = new File(filename);
            String result = file.createNewFile() ?
                    "File " + file.getName() + " is created successfully." :
                    "File "+ file.getName() +" is already exists";
            System.out.println(result);
        } catch (IOException e) {
            e.fillInStackTrace();
            throw e;
        }finally {
            System.out.println("createFile ");

        }
    }

    public void createPlayer(String data) throws IOException {
        try (FileWriter writer = new FileWriter(filename, true)) {
            writer.write(System.lineSeparator());
            writer.write(data);
        } catch (IOException e) {
            e.fillInStackTrace();
            throw e;
        }finally {
            System.out.println("finally New player "+data);

        }
    }

    public List<String> readAll() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            return reader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.fillInStackTrace();
            throw e;
        }finally {
            System.out.println("finally readAll ");

        }
    }

    public void readLine(int lineNumber) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = reader.lines().skip(lineNumber - 1).findFirst().orElse(null);
            System.out.println(line);
        }catch (IOException e) {
            e.fillInStackTrace();
            throw e;
        }finally {
            System.out.println("finally readLine ");

        }
    }

    public void updateLine(int lineNumber, String newData) throws IOException {
        List<String> fileContent = new ArrayList<>(readAll());
        try (FileWriter writer = new FileWriter(filename)) {
            fileContent.set(lineNumber - 1, newData);
            for (String line : fileContent) {
                writer.write(line);
                writer.write(System.lineSeparator());
            }
        }catch (IOException e) {
            e.fillInStackTrace();
            throw e;
        }finally {
            System.out.println("finally updateLine "+lineNumber);

        }
    }

    public void delete(int lineNumber) throws IOException {
        List<String> fileContent = new ArrayList<>(readAll());
        fileContent.remove(lineNumber - 1);
        try (FileWriter writer = new FileWriter(filename)) {
            for (String line : fileContent) {
                writer.write(line);
                writer.write(System.lineSeparator());
            }
        }catch (IOException e) {
            e.fillInStackTrace();
            throw e;
        }finally {
            System.out.println("finally delete "+lineNumber);

        }
    }


    public void readPlayers() throws IOException, IndexNotFoundException {
        List<String> lines = new ArrayList<>(readAll());
        convertToObject(lines).forEach(System.out::println);
        System.out.println("readPlayers ");
    }

    protected List<Player> convertToObject(List<String> lines) throws IndexNotFoundException {
        Pattern pattern = Pattern.compile(",");
        try {
            // convert to objects
            List<Player> players = lines
                    .stream()
                    .skip(1)
                    .map(line -> {
                        String[] arr = pattern.split(line);
                        return new Player(arr[0],
                                Integer.parseInt(arr[1]),
                                Double.parseDouble(arr[2]));
                    })
                    .collect(Collectors.toList());

            return players;
        }catch (ArrayIndexOutOfBoundsException e){
            IndexNotFoundException error = new IndexNotFoundException("New Exception - out of bound");
            error.fillInStackTrace();
            throw error;
        } finally {
            System.out.println("finally convertToObject ");

        }
    }

}
