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
    
    //NUP Splinter parties 
    public static Party APCT = new Party("All Peoples Congress for Tradition", "Big-Tent Monarchism");
    public static Party NPA = new Party("Nationalist People's Assembly", "Illiberal Republicanism");
    
    //REP Splinter parties 
    public static Party CDP = new Party("Conservative Democratic Party","Conservatism");
    public static Party LPP = new Party("Liberal Peoples Party","Liberalism");
    public static Party SDP = new Party("Social Democratic Party","Social Democracy");
    
    //LRP Splinter parties 
    public static Party SPP= new Party("Socialist Peoples Party","Participatory Socialism");
    public static Party ACRP= new Party("All-Communist Revolutionary Party","Revolutionary Socialism");
    
    //APCT Splinter parties
    public static Party IRP= new Party("Imperial Restoration Party","Unitary Monarchism");
    public static Party NPPC = new Party("National Particularist Peoples Congress","Paticularism");
    
    //NPA Splinter parties 
    public static Party CPP= new Party("Conservative Peoples Party","Aristocratic Conservatism");
    public static Party NDCP= new Party("National Democratic Conservative Party","Corporatocracy");
    
    // public static Party = new Party ("","");
    // CDP Splinter parties 
    public static Party LCO= new Party ("Liberal Conservatives","Market Liberalism");
    public static Party PDP = new Party ("Peoples Democratic Party","Social Conservatism");
    public static Party CFU = new Party ("Conservative Farmers Union","Agrarian Conservatism");
    
    // LPP Splinters
    public static Party PRU = new Party ("Progressive Union","Social Liberalism");
    public static Party DLP = new Party ("Democratic Liberal Party","Centrism");
    
    // SDP Splinters 
    public static Party NSD = new Party ("New Social Democrats","Reformism");
    public static Party SLP= new Party ("Social Labor Party","Unionism");
    
    // SPP Splinters
    public static Party DSP = new Party ("Democratic Socialist Party","Democratic Socialism");
    public static Party NLA = new Party ("National Liberal Alliance","Syndicalism");
    public static Party ALP = new Party ("Agrarian Labor Party","Social Agrarianism");
    
    //ACRP
    public static Party NRE= new Party ("National Revolution","Anarchism");
    public static Party CAP = new Party ("Communist Action Party","Communism");
    
    public static void addClassesToParties(){
        
        // National Unity Party - Broad reactionary base
NUP.addClass(upper, 4); NUP.addClass(newMid, 1); NUP.addClass(oldMid, 4); NUP.addClass(urban, 0); NUP.addClass(rural, 3);

// All Peoples Congress for Tradition - High rural traditionalism
APCT.addClass(upper, 4); APCT.addClass(newMid, 0); APCT.addClass(oldMid, 3); APCT.addClass(urban, 0); APCT.addClass(rural, 5);

// Imperial Restoration Party - Elite-heavy monarchism
IRP.addClass(upper, 5); IRP.addClass(newMid, 1); IRP.addClass(oldMid, 2); IRP.addClass(urban, 0); IRP.addClass(rural, 3);

// National Particularist Peoples Congress - Regional rural focus
NPPC.addClass(upper, 3); NPPC.addClass(newMid, 1); NPPC.addClass(oldMid, 3); NPPC.addClass(urban, 0); NPPC.addClass(rural, 5);

// Nationalist People’s Assembly - Authoritarian "Old Mid" focus
NPA.addClass(upper, 3); NPA.addClass(newMid, 2); NPA.addClass(oldMid, 5); NPA.addClass(urban, 2); NPA.addClass(rural, 2);

// Conservative Peoples Party - The Aristocracy party
CPP.addClass(upper, 5); CPP.addClass(newMid, 1); CPP.addClass(oldMid, 3); CPP.addClass(urban, 0); CPP.addClass(rural, 2);

// National Democratic Conservative Party - Big business/Industrialists
NDCP.addClass(upper, 5); NDCP.addClass(newMid, 4); NDCP.addClass(oldMid, 2); NDCP.addClass(urban, 1); NDCP.addClass(rural, 1);
        
        // Republican Party - The dominant big-tent
REP.addClass(upper, 3); REP.addClass(newMid, 5); REP.addClass(oldMid, 4); REP.addClass(urban, 4); REP.addClass(rural, 3);

// Conservative Democratic Party - Mainstream Right
CDP.addClass(upper, 4); CDP.addClass(newMid, 3); CDP.addClass(oldMid, 4); CDP.addClass(urban, 2); CDP.addClass(rural, 4);

// Liberal Conservatives - Fiscal focus
LCO.addClass(upper, 5); LCO.addClass(newMid, 4); LCO.addClass(oldMid, 3); LCO.addClass(urban, 1); LCO.addClass(rural, 2);

// Peoples Democratic Party - Socially traditional middle class
PDP.addClass(upper, 2); PDP.addClass(newMid, 2); PDP.addClass(oldMid, 5); PDP.addClass(urban, 2); PDP.addClass(rural, 4);

// Conservative Farmers Union - Purely agrarian
CFU.addClass(upper, 2); CFU.addClass(newMid, 1); CFU.addClass(oldMid, 3); CFU.addClass(urban, 0); CFU.addClass(rural, 5);

// Liberal Peoples Party - Educated professionals
LPP.addClass(upper, 3); LPP.addClass(newMid, 5); LPP.addClass(oldMid, 2); LPP.addClass(urban, 3); LPP.addClass(rural, 1);

// Progressive Union - Left-leaning urbanites
PRU.addClass(upper, 1); PRU.addClass(newMid, 5); PRU.addClass(oldMid, 2); PRU.addClass(urban, 4); PRU.addClass(rural, 1);

// Democratic Liberal Party - The radical center
DLP.addClass(upper, 3); DLP.addClass(newMid, 4); DLP.addClass(oldMid, 4); DLP.addClass(urban, 3); DLP.addClass(rural, 3);

// Social Democratic Party - Urban labor/New Mid alliance
SDP.addClass(upper, 1); SDP.addClass(newMid, 4); SDP.addClass(oldMid, 2); SDP.addClass(urban, 5); SDP.addClass(rural, 2);

// New Social Democrats - Moderate reformers
NSD.addClass(upper, 2); NSD.addClass(newMid, 5); NSD.addClass(oldMid, 3); NSD.addClass(urban, 4); NSD.addClass(rural, 2);

// Social Labor Party - Trade union focus
SLP.addClass(upper, 0); SLP.addClass(newMid, 2); SLP.addClass(oldMid, 1); SLP.addClass(urban, 5); SLP.addClass(rural, 2);

// Left Republican Party - Broad socialist base
LRP.addClass(upper, 0); LRP.addClass(newMid,0 ); LRP.addClass(oldMid, 0); LRP.addClass(urban, 5); LRP.addClass(rural, 2);

// Socialist Peoples Party - Grassroots focus
SPP.addClass(upper, 0); SPP.addClass(newMid, 2); SPP.addClass(oldMid, 1); SPP.addClass(urban, 4); SPP.addClass(rural, 4);

// Democratic Socialist Party - Constitutional Left
DSP.addClass(upper, 1); DSP.addClass(newMid, 4); DSP.addClass(oldMid, 2); DSP.addClass(urban, 4); DSP.addClass(rural, 2);

// National Labor Alliance - Syndicalist/Industrial workers
NLA.addClass(upper, 0); NLA.addClass(newMid, 1); NLA.addClass(oldMid, 0); NLA.addClass(urban, 5); NLA.addClass(rural, 1);

// Agrarian Labor Party - Radicalized peasants
ALP.addClass(upper, 0); ALP.addClass(newMid, 1); ALP.addClass(oldMid, 1); ALP.addClass(urban, 2); ALP.addClass(rural, 5);

// All-Communist Revolutionary Party - Hardline statists
ACRP.addClass(upper, 0); ACRP.addClass(newMid, 1); ACRP.addClass(oldMid, 0); ACRP.addClass(urban, 5); ACRP.addClass(rural, 3);

// National Revolution - Anarchist/Anti-hierarchical
NRE.addClass(upper, 0); NRE.addClass(newMid, 2); NRE.addClass(oldMid, 2); NRE.addClass(urban, 4); NRE.addClass(rural, 4);

// Communist Action Party - Vanguardist
CAP.addClass(upper, 0); CAP.addClass(newMid, 1); CAP.addClass(oldMid, 0); CAP.addClass(urban, 5); CAP.addClass(rural, 2);
    }
    /*
        .addClass(upper, );
        .addClass(newMid, );
        .addClass(oldMid, );
        .addClass(urban, );
        .addClass(rural, );
    */
    
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
