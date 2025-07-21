package CustomException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BufferReader {
    public static void main(String[] args) throws IOException {
        try(BufferedReader br=new BufferedReader(new FileReader("i.txt"))){
            String line;
            while ((line=br.readLine())!=null){
                System.out.println(line);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
