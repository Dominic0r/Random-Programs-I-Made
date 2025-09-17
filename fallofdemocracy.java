
import java.util.*;

public class Main
{
    public static Random ra = new Random();
    public static Scanner sc = new Scanner(System.in);
    static class Party{
        int seats;
        int support;
        int ideology;
        boolean IsRadical;
        String name;
        
        public Party(String name, boolean IsRadical, int ideology){
            this.name = name;
            this.IsRadical = IsRadical;
            this.ideology = ideology;
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
        Communists = new Party("Communist Party", true,0);
        Communists.addSupport(5);
        
        SocDems = new Party("Social Democratic Party", false,1);
        SocDems.addSupport(25);
        
        Liberals = new Party("Liberal Party", false,3);
        Liberals.addSupport(35);
        
        Cons = new Party("Conservative Party", false,5);
        Cons.addSupport(25);
        
        Fasc = new Party("Fascist Party", true,6);
        Fasc.addSupport(5);
        
        allParties.add(Communists);
    allParties.add(SocDems);
    allParties.add(Liberals);
    allParties.add(Cons);
    allParties.add(Fasc);
    }
    
    
    public static int year = 1925, quarter = 1;
    
    public static void election(){
        int total = 0;
        for(Party par : allParties){
            /*if(radicalism > 50){
                if(par.IsRadical){
                    par.support += (radicalism - 50)/10;
                }
            } else{
                if(!par.IsRadical){
                    par.support +=(50-radicalism)/10;
                }
            }*/
            total += par.support;
        }
        
        
        for(Party par : allParties){
            par.setSeats((par.support*100)/total);
        }
        snapUnderway = false;
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
            stability = (govSeats/2) + (30/partiesInGov)+ 20;
            return;
        }
        
        if(SocDems.seats + Liberals.seats > 50){
            govName = "Progress Coalition Government";
            partiesInGov = 2;
            hasRadicals = false;
            govIdeology = 2;
            govSeats = SocDems.seats + Liberals.seats;
            stability = (govSeats/2) + (30/partiesInGov)+ 20;
            return;
        }
        
        if(Cons.seats + Liberals.seats + SocDems.seats > 50){
            govName = "Unity Government";
            partiesInGov = 3;
            hasRadicals = false;
            govIdeology = 3;
            govSeats = Cons.seats + Liberals.seats + SocDems.seats;
            stability = (govSeats/2) + (30/partiesInGov)+ 20;
            return;
        }
        
        if(Cons.seats + Fasc.seats > 50){
            govName = "Rightist Government";
            partiesInGov = 2;
            hasRadicals = true;
            govIdeology = 5;
            govSeats = Cons.seats + Fasc.seats;
            stability = (govSeats/2) + (30/partiesInGov);
            return;
        }
        
        if(Communists.seats + SocDems.seats > 50){
            govName = "Leftist Government";
            partiesInGov = 2;
            hasRadicals = true;
            govIdeology = 1;
            govSeats = Communists.seats + SocDems.seats;
            stability = (govSeats/2) + (30/partiesInGov);
            return;
        }
        
        //toleration governments
        if(Cons.seats + Fasc.seats + Liberals.seats > 50){
            govName = "Rightist Government + Liberal Toleration";
            partiesInGov = 2;
            hasRadicals = false;
            govIdeology = 4;
            govSeats = Cons.seats + Fasc.seats;
            stability = (govSeats/2) + (30/partiesInGov);
            return;
        }
        
        if(Communists.seats + SocDems.seats+ Liberals.seats > 50){
            govName = "Leftist Government + Liberal Toleration";
            partiesInGov = 2;
            hasRadicals = false;
            govIdeology = 2;
            govSeats = Communists.seats + SocDems.seats;
            stability = (govSeats/2) + (30/partiesInGov);
            return;
        }
        
        
        radicalism += 10;
        stability -= 10;
        checkBounds();
        
    }
    
    public static void updateSupport(){
        int toAdd = 0;
        
        for(Party par: allParties){
            toAdd = 0;
            if(par.IsRadical){
                toAdd += (crisis - 50)/10;
                toAdd += (50- stability)/10;
                toAdd += radicalism/10;
                
            }else{
                toAdd += (50-crisis)/10;
                toAdd += (stability - 50)/10;
                toAdd -= radicalism/10;
            }
            
            toAdd += par.seats/10;
            
            toAdd += (6-Math.abs(par.ideology - govIdeology));
            toAdd += ra.nextInt(10);
            
            if(hasRadicals && par.ideology != govIdeology && par.IsRadical){
                toAdd /=10;
            }
            
            par.addSupport(toAdd);
            
            if(par.support< 0){
                par.support = 0;
            }
            
            
        }
    }
    
    public static void updateTime(){
        quarter++;
        if(quarter == 13){
            quarter = 1;
            year++;
        }
    }
    
    public static int electionCdown = 48;
    
    public static void countDown(){
        electionCdown--;
        if(electionCdown == 0){
            electionCdown = 48;
            election();
            formCoalition();
        }
    }
    
    public static void updateMisc(){
        if(crisis> 0){
            
            int factor = ra.nextInt(100);
            if(factor> stability){
                if(factor > stability+15){
                    crisis += stability/35;
                }else{
                    
                }
            }else{
                if(factor> stability-15){
                    
                }else{
                    crisis -= stability/35;
                }
                
            }
            
            if(crisis >100){
                crisis = 100;
            }
            if(crisis < 0){
                crisis = 0;
            }
        }
        
        if(crisis > 0){
            radicalism += (crisis/30) - (stability/25);
        }else{
            radicalism -= stability/40;
        }
        
        
        if(radicalism >100){
                radicalism = 100;
            }
            if(radicalism < 0){
                radicalism = 0;
            }
            
            
    }
    
    public static void crackDown(){
        if(partiesInGov == 1 && hasRadicals){
            for(Party par : allParties){
                if(par.ideology != govIdeology){
                    par.support /=2;
                }
                if(par.support< 0){
                par.support = 0;
            }
            }
        }
    }
    public static boolean snapUnderway = false;
    public static void checkSnap(){
        if(partiesInGov>1){
        if(ra.nextInt(100)> stability+ (stability/2) && !snapUnderway){
            radicalism+= 10;
            stability -= 10;
            electionCdown = 6;
            snapUnderway = true;
        }
        }
    }
    
    public static void events(){
        boolean hasHappened = false;
        /*if(ra.nextBoolean() & !hasHappened){ // crisis worsens
            hasHappened = true;
            crisis += 5;
        }
        
        if(ra.nextBoolean() & !hasHappened){ // crisis impoves
            hasHappened = true;
            crisis -= 5;
        }*/
        if(ra.nextBoolean() & !hasHappened){ // rally for democracy
            hasHappened = true;
            SocDems.addSupport(SocDems.support/2);
            Liberals.addSupport(Liberals.support/2);
            Cons.addSupport(Cons.support/2);
        }
        
        if(ra.nextBoolean() & !hasHappened){ // Communist prtest
            hasHappened = true;
            Communists.addSupport(Communists.support/2);
        }
        
        if(ra.nextBoolean() & !hasHappened){ // fascist protest
            hasHappened = true;
            Fasc.addSupport(Fasc.support/2);
        }
        
        
    }
    
    
    
	public static void main(String[] args) {
	    crisis = 100;
	    election();
		formCoalition();
	    while(true){
		System.out.println(year+ "/"+ quarter);
		//checkSnap();
		
		countDown();
		//events();
		updateMisc();
		updateSupport();
		crackDown();
		System.out.println("Governemnt in Power: "+ govName+ " - Seats: "+ govSeats+ "%");
		System.out.println("===================================");
		System.out.println("Government Stability: "+ stability);
		System.out.println("Crisis Level: "+ crisis);
		System.out.println("radicalism: "+ radicalism);
		System.out.println("===================================");
		
		for(Party par: allParties){
		    System.out.println(par);
		}
		
		sc.nextLine();
		updateTime();
	    }
	}
}
