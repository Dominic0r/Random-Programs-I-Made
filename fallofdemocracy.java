import java.util.*;

public class Main
{
    public static Random ra = new Random();
    static class Party{
        int seats;
        int support;
        boolean IsRadical;
        String name;
        
        public Party(String name, boolean IsRadical){
            this.name = name;
            this.IsRadical = IsRadical;
        }
        
        public void setSeats(int newSeats){
            this.seats = newSeats;
        }
        
        public void addSupport(int toAdd){
            this.support += toAdd;
        }
        
        @Override
        
        public String toString(){
            return name + " "+ seats+ "%";
        }
    }
    
    public static int stability = 0, radicalism = 0, crisis = 0;
    
    public static List<Party> allParties = new ArrayList<>();
    
    public static Party Communists;
    public static Party SocDems;
    public static Party Liberals;
    public static Party Cons;
    public static Party Fasc;
    
    static{
        Communists = new Party("Communist Party", true);
        Communists.addSupport(5);
        
        SocDems = new Party("Social Democratic Party", false);
        SocDems.addSupport(25);
        
        Liberals = new Party("Liberal Party", false);
        Liberals.addSupport(35);
        
        Cons = new Party("Conservative Party", false);
        Cons.addSupport(30);
        
        Fasc = new Party("Fascist Party", true);
        Fasc.addSupport(5);
        
        allParties.add(Communists);
    allParties.add(SocDems);
    allParties.add(Liberals);
    allParties.add(Cons);
    allParties.add(Fasc);
    }
    
    
    public static int year = 1928, quarter = 1;
    
    public static void election(){
        int total = 0;
        for(Party par : allParties){
            if(radicalism > 50){
                if(par.IsRadical){
                    par.support += (radicalism - 50)/10;
                }
            } else{
                if(!par.IsRadical){
                    par.support +=(50-radicalism)/10;
                }
            }
            total += par.support;
        }
        
        
        for(Party par : allParties){
            par.setSeats((par.support*100)/total);
        }
    }
    
    public static void checkBounds(){
        if(stability > 100){
            stability = 100;
        }
        if(stability < 0){
            stability = 0;
        }
        
        if(crisis > 100){
            crisis = 100;
        }
        if(crisis < 0){
            crisis = 0;
        }
        
        if(radicalism > 100){
            radicalism = 100;
        }
        if(radicalism < 0){
            radicalism = 0;
        }
        
    }
    
    public static int partiesInGov = 0;
    public static boolean hasRadicals = false;
    public static int govIdeology = -1; // 0 - far-left, 1 = left-wing, 2 - center left, 3 - centrist, 4- center-right, 5 - right-wing, 6- far-right
    public static String govName = "";
    public static int govSeats=0;
    
    public static void formCoalition(){
        for(Party par: allParties){
            if(par.seats > 50){
                partiesInGov = 1;
                hasRadicals = false;
                
                
                if(par == Communists){
                    govIdeology = 0;
                        hasRadicals = true;
                        govName = "Communist Government";
                        govSeats = par.seats;
                }else if(par == SocDems){
                    govIdeology = 1;
                    govName = "Social Democratic Government";
                    govSeats = par.seats;
                }else if(par == Liberals){
                    govIdeology = 3;
                    govName = "Liberal Government";
                    govSeats = par.seats;
                }else if(par == Cons){
                    govIdeology = 5;
                    govName = "Conservative Government";
                    govSeats = par.seats;
                }else if(par == Fasc){
                     govIdeology = 6;
                        hasRadicals = true;
                        govName = "Fascist Government";
                        govSeats = par.seats;
                }
                
                return;
            }
        }
        
        if(Cons.seats + Liberals.seats > 50){
            govName = "National Coalition Government";
            partiesInGov = 2;
            hasRadicals = false;
            govIdeology = 4;
            govSeats = Cons.seats + Liberals.seats;
            return;
        }
        
        if(SocDems.seats + Liberals.seats > 50){
            govName = "Progress Coalition Government";
            partiesInGov = 2;
            hasRadicals = false;
            govIdeology = 2;
            govSeats = SocDems.seats + Liberals.seats;
            return;
        }
        
        if(Cons.seats + Liberals.seats + SocDems.seats > 50){
            govName = "Unity Government";
            partiesInGov = 3;
            hasRadicals = false;
            govIdeology = 3;
            govSeats = Cons.seats + Liberals.seats + SocDems.seats;
            return;
        }
        
        if(Cons.seats + Fasc.seats > 50){
            govName = "Rightist Government";
            partiesInGov = 2;
            hasRadicals = true;
            govIdeology = 5;
            govSeats = Cons.seats + Fasc.seats;
            return;
        }
        
        if(Communists.seats + SocDems.seats > 50){
            govName = "Leftist Government";
            partiesInGov = 2;
            hasRadicals = true;
            govIdeology = 1;
            govSeats = Communists.seats + SocDems.seats;
            return;
        }
        
        radicalism += 10;
        stability -= 10;
        checkBounds();
        election();
    }
    
    public static void updateSUpport(){
        int toAdd = 0;
        for(Party par: allParties){
            toAdd += 
        }
    }
    
	public static void main(String[] args) {
		System.out.println(year+ " "+ quarter);
		election();
		formCoalition();
		System.out.println("Governemnt in Power: "+ govName+ " - Seats: "+ govSeats+ "%");
		for(Party par: allParties){
		    System.out.println(par);
		}
	}
}
