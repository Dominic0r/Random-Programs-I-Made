import java.util.*;
public class Main
{
    public static class socialClass{
        String name;
        int multiplier;
        int seats=0;
        
        //utility integers
        int util1, util2, util3, util4, util5;
        public socialClass(String name, int multiplier){
            this.name = name;
            this.multiplier = multiplier;
            util1=util2=util3=util4=util5=0;
        }
        
        public String getName(){
            return this.name;
        }
        
        public int getMult(){
            return this.multiplier;
        }
        
        public void addSeat(){
            seats++;
        }
        
        public void resetSeats(){
            seats = 0;
        }
    }
    public static class Party{
        Map<socialClass, Integer> classSup = new HashMap<>();
        String name, ideology;
        int seats = 0;
        //utility integers
        int util1, util2, util3, util4, util5;
        public Party(String name, String ideology){
            this.name = name;
            this.ideology = ideology;
            util1=util2=util3=util4=util5=0;
        }
        
        public void addClass(socialClass classToAdd, int sup){
            classSup.put(classToAdd, sup);
        }
        
        public void addSeat(){
            seats++;
        }
        
        public void resetSeats(){
            seats = 0;
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
            
    allClasses.add(upper);
    allClasses.add(newMid);
    allClasses.add(oldMid);
    allClasses.add(urban);
    allClasses.add(rural);
    }
    
    public static Party NUP = new Party("National Unity Party", "Big-Tent Reactionary");
    public static Party REP = new Party("Republican Party", "Big-Tent Republicanism");
    public static Party LRP = new Party("Left Republican Party", "Big-Tent Socialism");
    
    public static void addClassesToParties(){
        NUP.addClass(upper, 5);
        NUP.addClass(newMid, 0);
        NUP.addClass(oldMid, 3);
        NUP.addClass(urban, 0);
        NUP.addClass(rural, 2);
        
        REP.addClass(upper, 2);
        REP.addClass(newMid, 5);
        REP.addClass(oldMid, 0);
        REP.addClass(urban, 4);
        REP.addClass(rural, 1);
        
        LRP.addClass(upper, 0);
        LRP.addClass(newMid, 0);
        LRP.addClass(oldMid, 0);
        LRP.addClass(urban, 2);
        LRP.addClass(rural, 0);
        
    
    
    }
    
    public static List<socialClass> allClasses = new ArrayList<>();
    
    public static List<Party> allParties = new ArrayList<>();
    
    
    public static void declareParties(){
        allParties.add(NUP);
    allParties.add(REP);
    allParties.add(LRP);
    }
    public static void allocSeatsToClasses(){
        for(socialClass soc: allClasses){soc.util1=0;}
        // util1 will be used to determine how many points a class has
        for(socialClass soc : allClasses){
            
            for(Party par: allParties){
                soc.util1+= par.classSup.get(soc)*soc.multiplier;
            }
            
            soc.resetSeats();
        }
        
        int maxnum=0;
        socialClass maxClass = null;
        int curpoints = 0;
        for(int i=0; i<100;i++){
            for(socialClass soc : allClasses){
                
                curpoints = (soc.util1*100)/(soc.seats+1);
                
                if(curpoints> maxnum){
                    //System.out.println(i);
                    //System.out.println(soc.name+ " "+curpoints+" "+ soc.seats);
                    
                    maxnum = curpoints;
                    maxClass = soc;
                }
            }
            maxClass.addSeat();
            maxnum =0;
            maxClass = null;
        }
        
    }
    
    public static void election(){
        int maxnum =0;
        Party maxpar = null;
        
        for(Party par: allParties){par.resetSeats();par.util1=0;}
        
        int curpoints = 0;
        
        
        
        for(socialClass soc : allClasses){
            for(Party par: allParties){par.util1=0;}
            if(soc.seats>0){
                for(int i=0; i< soc.seats;i++){
                    maxnum = 0;
                    maxpar = null;
                    // util1 will be used as the "current seats in current class" variable
                    for(Party par: allParties){
                        curpoints = (par.classSup.get(soc)*soc.seats)/(par.util1+1);
                        curpoints += curpoints/2;
                        
                        if(curpoints> maxnum){
                            maxnum = curpoints;
                            maxpar =par;
                        }
                    }
                    
                    maxpar.util1++;
                    maxnum = 0;
                    maxpar = null;
                }
                for(Party par: allParties){
                    par.seats+=par.util1;
                }
            }
        }
        
        System.out.println("Election Results:");
        for(Party par: allParties){
            System.out.println(par.name+ ": "+ par.seats+ "%");
        }
    }
    
	public static void main(String[] args) {
		declareClasses();
		declareParties();
		addClassesToParties();
		allocSeatsToClasses();
		election();
	}
}
