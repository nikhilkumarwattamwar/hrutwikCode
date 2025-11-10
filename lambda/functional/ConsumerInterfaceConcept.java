package lambda.functional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerInterfaceConcept {
    public static void main(String[] args) {
        //takes one argument and returns nothing
        Consumer<Integer> consumer=(x)-> System.out.println(x);
        consumer.accept(10);

        List<Integer> list = new ArrayList<>();
        list.addAll(Arrays.asList(33,55,66,22,11));

        Consumer<List<Integer>> l1=(l)-> System.out.println(l);
        l1.accept(list);

        Consumer<List<Integer>>l2=(l)->{
            for(int i=0;i<l.size();i++){
                l.set(i,l.get(i)+1);
            }
        };
//        l2.accept(list);

        System.out.println("checkng andThen");
        l2.andThen(l1).accept(list);

        Consumer<List<Integer>> listFun=list1-> {
            for(Integer x:list1){
                System.out.println(x);
            }
        };
        listFun.accept(list);

        Consumer<List<Integer>> modify=modifying->{
            for(Integer x:modifying){
                System.out.println(x*2);
            }
        };
        modify.accept(list);
    }
}
