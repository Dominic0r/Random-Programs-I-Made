import java.util.*;

public class Main
{
    public static Random ra = new Random();
    
    public static int year = 1925;
    public static int moNum =0;
    public static String[] months = {"January","February","March","April","May","June","July","August","September","October","November","December"};
    
    public static int elecCount = 6;
    
    public static int startdate = year;
    
    public static boolean snapElec = false;
    
    public static int cooldown = 10;
    
    public static int auth = 0; // authoritarianism
    public static boolean isFair = true;
	
    
	 // ECONOMIC SYSTEM VARIABLES
    public static int economicIndex = 50; // Range: 0-100, 50 is average
    public static int unemploymentRate = 10; // percent
	
    public static class Group{
        int minPolicy;
        int maxPolicy;
        int givePoints;
        boolean isAlienated;
        int minpoints;
        int maxpoints;
        String name;
        
        Person leader;
        
        List<String>partyNames = new ArrayList<>();
        
        public Group(int minPolicy, int maxPolicy, String name, int givePoints){
            this.minPolicy = minPolicy;
            this.maxPolicy = maxPolicy;
            this.name = name;
            this.isAlienated = false;
            this.givePoints = givePoints;
            minpoints = givePoints- (givePoints/2);
            maxpoints = givePoints + (givePoints/2);
            this.leader = new Person(ra.nextInt(20)+45, getRandomName(), minPolicy +ra.nextInt(maxPolicy-minPolicy));
            
        }
        
        public int getMin(){
            return this.minPolicy;
        }
        
        
        public void checkLeaderAge(){
            if(leader.getAge() > 65){
                if(ra.nextInt(20)+75 < leader.getAge()){
                    oldPerson = this.leader;
                    changedLead = true;
					
					
					
                    
					if(oldPerson !=president && oldPerson != vicePresident){
						this.leader = new Person(ra.nextInt(20)+45, getRandomName(), minPolicy +ra.nextInt(maxPolicy-minPolicy));
					}else{
						if(oldPerson == president){
							addToPresArchive(president);
							president = vicePresident;
							presStartYear = year;
							appointVP();
						}
						
						if(oldPerson == vicePresident){
							appointVP();
						}
						this.leader = new Person(ra.nextInt(20)+45, getRandomName(), minPolicy +ra.nextInt(maxPolicy-minPolicy));
					}
                }
            }
        }
        
        public Person getLeader(){
            return this.leader;
        }
        
        public int getMax(){
            return this.maxPolicy;
        }
        
        public boolean getAlienated(){
            return this.isAlienated;
        }
        
        public String getName(){
            return this.name;
        }
        
        public int getPoints(){
            return this.givePoints;
        }
        
        public void changePoints(int addPoints){
            this.givePoints += addPoints;
            
            
            if(this.givePoints<1){
                this.givePoints = 1;
            }
            
            if(this.givePoints > maxpoints){
                this.givePoints = maxpoints;
            }
            
            if(this.givePoints < minpoints){
                this.givePoints = minpoints;
            }
        }
        
        
        public void changeAlienation(boolean setTo){
            isAlienated = setTo;
        }
        
        public void addPartyName(String newName){
            partyNames.add(newName);
        }
        
        public String getRandomPartyName(){
            //return this.partyNames.get(ra.nextInt(this.partyNames.size()));
            int avg = (minPolicy+maxPolicy)/2;
            if(avg>50){
                if(avg>85){
                    return farLeft.get(ra.nextInt(farLeft.size()));
                }else if(avg <=85 && avg> 70){
                    return leftWing.get(ra.nextInt(leftWing.size()));
                }else{
                    return centerLeft.get(ra.nextInt(centerLeft.size()));
                }
            }else{
                if(avg>30){
                    return centerRight.get(ra.nextInt(centerRight.size()));
                }else if(avg<=30 && avg>15){
                    return rightWing.get(ra.nextInt(rightWing.size()));
                }else{
                    return farRight.get(ra.nextInt(farRight.size()));
                }
            }
        }
        
        public int getIdeology(){
            return (minPolicy+maxPolicy)/2;
        }
    }
    
    public static class Party{
        int policy;
        int seats;
        String name;
        boolean isMajor;
        int unity;
		int failcount;
		
		armedGroup paramilitary;
        
        Person leader;
        List<Group> supportGroups = new ArrayList<>();
        
        public Party(int policy, String name){
            this.policy = policy;
            this.name = name;
            this.seats = 0;
			this.failcount = 0;
        }
		
		public void addFailCount(){
			failcount++;
		}
		
		public void resetFail(){
			failcount = 0;
		}
		
		public int getFailCount(){
			return failcount;
		}
        
        public void unityUpdate(){
            int ideounity = 0;
            int minide = 101;
            int maxide = -1;
            for(Group gro: supportGroups){
                if(gro.getIdeology() > maxide){
                    maxide = gro.getIdeology();
                }
                
                if(gro.getIdeology() < minide){
                    minide = gro.getIdeology();
                }
            }
            
            ideounity = (100-Math.abs(maxide-minide))/4;
            
            int numpar = 25- (supportGroups.size()*5);
			int seatadd = seats/4;
			int failed = (failcount==0)? 25:25-(failcount*5);
            unity = ideounity + numpar+seatadd+failed;
        }
        
        public int getUnity(){
            return unity;
        }
        
        public void determineLeader(){
            if(supportGroups!= null){
                
                int wpoint = -1;
                Group wingroup = null;
                
                oldPerson = leader;
                    if(leader != null){
                        for(Group gro: supportGroups){
                            if(gro.leader == leader){
                                wpoint = gro.getPoints()*5;
                                wingroup = gro;
                            }
                        }
                    }
                
                for(Group gro : supportGroups){
                    if(gro.getPoints() > wpoint){
                        wpoint = gro.getPoints();
                        wingroup = gro;
                    }
                }
                if(wingroup != null){
                    leader = wingroup.getLeader();
                    
                }else{
                    leader = new Person(ra.nextInt(20)+45, getRandomName(), policy);
                }
                
            } else{
                leader = new Person(ra.nextInt(20)+45, getRandomName(), policy);
            }
            
            if(leader !=oldPerson && oldPerson != null){
                changedLead = true;
            }
        }
        
        public boolean isMajor(){
            return this.isMajor;
        }
        
        public void setIsMajor(boolean newMaj){
            this.isMajor = newMaj;
        }
        
        public Person getLeader(){
            return this.leader;
        }
        
        public void addToSupport(Group addGroup){
            this.supportGroups.add(addGroup);
        }
        
        public void removeFromSupport(Group remGroup){
            this.supportGroups.remove(remGroup);
        }
        
        public int getPolicy(){
            return this.policy;
        }
        
        public void changePolicy(int addPol){
            this.policy+= addPol;
            if(this.policy>100){
                this.policy = 100;
            }
            
            if(this.policy<1){
                this.policy = 1;
            }
        }
        
        public String getName(){
            return this.name;
        }
        
        public void setSeats(int seats){
            this.seats = seats;
        }
        
        public int getSeats(){
            return this.seats;
        }
        
        public String getIdeology(){
            String returnIdeo = "";
            if(this.policy>50){
                if(this.policy>85){
                    returnIdeo = "Far-left";
                }else if(this.policy <=85 && this.policy> 70){
                    returnIdeo = "Left-wing";
                }else{
                    returnIdeo = "Center left";
                }
            }else{
                if(this.policy>30){
                    returnIdeo = "Center right";
                }else if(this.policy<=30 && this.policy>15){
                    returnIdeo = "Right-wing";
                }else{
                    returnIdeo = "Far-Right";
                }
            }
            
            return returnIdeo;
        }
        
    }
    
    public static class Person {
        int age;
        String name;
        int ideology;
        
        public Person(int age, String name, int ideology){
            this.age = age;
            this.name = name;
            this.ideology = ideology;
        }
        
        public void ageUp(){
            this.age++;
        }
        
        public int getIdeology(){
            return this.ideology;
        }
        
        public String disIdeo(){
            String returnIdeo = "";
            if(this.ideology>50){
                if(this.ideology>85){
                    returnIdeo = "Far-left";
                }else if(this.ideology <=85 && this.ideology> 70){
                    returnIdeo = "Left-wing";
                }else{
                    returnIdeo = "Center left";
                }
            }else{
                if(this.ideology>30){
                    returnIdeo = "Center right";
                }else if(this.ideology<=30 && this.ideology>15){
                    returnIdeo = "Right-wing";
                }else{
                    returnIdeo = "Far-Right";
                }
            }
            
            return returnIdeo;
        }
        
        public String getName(){
            return this.name;
        }
        
        public int getAge(){
            return this.age;
        }
    }
    
    public static List<Person> allPersons = new ArrayList<>();
    
    
    public static class archiveParty{
        int startdate,enddate;
        String name;
        String leaderName;
        
        public archiveParty(String name, int startdate, int enddate, String leaderName){
            this.name = name;
            this.startdate = startdate;
            this.enddate = enddate;
            this.leaderName = leaderName;
        }
        
        public void display(){
            System.out.println(this.leaderName + " | "+ this.name + " ("+ this.startdate+ " - "+ this.enddate+ ")");
        }
    }
    
    public static List<archiveParty> previousRulingParties = new ArrayList<>();
    
    
    public static class Coalition{
        ArrayList<Party> members = new ArrayList<>();
        Party leader;
        int stability;
        
        public Coalition(Party leader){
            this.leader = leader;
            members.add(leader);
        }
        
        public void addToMemberList(Party toAdd){
            members.add(toAdd);
        }
        
        public int getTotalSeats(){
            int total = 0;
            for(Party par : members){
                total += par.getSeats();
            }
            
            return total;
        }
        
        public int getStability(){
            int totstab = 0;
            int ideodif = 0;
            int parnumfac = 0;
            
            int extremeideoleft = -1;
            int extremeideoright = 101;
            
            for(Party par: members){
                if(par!= leader){
                    ideodif += Math.abs(par.getPolicy()- leader.getPolicy());
                    
                }
                
                if(par.getPolicy() > extremeideoleft){
                        extremeideoleft = par.getPolicy();
                    }
                    
                    if(par.getPolicy() < extremeideoright){
                        extremeideoright = par.getPolicy();
                    }
            }
            ideodif /= members.size();
            ideodif = 50- (ideodif/2);
            
            int extremedif = Math.abs(extremeideoright-extremeideoleft);
            
            
            
            
            
            /*if(members.size() ==1){
                parnumfac = 50/ members.size();
            }else{
                parnumfac = 50/ (members.size()-1);
            }*/
            
            parnumfac = 50 - (5*members.size());
            
            
            totstab+= ideodif + parnumfac;
            
            if(extremedif> 50){
                totstab -= totstab/4;
            }
            if(extremedif< 20){
                totstab+= totstab/4;
            }
            
            
            if(getTotalSeats() > 59){
                totstab+= totstab/4;
            }
            
            if(leader.getSeats() <= getTotalSeats()/2){
                totstab-=totstab/4;
            }
            stability = totstab;
            return totstab;
            
        }
		
		public boolean containsRadicals(){
			boolean hasRad = false;
			
			if(members != null){
				for(Party par: members){
					if(par.getPolicy() > 85 || par.getPolicy() < 15){
						hasRad = true;
					}
				}
			}
			
			return hasRad;
		}
        
        public int getNumOfMembers(){
            return members.size();
        }
        
        public Party getLeader(){
            return this.leader;
        }
        
        
        public void displayMembers(){
            for(Party par : members){
                System.out.println(par.getName());
            }
        }
        
        public boolean hasParty(Party testpar){
            return members.contains(testpar);
        }
    }
	
	public static class armedGroup{
		String name;
		int strength;
		boolean isPolitical;
		
		public armedGroup(String name, int strength, boolean isPolitical){
			this.name = name;
			this.strength = strength;
			this.isPolitical = isPolitical;
		}
	}
	
	public static armedGroup Police = new armedGroup("National Police", 1000, false);
    
    
    public static List<Group> allGroups = new ArrayList<>();
    public static void generateGroups(){
/*allGroups.add(new Group(1, 15, "Traditionalists", 20));         // 0 – culturally rigid, fading influence
allGroups.add(new Group(10, 30, "Nationalists", 35));           // 1 – assertive, suspicious of global elites
allGroups.add(new Group(30, 40, "Capitalists", 70));            // 2 – elite-backed, anti-populist
allGroups.add(new Group(20, 35, "Law & Order Bloc", 50));       // 3 – pro-police, anti-chaos, neutral on economics
allGroups.add(new Group(40, 55, "Small Business Owners", 45));  // 4 – practical-minded, split over regulation
allGroups.add(new Group(15, 30, "Rural Conservatives", 30));    // 5 – nostalgic, pro-subsidies
allGroups.add(new Group(55, 70, "Liberal Reformers", 70));      // 7 – urbanites, reform-focused
allGroups.add(new Group(40, 60, "Centrists / Moderates", 80)); // 6 – technocratic, middle-of-the-road
allGroups.add(new Group(75, 85, "Labor Unions", 50));           // 8 – class-driven, suspicious of elites
allGroups.add(new Group(65, 80, "Progressives", 60));           //9 – identity-focused, decentralized
allGroups.add(new Group(70, 85, "Environmentalists", 20));      //10 – passionate but divided
allGroups.add(new Group(75, 90, "Socialists", 35));            //11 – radical but infighting-prone
allGroups.add(new Group(85, 100, "Radicals", 15));          //12 – chaotic, often uncooperative*/

/*// Radical Right (0–15)
allGroups.add(new Group(0, 10, "Nationalists", getranNumbetween100and1(25))); // 35
allGroups.add(new Group(5, 15, "Reactionaries", getranNumbetween100and1(25))); // 25

// Dissident Right (15–30)
allGroups.add(new Group(15, 25, "Conservatives", getranNumbetween100and1(25)+25)); //40
allGroups.add(new Group(20, 30, "Traditional Right", getranNumbetween100and1(25)+25)); //30

// Establishment Right (30–45)
allGroups.add(new Group(30, 40, "Capitalists", getranNumbetween100and1(25)+50)); // 60
allGroups.add(new Group(35, 45, "Defense Industry", getranNumbetween100and1(25)+50)); //50

// Establishment Left (55–70)
allGroups.add(new Group(55, 65, "Administratic Progressives", getranNumbetween100and1(25)+50)); // 55
allGroups.add(new Group(60, 70, "Labor Moderates", getranNumbetween100and1(25)+50)); // 50

// Dissident Left (70–85)
allGroups.add(new Group(70, 80, "Reformists", getranNumbetween100and1(25)+25)); // 30
allGroups.add(new Group(75, 85, "Grassroots Progressives", getranNumbetween100and1(25)+25)); //35

// Radical Left (85–100)
allGroups.add(new Group(85, 95, "Socialists", getranNumbetween100and1(25))); // 25
allGroups.add(new Group(90, 100, "Communists", getranNumbetween100and1(25))); // 20*/

allGroups.add(new Group(0, 25, "Traditional Conservatives", 70));      // old elite, nobles, landowners
allGroups.add(new Group(20, 45, "Moderate Right Reformers", 50));     // constitutional monarchists, pragmatists
allGroups.add(new Group(40, 60, "Centrist Liberals", 65));            // business liberals, rule-of-law advocates
allGroups.add(new Group(55, 80, "Social Democrats", 75));             // moderate left, union-friendly
allGroups.add(new Group(75, 100, "Radical Socialists", 55));          // socialist activists, syndicalists
allGroups.add(new Group(85, 100, "Communist Vanguard", 40));          // revolutionaries, foreign-aligned communists

allGroups.add(new Group(0, 30, "Industrial Magnates", 80));           // major industrialists
allGroups.add(new Group(25, 50, "Small Business Owners", 60));        // shopkeepers, local entrepreneurs
allGroups.add(new Group(40, 70, "Urban Working Class", 90));          // factory workers, laborers
allGroups.add(new Group(60, 90, "Trade Unions", 85));                 // organized labor
allGroups.add(new Group(10, 40, "Agrarian Landowners", 65));          // rural landlords, estate owners
allGroups.add(new Group(50, 80, "Tenant Farmers & Peasants", 75));    // smallholders, sharecroppers

allGroups.add(new Group(0, 25, "Officer Corps", 75));                 // professional military elites
allGroups.add(new Group(20, 45, "Veterans' Leagues", 60));            // disillusioned ex-soldiers, paramilitaries
allGroups.add(new Group(45, 70, "Military Workers & Quartermasters", 40)); // rank-and-file soldiers

allGroups.add(new Group(0, 30, "Clergy & Church Establishment", 70)); // traditional moral authority
allGroups.add(new Group(40, 65, "Urban Middle Class", 80));           // bureaucrats, teachers, professionals
allGroups.add(new Group(55, 85, "Intellectuals & Academics", 60));    // artists, writers, professors
allGroups.add(new Group(20, 50, "Traditional Media Owners", 55));     // press barons, conservative journalists
allGroups.add(new Group(60, 90, "Progressive Journalists", 45));      // liberal and socialist press

allGroups.add(new Group(20, 60, "City Voters", 85));                  // urban electorate
allGroups.add(new Group(10, 50, "Rural Voters", 90));                 // agrarian communities
allGroups.add(new Group(50, 90, "Racial Minorities", 50));            // marginalized ethnic groups
allGroups.add(new Group(35, 70, "Immigrant Communities", 55));        // recent arrivals, labor migrants
allGroups.add(new Group(25, 60, "Women’s Organizations", 65));        // suffragists, homemakers
allGroups.add(new Group(15, 45, "Traditional Families", 70));         // moral conservatives, family associations



    }
	
	public static int getranNumbetween100and1(int num){
		return ra.nextInt(num)+1;
	}
    
    
            // Far-left Parties
public static ArrayList<String> farLeft = new ArrayList<>(Arrays.asList(
    "Workers' Vanguard Party",
    "Revolutionary Socialist Front",
    "United Proletarian Alliance",
    "Communist Renewal Bloc",
    "Red Star League",
    "Peoples' Revolutionary Council",
    "Anti-Capitalist Collective",
    "Socialist Unity Organization",
    "Union of Revolutionary Workers",
    "Radical Labor Movement",
    "People’s Liberation Party",
    "Proletarian Justice Front",
    "Workers' Liberation Front",
    "Internationalist Communist League",
    "Peasants and Workers’ Congress",
    "Socialist Revolutionary Front",
    "Workers' Solidarity Movement",
    "Class Struggle Party",
    "Peoples' Power Coalition",
    "United Revolutionary Left",
    "Socialist Action Committee",
    "Anti-Imperialist Front",
    "Red Workers’ Alliance",
    "Marxist Renewal Movement",
    "Democratic Socialist Front",
    "Communist Workers’ Bloc",
    "Workers' Struggle Party",
    "Council of the Left",
    "Socialist Resistance League",
    "Proletarian Unity Party",
    "Revolutionary Action Front",
    "Union of the Oppressed",
    "Workers' Liberation Council",
    "Radical Socialist Bloc",
    "Red Unity Movement",
    "Front of the Toilers",
    "Socialist Liberation Party",
    "Labor Socialist Alliance",
    "International Red Front",
    "Democratic Workers' Movement",
    "Progressive Socialist League",
    "Class Unity Front",
    "Socialist Advance Bloc",
    "Revolutionary Democracy Party",
    "Alliance for Workers' Power",
    "Workers’ Struggle Collective",
    "Freedom and Socialism Party",
    "Left Socialist Congress",
    "Peoples' Struggle Movement"
));

// Left-wing Parties
public static ArrayList<String> leftWing = new ArrayList<>(Arrays.asList(
    "Progressive Party",
    "Democratic Social Party",
    "Labor Alliance",
    "Social Justice Party",
    "Equality Movement",
    "New Left Coalition",
    "People’s Democratic Front",
    "Progressive Labor League",
    "United Socialist Democrats",
    "Alliance for Equality",
    "Democratic Reform Bloc",
    "Workers' Rights Party",
    "Forward Together Party",
    "Green Socialist Movement",
    "Justice and Progress Party",
    "Democratic Socialist League",
    "Union of Democratic Forces",
    "Equality and Freedom Front",
    "Progressive Unity Party",
    "Social Justice League",
    "Labor and Justice Party",
    "United Democratic Movement",
    "Freedom and Equality Party",
    "New Progressive Alliance",
    "Workers’ Progress Party",
    "Democratic Equality Front",
    "Socialist Democrats Union",
    "Justice Party",
    "Democracy and Labor Party",
    "Progressive Front",
    "Social Reform Bloc",
    "Union for Justice",
    "United Progressives",
    "Democratic Renewal League",
    "Equality Bloc",
    "Labor Progress Alliance",
    "United Justice Movement",
    "Progressive Democratic Bloc",
    "Justice and Liberty Front",
    "Equality and Democracy Party",
    "Social Renewal Party",
    "Reformist Unity Front",
    "Workers’ Democratic Alliance",
    "Left Unity Party",
    "Forward Justice League",
    "People’s Progressive Union",
    "Democratic Labor Bloc",
    "Alliance of Progressives",
    "Renewal and Justice Party"
));

// Center-left Parties
public static ArrayList<String> centerLeft = new ArrayList<>(Arrays.asList(
    "Democratic Party",
    "People’s Party",
    "Unity Party",
    "Progress Party",
    "Social Democratic Party",
    "New Horizons Party",
    "Future Party",
    "Forward Party",
    "Alliance for Democracy",
    "National Unity Movement",
    "Progressive Democrats",
    "Democratic Renewal Party",
    "People’s Democratic Alliance",
    "Reform Party",
    "Justice and Progress League",
    "Union for Democracy",
    "Democratic Front",
    "National Renewal Party",
    "Liberty and Justice Party",
    "Democratic Alliance",
    "Progressive Unity Front",
    "United Reformers",
    "Movement for Renewal",
    "Democracy Party",
    "National Progress League",
    "Democratic Future Party",
    "Union of Progressives",
    "Democracy and Justice Bloc",
    "Forward Democracy Party",
    "New Democratic Alliance",
    "Democratic National Movement",
    "Progressive Future Party",
    "People’s Alliance",
    "National Progress Bloc",
    "Democratic Congress",
    "Justice and Democracy Party",
    "National Renewal Front",
    "Alliance of Democrats",
    "Democratic Unity League",
    "New Progress Party",
    "Movement for Justice",
    "National Democratic Union",
    "Democratic Liberty Party",
    "Forward Democracy Front",
    "Democratic Renewal League",
    "Unity for Progress",
    "National Democratic Bloc",
    "Future and Justice Party",
    "Renewal Party"
));

// Center-right Parties
public static ArrayList<String> centerRight = new ArrayList<>(Arrays.asList(
    "National Party",
    "Republic Party",
    "Liberal Conservative Party",
    "Christian Democratic Party",
    "Unity and Freedom Party",
    "Reform and Stability Party",
    "National Progress Party",
    "Union Party",
    "Alliance for Liberty",
    "Renewal and Reform Party",
    "Stability Bloc",
    "Conservative Democrats",
    "Liberal Alliance",
    "National Unity Party",
    "Future Conservatives",
    "Responsible Reform Party",
    "Union for Stability",
    "Progress and Liberty Party",
    "National Conservative Party",
    "Moderate Reform Party",
    "Alliance of Liberals",
    "National Liberal Union",
    "United Conservative Front",
    "Democracy and Liberty Party",
    "Progressive Conservatives",
    "Stability League",
    "National Democratic Conservatives",
    "Union for Liberty",
    "Moderate Unity Party",
    "National Freedom Party",
    "Forward Conservatives",
    "Alliance for Renewal",
    "Democratic Conservatives",
    "Progress and Stability Party",
    "Renewal League",
    "Union of Liberals",
    "Conservative Progress Party",
    "National Responsibility Party",
    "Democratic Liberal Front",
    "Union of Reformers",
    "National Unity League",
    "Conservative Renewal Bloc",
    "National Moderates Party",
    "Democracy and Stability Front",
    "Union for Progress and Liberty",
    "Conservative Democrats Union",
    "Forward Liberty Party",
    "National Renewal Bloc",
    "Responsible Unity Party"
));

// Right-wing Parties
public static ArrayList<String> rightWing = new ArrayList<>(Arrays.asList(
    "Conservative Party",
    "National Freedom Front",
    "Traditionalist Party",
    "Faith and Nation Party",
    "Order and Stability Party",
    "National Reform Party",
    "Right Democratic Party",
    "Patriotic Union",
    "National Heritage Party",
    "Unity Conservatives",
    "Law and Order Party",
    "National Renewal League",
    "Freedom and Tradition Party",
    "Homeland Party",
    "National Stability Bloc",
    "Conservative Front",
    "Union of Patriots",
    "National Democratic Right",
    "Traditional Unity Party",
    "Faith and Family Party",
    "Right Progress Party",
    "National Pride League",
    "Conservative Renewal Party",
    "Alliance for Order",
    "National Liberty Party",
    "Faith and Nation Bloc",
    "Conservative Union",
    "Right Reform Party",
    "National Stability Party",
    "Conservative National League",
    "Tradition and Freedom Party",
    "Unity of the Right",
    "National Future Conservatives",
    "Order and Justice Party",
    "Patriotic Renewal Party",
    "Right Alliance",
    "Heritage and Faith Party",
    "National Order League",
    "Law and Justice Party",
    "Right Stability Party",
    "National Faith Party",
    "Homeland Renewal Bloc",
    "Conservative Heritage Party",
    "National Unity Conservatives",
    "Tradition and Stability Party",
    "Right Unity Party",
    "Alliance for Faith",
    "Nationalist Renewal Party",
    "Order Front",
    "Patriots’ Bloc"
));

// Far-right Parties
public static ArrayList<String> farRight = new ArrayList<>(Arrays.asList(
    "National Front",
    "Patriotic Renewal Front",
    "Homeland First Party",
    "United Patriots",
    "National Rebirth Party",
    "Faith and Fatherland Party",
    "Nationalist Bloc",
    "Front for Tradition",
    "National Sovereignty Party",
    "Right National Front",
    "Alliance for the Nation",
    "National Order Front",
    "Patriotic Unity Party",
    "National Destiny Party",
    "National Power League",
    "True Patriots Party",
    "Front of National Renewal",
    "National Salvation Bloc",
    "Faith and Nation Front",
    "Order and Nation Party",
    "Nationalist Renewal League",
    "Homeland Defense Party",
    "National Justice Front",
    "Patriotic Front",
    "National Integrity Party",
    "National Resurrection Front",
    "Unity of the Nation Party",
    "National Identity Party",
    "Fatherland Party",
    "National Future Front",
    "True Nation Party",
    "Sovereignty League",
    "National Homeland Bloc",
    "National Unity Front",
    "National Defense Party",
    "Faith and People Party",
    "National Reawakening Party",
    "Patriotic Heritage Front",
    "National Guardians",
    "Right Nationalist League",
    "Homeland First Front",
    "Front for National Unity",
    "National Power Party",
    "Faithful Nation Front",
    "National Order Bloc",
    "Sons of the Nation",
    "Homeland League",
    "Nationalist Salvation Party",
    "Patriotic Destiny Front",
    "National Will Party"
));
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public static List<Party> allParties = new ArrayList<>();
    public static void generateParties(){
        //allParties.add(new Party(60, "Democratic Party"));
        //allParties.add(new Party(40, "Republican Party"));
        //allParties.add(new Party(50, "Unity Party"));
        
        
        createNewParty(allGroups.get(6), (allGroups.get(6).getMin()+allGroups.get(6).getMax())/2);
        createNewParty(allGroups.get(7), (allGroups.get(7).getMin()+allGroups.get(7).getMax())/2);
        createNewParty(allGroups.get(8), (allGroups.get(8).getMin()+allGroups.get(8).getMax())/2);
        
    }
    
    public static List<Party>government = new ArrayList<>();
    public static List<Party>opposition = new ArrayList<>();
    
    public static Party rulingParty = null;
    
    public static int approvalRating = 100;
    
    
    
    public static String getRandomName() {
        String[] maleFirstNames = {
    "James", "George", "Henry", "Arthur", "Edward", "William", "Charles", "Thomas",
    "Alexander", "Alfred", "Frederick", "Richard", "Hugh", "Nicholas", "Matthew", "Peter",
    "Anthony", "Christopher", "Simon", "David", "Michael", "Francis", "Martin", "John",
    "Robert", "Andrew", "Stephen", "Patrick", "Samuel", "Joseph", "Benjamin", "Philip",
    "Jonathan", "Oliver", "Daniel", "Luke", "Mark", "Laurence", "Graham", "Nigel",
    "Colin", "Trevor", "Malcolm", "Gareth", "Clive", "Stuart", "Jeremy", "Miles",
    "Rupert", "Julian", "Dominic", "Sebastian", "Hugh", "Ian", "Douglas", "Kenneth",
    "Reginald", "Geoffrey", "Clifford", "Roland", "Harold", "Lawrence", "Gerald", "Cecil",
    "Rowan", "Adrian", "Spencer", "Lionel", "Edmund", "Alistair", "Giles", "Ewan",
    "Angus", "Hamish", "Crispin", "Neville", "Ralph", "Cedric", "Hector", "Basil",
    "Godfrey", "Maurice", "Percival", "Wilfred", "Stanley", "Archibald", "Reg", "Len"
};

String[] femaleFirstNames = {
    "Elizabeth", "Mary", "Anne", "Margaret", "Catherine", "Jane", "Victoria", "Charlotte",
    "Alexandra", "Eleanor", "Matilda", "Alice", "Beatrice", "Florence", "Edith", "Harriet",
    "Sarah", "Emma", "Olivia", "Lucy", "Isabella", "Sophia", "Amelia", "Emily",
    "Grace", "Hannah", "Abigail", "Clara", "Frances", "Rachel", "Joan", "Agnes",
    "Martha", "Ruth", "Helen", "Irene", "Diana", "Julia", "Caroline", "Georgina",
    "Henrietta", "Rosalind", "Philippa", "Victoria", "Rebecca", "Susannah", "Madeleine", "Sylvia",
    "Patricia", "Pauline", "Barbara", "Dorothy", "Janet", "Christine", "Deborah", "Pamela",
    "Jennifer", "Denise", "Jacqueline", "Sandra", "Theresa", "Louise", "Bridget", "Fiona",
    "Moira", "Sheila", "Gillian", "Hazel", "Iris", "Violet", "Phoebe", "Arabella",
    "Imogen", "Poppy", "Maisie", "Millie", "Esme", "Freya", "Evelyn", "Agatha",
    "Clarissa", "Winifred", "Constance", "Marjorie", "Ethel", "Edwina", "Cecily", "Beatrix"
};

String[] lastNames = {
    "Smith", "Jones", "Taylor", "Brown", "Williams", "Wilson", "Evans", "Thomas",
    "Roberts", "Johnson", "Lewis", "Walker", "Robinson", "Wood", "Thompson", "White",
    "Watson", "Jackson", "Wright", "Green", "Harris", "Cooper", "King", "Lee",
    "Edwards", "Clark", "Morris", "Moore", "Ward", "Turner", "Carter", "Phillips",
    "Mitchell", "Anderson", "Allen", "Scott", "Hill", "Adams", "Baker", "Nelson",
    "Hall", "Shaw", "Miller", "Hughes", "Parker", "Collins", "Reynolds", "Griffiths",
    "Morgan", "Price", "Richards", "Palmer", "Foster", "Pearce", "West", "Knight",
    "Atkinson", "Holmes", "Barrett", "Lawson", "Saunders", "Hardy", "Osborne", "Wheeler",
    "Chapman", "Fletcher", "Barnes", "Spencer", "Elliott", "Lloyd", "Rogers", "Matthews",
    "Harvey", "Nash", "Bishop", "Payne", "Howe", "Butler", "Cross", "Kent",
    "Cartwright", "Hancock", "Pritchard", "Underwood", "Hollingsworth", "Fairfax", "Mortimer", "Beaumont",
    "Chamberlain", "Montague", "Hawkins", "Radcliffe", "Barton", "Hurst", "Clarke", "Drummond"
};


        boolean male = ra.nextInt(100) < 90;

        String firstName = male
                ? maleFirstNames[ra.nextInt(maleFirstNames.length)]
                : femaleFirstNames[ra.nextInt(femaleFirstNames.length)];

        String lastName = lastNames[ra.nextInt(lastNames.length)];

        return firstName + " " + lastName;
    }

    
    
    
    
    public static void arrangeGroupsOld(){
        int min=0,max=0;
        int avg =0;
        
        int parpol = 0;
        
        int currentPoints = 15;
        
        int threshold = 2;
        
        int winNum =0;
        Party winKey = null;
        HashMap<Party,Integer> partyScore = new HashMap<>();
        
        for(Party par : allParties){
            par.supportGroups.clear();
        }
        
        for(Group gro : allGroups){
            partyScore.clear();
            min= gro.getMin();
            max = gro.getMax();
            avg = (min+max)/2;
            
            for(Party par : allParties){
                currentPoints = 0;
                parpol = par.getPolicy();
                if(parpol>=min && parpol <= max){
                    currentPoints+=100;
                }
                
                
                
                currentPoints -= Math.abs(parpol-avg)+1;
                currentPoints -= (100-par.unity)/5;
                
                
                if(par.supportGroups.contains(gro)&& par.supportGroups.size() == 1){
                    if(par.getSeats() < 10){
                        currentPoints /=2;
                    }else{
                        currentPoints+= 10000;
                    }
                }
                partyScore.put(par,currentPoints);
                
            }
            
            // Check what party resonates most with this group
            winNum = 0;                     
            winKey = null;
            for(Party par : partyScore.keySet()){
                if(partyScore.get(par)>= winNum){
                    winNum = partyScore.get(par);
                    winKey = par;
                }
            }
            
            // checks if they're a supporter of another party. if so, remove
            for(Party par : allParties){
                if(par.supportGroups.contains(gro)){
                    par.supportGroups.remove(gro);
                }
                
            }
            
            winKey.addToSupport(gro);
            
            /*for(Party par: partyScore.keySet()){
                if(partyScore.get(par) >= threshold){
                    par.addToSupport(gro);
                }
            }*/
            
        }
    }
    
    public static void arrangeGroups() {
    int min, max, avg;
    int parpol;
    int currentPoints;
    int threshold = 10; // % improvement required to switch

    for (Party par : allParties) {
        par.supportGroups.clear();
    }

    for (Group gro : allGroups) {
        Map<Party, Integer> partyScore = new HashMap<>();
        min = gro.getMin();
        max = gro.getMax();
        avg = (min + max) / 2;

        for (Party par : allParties) {
            currentPoints = 0;
            parpol = par.leader.getIdeology();
            if (parpol >= min && parpol <= max) {
                currentPoints += 100;
            } else {
                currentPoints += 50;
            }
            currentPoints /= (Math.abs(parpol - avg)) + 1;
			
			
            partyScore.put(par, currentPoints);
        }

        // Find party with max score
        int winNum = -1;
        Party winKey = null;
        for (Party par : partyScore.keySet()) {
            if (partyScore.get(par) > winNum) {
                winNum = partyScore.get(par);
                winKey = par;
            }
        }

        // Find current party (if any)
        Party currentParty = null;
        for (Party par : allParties) {
            if (par.supportGroups.contains(gro)) {
                currentParty = par;
                break;
            }
        }

        // Only switch if winKey’s score is much better than current
        boolean shouldSwitch = false;
        if (currentParty == null) {
            shouldSwitch = true; // not currently assigned
        } else {
            int currentScore = partyScore.get(currentParty);
            if (winNum > currentScore + threshold) {
                shouldSwitch = true;
            }
        }
		if(currentParty!=null){
		if(currentParty.supportGroups.size() ==1){
			shouldSwitch = false;
		}
		}
		

        // Reassign if needed
        if (shouldSwitch) {
            // Remove group from all parties
            for (Party par : allParties) {
                par.supportGroups.remove(gro);
            }
            winKey.addToSupport(gro);
        } else if (currentParty != null) {
            // Keep current assignment
            currentParty.addToSupport(gro);
        }
    }
}
    
    
    
    public static int leaderStartDate = startdate;
    
    public static void election(){
        checkMajors();
        HashMap<Party,Integer> partyScore = new HashMap<>();
        HashMap<Party,Integer> partyScoreOrig = new HashMap<>();
        HashMap<Party,Integer> partySeats = new HashMap<>();
        int points;
        int randbonus = ra.nextInt(allParties.size());
        int curparnum = 0;
        for(Party par : allParties){
            points = 0;
            for(Group gro : par.supportGroups){
                int avg = (gro.getMax()+gro.getMin())/2;
                int difference = (Math.abs(avg-par.getPolicy()))/5;
                int contrib =gro.getPoints()/(difference+1);
                
                points+= contrib;
            }
            
            /*if(government.contains(par)){
                points -= (year - startdate)/3;
                if(snapElec){
                    if(ra.nextBoolean()){
                        points -= (points*25)/100;
                    }
                }
                if(year-startdate >= 15){
                    int deductby=  (points * (year-startdate))/100;
                    points -= (points * deductby)/100;
                }
                
            }*/
            
            int inverseApp = 100 - approvalRating;
            /*if(government.contains(par)){
                points += (points*approvalRating)/100;
            }else{
                points += (points*inverseApp)/100;
            }*/
            
            if(rulingCoalition!=null){
				if(approvalRating<50 && !rulingCoalition.members.contains(par)){
					points += Math.abs((par.getPolicy() - rulingCoalition.getLeader().getPolicy()))-15;
				}
			}
				
			if(rulingCoalition!= null && rulingCoalition.members.contains(par)){
				points -= (points*unemploymentRate)/100;
			
				points -= (100-economicIndex)/4;
			}else{
				points += (points*unemploymentRate)/100;
			
				points += (100-economicIndex)/4;
			}
			
			
			
			if(par.supportGroups!= null){
				for(Group gro: par.supportGroups){
					if(gro.leader == president){points += points/4;
					}
					if(gro.leader == vicePresident){points += points/8;
					}
				}
			}
            
            
            if(snapElec&& rulingCoalition.members.contains(par)){
                points -= points/4;
            }
            
            if(par.isMajor()){
                points +=points/4;
            }
            
            if(par == rulingParty && !isFair){
                int increaseby = auth/20;
                points *=increaseby;
            }
			
			if(radicalism > 50){
				points = ((points*Math.abs(par.getPolicy() - 50))*2)/100;
			}
            
            /*for(int i=0; i<5; i++){
                if(ra.nextBoolean()){
                    points += points/4;
                }
            }*/
            //System.out.println(par.getName()+ " "+points*100);
            
			points += (points*(100-Math.abs(par.getPolicy()-overton)))/200;
			
			
            partyScore.put(par, points*100);
            curparnum++;
        }
        partyScoreOrig.putAll(partyScore);
        
        /*for(Party par : partyScore.keySet()){
            System.out.println(par.getName());
            System.out.println(partyScore.get(par));
        }*/
        
        int maxValue = 0;
        Party maxParty = null;
        
        // find party with most support
        for(Map.Entry<Party, Integer> entry : partyScore.entrySet()){
            if(entry.getValue()>maxValue){
                maxValue = entry.getValue();
        maxParty = entry.getKey();

            }
        }
        int newval = 0;
        //double one with most support to simulate FPTP representation
        
        newval = partyScore.get(maxParty)+(partyScore.get(maxParty)/4);
        partyScore.put(maxParty,newval);
        partyScoreOrig.put(maxParty,newval);
        //int newval=0;
        
        
        for(Party party : allParties){
            partySeats.put(party,0);
        }
        
        
        
        int maxScore = 0;
        Party winParty;
        
        for(int i=0; i<100; i++){
            maxScore = 0;
            winParty = null;
            for(Map.Entry<Party, Integer> entry : partyScore.entrySet()){
                if(entry.getValue()>maxScore){
                maxScore = entry.getValue();
                winParty = entry.getKey();
               }
            }
            
            /*System.out.println("Interation "+ i +"\n");
            /*for(Map.Entry<Party, Integer> entry : partyScore.entrySet()){
                System.out.println(entry.getValue()+ " "+ entry.getKey());
            }*/
            
            /*for(Map.Entry<Party, Integer> entry : partyScore.entrySet()){
                //System.out.println(entry.getValue()+ " "+ entry.getKey().getName());
                System.out.println(entry.getKey().getName()+ " ");
            }
            for(Party pp: allParties){
                //System.out.println(entry.getValue()+ " "+ entry.getKey().getName());
                System.out.println(pp.getName()+ " "+ partySeats.get(pp));
            }
            
            System.out.println(winParty.getName());
            
            if(winParty == null){
                System.out.println("WinParty is null! at iteration "+ i);
                
            }*/
            
            newval = partySeats.get(winParty) +1;
            partySeats.put(winParty,newval);
            
            
            newval = partyScoreOrig.get(winParty) / (partySeats.get(winParty)+1);
            partyScore.put(winParty,newval);
            
        }
        
        for(Party par : allParties){
            par.setSeats(partySeats.get(par));
            
        }
        
    }
    
    public static void findBiggestParty(){
        Party maxParty = null;
        int maxnum = 0;
        for(Party par : allParties){
            if(par.getSeats()> maxnum){
                maxnum = par.getSeats();
                maxParty = par;
            }
        }
        
        
        formCoalitions(maxParty);
    }
    
    public static Coalition rulingCoalition;
    public static ArrayList<Coalition> allCoalitions = new ArrayList<>();
    public static ArrayList<Coalition> coalitionsWithMajority = new ArrayList<>();
    
    public static ArrayList<Party> independentParties = new ArrayList<>();
    public static Person primeMinister = null;
	
	public static int numOfSnaps = 0;
    public static void formCoalitions(Party largePar){
        allCoalitions.clear();
        int thresh = 45 - (5*allParties.size());
        for(Party par : allParties){
            if(par.getSeats() >=thresh){
                
                allCoalitions.add(new Coalition(par));
                
            }
        }
        
        int coaPoint = 0;
        int threshold;
        int pragmatism;
        Party curCoaRuling;
        
        for(Coalition coa : allCoalitions){
            coaPoint = 0;
            curCoaRuling = coa.getLeader();
            
            pragmatism = 50-curCoaRuling.getSeats();
            
            //threshold = 30 + (int)(0.5 * curCoaRuling.getSeats());
            threshold = 50;
            if(curCoaRuling.getSeats()>51){
                threshold = 70;
            }
			
			if(snapElec){
				threshold -= numOfSnaps*3;
			}
            Scanner sc= new Scanner(System.in);
            //System.out.println(curCoaRuling.getName());
            int debugIdeologydif = 0;
            int pragmachange = 0;
            for(Party par: allParties){
                coaPoint = 0;
                if(par != curCoaRuling){
                    //coaPoint += pragmatism;
                    //coaPoint += 100 - Math.abs(par.getPolicy()-curCoaRuling.getPolicy());
                    debugIdeologydif = 100 - Math.abs(par.getPolicy()-curCoaRuling.getPolicy());
                    
                    //coaPoint += (50 -curCoaRuling.getSeats()) /2;
                    //pragmachange = (50 -curCoaRuling.getSeats()) /2;
                    
                    coaPoint += debugIdeologydif/2;
                    coaPoint+= pragmatism/2;
                    //coaPoint += pragmachange;
                    if(par == findSecondParty()){
						coaPoint -= coaPoint/4;
					}
                    
					
					if(par.getPolicy() > 85 || par.getPolicy() <15){
						threshold += threshold/4;
					}
                    
                    if(coaPoint >= threshold){
                        coa.addToMemberList(par);
                    }
                    /*System.out.println(par.getName() + ": "+ coaPoint);
                    System.out.println("Ideology dif: "+ debugIdeologydif);
                    System.out.println("pragmachange: "+ pragmachange);
                    System.out.println("pragmatism: "+ pragmatism);*/
                    
                }
            }
            //sc.nextLine();
            
        }
        
        int maxnum=-1;
        Coalition maxCoa = null;
        coalitionsWithMajority.clear();
        ArrayList<Coalition> toRemove = new ArrayList<>();
        for(Coalition coa : allCoalitions){
            if(coa.getTotalSeats() >= 35){
                coalitionsWithMajority.add(coa);
            }
            
            
        }
        
        for(Coalition coa : coalitionsWithMajority){
            if(coa.getNumOfMembers() ==0){
                toRemove.add(coa);
            }
        }
        
        coalitionsWithMajority.remove(toRemove);
        toRemove.clear();
        
        for(Coalition coa : coalitionsWithMajority){
            
				if(coa.getStability() > maxnum){
					maxCoa = coa;
					maxnum = coa.getStability();
				}else if(coa.getStability() <= maxnum&& maxCoa != null){
					if(coa.getTotalSeats() > maxCoa.getTotalSeats()){
						maxCoa = coa;
						maxnum = coa.getStability();
					}
				}
			
            
            
        }
        System.out.println("\nCoalition Negotiations:");
		for(Coalition coa : allCoalitions){
		    
		        
		        coa.displayMembers();
		        System.out.println(coa.getTotalSeats()+ "% of Parliament");
		        System.out.println("Stability level: "+ coa.stability);
		        System.out.println("====================");
		        
		    
		}
        
        
        if(maxCoa == null){
			maxCoa = new Coalition(largePar);
		}
        
        
        for(Coalition coa : allCoalitions){
            if(coa!=maxCoa && maxCoa !=null&& coa != null && coa.members !=null){
                
                coa.members.removeAll(maxCoa.members);
                toRemove.add(coa);
                
            }
        }
        
        
        allCoalitions.removeAll(toRemove);
        toRemove.clear();
        Party oldPar = null;
        if(rulingCoalition != null){
            oldPar = rulingCoalition.getLeader();
        }
        
        rulingCoalition = maxCoa;
		
		if(rulingCoalition.members.size()>1 && Math.abs(rulingCoalition.getLeader().getLeader().getIdeology()-50) < 35){	
			removeMostExtreme(rulingCoalition);
        }
        if(rulingCoalition.getLeader() != oldPar){
            if(oldPar !=null){
            addToArchive();
            }
            auth /=2;
            if(!isFair){
                isFair = true;
            }
            startdate = year;
            leaderStartDate = year;
            
            
        }
        
        approvalRating = 0;
        approvalRating =  rulingCoalition.getTotalSeats();
        
        rulingParty = rulingCoalition.getLeader();
        primeMinister = rulingCoalition.getLeader().getLeader();
        
        
        independentParties.clear();
        boolean isInd = true;
        
            for(Party par : allParties){
                isInd = true;
                for(Coalition coa : allCoalitions){
                    if(coa.hasParty(par)){
                        isInd = false;
                    }
                }
                if(isInd){
                    independentParties.add(par);
                }
            }
        
        
        
        
    }
	
	public static void removeMostExtreme(Coalition coa){
		int maxExtreme = 0;
		Party maxPar = null;
		
		for(Party par: coa.members){
			if(Math.abs(par.getPolicy()-50) > maxExtreme){
				maxExtreme = Math.abs(par.getPolicy()-50);
				maxPar = par;
			}
			
			
		}	
		if(coa.getTotalSeats()-maxPar.getSeats() > 50){
				coa.members.remove(maxPar);
			}
	}
	
	public static Party findSecondParty(){
		Party maxPar = null;
		int maxNum = 0;
		Party secondPar = null;
		int secNum = 0;
		for(Party par: allParties){
			if(par.getSeats()> maxNum){
				if(maxPar != null){
					secondPar = maxPar;
					secNum = maxNum;
					
				}
				
				maxPar = par;
				maxNum = par.getSeats();
			}else if(par.getSeats() > secNum){
				secNum = par.getSeats();
				secondPar = par;
			}
		}
		
		return secondPar;
	}
	
	public static List<Party> toleration = new ArrayList<>();
	
	public static void confidenceVote(){
		toleration.clear();
		int totalseatscoalition = 0;
		for(Party par : rulingCoalition.members){
			totalseatscoalition+= par.getSeats();
		}
		int threshold = 50;
		int partyPolicyDif = 0;
		int leaderPolicyDif = 0;
		int numofPars = allParties.size()*5;
		int points = 0;
		Party secondParty = findSecondParty();
		int secPartyPolicyDif;
		int secLeaderPolicyDif;
		int oppopoints = 0;
		if(totalseatscoalition >= 50){
			
		}else{
			for(Party par: allParties){
				oppopoints = 0;
				threshold = 50;
				if(!rulingCoalition.members.contains(par)){
					partyPolicyDif = ((100-Math.abs(par.getPolicy()- rulingParty.getPolicy()))/2);
					leaderPolicyDif = ((100-Math.abs(par.leader.getIdeology()- rulingParty.leader.getIdeology()))/4);
					points = partyPolicyDif + leaderPolicyDif+ numofPars;
					points -= Math.abs(par.getPolicy()-50)/2;
					if(!rulingCoalition.members.contains(secondParty)){
						secPartyPolicyDif = ((100-Math.abs(par.getPolicy()- secondParty.getPolicy()))/2);
						secLeaderPolicyDif = ((100-Math.abs(par.leader.getIdeology()- rulingParty.leader.getIdeology()))/4);
						oppopoints = (secPartyPolicyDif+ secLeaderPolicyDif + numofPars);
						
					}
					
					threshold+=oppopoints/10;
					int mostRadical = 0;
					for(Party mempar: rulingCoalition.members){
						if(Math.abs(mempar.getPolicy()-50) > mostRadical){
							mostRadical = Math.abs(mempar.getPolicy()-50);
						}
					}
					int modLevel = ((50-Math.abs(par.getPolicy()-50))/10)+1;
					//threshold -= numOfSnaps*modLevel;
					threshold -= numOfSnaps*3;
									
					threshold += (Math.abs(par.getPolicy()-50)/2) + (Math.abs(par.getPolicy()-mostRadical));
					
					if(rulingCoalition.containsRadicals() && (par.getPolicy() < 75 || par.getPolicy() > 25)){
						threshold += threshold/2;
					}
					
						if(points >= threshold){
							toleration.add(par);
						}
					
				}
			}
		}
		
		int votesYes = 0;
		for(Party par: allParties){
			if(rulingCoalition.members.contains(par) || toleration.contains(par)){
				votesYes+= par.getSeats();
			}
		}
		
		if(votesYes < 50){
			System.out.println("Confidence vote failed. Elections in 2 months");
			snapElec = true;
			elecCount = 2;
			numOfSnaps++;
			if(presCdown < 6){
				//presCdown+=2;
			}
			//changeRad(5);
		}else{
			numOfSnaps = 0;
		}
	}
    
    
    
    /*public static void formCoalitions(Party maxParty, int maxnum){
        government.clear();
        opposition.clear();
        
        government.add(maxParty);
        int minusby= 0;
        
        if(maxParty.getSeats()> 40){
            minusby = 60;
        }else if(maxParty.getSeats() <=40 && maxParty.getSeats()>25){
            minusby = 75;
        }else{
            minusby = 80;
        }
        
        int threshold =minusby-maxParty.getSeats();
        HashMap<Party, Integer> potentialCoalitionPartners = new HashMap<>();
        if(maxParty.getSeats() <50){
            for(Party par: allParties){
                if(par!=maxParty){
                    int difference = Math.abs(par.getPolicy()-maxParty.getPolicy());
                    if(difference < threshold){
                        potentialCoalitionPartners.put(par, difference);
                    } else {
                        opposition.add(par);
                    }
                }
            }
            
            int totSeats = maxParty.getSeats();
            List<Map.Entry<Party, Integer>> sortedPartners = new ArrayList<>(potentialCoalitionPartners.entrySet());
sortedPartners.sort(Comparator.comparingInt(Map.Entry::getValue));

for (Map.Entry<Party, Integer> entry : sortedPartners) {
    if (totSeats >= 50) break;

    Party partner = entry.getKey();
    government.add(partner);
    totSeats += partner.getSeats();
}

// All remaining parties go to the opposition
for (Map.Entry<Party, Integer> entry : sortedPartners) {
    Party p = entry.getKey();
    if (!government.contains(p)) {
        opposition.add(p);
    }
}
            
            
            
            
        
            int govseats = 0;
            for(Party par : government){
                govseats += par.getSeats();
            }
            
            
            if(govseats < 40){
                maxnum = 0;
                Party oldMax = maxParty;
                maxParty = null;
                for(Party par : allParties){
                    if(par.getSeats()> maxnum && par!= oldMax){
                        maxnum = par.getSeats();
                        maxParty = par;
                    }
                }
                
            }
            
            
            maxnum = 0;
            maxParty = null;
            
            for(Party par : government){
                if(par.getSeats()>maxnum){
                    maxnum = par.getSeats();
                    maxParty = par;
                }
            }
        }else{
            for(Party par : allParties){
                if(par != maxParty){
                    opposition.add(par);
                }
            }
        }
        
        if(rulingParty != maxParty){
            if(rulingParty !=null){
            addToArchive();
            }
            auth /=2;
            if(!isFair){
                isFair = true;
            }
            startdate = year;
            leaderStartDate = year;
            
            approvalRating +=  maxParty.getSeats()/2;
        }
        rulingParty = maxParty;
        
    }*/
    
    public static void checkAlienationOld(){
        int min,max,avg;
        int parpol = 0;
        int threshold = 0;
        int resonancePoints = 0;
        for(Group gro : allGroups){
            avg = gro.getIdeology();
            min = gro.getMin();
            max = gro.getMax();
            threshold = (avg > 20 && avg < 80)? 20:5;
            
            
            resonancePoints = 0;
            for(Party par : allParties){
                if(par.supportGroups.contains(gro)){
                parpol = par.getPolicy();
                
                if(parpol>=min && parpol<= max){
                    resonancePoints++;
                }
                
                if(Math.abs(parpol-avg)<threshold){
                resonancePoints++;
                }
                
                if(par.supportGroups.size() == 1){
                    resonancePoints++;
                }
                
                if(par.getUnity() < ra.nextInt(75)){
                    resonancePoints--;
                }
                
                int totGroupPoints = 0;
                for(Group gp : par.supportGroups){
                    totGroupPoints+= gp.getPoints();
                }
                
                
                if(gro.getPoints() < totGroupPoints/2){
                    resonancePoints++;
                }
                
                
                }
                
                
                
                
            }
            
            
            
            //totGroupPoints = totGroupPoints/(allGroups.size()+1);
            //totGroupPoints /=2;
            
            
            
            boolean isAlienated = resonancePoints<=0;
            
            if(gro.getAlienated()){
                createNewParty(gro, avg);
            }else{
                gro.changeAlienation(isAlienated);
            }
            
            /*if(resonancePoints == 0){
                createNewParty(gro, avg);
            }*/
            
        }
        
        
        
        
    }
    
    
    
    
    public static void checkAlienation() {
        int resonance = 0;
        int threshold = 75;
		
        List<Group> alienatedGroups = new ArrayList<>();
    for(Party par: allParties){
        for(Group gro : par.supportGroups){
            resonance = (100-Math.abs(gro.getIdeology()-par.getPolicy()))/4;
            resonance += (100-(par.supportGroups.size()*8))/4;
			resonance+= (100-Math.abs(gro.leader.getIdeology()-par.leader.getIdeology()))/2;
            resonance += par.getUnity()/10;
			
            boolean isAlienated = resonance<threshold;
            
            if(gro.getAlienated()){
                alienatedGroups.add(gro);
                
            }else{
                gro.changeAlienation(isAlienated);
            }
            
        }
    }
    
    for(Group gro: alienatedGroups){
        createNewParty(gro, gro.getLeader().getIdeology());
    }
}
    
    
    
    public static void createNewParty(Group gro, int avg){
        gro.changeAlienation(false);
        for(Party par : allParties){
                if(par.supportGroups.contains(gro)){
                    par.supportGroups.remove(gro);
                }
        }
        boolean found= true;
        int loops = 0;
        do{
            loops++;
            found = true;
            String newPartyName = gro.getRandomPartyName();
            for(Party par: allParties){
                if(par.getName().matches(newPartyName)){
                    found = false;
                }
            }
            if(loops > 10){
                found = true;
            }
        }while(!found);
        Party newParty = new Party(avg, gro.getRandomPartyName());
        
        newParty.leader = gro.getLeader();
        allParties.add(newParty);
        newParty.addToSupport(gro);
        
    }
    
	public static int detParNameIdeology(Party par){
		for(String name: farLeft){
			if(par.getName().equalsIgnoreCase(name)){
				return 1; // far left name
			}
		}
		
		for(String name: leftWing){
			if(par.getName().equalsIgnoreCase(name)){
				return 2; // far left name
			}
		}
		
		for(String name: centerLeft){
			if(par.getName().equalsIgnoreCase(name)){
				return 3; // far left name
			}
		}
		
		for(String name: centerRight){
			if(par.getName().equalsIgnoreCase(name)){
				return 4; // far left name
			}
		}
		
		for(String name: rightWing){
			if(par.getName().equalsIgnoreCase(name)){
				return 5; // far left name
			}
		}
		for(String name: farRight){
			if(par.getName().equalsIgnoreCase(name)){
				return 6; // far left name
			}
		}
		return 0;
	}
	
	public static int detActualPartyIdeo(Party par){
		int toreturn = 0;
            if(par.getPolicy()>50){
                if(par.getPolicy()>85){
                    toreturn= 1;
                }else if(par.getPolicy() <=85 && par.getPolicy()> 70){
                    toreturn= 2;
                }else{
                    toreturn= 3;
                }
            }else{
                if(par.getPolicy()>30){
                    toreturn= 4;
                }else if(par.getPolicy()<=30 && par.getPolicy()>15){
                    toreturn =5;
                }else{
                    toreturn =6;
                }
            }
            return toreturn;
            
	}
    
    public static void partyShifts(){
        int maxnum = 0;
        Group maxGroup = null;
        int avg =0;
        for(Party par: allParties){
            maxnum = -100;
            maxGroup = null;
            
            
            for(Group gro: par.supportGroups){
                if(gro.getPoints()> maxnum){
                    maxnum = gro.getPoints();
                    maxGroup = gro;
                }
            }
            
            if(maxGroup != null){
            //avg = (maxGroup.getMin()+maxGroup.getMax())/2;
            avg = maxGroup.getLeader().getIdeology();
            /*if(par.getPolicy()> avg){
                par.changePolicy((par.getPolicy()-avg)/10);
            }else{
                par.changePolicy((avg-par.getPolicy())/10);
            }*/
			int dif = par.getPolicy()- avg;
			
			if(dif>0){
				par.changePolicy(-1);
			}else if(dif < 0){
				par.changePolicy(1);
			}
			
            }
			
			if(Math.abs(detParNameIdeology(par)-detActualPartyIdeo(par))>1){
				switch(detActualPartyIdeo(par)){
					case 1: par.name = farLeft.get(ra.nextInt(farLeft.size()));
					break;
					case 2:par.name = leftWing.get(ra.nextInt(leftWing.size()));
					break;
					case 3:par.name = centerLeft.get(ra.nextInt(centerLeft.size()));
					break;
					case 4:par.name = centerRight.get(ra.nextInt(centerRight.size()));
					break;
					case 5:par.name = rightWing.get(ra.nextInt(rightWing.size()));
					break;
					case 6:par.name = farRight.get(ra.nextInt(farRight.size()));
					break;
				}
				//par.name = maxGroup.getRandomPartyName();
			}
			
        }
        
        
    }
    
    public static void demographicShifts(){
        int changeBy = 0;
        int nupoints = changeBy/allGroups.size();
        
        for(Group gro : allGroups){
			changeBy = ra.nextInt((gro.getPoints()/5)+1);
            if(ra.nextBoolean()){
                if(ra.nextBoolean()){
                    changeBy*=-1;
                    
                }
                gro.changePoints(changeBy);
                
                
                /*for(Group gor : allGroups){
                    if(gor != gro){form
                        gor.changePoints(nupoints);
                    }
                }*/
                
            }
        }
    }
    
    
    public static void checkSeats(){
        int threshold = 5;
		if(!isFair){
			threshold = -1;
		}
        int lostseats = 0;
        List<Party> toRemove = new ArrayList<>();
        for(Party par : allParties){
            if(par.getSeats()<= threshold){
                lostseats += par.getSeats();
                par.setSeats(0);
				par.addFailCount();
				if(par.getFailCount() > (ra.nextInt(20)+1)){
					toRemove.add(par);
				}
            }else{
				par.resetFail();
			}
        }
        
        allParties.removeAll(toRemove);
        toRemove.clear();
        if(lostseats >0){
            redistrib(lostseats);
        }
    }
    
    public static void redistrib(int toredis){
        int maxval=0;
        Party maxPar = null;
        for(int i=0; i< toredis;i++){
            maxval = 0;
                maxPar = null;
            for(Party par: allParties){
                
                if(((par.getSeats()*100)/(par.getSeats()+1))> maxval){
                    maxval = ((par.getSeats()*100)/(par.getSeats()+1));
                    maxPar = par;
                }
            }
            
            maxPar.setSeats(maxPar.getSeats()+1);
        }
        
        
    }
	
	public static void redistribInverse(int toredis){
        int maxval=10000;
        Party maxPar = null;
        for(int i=0; i< toredis;i++){
            maxval = 10000;
                maxPar = null;
            for(Party par: allParties){
                
                if(((par.getSeats()*100)/(par.getSeats()+1))< maxval){
                    maxval = ((par.getSeats()*100)/(par.getSeats()+1));
                    maxPar = par;
                }
            }
            
            maxPar.setSeats(maxPar.getSeats()+1);
        }
        
        
    }
	
	public static void showResults(){
		for(Party par : allParties){
                System.out.println(par.getName()+ ": "+ par.getSeats()+"%");
        }
	}
    
    public static void checkNoGroups(){
        List<Party> toRemove = new ArrayList<>();
		int lostseats = 0;
        for(Party par : allParties){
            if(par.supportGroups.size() == 0 && par.getSeats()==0){
                toRemove.add(par);
				if(par.getSeats()> 0){
					lostseats += par.getSeats();
				}
            }
			
			
        }
        
        
        if(rulingParty !=null){
            if(toRemove.contains(rulingParty)){
                //System.out.println("SCENARIO 2");
                //triggerElection();
                snapElec = true;
                if(elecCount> 3){
                elecCount = 3;
                }
                toRemove.remove(rulingParty);
            }
        }
        redistribInverse(lostseats);
        allParties.removeAll(toRemove);
        toRemove.clear();
        
        
    }
    
    public static int getGovNumSup(){
        if(rulingCoalition!=null){
            return rulingCoalition.getTotalSeats();
        }else{
            return 100;
        }
        
    }
    
	
    public static void triggerElection(){
        election();          // (4) Calculates votes and assigns seats based on group support
        checkSeats();        // (5) Removes parties that did poorly in the election
        findBiggestParty();    // (6) Uses final seat counts to build government and opposition
        elecCount = 5*12;
        sortParties();
        confidenceVote();
        electSpeaker();
        
    }
    
    public static boolean changedLead = false;
    
    public static Person oldPerson = null;
    
    public static int leadercDown = 0;
    
    
    public static void partyLeaderCheck(){
        for(Party par: allParties){
            oldPerson = null;
            changedLead = false;
            par.determineLeader();
            if(rulingParty!= null){
            if(changedLead && rulingParty== par && oldPerson != null){
                primeMinister = par.leader;
                previousRulingParties.add(new archiveParty(rulingParty.getName(),leaderStartDate,year, oldPerson.getName()));
                leaderStartDate=  year;
            }
            }
            
            if(changedLead){
                auth /=2;
            }
        }
    }
    
    public static void updateApproval(){
        approvalRating -= ra.nextInt(((year-startdate)/5)+1);
        
        //approvalRating -= auth/10;
         // Economy impact
        approvalRating += (economicIndex - 50) / 4; // boost/penalty for good/bad economy
        approvalRating -= unemploymentRate / 4; // penalty for high unemployment

        approvalRating += ra.nextInt(2);

        if (approvalRating < 1) approvalRating = 1;
        if (approvalRating > 99) approvalRating = 99;
        
        
        /*if(getGovNumSup()<50){
            approvalRating -= 50 - getGovNumSup();
        }*/
        
        approvalRating += ra.nextInt(2);
        
        if(approvalRating <1){
            approvalRating = 1;
        }
        
        if(approvalRating > 99){
            approvalRating = 99;
        }
    }
    
    public static void monthly(){
		updateEconomy();
		overtonShift();
        
        updateApproval();
        leadercDown --;
        if(leadercDown <0){
        for(Group gro : allGroups){
            oldPerson = null;
            changedLead = false;
            gro.checkLeaderAge();
            if(rulingParty != null){
            if(changedLead && rulingParty.getLeader() == gro.getLeader() && oldPerson != null  ){
                previousRulingParties.add(new archiveParty(rulingParty.getName(),leaderStartDate,year, oldPerson.getName()));
                leaderStartDate=  year;
            }
            }
        }
        
        partyLeaderCheck();
        leadercDown = 12;
        }
        
        for(Party par: allParties){
            par.unityUpdate();
        }
        
        moNum++;
        if(moNum == 12){
            moNum = 0;
            year++;
            
            for(Group gro : allGroups){
            gro.getLeader().ageUp();
        }
            
        
            
            if(rulingParty != null){
		
        checkIsFair();
        delayElec();
		
        }
            
        }
		/*checkParamilitaries();
			updateParamilitaries(); 
			
		if(rulingParty!=null){
			updateRad(); 
			updateAuth();
			checkOverthrow();
		}*/
		
		
		// At the end of updateRad() or monthly()
if (radicalism > 50) {
    int policeIncrease = (radicalism - 50) / 2; // More radicalism, more police
    addStrength(Police, policeIncrease);
}
if (auth > 75 && Police.strength < 2000) { // Cap police
    addStrength(Police, (auth - 70));
}

if(policeControl == 100){
	addStrength(Police, ((Police.strength-2000)/10)*-1);
}
        
        presCdown--;
        if(presCdown == 0){
            electPresident();
            presCdown = defpresCdown;
        }
        
        
        checkNoGroups();
        boolean rpartyhasnogroups = false;
        elecCount--;
        if(rulingParty!=null){
        Scanner sc = new Scanner(System.in);
        //System.out.println(rulingParty.supportGroups.size());
        
        rpartyhasnogroups = rulingParty.supportGroups.size() == 0;
        }
        
        cooldown--;
        int cdownnum = 24;
        
        boolean hasTrig = false;
        if(elecCount==0){
            //System.out.println("SCENARIO 1");
        triggerElection();
        showResults();
            hasTrig = true;
            cooldown = cdownnum;
            snapElec = false;
			
        }
        
        
        
        
        
        /*int govSeats=0;
        if(rulingParty!=null && !hasTrig && !snapElec){
            govSeats = rulingCoalition.getTotalSeats();
            if(rulingParty.getSeats() < govSeats/2){
                
                if(ra.nextInt(govSeats) > rulingParty.getSeats()+ (rulingParty.getSeats()/2) && cooldown <=0){
                    System.out.println("SCENARIO 1");
                    snapElec = true;
                    if(elecCount > 3){
                        elecCount  =3;
                    }
                }
            }
        }
        
        if(!hasTrig && govSeats < 50 && !snapElec){
            
            if(ra.nextInt((100-govSeats)+1) > govSeats+ (govSeats/2) && cooldown <=0){
                System.out.println("SCENARIO 2");
                snapElec = true;
                if(elecCount >3){
                    elecCount = 3;
                }
            }
        }*/
        
        if(!hasTrig && !snapElec&& rulingParty!=null && rulingCoalition != null){
            if(ra.nextInt(80)> rulingCoalition.stability+ (rulingCoalition.stability/2)){
                //formCoalitions(rulingParty);
				confidenceVote();
				
            }
        }
        
    }
	
    
    
    public static void addToArchive(){
        previousRulingParties.add(new archiveParty(rulingParty.getName(),leaderStartDate,year, rulingParty.getLeader().getName()));
    }
    
    public static void displayArchive(){
        for(archiveParty par : previousRulingParties){
            par.display();
        }
    }
    
    public static void checkMajors(){
        for (Party par: allParties){
            par.setIsMajor(par.getSeats()> 20);
        }
    }
    
    public static void updateAuth(){
		int change = Math.abs(rulingParty.getPolicy() -50)/10;
        change -= 10- (year-startdate);
        if(year- leaderStartDate < 5){
            change -= 1;
        }
        
   if(rulingParty.getPolicy() < 75 && rulingParty.getPolicy() > 25){
	   change -= 20;
   }
		
		if(president == primeMinister){
			change +=5;
		}
		
		if(radicalism>50){
			change += radicalism/10;
		} else{
			change -= (100-radicalism)/30;
		}
		
		
		change += rulingParty.getSeats()/20;
		
		if (radicalism > 50) {
    // Radicalism justifies crackdowns
    change+= (radicalism - 50) / 5; // e.g., at radicalism=80, extraAuth=6
    
}
        
        changeAuth(change);
    }
	
	public static void changeAuth(int change){
		auth += change;
		if(auth <0){
            auth =0;
        }
        if(auth > 100){
            auth = 100;
        }
	}
    
    public static void checkIsFair(){
        if(isFair){
            if(ra.nextInt(35)+50 < auth){
                isFair = false;
            }
        } else{
            if(ra.nextInt(35)> auth){
                isFair = true;
            }
        }
    }
    
    public static void delayElec(){
        if(elecCount <= 6 && ra.nextInt(50)+50< auth && rulingParty.getSeats() > 80){
            int ranfac = ra.nextInt(3)+1;
            elecCount += 12*ranfac;
        }
    }
    
    private static final Set<String> STOP_WORDS = new HashSet<>(Arrays.asList(
        "of", "the", "and", "in", "for", "to", "on", "at", "by", "with", "a", "an"
    ));


    public static String toAcronym(String phrase) {
        StringBuilder acronym = new StringBuilder();
        for (String word : phrase.split("\\s+")) {
            if (!word.isEmpty() && !STOP_WORDS.contains(word.toLowerCase())) {
                acronym.append(Character.toUpperCase(word.charAt(0)));
            }
        }
        return acronym.toString();
    }
    
    public static void sortParties(){
        allParties.sort(Comparator.comparing (Party -> Party.getPolicy()));
    }
    
    
    public static Person president;
    public static int presCdown = 10;
    public static int defpresCdown =72;
    
	public static List<presidentArchived> presArchive = new ArrayList<>();
	
	public static class presidentArchived{
		int startyear;
		int endyear;
		String party;
		String name;
		
		public presidentArchived(int startyear, int endyear, String party, String name){
			this.startyear = startyear;
			this.endyear = endyear;
			this.party = party;
			this.name = name;
		}
		
		@Override
		public String toString(){
			return name +" | "+ party+ " ("+ startyear+ " - "+ endyear+ ") ";
		}
	}
	
    public static void electPresidentOld(){
	Person oldPres= null;
	if(president != null){
		oldPres = president;
	}
    int rounds = 1;
    List<Person> candidates = new ArrayList<>();
    for (Group par : allGroups) {
        if (par.getLeader() != null) {
            System.out.println("Leader of "+ par.getName());
            
            candidates.add(par.getLeader());
        }
    }

    Person winner = null;
    boolean hasGotMajority = false;

    while (!hasGotMajority && candidates.size() > 0) {
        Map<Person, Integer> voteCount = new HashMap<>();  // <--- RESET VOTES EACH ROUND
        for (Person candidate : candidates) {
            voteCount.put(candidate, 0);
        }
        
        // Voting
        for (Party votingParty : allParties) {
            Person bestCandidate = null;
            int minDiff = Integer.MAX_VALUE;
            List<Person> tiedCandidates = new ArrayList<>();
            for (Person candidate : candidates) {
                int diff = Math.abs(votingParty.getPolicy() - candidate.getIdeology());
                if (diff < minDiff) {
                    minDiff = diff;
                    tiedCandidates.clear();
                    tiedCandidates.add(candidate);
                } else if (diff == minDiff) {
                    tiedCandidates.add(candidate);
                }
            }
            // If there's a tie, choose randomly among best candidates
            if (tiedCandidates.size()>1) {
                bestCandidate = tiedCandidates.get(ra.nextInt(tiedCandidates.size()));
            } else{
                bestCandidate = tiedCandidates.get(0);
            }
            // Party votes for candidate with closest ideology, weighted by seats
            voteCount.put(bestCandidate,  voteCount.getOrDefault(bestCandidate, 0) + votingParty.getSeats());
        }

        // Find winner
        winner = null;
        int maxVotes = 0;
        for (Map.Entry<Person, Integer> entry : voteCount.entrySet()) {
            if (entry.getValue() > maxVotes) {
                maxVotes = entry.getValue();
                winner = entry.getKey();
            }
        }

        /*System.out.println("Round " + rounds);
        for (Person pe : voteCount.keySet()) {
            //System.out.println(pe.getName() + ": " + voteCount.get(pe) + " votes");
            System.out.println(
            pe.getName() +
            " (Ideology: " + pe.disIdeo() + "): " +
            voteCount.get(pe) + " votes"
            );
        }*/
        
        System.out.println("Round " + rounds);
        for(Person pe : candidates){
            
            System.out.println(
            pe.getName() +
            " (Ideology: " + pe.disIdeo() + "): " +
            voteCount.get(pe) + "%"
            );
        }

        int totalVotes = 0;
        for (int v : voteCount.values()) totalVotes += v;
        
        
        if (maxVotes > totalVotes / 2) { // Use majority of total votes, not just >50
            hasGotMajority = true;
        } else {
            rounds++;
            int minVotes = Collections.min(voteCount.values());
            List<Person> lowestCandidates = new ArrayList<>();
            List<Person> zeroVotes = new ArrayList<>();
            for (Map.Entry<Person, Integer> entry : voteCount.entrySet()) {
                if (entry.getValue() == minVotes) {
                    lowestCandidates.add(entry.getKey());
                }
                
                if(entry.getValue() == 0){
                    zeroVotes.add(entry.getKey());
                }
            }
            // If more than one has the lowest, randomly pick one to eliminate
            Person toRemove = lowestCandidates.get(ra.nextInt(lowestCandidates.size()));
            candidates.remove(toRemove);
            candidates.removeAll(zeroVotes);
        }
    }
    president = winner;
	if(oldPres != null){
		if(president != oldPres){
			addToPresArchive(oldPres);
			presStartYear = year;
		}
		
		
	}else{
		
		presStartYear = year;
	}
	
	appointVP();
}


public static Person speaker = null;
public static void electSpeaker(){
	Person oldPres= null;
	if(speaker != null){
		oldPres = speaker;
	}
    int rounds = 1;
    List<Person> candidates = new ArrayList<>();
    for (Group par : allGroups) {
        if (par.getLeader() != null && par.getLeader()!= president & par.getLeader() !=primeMinister && par.getLeader()!= vicePresident ) {
            //System.out.println("Leader of "+ par.getName());
            
            candidates.add(par.getLeader());
        }
    }

    Person winner = null;
    boolean hasGotMajority = false;

    while (!hasGotMajority && candidates.size() > 0) {
        Map<Person, Integer> voteCount = new HashMap<>();  // <--- RESET VOTES EACH ROUND
        for (Person candidate : candidates) {
            voteCount.put(candidate, 0);
        }
        
        // Voting
        for (Party votingParty : allParties) {
            Person bestCandidate = null;
            int minDiff = Integer.MAX_VALUE;
            List<Person> tiedCandidates = new ArrayList<>();
            for (Person candidate : candidates) {
                int diff = Math.abs(votingParty.getPolicy() - candidate.getIdeology());
                if (diff < minDiff) {
                    minDiff = diff;
                    tiedCandidates.clear();
                    tiedCandidates.add(candidate);
                } else if (diff == minDiff) {
                    tiedCandidates.add(candidate);
                }
            }
            // If there's a tie, choose randomly among best candidates
            if (tiedCandidates.size()>1) {
                bestCandidate = tiedCandidates.get(ra.nextInt(tiedCandidates.size()));
            } else{
                bestCandidate = tiedCandidates.get(0);
            }
            // Party votes for candidate with closest ideology, weighted by seats
            voteCount.put(bestCandidate,  voteCount.getOrDefault(bestCandidate, 0) + votingParty.getSeats());
        }

        // Find winner
        winner = null;
        int maxVotes = 0;
        for (Map.Entry<Person, Integer> entry : voteCount.entrySet()) {
            if (entry.getValue() > maxVotes) {
                maxVotes = entry.getValue();
                winner = entry.getKey();
            }
        }

        /*System.out.println("Round " + rounds);
        for (Person pe : voteCount.keySet()) {
            //System.out.println(pe.getName() + ": " + voteCount.get(pe) + " votes");
            System.out.println(
            pe.getName() +
            " (Ideology: " + pe.disIdeo() + "): " +
            voteCount.get(pe) + " votes"
            );
        }*/
        
        System.out.println("Round " + rounds);
        for(Person pe : candidates){
            
            System.out.println(
            pe.getName() +
            " (Ideology: " + pe.disIdeo() + "): " +
            voteCount.get(pe) + "%"
            );
        }

        int totalVotes = 0;
        for (int v : voteCount.values()) totalVotes += v;
        
        
        if (maxVotes > totalVotes / 2) { // Use majority of total votes, not just >50
            hasGotMajority = true;
        } else {
            rounds++;
            int minVotes = Collections.min(voteCount.values());
            List<Person> lowestCandidates = new ArrayList<>();
            List<Person> zeroVotes = new ArrayList<>();
            for (Map.Entry<Person, Integer> entry : voteCount.entrySet()) {
                if (entry.getValue() == minVotes) {
                    lowestCandidates.add(entry.getKey());
                }
                
                if(entry.getValue() == 0){
                    zeroVotes.add(entry.getKey());
                }
            }
            // If more than one has the lowest, randomly pick one to eliminate
            Person toRemove = lowestCandidates.get(ra.nextInt(lowestCandidates.size()));
            candidates.remove(toRemove);
            candidates.removeAll(zeroVotes);
        }
    }
    speaker = winner;
	
}


public static void electPresident(){
	Person oldPres= null;
	if(president != null){
		oldPres = president;
	}
    int rounds = 1;
    List<Person> candidates = new ArrayList<>();
    for (Party par : allParties) {
		for(Group gro: par.supportGroups){
			if (gro.getLeader() != null && gro.getLeader()!=primeMinister&& gro.getLeader()!=speaker && ra.nextBoolean()) {
				//System.out.println("Leader of "+ gro.getName());
				
				candidates.add(gro.getLeader());
			}
		}
    }

    Person winner = null;
    boolean hasGotMajority = false;

    while (!hasGotMajority && candidates.size() > 0) {
        Map<Person, Integer> voteCount = new HashMap<>();  // <--- RESET VOTES EACH ROUND
        for (Person candidate : candidates) {
            voteCount.put(candidate, 0);
        }
        
        // Voting
        for (Group votingParty : allGroups) {
            Person bestCandidate = null;
            int minDiff = Integer.MAX_VALUE;
            List<Person> tiedCandidates = new ArrayList<>();
            for (Person candidate : candidates) {
                int diff = Math.abs(votingParty.getIdeology() - candidate.getIdeology());
                if (diff < minDiff) {
                    minDiff = diff;
                    tiedCandidates.clear();
                    tiedCandidates.add(candidate);
                } else if (diff == minDiff) {
                    tiedCandidates.add(candidate);
                }
            }
            // If there's a tie, choose randomly among best candidates
            if (tiedCandidates.size()>1) {
                bestCandidate = tiedCandidates.get(ra.nextInt(tiedCandidates.size()));
            } else{
                bestCandidate = tiedCandidates.get(0);
            }
            // Party votes for candidate with closest ideology, weighted by seats
            voteCount.put(bestCandidate,  voteCount.getOrDefault(bestCandidate, 0) + votingParty.getPoints());
        }

        // Find winner
        winner = null;
        int maxVotes = 0;
        for (Map.Entry<Person, Integer> entry : voteCount.entrySet()) {
            if (entry.getValue() > maxVotes) {
                maxVotes = entry.getValue();
                winner = entry.getKey();
            }
        }

        /*System.out.println("Round " + rounds);
        for (Person pe : voteCount.keySet()) {
            //System.out.println(pe.getName() + ": " + voteCount.get(pe) + " votes");
            System.out.println(
            pe.getName() +
            " (Ideology: " + pe.disIdeo() + "): " +
            voteCount.get(pe) + " votes"
            );
        }*/
        int totalVotes = 0;
for (int v : voteCount.values()) totalVotes += v;
        System.out.println("Round " + rounds);
        for(Person pe : candidates){
            if(voteCount.get(pe)>0){
            int votes = voteCount.get(pe);
    double percent = (votes * 100.0) / totalVotes;  // Convert to percentage
    System.out.printf("%s (Ideology: %s): %.2f%%\n", 
                      pe.getName(), 
                      pe.disIdeo(), 
                      percent);
			}
        }

        totalVotes = 0;
        for (int v : voteCount.values()) totalVotes += v;
        
        
        if (maxVotes > totalVotes / 2) { // Use majority of total votes, not just >50
            hasGotMajority = true;
        } else {
            rounds++;

    // If this is the first round, narrow down to the top 2 candidates
    if (rounds == 2) {
        // Sort candidates by their votes (descending order)
        List<Map.Entry<Person, Integer>> sortedVotes = new ArrayList<>(voteCount.entrySet());
        sortedVotes.sort((a, b) -> b.getValue() - a.getValue());

        // Keep only the top two
        candidates.clear();
        for (int i = 0; i < Math.min(2, sortedVotes.size()); i++) {
            candidates.add(sortedVotes.get(i).getKey());
        }

        System.out.println("\nProceeding to second round with:");
        for (Person p : candidates) {
            System.out.println("- " + p.getName());
        }

    } else {
        // From second round onwards, normal elimination (in case no majority again)
        int minVotes = Collections.min(voteCount.values());
        List<Person> lowestCandidates = new ArrayList<>();
        for (Map.Entry<Person, Integer> entry : voteCount.entrySet()) {
            if (entry.getValue() == minVotes) {
                lowestCandidates.add(entry.getKey());
            }
        }

        Person toRemove = lowestCandidates.get(ra.nextInt(lowestCandidates.size()));
        candidates.remove(toRemove);
    }
        }
    }
    president = winner;
	if(oldPres != null){
		if(president != oldPres){
			addToPresArchive(oldPres);
			presStartYear = year;
		}
		
		
	}else{
		
		presStartYear = year;
	}
	
	appointVP();
}

public static int presStartYear = year;


public static void addToPresArchive(Person oldPres){
	String name = oldPres.getName();
	String parname = "";
	for(Party par: allParties){
		
		for(Group gro: par.supportGroups){
			
			if(gro.leader == oldPres){
				parname = par.getName();
			}
			
			
		}
		
	}
	
	presArchive.add(new presidentArchived(presStartYear, year, parname, name ));
}

public static Person vicePresident;

public static void appointVP(){
	int maxnum = 0;
	Group maxGroup = null;
	int factor = 0;
	if(president!=null){
	for(Group gro : allGroups){
			if(gro.leader != president && gro.leader !=primeMinister && gro.leader!=speaker){
			factor = 100-Math.abs(president.getIdeology()-gro.leader.getIdeology());
			if(factor > maxnum){
				maxnum = factor;
				maxGroup = gro;
			}
		}
	}
	
	vicePresident = maxGroup.leader;
	}
}

public static boolean boombums = true; // boom = true, bust = false
public static int bbcDown = 10; // boombust countdown
public static int ecoHealth = 75;

public static void updateEcoHealth(){
	ecoHealth = economicIndex;
}

// --- ECONOMIC SYSTEM METHOD ---
    public static void updateEconomy() {
        // Random fluctuation, affected by policy centerness
        int economicChange = ra.nextInt(7) - 3; // -3 to +3
		if(boombums){
			economicChange += ra.nextInt(bbcDown+1)/2;
		}else{
			economicChange -= ra.nextInt(bbcDown+1)/2;
		}
		bbcDown--;
		
		if(economicIndex >85 && boombums){
			bbcDown/=2;
		}
		
		if(economicIndex< 15 && !boombums){
			bbcDown /=2;
		}
		
		economicChange -= (economicIndex-ecoHealth)/20;
		
		if(bbcDown<1){
			bbcDown = ((ra.nextInt(5)+1)*3)+3;
			boombums = !boombums;
			updateEcoHealth();
			if(ra.nextInt(100)<5){
				System.out.println("Economic Crash!");
				bbcDown = (ra.nextInt(10)+4)*3;
				boombums = false;
			}
		}
		
		
        if (rulingParty != null) {
            int centerness = 50 - Math.abs(rulingParty.getPolicy() - 50);
            economicChange += centerness / 20; // centrist parties stabilize economy
        }
        economicIndex += economicChange;
        if (economicIndex > 100) economicIndex = 100;
        if (economicIndex < 0) economicIndex = 0;
        // Unemployment loosely tracks economy + random fluctuation
        unemploymentRate = 20 - (economicIndex / 5) + ra.nextInt(3) - 1;
        if (unemploymentRate < 1) unemploymentRate = 1;
        if (unemploymentRate > 25) unemploymentRate = 25;
    }
	
	public static int overton = 50;
	public static boolean ideoTrend = ra.nextBoolean();
	public static int trendstrength = ra.nextInt(12);
	public static void overtonShift(){
		int totShift = 0;
		if(president != null){
			totShift += (president.getIdeology()-overton);
		}
		
		if(primeMinister!= null){
			totShift += (primeMinister.getIdeology()-overton);
		}
		
		if(vicePresident!= null){
			totShift += (vicePresident.getIdeology()-overton);
		}
		
		if(ideoTrend){
			totShift += ra.nextInt(trendstrength);
		}else{
			totShift -= ra.nextInt(trendstrength);
		}
		totShift /=10;
		trendstrength--;
		if(trendstrength <1){
			ideoTrend = !ideoTrend;
			trendstrength = (ra.nextInt(10)+4)*3;
		}
		
		
		
		overton += totShift;
		
		if(overton > 100){
			overton = 100;
		}
		
		if(overton < 0){
			overton = 0;
		}
	}
	
	public static int radicalism = 0;

	public static void updateRad(){
		int change = 0;
		if(snapElec){
			change += 2;
		}
		
		if(rulingCoalition!= null){
			if(rulingCoalition.getTotalSeats()<50){
				change +=1;
			}
		}
		
		change -= Police.strength/1000;
		
		//change -= (100-(Math.abs(rulingParty.getPolicy()-50)))/20;
		
		change += (isFair)? -1:2;
		
		change += (100-approvalRating)/20;
		
		change+= unemploymentRate/5;
		
		change = (change*policeControl)/75;
		
		if (auth > 70) {
    // Authoritarianism radicalizes public
    change+= (auth - 60) / 5; // e.g., at auth=80, extraRad=4
    
}

		
		if(policeControl> 75 && policeControl != 100){
			//change *=-1;
		}
		System.out.println("Change: " + change);
		changeRad(change);
	}
	public static void changeRad(int change){
		radicalism += change;
		if(radicalism > 100){
			radicalism = 100;
		}
		
		if(radicalism < 0){
			radicalism = 0;
		}
	}
	
	public static void checkParamilitaries(){
			// Only consider paramilitary formation if radicalism is high
		if (radicalism > 50) {
			for (Party par : allParties) {
				// Extreme parties are more likely to form paramilitaries
				boolean isExtreme = (par.getPolicy() < 20 || par.getPolicy() > 80);
				// Only form paramilitary if not already present
				boolean hasParamilitary = par.paramilitary != null;
				// The more radicalism, the higher the chance
				int chance = (radicalism/2) + (isExtreme ? 25 : 0); // boost for extremes

				if (!hasParamilitary && ra.nextInt(100) < chance) {
					newParamilitary(par);
					System.out.println(par.getName() + " has formed a paramilitary group!");
				}
			}
		} else {
			// Optionally, disband paramilitaries if radicalism drops
			for (Party par : allParties) {
				if (par.paramilitary != null && par.paramilitary.strength < 10) {
					System.out.println(par.getName() + " disbands their paramilitary group.");
					par.paramilitary = null;
				}
			}
		}
	}
	
	public static void newParamilitary(Party par){ //String name, int strength, boolean isPolitical
		String name;
		if(par.getPolicy()>50){
			name = leftWingParamilitaries[ra.nextInt(leftWingParamilitaries.length)];
		}else{
			name = rightWingParamilitaries[ra.nextInt(rightWingParamilitaries.length)];
		}
		
		
		par.paramilitary = new armedGroup(name, 10, true);
	}
    
	public static String[] leftWingParamilitaries = {
    "People’s Liberation Front", "Red Banner Movement", "Workers’ Freedom Army",
    "People’s Revolutionary Guard", "Democratic Liberation Front", "Union of the Masses",
    "New Dawn Brigades", "People’s Justice Army", "Revolutionary Labor Front",
    "Free People’s Force", "Popular Liberation Army", "People’s Unity Brigades",
    "Revolutionary People’s Vanguard", "United Workers’ Front", "Voice of the People Movement",
    "People’s Shield Army", "Liberation Movement of the Masses", "People’s Resistance Force",
    "Revolutionary People’s Union", "United Front of Labor", "Proletarian Defense Force",
    "Red People’s Guard", "Movement for the Poor", "Free Worker’s Army",
    "Socialist People’s Front", "New Dawn Collective", "People’s Defense Brigades",
    "Freedom for All Army", "Workers’ Unity Front", "Revolutionary Justice Force",
    "People’s Volunteer Corps", "Union of the Oppressed", "Red Horizon Movement",
    "Liberation of the People Army", "Democratic People’s Movement", "Socialist Vanguard",
    "Voice of Labor Front", "Proletarian Liberation Army", "New Horizon Movement",
    "Revolutionary Defense Force", "Workers’ People’s Guard", "People’s Unity Force",
    "Revolutionary Justice Front", "United Commune Movement", "People’s Volunteer Guard",
    "Red Uprising Army", "Freedom and Labor Front", "People’s Democratic Union",
    "Revolutionary People’s Front", "Free People’s Brigades", "Union for Equality"
};

	public static String[] rightWingParamilitaries = {
    "National Defense League", "Guardians of Order", "Homeland Protection Corps",
    "Faith and Honor Front", "Sons of the Nation", "Unity Defense Force",
    "Order and Progress Guard", "National Renewal Army", "Defenders of the Republic",
    "Shield of the Nation", "Faithful Service Corps", "Loyalist Volunteer Force",
    "Front for National Stability", "Patriotic Guard", "Order of the Banner",
    "National Salvation Legion", "Faithful People’s Army", "Unity and Justice Front",
    "Homeland Defense Force", "Order and Unity Brigade", "Legion of the Republic",
    "National Order Corps", "People’s Protection Movement", "Loyalist Defense Guard",
    "Faithful Front", "National Rebirth Force", "Order of the Crossed Swords",
    "Patriotic Renewal Front", "Defenders of Civilization", "Guardians of the State",
    "Front for National Harmony", "Shield of the Republic", "Faith and Nation Legion",
    "Homeland Defense Brigades", "Front for Order and Stability", "People’s Loyal Army",
    "Sovereign Defense Front", "Faithful Order Movement", "Front for National Revival",
    "Loyalist People’s Guard", "Order and Justice Corps", "Sons of the Republic",
    "Defenders of Tradition", "National People’s Legion", "Front for Faith and Duty",
    "United Loyalist Force", "Guardians of the Homeland", "National Restoration Corps",
    "Shield of the Fatherland", "Order of Renewal", "Patriot’s Legion"
};

public static void updateParamilitaries(){
	int strengthToAdd = 0;
	for(Party par: allParties){
		if(par.paramilitary!=null){
			strengthToAdd = 0;
			int tgroupstrength = 0;
			if(par.supportGroups!=null){
				for(Group gro: par.supportGroups){
					tgroupstrength+= gro.getPoints();
				}
				strengthToAdd = tgroupstrength/par.supportGroups.size();
			}
			
			strengthToAdd= (strengthToAdd*radicalism)/100;
			
			int poldif = Math.abs(par.getPolicy() - rulingParty.getPolicy());
			
			strengthToAdd = (strengthToAdd*poldif)/100;
			
			if(radicalism <50){
				strengthToAdd = (par.paramilitary.strength/10)*-1;
			}
			boolean neg = false;
			if(strengthToAdd < 1)
			{
				
				neg = true;
			}
			strengthToAdd = ra.nextInt(Math.abs(strengthToAdd)+1); 
			
			if(neg){
				strengthToAdd*=-1;
			}
			addStrength(par.paramilitary, strengthToAdd);
		}
	}
}

public static void addStrength(armedGroup arm, int toAdd){
	arm.strength+= toAdd;
	if(arm.strength <0){
		arm.strength = 0;
	}
}


	
	
public static int policeControl = 100;

public static void monopolyOfViolence(){
	int policeStrength = Police.strength;
	int otherParamilitaryStrength = 0;
	List<Party> partiesWithParams = new ArrayList<>();
	for(Party par: allParties){
		if(par.paramilitary !=null){
			otherParamilitaryStrength += par.paramilitary.strength;
			partiesWithParams.add(par);
		}
	}
	int total = policeStrength + otherParamilitaryStrength;
	int pctg = 0;
	
	System.out.println("\nThe Monopoly of Violence");
	pctg = (Police.strength*100)/total;
	System.out.println("Police: "+ Police.strength+ " ("+ pctg+ "%)");
	policeControl = pctg;
	for(Party par: partiesWithParams){
		pctg = (par.paramilitary.strength*100)/total;
		System.out.println(par.paramilitary.name+ ": "+ par.paramilitary.strength + " ("+ pctg+ "%)");
	}
	
	
	
}

public static void checkOverthrow(){
	int revolutionChance = 0;
if (auth > 70 && radicalism > 60) {
    revolutionChance += (auth + radicalism) / 2; // or something steeper
    // Consider adding more if approval is low
    if (approvalRating < 40) revolutionChance += (40 - approvalRating);
}
System.out.println("Revolution chance: "+ revolutionChance);
if (ra.nextInt(100) < revolutionChance) {
    overthrowGovernment();
}
	
}

public static void overthrowGovernmentOld(){
	int totalParamilitaryStrength = Police.strength;
	List<Party> partiesWithParams = new ArrayList<>();
	for(Party par: allParties){
		if(par.paramilitary !=null){
			totalParamilitaryStrength += par.paramilitary.strength;
			partiesWithParams.add(par);
		}
	}
	Party maxPar = null;
	int maxnum = 0;
	
	for(Party par: partiesWithParams){
		if(par.paramilitary.strength > maxnum && par != rulingParty){
			maxnum = par.paramilitary.strength;
			maxPar = par;
		}
	}
	
	int winparnumpctg = (maxnum*100)/totalParamilitaryStrength;
		System.out.println("The Government has been overthrown");
		addToArchive();
		rulingParty = maxPar;
		rulingCoalition = null;
		toleration.clear();
		radicalism/=2;
		auth = Math.abs(maxPar.getPolicy()-50)*2;
		approvalRating = winparnumpctg;
		startdate = year;
            leaderStartDate = year;
	formCoalitions(maxPar);
}

public static void overthrowGovernment() {
    // Gather all paramilitary parties (not in government)
    List<Party> rebels = new ArrayList<>();
    int rebelStrength = 0, policeStrength = Police.strength;
    for (Party par : allParties) {
        if (par.paramilitary != null && par != rulingParty) {
            rebels.add(par);
            rebelStrength += par.paramilitary.strength;
        }
    }

    // Coup odds: rebels vs police
    boolean coupSuccess = rebelStrength > policeStrength || ra.nextInt(policeStrength + rebelStrength + 1) < rebelStrength;

    if (!coupSuccess) {
        // Coup fails: government cracks down
        System.out.println("Coup attempt failed! Crackdown on rebels.");
        for (Party rebel : rebels) {
            addStrength(rebel.paramilitary, -ra.nextInt(rebel.paramilitary.strength / 2 + 1)); // lose up to half strength
        }
        addStrength(Police, policeStrength / 4); // police emboldened
        changeAuth(10); // authoritarianism rises
        changeRad(10); // radicalism rises
        approvalRating -= 10;
        return;
    }

    // Coup succeeds: determine leader (random weighted by strength)
    int totalStrength = 0;
    for (Party rebel : rebels) totalStrength += rebel.paramilitary.strength;
    int pick = ra.nextInt(totalStrength);
    Party newGov = null;
    int runningSum = 0;
    for (Party rebel : rebels) {
        runningSum += rebel.paramilitary.strength;
        if (pick < runningSum) {
            newGov = rebel;
            break;
        }
    }

    System.out.println("The government has been overthrown by " + newGov.getName() + "!");
    addToArchive();
    rulingParty = newGov;
    rulingCoalition = null;
    toleration.clear();
    radicalism /= 2;
    auth = Math.abs(newGov.getPolicy() - 50) * 2;
    approvalRating = 30 + ra.nextInt(40); // low, but not zero
    startdate = year;
    leaderStartDate = year;

    // Aftermath: all rebel paramilitaries lose strength (civil war casualties)
    for (Party rebel : rebels) {
        if (rebel != newGov) {
            addStrength(rebel.paramilitary, -rebel.paramilitary.strength / 2);
        }
    }
    // Police are weakened
    addStrength(Police, -policeStrength / 2);

    formCoalitions(newGov);
}

public static void displayRad(){
	System.out.print("Radicalism is ");
	if(radicalism> 50){
		if(radicalism> 75){
			System.out.print("Rampant");
		}else{
			System.out.print("Common");
		}
	}else{
		if(radicalism>25){
			System.out.print("Rising");
		}else{
			System.out.print("Minimal");
		}
	}
	System.out.println(" ("+ radicalism+")");
}

public static void displayOverton(){
	System.out.print("The overton window leans towards ");
	if(overton>50){
                if(overton>85){
                    System.out.print("the far left");
                }else if(overton <=85 && overton> 70){
                    System.out.print("the left");
                }else{
                    System.out.print("the center");
                }
            }else{
                if(overton>30){
                    System.out.print("the center");
                }else if(overton<=30 && overton>15){
                    System.out.print("the right");
                }else{
                    System.out.print("the far right");
                }
            }
	System.out.println(" ("+ overton+")");
}

public static void displayPresArchive(){
	for(presidentArchived prar: presArchive){
		System.out.println(prar);
	}
}

public static void displayAllArchives(){
	for(presidentArchived prar: presArchive){
		System.out.println("\n"+prar);
		for(archiveParty par : previousRulingParties){
			if(par.enddate > prar.startyear&& par.startdate < prar.endyear){
				System.out.print("     ");
				par.display();
			}
		}
	} 
	
	String parname = "";
	
	for(Party par: allParties){
		for(Group gro: par.supportGroups){
			if(gro.getLeader() == president){
				parname = par.getName();
			}
		}
	}
	
	System.out.println("\n"+ president.getName()+" | "+ parname+ " ("+ presStartYear+ " - Present)");
	for(archiveParty par: previousRulingParties){
		
		if(par.enddate > presStartYear){
			System.out.print("     ");
			par.display();
		}
		
	}
	System.out.print("     ");
	System.out.println(primeMinister.getName() + " | "+ rulingParty.getName()+ " ("+ startdate+ " - Present)");
}
	
	public static void main(String[] args) throws Exception{
	    Scanner sc = new Scanner(System.in);
		generateGroups();
		generateParties();
		int arrangeCountdown = 6;
		
		while(true){
		
		arrangeCountdown--;
		//if(arrangeCountdown == 0){
			arrangeGroups();     // (1) Assigns groups to parties based on current party policies
		//	arrangeCountdown = 6;
		//}
        checkAlienation();   // (2) Identifies unrepresented groups and may create new parties
        checkNoGroups();     // (3) Cleans up parties with no support groups (needed after new parties may be created)
        for(Party par: allParties){
            par.unityUpdate();
        }
        monthly();
        sortParties();
        System.out.println("Approval Rating: "+ approvalRating+ "%");
        //System.out.println("DEBUG AUTH: "+ auth);
		// --- ECONOMIC DISPLAY ---
            System.out.println("Economic Index: " + economicIndex);
            System.out.println("Unemployment Rate: " + unemploymentRate + "%");
			int gdpGrowth = (economicIndex-25)/5;
			System.out.println("GDP Growth: " +gdpGrowth+"%");
			
			System.out.println("\n");
			
			//displayRad();
			displayOverton();
			//System.out.println("Democracy Index: "+ (100-auth));
		System.out.println(months[moNum]+ " - "+ year);
		System.out.print("Next Presidential election in ");
		if(presCdown > 12){
		    
		    System.out.print(presCdown/12);
		    if(presCdown/12 >1){
		        System.out.println(" years");
		    }else{
		        System.out.println(" year");
		    }
		    
		}else{
		    System.out.print(presCdown);
		    if(presCdown > 1){
		        System.out.println(" months");
		    }else{
		        System.out.println(" month");
		    }
		}
		
		System.out.print("Next Parliamentary election in ");
		if(elecCount > 12){
		    
		    System.out.print(elecCount/12);
		    if(elecCount/12 >1){
		        System.out.println(" years");
		    }else{
		        System.out.println(" year");
		    }
		    
		}else{
		    System.out.print(elecCount);
		    if(elecCount > 1){
		        System.out.println(" months");
		    }else{
		        System.out.println(" month");
		    }
		}
		
		
		if(president != null){
		    System.out.println("\nPresident: "+ president.getName()+ " ("+ president.disIdeo()+ ")");
			System.out.println("	Vice-President: "+ vicePresident.getName()+ " ("+ vicePresident.disIdeo()+ ")");
		}
		if(speaker !=null){
			System.out.println("Speaker: "+ speaker.getName() + " ("+ speaker.disIdeo()+ ")");
		}
		if(rulingParty != null){
		    if(rulingParty.getLeader() != null){
		    System.out.println("Prime Minister: " + primeMinister.getName()+ " ("+ primeMinister.disIdeo()+ ")");
		    }
		System.out.println("\nRuling Party: "+ rulingParty.getName());
		}
		
        /*System.out.println("\nThe Governing Coalition: ");
        for(Party par: government){
                System.out.println(par.getName());
            }
            
        System.out.println("\nThe Opposition Coalition: ");
        for(Party par: opposition){
                System.out.println(par.getName());
            }
		
		}*/
		int idx = 1;
		if(rulingCoalition != null){
		for(Party par: rulingCoalition.members){
		    System.out.print(toAcronym(par.getName()));
		    System.out.print((idx == rulingCoalition.members.size())? "":"-" );
		    idx++;
		}
		
		System.out.println(" Government - "+ rulingCoalition.getTotalSeats()+ "% of Parliament");
		rulingCoalition.displayMembers();
		}
		
		if(toleration.size()>0){
			System.out.println("\nParties in toleration:");
			
			for(Party par: toleration){
				System.out.println(par.getName());
			}
			
		}
		
		System.out.println("\nOpposition Parties:");
		for(Party par: independentParties){
			if(!toleration.contains(par)){
				System.out.println(par.getName());
			}
		}
		
		int totsup = 0;
		for(Party par: allParties){  
		    totsup = 0;
		    System.out.println("\n\n"+par.getName()+ " - "+ par.getIdeology() + " - "+ par.getSeats()+ "% of Parliament");
		    if(par.getLeader()!=null){
		    System.out.println("Leader: "+ par.getLeader().getName());
			//System.out.println(par.getLeader().getIdeology());
			//System.out.println(par.getPolicy());
		    }else{
		        System.out.println("Leader: No Leader");
		    }
		    for(Group gro : par.supportGroups){
		        System.out.print(" - "+gro.getName());
		        totsup += gro.getPoints();
		    }
		    System.out.println(par.getUnity());
		    //System.out.println("Support Points: "+ totsup);
		    
		}
		//monopolyOfViolence();
		
		int totalnumofseats = 0;
		for(Party par : allParties){
		    totalnumofseats += par.getSeats();
		}
		
		//System.out.println("Total Number of Seats: "+ totalnumofseats);
		
		//for debug 
		/*System.out.println("\n\n");
		for(Group gro : allGroups){
		    System.out.println(gro.getName()+ " "+ gro.getPoints());
		}*/
		
		System.out.println("\n");
		String put = "";
		
		//if(1 == 2){
		put = sc.nextLine();
		//}
		//String put = "debugmode"; // for debug
		if(put.equalsIgnoreCase("archive")){
			
			/*System.out.println("prim for Prime Ministers and pres for Presidents");
			put = sc.nextLine();
			if(put.equalsIgnoreCase("prim")){
				displayArchive();
			}else if(pres){
				displayPresArchive();
			}else{
				System.out.println("Presidents: ");
				
			}*/
		    displayAllArchives();
		    sc.nextLine();
		}
		if(put.equalsIgnoreCase("exit")){
			System.exit(0);
		}
		//System.out.print("\033[H\033[2J"); System.out.flush();
		System.out.println(new String(new char[50]).replace("\0", "\r\n")); 
		demographicShifts();
		partyShifts();
		
		
		}
		
		
	}
}
