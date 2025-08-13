package GitFolder.hrutwikCode.JAVA_8;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PracticeExamples {
    public static void main(String[] args) {
        List<Integer> listOfIntegers = Arrays.asList(71, 18, 42,55,85,25, 21, 67, 32, 95, 14, 56, 87);
        List<String> listOfStrings = Arrays.asList("Java", "Python", "C#", "Java", "Kotlin", "Python");
        List<Double> decimalList = Arrays.asList(12.45, 23.58, 17.13, 42.89, 33.78, 71.85, 56.98, 21.12);

        //Given a list of integers, separate odd and even numbers?
        Map<Boolean,List<Integer>> evenOdd=listOfIntegers.stream().collect(Collectors.partitioningBy(i->i%2==0));
        for (Map.Entry<Boolean,List<Integer>> map: evenOdd.entrySet()){
            if (map.getKey()){
                System.out.println("Even Values :"+map.getValue());
            }else {
                System.out.println("Odd Values :"+map.getValue());
            }
        }

//        How do you remove duplicate elements from a list using Java 8 streams?
        List<String> removeDuplicate=listOfStrings.stream().distinct().collect(Collectors.toList());
        System.out.println(removeDuplicate);

//        How do you find frequency of each character in a string using Java 8 streams?
        String inputString = "Java Concept Of The Day Day";
        Map<Character,Long> lengthEachCharacter= inputString.chars().mapToObj(i->(char)i).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
        System.out.println(lengthEachCharacter);
//        How do you find frequency of each element in an array or a list?
//        Map<String,Long> lengthEachWord=Arrays.stream(inputString.split(" +")).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
        Map<String,Long> lengthEachWord=Arrays.stream(inputString.split("\\s+")).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
        System.out.println(lengthEachWord);
//        How do you find frequency of each element in an array or a list?
        Map<String,Long> frqByWord=listOfStrings.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
        System.out.println(frqByWord);

//        How do you sort the given list of decimals in reverse order?
//        List<Double> decimalInReverse=decimalList.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        List<Double> decimalInReverse=decimalList.stream().sorted((a,b)-> (int) (b-a)).collect(Collectors.toList());
        System.out.println("list of decimals in reverse order : "+decimalInReverse);

//        Given a list of strings, join the strings with ‘[‘ as prefix, ‘]’ as suffix and ‘,’ as delimiter?
        String joinTheStrings=listOfStrings.stream().collect(Collectors.joining(",","[","]"));
        System.out.println("join the strings with ‘[‘ as prefix, ‘]’ as suffix and ‘,’ as delimiter: "+joinTheStrings);

//        From the given list of integers, print the numbers which are multiples of 5?
        List<Integer> multiplesOf5=listOfIntegers.stream().filter(i->i%5==0).collect(Collectors.toList());
        System.out.println("multiples of 5 : "+multiplesOf5);

//        Given a list of integers, find maximum and minimum of those numbers?
//        List<Integer> min=listOfIntegers.stream().sorted().limit(1).collect(Collectors.toList());
        Optional<Integer> min=listOfIntegers.stream().min(Comparator.naturalOrder());
        System.out.println(listOfIntegers);
        System.out.println("minimum :"+min.get());
        int max=listOfIntegers.stream().max(Comparator.naturalOrder()).get();
        System.out.println("Maximun :"+max);

//        How do you merge two unsorted arrays into single sorted array using Java 8 streams?

        int[] a = new int[] {4, 2, 7, 1};
        int[] b = new int[] {8, 3, 9, 5};
        System.out.println("merge two unsorted arrays into single sorted array : ");
        int[] concat= IntStream.concat(Arrays.stream(a),Arrays.stream(b)).sorted().toArray();
        System.out.println(Arrays.toString(concat));

//        How do you get three maximum numbers and three minimum numbers from the given list of integers?

        System.out.println("3 Minimum numbers :");
        listOfIntegers.stream().sorted().limit(3).forEach(i-> System.out.print  (i+"\t"));
        System.out.println("\n3 mAximun numbers :");
        listOfIntegers.stream().sorted(Comparator.reverseOrder()).limit(3).forEach(i-> System.out.print(i+"\t"));

//        Java 8 program to check if two strings are anagrams or not?
        String s1 = "RaceCar";
        String s2 = "CarRace";
        s1= Stream.of(s1.split("")).map(String::toUpperCase).sorted().collect(Collectors.joining());
        s2=Stream.of(s2.split("")).map(String::toUpperCase).sorted().collect(Collectors.joining());
        if (s1.equals(s2)){
            System.out.println("\nIs Anagrams");
        }else {
            System.out.println("Not anagrams");
        }

//        Reverse each word of a string using Java 8 streams?
        String str = "Java Concept Of The Day";
        String reversedStr = Arrays.stream(str.split(" ")).map(word -> new StringBuffer(word).reverse()).collect(Collectors.joining(" "));
        System.out.println(reversedStr);

//        How do you find common elements between two arrays?
        List<Integer> list1 = Arrays.asList(71, 21, 34, 89, 56, 28);
        List<Integer> list2 = Arrays.asList(12, 56, 17, 21, 94, 34);
        list1.stream().filter(list2::contains).forEach(System.out::println);

//        Given an integer array, find sum and average of all elements?
        int sum=Arrays.stream(a).sum();
        System.out.println("sum of all elements?: "+sum);
        double avg=Arrays.stream(a).average().getAsDouble();
        System.out.println("Average of all elements? :"+avg);

//        How do you find sum of first 10 natural numbers?
        long sumFirst10NaturalNumbers=IntStream.range(1,11).sum();
        System.out.println("find sum of first 10 natural numbers : "+sumFirst10NaturalNumbers);


//        How do you get last element of an array?
        int name=list2.stream().skip(list2.size()-1).findFirst().get();
        System.out.println(name);

//        Find the age of a person in years if the birthday has given?
        LocalDate localDate=LocalDate.of(2000,8,1);
        LocalDate now=LocalDate.now();
        System.out.println(ChronoUnit.YEARS.between(localDate,now));

//        Who has the most working experience in the organization?








        // how many time e mentioned
//        String s="hrutwik says like like eeee ee ";
//       long c =Stream.of(s.split(" " )).filter(i->i.equals("e")).count();
//        System.out.println(count);
//        String rev =Stream.of(s.split("")).reduce("",(d,c)->c+d);
//        System.out.println(rev);
//        //
//        long count1=s.chars().filter(i->i=='').count();
//        System.out.println(count1);

    }


}