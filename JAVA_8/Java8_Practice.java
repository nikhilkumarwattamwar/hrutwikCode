package GitFolder.hrutwikCode.JAVA_8;

import java.util.Arrays;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Java8_Practice {
    public static void main(String[] args) {
        //        How do you find sum of first 10 natural numbers?
        long sumFirst10NaturalNumbers= IntStream.range(1,11).sum();
        System.out.println("find sum of first 10 natural numbers : "+sumFirst10NaturalNumbers);

//        Reverse an integer array
        int arr[]={1,4,7,6,8,5,66};
        int[] reversedArray=IntStream.rangeClosed(1, arr.length).map(i -> arr[arr.length - i]).toArray();
        System.out.println(Arrays.toString(reversedArray));

//        Print first 10 even numbers
//        IntStream.rangeClosed(1,20).filter(i->i%2==0).forEach(System.out::println);
        IntStream.rangeClosed(1,10).map(i->i*2).forEach(System.out::println);

//        How do you find the most repeated element in an array?
        List<String> listOfStrings = Arrays.asList("Pen", "Eraser", "Note Book", "Pen", "Pencil", "Pen", "Note Book", "Pencil");
         Map<String,Long> repeatedElement =listOfStrings.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
        System.out.println(repeatedElement);
        Map.Entry<String, Long> maxElement= repeatedElement.entrySet().stream().max(Map.Entry.comparingByValue()).get();
        System.out.println("find the most repeated element in an array = "+maxElement.getKey()+" : "+maxElement.getValue());

//        Palindrome program using Java 8 streams

        String str="nitin";
        String palidrome=Stream.of(str.split("")).reduce("",(a,b)->b+a);
        System.out.println(str + " "+palidrome);
        System.out.println(palidrome.equals(str)?"palindrom ":"not palidron");

//        Given a list of strings, find out those strings which start with a number?
        List<String>  startNumber= Arrays.asList("One", "2wo", "3hree", "Four", "5ive", "Six");
//        startNumber.stream().filter(i->i.charAt(0)>45&& i.charAt(0)<55).forEach(System.out::println);
        startNumber.stream().filter(s -> Character.isDigit(s.charAt(0))).forEach(System.out::println);

//        How do you extract duplicate elements from an array?
        List<Integer> numbers = Arrays.asList(111, 222, 333, 111, 555, 333, 777, 222);
        Set<Integer> uniqueElements = new HashSet<>();
        numbers.stream().filter(i->!uniqueElements.add(i)).forEach(System.out::println);
        System.out.println(uniqueElements);

    }

}





