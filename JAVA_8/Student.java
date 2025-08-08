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