package GitFolder.hrutwikCode.comparator;

import java.util.*;
import java.util.Comparator;

public class CustomComparator {
    public static void main(String[] args) {

        List<Person> people = new ArrayList<>();
        people.add(new Person("Hrutwik", 50000));
        people.add(new Person("Amit", 45000));
        people.add(new Person("Sonal", 55000));
        people.add(  new Person("Xavi", 30000));
        people.add( new Person("AAAAAAAAAAAAAA", 60000));
//        Comparator<Person> comparator=new Comparator<Person>() {
//            @Override
//            public int compare(Person o1, Person o2) {
//                return (o1.name.compareTo(o2.name));
//            }
//        };

       people.sort(Comparator.comparing(p->p.name));
        System.out.println(people);
    }
}

class Person{
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSal() {
        return sal;
    }

    public void setSal(int sal) {
        this.sal = sal;
    }

    int sal;

    public Person(String name, int sal) {
        this.name = name;
        this.sal = sal;
    }

    @Override
    public String toString() {
        return "Name : "+name+" Salart : "+sal;
    }
}
class ComparableExamples{
    public static void main(String[] args) {

        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Hrutwik", 50000));
        employees.add(new Employee("Amit", 45000));
        employees.add(new Employee("Sonal", 55000));
        employees.add(  new Employee("Xavi", 30000));
        employees.add( new Employee("AAAAAAAAAAAAAA", 60000));

        Collections.sort(employees);
        System.out.println(employees);
    }
}
class Employee implements Comparable<Employee>{
    String name;
    int id;

    public Employee(String name, int sal) {
        this.name = name;
        this.id = sal;
    }

    @Override
    public String toString() {
        return "Name : "+name+" ID : "+id;
    }

    @Override
    public int compareTo(Employee o) {
        return o.name.length()-this.name.length();
    }
}
