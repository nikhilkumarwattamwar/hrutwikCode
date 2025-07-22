package Collection.LinkedList;

import CustomException.Chained2;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ManualOpration {
    public static void main(String[] args) {
        addingAndDisplay();
    }
    static char toUpper(char c){
        return (char) (c-('a'-'A'));
    }

    static  void addingAndDisplay(){
        List<String> names=new LinkedList<>();
        names.add("alice");
        names.add("bob");
        names.add("charlie");
        names.add("david");
        names.add("Eve");
        names.add("Frank");
        names.add("Grace");
        names.add("Heidi");
        names.add("Ivan");
        names.add("Judy");

        for (int i=0;i<names.size();i++){
            System.out.print("  "+names.get(i));
        }
        for (int i=names.size()-1;i>0;i--){
            if (i%2==0){
                names.remove(i);
            }
        }
        boolean flag=true;
        for (int i=0;i<names.size();i++){
            String name=names.get(i);
            String capitalized = Character.toUpperCase(name.charAt(0)) + name.substring(1).toLowerCase();

            names.set(i,capitalized);
        }
        System.out.println(names);
        Iterator<String> iterator = names.iterator();
        while (iterator.hasNext()) {
            String word = iterator.next().toLowerCase();
            if ("aeiou".indexOf(word.charAt(0)) != -1) {
                iterator.remove();
            }
        }
        System.out.println(names);


    }
}
