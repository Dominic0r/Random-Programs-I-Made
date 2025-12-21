import java.util.*;
public class Main
{
    public static class Issue{
        String name;
        int popu; // the issue's popularity
        public Issue(String name, int popu){
            this.name = name;
            this.popu = 125-popu;
            
        }
        
        public String Name(){
            return name;
        }
        public int Popularity(){
            return popu*2;
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
    
    public static Issue culture = new Issue("Culture",50);
    public static Issue environment= new Issue("Environemnt",80);
    public static Issue police= new Issue("Police",97);
    public static Issue parks= new Issue("Parks",87);
    public static Issue wasteDisposal= new Issue("Waste Disposal",95);
    public static Issue health= new Issue("Health",96);
    public static Issue fireBrigade= new Issue("Fire Brigades",97);
    public static Issue education= new Issue("Education",68);
    public static Issue sport= new Issue("Sport",75);
    public static Issue religion= new Issue("Religion",50);
    public static Issue transportation= new Issue("Transportation",73);
    public static Issue taxes= new Issue("Taxes",84);
    
    
    
    //                                                 Residential, Commercial, Industrial
    
    
    public static Party Cons = new Party ("Conservative Party",1,3,2, 1,0,3);
    public static Party Libs = new Party ("Liberal Party", 2,3,1, 0,3,2);
    public static Party Greens = new Party ("Green Party", 1,0,1, 1,2,0);
    public static Party SocDems = new Party ("Social Democratic Party", 3,1,2, 3,1,0);
    
    public static Party National = new Party ("National Party", 3,1,2, 3,0,1);
    public static Party Peoples = new Party ("Peoples Party",1,2,3, 2,1,3);
    public static Party Progress = new Party ("Progressive Party", 2, 2, 1, 1, 3, 0);
    
    public static int resiDemand = 4, commDemand = 1, induDemand =1;
    
    public static int lowPercent = 3072, medPercent = 470, upPercent=0;
    public static int total = lowPercent+medPercent+upPercent;
    
    public static Party ruling = null;
    public static int approvalrating = 64;
    public static void addIssuesToParties(){
        National.addToHigh(religion);
        National.addToHigh(police);
        National.addToHigh(culture);
        
        National.addToMed(sport);
        National.addToMed(environment);
        National.addToMed(health);
        
        National.addToLow(wasteDisposal);
        National.addToLow(parks);
        National.addToLow(fireBrigade);
        
        Cons.addToHigh(taxes);
        Cons.addToHigh(religion);
        Cons.addToHigh(police);
        
        Cons.addToMed(culture);
        Cons.addToMed(sport);
        
        Cons.addToLow(wasteDisposal);
        Cons.addToLow(fireBrigade);
        
        Peoples.addToHigh(taxes);
        Peoples.addToHigh(transportation);
        
        Peoples.addToMed(police);
        Peoples.addToMed(wasteDisposal);
        
        
        Peoples.addToLow(religion);
        Peoples.addToLow(parks);
        Peoples.addToLow(fireBrigade);
        
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
        
        Progress.addToHigh(education);
        Progress.addToHigh(culture);
        Progress.addToHigh(wasteDisposal);
        
        Progress.addToMed(health);
        Progress.addToMed(environment);
        Progress.addToMed(transportation);
        
        Progress.addToLow(fireBrigade);
        Progress.addToLow(sport);
        
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
	    //allParties.add(National);
	    allParties.add(Cons);
	    //allParties.add(Peoples);
	    allParties.add(Libs);
	    allParties.add(Greens);
	    //allParties.add(Progress);
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
	    
	    
	    lowPercent = (lowPercent*100)/total;
	    //System.out.println(lowPercent);
	    medPercent = (medPercent*100)/total;
	    //System.out.println(medPercent);
	    upPercent = (upPercent*100)/total;
	    //System.out.println(upPercent);
	    
		addIssuesToParties();
		
		int noOfDistricts = total/250;
		int majority = noOfDistricts/2;
		
		// Section to give points
		//  Issues
		for(Party par: allParties){
		    for(Issue i : allIssues){
		        if(par.highList().contains(i)){
		            par.addPoints(i.Popularity());
		        }
		        if(par.medList().contains(i)){
		            par.addPoints(i.Popularity()/2);
		        }
		        if(par.lowList().contains(i)){
		            par.addPoints(i.Popularity()/4);
		        }
		    }
		}
		
		
		
		//  infrastructure
		for(Party par: allParties){
		    int toAdd = ((par.resiPriority()*resiDemand)+ (par.induPriority()*induDemand)+ (par.comPriority()*commDemand));
		    par.addPoints(toAdd);
		    
		        //System.out.println(par.Name()+ " " +toAdd);
		    
		}
		
		//  Class
		for(Party par: allParties){
		    int toAdd = (par.lowerClassPopularity()*lowPercent)+ (par.mediumClassPopularity()*medPercent)+ (par.upperClassPriority()*upPercent);
		    par.addPoints(toAdd);
		    //System.out.println(par.Name()+ " "+ toAdd);
		}
		
		if(ruling!=null){
		    ruling.addPoints((ruling.Points()/((approvalrating+1)))*-2);
		}
		//Seat Distribution via D'hondt
		int maxnum = 0;
		Party maxPar = null;
		
		int total = 0;
		for(Party par: allParties){
		    total+=par.Points();
		}
		Random ra = new Random();
		for(int i=0; i!=noOfDistricts;i++){
		    maxnum = 0;
		    maxPar = null;
		    for(Party par: allParties){
		        //int curpoints = (int) ((par.Points()*100)/((par.Seats()/2)+1));
		        int curpoints = par.Points();
		        curpoints += ra.nextInt((curpoints)+1);
		        if(curpoints> maxnum){
		            maxnum =curpoints;
		            maxPar = par;
		        }
		    }
		    
		    if(maxPar !=null){
		        maxPar.addSeat();
		    }
		}
		
		
		
		
		System.out.println("Results:");
		Map<Party, Integer> CoalitionMembers = new HashMap<>();
		for(Party par: allParties){
		    System.out.println(par);
		    CoalitionMembers.put(par, 0);
		}
		
		
		int curpoints = 0;
		System.out.println("\nGovernment Formed:");
		    for(int i=0; i< 5;i++){
		        Party maxpar = null;
		        maxnum = 0;
		        
		        for(Party par: CoalitionMembers.keySet()){
		            curpoints =0;
		            curpoints =(int) ((par.Seats()*5)/((CoalitionMembers.get(par))+1));
		            
		            //curpoints+= par.Seats()/2;
		            if(curpoints> maxnum){
		                maxnum = curpoints;
		                maxpar = par;
		            }
		        }
		        
		        switch(i){
		            case 0: System.out.print("Tax Policy: ");
		            break;
		            case 1: System.out.print("Income Policy: ");
		            break;
		            case 2: System.out.print("Zoning Policy: ");
		            break;
		            case 3: System.out.print("Energy Policy: ");
		            break;
		            case 4: System.out.print("Treasury Policy: ");
		            break;
		        }
		        System.out.println(maxpar.Name());
		        CoalitionMembers.put(maxpar, CoalitionMembers.get(maxpar)+1);
		        
		    }
		    
		    System.out.println("\nIssue Support in Government (Requires "+ majority + " to pass):");
		    int seatsup = 0;
		    for(Issue i: allIssues){
		        seatsup = 0;
		        for(Party par: allParties){
		            int issueval = par.Seats();
		            if(par.highList().contains(i)){
		                issueval = 1;
		            }
		            if(par.medList().contains(i)){
		                issueval = 2;
		            }
		            if(par.lowList().contains(i)){
		                issueval = 3;
		            }
		            if(i == fireBrigade || i== police || i==health){
		                if(issueval>1){
		                    issueval--;
		                }
		            }
		            
		            issueval -= i.Popularity()/75;
		            if(issueval<=0){
		                issueval = 1;
		            }
		            
		            seatsup += (par.Seats()/issueval);
		            
		        }
		        if(seatsup>=majority){
		            System.out.println(i.Name()+ " - " +seatsup + " of "+ majority+ " needed");
		        }
		    }
		    
		    System.out.println("\nAllowed Zones:");
		    int resiSupport = 0, commSupport = 0, induSupport=0;
		    // Residential
		    seatsup =0;
		    for(Party par: allParties){
		        
		        int divider = 4-par.resiPriority();
		        seatsup+= par.Seats()/divider;
		    }
		    if(seatsup>=majority){
		        System.out.println("Dense Residential Zones - "+ seatsup +" of "+ majority+ " needed");
		    }
		    
		    seatsup =0;
		    for(Party par: allParties){
		        
		        int divider = 4-par.resiPriority();
		        divider -= (divider >1)? 1:0;
		        seatsup+= par.Seats()/divider;
		    }
		    if(seatsup>=majority){
		        System.out.println("Residential Zones - "+ seatsup +" of "+ majority+ " needed");
		    }
		    
		    
		    
		    //Commercial
		    seatsup =0;
		    for(Party par: allParties){
		        
		        int divider = 4-par.comPriority();
		        seatsup+= par.Seats()/divider;
		    }
		    if(seatsup>=majority){
		        System.out.println("Dense Commercial Zones - "+ seatsup +" of "+ majority+ " needed");
		    }
		    seatsup =0;
		    for(Party par: allParties){
		        
		        int divider = 4-par.comPriority();
		        divider -= (divider >1)? 1:0;
		        seatsup+= par.Seats()/divider;
		    }
		    if(seatsup>=majority){
		        System.out.println("Commercial Zones - "+ seatsup +" of "+ majority+ " needed");
		    }
		    
		    //Industrial
		    seatsup =0;
		    for(Party par: allParties){
		        
		        int divider = 4-par.induPriority();
		        seatsup+= par.Seats()/divider;
		    }
		    if(seatsup>=majority){
		        System.out.println("Dense Industrial Zones - "+ seatsup +" of "+ majority+ " needed");
		    }
		    
		    seatsup =0;
		    for(Party par: allParties){
		        
		        int divider = 4-par.induPriority();
		        divider -= (divider >1)? 1:0;
		        seatsup+= par.Seats()/divider;
		    }
		    if(seatsup>=majority){
		        System.out.println("Industrial Zones - "+ seatsup +" of "+ majority+ " needed");
		    }
		    
		    //Farms 
		    seatsup =0;
		    for(Party par: allParties){
		        
		        int divider = 4-par.induPriority();
		        divider = (divider/2)+1;
		        if(par!=Greens){
		            seatsup+= par.Seats()/divider;
		        }else{
		            seatsup += par.Seats();
		        }
		    }
		    if(seatsup>=majority){
		        System.out.println("Farm Zones - "+ seatsup +" of "+ majority+ " needed");
		    }
		    
		    for(Party par: allParties){
		     //   System.out.println(par.Name() +" "+ par.Points());
		    }
		    
		
	}
}
