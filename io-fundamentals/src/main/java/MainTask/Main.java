package MainTask;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File("E:\\Projects\\main");
        FileWriter writer = new FileWriter("src\\main\\java\\MainTask\\task1.txt");
        drawTree(file, false, 1, writer);
        writer.flush();
    }

    private static void drawTree(File main, boolean isCurrentFolderLast, int stack, FileWriter writer) throws IOException {
        File[] fileList = main.listFiles();

        for (File file : fileList) {
            if (file.isFile())
                writer.write(String.format("%"+stack+"s", "").replace(" ", "│   ") + file.getName() + "\n");
        }

        for (File file : fileList) {
            if (file.isDirectory()) {
                if (stack == 1) writer.write("├───");
                else writer.write(String.format("%"+(stack-1)+"s", "").replace(" ", "│   "));
                if(fileList[fileList.length-1] != file) {
                    writer.write("├───" + file.getName() + "\n");
                    drawTree(file, false, ++stack, writer);
                } else {
                    writer.write("└───" + file.getName() + "\n");
                    drawTree(file, true, ++stack, writer);
                }
            }
        }
    }
}