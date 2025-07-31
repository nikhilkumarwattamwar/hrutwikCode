package oops;

public class Liscov {
    public void engine(){
        System.out.println("this vehicle has engine");

    }

    public void breaks(){
        System.out.println("this vehicle has breaks");
        }
    }

    class car extends Liscov {
        //override
        public void engine() {
            System.out.println("this car has engin");
        }
    }


         class Vehiclewithoutengine {
            public void breks() {
                System.out.println("this vehicle has breaks");
            }
        }

            class Cycle extends Vehiclewithoutengine {

                public void breks() {
                    System.out.println("this car has breaks");
                }

                public static void main(String[] args) {
                    Cycle obj= new Cycle();
                    obj.breks();
                }
            }



