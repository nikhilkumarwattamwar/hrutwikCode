package GitFolder.hrutwikCode.JAVA_8;

import java.util.*;
import java.util.stream.Collectors;

public class AdvanceExample {
    public static void main(String[] args) {
        List<String> names = List.of("Anil", "Amol", "Ravi", "Rahul", "Suresh");
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        List<List<Integer>> nested = List.of(
                List.of(1, 2),
                List.of(3, 4),
                List.of(5)
        );
        List<Employee> employees = List.of(
                new Employee("Hrutwik", 30000),
                new Employee("Anil", 50000),
                new Employee("Rahul", 40000)
        );


        Map<Character,List<String>> grouped=  names.stream().collect(Collectors.groupingBy(name->name.charAt(0)));
        System.out.println("Group names by their first character"+ grouped);

        Map<Boolean, List<Integer>> partitioned =numbers.stream().collect(Collectors.partitioningBy(a->a%2==0));
        System.out.println(" Partition list into even and odd "+ partitioned);

       String longest = names.stream().max(Comparator.comparing(String::length)).orElseGet(()-> { System.out.println("NO Names Found");return "";});
        System.out.println("Logest Stream :" +longest );
        System.out.println("==========================================================================================================================");

       List<Integer> flat= nested.stream().flatMap(List::stream).collect(Collectors.toList());
        System.out.println("Before Flattning the List "+ nested);
        System.out.println("After Flattning the List "+ flat);

        System.out.println("Count frequency of each word :");
      Map<Object, Long> countByCharacterFrequency = names.stream().flatMap(w->w.chars().mapToObj(c->(char) c)).collect(Collectors.groupingBy(a->a,Collectors.counting()));

        Map<Object, Long> countByWordFrequency= names.stream().collect(Collectors.groupingBy(w->w,Collectors.counting()));
                System.out.println("Count frequency of each word :" +countByWordFrequency);
                System.out.println("Count frequency of each Character :" +countByCharacterFrequency);




        System.out.println("==========================================================================================================================");
        System.out.println("Sort employees by salary");
      List<Employee> sortEmployeesBySalary=  employees.stream().sorted(Comparator.comparing(Employee::getName,Comparator.reverseOrder())).collect(Collectors.toList());
        System.out.println("Sort employees by salary"+sortEmployeesBySalary);

       List<Employee> highestPaid= employees.stream().sorted(Comparator.comparing(Employee::getSalary,Comparator.reverseOrder())).limit(2).collect(Collectors.toList());
        System.out.println("Get names of top 2 highest paid employees "+highestPaid);

       int someAllSalary= employees.stream().mapToInt(Employee::getSalary).sum();
        System.out.println(someAllSalary + " Sum of all salaries");
        double avgSalary=employees.stream().collect(Collectors.averagingInt(Employee::getSalary));
        System.out.println(avgSalary + " Avarage of all salaries");

        String joinNames=names.stream().collect(Collectors.joining(","));
        System.out.println(joinNames + " Join all names with commas");
    }
}

class Employee {
    String name;
    int salary;
    Employee(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }
    public String getName() { return name; }
    public int getSalary() { return salary; }

    @Override
    public String toString() {
        return "Name : "+name+" Salary :"+salary;
    }
}


