package StreamAPI;

import java.util.Arrays;
import java.util.List;

public class SumOfAllEvenNumbers {
    public static void main(String[] args) {
        List<Integer> numbers= Arrays.asList(14,2,5,4,1,4,1,4,7,5,4,8,48);
       long x= numbers.stream()
                .distinct()
               .filter(a->a%2==0)
                .reduce(0,(a,b)-> a+b);
        System.out.println(x);
    }
}
