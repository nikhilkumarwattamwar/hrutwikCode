package lambda.functional;

public interface Square {
    public int multiple(int x);
    static void m(){
        System.out.println("static method");
    }
}

class Demo{
    public static void main(String[] args) {
        Square s=(x)->x*x;
        System.out.println(s.multiple(20));
        Square.m();
    }

}
