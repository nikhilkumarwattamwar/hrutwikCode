package lambda.functional;

import java.util.function.BiFunction;
import java.util.function.Function;

public class FunctionInterfaceConcept {
    public static void main(String[] args) {
        Function<String,Integer> f=s -> s.length();
        System.out.println(f.apply("java"));

        Function<Integer,Integer> func=x->x*x;
        System.out.println(func.apply(10));

        int chain=f.andThen(func).apply("java");
        System.out.println(chain);

       int s= func.compose(f).apply("java");
        System.out.println(s);

        //Bi-Function - takes two arguments

        BiFunction<Integer,Integer,Integer>add=(a,b)->a+b;
        System.out.println(add.apply(4,5));

    }
}
