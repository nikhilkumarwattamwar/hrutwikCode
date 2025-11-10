package lambda.functional;

import java.util.function.BinaryOperator;

public class BinaryOperatorConcept {
    public static void main(String[] args) {
        BinaryOperator<Integer> binaryOperator=(a,b)->a*b;
        System.out.println(binaryOperator.apply(6,7));

    }
}
