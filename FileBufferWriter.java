package CustomException;
import java.io.*;

public class FileBufferWriter {
    public static void main(String[] args) throws Exception {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("i.txt"))) {
            bw.write("This file was created and written to using Java!");
            bw.newLine();
            bw.write("This is written using try-with-resources.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
