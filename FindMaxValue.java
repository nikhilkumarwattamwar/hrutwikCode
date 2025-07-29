package GitFolder.hrutwikCode;

import java.util.Arrays;
import java.util.List;

public class FindMaxValue {
    public static void main(String[] args) {


        List<Integer> arr = Arrays.asList(4, 5, 7, 8, 9, 9, 99, 5, 245);
       int a1= arr.stream()
                .min(Integer::compareTo)
                .get();
        System.out.println(a1);
       int a2=arr.stream()
               .reduce(Integer.MIN_VALUE,(a,b)-> a>b?a:b);
        System.out.println(a2);

    }

}
