package GitFolder.hrutwikCode.JAVA_8;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Java8Features {
    public static void main(String[] args) {
        List<String> names = List.of("Anil", "Bob", "Arun", "David");
        List<Integer> nums = List.of(4, 2, 8, 1);
        String countCourrances="kjhgffkgjkiuyth";
        List<List<Integer>> nested = List.of(
                List.of(1, 2),
                List.of(3, 4),
                List.of(5)
        );

        names.stream().filter(a->a.charAt(0)=='A').forEach(System.out::println);
       List<String> upperCaseNames= names.stream().map(a->a.toUpperCase()).collect(Collectors.toList());
        System.out.println(upperCaseNames);

        nums.stream().sorted(Comparator.reverseOrder()).forEach(System.out::print);
      long num=  nums.stream().filter(a->a%2==0).count();
        System.out.println("numbers of element :" +num);

        String findFirdt= String.valueOf(names.stream().filter(a->a.startsWith("A")).findFirst());
        System.out.println(findFirdt);

      Map<Integer, List<String>> byLentgth=  names.stream().collect(Collectors.groupingBy(String::length));
        System.out.println(byLentgth);

       long sum= nums.stream().reduce(0,(a,b)->a+b);
        System.out.println("sum of elements :"+sum );

       List<Integer> flatterns= nested.stream().flatMap(List::stream).collect(Collectors.toList());
        System.out.println(flatterns);


        Map<Character, Long> frequencyMap = countCourrances.chars() // IntStream
                .mapToObj(c -> (char) c) // Convert int to Character
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(frequencyMap);
        Optional<Map.Entry<Character, Long>> maxEntry = frequencyMap.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue());

        // Step 4: Display max occurring character
        if (maxEntry.isPresent()) {
            System.out.println("\nMost frequent character: " + maxEntry.get().getKey() +
                    " (occurs " + maxEntry.get().getValue() + " times)");
        }
    }
}
