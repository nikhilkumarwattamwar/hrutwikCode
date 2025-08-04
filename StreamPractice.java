package GitFolder.hrutwikCode;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamPractice {
    public static void main(String[] args) {

        List<String> names= Arrays.asList("Hrutwik","Sachin","Rahul","Virat","ss");
        List<Integer> numbers=List.of(1,2,4,7,8,7,5,6,9,9,554);
        /* Predicate examples */
        Predicate<Integer> isEven=n->n%2==0;
        List<Integer> usingPredicateEvenNumbers= numbers.stream().filter(isEven).collect(Collectors.toList());
        System.out.println("Using Predicate Find Even Numbers : "+usingPredicateEvenNumbers);

        Predicate<String> startWithS=n->n.startsWith("s");
        List<String> startWithSUsingPredicate= names.stream().map(String::toLowerCase).filter(startWithS).collect(Collectors.toList());
        System.out.println("Using Predicate Start With S :"+startWithSUsingPredicate);

        List<Integer> oddNumbersNegate = numbers.stream().distinct().filter(isEven.negate()).collect(Collectors.toList());
        System.out.println("Odd Numbers using negate() :"+oddNumbersNegate);

        Predicate<String> strLength=n->n.length()>5;
        List<String> greaterThanLength5=names.stream().filter(strLength).collect(Collectors.toList());
        System.out.println("Lentgh of String is greater than 5 :"+greaterThanLength5);

        List<String> usingAndMethod=names.stream().map(String::toLowerCase).filter(strLength.and(startWithS)).collect(Collectors.toList());
        System.out.println("Using and() :"+usingAndMethod);

        names.stream().filter(strLength.or(startWithS)).forEach(System.out::println);


        /* Consumer , Supplier Examples */
        Consumer<String> toUpperCase=n-> System.out.println(n.toUpperCase()+"\t");
        names.stream().forEach(toUpperCase);

        Consumer<Integer> squareOfNumbers=n-> System.out.println(n+" Square of :"+n*n);
        numbers.stream().distinct(). forEach(squareOfNumbers);

        Consumer<String> addCharacter=n-> System.out.println("Hello "+n);
        names.stream().forEach(addCharacter.andThen(toUpperCase));

        Supplier<String> greetSupplier = () -> "Hello, Hrutwik";
        System.out.println(greetSupplier.get());

        Supplier<Double> randomSupplier=()->  (Math.random()*100);
        Stream.generate(randomSupplier).limit(5).forEach(System.out::println);

        Supplier<List<String>> nameSupplier = () -> Arrays.asList("Hrutwik", "Amit", "Sonal", "", "Neha", "");
        Predicate<String> isNotEmpty = s -> !s.isEmpty();
        Consumer<String> printer = s -> System.out.println("NOt Empty : " + s);

        nameSupplier.get().stream().filter(isNotEmpty).forEach(printer);

        /* Funtion Example */

        Function<String,Integer> length=n->n.length();
        names.stream().map(length).forEach(System.out::println);

        Function<String,String> toUpper=String::toUpperCase;
        names.stream().map(toUpper).forEach(System.out::println);

        BiPredicate<String, Integer> matchLength = (s, l) -> s.length() == l;
        System.out.println(matchLength.test("Hrutwik", 7));

        BiFunction<String, String, String> concat = String::concat;
        System.out.println(concat.apply("Java", "Stream"));

        Map<String,Integer> map=names.stream().collect(Collectors.toMap(name ->name,l-> l.length()));
        System.out.println(map);

        long count= names.stream().collect(Collectors.counting());
        System.out.println("size of nameList : "+count);

        String join=names.stream().collect(Collectors.joining(" * ", "[", "]"));
        System.out.println("Joining by stars : "+join);

        IntSummaryStatistics s=numbers.stream().collect(Collectors.summarizingInt(i->i));
        System.out.println(s);

        int sumOfElement = numbers.stream().collect(Collectors.summingInt(i -> i));
        Double avgOfElement =  numbers.stream().collect(Collectors.averagingInt(i->i));
        System.out.println("Sum of Elements : "+sumOfElement+" : Average Of Elements : "+avgOfElement);

        Map<Integer, List<String>> groupedAndCounted =names.stream().collect(Collectors.groupingBy(String::length));
        System.out.println(groupedAndCounted);

        Map<Boolean, Long> evenOddCount = numbers.stream().distinct().collect(Collectors.partitioningBy(n -> n % 2 == 0, Collectors.counting()));
        Map<Boolean, List<Integer>> evenOdd = numbers.stream().distinct().collect(Collectors.partitioningBy(n -> n % 2 == 0));
        System.out.println(evenOddCount);
        System.out.println(evenOdd);

        Map<Integer, List<Character>> firstLettersByLength = names.stream()
                .collect(Collectors.groupingBy(String::length,Collectors.mapping(name -> Character.toUpperCase(name.charAt(0)), Collectors.toList())));
        System.out.println(firstLettersByLength);



    }
}
