import java.util.*;
public class Main
{
    public static Random ra = new Random();
    public static Scanner sc = new Scanner(System.in);
    
    public static int population = 300;
    public static int healthy = population;
    public static int malnourished = 0;
    public static int starving = 0;
    public static int land = 500;
    public static int grain = 500;
    public static int army = 150;
    public static int loyalty = 100;
    public static int money = 100;
    
    public static int grainPrice = 10; // ranges from 10-20
    public static int landPrice = 15; // ranges from 15-30
    
    public static boolean satisfied = true;
    
    
    public static String[] droughtQuotes = {
        """
        Jeremiah 14:1–3 (KJV)
        "The word of the Lord that came to Jeremiah concerning the dearth.
        Judah mourneth, and the gates thereof languish; they are black unto the ground;
        and the cry of Jerusalem is gone up.
        And their nobles have sent their little ones to the waters: 
        they came to the pits, and found no water; 
        they returned with their vessels empty."
        """,
        
        """
        Amos 4:7–8 (KJV)
        "And also I have withholden the rain from you,
        when there were yet three months to the harvest:
        and I caused it to rain upon one city,
        and caused it not to rain upon another city:
        one piece was rained upon,
        and the piece whereupon it rained not withered.
        So two or three cities wandered unto one city, to drink water;
        but they were not satisfied:
        yet have ye not returned unto me, saith the Lord."
        """,
        
        """
        Joel 1:10–12 (KJV)
        "The field is wasted, the land mourneth;
        for the corn is wasted:
        the new wine is dried up,
        the oil languisheth.
        Be ye ashamed, O ye husbandmen; howl, O ye vinedressers,
        for the wheat and for the barley;
        because the harvest of the field is perished.
        The vine is dried up, and the fig tree languisheth."
        """
    };
    
    public static String[] plagueQuotes = {
    """
    Exodus 9:15 (KJV)
    "For now I will stretch out my hand, that I may smite thee and thy people with pestilence;
    and thou shalt be cut off from the earth."
    """,
    
    """
    Ezekiel 5:17 (KJV)
    "So will I send upon you famine and evil beasts,
    and they shall bereave thee;
    and pestilence and blood shall pass through thee;
    and I will bring the sword upon thee.
    I the Lord have spoken it."
    """,
    
    """
    Revelation 6:8 (KJV)
    "And I looked, and behold a pale horse:
    and his name that sat on him was Death,
    and Hell followed with him.
    And power was given unto them over the fourth part of the earth,
    to kill with sword, and with hunger, and with death,
    and with the beasts of the earth."
    """,
    
    """
    Numbers 14:12 (KJV)
    "I will smite them with the pestilence, and disinherit them,
    and will make of thee a greater nation and mightier than they."
    """
};


public static String[] raiderQuotes = {
    """
    Judges 6:3–5 (KJV)
    "And so it was, when Israel had sown,
    that the Midianites came up, and the Amalekites, and the children of the east,
    even they came up against them;
    And they encamped against them, and destroyed the increase of the earth,
    and left no sustenance for Israel, neither sheep, nor ox, nor ass."
    """,
    
    """
    Jeremiah 5:6 (KJV)
    "Wherefore a lion out of the forest shall slay them,
    and a wolf of the evenings shall spoil them,
    a leopard shall watch over their cities:
    every one that goeth out thence shall be torn in pieces."
    """,
    
    """
    2 Kings 24:2 (KJV)
    "And the Lord sent against him bands of the Chaldees,
    and bands of the Syrians, and bands of the Moabites,
    and bands of the children of Ammon,
    and sent them against Judah to destroy it."
    """
};

public static String[] corruptionQuotes = {
    """
    Micah 3:11 (KJV)
    "The heads thereof judge for reward,
    and the priests thereof teach for hire,
    and the prophets thereof divine for money:
    yet will they lean upon the Lord, and say,
    Is not the Lord among us? none evil can come upon us."
    """,
    
    """
    Isaiah 1:23 (KJV)
    "Thy princes are rebellious, and companions of thieves:
    every one loveth gifts, and followeth after rewards:
    they judge not the fatherless,
    neither doth the cause of the widow come unto them."
    """,
    
    """
    Ezekiel 22:27 (KJV)
    "Her princes in the midst thereof are like wolves ravening the prey,
    to shed blood, and to destroy souls, to get dishonest gain."
    """
};


public static String[] scandalQuotes = {
    """
    Luke 12:2 (KJV)
    "For there is nothing covered, that shall not be revealed;
    neither hid, that shall not be known."
    """,
    
    """
    Proverbs 28:13 (KJV)
    "He that covereth his sins shall not prosper:
    but whoso confesseth and forsaketh them shall have mercy."
    """,
    
    """
    Ecclesiastes 12:14 (KJV)
    "For God shall bring every work into judgment,
    with every secret thing, whether it be good, or whether it be evil."
    """
};


public static String[] ratsQuotes = {
    """
    Joel 1:4 (KJV)
    "That which the palmerworm hath left hath the locust eaten;
    and that which the locust hath left hath the cankerworm eaten;
    and that which the cankerworm hath left hath the caterpiller eaten."
    """,
    
    """
    Leviticus 26:20 (KJV)
    "And your strength shall be spent in vain:
    for your land shall not yield her increase,
    neither shall the trees of the land yield their fruits."
    """,
    
    """
    Amos 5:19 (KJV)
    "As if a man did flee from a lion, and a bear met him;
    or went into the house, and leaned his hand on the wall, and a serpent bit him."
    """
};

public static final String RESET = "\u001B[30m";

public static final String RED = "\u001B[31m";
public static final String GREEN = "\u001B[32m";
public static final String YELLOW = "\u001B[33m";
public static final String BLUE = "\u001B[34m";
public static final String PURPLE = "\u001B[35m";
public static final String CYAN = "\u001B[36m";
public static final String WHITE = "\u001B[37m";
    
    
    
    public static void feedPeople(int grainGiven){
        grain -= grainGiven;
        int underfed = 0;
        int starveToMal = 0;
        /*if(grainGiven>= population){
            if(malnourished>0){
                System.out.println(malnourished+ " people have recovered from malnourishment");
                malnourished = 0;
            }
            
            if(starving> 0){
                System.out.println(starving+ " people have recovered from starvation");
                starveToMal = starving;
                starving = 0;
            }
        }*/
        int remains = grainGiven-healthy;
        
        if(remains > 0){
            if(remains<malnourished){
                healthy += remains;
                malnourished-= remains;
            }else{
                healthy += malnourished-(remains-malnourished);
                remains -= malnourished;
                malnourished =0;
                
                starveToMal += remains;
                if(remains> starving){
                    remains -= starving;
                    grain += remains;
                    remains =0;
                }else{
                    starving -= remains;
                }
            }
        }
        
        
        
        if(healthy> grainGiven){
            underfed = healthy- grainGiven;
        }
        
        if(starving>0){
            population-= starving;
            System.out.println(starving+ " people have died of starvation");
        }
        
        starving = malnourished;
        
        malnourished = underfed+starveToMal;
    }
    
    public static void sellGrain(int sellAmount){
        grain -= sellAmount;
        money += sellAmount*grainPrice;
    }
    
    public static void buyLand(int buyAmount){
        land += buyAmount;
        money -= buyAmount*landPrice;
    }
    
    public static void buySoldiers(int buyAmount){ // the price of soldiers is a constant 10
        army += buyAmount;
        money -= buyAmount*10;
    }
    
    public static void bribeElites(int giveMon){
        loyalty += giveMon/10;
        
        if(loyalty > 110){
            loyalty = 110;
        }
    }
    
    
    
    public static void slowPrint(String text, int delayMillis, String color) {
    System.out.print(color);
    for (char c : text.toCharArray()) {
        System.out.print(c);
        try {
            Thread.sleep(delayMillis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    System.out.println(RESET); // Reset color after print
}
    
    
    
    
    public static int maxdisasters = 2;
    
    public static void updates(){
        
        int divisor = 2;// lower means harder
        int disasters = 0;
        
        
        grainPrice = ra.nextInt(10)+10;
        landPrice = ra.nextInt(15)+15;
        
        int newpops = (healthy/5)+ (malnourished/10) + (starving/20);
        System.out.println("Your population increased by "+newpops);
        population+= newpops;
        
        if(ra.nextBoolean() && disasters<maxdisasters){ // drought
            int destroyedLand = ra.nextInt((land/divisor)+1);
            //System.out.println("A drought has destroyed "+ destroyedLand+ " acres of arable land");
            
            slowPrint(droughtQuotes[ra.nextInt(droughtQuotes.length)], 50,YELLOW);
            //System.out.println(droughtQuotes[ra.nextInt(droughtQuotes.length)]);
            land -= destroyedLand;
            disasters++;
            maxdisasters--;
        }else{
            maxdisasters++;
        }
        
        if(ra.nextBoolean() && disasters<maxdisasters){ // plague
            int dead = ra.nextInt((population/divisor)+1);
            //System.out.println("A plague has killed "+ dead+ " people in your land");
            slowPrint(plagueQuotes[ra.nextInt(plagueQuotes.length)], 50,PURPLE);
            population -= dead;
            if(dead> healthy){
                malnourished -= dead-healthy;
                if(dead-healthy > malnourished){
                    starving -= (dead-healthy)-malnourished;
                }
            }
            disasters++;
            maxdisasters--;
        }else{
            maxdisasters++;
        }
        
        if((population>= 2000 && ra.nextBoolean() && disasters<maxdisasters) || !satisfied){
            int raiders = ra.nextInt(population/3);
            if(raiders > army|| !satisfied){
                population-= ra.nextInt((population/divisor)+1);
                grain-= ra.nextInt((grain/divisor)+1);
                money -= ra.nextInt((money/divisor)+1);
                army = 0;
                
                if(!satisfied){
                    population-= ra.nextInt((population/divisor)+1);
                grain-= ra.nextInt((grain/divisor)+1);
                money -= ra.nextInt((money/divisor)+1);
                
                satisfied = true;
                }
                
                //System.out.println("Raiders have ravaged your kingdom!");
                //System.out.println(droughtQuotes[ra.nextInt(raiderQuotes.length)]);
                slowPrint(raiderQuotes[ra.nextInt(raiderQuotes.length)], 50,RED);
            } else{
                System.out.println("Some raiders tried and failed to invade your kingdom!");
                army -= raiders;
            }
            disasters++;
            maxdisasters--;
        }else{
            maxdisasters++;
        }
        
        if(ra.nextBoolean() && disasters<maxdisasters){// corruption
            int stolen = ra.nextInt((money/divisor)+1);
            //System.out.println("A corrupt member of your government has stolen "+ stolen+ " PZH");
            //System.out.println(droughtQuotes[ra.nextInt(corruptionQuotes.length)]);
            slowPrint(corruptionQuotes[ra.nextInt(corruptionQuotes.length)], 50,CYAN);
            money -= stolen;
            disasters++;
            maxdisasters--;
        }else{
            maxdisasters++;
            
        }
        
        if(ra.nextBoolean() && disasters<maxdisasters){ // scandal
            int lost = ra.nextInt((loyalty/divisor)+1);
            //System.out.println("A scandal has errupted in your government!");
            //System.out.println(droughtQuotes[ra.nextInt(scandalQuotes.length)]);
            slowPrint(scandalQuotes[ra.nextInt(scandalQuotes.length)], 50,BLUE);
            loyalty -= lost;
            disasters++;
            maxdisasters--;
        }else{
            maxdisasters++;
        }
        
        /*if(ra.nextBoolean()){ // defections
            int defected = ra.nextInt((army/4)+1);
            System.out.println(defected+ " soldiers defected from our army today!");
        }*/
        
        army = population/4;
        
        int newGrain = 0;
        
        healthy = population- (malnourished+starving);
        if(healthy<0){
            healthy = 0;
        }
        int potential = (int)(healthy*1.5)+ (malnourished/2)+ (starving/4);
        int maxyield = land;
        
        newGrain = Math.min(potential, maxyield);
        
        System.out.println("\nYour people harvested "+ newGrain+ " bushels of grain\n");
        
        if(loyalty >90){
            int royalGrain = 500/grainPrice;
            System.out.println("\nThe Nobility has bought you "+ royalGrain+ " bushels of grain!\n");
            newGrain += royalGrain;
        }
        
        if(ra.nextBoolean()&& disasters<maxdisasters){ // rats
            int lost = ra.nextInt((grain/divisor)+1);
            //System.out.println("Rats have eaten "+ lost + " bushels of grain!");
            //System.out.println(droughtQuotes[ra.nextInt(ratsQuotes.length)]);
            slowPrint(ratsQuotes[ra.nextInt(ratsQuotes.length)], 50,GREEN);
            newGrain -= lost;
            disasters++;
            maxdisasters--;
        }else{
            maxdisasters++;
        }
        grain += newGrain;
        
    }
    
    public static boolean checkLossConditions(){
        
        if(population == 0){
            System.out.println("Everyone is dead");
            return true;
        }
        
        if(starving>= population/2){
            System.out.println("The people are tired of starving and have risen up to overthrow you!");
            return true;
        }
        
        if(loyalty < 65 && ra.nextInt(65) > loyalty){
            System.out.println("The Nobility no longer favors you and ousts you in a palace coup!");
            return true;
        }
        
        if(population/4> army){
            System.out.println("You have too little soldiers to enforce your will on the people. Your kingdom has fallen into anarchy!");
            return true;
        }
        return false;
    }
    
    public static void kingdomStats(){
        healthy = population- (malnourished+starving);
        if(healthy<0){
            healthy = 0;
        }
        System.out.println("Population: "+ population);
        System.out.println("    Healthy: "+ healthy);
        System.out.println("    Malnourished: "+ malnourished);
        System.out.println("    Starving: "+ starving);
        System.out.println("Arable land (acres): "+ land);
        System.out.println("We have "+ money+ " PZH");
        
        
        
        System.out.print("We have");
        if(grain > population){
            System.out.print(" plenty of grain");
        }else if(grain <= population&& grain > population/2){
            System.out.print(" adequate amounts of grain");
        } else{
            System.out.print(" dangerously low amounts of grain");
        }
        System.out.println(" ("+ grain+ " Bushels)");
        
        System.out.print("We have a");
        if(army > population/2){
            System.out.print(" massive army");
        }else if( army <= population/2 && army> population/3){
            System.out.print("n adequate army");
        }else if (army <= population/3){
            System.out.print(" small army");
        }
        System.out.println(" ("+ army+ " men)");
        
        System.out.print("The Nobility is");
        if(loyalty >90){
            System.out.println(" loyal");
        }else if(loyalty <= 90 && loyalty > 65){
            System.out.println(" satisfied");
        } else if(loyalty < 65){
            System.out.println(" disloyal");
        }
    }
    
    
    
    public static void game()throws Exception{
        int uput = 0;
        while(true){
            kingdomStats();
            
            boolean passed = false;
            do{
            System.out.println("How much grain will you feed the people?");
            uput = sc.nextInt();
            if(uput > grain){
                System.out.println("I'm afraid you do not have that much amount of grain");
            }else{
                feedPeople(uput);
                passed = true;
            }
            }while(!passed);
            System.out.print("\033[H\033[2J");
        System.out.flush();
        kingdomStats();
            passed = false;
            do{
                System.out.println("How much grain would you like to sell? ("+ grainPrice+" PZH per bushels of grain)");
                uput = sc.nextInt();
                if(uput > grain){
                    System.out.println("I'm afraid you do not have that much amount of grain");
                }else{
                    sellGrain(uput);
                    passed = true;
                }
            }while(!passed);
            
            System.out.print("\033[H\033[2J");
        System.out.flush();
        kingdomStats();
            passed = false;
            do{
                System.out.println("How much acres of land would you like to buy? ("+ landPrice+" PZH per acre of land)");
                uput = sc.nextInt();
                if(uput*landPrice > money){
                    System.out.println("I'm afraid you do not have that much amount of money");
                }else{
                    buyLand(uput);
                    passed = true;
                }
            }while(!passed);
            System.out.print("\033[H\033[2J");
        System.out.flush();
        kingdomStats();
            passed = false;
            do{
                System.out.println("How much soldiers do you want to raise this year? (10 PZH per soldier)");
                uput = sc.nextInt();
                if(uput*10 > money){
                    System.out.println("I'm afraid you do not have that much amount of grain");
                }else{
                    buySoldiers(uput);
                    passed = true;
                }
            }while(!passed);
            System.out.print("\033[H\033[2J");
        System.out.flush();
        kingdomStats();
            passed = false;
            do{
                System.out.println("How much money would you like to donate to the Nobility?");
                uput = sc.nextInt();
                if(uput > money){
                    System.out.println("I'm afraid you do not have that much amount of money");
                }else{
                    bribeElites(uput);
                    passed = true;
                }
            }while(!passed);
            
            if(checkLossConditions()){
                System.exit(0);
            }
            System.out.print("\033[H\033[2J");
        System.out.flush();
        
        
        if(money >= 10000){
            satisfied = false;
            kingdomStats();
            passed = false;
            do{
                System.out.println("A larger kingdom sees your weath and demands tribute");
                uput = sc.nextInt();
                if(uput > money){
                    System.out.println("I'm afraid you do not have that much amount of money");
                }else{
                    if(ra.nextInt(money)<uput){
                        System.out.println("They were satisfied by the tribute");
                        satisfied = true;
                    } else{
                        System.out.println("You shall pay for your insolence");
                        satisfied = false;
                    }
                    Thread.sleep(1000);
                    
                    passed = true;
                }
            }while(!passed);
            
            if(checkLossConditions()){
                System.exit(0);
            }
            System.out.print("\033[H\033[2J");
        System.out.flush();
        }
        
        
        fillWhiteScreen();
            updates();
            
            
        }
    }
    
    public static final String BLACK_TEXT = "\u001B[30m";
public static final String WHITE_BG = "\u001B[47m";
public static final String CLEAR_SCREEN = "\u001B[2J\u001B[H";

// Call this to "whiten" the screen
public static void fillWhiteScreen() {
    System.out.print(CLEAR_SCREEN); // Clear screen and move cursor to top
    System.out.print(WHITE_BG + BLACK_TEXT);
    
    for (int i = 0; i < 40; i++) { // adjust to terminal height
        System.out.println(" ".repeat(100)); // adjust to terminal width
    }

    System.out.print("\u001B[H"); // Move cursor to top-left
}
    
	public static void main(String[] args) throws Exception {
	    fillWhiteScreen();
		System.out.println("OLDKINGDOM\npress Enter to start");
		sc.nextLine();
		fillWhiteScreen();
		game();
	}
}
