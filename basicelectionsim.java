import java.util.*;
public class Main
{
    public static Scanner sc = new Scanner(System.in);
    public static Random ra = new Random();
    
    public static class ideoGroup {
        String name;
        int size;
        int ideology;
        int satisfaction;
        
        
        public ideoGroup(String name, int size, int ideology){
            this.name = name;
            this.size = size;
            this.ideology = ideology;
            this.satisfaction = 100;
        }
        
        public String getName(){return name;}
        public int getSize(){return size;}
        public int getIdeology(){return ideology;}
        public int getSatisfaction(){return satisfaction;}
        
        public int proximityWith(Party par){
            return 100-Math.abs(par.getIdeology()-ideology);
        }
        
        public void updateSize(int toAdd){
            size+= toAdd;
        }
        
        public void updateSatisfaction(int toAdd){
            satisfaction += toAdd;
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
        
        public void updateApproval(int newVal){
            popularity += newVal;
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
        
        public void ideoDriftOld(){
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
            if(this.ideology> 100){
                this.ideology = 100;
            }
            if(this.ideology< 0){
                this.ideology = 0;
            }
        }
        
        
        public void ideoDrift(){
            if(score == 0) return;
            double weightedIdeologySum = 0;
            ideoGroup maxGroup = null;
            int maxnum=0;
            for(Map.Entry<ideoGroup,Integer> entry : demographics.entrySet()){
                ideoGroup gro = entry.getKey();
                int votesGot = entry.getValue();
                if(entry.getValue()> maxnum){
                    maxGroup = entry.getKey();
                    maxnum = entry.getValue();
                }
            }
            
            int targetIdeo = maxGroup.getIdeology();
            int driftspeed = 3;
            if(this.ideology < targetIdeo) this.ideology+= driftspeed;
            if(this.ideology> targetIdeo) this.ideology-= driftspeed;
            
            this.ideology += ra.nextInt(10)-ra.nextInt(10);
            
            int minsat = 1000;
            ideoGroup minGroup = null;
            for(ideoGroup gro : allGroups){
                if(gro.getSatisfaction()< minsat){
                    minsat = gro.getSatisfaction();
                    minGroup = gro;
                }
            }
            targetIdeo = minGroup.getIdeology();
            driftspeed = 2;
            if(this.ideology < targetIdeo) this.ideology+= driftspeed;
            if(this.ideology> targetIdeo) this.ideology-= driftspeed;
            
            if(this.ideology> 100){
                this.ideology = 100;
            }
            if(this.ideology< 0){
                this.ideology = 0;
            }
        }
    }
    
    public static class Coalition{
        Party leader;
        int size;
        List<Party> members = new ArrayList<>();
        
        public Coalition(Party leader){
            this.leader = leader;
            size = leader.getPercent();
            members.add(leader);
        }
        
        public Party getLeader(){return leader;}
        
        public int getSize(){ return size;}
        
        public void addSize(int toAdd){
            size+=toAdd;
        }
        
        public boolean invitation(Party other){
            if(leader.proximityWith(other)> 50){
                return true;
            }else{
                return false;
            }
        }
        
        public void addParty(Party toAdd){
            members.add(toAdd);
        }
        
        public List<Party> getMemberList(){
            return members;
        }
    }
    
    public static Coalition rulingCoalition;
    
    public static int approvalRatingChange;
    
    public static List<ideoGroup> allGroups = new ArrayList<>();
    public static void addGroups(){
        allGroups.add(new ideoGroup("Communists",20,95));
        allGroups.add(new ideoGroup("Socialists",20,80));
        allGroups.add(new ideoGroup("Progressives",20,65));
        allGroups.add(new ideoGroup("Liberals",20,50));
        allGroups.add(new ideoGroup("Conservatives",20,35));
        allGroups.add(new ideoGroup("Nationalists",20,20));
        allGroups.add(new ideoGroup("Fascists",20,5));
    }
    
    public static List<Party> allParties = new ArrayList<>();
    
    public static void addParties(){
        allParties.add(new Party("Socialist Party", 85,true));
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
            boolean hasvoted = false;
            int maxProximity = 0;
            
            for(Party par: allParties){
                if(gro.proximityWith(par)>40){
                    hasvoted = true;
                    if(gro.proximityWith(par)>maxProximity){
                        maxProximity = gro.proximityWith(par);
                    }
                    int toAdd = (gro.getSize()*gro.proximityWith(par))/100;
                    if(rulingCoalition!= null){
                        if(rulingCoalition.getMemberList().contains(par)){
                            toAdd -= (int) (toAdd*Math.abs(approvalRatingChange))/1000;
                        }
                    }
                    par.addVotes(toAdd/(ra.nextInt(3)+1));
                    //par.addVotes(toAdd);
                    par.recordVotes(gro,toAdd);
                }
            }
            int satischange = 5-(maxProximity/20);
            if(!hasvoted){
                satischange-=5;
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
            par.setApproval(pctg);
        }
        
        
    }
    
    public static void coalitionFormation(){
        Party winner = null;
        int winnum = 0;
        for(Party par: allParties){
            if(par.getScore() > winnum){
                winner = par;
                winnum = par.getScore();
            }
            //System.out.println(par.getScore());
        }
        
        rulingCoalition = new Coalition(winner);
        
        if(winner.percent<50){
            for(Party par: allParties){
                if(par != winner){
                    if(rulingCoalition.invitation(par)){
                        rulingCoalition.addParty(par);
                        rulingCoalition.addSize(par.getPercent());
                    }
                }
            }
            
            if(rulingCoalition.getSize() <50){
                for(Party par: allParties){
                    if(par.getScore() > winnum && par!= rulingCoalition.getLeader()){
                        winner = par;
                        winnum = par.getScore();
                    }
                    
                }
                rulingCoalition = new Coalition(winner);
            }
        }
        
    }
    
    public static void updateTick(){
        approvalRatingChange = ra.nextInt(5)-ra.nextInt(10);
        for(Party par: rulingCoalition.getMemberList()){
            approvalRatingChange*= (ra.nextInt(3))+1;
            par.updateApproval(approvalRatingChange);
            
            par.ideoDrift();
        }
        
        for(ideoGroup gro: allGroups){
            gro.updateSize(ra.nextInt(20));
        }
        
    }
    
    
    
	public static void main(String[] args) {
	    addGroups();
	    addParties();
		int year = 1920;
		int interval  =5;
		int electionsToSimulate = 22;
		
		for(int i=0; i<electionsToSimulate;i++){
		    election();
		    coalitionFormation();
		    System.out.println(year);
		    System.out.println("Winner: "+ rulingCoalition.getLeader().getName());
		    
		    for(Party par: allParties){
		        System.out.println(par.getName()+ " "+ par.getPercent()+"%");
		        System.out.println("Ideology: "+ par.getIdeology());
		        System.out.println("====================");
		    }
		    updateTick();
		    year+=interval;
		}
	}
}
