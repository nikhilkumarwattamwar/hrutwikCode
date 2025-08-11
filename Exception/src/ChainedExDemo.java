public class ChainedExDemo {
    static void demoMethod(){
        NullPointerException e= new NullPointerException("Top layer");
        e.initCause(new ArithmeticException("Cause"));
        throw e;
    }

    public static void main(String[] args) {
        try {
            demoMethod();
        }catch (NullPointerException ne){
            System.out.println("caught:"+ne);
            System.out.println("original cause"+ ne.getCause());
        }
    }
}
