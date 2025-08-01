package GitFolder.hrutwikCode;

import javax.sound.midi.Soundbank;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.*;
import java.util.List;

public class UnmodifiableCollections {
    public static void main(String[] args) {
        List<String> names=new ArrayList<>();
        names.add("Sunil");
        names.add("Gopal");
        names.add("Vishwas");
        System.out.println("Orinal List :"+names);
       List<String> unmodifiableList= Collections.unmodifiableList(names);
        System.out.println("View Of Orirginal List :"+unmodifiableList);
        /* unmodifiableList.add("hk");  if we try to modify the unmodifiableCollection then it will Throws UnsupportedOperationException*/
        names.add("hk");
        System.out.println("After modifying the Original List it Reflects on unmodifiableList : "+unmodifiableList);

        Set<Integer> numbersSet=new HashSet<>();
        numbersSet.add(4);
        numbersSet.add(45);
        numbersSet.add(52);
        numbersSet.add(63);
        System.out.println("Orignal Set "+ numbersSet);
        Set<Integer> unmodifiableSet= Collections.unmodifiableSet(numbersSet);
        System.out.println("View of Orirginal Set : "+ unmodifiableSet);
        /* unmodifiableSet.add(500);  if we try to modify the unmodifiableCollection then it will Throws UnsupportedOperationException*/
        System.out.println(numbersSet.add(500)+ " After modifying the Original Set it Reflects on unmodifiableSet "+ unmodifiableSet);


        Map<String,Integer> map=new HashMap<>();
        map.put("Kunal",11);
        map.put("Sachin",12);
        map.put("Ganesh",13);
        map.put("Swapnil",14);
        System.out.println("Original Map : "+map);
        Map<String,Integer> unmodifiableMap=Collections.unmodifiableMap(map);
        System.out.println("View of Orirginal Map :  "+unmodifiableMap);
       /* unmodifiableMap.remove("Ganesh");if we try to remove the View Map then it will Throws UnsupportedOperationException*/

        map.remove("Ganesh");
        System.out.println("After Removing the element from Original Map : "+unmodifiableMap);

        /* Factory Methods of Collections */
        /*      Factory Methods are truly immutable we can not modify if we try to modify then it will give UnsupportedOperationException*/

        List<String> list=List.of("Alice","John","Tiger");
        /* list.add("Jonty"); we encounter the  UnsupportedOperationException Because we try to modify the list that is created usinb Factory Method*/
        System.out.println("List Created Using the Factory Method List.of() : "+list);

        Set<Integer> set=Set.of(1,4,5,7,8,6,66);
       /* set.add(54); we encounter the  UnsupportedOperationException Because we try to modify the Set that is created using Factory Method*/
        System.out.println("Set Created Using the Factory Method Set.of() : "+list);

        Map<String,Integer> factoryMap=Map.of("Alice",45,"John",45);
       /* factoryMap.remove("Alice"); we encounter the  UnsupportedOperationException Because we try to modify the Map that is created using Factory Method */
        System.out.println("Map Created Using the Factory Method Map.of() : "+factoryMap);

        List<Integer> arr=Arrays.asList(1,5,4,8,6);
        arr.set(1,45); /* Not Truly immutable it creates only fixed sized Array that can be able
        to update the elements but cannot be add/remove elements if we try then throws UnsupportedOperationException*/
        System.out.println(arr);



    }
}
