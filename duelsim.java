import java.util.*;

public class Main
{
    public static class Person{
        String name;
        String color;
        // static - never changes in round 
        // dynamic - changes in round
        int aim; // static 
        
        //statuses that affect their aim
        int calmness; // dynamic
        int stress; // static 
        boolean isInjured // static 
        
        //statuses that affect others aim 
        int wins; // affects opponent calmness | static
        
        //statuses that affect whether they shoot or not 
        int mercy; // static 
        Map<Person, Integer> relations = new HashMap<>(); // static 
        int debt; // static 
        
        //personality statuses with no effect
        int personality; // static
        
        int trauma; // static
        
        public Person(String name, String color, int aim,){
            
        }
    }
    
	public static void main(String[] args) {
		System.out.println("Hello World");
	}
}
