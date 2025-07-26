package StreamAPI;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Sorted {
    public static void main(String[] args) {
        List<Integer> arr= Arrays.asList(1,1,2,3,8,4);
        List<Integer> a = arr.stream().sorted().collect(Collectors.toList());
        System.out.println(a);
    }
}
