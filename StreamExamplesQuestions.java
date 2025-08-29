package GitFolder.hrutwikCode;

import java.util.*;
import java.util.stream.Collectors;

public class StreamExamplesQuestions {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Apple", "Avocado", "Banana", "Apricot");
        List<Integer> numbers = Arrays.asList(5, 3, 8, 1);



        /*     Find the Longest String */
       Optional<String> longestString= list.stream().max(Comparator.comparing(String::length));
        longestString.ifPresent(System.out::println);

        /*  Sort in Descending Order*/
        System.out.println("Elements Sorted :");
       numbers.stream().sorted(Comparator.reverseOrder()).forEach(System.out::print);

        /* Group by String Length */
        Map<Object,List<String>> byLength=list.stream().collect(Collectors.groupingBy(String::length));
        System.out.println("Elements Grouped By Length : "+byLength);

        /* Join Strings with Comma */
        String joiningByComma=list.stream().collect(Collectors.joining(","));
        System.out.println("Joining By Comma :"+joiningByComma);

        /* Find Max and Min */
        Optional<Integer> max=numbers.stream().max(Integer::compare);
        System.out.println("Max Element : "+max.get());
        Optional<Integer> min=numbers.stream().min(Integer::compareTo);
        System.out.println("Max Element : "+min.get());

        /* Average of a List */
        double avg= numbers.stream().mapToInt(i->i).average().orElse(0);
        System.out.println("Average of List "+numbers+" is :"+avg);

        /*  Partition Even and Odd */
        Map<Boolean,List<Integer>> partitioned=numbers.stream().collect(Collectors.partitioningBy(a->a%2==0));
        System.out.println("Partitioned the List Even N Odd :"+partitioned);

        Set<String> upperCaseSet=list.stream().map(String::toUpperCase).collect(Collectors.toSet());
        System.out.println("Converted String into UpperCase :"+upperCaseSet);

        /* Sum Of Squares is >5*/
        System.out.println("Sum Of Squares is > 5 : ");
        numbers.stream().map(n->n*n).filter(n->n>15).forEach(System.out::println);

    }
}
