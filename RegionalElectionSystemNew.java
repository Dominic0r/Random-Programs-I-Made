import java.util.*;
public class Main
{
    public static Random ra = new Random();
    public static Scanner sc = new Scanner(System.in);
    
    public static int month = 1;
    public static int year = 1948;
    
    public static int seatUpCountdown = 0;// every two years
    public static int genElecCountdown = 6; // default is 60
    
    public static int totLeft = 0, totRight=0;
    
    public static int currentRulingCoalition =0; // 1- left, 2- right
    
    static class Nation{
        int militaryLoyalty;
        int economy;
    }
    
    static class Region{
        int population;
        int seats;
        String name;
        int left =0, right = 0;
        
        Region(int pop, int seat, String n){
            this.population = pop;
            this.seats = seat;
            this.name = n;
            this.left = 25 + ra.nextInt(30);
            this.right = 100-left;
            
        }
        
        void updatePolitics(){
            int cnum = ra.nextInt(20);
            if(cnum >10){
                if(cnum >15){
                    this.left += ra.nextInt(10);
                }else{
                    this.left += ra.nextInt(5);
                }
            }else{
                if(cnum>5){
                    this.left -= ra.nextInt(5);
                }else{
                    this.left -= ra.nextInt(10);
                }
            }
            
            if(this.left <25){
                this.left = 25;
            } else if(this.left >75){
                this.left = 75;
            }
            
            this.right = 100-left;
        }
        
        void seatReset(){
            this.seats = 0;
        }
        
        void changeSeats(int nseat){
            this.seats = nseat;
        }
        
        
        void changePopulation(){
            int change = ra.nextInt(((this.population*3)/100)+1);
            if(ra.nextInt(10)>5){
                this.population += change;
            }else{
                this.population -= (change/10)+1;
            }
        }
        
        void displayDat(){
            System.out.println(this.name);
            System.out.println("Seats:"+this.seats);
            System.out.println("Ppopulation: "+this.population);
        }
        
        public int getPop(){
            return this.population;
        }
        
        public int getSeat(){
            return this.seats;
        }
        
        public int getLeft(){
            return this.left;
        }
        
        public int getRight(){
            return this.right;
        }
        
        public String getName(){
            return this.name;
        }
    }
    
    
    
    
    
    public static int[][] regdat= {
        {150000,75000,90000,32000,14000},
        {0,0,0,0,0}
    };
    
    public static Region[] reg = new Region[5];
    static{
        reg[0] = new Region(regdat[0][0],regdat[1][0],"Aber Metro Area");
     reg[1] = new Region(regdat[0][1],regdat[1][1],"Carpenter");
     reg[2] = new Region(regdat[0][2],regdat[1][2],"New Bethlehem");
     reg[3] = new Region(regdat[0][3],regdat[1][3],"De Maine");
     reg[4] = new Region(regdat[0][4],regdat[1][4],"Enson");
    }
    
    
    
    
    
    static class Party{
        String name;
        int seats;
        int ideology; // 1- left, 2- right
        int support; // 0-100
        int age;
        int[] wonInRegions = new int[5];
        
        boolean isMajor;
        
        public Party(String name, int support, int ideology){
            this.name = name;
            this.seats = 0;
            this.ideology = ideology;
            this.support = support;
            this.age = 0;
            for(int i=0; i<this.wonInRegions.length;i++){
                this.wonInRegions[i] = 0;
            }
            this.isMajor = false;
        }
        
        void checkMajor(){
            isMajor = false;
            if(this.seats >= Math.round((totseats*15)/100)){
                isMajor = true;
            }
        }
        
        public boolean getMajor(){
            return this.isMajor;
        }
        
        void clearWon(){
            for(int i=0; i<this.wonInRegions.length;i++){
                this.wonInRegions[i] = 0;
            }
        }
        
        void upAge(){
            this.age++;
        }
        
        void addRegSeat(int gain, int region){
            this.wonInRegions[region] = 0;
            this.wonInRegions[region] = gain;
        }
        
        void showRegWons(int region){
            String[] ideoStr = {"Leftist","Rightist"};
            System.out.println(this.name+ "("+ ideoStr[this.ideology-1]+ "): " + this.wonInRegions[region]+ " Seats");
        }
        
        int getSpecificSeats(int region){
            return this.wonInRegions[region];
        }
        
        int gAge(){
            return this.age;
        }
        
        int gSeat(){
            return this.seats;
        }
        
        int gIdeo(){
            return this.ideology;
        }
        
        int gSup(){
            return this.support;
        }
        
        void seatClear(){
            this.seats = 0;
        }
        
        void addSeat(int newSeats){
            this.seats += newSeats;
        }
        
        void upSupport(){
            int rng = ra.nextInt(20);
            if(rng>10){
                if(rng >15){
                    this.support += 2;
                }else{
                    this.support += 1;
                }
            }else{
                if(rng>5){
                    this.support-=1;
                }else{
                    this.support-=2;
                }
            }
            
            if(this.support<1){
                this.support = 1;
            }
            if(this.support>100){
                this.support = 100;
            }
        }
        
        
        
        @Override
        public String toString(){
            String[] ideoStr = {"Leftist","Rightist"};
            //return name+ " ("+ideoStr[this.ideology-1]+")" ;
            return name+ " ("+ideoStr[this.ideology-1]+")" ;
        }
    }
    
    public static String[] leftistParties = {"People's Alliance","Progressive Union","Socialist Front","Labor Renewal Party","Democratic Workers' League","Popular Movement for Justice","Red Horizon Party","United Left Bloc","New Dawn Coalition","Equal Future Party","People First Movement","Social Justice Assembly","Labor Unity Front","Workers’ Democratic Congress","Union for Progress","Communal Path Party","Social Freedom Alliance","The Egalitarian Front","Progressive Vanguard","Peasant and Workers’ Union","New Society Initiative","Red Star Movement","Green Left Party","Revolutionary Unity League","Democratic Social Collective","People's Voice Party","Community Solidarity Front","Left for the Nation","Justice and Welfare Union","United Social Front","Democracy for All Party","Common Future Movement","Solidarity Bloc","The Social Option","People Over Profit Party","Fair Future Party","United Labor Party","Equality First League","Reform and Redistribution Party","Progress for the People","People’s Democratic Alliance","Grassroots Justice League","The New Commons Party","Radical Labor Party","Democratic Green Coalition","Citizen's Unity Party","United Proletarian Front","Red Banner League","Progressive Federation","Popular Social Congress"};
    public static String[] rightistParties = {"National Renewal Front","Patriot's League","Unity and Order Party","The Conservative Alliance","Liberty and Heritage Union","Homeland First Party","Sovereign Path Movement","Guardians of the Nation","Traditional Values Party","Nationalist Union","Freedom and Faith Party","Rightward Front","Heritage Coalition","Strength and Prosperity Party","Loyalist Movement","Republican Front","True Order Party","Front for Stability","Faith and Country Party","The Homeland Alliance","Patriotic Renewal Bloc","Sovereignty and Honor Party","National Justice Party","Family and Nation Party","Rising Flag League","People’s Conservatism Party","The Right Path Coalition","Citizens for Order","Tradition and Liberty Party","Security and Progress Front","Faithful Nation Party","Civic Order League","Guardians of Legacy","Conservative Movement for Unity","Order and Discipline Party","Christian National Party","Flag and Fatherland Front","United Patriots Bloc","Traditional Republican Party","The Restoration League","Front for National Identity","Civic National Alliance","Free Market Bloc","The Law and Liberty Party","Right for the People","Pride and Country Movement","Great Nation Party","Unity of the Land Party","Defenders of the Republic","Iron Banner Party"};

    
     public static ArrayList<Party> parties = new ArrayList<>();
     public static ArrayList<Party> leftCoalition = new ArrayList<>();
     public static ArrayList<Party> rightCoalition = new ArrayList<>();
     
     public static void genParty(){
         int genIdo = ra.nextInt(2)+1;
         if(ra.nextInt(5)>leftCoalition.size()){
             genIdo = 1;
         }
         if(ra.nextInt(5)>rightCoalition.size()){
             genIdo=2;
         }
         
         String genname = "";
         int gensupport = 15;
         if(genIdo==1){
             genname = leftistParties[ra.nextInt(leftistParties.length)];
         }else{
             genname = rightistParties[ra.nextInt(rightistParties.length)];
         }
         gensupport+= ra.nextInt(45);
         parties.add(new Party(genname, gensupport, genIdo));
         
         //System.out.println("Ideology (1- left |  2- right): "+ genIdo);
         //sc.nextLine();
     }
     
     
     
    
    
    
    
    
    
    
    public static int totseats = 36;
    public static int totpop;
    
    public static void giveseats(){
        int dx = 0;
        
        for(int i=0;i<reg.length;i++){
            reg[i].seatReset();
            regdat[1][i] = 0;
            regdat[0][i] = reg[i].getPop();
        }
        
        int[] ndat = regdat[0].clone();
        int[] wco = new int[regdat[0].length];
        int wnr = 6;
        int winseat = 0;
        for(int i=0; i<totseats;i++){
            dx=0;
            for(int b : ndat){
                if(b>winseat){
                    wnr = dx;
                    winseat =b;
                }
                dx++;
            }
            
            regdat[1][wnr]++;
            wco[wnr]++;
            ndat[wnr] = regdat[0][wnr]/(wco[wnr]+1);
            
            wnr = 6;
            winseat = 0;
        }
        dx = 0;
        for(int i=0; i<reg.length;i++){
            reg[i].changeSeats(regdat[1][i]);
        }
    }
    
    public static void getTotalPop(){
        totpop = 0;
        for(Region r : reg){totpop+=r.getPop();}
        
    }
    
    public static void newTotalSeat(){
        int seatCap = (int)Math.sqrt(totpop)/15;
        
        totseats = 0;
        totseats = totpop/10000;
        
        if(totseats>seatCap){
            totseats = seatCap;
        }
    }
    
    public static void polUp(){
        for(Region r: reg){
            if(ra.nextInt(10)>5){
            r.updatePolitics();
            }
        }
    }
   
    public static void monthly(){
        for(Region r : reg){r.changePopulation();r.updatePolitics();}
        getTotalPop();
        for(Party p : parties){
            if(ra.nextInt(10)>5){
            p.upSupport();
            }
            p.upAge();
        }
        
        remParty();
        if(ra.nextInt(10)>parties.size()){
            genParty();
        }
        
        
        
        month++;
        genElecCountdown--;
        if(genElecCountdown ==0){
                newTotalSeat();
                giveseats();
                genElec();
                getTotalLeft();
                getTotalRight();
                genElecCountdown =60;
            }
            
        if(month >11){
            month =0;
            year++;
            
            polUp();
            
        }
    }
    
    public static void dispAll(){
        for(Region r : reg){
		    r.displayDat();
		    System.out.println("\n");
		}
    }
    
    public static void sortPartiesByIdeology(){
        leftCoalition.clear();
        rightCoalition.clear();
        for(Party p : parties){
            if(p.gIdeo() == 1){
                leftCoalition.add(p);
            }else{
                rightCoalition.add(p);
            }
        }
    }
    
    public static void remParty(){
         Iterator<Party> iter = parties.iterator();
         while(iter.hasNext()){
             Party p = iter.next();
             if(p.gSup() < 25 && p.gSeat()==0 && ra.nextInt(100)<p.gAge()){
                 iter.remove();
             }
         }
         sortPartiesByIdeology();
     }
   
   
   public static void genElec(){
       for(Party p : parties){
           p.clearWon();
       }
       
       
       int rseat = 0, lefto =0, righto=0;
       
       int[] psup = new int[parties.size()];
       int[] pclone = new int[parties.size()];
       int[] seatgain = new int[parties.size()];
       
       int[] regSeatGain = new int[parties.size()];
       
       int wnr = -1, wvt = 0;
       
       int idx = 0;
       
       
       
       for(int i=0; i<reg.length; i++){
           for(int p=0; p<regSeatGain.length;p++){
           regSeatGain[p] = 0;
       }
           
           rseat = reg[i].getSeat();
           lefto = (reg[i].getLeft()+1)/leftCoalition.size();
           righto = (reg[i].getRight()+1)/rightCoalition.size();
           
           idx = 0;
           for(Party p : parties){
               p.checkMajor();
               p.seatClear();
               if(p.gIdeo() == 1){ // left
                   psup[idx] = (p.gSup()*lefto)*rseat;
               } else{ // right
                   psup[idx] = (p.gSup()*righto)*rseat;
               }
               
               if(p.getMajor()){
                   psup[idx]= psup[idx]*10;
               }
               
               idx++;
           }
           
           pclone= psup.clone();
           
           for(int b=0; b<rseat; b++){
               wnr = -1;
               wvt = 0;    
               //for(int x:pclone){ // for debug purposes
               //    System.out.println(x);
               //}
               
               for(int x = 0; x<parties.size();x++){
                   if(pclone[x] >=wvt){
                       wnr = x;
                       wvt = pclone[x];
                   }
               }
               
               seatgain[wnr]++;
               regSeatGain[wnr]++;
               pclone[wnr] = psup[wnr]/(seatgain[wnr]+1);
               
           }
           
           idx = 0;
           for(Party p : parties){
               p.addSeat(seatgain[idx]);
               p.addRegSeat(regSeatGain[idx], i);
               idx++;
           }
           
       }
   }
    
    public static void getTotalLeft(){
        totLeft=0;
        for(Party p : leftCoalition){
            totLeft+=p.gSeat();
        }
    }
    
    public static void getTotalRight(){
        totRight=0;
        for(Party p : rightCoalition){
            totRight+=p.gSeat();
        }
    }
    
    public static void findMajorityCoalition(){
        int nextRuling = 0;
        if(totLeft > totRight){
            nextRuling = 1;
        }else if(totRight > totLeft){
            nextRuling = 2;
        }else if(totLeft== totRight){
            if(currentRulingCoalition == 1){
                nextRuling=2;
            }else{
                nextRuling=1;
            }
        }
        
        currentRulingCoalition = nextRuling;
    }
    
    public static void allParties(){
        sortPartiesByIdeology();
        System.out.println("Leftist Parties===== " + totLeft+ " Seats");
        for(Party p : leftCoalition){
            if(p.gSeat() >1){
            System.out.println(p);
            System.out.println("Seats: " + p.gSeat());
            }
        }
        System.out.println("\nRightist Parties===== "+ totRight + " Seats");
        for(Party p : rightCoalition){
            if(p.gSeat() >1){
            System.out.println(p);
            System.out.println("Seats: " + p.gSeat());
            }
        }
    }
    
    public static void byRegion(){
        for(int i=0; i<reg.length;i++){
            System.out.println(reg[i].getName()+"#################");
            System.out.println("Leftist Parties===== ");
        for(Party p : leftCoalition){
            if(p.getSpecificSeats(i)>0){
            p.showRegWons(i);
            }
        }
        System.out.println("\nRightist Parties===== ");
        for(Party p : rightCoalition){
            if(p.getSpecificSeats(i)>0){
            p.showRegWons(i);
            }
        }
            
            
            
            System.out.println("\n");
        }
    }
    
    
    
    public static String[] months = {"January","February","March","April","May","June","July","August","September","October","November","December"};
    
	public static void main(String[] args) throws Exception{
	    String uput="";
		giveseats();
		for(int i=0; i<regdat[0].length;i++){
		    totpop+= regdat[0][i];
		}
		genParty();
		Thread.sleep(100);
		genParty();
		Thread.sleep(100);
		genParty();
		Thread.sleep(100);
		genParty();
		Thread.sleep(100);
		genParty();
		
		while(true){
		    System.out.println("\n=========================");
		    
		System.out.println(months[month]+ ", "+ year);
		System.out.println("Next Election in " + genElecCountdown+ " Months");
		System.out.println("Total Population: " + totpop);
		System.out.println("Total Seats: " + totseats);
		System.out.println("\n==========");
		System.out.println("1- Display Regions");
		System.out.println("2- Display Parties");
		System.out.println("3- Display Seats By Region");
		//if(year == 2025){
		
		//}
		boolean loper = false;
		do{
		    uput = "";
		    uput = sc.nextLine();
		if(!uput.equals("")){
		if(uput.equals("1")){
		    dispAll();
		    
		}else if(uput.equals("2")){
		    allParties();
		}else if(uput.equals("3")){
		    byRegion();
		}
		
		}else{
		    loper = true;
		}
		
		}while(!loper);
		Thread.sleep(100);
		monthly();
		System.out.print("\033[H\033[2J");  
        System.out.flush();  
		}
	}
}
