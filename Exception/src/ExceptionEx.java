public class ExceptionEx {
    public static void main(String[] args) {
        try {
            int a = 100, b = 0, c;
            c = a / b;
            System.out.println(c);
            System.out.println("checking try ");
        } catch (Exception e) {
//            System.out.println(e);
//            e.printStackTrace();
//            System.out.println(e);
            System.out.println("checking");
        }
        finally {
            System.out.println("this is finally block");
            System.out.println("hello");
        }
         }
    }

