package GitFolder.hrutwikCode;

import jdk.jfr.Frequency;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

//        Who has the most working experience in the organization?
        Employee mostExp= employeeList.stream().sorted(Comparator.comparing(Employee::getYearOfJoining)).findFirst().get();
        //Optimize way
//        Employee mostExp=employeeList.stream().min(Comparator.comparing(Employee::getYearOfJoining)).orElse(null);
        System.out.println("Who has the most working experience in the organization : "+mostExp);

//        How many male and female employees are there in the sales and marketing team?
        Map<String,Long> maleAndFemaleEmployeesSales=employeeList.stream().filter(i->i.department.equals("Sales And Marketing")).collect(Collectors.groupingBy(Employee::getGender,Collectors.counting()));
        System.out.println("male and female employees are there in the sales and marketing team : "+maleAndFemaleEmployeesSales);


//        What is the average salary of male and female employees?
        Map<String,Double> avgSalMalNFemale=employeeList.stream().collect(Collectors.groupingBy(Employee::getGender,Collectors.averagingDouble(Employee::getSalary)));
        System.out.println("What is the average salary of male and female employees? : "+avgSalMalNFemale);

//        List down the names of all employees in each department?
        Map<String,List<String>> namesOfEmpEachDept=employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.mapping(Employee::getName,Collectors.toList())));
        System.out.println("names of all employees in each department : "+namesOfEmpEachDept);
        for (Map.Entry<String,List<String>> names:namesOfEmpEachDept.entrySet()){
            System.out.println("---------------------------------------");
            System.out.println("Dept Name : "+names.getKey());
            System.out.println("---------------------------------------");
            for (String name: names.getValue()){
                System.out.println("Names : "+name);
            }
        }


//        Given a list of integers, find all elements that appear more than once, preserving the order of their first occurrence.
        List<Integer> numbers=Arrays.asList(1, 3, 5, 3, 7, 1, 9, 3);

        Set<Integer> added=new HashSet<>();

//        List<Integer> res=numbers.stream().filter(n->Collections.frequency(numbers,n)>1).filter(added::add).collect(Collectors.toList());
        Set<Integer> res=numbers.stream().filter(n->!added.add(n)).collect(Collectors.toSet());
        System.out.println(res);

//        Given a paragraph string, find the top 3 most frequent words ignoring case and punctuation.
        String str="Java is great . Java is powerful . Streams are powerful in Java .";
//        Map<String,Long> feqEmelemnt=Stream.of(str.split(" ")).collect(Collectors.groupingBy(String::toLowerCase,Collectors.counting()));
        Map<String,Long> feqEmelemnt=Arrays.stream(
                        str.toLowerCase()               // ignore case
                                .replaceAll("[^a-z\\s]", "")  // remove punctuation
                                .split("\\s+"))               // split by whitespace
                .collect(Collectors.groupingBy(word -> word, Collectors.counting()));
        System.out.println(feqEmelemnt);
        feqEmelemnt.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).limit(3).forEach(s-> System.out.println(s.getKey()));

        List<String> list=Arrays.asList("Java", "", "Streams", null, "API");
        String concat =list.stream().filter(Objects::nonNull).filter(s->!s.isEmpty()).collect(Collectors.joining("-"));
        System.out.println(concat);


//        Character Frequency Counter
        String para="Java Streams API";
        Map<Character,Long> charCount=para.chars().mapToObj(c->(char)c).map(Character::toLowerCase).filter(Character::isLetter).collect(Collectors.groupingBy(Function.identity(),Collectors.counting() ));
        System.out.println(charCount);

//        Longest Word in a Sentence
        String sentence = "Java Stream API makes life easiers";
         String longesrWord=Stream.of(sentence.split(" ")).max(Comparator.comparing(String::length)).get();
        System.out.println(longesrWord);

//        Anagram Checker
        String s1 = "Listen";
        String s2 = "Silent";
        String res1=Stream.of(s1.split("")).map(String::toUpperCase).sorted(Comparator.reverseOrder()).collect(Collectors.joining());
        String res2=Stream.of(s2.split("")).map(String::toUpperCase).sorted(Comparator.reverseOrder()).collect(Collectors.joining());
        System.out.println(res2.equals(res1));

//        Palindrome Words in a Sentence
        String palindromeSentence = "Madam teaches civic duties";
       String  rev= Stream.of(palindromeSentence.split(" ")).map(m->new StringBuilder(m).reverse()).collect(Collectors.joining(" "));
       Set<String> palindromeWords=Stream.of(palindromeSentence.split(" ")).map(String::toUpperCase).filter(w->w.equals(new StringBuilder(w).reverse().toString())).collect(Collectors.toSet());
        System.out.println(rev);
        System.out.println(palindromeWords);

//        Most Frequent Word

        String text = "Java is great and Java is fun";
        Map.Entry<String, Long> mostFeq=Stream.of(text.split(" ")).map(String::toUpperCase).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
                .entrySet().stream().max(Map.Entry.comparingByValue()).get();

        String lenght=Stream.of(text.split(" ")).max(Comparator.comparing(String::length)).get();

        System.out.println(mostFeq);
        System.out.println(lenght);

        // Reverse Each Word
       List<String> revEachWord= Arrays.stream(text.split(" ")).map(x->new StringBuilder(x).reverse().toString()).collect(Collectors.toList());
        System.out.println(revEachWord);





    }



}
