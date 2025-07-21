package CustomException;

public class MultipleResources {
    public static void main(String[] args) {
        try (ResourceA rA=new ResourceA(); ResourceB rB= new ResourceB()){
            rA.print();
            rB.print();
            throw new RuntimeException("Exception Occurred in Try Block");


        }catch (Exception e){
            System.out.println(e);
            for (Throwable t:e.getSuppressed()){
                System.out.println(t);
            }
        }
    }

}
class ResourceA implements AutoCloseable{
    public void print(){
        System.out.println("from ResourceA print() ....");

    }

    @Override
    public void close() throws Exception {
        System.out.println("Resource A is Closing .......");

    }
}
class ResourceB implements AutoCloseable{
    public void print(){
        System.out.println("from Resource B print() ....");

    }

    @Override
    public void close() throws Exception {
        System.out.println("Resource B is Closing .....");
        throw new RuntimeException("Exception Occurred in Resource B print()");

    }
}
