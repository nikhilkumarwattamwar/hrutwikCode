package GitFolder.hrutwikCode;

public class TryWithResource {
    public static void main(String[] args) {
        try(Resource1 re=new Resource1()){
            re.doSomething();
        }catch (Exception e){
            System.out.println(e.getMessage());
            for (Throwable t:e.getSuppressed()){
                System.out.println(t);
            }
        }
    }
}
class Resource1 implements AutoCloseable{
    public void doSomething(){
        System.out.println("From doSomeThing.....");
//        throw new RuntimeException("from doSomething Error");
    }

    @Override
    public void close() throws Exception {
        System.out.println("Resource is Closing...");
        throw new RuntimeException("from close()");
    }
}