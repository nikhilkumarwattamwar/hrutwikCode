package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Example {

        static Logger logger= LogManager.getLogger(Example.class);

        public static void main(String[] args) {
            try {
                int data= 10/0;
            }catch (ArithmeticException e){
            logger.error("exception occured",e);
                logger.debug("exception occured",e);
            }
        }
    }

