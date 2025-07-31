package oops.polymorphism;
//multiple method with same but different parameters
public class overloading {
    public int sum(int x , int y){
        return x+y;
    }

    public double sum(double x, double y , double z){
        return x+y+z;
    }

    public static void main(String[] args) {
        overloading ob= new overloading();
        System.out.println(ob.sum(5,8));
        System.out.println(ob.sum(5.6,6.4, 8.6));
    }
}
