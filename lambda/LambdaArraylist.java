package lambda;

import java.util.ArrayList;
import java.util.Arrays;

public class LambdaArraylist {
    public static void main(String[] args) {
        ArrayList al = new ArrayList();
        al.addAll(Arrays.asList(10,20,30,40));
        al.forEach(n-> System.out.println(n));

        for(int i=0;i< al.size();i++){
            System.out.println(al.get(i));
        }

        for(Object x:al){
            System.out.println(x);
        }

    }
}
