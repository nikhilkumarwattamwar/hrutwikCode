package ConstructorChaining;

public class Person {
    String name;
    Person(){
        this("john");
    }
    Person(String name){
        this.name=name;
        System.out.println("from Person Parameterized Constructor");
    }
    void show(){
        System.out.println("Name Of Employee:"+name);
    }
}
class Employee extends Person{
    String company;
    Employee(){
        this("HP");
    }
    Employee(String company){
        this(company,"john");
    }
    Employee(String company,String name){
        super(name);
        this.company=company;
        System.out.println("From Employee Constructor");
    }
    void show(){
        System.out.println("Name OF Employee:"+name+" Company:"+company);
    }
}
class Execution{
    public static void main(String[] args) {
        Employee e2=new Employee();
        e2.show();
        System.out.println("=============================================");
        Person p1= new Person();
        p1.show();
        System.out.println("=============================================");
        Person p2=new Employee("jk");
        p2.show();
        System.out.println("=============================================");
        Employee e3=new Employee("ACC","Bella");
        e3.show();
        System.out.println("=============================================");
        Employee e5=(Employee) p2;
        e5.show();


    }
}
