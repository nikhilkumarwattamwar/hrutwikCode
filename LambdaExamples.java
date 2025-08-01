package GitFolder.hrutwikCode;


import java.util.List;

interface SquareNumber{
    long square(int a);
}
interface Compare {
    boolean areEqual(String a, String b);
}
interface CheckEmptyString{
    boolean check(String a);
}
interface Add{
    int sum(int a,int b);
}
interface StringLenght{
    int length(String name);
}
interface EvenOddNumber{
    boolean checkEvenOdd(int a);
}
interface ConvertStringIntoUpperCase{
    String upperCase(String a);
}
public class LambdaExamples {
    public static void main(String[] args) {
        Runnable r=()-> System.out.println("Inside the Runnable");
        r.run();

        Add sumOfValues=(a,b)-> a+b;
        System.out.println("Sum of Values : "+sumOfValues.sum(10,30));

        StringLenght lenght=(a)->a.length();
        System.out.println("Length of String : "+lenght.length("Alice"));

        EvenOddNumber evenOddNumber=(a)-> a%2==0;
        System.out.println(evenOddNumber.checkEvenOdd(501)?"Number is Even ": "Number id Odd ");

        ConvertStringIntoUpperCase convertStringIntoUpperCase=(a)->a.toUpperCase();
        System.out.println(convertStringIntoUpperCase.upperCase("john"));

        List<Integer> nums = List.of(1, 2, 3, 4, 5);
        nums.forEach(n -> System.out.print(n+" "));

        System.out.println();
        SquareNumber squareNumber=(a)->a*a;
        System.out.println(squareNumber.square(5));

        CheckEmptyString emptyString=(a)->a.isEmpty();
        System.out.println(emptyString.check("")?"String is empty": "String is not empty");
        System.out.println(emptyString.check("7")?"String is empty": "String is not empty");

        Compare cmp = (a, b) -> a.equals(b);
        System.out.println("Are equal :" + cmp.areEqual("Java", "Java"));

    }
}
