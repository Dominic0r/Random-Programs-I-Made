import java.util.*;

public class Main
{
    public static class Person{
        String name;
        String color;
        // static - never changes throughout tournament
        // fluid - changes throughout tournament
        // dynamic - changes in rounds 
        
        int baseAim; // static - how well this person aims
        int personality; // static - their personality number
        int temper; // static - how much they can control their own emotions
        
        
        int kindness; // fluid - how kind this person is 
        int health; // fluid - how healthy they are, may be affected by injuries and such.
        int wins; // fluid - increases the more wins they have
        Map<Person, Integer> relations = new HashMap<>(); // fluid - their relationships with other contestants
        Map<Person, Integer> debt = new HashMap<>(); // fluid - whether they owe a person or not
        int calmness; // fluid - changes
        int sanity; // fluid
        
        
        int mercy; // dynamic - their mercy for a certain person 
        int nerves; // dynamic - increases the more steps taken and for every failed calmness check
        
        public Person(String name, String color, int baseAim, int personality, int temper, int kindness, int calmness, int sanity){
            this.name=name;
            this.color = color;
            this.baseAim=baseAim;
            this.personality=personality;
            this.temper=temper;
            this.kindness=kindness;
            this.calmness=calmness;
            this.sanity=sanity;
            
            this.health=100;
            this.wins=0;
            
        }
        
    }
    
	public static void main(String[] args) {
		System.out.println("Hello World");
	}
}
