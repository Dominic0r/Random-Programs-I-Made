import java.util.*;
public class Main
{
    public static class Issue{
        String name;
        int popu; // the issue's popularity
        public Issue(String name, int popu){
            this.name = name;
            this.popu = popu;
        }
        
        public String Name(){
            return name;
        }
        public int Popularity(){
            return popu.
        }
    }
    
    public static class Party{
        String name;
        int seats;
        
        List<Issue> high = new ArrayList<>();
        List<Issue> medium = new ArrayList<>();
        List<Issue> low = new ArrayList<>();
        
        int res, com, ind; // zone demands - 3 = high, 2 = medium, 1= low, 0 no priority
        
        int lowClass, medClass, upClass;
        
        int seats = 0;
        int points = 0;
        
        public Party(String name, int res, int com, int ind, int lowClass, int medClass, int upClass){
            this.name = name;
            this.res = res;
            this.comn = com;
            this.ind = ind;
            this.lowClass = lowClass;
            this.medClass = medClass;
            this.upClass = upClass;
        }
        
        public void addToHigh(Issue toAdd){
            high.add(toAdd);
        }
        
        public void addToMed(Issue toAdd){
            medium.add(toAdd);
        }
        
        public void addToLow(Issue toAdd){
            low.add(toAdd);
        }
        
        public List<Issue> highList(){
            return high;
        }
        
        public List<Issue> medList(){
            return medium;
        }
        
        public List<Issue> lowList(){
            return low;
        }
        
        public int resiPriority(){
            return res;
        }
        
        public int comPriority(){
            return com;
        }
        
        public int induPriority(){
            return ind;
        }
        
        public int lowerClassPopularity(){
            return lowClass;
        }
        
        public int mediumClassPopularity(){
            return medClass;
        }
        
        public int upperClassPriority(){
            return upClass;
        }
        
        public void addSeat(){
            seats++;
        }
        
        public int Seats(){
            return seats;
        }
        
        public void addPoints(int toAdd){
            points+= toAdd;
        }
        
        public int Points(){
            return points;
        }
    }
    
    public static Issue culture = new Issue("Culture",0);
    public static Issue environment= new Issue("Environemnt",0);
    public static Issue police= new Issue("Police",0);
    public static Issue parks= new Issue("Parks",0);
    public static ublic Issue wasteDisposal= new Issue("Waste Disposal",0);
    public static Issue health= new Issue("Health",0);
    public static Issue fireBrigade= new Issue("Fire Brigades",0);
    public static Issue education= new Issue("Education",0);
    public static Issue sport= new Issue("Sport",0);
    public static Issue religion= new Issue("Religion",0);
    public static Issue transportation= new Issue("Transportation",0);
    public static Issue taxes= new Issue("Taxes",0);
    
    
    
    //                                                 Residential, Commercial, Industrial
    public static Party Cons = new Party ("Conservative Party",1,3,2);
    public static Party Libs = new Party ("Liberal Party", 2,3,1);
    public static Party Greens = new Party ("Green Party", 1,0,1);
    public static Party SocDems = new Party ("Social Democratic Party", 3,1,2);
    
    
    
    public static void addIssuesToParties(){
        Cons.addToHigh(taxes);
        Cons.addToHigh(religion);
        Cons.addToHigh(police);
        
        Cons.addToMed(culture);
        Cons.addToMed(sport);
        
        Cons.addToLow(wasteDisposal);
        
        
        Libs.addToHigh(education);
        Libs.addToHigh(culture);
        Libs.addToHigh(wasteDisposal);
        
        Libs.addToMed(parks);
        Libs.addToMed(taxes);
        Libs.addToMed(fireBrigade);
        
        Libs.addToLow(health);
        Libs.addToLow(sport);
        Libs.addToLow(police);
        
        
        Greens.addToHigh(environment);
        Greens.addToHigh(parks);
        
        Greens.addToMed(wasteDisposal);
        
        Greens.addToLow(transportation);
        
        SocDems.addToHigh(health);
        SocDems.add(education);
        SocDems.add(transportation);
        
        SocDems.addToMed(environment);
        SocDems.addToMed(parks);
        
        SocDems.addToLow(fireBrigade);
        SocDems.addToLow(wasteDisposal);
    }

    
	public static void main(String[] args) {
	    List<Party> allParties = new ArrayList<>();
	    allParties.add(Cons);
	    allParties.add(Libs);
	    allParties.add(Greens);
	    allParties.add(SocDems);
	    
		addIssuesToParties();
		
		// Section to give points
		//  Issues
		for(Party par: allParties){
		    
		}
		
	}
}
