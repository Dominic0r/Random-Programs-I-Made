import java.util.*;
public class Main
{
    public static Scanner sc = new Scanner(System.in);
    public static Random ra = new Random();
    
    public static class ideoGroup {
        String name;
        int size;
        int ideology;
        
        
        public ideoGroup(String name, int size, int ideology){
            this.name = name;
            this.size = size;
            this.ideology = ideology;
        }
        
        public String getName(){return name;}
        public int getSize(){return size;}
        public int getIdeology(){return ideology;}
        
        public int proximityWith(Party par){
            return 100-Math.abs(par.getIdeology()-ideology);
        }
        
        public void updateSize(int toAdd){
            size+= toAdd;
        }
        
    }
    
    public static class Party{
        String name;
        int ideology; // goes from 0 - 100 0- most rightwing, 100 - most left-wing
        boolean isActive;
        int score=0; // total raw popularity score
        int percent = 0; // percentage
        int popularity=0; // approval rating
        Map<ideoGroup, Integer> demographics = new HashMap<>();
        
        public Party(String name, int ideology, boolean isActive){
            this.name = name;
            this.ideology = ideology;
            this.isActive = isActive;
        }
        
        public String getName(){return name;}
        public int getIdeology(){return ideology;}
        public boolean isPartyActive(){return isActive;}
        public int getScore(){return score;}
        public int getPopularity(){return popularity;}
        public int getPercent(){return percent;}
        
        public void setPercent(int newVal){
            percent = newVal;
        }
        
        public int proximityWith(ideoGroup gro){
            return 100-Math.abs(gro.getIdeology()-ideology);
        }
        
        public int proximityWith(Party par){
            return 100-Math.abs(par.getIdeology()-ideology);
        }
        
        public void resetScore(){ score = 0;}
        public void addToScore(int toAdd){
            score+= toAdd;
        }
        
        public void setApproval(int newVal){
            popularity = newVal;
        }
        
        public void resetElectionData(){
            score = 0;
            demographics.clear();
        }
        public void recordVotes(ideoGroup gro, int amt){
            demographics.put(gro, amt);
        }
        
        public void addVotes(int toAdd){
            score+= toAdd;
        }
        
        public void ideoDrift(){
            if(score == 0) return;
            double weightedIdeologySum = 0;
            for(Map.Entry<ideoGroup,Integer> entry : demographics.entrySet()){
                ideoGroup gro = entry.getKey();
                int votesGot = entry.getValue();
                
                weightedIdeologySum += (gro.getIdeology()*votesGot);
            }
            
            int targetIdeo = (int) (weightedIdeologySum / score);
            int driftspeed = 5;
            if(this.ideology < targetIdeo) this.ideology+= driftspeed;
            if(this.ideology> targetIdeo) this.ideology-= driftspeed;
        }
        
    }
    
    public static class Coalition{
        Party leader;
        int size;
        List<Party> members = new ArrayList<>();
        
    }
    
    public static List<ideoGroup> allGroups = new ArrayList<>();
    public static void addGroups(){
        allGroups.add(new ideoGroup("Communists",2,95));
        allGroups.add(new ideoGroup("Socialists",10,80));
        allGroups.add(new ideoGroup("Progressives",20,65));
        allGroups.add(new ideoGroup("Liberals",30,50));
        allGroups.add(new ideoGroup("Conservatives",20,35));
        allGroups.add(new ideoGroup("Nationalists",10,20));
        allGroups.add(new ideoGroup("Fascists",2,5));
    }
    
    public static List<Party> allParties = new ArrayList<>();
    
    public static void addParties(){
        allParties.add(new Party("Democratic Party", 65, true));
        allParties.add(new Party("Republican Party", 45, true));
    }
    
    public static void updateGroupSize(){
        for(ideoGroup gro: allGroups){
            gro.updateSize(ra.nextInt(10));
        }
    }
    
    public static void election(){
        for(Party par: allParties){
            par.resetElectionData();
        }
        
        for(ideoGroup gro: allGroups){
            for(Party par: allParties){
                if(gro.proximityWith(par)>40){
                    int toAdd = (gro.getSize()*gro.proximityWith(par))/100;
                    par.addVotes(toAdd);
                    par.recordVotes(gro,toAdd);
                }
            }
        }
        
        // set percentages
        int totalVotes = 0;
        for(Party par: allParties){
            totalVotes += par.getScore();
        }
        
        for(Party par: allParties){
            int pctg = (int) (par.getScore()*100)/ totalVotes;
            par.setPercent(pctg);
        }
        
        
    }
    
    
    
    
	public static void main(String[] args) {
		System.out.println("Hello World");
	}
}
