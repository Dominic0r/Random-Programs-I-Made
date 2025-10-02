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
					
					
					
                    this.leader = new Person(ra.nextInt(20)+45, getRandomName(), minPolicy +ra.nextInt(maxPolicy-minPolicy));
					if(oldPerson == president){
						president = vicePresident;
						appointVP();
					}
					
					if(oldPerson == vicePresident){
						appointVP();
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
        
        Person leader;
        List<Group> supportGroups = new ArrayList<>();
        
        public Party(int policy, String name){
            this.policy = policy;
            this.name = name;
            this.seats = 0;
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
            
            ideounity = (100-Math.abs(maxide-minide))/2;
            
            int numpar = 50- (supportGroups.size()*5);
            unity = ideounity + numpar;
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
            
            if(getTotalSeats() < 40){
                totstab/=2;
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
allGroups.add(new Group(1, 15, "Traditionalists", 20));         // 0 – culturally rigid, fading influence
allGroups.add(new Group(10, 30, "Nationalists", 35));           // 1 – assertive, suspicious of global elites
allGroups.add(new Group(30, 40, "Capitalists", 70));            // 2 – elite-backed, anti-populist
allGroups.add(new Group(20, 35, "Law & Order Bloc", 50));       // 3 – pro-police, anti-chaos, neutral on economics
allGroups.add(new Group(40, 55, "Small Business Owners", 45));  // 4 – practical-minded, split over regulation
allGroups.add(new Group(15, 30, "Rural Conservatives", 30));    // 5 – nostalgic, pro-subsidies
allGroups.add(new Group(55, 70, "Liberal Reformers", 70));      // 7 – urbanites, reform-focused
allGroups.add(new Group(40, 60, "Centrists / Moderates", 80)); // 6 – technocratic, middle-of-the-road
allGroups.add(new Group(65, 80, "Labor Unions", 50));           // 8 – class-driven, suspicious of elites
allGroups.add(new Group(70, 85, "Progressives", 60));           //9 – identity-focused, decentralized
allGroups.add(new Group(65, 80, "Environmentalists", 20));      //10 – passionate but divided
allGroups.add(new Group(75, 90, "Socialists", 35));            //11 – radical but infighting-prone
allGroups.add(new Group(85, 100, "Radicals", 15));          //12 – chaotic, often uncooperative

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
            
            
            if(approvalRating<50 && !rulingCoalition.members.contains(par)){
                points += Math.abs((par.getPolicy() - rulingCoalition.getLeader().getPolicy()))-15;
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
					if(gro.leader == president){points += points/2;
					}
					if(gro.leader == vicePresident){points += points/4;
					}
				}
			}
            
            
            if(snapElec&& rulingCoalition.members.contains(par)){
                points /=2;
            }
            
            if(par.isMajor()){
                points +=points/4;
            }
            
            if(par == rulingParty && !isFair){
                int increaseby = auth/10;
                points *=increaseby;
            }
            
            /*for(int i=0; i<5; i++){
                if(ra.nextBoolean()){
                    points += points/4;
                }
            }*/
            //System.out.println(par.getName()+ " "+points*100);
            
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
    public static void formCoalitions(Party largePar){
        allCoalitions.clear();
        int thresh = 40 - (5*allParties.size());
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
            }else if(coa.getStability() == maxnum){
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
        
        
        
        
        
        for(Coalition coa : allCoalitions){
            if(coa!=maxCoa&& coa != null){
                
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
        int threshold = 65;
        List<Group> alienatedGroups = new ArrayList<>();
    for(Party par: allParties){
        for(Group gro : par.supportGroups){
            resonance = (100-Math.abs(gro.getIdeology()-par.getPolicy()))/2;
            resonance += (100-(par.supportGroups.size()*5))/2;
            
            
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
        int threshold = allParties.size();
        int lostseats = 0;
        List<Party> toRemove = new ArrayList<>();
        for(Party par : allParties){
            if(par.getSeats()<= threshold){
                lostseats += par.getSeats();
                toRemove.add(par);
            }
        }
        
        allParties.removeAll(toRemove);
        toRemove.clear();
        if(lostseats >0){
            redistrib(lostseats);
        }
    }
    
    public static void redistrib(int toredis){
        int maxval=100000;
        Party maxPar = null;
        for(int i=0; i< toredis;i++){
            maxval = 100000;
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
            if(par.supportGroups.size() == 0){
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
        redistrib(lostseats);
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
        //updateAuth(); Authoritarian backsliding delayed for now
        //checkIsFair();
        //delayElec();
        }
            
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
        
        if(!hasTrig && !snapElec&& rulingParty!=null){
            if(ra.nextInt(80)> rulingCoalition.stability+ (rulingCoalition.stability/2)){
                
                if(elecCount >12){
                    System.out.println("SNAP ELECTION");
                    snapElec = true;
                    elecCount = 5;
                }
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
        auth -= 10- (year-startdate);
        if(year- leaderStartDate < 5){
            auth -= 1;
        }
        
        if(rulingParty.getPolicy() < 15 || rulingParty.getPolicy() > 85){
            auth += 1;
        }else{
            auth -=50;
        }
        
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
    
    public static void electPresident(){
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
	appointVP();
}

public static Person vicePresident;

public static void appointVP(){
	int maxnum = 0;
	Group maxGroup = null;
	int factor = 0;
	
	for(Group gro : allGroups){
			if(gro.leader != president){
			factor = 100-Math.abs(president.getIdeology()-gro.leader.getIdeology());
			if(factor > maxnum){
				maxnum = factor;
				maxGroup = gro;
			}
		}
	}
	
	vicePresident = maxGroup.leader;
}

public static boolean boombums = true; // boom = true, bust = false
public static int bbcDown = 10; // boombust countdown

// --- ECONOMIC SYSTEM METHOD ---
    public static void updateEconomy() {
        // Random fluctuation, affected by policy centerness
        int economicChange = ra.nextInt(7) - 3; // -3 to +3
		if(boombums){
			economicChange += ra.nextInt(bbcDown+1);
		}else{
			economicChange -= ra.nextInt(bbcDown+1);
		}
		bbcDown--;
		
		if(bbcDown<1){
			bbcDown = ((ra.nextInt(5)+1)*4)+6;
			boombums = !boombums;
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

    
    
	public static void main(String[] args) throws Exception{
	    Scanner sc = new Scanner(System.in);
		generateGroups();
		generateParties();
		
		
		while(true){
		    
		arrangeGroups();     // (1) Assigns groups to parties based on current party policies
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
		    System.out.println("President: "+ president.getName()+ " ("+ president.disIdeo()+ ")");
			System.out.println("Vice-President: "+ vicePresident.getName()+ " ("+ vicePresident.disIdeo()+ ")");
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
		
		System.out.println("\nOpposition Parties:");
		for(Party par: independentParties){
		    System.out.println(par.getName());
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
		
		int totalnumofseats = 0;
		for(Party par : allParties){
		    totalnumofseats += par.getSeats();
		}
		
		System.out.println("Total Number of Seats: "+ totalnumofseats);
		
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
		    displayArchive();
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
