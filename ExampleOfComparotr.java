package GitFolder.hrutwikCode;

import java.util.*;

public class ExampleOfComparotr {
    public static void main(String[] args) {
        List<Person> personList= new ArrayList<Person>();
        personList.add(new Person("Vinod", 4));
        personList.add(new Person("Vikas", 9));
        personList.add(new Person("Z", 1));
        personList.add(new Person("VIP", 2));
        Comparator<Person> comparator=new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.name.compareTo(o2.name);
            }
        };
        Collections.sort(personList,comparator);
        System.out.println(personList);
        TreeMap<Person, String> personMap = new TreeMap<>(comparator);

        personMap.put(new Person("Vinod", 4000), "Tester");
        personMap.put(new Person("Vikas", 9000), "Developer");
        personMap.put(new Person("Z", 1000), "Intern");
        personMap.put(new Person("Aniket", 2000), "Manager");
        personMap.put(new Person("SameSalary", 2000), "HR");

        System.out.println(personMap);

    }
}

class Person{
    String name;
    int salary;

    Person(String name,int id){
        this.salary=id;
        this.name=name;

    };

    @Override
    public String toString() {
        return "Name-"+name+" : "+"Salary-"+salary+" ";
    }
}

