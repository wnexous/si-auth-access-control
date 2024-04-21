package controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileController {

    static protected String basePathname = "files/";
    static protected File folderBase = new File(basePathname);

    static public File[] getFolderFiles() {
        return folderBase.listFiles();
    }

    static public File getFileByName(String filename) {
        for (File file : getFolderFiles()) {
            if (file.getName().equals(filename)) {
                return file;
            }
        }
        return null;
    }

    static public void deleteFile(File file) {
        file.delete();
    }

    static public void createFile(String filename) throws IOException {
        File newFile = new File(basePathname + filename);
        newFile.createNewFile();
    }

    static public List<String> readFile(File file) {
        Path path = file.toPath();
        List<String> lines;

        try {
            lines = Files.readAllLines(path);
        } catch (Exception e) {
            lines = null;
        }

        return lines;
    }
}
