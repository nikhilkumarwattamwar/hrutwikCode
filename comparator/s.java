package GitFolder.hrutwikCode.comparator;

import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

 interface Sample {
    public static void main(String[] args) {

        Sample s1=new Sample() {};
        s1.a();
        System.out.println(s1.getClass());
        s1.a();
    }
    private void a(){
        System.out.println("hello from a");
    }
}
class b
{
    public static void main(String[] args) {

    }

}

class Pract{
    public static void main(String[] args) {
        // int []={4,5,7,5,5}

        List<Integer> list= List.of(4,5,8,1,2,5,5,990);
      list.stream().distinct().sorted().skip(1).collect(Collectors.toList());
//        System.out.println(a.get());
    }
}
//