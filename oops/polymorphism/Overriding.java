package oops.polymorphism;

public class Overriding {
    public static void main(String[] args) {

        Animal a = new Dog();
           a.makesound();
    }
}
class Animal{
    Animal(){
        System.out.println("Animal class constructor");
    }

    void makesound(){
        System.out.println("some animal make sound");
    }
}

class Dog extends Animal{
    Dog(){
        System.out.println("Dog class constructor");
    }

    @Override
    void makesound(){
        System.out.println("Bark");
    }
}