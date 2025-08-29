package GitFolder.hrutwikCode;

import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StreamExampless {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 45,55,2,2,3,45, 3, 4, 5, 6,21,25);
        List<Integer> list2 = Arrays.asList(1, 45,55);
        List<String> names = Arrays.asList("hrutwik", "rahul", "kale","AkashDeep","Gill");
        List<List<String>> nested = Arrays.asList(
                Arrays.asList("a", "b"),
                Arrays.asList("c", "d")
        );

        List<Integer> evenNumbers=list.stream().filter(n->n%2==0).collect(Collectors.toList());
        System.out.println("Even Numbers From List : "+evenNumbers);

        List<Integer> squareNumbers=list.stream().map(n->n*n).collect(Collectors.toList());
        System.out.println("Square of Each Number : "+squareNumbers);


        int sumOfNumbers= list.stream().reduce(0,(a,b)->a+b);
        System.out.println("Some Of Numbers : "+sumOfNumbers);

        Optional<Integer> firstElementGreateThan10=list.stream().filter(n->n>10).findFirst();
        System.out.println("first element Greater Than 10 : "+" "+firstElementGreateThan10.get());

        Predicate<String> asName=(n)->n.equals("kale");

        List<String> convertIntoUpperCase=names.stream().filter(asName)
                .map(String::toUpperCase)
                .collect(Collectors.toList());;
        System.out.println("Converting into Upper Case : "+convertIntoUpperCase);

        Optional<String> nameStartWithh= names.stream().filter(a->a.startsWith("h")).findFirst();
        System.out.println("Name Start with h :"+nameStartWithh.get());

        List<Integer>  sortedList = list.stream()
                .sorted()
                .collect(Collectors.toList());
        List<String> sortedNames = names.stream().
                sorted()
                .collect(Collectors.toList());
        System.out.println("Sorted List Of Interger : "+sortedList);
        System.out.println("Sorted List Of Named : "+sortedNames);

       Optional<Integer> maxElement=list.stream()
                                            .max(Integer::compareTo);
       System.out.println("Max Element From List :"+maxElement.get());
       Optional<Integer> minElement=list.stream()
                                            .min(Integer::compareTo);
       System.out.println("Min Element From List : "+minElement.get());

       List<Integer>distinctElementFromList =list.stream().distinct().collect(Collectors.toList());
        System.out.println("Distinct Element From List :"+distinctElementFromList);

       Map<Object, List<String>> groupingByLengthOfString = names.stream().distinct().collect(Collectors.groupingBy(n-> n.length()));
       System.out.println("Grouping the String by length of string : "+groupingByLengthOfString);

       String joiningBy =names.stream().collect(Collectors.joining(","));
       System.out.println("Joining String By ',' "+joiningBy);

       Map<Boolean, List<Integer>> greaterThan10=list.stream().collect(Collectors.partitioningBy(n->n>10));
        System.out.println(greaterThan10);

        int product = list2.stream().reduce(1, (a, b) -> a * b);
        System.out.println("Multiplication of numbers :"+product);

        boolean allPositive = list.stream().allMatch(n -> n < 0);
        System.out.println(allPositive?"All elements satisfy Condition :"+allPositive:"Not Satisfy :"+allPositive);
        boolean hasEven = list.stream().anyMatch(n -> n % 2 == 0);
        System.out.println(hasEven +": one of the elements satisfy the condition");


    }
}
