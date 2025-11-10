package lambda;

public class LambdaExample {
    public static void main(String[] args) {
        Shape s= new Shape() {
            @Override
            public void draw(String name) {
                System.out.println("this iss a "+name);
            }
        };
        s.draw("circle");

    Shape circle=(String name)-> System.out.println("this is "+name);
    circle.draw("circle");
    }

}
interface Shape{
    void draw(String name);
}

//class Circle implements Shape{
//    public void draw(String name){
//        System.out.println("this is"+name);
//    }
//}