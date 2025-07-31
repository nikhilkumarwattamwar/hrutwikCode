package oops.polymorphism;

public class Example {
    public static void main(String[] args) {
        Employee e = new Developer("max");
        e.work();
        Employee e1= new Employee("pete");
        e1.work();

    }
}
 class Employee{
    String name;
    Employee(String name){
        this.name= name;
        System.out.println("Employee constructor name :"+ name);
    }

    void work(){
        System.out.println(name+" is working");
    }
 }

 class Developer extends Employee{
    Developer(String name){
        super(name);
        System.out.println("Developer constructor name is : "+ name);
    }


     void work(){
        System.out.println(name+" is coding");
    }

 }