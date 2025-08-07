public class MultipleEx {
    public void test1(){
        int a=10,b=0,c;
        c=a/b;
    }

    public void test2(){
        int[] arr={1,2,3};
        System.out.println(arr[10]);
    }

    public static void main(String[] args) {
        MultipleEx obj= new MultipleEx();
        try {

            try {
                obj.test1();
            } catch (ArithmeticException e1) {
                e1.printStackTrace();
                System.out.println("catched exception in test1()");
            }
            try {
                obj.test2();
            }catch (ArrayIndexOutOfBoundsException e2){
                e2.getMessage();
                System.out.println("catched exception in test2()");
            }
        }catch (Exception e3){
            e3.toString();
            System.out.println("catched exception in outer try catch block");
        }
        finally {
            System.out.println("This is finally block");
        }
        System.out.println("out of try catch finally block");
    }
}
