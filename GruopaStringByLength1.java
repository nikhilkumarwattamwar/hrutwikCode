package StreamAPI;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GruopaStringByLength1 {
    public static void main(String[] args) {
        List<String> names= Arrays.asList("hrutwik","munna");
        Map<Integer, List<String>> name=  names.stream()
                .collect(Collectors.groupingBy(String::length));
        System.out.println(name);

    }
}
