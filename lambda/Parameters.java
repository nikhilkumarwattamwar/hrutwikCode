package lambda;

public class  Parameters {
     static void fun(TestA t1){
        t1.print();}

        static void twoParameter(TestB tb,String name,int id){
            tb.details(name, id);
         }

    public static void main(String[] args) {
        TestA t=()-> System.out.println("hello");
                t.print();

       fun(()-> System.out.println("bye"));

       twoParameter((name,id)-> System.out.println(name+":"+id),"ram",10);
    }
}
interface TestA{
    void print();
}

interface TestB{
    void details(String name,int id);
}