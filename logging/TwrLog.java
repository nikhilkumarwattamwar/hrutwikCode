import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TwrLog {
    static Logger logger = LogManager.getLogger(TwrLog.class);

    public static void main(String[] args) {
        try(BufferedReader br= new BufferedReader(new FileReader("data.txt"))) {
           String line;
           while ((line= br.readLine())!=null){
               logger.debug("read line :"+line);
           }
        }catch (IOException e){
            logger.error("error reading line :",e);
        }
    }
}
