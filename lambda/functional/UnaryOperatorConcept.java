package lambda.functional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.UnaryOperator;

public class UnaryOperatorConcept {
    public static void main(String[] args) {
        UnaryOperator<String> uo=s -> "hello "+s;
        System.out.println(uo.apply("java"));

        List<Integer> list = new ArrayList<>();
        list.addAll(Arrays.asList(34,56,26,75,4,76));

        list.replaceAll(x->x+1);
        System.out.println(list);
    }
}
