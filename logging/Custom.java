import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


class ParentException extends Exception {
    public ParentException(String msg) {
        super(msg);
    }
}
class ChildException extends ParentException{
    public ChildException(String msg){
        super(msg);
    }
}
public class Custom {

    static void data(String name) throws ParentException{
        if(name==null){
              throw new ChildException("data cannot be null");
        }else {
            logger.info("data is :"+name);
        }
    }

    static Logger logger = LogManager.getLogger(Custom.class);


    public static void main(String[] args) {
        try {
            data(null);
        }catch (ChildException ce){
            logger.error("caught child exception: "+ce);
        }catch (ParentException pe){
            logger.error("caught aparent exception "+pe);
        }
    }

}
