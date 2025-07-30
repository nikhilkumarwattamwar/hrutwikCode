package GitFolder.hrutwikCode;

import java.util.Arrays;
import java.util.List;

public class EvenOdd {

    public static void main(String[] args) {
        List<Integer> arr= Arrays.asList(4,5,7,8,8,7,4,6,9,9,7,4,5,2);
        arr.stream().distinct()
                .filter(n->n%2==1)
                .forEach(System.out::println);
    }
}
