import java.util.*;

public class Main
{
    public static Scanner sc = new Scanner(System.in);
    public static Random ra = new Random();
    public static class Party{
        String name;
        int ideology;
        
        int seats=0;
        int points=0;
        int momentum=0;
        
        public Party(String name, int ideology){
            this.name = name;
            this.ideology = ideology;
        }
        
        public String getName(){return name;}
        public int getSeats(){return seats;}
        public int getPoints(){return points;}
        public int getMomentum(){return momentum;}
        
        public void updatePoints(){
            points+= momentum;
            momentumCountdown();
        }
        
        public void momentumCountdown(){
            if(momentum>0){
                momentum--;
            }
        }
        
        public void updateMomentum(){
            momentum+= ra.nextInt(10);
        }
        
        public void addSeats(){
            seats++;
        }
        
        public void resetPoints(){
            points = 0;
        }
        
        public void resetSeats(){
            seats = 0;
        }
    }
    
    public static List<Party> allParties = new ArrayList<>();
    
    public static String getName(int ideology){
        String[] farRightParties = {
    "National Heritage Front", "Sovereign Peoples Alliance", "Traditionalist Guard", "Patriot Unity Party", 
    "Fatherland First", "National Restoration Movement", "Identity Alliance", "Sacred Soil Coalition", 
    "National Vanguard", "Sovereign Will Party", "Legacy and Order", "Ancestral Roots Party", 
    "National Solidarity Front", "True Patriot League", "Cultural Defense Force", "National Revivalist Party", 
    "Sovereignty Defense", "Pure Nation Movement", "Order and Heritage", "National Bastion", 
    "Preservation Front", "Native Lands Party", "National Honor Council", "Unity and Strength", 
    "Traditional Values Bloc", "National Guard Party", "Sovereign Dawn", "Homeland Defense League", 
    "National Soul Party", "Integrity and Nation", "National Steel Party", "Heritage Foundation Party", 
    "The Patriotic Front", "National Shield", "Sovereignty First", "Traditionalist Union", 
    "National Core Movement", "Peoples National Guard", "Identity First", "Nationalist Will", 
    "Homeland Restoration", "National Duty Party", "Citizens for Sovereignty", "National Spirit Bloc", 
    "The Sovereignty League", "Patriot Vanguard", "National Pillar", "Homeland Unity", 
    "National Essence Party", "The Sovereign Guard"
};

String[] rightWingParties = {
    "Conservative Union", "National Action Party", "Liberty and Order", "National Reform Bloc", 
    "The Republican League", "Conservative Alliance", "National Stability Party", "United Right Movement", 
    "The Order Party", "National Strength", "Conservative Peoples Party", "Freedom and Security", 
    "National Prosperity Party", "Citizens for Order", "The Federalist Party", "National Coalition", 
    "Law and Liberty Party", "National Unity Movement", "Traditionalist Party", "Conservative Front", 
    "National Direction", "The Security Bloc", "Liberty First", "National Integrity", 
    "Right-Way Alliance", "Conservative Democratic Party", "National Progress Bloc", "The Solidary Right", 
    "Security and Growth", "Constitutional Union", "The National League", "Conservative Guard", 
    "National Merit Party", "Responsible Governance", "The Rightward Path", "National Enterprise Party", 
    "Civic Order Party", "The Patriotic Union", "United Conservative Front", "National Justice Party", 
    "Stable Future Party", "The Conservative Will", "National Standard", "Liberty and Nation", 
    "The Rightist Bloc", "National Foundation", "Order and Liberty", "Conservative Strength", 
    "National Consensus", "The Right Alliance"
};

String[] centerRightParties = {
    "Liberal Conservative Party", "Reform and Growth", "The Moderate Bloc", "Democratic Union", 
    "Civic Prosperity Party", "The Liberty Alliance", "Modern National Party", "Economic Reform Party", 
    "The Center-Right Union", "National Liberal Party", "Prosperity First", "The Modernist League", 
    "Civic Reform Movement", "The Democratic Coalition", "National Opportunity Party", "Liberal Union", 
    "The Growth Alliance", "Modern Conservative Party", "Civic Liberty Party", "The Reform League", 
    "Economic Liberty Party", "The Center Alliance", "National Development Party", "The Merit Party", 
    "Modern Citizens' Union", "Progressive Conservative Party", "Market Liberty Party", "The Liberal League", 
    "Civic Strength Party", "The Reformist Front", "National Progress Party", "The Liberty Bloc", 
    "Modern Governance Party", "Economic Unity", "The Civic Union", "National Recovery Party", 
    "The Growth Party", "Democratic Reform Bloc", "The Middle Path Right", "Civic Action Party", 
    "National Modernizers", "The Prosperity Bloc", "Liberal Action Party", "Center-Right Coalition", 
    "National Renewal Party", "The Liberty Guard", "Economic Progress Party", "Modern Unity", 
    "The Reformist Union", "Civic Merit League"
};

String[] centerLeftParties = {
    "Social Democratic Party", "Democratic Labor Party", "The Progressive Alliance", "Modern Social Party", 
    "Peoples Reform Party", "The Social Union", "Citizens' Progress Party", "Democratic Reformists", 
    "Social Justice Party", "The Welfare Bloc", "Peoples Democratic Party", "Equality and Progress", 
    "The Moderate Left", "Social Labor Alliance", "National Social Party", "The Reformist Left", 
    "Civic Equality Party", "Social Liberty Movement", "The Peoples Coalition", "Progressive Labor", 
    "The Democratic Front", "Social Renewal Party", "Citizens' Welfare Party", "Modern Labor Party", 
    "Social Progress Union", "The Democratic League", "Peoples Justice Party", "Fair Society Party", 
    "The Social League", "Progressive Reform Bloc", "Democratic Equality", "The Peoples Union", 
    "Social Reform Party", "Citizens' Solidarity Party", "The Progress Party", "Labor and Liberty", 
    "Social Fairness Party", "The Reformist Bloc", "Modern Equality Party", "Social Unity Movement", 
    "The Peoples Bloc", "Democratic Welfare Party", "Civic Progress League", "Social Future Party", 
    "The Equality Front", "Progressive Social Union", "The Labor Front", "Social Balance Party", 
    "The Democratic Path", "Peoples Prosperity League"
};

String[] leftWingParties = {
    "Workers Party", "Socialist Alliance", "Peoples Labor Front", "The Socialist Union", 
    "Workers Unity Party", "Solidarity Movement", "Social Justice Front", "Workers Rights Party", 
    "The Peoples Movement", "United Socialist Party", "Labor and Solidarity", "Workers Progress Party", 
    "Socialist Action League", "Peoples Empowerment Party", "The Workers Bloc", "Equality and Justice", 
    "Socialist Labor Movement", "Peoples Will Party", "Workers Front", "Socialist Reform Party", 
    "Solidarity and Justice", "The Peoples Guard", "Workers League", "Socialist Strength", 
    "Common Good Party", "The Worker Union", "Peoples Equality Party", "Socialist Unity", 
    "Labor Reform Party", "The Solidarity Bloc", "Workers' Action Party", "Peoples Victory Party", 
    "Socialist Republic Party", "United Labor Front", "The Workers Guard", "People's Liberty Party", 
    "Socialist Future", "Labor Power Party", "The Peoples Voice", "Socialist Progress Party", 
    "Workers Collective", "The Solidarity Front", "Peoples Strength Party", "Socialist League", 
    "Labor and Justice", "The Peoples League", "Workers Solidarity Party", "Socialist Vanguard", 
    "Equality Movement", "The Workers Alliance"
};

String[] farLeftParties = {
    "Revolutionary Workers Party", "Communist League", "Peoples Revolutionary Front", "Red Vanguard", 
    "Socialist Liberation Party", "Revolutionary Unity", "Workers Liberation Front", "The Peoples Army", 
    "Communist Solidarity", "Revolutionary Action Party", "Peoples Communes Party", "The Red Front", 
    "Workers Resistance", "Revolutionary Labor League", "Communist Unity Bloc", "The Peoples Struggle", 
    "Socialist Vanguard Party", "Revolutionary Socialist Party", "Red Solidarity Movement", "The Peoples Guard", 
    "Communist Progress Party", "Revolutionary Will Party", "Workers Republic Front", "The Red League", 
    "Socialist Struggle Party", "Revolutionary Progress", "Communist Front", "The People's Vanguard", 
    "Revolutionary Socialist Union", "Workers Power Bloc", "Red Action Party", "The Peopl's Resistance", 
    "Communist Labor Party", "Revolutionary Defense", "Socialist Revolution Party", "Workers Liberation", 
    "The Red Union", "Revolutionary Solidarity", "Communist Will", "The Peoples Liberation", 
    "Revolutionary Front", "Red Future Party", "Workers Communes", "The Socialist Army", 
    "Revolutionary Core", "Communist Action", "The Peoples Revolution", "Red Workers' Party", 
    "Revolutionary League", "The Socialist Bastion"
};
        
        
        
        
        
        String toreturn = "";
        
        switch(ideology){
            case 0: toreturn = farRightParties[ra.nextInt(farRightParties.length)];
                break;
            case 1: toreturn = rightWingParties[ra.nextInt(rightWingParties.length)];
                break;
            case 2: toreturn = centerRightParties[ra.nextInt(centerRightParties.length)];
                break;
            case 3: toreturn = centerLeftParties[ra.nextInt(centerLeftParties.length)];
                break;
            case 4: toreturn = leftWingParties[ra.nextInt(leftWingParties.length)];
                break;
            case 5: toreturn = farLeftParties[ra.nextInt(farLeftParties.length)];
                break;
        }
        
        return toreturn;
        
    }
    
    public static void newParty(){
        int ideology = ra.nextInt(6);
        String name = getName(ideology);
        
        allParties.add(new Party(name, ideology));
    }
    
    public static void updateAllMomentum(){
        for(Party par: allParties){
            par.updateMomentum();
        }
    }
    
    public static void updateAllPoints(){
        for(Party par: allParties){
            par.updatePoints();
        }
    }
    
    public static void resetAllPoints(){
        for(Party par: allParties){
            par.resetPoints();
        }
    }
    
    public static void resetAllSeats(){
        for(Party par: allParties){
            par.resetSeats();
        }
    }
    
    public static int month = 0;
    public static int year = 1920;
    public static String[] monthName = {"January","February","March","April","May","June","July","August","September","October","November","December"};
    
    
    public static void parliamentaryElection(){
        int maxSeats = 100;
        int maxPoints = 0;
        Party winner=null;
        for(int i=0; i<maxSeats;i++){
            winner = null;
            maxPoints = 0;
            for(Party par: allParties){
                int curpoints = (par.getPoints()*100)/ (ra.nextInt(4)+1);
                if(curpoints > maxPoints){
                    maxPoints = curpoints;
                    winner = par;
                }
            }
            
            
            winner.addSeats();
            
        }
    }
    
    public static void parliamentDisplay(){
        for(Party par: allParties){
            System.out.println(par.getName()+" "+ par.getSeats()+"%");
        }
    }
    
    public static int parliamentCountdown = 5;
    
    public static void checkParCountdown(){
        parliamentCountdown--;
        if(parliamentCountdown == 0){
    	    parliamentaryElection();
    	    parliamentDisplay();
    	    
    	    parliamentCountdown = 60;
    	}
    }
    
    public static void monthly(){
        month++;
        if(month == 12){
            month = 0;
            year++;
        }
        
        updateAllMomentum();
    		updateAllPoints();
    		
    		checkParCountdown();
    		
    	
    }
    
    
    
	public static void main(String[] args) {
		for(int i=0; i<5; i++){
		    newParty();
		}
		
		while(true){
    		
    		
    		System.out.println(monthName[month]+", "+ year);
    		
    		for(Party par: allParties){
    		    System.out.println(par.getName()+ " "+ par.getPoints()+ " ("+ par.getMomentum()+")");
    		}
    	    sc.nextLine();
    	    monthly();
		}
	}
}
