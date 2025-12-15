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
            return popu;
        }
    }
    
    public static class Party{
        String name;
        
        List<Issue> high = new ArrayList<>();
        List<Issue> medium = new ArrayList<>();
        List<Issue> low = new ArrayList<>();
        
        int res, com, ind; // zone priority - 3 = high, 2 = medium, 1= low, 0 no priority
        
        int lowClass, medClass, upClass;
        
        int seats = 0;
        int points = 0;
        
        public Party(String name, int res, int com, int ind, int lowClass, int medClass, int upClass){
            this.name = name;
            this.res = res;
            this.com = com;
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
        
        public String Name(){
            return name;
        }
        
        @Override
        public String toString(){
            return name+ " - "+ seats + " Seats";
        }
    }
    
    public static Issue culture = new Issue("Culture",3);
    public static Issue environment= new Issue("Environemnt",3);
    public static Issue police= new Issue("Police",3);
    public static Issue parks= new Issue("Parks",3);
    public static Issue wasteDisposal= new Issue("Waste Disposal",3);
    public static Issue health= new Issue("Health",3);
    public static Issue fireBrigade= new Issue("Fire Brigades",6);
    public static Issue education= new Issue("Education",6);
    public static Issue sport= new Issue("Sport",8);
    public static Issue religion= new Issue("Religion",12);
    public static Issue transportation= new Issue("Transportation",19);
    public static Issue taxes= new Issue("Taxes",31);
    
    
    
    //                                                 Residential, Commercial, Industrial
    public static Party Cons = new Party ("Conservative Party",1,3,2, 1,0,2);
    public static Party Libs = new Party ("Liberal Party", 2,3,1, 0,2,1);
    public static Party Greens = new Party ("Green Party", 1,0,1, 1,2,0);
    public static Party SocDems = new Party ("Social Democratic Party", 3,1,2, 2,1,0);
    
    
    public static int resiDemand = 5, commDemand = 5, induDemand = 1;
    
    public static int lowPercent = 310, medPercent = 11406, upPercent=31013;
    
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
        SocDems.addToHigh(education);
        SocDems.addToHigh(transportation);
        
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
	    
	    List<Issue> allIssues = new ArrayList<>();
	    allIssues.add(culture);
	    allIssues.add(environment);
	    allIssues.add(police);
	    allIssues.add(parks);
	    allIssues.add(wasteDisposal);
	    allIssues.add(health);
	    allIssues.add(fireBrigade);
	    allIssues.add(education);
	    allIssues.add(sport);
	    allIssues.add(religion);
	    allIssues.add(transportation);
	    allIssues.add(taxes);
	    
	    
		addIssuesToParties();
		
		// Section to give points
		//  Issues
		for(Party par: allParties){
		    for(Issue i : allIssues){
		        if(par.highList().contains(i)){
		            par.addPoints(i.Popularity()*3);
		        }
		        if(par.medList().contains(i)){
		            par.addPoints(i.Popularity()*2);
		        }
		        if(par.lowList().contains(i)){
		            par.addPoints(i.Popularity());
		        }
		    }
		}
		
		//  infrastructure
		for(Party par: allParties){
		    int toAdd = (par.resiPriority()*resiDemand)+ (par.induPriority()*induDemand)+ (par.comPriority()*commDemand);
		    par.addPoints(toAdd);
		    
		}
		
		//  Class
		for(Party par: allParties){
		    int toAdd = (par.lowerClassPopularity()*lowPercent)+ (par.mediumClassPopularity()*medPercent)+ (par.upperClassPriority()*upPercent);
		    par.addPoints(toAdd);
		}
		
		//Seat Distribution via D'hondt
		int maxnum = 0;
		Party maxPar = null;
		for(int i=0; i!=100;i++){
		    maxnum = 0;
		    maxPar = null;
		    for(Party par: allParties){
		        if((par.Points()*100)/(par.Seats()+1) > maxnum){
		            maxnum = (par.Points()*100)/(par.Seats()+1);
		            maxPar = par;
		        }
		    }
		    
		    if(maxPar !=null){
		        maxPar.addSeat();
		    }
		}
		
		
		System.out.println("Results:");
		for(Party par: allParties){
		    System.out.println(par);
		}
		String governmentFormed ="";
		for(Party par: allParties){
		    if(par.Seats()>50){
		        governmentFormed = par.Name()+ " Government";
		    }
		}
		int maxseats = 0;
		if(governmentFormed.equals("")){
		    if(Cons.Seats()+Libs.Seats() > 50 && Cons.Seats()+Libs.Seats()> maxseats){
		        maxseats = Cons.Seats()+Libs.Seats();
		        governmentFormed = "Conservative-Liberal Coalition";
		    }
		    
		    if(Libs.Seats()+Greens.Seats()> 50 && Libs.Seats()+Greens.Seats()> maxseats){
		        maxseats = Libs.Seats()+Greens.Seats();
		        governmentFormed = "Liberal-Green Coalition";
		    }
		    
		    if(Greens.Seats()+SocDems.Seats()> 50 && Greens.Seats()+SocDems.Seats() >maxseats){
		        maxseats = Greens.Seats()+SocDems.Seats();
		        governmentFormed = "Red-Green Coalition";
		    }
		}
		
		System.out.println("Government Formed: "+ governmentFormed);
	}
}
