import java.util.*;
public class Main
{
    public static Scanner sc = new Scanner(System.in);
    public static Random ra = new Random();
    
    public static class ideoGroup {
        String name;
        String splintername;
        int size;
        int ideology;
        int satisfaction;
        boolean hasSplintered = false;
        
        
        public ideoGroup(String name, String splintername, int size, int ideology){
            this.name = name;
            this.splintername = splintername;
            this.size = size;
            this.ideology = ideology;
            this.satisfaction = 100;
        }
        
        public String getSplinterName(){return splintername;}
        public String getName(){return name;}
        public int getSize(){return size;}
        public int getIdeology(){return ideology;}
        public int getSatisfaction(){return satisfaction;}
        
        public boolean hasGroupSplintered(){return hasSplintered;}
        public void toggleSplinter(){
            hasSplintered = true;
        }
        
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
        double recognition = 0;// how established a party is
        int failcount = 0;
        
        public Party(String name, int ideology, boolean isActive){
            this.name = name;
            this.ideology = ideology;
            this.isActive = isActive;
            failcount = 0;
        }
        
        public void incrementFail(){failcount++;}
        public int getFailCount(){return failcount;}
        public void resetFail(){failcount=0;}
        
        public String getName(){return name;}
        public int getIdeology(){return ideology;}
        public boolean isPartyActive(){return isActive;}
        public int getScore(){return score;}
        public int getPopularity(){return popularity;}
        public int getPercent(){return percent;}
        
        public double getRecognition(){return recognition;}
        public void setRecog(double newVal){
            recognition = newVal;
        }
        
        public void incrementRecognition(){
            recognition+=0.02;
        }
        
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
            
            this.ideology += ra.nextInt(3)-ra.nextInt(3);
            
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
        /*allGroups.add(new ideoGroup("Communist",2,95));
        allGroups.add(new ideoGroup("Socialist",10,80));
        allGroups.add(new ideoGroup("Progressive",20,65));
        allGroups.add(new ideoGroup("Liberal",30,50));
        allGroups.add(new ideoGroup("Conservative",20,35));
        allGroups.add(new ideoGroup("Nationalist",10,20));
        allGroups.add(new ideoGroup("Fascist",2,5));*/
        //      allGroups.add(new ideoGroup("", "", 20, 60));
        // Reactionary Bloc
    allGroups.add(new ideoGroup("Monarchists", "All-Peoples Congress for Tradition", 15, 10));
    allGroups.add(new ideoGroup("Illiberal Republicans", "Nationalist Peoples Assembly", 10, 25));
    
    allGroups.add(new ideoGroup("Unitary Monarchists", "Imperial Restoration Party", 5, 10));
    allGroups.add(new ideoGroup("Particularists", "National Particularist Peoples Congress", 5, 12));
    
    allGroups.add(new ideoGroup("Aristocratic Conservatives", "Conservative Peoples Party", 15, 20));
    allGroups.add(new ideoGroup("Corporatists", "National Democratic Conservative Party", 15, 17));
    
    // Republican Bloc
    allGroups.add(new ideoGroup("Big-Tent Conservatives", "Conservative Democratic Party", 20, 40));
    allGroups.add(new ideoGroup("Liberals", "Liberal Peoples Party", 35, 50));
    allGroups.add(new ideoGroup("Social Democrats", "Social Democratic Party", 20, 65));
    
    allGroups.add(new ideoGroup("Market Liberals", "Liberal Conservatives", 20, 45));
    allGroups.add(new ideoGroup("Agrarian Conservatives", "Conservative Farmers Union", 17, 35));
    allGroups.add(new ideoGroup("Social Conservatives", "Peoples Democratic Party", 20, 32));
    
    allGroups.add(new ideoGroup("Social Liberals", "Progressive Union", 20, 55));
    allGroups.add(new ideoGroup("Centrists", "Democratic Liberal Party", 25, 50));
    
    allGroups.add(new ideoGroup("Reformists", "New Social Democrats", 18, 60));
    allGroups.add(new ideoGroup("Unionists", "Social Labor Party", 18, 70));
    
    
    // Revolutionary Bloc
    allGroups.add(new ideoGroup("Participationists", "Socialist Peoples Party", 15, 70));
    allGroups.add(new ideoGroup("Anti-Participationists", "All-Communist Revolutionary Party", 10, 80));
    
    allGroups.add(new ideoGroup("Democratic Socialists", "Democratic Socialist Party", 17, 67));
    allGroups.add(new ideoGroup("Syndicalists", "National Labor Alliance", 15, 72));
    allGroups.add(new ideoGroup("Agrarian Socialists", "Agrarian Socialist Party", 15, 65));
    
    allGroups.add(new ideoGroup("Anarchists", "National Revolution", 5, 85));
    allGroups.add(new ideoGroup("Statists", "Communist Action Party", 5, 95));
    }
    
    public static List<Party> allParties = new ArrayList<>();
    
    public static void addParties(){
        //allParties.add(new Party("Socialist Party", 85,true));
        //allParties.add(new Party("Democratic Party", 65, true));
        //allParties.add(new Party("Republican Party", 45, true));
        //allParties.add(new Party("Nationalist Party",15,true));
        allParties.add(new Party("National Unity Party", 15, true)); // Reactionary
    allParties.add(new Party("Republican Party", 50, true));     // Republican
    allParties.add(new Party("Left Republican Party", 85, true)); // Revolutionary
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
                if(gro.proximityWith(par)>90){
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
                    toAdd += (int) toAdd* par.getRecognition();
                    par.addVotes(toAdd/(ra.nextInt(4)+1));
                    //par.addVotes(toAdd);
                    par.recordVotes(gro,toAdd);
                }
            }
            int satischange = -1*(5-(maxProximity/20));
            if(!hasvoted){
                satischange-=ra.nextInt(10);
            }
            satischange+= ra.nextInt(3)-ra.nextInt(3);
            gro.updateSatisfaction(satischange);
            
        }
        
        // set percentages
        int totalVotes = 0;
        for(Party par: allParties){
            par.setPercent(0);
            totalVotes += par.getScore();
            if(par.getPercent()>20){
                par.incrementRecognition();
            }
        }
        // proportional
        
        
        /*for(Party par: allParties){
            
            int pctg = (int) (par.getScore()*100)/ totalVotes;
            par.setPercent(pctg);
            par.setApproval(pctg);
            
        }*/
        
        
        //seat distribution
        for(int i=0; i<100;i++){ // simulation of first past the post
            int maxnum =0;
            Party maxpar = null;
            for(Party par: allParties){
                int curscore = par.getScore()/ (ra.nextInt(4)+1);
                if(curscore > maxnum){
                    maxnum = curscore;
                    maxpar = par;
                }
            }
            if (totalVotes <= 0) return;
            maxpar.setPercent(maxpar.getPercent()+1); //nullpoiintererror here
        }
        
        
    }
    
    public static int startyear;
    public static int year = 1900;
    
    public static void electLeadParty() {
    int rounds = 1;
    // Every active party starts as a candidate
    List<Party> candidates = new ArrayList<>(allParties);
    
    Party winningParty = null;
    boolean hasGotMajority = false;

    // Run-off election logic
    while (!hasGotMajority && candidates.size() > 0) {
        // Reset the vote count for this round
        Map<Party, Integer> voteCount = new HashMap<>();
        for (Party candidate : candidates) {
            voteCount.put(candidate, 0);
        }

        // Voting Phase: Every party in the parliament votes
        for (Party votingParty : allParties) {
            Party bestCandidate = null;
            int minDiff = Integer.MAX_VALUE;
            List<Party> tiedCandidates = new ArrayList<>();

            for (Party candidate : candidates) {
                // Calculate ideological distance
                int diff = Math.abs(votingParty.getIdeology() - candidate.getIdeology());
                
                if (diff < minDiff) {
                    minDiff = diff;
                    tiedCandidates.clear();
                    tiedCandidates.add(candidate);
                } else if (diff == minDiff) {
                    tiedCandidates.add(candidate);
                }
            }

            // Handle ties randomly
            if (tiedCandidates.size() > 1) {
                bestCandidate = tiedCandidates.get(ra.nextInt(tiedCandidates.size()));
            } else {
                bestCandidate = tiedCandidates.get(0);
            }

            // Weight the vote by the party's parliamentary percentage (seats)
            int currentVotes = voteCount.getOrDefault(bestCandidate, 0);
            voteCount.put(bestCandidate, currentVotes + votingParty.getPercent());
        }

        // Find the current round leader
        winningParty = null;
        int maxVotes = -1;
        for (Map.Entry<Party, Integer> entry : voteCount.entrySet()) {
            if (entry.getValue() > maxVotes) {
                maxVotes = entry.getValue();
                winningParty = entry.getKey();
            }
        }

        // Display Round Results
        System.out.println("--- Election Round " + rounds + " ---");
        for (Party cand : candidates) {
            System.out.print(cand.getName() + ": " + voteCount.get(cand) + "% || ");
        }
        System.out.println("\n");

        // Check for 50%+ Majority (of the 100 seats)
        if (maxVotes > 50) { 
            hasGotMajority = true;
        } else if (candidates.size() > 1) {
            // Elimination Phase: Remove the party with the least support
            rounds++;
            int minVotes = Collections.min(voteCount.values());
            List<Party> lowestCandidates = new ArrayList<>();
            
            for (Map.Entry<Party, Integer> entry : voteCount.entrySet()) {
                if (entry.getValue() == minVotes) {
                    lowestCandidates.add(entry.getKey());
                }
            }
            
            // Remove one of the lowest-performing parties
            Party toRemove = lowestCandidates.get(ra.nextInt(lowestCandidates.size()));
            candidates.remove(toRemove);
            
            // Cleanup: remove any other parties that got 0 votes to speed up the loop
            Iterator<Party> it = candidates.iterator();
            while(it.hasNext()){
                Party p = it.next();
                if(voteCount.get(p) == 0 && p != winningParty) it.remove();
            }
        } else {
            // Only one candidate left, they win by default
            hasGotMajority = true;
        }
    }

    // Assign the winner to your rulingCoalition logic
    if (winningParty != null) {
        System.out.println("GOVERNMENT FORMED BY: " + winningParty.getName());
        
        if(rulingCoalition!=null){
            if(winningParty != rulingCoalition.getLeader()){
                leaderArchive.add(new Archive(rulingCoalition.getLeader().getName(), startyear, year));
            }
        }
        startyear = year;
        rulingCoalition = new Coalition(winningParty);
        winningParty.incrementRecognition();
        
    }
}
    
    public static void checkForNewParties() {
    for (ideoGroup gro : allGroups) {
        
        if (gro.getSatisfaction() < 30) {
            
            
            boolean alreadyRepresented = false;
            for (Party par : allParties) {
                if (Math.abs(par.getIdeology() - gro.getIdeology()) < 10) {
                    alreadyRepresented = true;
                    break;
                }
            }

            
            if (!alreadyRepresented&& !gro.hasGroupSplintered()) {
                String newName = gro.getSplinterName();
                allParties.add(new Party(newName, gro.getIdeology(), true));
                System.out.println("!!! NEW PARTY FORMED: " + newName + " !!!");
                gro.toggleSplinter();
                
                gro.updateSatisfaction(40); 
            }
        }
    }
}

public static String detIdeo(Party par){
    int ideo = par.getIdeology()/20;
    switch(ideo){
        case 0: return "Right-Wing";
            
        case 1: return "Center-Right";
            
        case 2:return "Centrist";
            
        case 3: return "Center-Left";
            
        case 4: return "Left-Wing";
        case 5: return "Left-Wing";
        
    }
    return"";
}

public static void checkFails(){
    List<Party> toRemove = new ArrayList<>();
    for(Party par: allParties){
        if(par.getPercent()<15){
            par.incrementFail();
        }else{
            par.resetFail();
        }
        
        if(par.getFailCount()>=5){
            
            toRemove.add(par);
            System.out.println(par.getName()+ " Removed!");
        }
    }
    
    allParties.removeAll(toRemove);
    
    
}

public static void events(){
    boolean eventHappened = false;
    if(ra.nextInt(10)<5){
       
        switch(ra.nextInt(5)){
            case 0:
                System.out.println("Economic Crisis!");
            for(ideoGroup gro : allGroups){
                if(gro.getIdeology()> 80 || gro.getIdeology()< 20){
                    gro.updateSize(ra.nextInt((gro.getSize()/2)+1));
                    approvalRatingChange -= ra.nextInt(5);
                    gro.updateSatisfaction(-1*ra.nextInt(25));
                }
            }
                break;
            case 1:
                System.out.println("Economic Boom!");
            for(ideoGroup gro : allGroups){
                if(gro.getIdeology()< 80 || gro.getIdeology()> 20){
                    gro.updateSize(ra.nextInt((gro.getSize()/2)+1));
                    approvalRatingChange += ra.nextInt(5);
                }
                moderateVoters();
            }
                break;
            case 2:
                System.out.println("Labor Strikes!");
            for(ideoGroup gro : allGroups){
                if(gro.getIdeology()> 60){
                    gro.updateSize(ra.nextInt((gro.getSize()/2)+1));
                }
            }
                break;
            case 3:
                System.out.println("Immigration Crisis!");
            for(ideoGroup gro : allGroups){
                if(gro.getIdeology()< 40){
                    gro.updateSize(ra.nextInt((gro.getSize()/2)+1));
                }
            }
                break;
            case 4:
                System.out.println("Populist Wave!");
                for(Party par : allParties) {
        // Establishment parties (high recognition) get their bonus flipped to a penalty
        if (par.getRecognition() > 0) {
            par.setRecog(par.getRecognition() * -1.5); // Punish them harder than they were helped
        } else {
            // Insurgent/New parties get a massive boost
            par.setRecog(0.5); 
        }
    }
    // Make the supporters of the ruling coalition particularly angry
    for(Party member : rulingCoalition.getMemberList()) {
        for(ideoGroup gro : allGroups) {
            if(gro.proximityWith(member) > 70) {
                gro.updateSatisfaction(-20);
            }
        }
    }
                break;
            
        }
    }
}

public static ideoGroup findClosestGroup(int toFind){
    int maxnum = 0;
    ideoGroup maxGroup=null;
    for(ideoGroup gro: allGroups){
        int curscore = 100-Math.abs(gro.getIdeology()-toFind);
        if(curscore> maxnum){
            maxnum = curscore;
            maxGroup = gro;
        }
    }
    return maxGroup;
}

public static void moderateVoters() {
    for (ideoGroup gro : allGroups) {
        // If satisfaction is high (Economic Boom or good governance)
        if (gro.getSatisfaction() > 70) {
            int moderates = gro.getSize() / 15; // ~6% move toward center
            gro.updateSize(-moderates);
            
            ideoGroup target;
            // Move toward the 50 (Center) mark
            if (gro.getIdeology() > 50) {
                target = findClosestGroup(gro.getIdeology() - 15);
            } else {
                target = findClosestGroup(gro.getIdeology() + 15);
            }
            
            if (target != null) target.updateSize(moderates);
        }
    }
}

public static void radicalizeVoters() {
    for (ideoGroup gro : allGroups) {
        // If a group is very unhappy, some members move toward the extremes
        if (gro.getSatisfaction() < 25) {
            int defectors = gro.getSize() / 20; // 5% leave
            gro.updateSize(-defectors);
            
            // If they are on the Left half, they move further Left.
            // If on the Right half, they move further Right.
            ideoGroup target = null;
            if (gro.getIdeology() > 50) {
                target = findClosestGroup(gro.getIdeology() + 15);
            } else {
                target = findClosestGroup(gro.getIdeology() - 15);
            }
            if (target != null) target.updateSize(defectors);
        }
    }
}
    
    public static void updateTick(){
        events();
        checkFails();
        updateGroupSize();
        radicalizeVoters();
        checkForNewParties();
        approvalRatingChange = ra.nextInt(5)-ra.nextInt(10);
        for(Party par: rulingCoalition.getMemberList()){
            approvalRatingChange*= (ra.nextInt(3))+1;
            par.updateApproval(approvalRatingChange);
            
            par.ideoDrift();
        }
        
        Collections.sort(allParties, Comparator.comparingInt(Party::getIdeology));
    }
    
    
    public static class Archive{
        String name;
        int start, end;
        public Archive(String name, int start, int end){
            this.name = name;
            this.start = start;
            this.end = end;
        }
        
        @Override
        public String toString(){
            return name + " "+start+"-"+end;
        }
    }
    
    
    public static List<Archive> leaderArchive = new ArrayList<>();
    
    
    
    
    public static String getDynamicColor(int ideo) {
    int colorCode;
    
    // RIGHT-WING: Blue/Navy spectrum
    if (ideo < 20) colorCode = 18;       // Navy Blue (Reactionary/Far-Right)
    else if (ideo < 35) colorCode = 27;  // Royal Blue (Conservative)
    
    // CENTER: Yellow/Gold/Orange spectrum
    else if (ideo < 45) colorCode = 214; // Orange-Yellow (Liberal/Center-Right)
    else if (ideo < 55) colorCode = 226; // Bright Yellow (Pure Centrist)
    else if (ideo < 65) colorCode = 190; // Lime/Yellow-Green (Center-Left/Green)
    
    // LEFT-WING: Red/Crimson spectrum
    else if (ideo < 80) colorCode = 203; // Light Red (Social Democrat)
    else if (ideo < 95) colorCode = 196; // Pure Red (Socialist)
    else colorCode = 88;                // Dark Crimson (Communist/Far-Left)

    return "\u001B[38;5;" + colorCode + "m";
}
public static final String RESET = "\u001B[0m";
    
    public static void visualizeParliament() {
    Collections.sort(allParties, Comparator.comparingInt(Party::getIdeology));

    System.out.println("\n      --- THE NATIONAL ASSEMBLY ---");
    
    List<String> allSeats = new ArrayList<>();
    for (Party par : allParties) {
        String color = getDynamicColor(par.getIdeology());
        for (int i = 0; i < par.getPercent(); i++) {
            allSeats.add(color + "o" + RESET);
        }
    }

    // Fill remaining seats if election math is slightly off 100
    while (allSeats.size() < 100) allSeats.add("·");

    // Print in a 10x10 block
    for (int i = 0; i < 100; i++) {
        System.out.print(allSeats.get(i) + " ");
        if ((i + 1) % 10 == 0) System.out.println(); // New row every 10 seats
    }

    System.out.println("-------------------------------------");
    
    // Print Legend
    for (Party par : allParties) {
        if (par.getPercent() > 0) {
            System.out.print(getDynamicColor(par.getIdeology()) + "o " + RESET 
                + par.getName() + " [" + par.getPercent() + "%]  ");
        }
    }
    System.out.println("\n");
}
    
    
    
	public static void main(String[] args) {
	    addGroups();
	    addParties();
		
		int interval  =4;
		int electionsToSimulate = 32;
		
		for(int i=0; i<electionsToSimulate;i++){
		    System.out.println(year+ "=========================");
		    election();
		    electLeadParty();
		    
		    //System.out.println("Winner: "+ rulingCoalition.getLeader().getName());
		    
		    /*for(Party par: allParties){
		        System.out.println(par.getName()+ " "+ par.getPercent()+"%");
		        System.out.println("Ideology: "+ detIdeo(par));
		        //System.out.println(par.getRecognition());
		        System.out.println("====================");
		    }
		    System.out.println("\n");*/
		    
		    char[] spectrum = new char[21]; // 0 to 100 in blocks of 5
    Arrays.fill(spectrum, '-');
    for (Party p : allParties) {
        int index = p.getIdeology() / 5;
        if (p.getPercent() > 20) spectrum[index] = 'X'; // Major Party
        else if (p.getPercent() > 5) spectrum[index] = 'o'; // Minor Party
    }
    visualizeParliament();
    System.out.println("Spectrum: [R] " + String.valueOf(spectrum) + " [L]");
		    for(ideoGroup gro : allGroups){
		        //System.out.println(gro.getName()+ " "+ gro.getSize() + " "+ gro.getSatisfaction());
		    }
		    double totalRecog = 0;
for(Party p : allParties) totalRecog += p.getRecognition();
System.out.println("Establishment Strength: " + String.format("%.2f", totalRecog));
		    sc.nextLine();
		    updateTick();
		    year+=interval;
		    
		}
		
	}
}
