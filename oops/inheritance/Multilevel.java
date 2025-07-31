package oops.inheritance;

public class Multilevel {
    public static void main(String[] args) {
        Puppy p= new Puppy();
        p.sound();
        Dog d= new Dog();
        d.sound();

    }
}
class Animal{
    int i =10;
    void sound(){
        System.out.println("Animal makes a sound");
    }
    void test(){
        System.out.println("Animal makes a test");
    }
}

class Dog extends Animal{

    void sound(){
        super.test();

        System.out.println("Dog barks"+i);
    }

}

class Puppy extends Dog{
    void sound(){
       super.sound(); //calls dogs sound
        System.out.println("puppy whines");
    }
}
