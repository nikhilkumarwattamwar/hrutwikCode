public class Return {
    public static void main(String[] args) {
        try{
            int num=9/1;
            System.out.println(num);
            return; //method exits here
        }catch (Exception e){
            System.out.println("inside the catch block");
        }
        finally {
            System.out.println("inside the finally block");//this line will execute
        }
        System.out.println("outside try catch finally block"); //this line will not execute because of the return
    }
}
