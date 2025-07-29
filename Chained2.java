package GitFolder.hrutwikCode;;

import java.security.spec.ECField;

public class Chained2 {
    public static void main(String[] args) {
        try(Resource11 r1=new Resource11();Resource12 r2=new Resource12();){
            r1.use();

        }catch (Exception e){
            System.out.println(e.getMessage());
            Exception ex=new Exception("Chained Exception");
            e.initCause(ex);
            for (Throwable t:e.getSuppressed()){
                System.out.println(t.getMessage());
            }
            System.out.println(e.getCause());
        }
    }
}

class Resource11 implements AutoCloseable{
    public void use(){
        System.out.println(" use() from Resource11");
        throw new RuntimeException("RuntimeException in Resource11");
    }

    @Override
    public void close() throws Exception {
        System.out.println("Resource11 is closing ...");
        throw  new Exception("From resource11 close()");
    }
}
class Resource12 implements AutoCloseable{
    @Override
    public void close() throws Exception {
        System.out.println("hello from Resource12");
    }
}