import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        tree(args[0]);
    }

    private static void tree(String pathname) throws IOException {
        if (pathname.substring(pathname.length()-4).equals(".txt")) {
            analyzeTree(pathname);
        } else {
            FileWriter writer = new FileWriter("src\\main\\java\\task1.txt");
            drawTree(new File(pathname), 1, writer);
            writer.flush();
            writer.close();
        }
    }

    private static void drawTree(File externalFile, int recursionDepth, FileWriter writer) throws IOException {
        List<File> directoryList = new ArrayList<>(), fileList = new ArrayList<>();
        for (File file : externalFile.listFiles()) {
            if (file.isFile()) fileList.add(file);
            else directoryList.add(file);
        }
        for (File file : fileList) {
            writer.write(String.format("%"+recursionDepth+"s", "").replace(" ", "│   ")
                    + file.getName() + "\n");
        }
        for (File file : directoryList) {
            boolean isCurrentFolderLast = directoryList.get(directoryList.size()-1) == file;
            try {
                writer.write(String.format("%"+(recursionDepth-1)+"s", "").replace(" ", "│   "));
            } catch (FormatFlagsConversionMismatchException ignored) { }
            writer.write((isCurrentFolderLast ? "└───" : "├───") + file.getName() + "\n");
            drawTree(file, recursionDepth+1, writer);
        }
    }

    private static void analyzeTree(String pathname) throws IOException {
        int amountOfFolders = 0, amountOfFiles = 0, amountOfFilesInCurrentFolder = 0;
        List<String> listOfStrings = Files.readAllLines(Paths.get(pathname));
        List<Integer> amountOfFilesInEachFolder = new ArrayList<>();
        List<Integer> lengthsOfFileNames = new ArrayList<>();
        for (String string : listOfStrings) {
            if (string.contains("└───") || string.contains("├───")) {
                amountOfFolders++;
                amountOfFilesInEachFolder.add(amountOfFilesInCurrentFolder);
                amountOfFilesInCurrentFolder = 0;
            } else {
                lengthsOfFileNames.add(string.substring(string.lastIndexOf("│   ")+4).length());
                amountOfFiles++;
                amountOfFilesInCurrentFolder++;
            }
        }
        double averageAmountOfFilesInFolder =
                (double) amountOfFilesInEachFolder.stream().mapToInt(s->s).sum() / amountOfFilesInEachFolder.size();
        double averageLengthOfFileName =
                (double) lengthsOfFileNames.stream().mapToInt(s->s).sum() / lengthsOfFileNames.size();
        System.out.println("Amount of folders = " + amountOfFolders);
        System.out.println("Amount of files = " + amountOfFiles);
        System.out.println("Average amount of files in folder = " + averageAmountOfFilesInFolder);
        System.out.println("Average length of file name = " + averageLengthOfFileName);
    }
}