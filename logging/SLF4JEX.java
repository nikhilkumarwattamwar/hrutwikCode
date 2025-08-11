
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class SLF4JEX {

    static Logger logger = LoggerFactory.getLogger(SLF4JEX.class);

    private static int divide(int a, int b) {
        logger.trace("Divide", a, b);
        return a / b;
    }

        public static void main(String[] args) {
            logger.info("main method started");

            try {
                int result = divide(10, 2);
                logger.debug("result: ", result);
            } catch (ArithmeticException e) {
                logger.error("error caught", e);
            }

            logger.warn("method is about to end");
            logger.info("main method ended");
        }


    }

