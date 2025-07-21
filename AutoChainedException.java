package CustomException;

public class AutoChainedException {
    public static void main(String[] args) {
        try (MyResource m=new MyResource()){
            throw new RuntimeException("RuntimeException from try");
        }catch (Exception e){
            System.out.println(e.getMessage());
            for (Throwable t:e.getSuppressed()){
                System.out.println("Suppressed "+t);
            }
        }
    }
}
class MyResource implements AutoCloseable{
    @Override
    public void close() throws Exception {
        System.out.println("MyResource is Closing ......");
        throw new Exception("Exception from close() ....");
    }
}
