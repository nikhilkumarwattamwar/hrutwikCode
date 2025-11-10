package lambda.functional;

import java.util.function.Function;

public class Person {
    String name;
    Person(String name){
        this.name=name;
    }
    public String toString(){
        return name;
    }

    public static void main(String[] args) {
        Function<Person,String> modify=e->"Hello "+e.name;

        Person p = new Person("sam");
        System.out.println(modify.apply(p));
    }
}
