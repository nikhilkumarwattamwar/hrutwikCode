package GitFolder.hrutwikCode;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Employee {
    int id;

    String name;

    int age;

    String gender;

    String department;

    int yearOfJoining;

    double salary;

    public Employee(int id, String name, int age, String gender, String department, int yearOfJoining, double salary)
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

    public static void main(String[] args) {
        List<Employee> employeeList = new ArrayList<Employee>();

        employeeList.add(new Employee(111, "Jiya Brein", 32, "Female", "HR", 2011, 25000.0));
        employeeList.add(new Employee(122, "Paul Niksui", 25, "Male", "Sales And Marketing", 2015, 13500.0));
        employeeList.add(new Employee(133, "Martin Theron", 29, "Male", "Infrastructure", 2012, 18000.0));
        employeeList.add(new Employee(144, "Murali Gowda", 28, "Male", "Product Development", 2014, 32500.0));
        employeeList.add(new Employee(155, "Nima Roy", 27, "Female", "HR", 2013, 22700.0));
        employeeList.add(new Employee(166, "Iqbal Hussain", 43, "Male", "Security And Transport", 2016, 10500.0));
        employeeList.add(new Employee(177, "Manu Sharma", 35, "Male", "Account And Finance", 2010, 27000.0));
        employeeList.add(new Employee(188, "Wang Liu", 31, "Male", "Product Development", 2015, 34500.0));
        employeeList.add(new Employee(199, "Amelia Zoe", 24, "Female", "Sales And Marketing", 2016, 11500.0));
        employeeList.add(new Employee(200, "Jaden Dough", 38, "Male", "Security And Transport", 2015, 11000.5));
        employeeList.add(new Employee(211, "Jasna Kaur", 27, "Female", "Infrastructure", 2014, 15700.0));
        employeeList.add(new Employee(222, "Nitin Joshi", 25, "Male", "Product Development", 2016, 28200.0));
        employeeList.add(new Employee(233, "Jyothi Reddy", 27, "Female", "Account And Finance", 2013, 21300.0));
        employeeList.add(new Employee(244, "Nicolus Den", 24, "Male", "Sales And Marketing", 2017, 10700.5));
        employeeList.add(new Employee(255, "Ali Baig", 23, "Male", "Infrastructure", 2018, 12700.0));
        employeeList.add(new Employee(266, "Sanvi Pandey", 26, "Female", "Product Development", 2015, 28900.0));
        employeeList.add(new Employee(277, "Anuj Chettiar", 31, "Male", "Product Development", 2012, 35700.0));

//        How many male and female employees are there in the organization?
         Map<String, Long> maleAndFemaleEmployees =employeeList.stream().collect(Collectors.groupingBy(Employee::getGender,Collectors.counting()));
        System.out.println("male and female employees are there in the organization : "+maleAndFemaleEmployees);

//        Print the name of all departments in the organization?
        employeeList.stream().map(Employee::getDepartment).distinct().forEach(System.out::println);

//        What is the average age of male and female employees?
       Map<String,Double> averageAge=employeeList.stream().collect(Collectors.groupingBy(Employee::getGender,Collectors.averagingInt(Employee::getAge)));
        System.out.println("average age of male and female employees : "+averageAge);

//        Get the details of highest paid employee in the organization?
        Optional<Employee> maxSalary=employeeList.stream().max(Comparator.comparing(Employee::getSalary));
        System.out.println(maxSalary);

//        Get the names of all employees who have joined after 2015?
        List<String> after2015=employeeList.stream().filter(i->i.yearOfJoining>=2015).map(Employee::getName).collect(Collectors.toList());
        System.out.println("names of all employees who have joined after 2015 :"+after2015);

//        Count the number of employees in each department?
        Map<String,Long> numberOfEmployees=employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.counting()));
        System.out.println("Count the number of employees in each department : "+numberOfEmployees);

//        What is the average salary of each department?
        Map<String,Double> avgSalEachDept=employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.averagingDouble(Employee::getSalary)));
        System.out.println("average salary of each department : "+avgSalEachDept);

//        Get the details of youngest male employee in the product development department?
       Optional<Employee>  yougestDetails= employeeList.stream().filter(i->i.department.equals("Product Development") && i.getGender()=="Male").min(Comparator.comparingInt(Employee::getAge));
        System.out.println("details of youngest male employee in the product development departmen t : "+yougestDetails.get());

//        Who has the most working experience in the organization?
        Employee e=employeeList.stream().min(Comparator.comparingInt(Employee::getYearOfJoining)).get();
        System.out.println("Who has the most working experience in the organization : "+e);

//        How many male and female employees are there in the sales and marketing team?
        Map<String,Long>  empluyessInSalesAndMarketing= employeeList.stream().filter(i->i.department.equals("Sales And Marketing")).collect(Collectors.groupingBy(Employee::getGender,Collectors.counting()));
        System.out.println("How many male and female employees are there in the sales and marketing team : "+empluyessInSalesAndMarketing);

//        List down the names of all employees in each department?
        Map<String,List<String>>  namesOfAllEmployeesInEachDepartment=employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.mapping(Employee::getName,Collectors.toList())));
        System.out.println("List down the names of all employees in each department? : "+namesOfAllEmployeesInEachDepartment);

//        What is the average salary and total salary of the whole organization?
        DoubleSummaryStatistics doubleSummaryStatistics=employeeList.stream().collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println("Average salary : "+doubleSummaryStatistics.getAverage());
        System.out.println("Total Salary : "+doubleSummaryStatistics.getSum());

//        Separate the employees who are younger or equal to 25 years from those employees who are older than 25 years.
        Map<Boolean, List<Employee>> separateEmpMoreAgeThan25=employeeList.stream().collect(Collectors.partitioningBy(i->i.age>25));
        System.out.println("Older than 25:");
        separateEmpMoreAgeThan25.get(true).forEach(System.out::println);

        System.out.println("Younger or equal to 25:");
        separateEmpMoreAgeThan25.get(false).forEach(System.out::println);

        Map<Boolean, List<String>> NameONly25=employeeList.stream().collect(Collectors.partitioningBy(i->i.age>25,Collectors.mapping(Employee::getName,Collectors.toList())));
        System.out.println("Older than 25: " + NameONly25.get(true));
        System.out.println("Younger or equal to 25: " + NameONly25.get(false));

    }


}
