package GitFolder.hrutwikCode;

import javax.sound.midi.Soundbank;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SequentialAndParallelExamples {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Hrutwik", "Virat", "Rahul", "Siraj");
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5);
        List<String> words = List.of("listen", "silent", "enlist", "rat", "tar", "art");


        list.stream().forEach(System.out::println);
//        list.parallelStream().forEach(s -> System.out.println("Parallel: " + Thread.currentThread().getName() + " -> " + s));
        list.parallelStream().forEach(s -> System.out.println("Parallel:  -> " + s));

        nums.stream().map(n->n*n).forEach(s-> System.out.println("Sequential   :"+s));
        nums.parallelStream().map(n->n*n).forEachOrdered(s-> System.out.println("Parallel  :"+s));

        int sequentialSum=nums.stream().mapToInt(i->i).sum();
        int parallelSum=nums.parallelStream().mapToInt(i->i).sum();
        System.out.println("Sequential Stream  :"+sequentialSum);
        System.out.println("Parallel Stream :"+parallelSum);

        long start = System.currentTimeMillis();
        IntStream.range(1, 10000).forEach(i -> {});
        System.out.println("Sequential Time: " + (System.currentTimeMillis() - start) + " ms");

        start = System.currentTimeMillis();
        IntStream.range(1, 10000).parallel().forEach(i -> {});
        System.out.println("Parallel Time: " + (System.currentTimeMillis() - start) + " ms");


        nums.stream().filter(n -> n % 2 == 0).forEach(n -> System.out.println("Sequential even: " + n));
        nums.parallelStream().filter(n -> n % 2 == 0).forEach(n -> System.out.println("Parallel even: " + n));

        list.stream().map(String::toUpperCase).forEach(s->System.out.println("Sequential UpperCase :"+s));
        list.parallelStream().map(String::toUpperCase).forEach(s->System.out.println("Parallel UpperCase :"+s));

        String sequentialJoining= list.stream().collect(Collectors.joining(","));
        String paralleJoining=list.parallelStream().collect(Collectors.joining(","));
        System.out.print("Sequential Joining :"+sequentialJoining+"\nParallel Joining :"+paralleJoining);

        long countSequential = list.stream().filter(w -> w.contains("a")).count();
        long countParallel = list.parallelStream().filter(w -> w.contains("a")).count();
        System.out.println("Sequential count: " + countSequential);
        System.out.println("Parallel count: " + countParallel);

        list.stream().sorted(Comparator.reverseOrder()).forEach(s-> System.out.print(s+"\t"));
        Map<Integer,List<String>> groupinBy=list.stream().collect(Collectors.groupingBy(String::length));
        System.out.println(groupinBy);

        Optional<Integer> max=nums.stream().max(Integer::compareTo);
        System.out.println("Max Element :"+max.get());

        list.stream().filter(a->a.startsWith("H")).forEach(System.out::println);
         Map<String,List<String>> anagram=words.stream().collect(Collectors.groupingBy(w->w.chars().sorted().mapToObj(a->String.valueOf((char) a)).collect(Collectors.joining())));
        System.out.println(anagram);

        List<String> name=Arrays.asList("hrutwik","heu","java","java8","eight");

      name.stream().filter(a->a.startsWith("j")).forEach(System.out::println);
//    System.out.println(namesJ);

    }
}
