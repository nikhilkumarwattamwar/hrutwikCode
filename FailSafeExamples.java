package GitFolder.hrutwikCode;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class FailSafeExamples {
    public static void main(String[] args) {
        ConcurrentHashMap<Integer,String> concurrentHashMap=new ConcurrentHashMap<>();
        concurrentHashMap.put(1,"VIpul");
        concurrentHashMap.put(2,"Sunil");
        concurrentHashMap.put(3,"John");
        concurrentHashMap.put(4,"Tejas");

        Iterator<Map.Entry<Integer,String>> iterator=concurrentHashMap.entrySet().iterator();

        while (iterator.hasNext()){
            System.out.println(iterator.next());
            concurrentHashMap.put(5,"5");
            concurrentHashMap.remove(1); // it can able to perform operation concurrently because it works on clone map
        }
        System.out.println(concurrentHashMap);
    }
}

class FailSafeCopyOnWrite{
    public static void main(String[] args) {
        //A thread-safe variant of ArrayList in which all mutative operations (add, set, and so on)
        // are implemented by making a fresh copy of the underlying array.
        //This is ordinarily too costly, but may be more efficient than alternatives when traversal operations vastly outnumber mutations,
        // and is useful when you cannot or don't want to synchronize traversals, yet need to preclude interference among concurrent threads.
        CopyOnWriteArrayList<String> copy=new CopyOnWriteArrayList<>(List.of("john","vipul","sagar"));

        ListIterator<String> iterator=copy.listIterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
//            System.out.println(iterator.previous()); // if we add previous here it creates a loop
            copy.add("Joe");
        }
        System.out.println(copy);
        while (iterator.hasPrevious()){ // .hasPrevious()(backTracing is only possible when we are traversed forward )
            System.out.println(iterator.previous());
            copy.remove("Joe");
        }

        System.out.println(copy);
    }
}
 class FailSafeCOWArraySet {
    public static void main(String[] args) {
        CopyOnWriteArraySet<String> set = new CopyOnWriteArraySet<>(Set.of("Jokn", "Cena","Dolph"));
///It is best suited for applications in which set sizes generally stay small,
// read-only operations vastly outnumber mutative operations, and you need to prevent interference among threads during traversal.
//It is thread-safe.
//Mutative operations (add, set, remove, etc.) are expensive since they usually entail copying the entire underlying array.
//Iterators do not support the mutative remove operation.
//Traversal via iterators is fast and cannot encounter interference from other threads.
// Iterators rely on unchanging snapshots of the array at the time the iterators were constructed.
        Iterator<String> it = set.iterator();

        while (it.hasNext()) {
            String val = it.next();
            if (val.equals("Cena")) {
                set.remove(val);
            }
        }

        System.out.println(set);
    }
}
