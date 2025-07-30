package GitFolder.hrutwikCode;

import java.util.*;

public class FailFastExamples {
    public static void main(String[] args) {
        ArrayList<String> arrayList=new ArrayList<>(Arrays.asList("Vipul","KUnal","John","Alice"));
        Iterator<String> iterator=arrayList.iterator();
        while (iterator.hasNext()){
            if (iterator.next()=="John") {
                arrayList.remove(iterator.next()); // we use arraylist.remove() modCount will incremented it means collection has been modified
            }
            System.out.println(arrayList);
            iterator.remove(); // we use iterator.remove() then it will not give .ConcurrentModificationException because it internally modifies the modcount

        }

    }
}

class FailFastHAshMap{
    public static void main(String[] args) {
        HashMap<Integer,String> map=new HashMap<>();
        map.put(1,"Vipul");
        map.put(2,"john");
        map.put(3,"Alice");
        Iterator<Map.Entry<Integer,String>> iterator=map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<Integer,String> entry=iterator.next();
            System.out.println("ID :"+entry.getKey()+" Name:"+entry.getValue());
//            map.remove(entry.getKey());
            map.put(4,"ROman");

        }
        System.out.println(map);
    }
}

class FailFastHashSet{
    public static void main(String[] args) {
        HashSet<Integer> numbers =new HashSet<>(Arrays.asList(1,4,3,5,7,8,9,3));

        Iterator<Integer> iterator= numbers.iterator();
        while (iterator.hasNext()){

            int entry=iterator.next();
            if (entry==5){
                iterator.remove();//we can remove element with Iterator remove method
            }
//            System.out.println(entry);

//            numbers.add(25);
            numbers.remove(entry); // we can`t add remove the element while iteration over it.
        }
        numbers.add(255); // this will add to hashSet
        System.out.println(numbers);

    }
}

class FailFastLinkedList{
    public static void main(String[] args) {
        List<String> list=new LinkedList<>(List.of("john","Vipul","mukunad","Bijoy"));

        ListIterator<String> iterator= list.listIterator();

        while (iterator.hasNext()){
            System.out.println( iterator.next());
            list.add("Hello"); // we face exception java.util.ConcurrentModificationException
        }

        System.out.println("Traversing Backward :");
        while (iterator.hasPrevious()){
            System.out.println(iterator.previous());
        }
    }
}
