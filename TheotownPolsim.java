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
        
        int res, com, ind; // zone demands
        
        public Party(String name, int res, int com, int ind){
            this.name = name;
            this.res = res;
            this.comn = com;
            this.ind = ind;
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
    }
    
    public Issue culture = new Issue("Culture",0);
    public Issue environment= new Issue("Environemnt",0);
    public Issue police= new Issue("Police",0);
    public Issue parks= new Issue("Parks",0);
    public Issue wasteDisposal= new Issue("Waste Disposal",0);
    public Issue health= new Issue("Health",0);
    public Issue fireBrigade= new Issue("Fire Brigades",0);
    public Issue education= new Issue("Education",0);
    public Issue sport= new Issue("Sport",0);
    public Issue religion= new Issue("Religion",0);
    public Issue transportation= new Issue("Transportation",0);
    public Issue taxes= new Issue("Taxes",0);

    
	public static void main(String[] args) {
		System.out.println("Hello World");
	}
}
