package GitFolder.hrutwikCode.JAVA_8;

import java.text.NumberFormat;
import java.util.*;
import java.util.stream.*;

public class Student {
    String name;

    int id;

    String subject;

    double percentage;

    public Student(String name, int id, String subject, double percentage)
    {
        this.name = name;
        this.id = id;
        this.subject = subject;
        this.percentage = percentage;
    }

    public String getName()
    {
        return name;
    }

    public int getId()
    {
        return id;
    }

    public String getSubject()
    {
        return subject;
    }

    public double getPercentage()
    {
        return percentage;
    }

    @Override
    public String toString()
    {
        return name+"-"+id+"-"+subject+"-"+percentage;
    }

}

 class Practice {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student("Amit", 101, "Math", 72.5),
                new Student("Neha", 102, "Science", 55.0),
                new Student("Ravi", 103, "English", 68.0),
                new Student("Priya", 104, "History", 45.3),
                new Student("Karan", 105, "Physics", 81.2),
                new Student("Pooja", 106, "Chemistry", 60.0),
                new Student("Rahul", 107, "Biology", 90.5)
        );



//        Given a list of students, write a Java 8 code to partition the students who got above 60% from those who didn’t?
        Map<Boolean , List<Student>> partitioned =students.stream().collect(Collectors.partitioningBy(s->s.getPercentage()>60));
        System.out.println("Above 60 : "+partitioned.get(true)+"\n Below 60 : "+partitioned.get(false));

//        Given a list of students, write a Java 8 code to get the names of top 3 performing students?
        List<Student> performingStudents =students.stream().sorted(Comparator.comparingDouble(Student::getPercentage).reversed()).limit(3).collect(Collectors.toList());
        System.out.println(performingStudents);

//        Given a list of students, how do you get the name and percentage of each student?
        Map<String,Double> eachStudent=students.stream().collect(Collectors.toMap(Student::getName,Student::getPercentage));
        System.out.println(eachStudent);

//        Given a list of students, how do you get the subjects offered in the college?
        List<String> subjeacts=students.stream().map(Student::getSubject).collect(Collectors.toList());
        System.out.println(subjeacts);

//        Given a list of students, write a Java 8 code to get highest, lowest and average percentage of students?
        DoubleSummaryStatistics avgPercentage =students.stream().collect(Collectors.summarizingDouble(Student::getPercentage));
        System.out.println("Average Percentage : "+avgPercentage.getAverage()+" Max Percentage : "+ avgPercentage.getMax()+" Min Percentage :"+avgPercentage.getMin());

//        How do you get total number of students from the given list of students?

       Long countStudent= students.stream().collect(Collectors.counting());
        System.out.println(countStudent);

//        How do you get the students grouped by subject from the given list of students?
       Map<String,List<Student>> groupedByStudent=students.stream().collect(Collectors.groupingBy(Student::getSubject));
        System.out.println(groupedByStudent);
    }


}

class Employee1
{
    int id;

    String name;

    int age;

    String gender;

    String department;

    int yearOfJoining;

    double salary;

    public Employee1(int id, String name, int age, String gender, String department, int yearOfJoining, double salary)
    {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.department = department;
        this.yearOfJoining = yearOfJoining;
        this.salary = salary;
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public int getAge()
    {
        return age;
    }

    public String getGender()
    {
        return gender;
    }

    public String getDepartment()
    {
        return department;
    }

    public int getYearOfJoining()
    {
        return yearOfJoining;
    }

    public double getSalary()
    {
        return salary;
    }

    @Override
    public String toString()
    {
        return "Id : "+id
                +", Name : "+name
                +", age : "+age
                +", Gender : "+gender
                +", Department : "+department
                +", Year Of Joining : "+yearOfJoining
                +", Salary : "+salary;
    }
}

 class EmployeeData {
    public static void main(String[] args) {

        List<Employee1> employeeList = Arrays.asList(
                new Employee1(122, "Ritika", 25, "Female", "Sales", 2015, 30000.0),
                new Employee1(133, "Amit", 29, "Male", "IT", 2012, 45000.0),
                new Employee1(144, "Priya", 28, "Female", "IT", 2014, 35000.0),
                new Employee1(111, "John", 32, "Male", "HR", 2011, 25000.0),
                new Employee1(155, "Rohan", 35, "Male", "Finance", 2010, 55000.0),
                new Employee1(166, "Meena", 31, "Female", "HR", 2013, 27000.0),
                new Employee1(177, "Vikram", 38, "Male", "Sales", 2009, 65000.0),
                new Employee1(188, "Anjali", 27, "Female", "Marketing", 2016, 32000.0),
                new Employee1(199, "Suresh", 30, "Male", "Marketing", 2014, 40000.0),
                new Employee1(200, "Pooja", 26, "Female", "Finance", 2018, 37000.0)
        );

//        Given a list of employees, write a Java 8 code to count the number of employees in each department?
        Map<String, Long> empEachDept=employeeList.stream().collect(Collectors.groupingBy(Employee1::getDepartment,Collectors.counting()));
        System.out.println(empEachDept);

//        Given a list of employees, find out the average salary of male and female employees?
       Map<String, DoubleSummaryStatistics> doubleSummaryStatistics= employeeList.stream().collect(Collectors.groupingBy(Employee1::getGender,Collectors.summarizingDouble(Employee1::getSalary)));
        System.out.println("Average of Male Salary "+doubleSummaryStatistics.get("Male").getAverage());
        System.out.println("Average of Female Salary "+doubleSummaryStatistics.get("Female").getAverage());

//        Write a Java 8 code to get the details of highest paid employee in the organization from the given list of employees?
       List<Employee1> highestPaid= employeeList.stream().sorted(Comparator.comparing(Employee1::getSalary).reversed()).limit(1).collect(Collectors.toList());
        System.out.println(highestPaid);

//        57) Write the Java 8 code to get the average age of each department in an organization?
       Map<String,IntSummaryStatistics> avgAgeOFDept =employeeList.stream().collect(Collectors.groupingBy(Employee1::getDepartment,Collectors.summarizingInt(Employee1::getAge)));
        System.out.println(avgAgeOFDept);

//        Given a list of employees, how do you find out who is the senior most employee in the organization?
       Map<String, List<Employee1>> name =employeeList.stream().sorted(Comparator.comparing(Employee1::getYearOfJoining)).limit(1).collect(Collectors.groupingBy(Employee1::getName));
        System.out.println(name);



    }
}

class p{
    public static void main(String[] args) {
        try{
            int a=10/0;
        }catch (ArithmeticException a){

        }

    }
}