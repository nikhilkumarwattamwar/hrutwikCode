package GitFolder.hrutwikCode;

import java.security.spec.ECField;
import java.util.*;
import java.util.Optional;

public class OptionalClassExamples {
    public static void main(String[] args) {
        Optional<String> name=Optional.of("Hrutwik");
        System.out.println(name);

        Optional<Integer> nullValues= Optional.ofNullable(null);
        Optional<Integer> values= Optional.ofNullable(452);
        System.out.println("Null values in Nullable : "+nullValues+" Values in Nullable : "+values);

        Optional<String> empty=Optional.empty();
        System.out.println(empty);

        Optional<String> nestedOptional=Optional.of(String.valueOf(name));
        System.out.println(nestedOptional+": nested Optional");
        System.out.println(" Flattens :"+nestedOptional.flatMap(s->Optional.of(s.toUpperCase())));
        Optional<String> upperCase=name.flatMap(a->Optional.of(a.toUpperCase()));
        System.out.println("Using FlatMap : "+upperCase);

        Optional<String> lowerCaseValues =name.map(a->a.toLowerCase());
        System.out.println("Using Map :"+lowerCaseValues);

        String getName=name.get();
        System.out.println("Using get() Fetching Values : "+getName);
        System.out.println("Using get() Null  Fetching Values : "+nullValues);

        int nameHash=name.hashCode();
        int nullValuesHash=nullValues.hashCode();
        System.out.println("hashCode() of null Values :"+nullValuesHash);
        System.out.println("hashCode() of name :"+nameHash);

        boolean isPresent=name.isPresent();
        System.out.println("Checking Values using isPresent() : "+nullValues.isPresent());
        System.out.println("Checking Values using isPresent() : "+isPresent);

        name.ifPresent(n -> System.out.println("ifPresent: Hello " + n+" Kale"));
        nullValues.ifPresent(n-> System.out.println("ifPresent : "+n));/* wont print anything becaues ifPresent returing flase */

        Optional<Integer> nameLennghtUsingMap=name.map(String::length);
        System.out.println("Length of "+name+" is :"+nameLennghtUsingMap.get());
        Optional<String> addedValuesIntoNullInstance=nullValues.map(a->a+" Adding value into the null optional using map :" );
        Optional<Optional<String>> addedValuesIntoNullInstanceUsingOF=nullValues.map(a->Optional.of(" Adding value into the null optional using map :" ));
        System.out.println(addedValuesIntoNullInstance); /* if Optional instance is empty then map will not run*/
        System.out.println(addedValuesIntoNullInstanceUsingOF); /* if Optional instance is empty then map will not run*/

        String usingOrElse=name.orElse("Default Values");
        System.out.println("Using the orElse() :"+usingOrElse);
        Integer usingOElseOnNullValues= nullValues.orElse(74);
        System.out.println("Using the orElse() :"+usingOElseOnNullValues);
        List<String> list = List.of("123", "abc", "456");
        for (String s:list){
           Optional<Integer> convertedValues= convertToInt(s);
            System.out.println( "String Values Parsed into Integer :"+convertedValues.orElse(-1));

        }

        String name2 = name.orElseGet(() -> "orElseGet Default");
        System.out.println("orElseGet: " + name2);


        String name3 = name.orElseThrow(() -> new RuntimeException("orElseThrow Name not found"));
        System.out.println("orElseThrow: " + name3);

        int nullVal=nullValues.orElseGet(()->455);
        System.out.println("Using Default orElseGet when there is null valiue :"+nullVal);

        Integer  orElseThrowValue= nullValues.orElseThrow(() -> new RuntimeException("orElseThrow Value not found"));
        System.out.println("orElseThrow: " + orElseThrowValue);


    }
   static  Optional<Integer>  convertToInt(String s){
        try
        {
            return Optional.of(Integer.parseInt(s));
        }catch (Exception e){
            System.out.println(e);
            return Optional.empty();
        }

    }
}

