package oops;

public class Parent {
    public void method(){
        //overridden method
        System.out.println("im method of parent class");
    }
}
class Child extends Parent{
public void method(){
    // overriding method
    System.out.println("im method of child class");
}
}