import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class DBException extends Exception{
   public DBException (String msg){
       super(msg);
   }
}

class ServiceException extends Exception{
    public ServiceException(String msg, Throwable cause){
        super(msg, cause);
    }
}

public class Chainlog {

    static void dbconnection() throws DBException{
        throw new DBException("unable to connect to the db");
    }

    static void processreq() throws ServiceException {
        try{
            dbconnection();
        }catch (DBException e){
            throw new ServiceException("service failed",e);
        }
    }

    public static void main(String[] args)  {
        Logger logger = LoggerFactory.getLogger(Chainlog.class);
        try{
            processreq();
        }catch (ServiceException e1){
            logger.error("caught service error",e1);
            logger.error("root cause",e1.getCause());
        }
    }
}
