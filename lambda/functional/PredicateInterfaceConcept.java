package lambda.functional;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PredicateInterfaceConcept {
    public static void main(String[] args) {
        Predicate<String> p=s1->s1.length()<10;
        System.out.println(p.test("checking"));


        List<Integer> list= Arrays.asList(11,22,33,44,55);

        Predicate<Integer> pre= x->x>20 && x<50;
        List<Integer> list1=list.stream().filter(pre).collect(Collectors.toList());
        System.out.println(list1);

        List<String> names=Arrays.asList("ram","reem","sam","gia","anna");

        Predicate<String> length=s->s.length()>3;
        List<String> namesList=names.stream().filter(length.negate()).collect(Collectors.toList());
        System.out.println(namesList);
    }
}
