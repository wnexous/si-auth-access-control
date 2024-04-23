package controllers;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import types.DataColumnTypes;

/**
 * 
 * HOW USE
 * 
 * exemple:
 * DataController dc = new DataController("UsersData.txt");
 * 
 */

public class DataController {

    private ArrayList<String[]> file;
    public String fileName;

    public DataController(String fileName) {
        this.fileName = "src/data/" + fileName;

        try {
            this.file = readFile(this.fileName);
        } catch (Exception e) {
            System.out.println("Erro ao ler arquivo! >>" + e.getMessage());
        }
    }

    protected void reloadFileRead(){
        try {
            this.file = readFile(this.fileName);
        } catch (Exception e) {
            System.out.println("Erro ao ler arquivo! >>" + e.getMessage());
        }
    }

    private ArrayList<String[]> readFile(String fileName) throws FileNotFoundException {

        Scanner scanner = new Scanner(new FileReader(fileName)).useDelimiter("\r\n");
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
        reloadFileRead();
        return this.file;
    }

    public Integer getIndexFromColumn(DataColumnTypes column) {
        String[] header = getHeader();
        for (int i = 0; i < header.length; i++) {
            if (header[i].equals(column.getColumnName()))
                return i;

        }
        return null;
    }

    public String[] getHeader() {
        if (getFile() != null)
            return getFile().get(0);
        return null;
    }

    public List<String[]> getTable() {
        if (getFile().size() > 0)
            return getFile().subList(1, getFile().size());

        return null;
    }

    public String[] findItemByColumn(String item, DataColumnTypes column) {
        String[] header = this.getHeader();
        List<String[]> table = this.getTable();
        for (int i = 0; i < header.length; i++) {
            if (header[i].equals(column.getColumnName())) {
                for (String[] row : table) {
                    if (row[i].equals(item))
                        return row;
                }
            }
        }
        return null;
    }

    public List<String[]> findItemsByColumn(String item, DataColumnTypes column) {
        String[] header = this.getHeader();
        List<String[]> table = this.getTable();
        List<String[]> finded = new ArrayList<String[]>();
        for (int i = 0; i < header.length; i++) {
            if (header[i].equals(column.getColumnName())) {
                for (String[] row : table) {
                    if (row[i].equals(item))
                        finded.add(row);
                }
            }
        }
        return finded;
    }

    public Integer findIndexFromItemByColumn(String item, DataColumnTypes column) {
        String[] header = this.getHeader();
        List<String[]> table = this.getTable();
        for (int i = 0; i < header.length; i++) {
            if (header[i].equals(column.getColumnName())) {
                for (String[] row : table) {
                    if (row[i].equals(item))
                        return table.indexOf(row);
                }
            }
        }
        return null;
    }

    public void saveFile(String[] header, List<String[]> table) {

        try {
            FileWriter writer = new FileWriter(this.fileName);
            BufferedWriter bw = new BufferedWriter(writer);

            // Limpa o arquivo
            writer.write("");

            // cria o texto do header e escreve;
            String headerText = "";
            // for (String column : header) {
            // headerText += column + ";";
            // }

            for (int i = 0; i < header.length; i++) {
                headerText += header[i];

                if ((header.length - 1) != i) {
                    headerText += ";";
                }

            }
            bw.write(headerText);

            // cria o texto de cada linha e escreve;

            for (int i = 0; i < table.size(); i++) {

                String[] row = table.get(i);

                String rowText = "";

                for (int j = 0; j < row.length; j++) {
                    rowText += row[j];

                    if ((row.length - 1) != j) {
                        rowText += ";";
                    }
                }
                bw.newLine();
                bw.append(rowText);
            }

            bw.close();

            // ArrayList<String[]> file = new ArrayList<String[]>();
            // file.add(header);

            // for (String[] row : table) {
            // file.add(row);
            // }
            this.file = readFile(this.fileName);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void appendRow(String[] row) {
        List<String[]> table = getTable();
        table.add(row);

        this.saveFile(this.getHeader(), table);
    }

    public void removeRowByIndex(int index) {
        List<String[]> table = getTable();
        List<String[]> newTable = new ArrayList<String[]>();

        if (table == null)
            return;

        /*
         * Por algum motivo, o metodo .remove(index)
         * estava gerando algum erro desconhecido
         * entao optei por usar um metodo de filtragem
         * da lista ao invez de remover o item pelo index
         */
        for (int i = 0; i < newTable.size(); i++) {
            if (i != index)
                newTable.add(table.get(i));
        }

        /* metodo que estava derando erro */
        // table.remove(index);

        this.saveFile(this.getHeader(), newTable);
    }

    public void removeRowByRow(String[] row) {
        List<String[]> table = getTable();

        for (String[] tr : table) {
            if (tr.equals(row))
                table.remove(tr);
        }
        this.saveFile(this.getHeader(), table);
    }

}
