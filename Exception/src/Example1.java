public class Example1 {
    public static void main(String[] args) {
        int a=10, b=0, c=0;
        try {
            c=a/b;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("exceotion catched ");
        }
        System.out.println("result: "+c);
    }
}
