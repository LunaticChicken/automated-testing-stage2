import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        tree(args[0]);
    }

    private static void tree(String pathname) throws IOException {
        if (pathname.matches(".*\\.txt$")) {
            analyzeTree(pathname);
        } else {
            FileWriter writer = new FileWriter("src\\main\\java\\MainTask.txt");
            File externalDirectory = new File(pathname);
            writer.write(externalDirectory.getAbsolutePath() + "\n");
            drawTree(new File(pathname), 1, writer);
            writer.flush();
            writer.close();
        }
    }

    private static void drawTree(File externalDirectory, int recursionDepth, FileWriter writer) throws IOException {
        List<File> directoryList = new ArrayList<>();
        List<File> fileList = new ArrayList<>();
        for (File file : externalDirectory.listFiles()) {
            if (file.isFile()) fileList.add(file);
            else directoryList.add(file);
        }
        for (File file : fileList) {
            writer.write(String.format("%" + recursionDepth + "s", "").replace(" ", "│   ")
                    + file.getName() + "\n");
        }
        for (File file : directoryList) {
            boolean isCurrentFolderLast = directoryList.get(directoryList.size() - 1) == file;
            if (recursionDepth > 1) {
                writer.write(String.format("%" + (recursionDepth-1) + "s", "").replace(" ", "│   "));
            }
            writer.write((isCurrentFolderLast ? "└───" : "├───") + file.getName() + "\n");
            drawTree(file, recursionDepth + 1, writer);
        }
    }

    private static void analyzeTree(String pathname) throws IOException {
        int amountOfFolders = 0, amountOfFiles = 0, totalLengthOfFilesNames = 0;
        List<String> stringsOfMainTXTFile = Files.readAllLines(Paths.get(pathname));
        stringsOfMainTXTFile.set(0, "");
        for (String string : stringsOfMainTXTFile) {
            if (string.contains("└───") || string.contains("├───")) {
                amountOfFolders++;
            } else if (!string.equals("")) {
                amountOfFiles++;
                totalLengthOfFilesNames += string.substring(string.lastIndexOf("│   ") + 4).length();
            }
        }
        double averageAmountOfFilesInFolder = (double) amountOfFiles / amountOfFolders;
        double averageLengthOfFileName = (double) totalLengthOfFilesNames / amountOfFiles;

        System.out.println("Amount of folders = " + amountOfFolders);
        System.out.println("Amount of files = " + amountOfFiles);
        System.out.println("Average amount of files in a folder = " + averageAmountOfFilesInFolder);
        System.out.println("Average length of file name = " + averageLengthOfFileName);
    }
}