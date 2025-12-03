import java.util.*;
public class Main
{
    public static class socialClass{
        String name;
        int multiplier;
        public socialClass(String name, int multiplier){
            this.name = name;
            this.multiplier = multiplier;
        }
        
        public String getName(){
            return this.name;
        }
        
        public int getMult(){
            return this.multiplier;
        }
    }
    public static class Party{
        Map<socialClass, int> classSup = new HashMap<>();
        String name, ideology;
        public Party(String name, String ideology){
            this.name = name;
            this.ideology = ideology;
        }
        
        public void addClass(socialClass classToAdd, int sup){
            classSup.put(classToAdd, sup);
        }
    }
    
    public static socialClass upper;
    public static socialClass newMid;
    public static socialClass oldMid;
    public static socialClass urban;
    public static socialClass rural;
    
    public static void declareClasses(){
        upper = new socialClass("Upper Class", 1);
        
        newMid = new socialClass("New Middle Class", 3);
        oldMid = new socialClass("Old Middle Class", 2);
        
        urban = new socialClass("Urban Working Class", 4);
        rural = new socialClass("Rural Working Class", 3);
    }
    
    public static Party NUP = new Party("National Unity Party", "Big-Tent Reactionary");
    public static Party REP = new Party("Republican Party", "Big-Tent Republicanism");
    public static Party LRP = new Party("Left Republican Party", "Big-Tent Socialism");
    
    public static void addClassesToParties(){
        NUP.addClass(upper, 5);
        NUP.addClass(newMid, 1);
        NUP.addClass(oldMid, 3);
        NUP.addClass(urban, 0);
        NUP.addClass(rural, 2);
        
        REP.addClass(upper, 2);
        REP.addClass(newMid, 5);
        REP.addClass(oldMid, 2);
        REP.addClass(urban, 3);
        REP.addClass(rural, 1);
        
        LRP.addClass(upper, 0);
        LRP.addClass(newMid, 2);
        LRP.addClass(oldMid, 0);
        LRP.addClass(urban, 5);
        LRP.addClass(rural, 2);
        
    }
	public static void main(String[] args) {
		System.out.println("Hello World");
	}
}
