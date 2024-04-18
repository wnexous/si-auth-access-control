package controllers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataController {

    private ArrayList<String[]> file;

    public DataController(String fileName) {
        String arquivo = "src/data/" + fileName;

        try {
            file = readFile(arquivo);
        } catch (Exception e) {
            System.out.println("Erro ao ler arquivo! >>" + e.getMessage());
        }
    }

    private ArrayList<String[]> readFile(String fileName) throws FileNotFoundException {

        Scanner scanner = new Scanner(new FileReader(fileName)).useDelimiter("\\n");
        ArrayList<String[]> rows = new ArrayList<String[]>();

        while (scanner.hasNext()) {
            String row = scanner.next();
            String[] rowSplited = row.split(";");
            rows.add(rowSplited);
        }
        scanner.close();
        return rows;
    }

    public ArrayList<String[]> getFile() {
        return this.file;
    }

    public String[] getHeader() {
        if (this.file != null)
            return this.file.get(0);
        return null;
    }

    public List<String[]> getTable() {
        if (this.file.size() > 0)
            return this.file.subList(1, this.file.size());

        return null;
    }

    public String[] findItemByColumn(String item, String column) {
        String[] header = this.getHeader();
        List<String[]> table = this.getTable();
        for (int i = 0; i < header.length; i++) {
            if (header[i].equals(column)) {
                for (String[] row : table) {
                    if (row[i].equals(item))
                        return row;
                }
            }
        }
        return null;
    }

}
