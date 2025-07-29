package GitFolder.hrutwikCode;

public class DividedByZero {
    static int num=1422;
    static void DivideByZero(int num) throws DividedByZeroCheckedException{
        if(num==0) throw new DividedByZeroCheckedException("Checked : Can't Divide by Zero");
        System.out.println(DividedByZero.num/num);
    }
    public static void unsafeDivide(int a) {
        if (a == 0)
            throw new DivideBYZeroUncheckedException("Unchecked: Division by zero");
        System.out.println("Result: " + (num/a));
    }

    public static void main(String[] args)   {
        try {

            DivideByZero(0);
            DivideByZero(5);// If Exception Occuer in above line then below code will not execute
        } catch (DividedByZeroCheckedException e) {
            System.out.println( e.getMessage());
        }
        try {
            unsafeDivide(0);
        } catch (DivideBYZeroUncheckedException e) {

            System.out.println( e.getMessage());
        }

            unsafeDivide(5);


    }
}
class DividedByZeroCheckedException extends Exception{
    DividedByZeroCheckedException(String s){
        super(s);
    }
}
class DivideBYZeroUncheckedException extends RuntimeException{
    DivideBYZeroUncheckedException(String s){
        super(s);
    }
}
