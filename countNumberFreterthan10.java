package StreamAPI;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class countNumberFreterthan10 {
    public static void main(String[] args) {
        List<Integer> numbers= Arrays.asList(1,1,2,8,8,7,4,6,55,66,4,2,3,66,5,223,25,23,6);
//        List<Integer>
             long  a1=  numbers.stream()
                .filter(a->a>10)
                        .distinct().count();
//                .collect(Collectors.toList());
        System.out.println(a1);
    }
}
