public class Heart {
    void beat(){
        System.out.println("heart is beating");
    }
}

class Person1 {

    Heart heart = new Heart();
    void alive(){
        heart.beat();
        System.out.println("person1 is alive");
    }
}
