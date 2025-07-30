package GitFolder.hrutwikCode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ConvertIntoUpperCase {
    public static void main(String[] args) {
        List<String > names= Arrays.asList("hrutwik","munna");
      List<String> upperNames=  names.stream()
                .map(a->a.toUpperCase())
                .collect(Collectors.toList());
        System.out.println(upperNames);
    }
}
