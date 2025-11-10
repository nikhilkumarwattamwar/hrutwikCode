package lambda.functional;

import java.util.function.Supplier;

public class SupplierInterfaceConcept{
    public static void main(String[] args) {
        Supplier<String> s=()-> "hello";
        System.out.println(s.get());


        Supplier<Student> student=()-> new Student("ram",4);
        System.out.println(student.get());
    }
}
class Student{
    String name;
    int id;
    Student(String name,int id){
        this.name=name;
        this.id=id;
    }

    public String toString(){
        return name+": "+id;
    }
}