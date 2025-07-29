package GitFolder.hrutwikCode;

public class MObileNumberValidation {
    public static void NumberValidator(String num) throws InvalidMobileNumber{
        if ( num.length()<9||num==null ){
            throw new MobileNumbberTooShort("NUmber is too Short Atlest have 10 number ");
        }
        if (!num.matches("[0-9]{10}")){
            throw new InvalidMobileNumber("Invalid Mobile Number Format");
        }
        System.out.println(num);
    }

    public static void main(String[] args) {
        try{
            NumberValidator("1452458755");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }     try{
            NumberValidator("");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        try{
            NumberValidator("1458755iii");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
class InvalidMobileNumber extends Exception{
    public InvalidMobileNumber(String s){
        super(s);
    }
}
class MobileNumbberTooShort extends RuntimeException{
    public MobileNumbberTooShort(String s){
        super(s);
    }
}
