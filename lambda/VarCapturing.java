package lambda;

public class VarCapturing {
    int a=10;
    Runnable r=()-> {
        System.out.println("capturing :"+a);
        a++;
        System.out.println(a);
    };

    void display(){
         String name="sam";
        Runnable r2=()->{
            System.out.println(name);
//            name="reem";  to main consistency lamda exp can access local var of method only
//                          when they are final or effectively final bcz lamda might live longer that method
        };
        r2.run();
    }

    public static void main(String[] args) {
        VarCapturing obj= new VarCapturing();
        obj.r.run();
        obj.display();
    }
}
