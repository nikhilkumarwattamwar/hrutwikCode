package StreamAPI;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GreaterThan20 {
    public static void main(String[] args) {
        List<Integer> arr= Arrays.asList(1,2,0,4,22,24,2,46,3,1,56,88,58,88);
        List<Integer> b=arr.stream()
                .distinct()
                .filter( a-> a>20).collect(Collectors.toList());
        System.out.println(b);
    }
}
