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
            minpoints = givePoints- (givePoints/5);
            maxpoints = givePoints + (givePoints/5);
            this.leader = new Person(ra.nextInt(20)+45, getRandomName(), (minPolicy+maxPolicy)/2);
            
        }
        
        public int getMin(){
            return this.minPolicy;
        }
        
        
        public void checkLeaderAge(){
            if(leader.getAge() > 65){
                if(ra.nextInt(20)+75 < leader.getAge()){
                    oldPerson = this.leader;
                    changedLead = true;
                    this.leader = new Person(ra.nextInt(20)+45, getRandomName(), (minPolicy+maxPolicy)/2);
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
            return this.partyNames.get(ra.nextInt(this.partyNames.size()));
        }
    }
    
    public static class Party{
        int policy;
        int seats;
        String name;
        boolean isMajor;
        
        Person leader;
        List<Group> supportGroups = new ArrayList<>();
        
        public Party(int policy, String name){
            this.policy = policy;
            this.name = name;
            this.seats = 0;
        }
        
        public void determineLeader(){
            if(supportGroups!= null){
            int wpoint = -1;
            Group wingroup = null;
            
            oldPerson = leader;
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
    
    
    public static List<Group> allGroups = new ArrayList<>();
    public static void generateGroups(){
allGroups.add(new Group(1, 15, "Traditionalists", 15));         // 0 – culturally rigid, fading influence
allGroups.add(new Group(10, 30, "Nationalists", 25));           // 1 – assertive, suspicious of global elites
allGroups.add(new Group(25, 45, "Capitalists", 70));            // 2 – elite-backed, anti-populist
allGroups.add(new Group(20, 35, "Law & Order Bloc", 50));       // 3 – pro-police, anti-chaos, neutral on economics
allGroups.add(new Group(35, 55, "Small Business Owners", 60));  // 4 – practical-minded, split over regulation
allGroups.add(new Group(15, 35, "Rural Conservatives", 30));    // 5 – nostalgic, pro-subsidies
allGroups.add(new Group(35, 65, "Centrists / Moderates", 80)); // 6 – technocratic, middle-of-the-road
allGroups.add(new Group(55, 75, "Liberal Reformers", 70));      // 7 – urbanites, reform-focused
allGroups.add(new Group(60, 85, "Labor Unions", 60));           // 8 – class-driven, suspicious of elites
allGroups.add(new Group(70, 90, "Progressives", 45));           //9 – identity-focused, decentralized
allGroups.add(new Group(65, 95, "Environmentalists", 15));      //10 – passionate but divided
allGroups.add(new Group(80, 100, "Socialists", 30));            //11 – radical but infighting-prone
allGroups.add(new Group(85, 100, "Radical Youth", 15));          //12 – chaotic, often uncooperative


// 0 - Traditionalists
allGroups.get(0).addPartyName("Faith and Family Party");
allGroups.get(0).addPartyName("Moral Order Party");
allGroups.get(0).addPartyName("Sacred Tradition Front");
allGroups.get(0).addPartyName("Conscience Party");
allGroups.get(0).addPartyName("Covenant Union");
allGroups.get(0).addPartyName("Pillar of Values Movement");
allGroups.get(0).addPartyName("Heritage Party");
allGroups.get(0).addPartyName("National Virtue Coalition");
allGroups.get(0).addPartyName("Guardian Party");
allGroups.get(0).addPartyName("Traditional Path Front");

// 1 - Nationalists
allGroups.get(1).addPartyName("National Front");
allGroups.get(1).addPartyName("Sovereign Nation Party");
allGroups.get(1).addPartyName("Flagbearers' Union");
allGroups.get(1).addPartyName("Homeland Voice Movement");
allGroups.get(1).addPartyName("Great Nation Alliance");
allGroups.get(1).addPartyName("Citizens' Front");
allGroups.get(1).addPartyName("Homeland Defense Party");
allGroups.get(1).addPartyName("Unity Nation Bloc");
allGroups.get(1).addPartyName("National Renewal Party");
allGroups.get(1).addPartyName("True Patriots' Union");

// 2 - Capitalists
allGroups.get(2).addPartyName("Free Market Party");
allGroups.get(2).addPartyName("Enterprise League");
allGroups.get(2).addPartyName("Economic Liberty Coalition");
allGroups.get(2).addPartyName("Growth First Party");
allGroups.get(2).addPartyName("Opportunity Union");
allGroups.get(2).addPartyName("Business Forward Bloc");
allGroups.get(2).addPartyName("Commerce Alliance");
allGroups.get(2).addPartyName("Modern Economy Front");
allGroups.get(2).addPartyName("Prosperity Party");
allGroups.get(2).addPartyName("Innovation and Trade Party");

// 3 - Law & Order Bloc
allGroups.get(3).addPartyName("Order and Justice Party");
allGroups.get(3).addPartyName("Peace and Stability Union");
allGroups.get(3).addPartyName("Security First Party");
allGroups.get(3).addPartyName("Lawkeepers' Front");
allGroups.get(3).addPartyName("Shield Party");
allGroups.get(3).addPartyName("Justice and Honor Movement");
allGroups.get(3).addPartyName("Civic Discipline Coalition");
allGroups.get(3).addPartyName("Safe Streets Party");
allGroups.get(3).addPartyName("Law and Duty Alliance");
allGroups.get(3).addPartyName("Nation Secure Bloc");

// 4 - Small Business Owners
allGroups.get(4).addPartyName("Entrepreneurs' Party");
allGroups.get(4).addPartyName("Independent Work Alliance");
allGroups.get(4).addPartyName("Local Prosperity Party");
allGroups.get(4).addPartyName("Peoples Commerce Front");
allGroups.get(4).addPartyName("Small Enterprise Coalition");
allGroups.get(4).addPartyName("Owners and Workers Party");
allGroups.get(4).addPartyName("Community Business Bloc");
allGroups.get(4).addPartyName("Merchant League");
allGroups.get(4).addPartyName("Self-Reliance Movement");
allGroups.get(4).addPartyName("Marketplace Party");

// 5 - Rural Conservatives
allGroups.get(5).addPartyName("Rural Voice Party");
allGroups.get(5).addPartyName("Farm and Faith Coalition");
allGroups.get(5).addPartyName("Fields and Families Party");
allGroups.get(5).addPartyName("Agrarian Union");
allGroups.get(5).addPartyName("Backcountry Party");
allGroups.get(5).addPartyName("Soil and Spirit Front");
allGroups.get(5).addPartyName("Homestead Movement");
allGroups.get(5).addPartyName("Green Hills Party");
allGroups.get(5).addPartyName("Folkland Bloc");
allGroups.get(5).addPartyName("Countryside Alliance");

// 6 - Centrists / Moderates
allGroups.get(6).addPartyName("Common Ground Party");
allGroups.get(6).addPartyName("Unity Movement");
allGroups.get(6).addPartyName("National Consensus Party");
allGroups.get(6).addPartyName("Balanced Future Bloc");
allGroups.get(6).addPartyName("People’s Middle Way");
allGroups.get(6).addPartyName("Moderate Alliance");
allGroups.get(6).addPartyName("Civic Bridge Party");
allGroups.get(6).addPartyName("Forward Together Front");
allGroups.get(6).addPartyName("Coalition for Progress");
allGroups.get(6).addPartyName("Stability and Reform Party");

// 7 - Liberal Reformers
allGroups.get(7).addPartyName("Reform Party");
allGroups.get(7).addPartyName("Civic Renewal Front");
allGroups.get(7).addPartyName("Open Society Party");
allGroups.get(7).addPartyName("Freedom and Rights Coalition");
allGroups.get(7).addPartyName("Hope and Change Movement");
allGroups.get(7).addPartyName("New Light Party");
allGroups.get(7).addPartyName("Modern Unity Bloc");
allGroups.get(7).addPartyName("Future Civic Party");
allGroups.get(7).addPartyName("Progressive Union");
allGroups.get(7).addPartyName("Center for Reform");

// 8 - Labor Unions
allGroups.get(8).addPartyName("Workers Alliance");
allGroups.get(8).addPartyName("Labor Solidarity Party");
allGroups.get(8).addPartyName("Peoples Rights Front");
allGroups.get(8).addPartyName("Union Front Party");
allGroups.get(8).addPartyName("Working Families Bloc");
allGroups.get(8).addPartyName("Justice for Labor Party");
allGroups.get(8).addPartyName("Strong Hands Movement");
allGroups.get(8).addPartyName("Labor Voice Union");
allGroups.get(8).addPartyName("Fair Work Party");
allGroups.get(8).addPartyName("Peoples Strength Coalition");

// 9 - Progressives
allGroups.get(9).addPartyName("Forward Equality Party");
allGroups.get(9).addPartyName("Change Now Coalition");
allGroups.get(9).addPartyName("Rainbow Front");
allGroups.get(9).addPartyName("Social Justice Party");
allGroups.get(9).addPartyName("Tomorrow Movement");
allGroups.get(9).addPartyName("Equality Bloc");
allGroups.get(9).addPartyName("Bright Future Party");
allGroups.get(9).addPartyName("Inclusive Society Party");
allGroups.get(9).addPartyName("Unity for Progress");
allGroups.get(9).addPartyName("New Rights Party");

// 10 - Environmentalists
allGroups.get(10).addPartyName("Green Party");
allGroups.get(10).addPartyName("Planet First Coalition");
allGroups.get(10).addPartyName("EcoFuture Bloc");
allGroups.get(10).addPartyName("Clean Earth Alliance");
allGroups.get(10).addPartyName("Natures Voice Party");
allGroups.get(10).addPartyName("Zero Emissions Front");
allGroups.get(10).addPartyName("Living Earth Party");
allGroups.get(10).addPartyName("Global Greens Union");
allGroups.get(10).addPartyName("Sustainable Society Party");
allGroups.get(10).addPartyName("Earthguard Movement");

// 11 - Socialists
allGroups.get(11).addPartyName("Peoples Socialist Party");
allGroups.get(11).addPartyName("Red Flag Union");
allGroups.get(11).addPartyName("United Workers Party");
allGroups.get(11).addPartyName("Class Struggle Front");
allGroups.get(11).addPartyName("Social Equality Bloc");
allGroups.get(11).addPartyName("Revolutionary Justice Party");
allGroups.get(11).addPartyName("Democratic Labor Party");
allGroups.get(11).addPartyName("New Left Front");
allGroups.get(11).addPartyName("Common Struggle Union");
allGroups.get(11).addPartyName("People Power Coalition");

// 12 - Radical Youth
allGroups.get(12).addPartyName("Youth Liberation Front");
allGroups.get(12).addPartyName("Revolt and Rise Party");
allGroups.get(12).addPartyName("Next Gen Coalition");
allGroups.get(12).addPartyName("Awakened Voices Party");
allGroups.get(12).addPartyName("Radical Future Bloc");
allGroups.get(12).addPartyName("Voices of Change");
allGroups.get(12).addPartyName("Break the Chains Party");
allGroups.get(12).addPartyName("The Spark Movement");
allGroups.get(12).addPartyName("Reimagine Party");
allGroups.get(12).addPartyName("New Flame Front");




    }
    
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
    
    public static int approvalRating = 50;
    
    
    
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

    
    
    
    
    public static void arrangeGroups(){
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
                }else{
                    currentPoints += 50;
                }
                
                currentPoints /= (Math.abs(parpol-avg))+1;
                
                partyScore.put(par,currentPoints);
                
                if(par.supportGroups.contains(gro)&& par.supportGroups.size() == 1){
                    currentPoints+= 10000;
                }
                
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
    
    public static int leaderStartDate = startdate;
    
    public static void election(){
        checkMajors();
        HashMap<Party,Integer> partyScore = new HashMap<>();
        HashMap<Party,Integer> partyScoreOrig = new HashMap<>();
        HashMap<Party,Integer> partySeats = new HashMap<>();
        int points;
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
                points += Math.abs((par.getPolicy() - rulingCoalition.getLeader().getPolicy()));
            }
            
            
            if(snapElec&& rulingCoalition.members.contains(par)){
                points /=2;
            }
            
            if(par.isMajor()){
                //points += points/4;
            }
            
            if(par == rulingParty && !isFair){
                int increaseby = auth/10;
                points *=increaseby;
            }
            //System.out.println(par.getName()+ " "+points*100);
            
            partyScore.put(par, points*100);
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
            for(Map.Entry<Party, Integer> entry : partyScore.entrySet()){
                System.out.println(entry.getValue()+ " "+ entry.getKey());
            }
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
            if(par.getSeats() >= allParties.size()){
                System.out.println(par.getName()+ ": "+ par.getSeats()+"%");
            }
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
            if(coa!=maxCoa){
                
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
    
    public static void checkAlienation(){
        int min,max,avg;
        int parpol = 0;
        int threshold = 10;
        int resonancePoints = 0;
        for(Group gro : allGroups){
            min= gro.getMin();
            max = gro.getMax();
            avg = (min+max)/2;
            
            
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
                }
                
            }
            
            int totGroupPoints = 0;
            for(Group gp : allGroups){
                totGroupPoints+= gp.getPoints();
            }
            
            totGroupPoints = totGroupPoints/(allGroups.size()+1);
            //totGroupPoints /=2;
            if(resonancePoints == 0){
                    if(gro.getPoints() < totGroupPoints/2){
                        resonancePoints++;
                    }
                }
                
            
            if(gro.getAlienated()){
                createNewParty(gro, avg);
            }else{
                gro.changeAlienation(resonancePoints==0);
            }
            
            /*if(resonancePoints == 0){
                createNewParty(gro, avg);
            }*/
            
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
        
        allParties.add(newParty);
        newParty.addToSupport(gro);
        
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
            avg = (maxGroup.getMin()+maxGroup.getMax())/2;
            
            if(par.getPolicy()> avg){
                par.changePolicy((par.getPolicy()-avg)/5);
            }else{
                par.changePolicy((avg-par.getPolicy())/5);
            }
            }
        }
        
        
    }
    
    public static void demographicShifts(){
        int changeBy = ra.nextInt(9)+1;
        int nupoints = changeBy/allGroups.size();
        
        for(Group gro : allGroups){
            if(ra.nextBoolean()){
                if(ra.nextBoolean()){
                    changeBy*=-1;
                    
                }else{
                    nupoints*=-1;
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
        List<Party> toRemove = new ArrayList<>();
        for(Party par : allParties){
            if(par.getSeats()<threshold){
                toRemove.add(par);
            }
        }
        
        allParties.removeAll(toRemove);
        toRemove.clear();
    }
    
    public static void checkNoGroups(){
        List<Party> toRemove = new ArrayList<>();
        for(Party par : allParties){
            if(par.supportGroups.size() == 0 && par.getSeats() == 0){
                toRemove.add(par);
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
            if(changedLead && rulingParty== par && oldPerson != null  ){
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
        approvalRating -= (year-startdate)/5;
        
        approvalRating -= auth/10;
        
        
        
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
            if(ra.nextInt(60)> rulingCoalition.stability+ (rulingCoalition.stability/2)){
                snapElec = true;
                if(elecCount >3){
                    elecCount = 3;
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
    
	public static void main(String[] args) throws Exception{
	    Scanner sc = new Scanner(System.in);
		generateGroups();
		generateParties();
		
		
		while(true){
		    
		arrangeGroups();     // (1) Assigns groups to parties based on current party policies
        checkAlienation();   // (2) Identifies unrepresented groups and may create new parties
        checkNoGroups();     // (3) Cleans up parties with no support groups (needed after new parties may be created)
        monthly();
        sortParties();
        System.out.println("DEBUG APPROVAL: "+ approvalRating);
        //System.out.println("DEBUG AUTH: "+ auth);
		
		System.out.println(months[moNum]+ " - "+ year);
		System.out.print("Next election in ");
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
		if(rulingParty != null){
		System.out.println("\nRuling Party: "+ rulingParty.getName());
		
		if(rulingParty.getLeader() != null){
		    System.out.println("Prime Minister: " + rulingParty.getLeader().getName());
		}
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
		    }else{
		        System.out.println("Leader: No Leader");
		    }
		    for(Group gro : par.supportGroups){
		        System.out.print(" - "+gro.getName());
		        totsup += gro.getPoints();
		    }
		    //System.out.println("Support Points: "+ totsup);
		    
		}
		
		//for debug 
		/*System.out.println("\n\n");
		for(Group gro : allGroups){
		    System.out.println(gro.getName()+ " "+ gro.getPoints());
		}*/
		
		System.out.println("\n");
		String put = "";
		
		put = sc.nextLine();
		
		//String put = "debugmode"; // for debug
		if(put.equalsIgnoreCase("archive")){
		    displayArchive();
		    sc.nextLine();
		}
		System.out.print("\033[H\033[2J"); System.out.flush();
		demographicShifts();
		partyShifts();
		
		
		}
		
		
	}
}
