import java.util.*;
import java.text.DecimalFormat;

public class Main
{
    public static int victory = 0;
    public static int senate = 21;
    public static int opposition = 0;
    
    public static int support = 0;
    
    public static int curEvID = 0;
    
    public static int policy = 0; // max 20, min 0 policy implementation
    
    public static double totalvoters = 30322884d;
    
    public static int yourpercent = 0;
    public static int oppercent = 0;
    public static int otherpercent = 0;
    
    public static float yourpercent_final = 0;
    public static float oppercent_final = 0;
    public static float otherpercent_final = 0;
    public static String mainOpponent = "";
    
    public static int opscore=0, yourscore=0, otherscore=0;
    
    public static boolean isElection = false;
    
    public static void detWin(int ypercentf,int oppercentf,int otpercentf){
        if(ypercentf > oppercentf && ypercentf >otpercentf){
            System.out.println("Gloria Macapagal Arroyo Has Won!");
        } else if (oppercentf > ypercentf && oppercentf > otpercentf){
            System.out.println(mainOpponent+ " Has Won!");
        }
    }
    
    public static void calcPercent(int yscore, int opposcore, int otscore){
        DecimalFormat df = new DecimalFormat("#");
        Random rand = new Random();
df.setGroupingUsed(true); // Optional: Adds commas for thousands grouping
df.setMaximumFractionDigits(0); // Ensures no decimal places

        
        if(isElection){
            yourscore += (rand.nextInt(3))*5;
            opscore += (rand.nextInt(3))*5;
            otherscore += (rand.nextInt(3))*5;
        }

        
        int ycopy = yscore*10000;
        int ocopy = opposcore*10000;
        int otcopy = otscore*10000;
        yourpercent = 0;
        oppercent = 0;
        otherpercent = 0;
        for(int i=0; i<10000; i++){
            if(ycopy >= ocopy && ycopy >= otcopy){
                yourpercent++;
                ycopy = (yscore*10000)/yourpercent;
            }else if (ocopy > ycopy && ocopy >= otcopy){
                oppercent++;
                ocopy = (opposcore*10000)/oppercent;
            } else if (otcopy > ycopy && otcopy > ocopy){
                otherpercent++;
                otcopy = (otscore*10000)/otherpercent;
            }
            
            /*System.out.println("yourpercent: "+yourpercent);
            System.out.println("oppercent: "+oppercent);
            System.out.println("otherpercent: "+otherpercent);
            System.out.println("ycopy: "+ycopy);
            System.out.println("ocopy: "+ocopy);
            System.out.println("otcopy: "+otcopy);*/
        }
        yourpercent_final = (float) yourpercent/100;
        oppercent_final = (float) oppercent/100;
        otherpercent_final = (float) otherpercent/100;
        
        double yourvotes = Math.round((totalvoters*yourpercent)/10000);
        double oppovotes = Math.round((totalvoters*oppercent)/10000);
        double othervotes = Math.round((totalvoters*otherpercent)/10000);
        System.out.println("==========================================================================================");
        System.out.println("Estimated results: ");
        System.out.println("Gloria Macapagal Arroyo: " + df.format(yourvotes) +" ("+ yourpercent_final+"%)");
        System.out.println(mainOpponent+": " + df.format(oppovotes)+ " ("+ oppercent_final+"%)");
        System.out.println("Other: " + df.format(othervotes)+ " ("+ otherpercent_final+"%)");
    }
    
    
    public static void checkLimits(){
        Random ra = new Random();
        if(victory > 9){
            victory = 9;
        }
        if(victory < -11) {
            victory = -11;
        }
        
        if(senate > 24){
            senate = 24;
        }
        if(senate <0){
            senate = 0;
        }
        
        if(opposition > 8){
            support -=1;
        }
        
        if(opposition > 16){
            opposition = 16;
        }
        if(opposition < -7){
            opposition = -7;
        }
        
        if(policy >20){
            policy = 20;
        }
        if(policy < 0){
            policy = 0;
        }
        support+= ra.nextInt(10)- ra.nextInt(10);
        if(support>50){
            support = 50;
        }
        if(support <-50){
            support = -50;
        }
        
        System.out.println("\nVictory Points: " + victory);
        System.out.println("Opposition Level: " + opposition);
        System.out.println("Policy Implementation: " + policy);
        System.out.println("Popularity: " + (support+50));
        System.out.println("Senate Support: " + senate+"\n");
    }
    
	public static void main(String[] args) {
	    Random ra = new Random();
	    Scanner sc = new Scanner(System.in);
		int uput = 0;
		// ev 0
		System.out.println("We've done it. We've succesfully ousted a corrupt government and installed ourselves in power. You step up on the stage and prepare to address the massive crowds that have gathered in your support");
        System.out.println("It's so important that I make a unifying speech. After what we had just gone through, let's try and unite the nation");
        System.out.println("I'd like to thank those in congress and those in Estrada's cabinet who have been so brave to step out and come to my support.");
        System.out.println("Joseph Estrada led a corrupt government, one that betrayed the trust of the people. I promise to restore integrity to Malacañan");
        switch(sc.nextInt()){
            case 1: victory++;
            support +=5;
                break;
            case 2: senate++;
            opposition+=1;
            support +=3;
                break;
            default: opposition+=2;
            support +=10;
        }
        checkLimits();
        
        // ev 1
        System.out.println("In the 1990s, the government contracted IMPSA to rehabilitate several places in the country, giving them a massive sum of money. Years later, and barely anything has been done.");
        System.out.println("There was obviously some corruption involved here. What we can do now is to simply promise to fix corruption and avoid issue slike this in the future");
        System.out.println("Let's keep this under wraps. Distract the media.");
        System.out.println("It was obviously the fault of the past two administrations who invited IMPSA to our country");
        switch(sc.nextInt()){
            case 1: senate -= 3;
            support +=1;
                break;
            case 2: opposition += 1;
            support -=3;
                break;
            default: 
            victory-=1;
            opposition +=2;
            senate-=2;
            support -=5;
                    
        }
        checkLimits();
        
        System.out.println("\nMILF Peace Talks");
        System.out.println("The Moro Islamic Liberation Front, an islamist separatist insurgent group in Mindanao have agreed to peace talks with the government. How should we conduct ourselves in the peace talks?");
        System.out.println("Send in military representatives, don't make any concessions");
        System.out.println("I'll go there myself with an entourage of diplomats and senators to make a balanced deal");
        System.out.println("Delegate the talks to local representatives of the area and experts on the matter, be pragmatic with the concessions");
        switch(sc.nextInt()){
            case 1: victory -=1;
            opposition+=2;
            senate -=1;
                break;
            case 2: victory +=1;
            opposition +=3;
            senate +=2;
            support -=3;
                break;
            default: 
            victory +=1;
            senate -= 3;
            support += 5;
        }
        checkLimits();
        
        // ev 2
        System.out.println("Supporters of Joseph Estrada have rallied to attempt to restore him to Malacañan");
        System.out.println("This is a serious threat that might inspire future uprisings. Let's make an example by cracking down hard.");
        System.out.println("Responding harshly will just make us look bad. Let's increase security and deploy a couple units to the scene, but only to keep order.");
        System.out.println("Let's allow them to protest, no additional security or anything. Just keep the cameras pointed at them.");
        switch(sc.nextInt()){
            case 1: victory -=1;
            opposition +=2;
            support -=5;
                break;
            case 2: victory+=1;
            opposition+=1;
                break;
            default: victory+=2;
            support +=3;
            senate -=2;
                    
        }
        checkLimits();
        // ev 3
        System.out.println("\nThe midterms are coming in a few weeks. Who should we campaign with?");
        System.out.println("Let's campaign with liberal figures like Francis Pangilinan, really capture that liberal vote");
        System.out.println("Let's campaign with our political allies like Villar, gain favor in the senate");
        switch(sc.nextInt()){
            case 1: opposition+=1;
            senate-=1;
            support +=2;
                break;
            default: 
            senate+=1;
                    
        }
        // support max 16, min -7
        checkLimits();
        senate -= 8;
        switch(opposition){
            case 0: senate += 10;
                break;
            case 1: senate +=6;
                break;
            case 2: senate +=2;
                break;
            case 3:
                break;
            case 4: senate -= 2;
                break;
            default: senate -= 6;
        }
        senate += support/2;
        checkLimits();
        // ev 4
        System.out.println("\nDos Palmas Kidnappings");
        System.out.println("20 local and foreign people in the Dos Palmas hotel were kidnapped by Abu Sayyaf");
        System.out.println("Respond with considerable military action");
        System.out.println("Attempt to communicate with Abu Sayyaf and negotiate a deal");
        switch(sc.nextInt()){
            case 1: victory -=2;
            opposition+=1;
            senate-=2;
            support +=3;
                break;
            default: 
            victory-=1;
            opposition+=2;
            support -=3;
        }
        checkLimits();
        if(senate >=18){
            policy+=2;
            opposition -=1;
        } else if (senate >=12 && senate < 18){
            policy+=1;
        } else if (senate >=6 && senate < 12){
            policy+=-1;
            opposition+=1;
        } else if (senate < 6){
            policy+=-2;
            opposition+=2;
        }
        checkLimits();
        // ev 5
        System.out.println("\nCombatting Communist Insurgency");
        System.out.println("The NPA, despite being heavily weakened in the past few years, are still waging guerilla war against the government.");
        System.out.println("Crush the insurgency using military force via Oplan Bantay Laya");
        System.out.println("Reach out to the civilian population via outreach and aid programs");
        System.out.println("Attempt to negotiate a ceasefire with the NPA");
        switch(sc.nextInt()){
            case 1: victory -=1;
            opposition+=2;
            support -=3;
                break;
            case 2: victory+=1;
            opposition-=1;
            support +=5;
                break;
            default: 
            victory-=2;
            opposition+=1;
            senate-=5;
            support -=5;
                    
        }
        checkLimits();
        // ev 6
        System.out.println("\nLast year, the United States was attacked by Islamist terrorists in what is now being called the 9/11 Attacks. While we've already made a condolence message last year, we can take advantage of this by promoting our local counter-terrorism campaign to the public");
        System.out.println("It's best not to use this to shore up support");
        System.out.println("Write me a quick speech talking about the dangers of terrorism and how we're combatting it");
        switch(sc.nextInt()){
            case 1:
                
                break;
            default: 
            opposition+=1;
            support +=2;
        }
        checkLimits();
        // ev 7
        System.out.println("\nThe annual Balikatan exercises have just concluded, and our troops have gotten a lot of experience and training from the US. We could really put this experience to use in combatting Abu Sayyaf");
        System.out.println("Let's focus our efforts on directly combatting these terrorists");
        System.out.println("How about we try to infiltrate this organization and kill it from within?");
        System.out.println("No, let's try to negotiate a ceasefire with Abu Sayyaf. Surely they're much more intimidated now");
        int chansa = ra.nextInt(10);
        switch(sc.nextInt()){
            case 1: victory += (chansa <5)? 2:-1;
            opposition += (chansa < 5)? -2:1;
            senate += (chansa <5)? 2:0;
            support +=(chansa <5)? 2:-2 ;
                break;
            case 2:victory += (chansa <4)? 3:-2;
            opposition += (chansa < 4)? -3:2;
            senate += (chansa <4)? 3:-2;
            support +=(chansa <4)? 5:-5 ;
                break;
            default: 
            victory-=3;
            opposition+=2;
            senate-=3;
            support -=7;
        }
        checkLimits();
        
        System.out.println("Being one of an economic background, you have chosen the economy as your primary focus of policy. There's currently an economic reform bill being pushed in the Senate that aligns with your policy. Passing it would be very beneficial for you, at a cost.");
        System.out.println("Pull all the strings we got to get this on my desk.");
        System.out.println("Let the chips fall where they may");
        System.out.println("Oppose the bill to rally support");
        int ecochance1 = ra.nextInt(24);
        switch(sc.nextInt()){
            case 1: policy +=(senate >=12)? 5:0;
            support -=(senate >=12)?  5:0;
            senate -=(senate >=12)? 5:0;
            opposition+=(senate >=12)? 2:0;
                break;
            case 2:policy += (ecochance1 < senate)? 2:0;
            support += (ecochance1<senate)? -2:0;
            opposition += (ecochance1<senate)? 1:0;
                break;
            default: 
            policy-=2;
            senate-=2;
            support +=5;
        }
        checkLimits();
        
        // ev 8
        System.out.println("\nIt's a beautiful afternoon, Rizal Day celebrations are about to conlude and you are to make a speech");
        System.out.println("Let's focus this speech on unity. It's very important in stabilizing this nation");
        System.out.println("I'll shore up support by pledging to not run in 2004. Surely changing my mind next year won't cause too much uproar, right?");
        switch(sc.nextInt()){
            case 1:support +=5;
            victory -=1;
                break; 
            default:senate -=1;
            opposition-=2;
            support -=10;
        }
        if(senate >=18){
            policy+=2;
            opposition -=1;
        } else if (senate >=12 && senate < 18){
            policy+=1;
        } else if (senate >=6 && senate < 12){
            policy+=-1;
            opposition+=1;
        } else if (senate < 6){
            policy+=-2;
            opposition+=2;
        }
        checkLimits();
        // ev 9
        System.out.println("\nTerrorists have just detonated a bomb inside Davao Airport. On your way there, you prepare your statement");
        System.out.println("I'll send my condolences to the victims. Make me look sympathetic and all that");
        System.out.println("Let's use this event to emphasize the need for more counterterrorism operations");
        switch(sc.nextInt()){
            case 1: victory +=1;
            support +=5;
                break;
            default: victory -=1;
            senate+=2;
            opposition+=1;
            support +=1;
        }
        checkLimits();
        
        
        System.out.println("Several hundred soldiers have occupied the Oakwood Premier Ayala Center, accusing you of corruption and plots to declare martial law");
        System.out.println("Crush the rebels! Use any means necessary to end this mutiny! Censor the press!");
        System.out.println("This is nothing but an attempt by a disgruntled faction of the military to assert its power and gain influence in the government. Send in soldiers to blockade them but don't engage first");
        System.out.println("Go on TV and address the rebels' accusations. Depict ourselves as the more reasonable ones.");
        switch(sc.nextInt()){
            case 1: victory -=2;
            opposition +=2;
            support -=5;
            senate -=2;
                break;
            case 2: victory +=1;
            opposition +=1;
            support -=2;
                break;
            default: victory -=1;
            opposition -=1;
            senate -=1;
            support +=2;
        }
        checkLimits();
        
        // ev 10
        System.out.println("\nUS President George Bush has just approached you, asking if you'd be interested in joining a joint coalition to invade Iraq.");
        System.out.println("This will be beneficial to me and make me look like a strong leader. Send a couple of our soldiers out there to participate");
        System.out.println("I refuse to send troops to fight in a war with flimsy justifications.");
        switch(sc.nextInt()){
            case 1: senate +=3;
            opposition+=2;
            support -=5;
                break;
            default: senate -=5;
            opposition-=1;
            support +=5;
        }
        checkLimits();
        
        System.out.println("An infrastructure bill's going around in the senate. Maybe you can throw your hat into the ring and support it, or sink it to get some support.");
        System.out.println("Pull all the strings we got to get this on my desk.");
        System.out.println("Let the chips fall where they may");
        System.out.println("Oppose the bill to rally support");
        int ecochance2 = ra.nextInt(24);
        switch(sc.nextInt()){
            case 1: policy +=(senate >=12)? 5:0;
            support -=(senate >=12)?  5:0;
            senate -=(senate >=12)? 5:0;
            opposition+=(senate >=12)? 2:0;
                break;
            case 2:policy += (ecochance1 < senate)? 2:0;
            support += (ecochance1<senate)? -2:0;
            opposition += (ecochance1<senate)? 1:0;
                break;
            default: 
            policy-=2;
            senate-=2;
            support +=5;
        }
        checkLimits();
        
        // ev 11
        System.out.println("\nJose Pidal Scandal");
        System.out.println("Your husband has been implicated in an investigation into a dummy account. What is your response?");
        System.out.println("Let's keep quiet on this scandal until it all blows over.");
        System.out.println("They wanna investigate? Fine, I'll let them investigate.");
        System.out.println("This is clearly just an attempt by the opposition to unermine my administration. Ready me a speech, I'll put these guys on blast.");
        switch(sc.nextInt()){
            case 1: opposition+=1;
            support -=2;
                break;
            case 2: opposition -=1;
            senate -=1;
            victory-=1;
                break;
            default: 
            opposition+=2;
            senate -=2;
            support -=5;
                    
        }
        checkLimits();
        if(senate >=18){
            policy+=2;
            opposition -=1;
        } else if (senate >=12 && senate < 18){
            policy+=1;
            
        } else if (senate >=6 && senate < 12){
            policy+=-1;
            opposition+=1;
        } else if (senate < 6){
            policy+=-2;
            opposition+=2;
        }
        checkLimits();
        int vpid = 0;
        System.out.println("Choose your Running Mate");
        System.out.println("We need someone who's popular with the people and wasn't in my coalition in 2001 to form a sort of unity ticket...");
        System.out.println("I'll call a good friend in the senate, someone I can trust to be my deputy...");
        System.out.println("Let's call in one of the spice boys, bring in a new face to the national stage...");
        switch(sc.nextInt()){
            case 1:
                support +=5;
                senate -=1;
                vpid = 0;
                opposition -=5;
                break;
            case 2:
                support +=3;
                senate += 2;
                opposition+=5;
                vpid = 1;
                break;
            default: 
            support +=1;
            vpid = 2;
        }
        checkLimits();
        
        System.out.println("Opposition senators are now reviving the Jose Pidal Scandal from last year to accuse you of funneling money from the Jose Pidal bank account into your campaign");
        System.out.println("At it again? Write me a speech, let's head to the senate and call these guys out for what they're really doing");
        System.out.println("Just ignore it, they're just trying to stir up old drama before the election");
        switch(sc.nextInt()){
            case 1: opposition +=1;
            victory+=1;
            support -=2;
                break;
            default: 
            victory-=1;
            support -=5;
        }
        checkLimits();
        
        //victory += senate/6;
        victory += policy/10;
        
        
        
        int vicid = 0;
        if(victory>3){
            vicid = 2;
        }else if(victory <=3 && victory>-5){
            vicid = 1;
        } else if (victory <=-5){
            vicid = 0;
        }
        int oppID = 0;
        switch(vicid){
            case 0:
                if(opposition>9){
                    oppID = 2;
                }else if(opposition <=9 && opposition >1){
                    oppID = 1;
                }else if(opposition <=1){
                    oppID = 0;
                }
                break;
            case 1:
                if(opposition>9){
                    oppID = 5;
                }else if(opposition <=9 && opposition >1){
                    oppID = 4;
                }else if(opposition <=1){
                    oppID = 3;
                }
                break;
            case 2:
            if(opposition>9){
                    oppID = 8;
                }else if(opposition <=9 && opposition >1){
                    oppID = 7;
                }else if(opposition <=1){
                    oppID = 6;
                }
        }
        
        opscore = 0;
        yourscore = (support+50)-opposition;;
        isElection = true;
        System.out.print("Your opponent will be: ");
        switch(oppID){
            case 0:
                System.out.println("Eddie Gil");
                mainOpponent = "Eddie Gil";
                opscore = 10;
                break;
            case 1:
                System.out.println("Bongbong Marcos");
                mainOpponent = "Bongbong Marcos";
                opscore = 25;
                break;
            case 2:
                System.out.println("Chavit Singson");
                mainOpponent = "Chavit Singson";
                opscore = 30;
                break;
            case 3:
                System.out.println("Richard Gordon");
                mainOpponent = "Richard Gordon";
                opscore = 35;
                break;
            case 4:
                System.out.println("Ping Lacson");
                mainOpponent = "Ping Lacson";
                opscore = 40;
                break;
            case 5:
                System.out.println("Raul Roco");
                mainOpponent = "Raul Roco";
                opscore = 45;
                break;
            case 6:
                System.out.println("Rodrigo Duterte");
                mainOpponent = "Rodrigo Duterte";
                opscore = 50;
                break;
            case 7:
                System.out.println("Fernando Poe Jr.");
                mainOpponent = "Fernando Poe Jr.";
                opscore = 55;
                break;
            case 8:
                System.out.println("Miriam Defensor Santiago");
                mainOpponent = "Miriam Defensor Santiago";
                opscore = 60;
                break;
        }
        opscore+=opposition;
        otherscore =(opscore+yourscore)/5;
        int putmo = 0;
        
        
        switch(oppID){
            case 0:
                calcPercent(yourscore, opscore, otherscore);
                System.out.println("\nEddie is claiming that he can pay off the National Debt using gold he apparently has");
                System.out.println("Head to the media and ridicule him there");
                System.out.println("Criticize and joke about him in speeches and rallies, but nothing more");
                System.out.println("Don't address it at all");
                switch(sc.nextInt()){
                    case 1:
                        yourscore +=5;
                        opscore+=10;
                        break;
                    case 2:
                        yourscore+=3;
                        opscore+=1;
                        break;
                    default:
                    otherscore+=3;
                }
                
                calcPercent(yourscore, opscore, otherscore);
                System.out.println("\nMaybe we could target Gil's eligibility.");
                System.out.println("Let's amplify this, make it a focus of our campaign to beat this guy");
                System.out.println("Let's focus on promoting our own policies");
                switch(sc.nextInt()){
                    case 1:
                        yourscore +=5;
                        opscore+=10;
                        break;
                    default:
                    yourscore += 5;
                }
                
                calcPercent(yourscore, opscore, otherscore);
                System.out.println("\nGil is popular in the more rural areas. Should we campaign there?");
                System.out.println("Of course, let's not concede the provinces to this guy.");
                System.out.println("No, let's focus on places where we're already polling high in and consolidate our vote in the cities");
                switch(sc.nextInt()){
                    case 1:
                        yourscore += 3;
                        opscore +=5;
                        break;
                    default:
                    yourscore +=5;
                    opscore+=5;
                }
                
                calcPercent(yourscore, opscore, otherscore);
                System.out.println("\nThere are some murmurs calling for Eddie to be disqualified. Should we amplify and platform these ideas?");
                System.out.println("Yes, Eddie is clearly incompetent and amplifying these calls will make him less legitimate");
                System.out.println("Let's not risk the media trying to call us undemocratic or whatever.");
                switch(sc.nextInt()){
                    case 1:
                        opscore +=15;
                        break;
                    default:
                    
                }
                
                calcPercent(yourscore, opscore, otherscore);
                System.out.println("\nGil is using very unorthodox and unconventional campaign methods, attracting the media's attention. We could either keep on with our own strategy or piggyback off of that and try our hand at avant-garde stuff");
                System.out.println("Deviating from our current strategy now would make us look weak and indecisive");
                System.out.println("If it's what's getting the media's attention, then we should capitalize on it. Call my media team.");
                switch(sc.nextInt()){
                    case 1:
                        yourscore +=5;
                        opscore +=10;
                        break;
                    default:
                    yourscore += 3;
                    opscore += 15;
                }
                
                calcPercent(yourscore, opscore, otherscore);
                System.out.println("\nGil's about to stage a rally that's expected to pull thousands. Let's do our own");
                System.out.println("Let's hold our rally at a stronghold province, get the media to watch us, pull all stops for this one!");
                System.out.println("Let's hold a rally in a competitive province, it'll make us look brave.");
                switch(sc.nextInt()){
                    case 1:
                        yourscore +=10;
                        opscore +=5;
                        break;
                    default:
                    yourscore += 5;
                    opscore +=10;
                }
                
                calcPercent(yourscore, opscore, otherscore);
                System.out.println("\nThe debates are here. We have a couple ways we can take this. Either we focus on our own policy, attack Eddie, or we could just not show up so we don't legitimize his candidacy");
                System.out.println("I'll attend, but I'll only be talking about my policies and what I'll do for the next four years.");
                System.out.println("We can really use this moment to attack Eddie, give the media some really good clips.");
                System.out.println("I won't attend. I will not legitimize this glorified nuisance candidate");
                switch(sc.nextInt()){
                    case 1:
                        yourscore +=5;
                        opscore +=5;
                        break;
                    case 2:
                        yourscore +=5;
                        opscore +=10;
                        break;
                    default:
                    opscore +=10;
                }
                
                break;
            case 1:
                calcPercent(yourscore, opscore, otherscore);
                System.out.println("\nWe could capitalize on the bloody legacy of the Marcos Family to hurt his campaign");
                System.out.println("18 years ago, we overthrew a corrupt regime that killed thousands and stole millions. Are we really gonna let the son of the head of that regime become president?");
                System.out.println("We can't risk alienating more moderate voices by hearkening back to EDSA 1. Let's make a couple jabs and side comments but nothing more.");
                System.out.println("Let's not talk about this at all. This is a topic that's too controversial");
                switch(sc.nextInt()){
                    case 1:
                        yourscore +=10;
                        opscore +=5;
                        break;
                    case 2:
                        yourscore +=5;
                        opscore +=5;
                        break;
                    default:
                    opscore +=5;
                }
                
                
                calcPercent(yourscore, opscore, otherscore);
                System.out.println("\nThe Marcos family has stolen millions from the country when they were in power. Maybe we can use this ill-gotten wealth to our advantage");
                System.out.println("Let's give these talking points a megaphone. Make the term 'Marcos Magnanakaw' common again");
                System.out.println("Fine, but we won't make it the center of our campaign. Too controversial for that.");
                System.out.println("I'd rather focus on promoting my own policies and plans for the country than attacking my opponent.");
                switch(sc.nextInt()){
                    case 1:
                        yourscore += 10;
                        opscore += 5;
                        break;
                    case 2:
                        yourscore +=3;
                        opscore +=5;
                        break;
                    default:
                    opscore +=5;
                }
                
                calcPercent(yourscore, opscore, otherscore);
                System.out.println("\nMarcos is rebranding his family's image and repainting the Martial Law period as a time of prosperity. How should we react?");
                System.out.println("Many victims of Martial Law are very much still alive. Have my Media crew contact some of them. Let's put the real legacy of Martial Law on air");
                System.out.println("Let's take advantage of this and run ads on my economic policy. Let's prove that Democracy and Prosperity aren't mutually exclusive!");
                System.out.println("Instead of attacking his father, we can instead promote my education policy and college program plans. Let's give the youth a future, not delude them with the past.");
                switch(sc.nextInt()){
                    case 1:
                        yourscore +=8;
                        opscore +=5;
                        break;
                    case 2:
                        yourscore +=3;
                        opscore +=5;
                        break;
                    default:
                    opscore +=5;
                }
                
                calcPercent(yourscore, opscore, otherscore);
                System.out.println("\nThe Solid North is the term used for the political bloc of regions in Northern Luzon that have historyically voted for Marcos and Marcos-backd candidates. This election will be no different, but how about we do a power move and organize a rally or a motorcade there?");
                System.out.println("Call the senators, call my organizer, we're gonna do a big rally in Ilocos Norte");
                System.out.println("Fine, but let's not make it too high profile. I'll do a couple rallies around the North");
                System.out.println("Let's not waste resources on this vanity sideproject. Let's keep on running ads and holding rallies at competitive provinces.");
                switch(sc.nextInt()){
                    case 1:
                        yourscore +=10;
                        opscore +=3;
                        break;
                    case 2:
                        yourscore +=8;
                        opscore +=5;
                        break;
                    default:
                    yourscore += 3;
                    opscore +=5;
                }
                
                
                calcPercent(yourscore, opscore, otherscore);
                System.out.println("\nBongbong is from a notorious political dynasty that's dominated Ilocos politics for decades. Let's hit them on that and accuse them of running a corrupt political machine");
                System.out.println("Good idea. Get me my media guy, we're brainstorming ads.");
                System.out.println("Let's remember that I'm from a prominent political family myself. Let's not throw rocks from a glass house");
                switch(sc.nextInt()){
                    case 1:
                        otherscore +=15;
                        break;
                    default:
                    yourscore+=5;
                    opscore +=5;
                }
                
                calcPercent(yourscore, opscore, otherscore);
                System.out.println("\nMarcos recently received an endorsement from Juan Ponce Enrile, a top guy under the Marcos Regime. Maybe now we can use this to our advantage");
                System.out.println("Let's totally use this. Call the news stations, schedule interviews, write me some speeches. We're gonna put him on blast");
                System.out.println("I don't see why we can't use this. Just make it so that it isn't too divisive.");
                System.out.println("No. We'll focus our resources on my policy, and my plans for the country. Enrile isn't that relevant on the grand scheme of things anymore anyway.");
                switch(sc.nextInt()){
                    case 1:
                        yourscore +=3;
                        opscore +=1;
                        break;
                    case 2:
                        yourscore +=3;
                        opscore +=2;
                        break;
                    default:
                    yourscore += 4;
                    opscore +=2;
                }
                
                calcPercent(yourscore, opscore, otherscore);
                System.out.println("\nIt's debate night. What's your strategy?");
                System.out.println("Let's focus on linking Bongbong to his father and make constant attacks and jabs there. That'll catch the nation's attention");
                System.out.println("Let's balance it out and talk about both my policy and Marcos' questionable past. Let's not make it all about him.");
                System.out.println("This is a debate, not an attack ad. I'll only talk about my policies and my plans primarily.");
                switch(sc.nextInt()){
                    case 1:
                        yourscore +=1;
                        opscore +=4;
                        break;
                    case 2:
                        yourscore +=5;
                        yourscore +=3;
                        break;
                    default:
                    yourscore +=3;
                    opscore +=5;
                }
                
                break;
                case 2:
                calcPercent(yourscore, opscore, otherscore);
                System.out.println("\nBack in 2001, Chavit Singson was pivotal in the overthrow of Joseph Estrada by exposing his corruption. Now, he's running against us, claiming that he's the true anti-corruption candidate.");
                System.out.println("He was just the guy that had a briefcase and accused him. The real stuff was done by senators and the people.");
                System.out.println("Have we all forgotten what I did in EDSA? I played a much bigger role as the Vice-President.");
                System.out.println("Let's not dwell on the past. Let's focus on my policies and my plans.");
                switch(sc.nextInt()){
                    case 1:
                        yourscore +=2;
                        opscore +=5;
                        break;
                    case 2:
                        yourscore +=5;
                        opscore +=3;
                        break;
                    default:
                    yourscore +=1;
                    opscore +=3;
                }
                
                calcPercent(yourscore, opscore, otherscore);
                System.out.println("\nSingson has just unveiled a bold set of plans that includes subsidies and support for local industry. This has wowed a lot of people, but experts are claiming that these plans are unrealistic with the estimated budget next year.");
                System.out.println("Get me these experts and give them a platform. Call him out on it. Make him look unreasonable.");
                System.out.println("Call my media guy. We're making our own set of bold promises to match his.");
                System.out.println("Instead of making promises, why don't we show off all the things we've already achieved under my first term?");
                switch(sc.nextInt()){
                    case 1:
                        yourscore +=2;
                        opscore +=5;
                        break;
                    case 2:
                        yourscore +=3;
                        opscore +=3;
                        break;
                    default:
                    
                    yourscore += policy/2;
                }
                
                calcPercent(yourscore, opscore, otherscore);
                System.out.println("\nChavit has significant influence in Ilocos Sur, allowing him to galvanize support in the North. Should we try campaigning there or should we stick to our own strongholds?");
                System.out.println("Northern Luzon has lots of votes. We can't risk losing them all to Singson. Let's do a couple rallies there, put pressure on him in his own home");
                System.out.println("Let's focus our resources on our own strongholds, maybe do some campaigning in competitive regions too.");
                System.out.println("We could contact local politicians and rival factions in his region to support us. Get me the contacts for all the congressmen in Ilocos!");
                switch(sc.nextInt()){
                    case 1:
                        yourscore +=5;
                        opscore +=4;
                        break;
                    case 2:
                        yourscore +=2;
                        opscore +=5;
                        break;
                    default:
                    yourscore +=1;
                    opscore +=5;
                }
                
                calcPercent(yourscore, opscore, otherscore);
                System.out.println("\nSingson has a history of being an opportunistic politician. Maybe we can capitalize on this.");
                System.out.println("Good idea. Run attack ads, make comments of it in rallies. We can't trust a butterfly to run our country.");
                System.out.println("Let's paint him as a turncoat but don't go too overboard. ");
                System.out.println("This is unnecessary. Let's keep on promoting our own policies and plans.");
                switch(sc.nextInt()){
                    case 1:
                        yourscore +=2;
                        opscore +=4;
                        break;
                    case 2:
                        yourscore +=3;
                        opscore +=3;
                        break;
                    default:
                    yourscore +=2;
                    opscore +=5;
                }
                
                calcPercent(yourscore, opscore, otherscore);
                System.out.println("\nA poll recently released that showed that Chavit is particularly very popular with rural folk thanks to his populist messaging. How should we counter this?");
                System.out.println("Get me on TV. Let's try our hand at this populism thing.");
                System.out.println("Let's get on the campaign bus and head to rural communities and connect with the people directly.");
                System.out.println("We don't need this populism. Let's just keep showing the people what we've already achieved and how they benefit from that.");
                switch(sc.nextInt()){
                    case 1:
                        yourscore +=1;
                        opscore +=5;
                        break;
                    case 2:
                        yourscore +=5;
                        opscore +=5;
                        break;
                    default:
                    yourscore +=3;
                    opscore +=5;
                }
                
                calcPercent(yourscore, opscore, otherscore);
                System.out.println("\nIt's already well known that Chavit Singson has very deep ties with the Filipino underworld and gambling specifically. Maybe we can take advantage of this.");
                System.out.println("Let's run ads and comment on it in our rallies. He's clearly not to be trusted with the Presidency");
                System.out.println("Avoid this topic. It's far too controversial. Let's just keep promoting our own policies.");
                System.out.println("Are we really letting a criminal run for President? Let's open an investigation on this guy");
                switch(sc.nextInt()){
                    case 1:
                        yourscore +=3;
                        opscore +=3;
                        break;
                    case 2:
                        yourscore +=3;
                        opscore +=2;
                        break;
                    default:
                    opscore +=10;
                }
                
                calcPercent(yourscore, opscore, otherscore);
                System.out.println("\nChavit is holding a massive rally in a few weeks. Let's split the media coverage and hold our own rally");
                System.out.println("Let's hold a rally in Pampanga, get a big crowd of people there and make the cameras watch.");
                System.out.println("Call our senate bets and let's hold a rally in Metro Manila. The media'll love it");
                System.out.println("Let's take the fight to Chavit and hold a rally in Baguio. Break into the North.");
                switch(sc.nextInt()){
                    case 1:
                        yourscore +=3;
                        opscore +=5;
                        break;
                    case 2:
                        yourscore +=5;
                        opscore +=5;
                        break;
                    default:
                    yourscore +=5;
                    opscore +=3;
                }
                    break;
                case 3:
                
                calcPercent(yourscore, opscore, otherscore);
                System.out.println("\nDick is campaigning on his success in transforming Subic into an economic hub and is promising to do the same to the entire country if elected President");
                System.out.println("Did we already forget the fact that he barricaded himself inside Subic when he was removed by the last administration? What if he doesn't want to leave Malacañan next?");
                System.out.println("Let's counter this by promoting my own economic background and showing off what I've achieved on my own.");
                switch(sc.nextInt()){
                    case 1:
                        yourscore +=3;
                        opscore +=5;
                        break;
                    default:
                    yourscore +=policy/2;
                    opscore +=5;
                }
                
                calcPercent(yourscore, opscore, otherscore);
                System.out.println("\nGordon has released new ads boasting about his achievements in improving tourism, criticizing your government for not helping in that industry enough");
                System.out.println("It's true that we haven't invested in much tourism, but we've instead invested in the rest of the economy. Show 'em what for");
                System.out.println("Oh please, I've done a lot for the tourism industry. Let's get me on TV and show it all off.");
                System.out.println("You know what? Yeah, we DO need to improve our tourism. Let's start promising to improve tourism too");
                switch(sc.nextInt()){
                    case 1:
                        yourscore +=policy/2;
                        opscore +=5;
                        break;
                    case 2:
                        yourscore +=policy/4;
                        opscore +=5;
                        break;
                    default:
                    yourscore +=1;
                    opscore +=5;
                }
                
                calcPercent(yourscore, opscore, otherscore);
                System.out.println("\nIn a recent rally, Gordon emphasized his position in the Philippine Red Cross and positioned himself as an expert in crisis management. Do we respond?");
                System.out.println("We've done lots in disaster preparedness, right? Come on, get me a couple interviews");
                System.out.println("This is a losing front. Let's not comment on this.");
                switch(sc.nextInt()){
                    case 1:
                        yourscore +=policy/4;
                        opscore +=5;
                        break;
                    default:
                    yourscore +=3;
                    opscore +=5;
                }
                
                calcPercent(yourscore, opscore, otherscore);
                System.out.println("\nDick is quickly centering his campaign on nationalism and self-reliance, criticizing your foreign policy all the while.");
                System.out.println("Let's counter this by showing off all the diplomatic achievements we've made");
                System.out.println("Criticize his stance and make him look like an unrealistic idealist.");
                System.out.println("It seems popular with the people. I'll promote my own domestic policies to match him.");
                switch(sc.nextInt()){
                    case 1:
                        yourscore +=policy/4;
                        opscore +=3;
                        break;
                    case 2:
                        yourscore +=3;
                        opscore +=5;
                        break;
                    default:
                    yourscore +=5;
                    opscore +=3;
                }
                
                calcPercent(yourscore, opscore, otherscore);
                System.out.println("\nABS-CBN has recently approached you, asking if you want to hold a debate. Dick is famous for being very good at debating. Should wy face him head on?");
                System.out.println("Of course. I'm the incumbent. It'd make me look weak if I refused to debate him. Let's prepare");
                System.out.println("Fine, but I have a few requests on rule changes to give me some benefits in the debate");
                System.out.println("No, facing off against an opponent like that is suicide. Let's stick with interviews and scripted appearances");
                switch(sc.nextInt()){
                    case 1:
                        yourscore +=3;
                        opscore +=5;
                        break;
                    case 2:
                        yourscore +=3;
                        opscore +=4;
                        break;
                    default:
                    opscore +=5;
                }
                
                calcPercent(yourscore, opscore, otherscore);
                System.out.println("\nA recently released poll shows that Gordon appeals strongly to middle-class voters.");
                System.out.println("We can't just let him win a demographic. let's make ads tailored for these people, show them that we care");
                System.out.println("We can always rely on the rural vote. Let's hold a couple rallies in the provinces.");
                switch(sc.nextInt()){
                    case 1:
                        yourscore +=5;
                        opscore +=4;
                        break;
                    default:
                    yourscore +=5;
                    opscore +=3;
                }
                
                calcPercent(yourscore, opscore, otherscore);
                System.out.println("\nGordon has recently laid out a comprehensive set of plans for the country if he is ever elected President. How do we respond?");
                System.out.println("Let's show our own comprehensive plans for the country. Make promises, we got six years to do it");
                System.out.println("Call the news stations. Let's start picking apart his plans, do a real takedown of the guy.");
                switch(sc.nextInt()){
                    case 1:
                        yourscore +=3;
                        opscore +=5;
                        break;
                    default:
                    yourscore +=3;
                    opscore +=5;
                }
                    break;
                case 4:
                    calcPercent(yourscore, opscore, otherscore);
                System.out.println("\nLacson is positioning himself as an anti-corruption candidate, highlighting his career in law enforcement to further boost his integrity");
                System.out.println("We're anti corruption too. Let's get ourselves into some softball interviews and rallies");
                System.out.println("He isn't so innocent. Dig something up on Lacson.");
                System.out.println("Let's not address this issue. We've had our fair share of scandals.");
                switch(sc.nextInt()){
                    case 1:
                        yourscore +=1;
                        opscore +=5;
                        break;
                    case 2:
                        yourscore +=3;
                        opscore +=4;
                        break;
                    default:
                    yourscore +=2;
                    opscore +=5;
                }
                
                calcPercent(yourscore, opscore, otherscore);
                System.out.println("\nLacson's campaign is centering itself around law and order and stability, criticizing the government's lackluster performance in that regard.");
                System.out.println("We've improved the security sector of our country by a lot, thank you very much.");
                System.out.println("We addressed the root problem of crime by investing more into our economy and giving people safe and legal jobs");
                switch(sc.nextInt()){
                    case 1:
                        yourscore += policy/4;
                        opscore +=5;
                        break;
                    default:
                    yourscore +=policy/2;
                    opscore +=5;
                }
                
                calcPercent(yourscore, opscore, otherscore);
                System.out.println("\nlacson's critics are raising questions about his past in law enforcement, alleging that he committed or allowed human rights violations in his tenure");
                System.out.println("We can absolutely use this to our advantage. Do interviews, dig up some dirt, find his colleagues.");
                System.out.println("These are just allegations with little solid backing. Let's not risk making a nothingburger");
                System.out.println("These are some serious allegations. Call the interior secretary, call the senate, let's start investigating this guy");
                switch(sc.nextInt()){
                    case 1:
                        yourscore +=3;
                        opscore +=5;
                        break;
                    case 2:
                        yourscore +=4;
                        opscore +=5;
                        break;
                    default:
                    yourscore +=1;
                    opscore +=10;
                }
                
                calcPercent(yourscore, opscore, otherscore);
                System.out.println("\nWe've noticed that Lacson's recent speeches have been including more and more authoritarian rhetoric and messaging in them.");
                System.out.println("Let's use this to our full advantage. Get me on TV and I'll talk about how dangerous authoritarianism is");
                System.out.println("This isn't necessary. Let's keep our talking points all about policy.");
                switch(sc.nextInt()){
                    case 1:
                        yourscore +=3;
                        opscore +=3;
                        break;
                    default:
                    yourscore +=3;
                    opscore +=5;
                }
                
                calcPercent(yourscore, opscore, otherscore);
                System.out.println("\nSeveral prominent police and military figures have come out in support of Lacson. What do we do?");
                System.out.println("Let's remind the military and police of what my administration has done for them. That'll teach them.");
                System.out.println("Let's keep campaigning for the broader civilian vote.");
                switch(sc.nextInt()){
                    case 1:
                        yourscore +=policy/4;
                        opscore +=8;
                        break;
                    default:
                    yourscore +=5;
                    opscore +=8;
                }
                
                calcPercent(yourscore, opscore, otherscore);
                System.out.println("\nPing has been getting broader support with his straightforward and somewhat populist rhetoric.");
                System.out.println("Let's adapt, of course. The people want to talk straight, let's talk straight");
                System.out.println("Get me to the media and I'll criticize him for being oversimplistic and unfit for leadership.");
                System.out.println("We don't need to adapt. Let's hold a rally and talk about our achievements in the economy");
                switch(sc.nextInt()){
                    case 1:
                        yourscore +=4;
                        opscore +=5;
                        break;
                    case 2:
                        yourscore +=1;
                        opscore +=5;
                        break;
                    default:
                    yourscore +=policy/2;
                    opscore +=4;
                }
                
                calcPercent(yourscore, opscore, otherscore);
                System.out.println("\nOld fumbles by Lacson have recently begun resurfacing. We can either take advantage of these or keep on promoting our own policies.");
                System.out.println("Of course we have to take advantage of this. Let's put his mistakes on air, talk about it in rallies");
                System.out.println("Let's campaign on it a little, but focus the majority of my rhetoric on policy");
                System.out.println("We don't need to take advantage of low hanging fruit.");
                switch(sc.nextInt()){
                    case 1:
                        yourscore +=5;
                        opscore +=5;
                        break;
                    case 2:
                        yourscore +=5;
                        opscore +=4;
                        break;
                    default:
                    yourscore +=4;
                    opscore +=3;
                }
                    break;
                case 5:
                    
                calcPercent(yourscore, opscore, otherscore);
                System.out.println("\nRoco is campaigning as a progressive reformer, painting you as conservative and stagnant");
                System.out.println("I've done lots of reforms in my term. Let's show them");
                System.out.println("A lot of his plans are impractical and unrealistic. Unlike my plans which have already produced lots of achievements");
                System.out.println("I didn't have much time to implement reforms in my first term cause I was so busy stabilizing the country. I promise to do more if given a full 6-year term");
                switch(sc.nextInt()){
                    case 1:
                        yourscore +=policy/4;
                        opscore +=5;
                        break;
                    case 2:
                        yourscore +=policy/2;
                        opscore +=5;
                        break;
                    default:
                    yourscore +=4;
                    opscore +=4;
                }
                
                calcPercent(yourscore, opscore, otherscore);
                System.out.println("\nRoco is especially appealing to the younger voters for his education policies and promises to invest more in Philippine education");
                System.out.println("Like I haven't invested in our education system? Let's show off what we've achieved");
                System.out.println("While I haven't invested in education, I've certainly invested in the economy to make education more affordable.");
                switch(sc.nextInt()){
                    case 1:
                        yourscore +=policy/4;
                        opscore +=5;
                        break;
                    default:
                    yourscore += policy/2;
                    opscore +=10;
                }
                
                calcPercent(yourscore, opscore, otherscore);
                System.out.println("\nA newly released poll shows that Roco is polling high with women thanks to his gender equality policy.");
                System.out.println("Hellooo? I'm the woman here. Vote for me cause I represent the equal opportunity that this country provides to all genders.");
                System.out.println("My administration has done so much to help women get an equal ladder.");
                System.out.println("And? Instead of getting into identity politics, let's focus on actual, tangible policies");
                switch(sc.nextInt()){
                    case 1:
                        yourscore +=1;
                        opscore +=8;
                        break;
                    case 2:
                        yourscore +=policy/4;
                        opscore +=5;
                        break;
                    default:
                    yourscore +=3;
                    opscore +=4;
                }
                
                calcPercent(yourscore, opscore, otherscore);
                System.out.println("\nRoco's clean image and progressive platform resonates strongly with middle-class voters");
                System.out.println("Let's keep the middle class fight competitive. make new ads targetting those guys");
                System.out.println("Double down on consolidating the rural vote to balance it out");
                System.out.println("Let's shift the narrative here. I worked hard to combat corruption in my administration");
                switch(sc.nextInt()){
                    case 1:
                        yourscore +=3;
                        opscore +=5;
                        break;
                    case 2:
                        yourscore +=4;
                        opscore +=5;
                        break;
                    default:
                    yourscore +=policy/4;
                    opscore +=10;
                }
                
                calcPercent(yourscore, opscore, otherscore);
                System.out.println("\nA debate between you and Roco is coming up. What's the strategy?");
                System.out.println("Let's make bombastic statements, really get the cameras on us");
                System.out.println("Prepare me some good comebacks to match his rhetoric");
                System.out.println("I'll make myself look like the more reasonable, pragmatic candidate, and him the naive one");
                System.out.println("How about we just sit this one out? The media'll just twist up everything I say to make it look bad");
                switch(sc.nextInt()){
                    case 1:
                        yourscore +=4;
                        opscore +=5;
                        break;
                    case 2:
                        yourscore +=4;
                        opscore +=5;
                        break;
                    case 3:
                        yourscore += 2;
                        opscore +=6;
                        break;
                    default:
                    yourscore +=1;
                    opscore +=10;
                }
                
                calcPercent(yourscore, opscore, otherscore);
                System.out.println("\nThere are rising concerns about Raul Roco's health and if he'll even be fit to run government.");
                System.out.println("Take advantage of this. Let's center our campaign on targetting his poor health");
                System.out.println("Let's not break decorum here. It'll just make us look like bullies. Focus on policy");
                switch(sc.nextInt()){
                    case 1:
                        yourscore +=1;
                        opscore +=10;
                        break;
                    default:
                    yourscore +=5;
                    opscore +=4;
                }
                
                calcPercent(yourscore, opscore, otherscore);
                System.out.println("\nWhile Roco has been framed as the idealist reformist, you have been framed as the establishment pragmatist. Should we manipulate this narrative to our advantage?");
                System.out.println("Let's double down on our framing. We're the stability candidate. We do what works. Show off our policy victories and promise six more years of it");
                System.out.println("Let's attack Roco's idealism. It's far too unrealistic. Run ads, get me into interviews");
                System.out.println("Let's subtly coopt some of his policies and reforms, bite into his base of support");
                switch(sc.nextInt()){
                    case 1:
                        yourscore += policy;
                        opscore +=5;
                        break;
                    case 2:
                        yourscore +=4;
                        opscore +=4;
                        break;
                    default:
                    yourscore +=2;
                    opscore +=6;
                }
                    break;
                case 6:
                calcPercent(yourscore, opscore, otherscore);
                System.out.println("\nDuterte is centering his campaign on law and order, promising to eliminate crime in months while citing his record in Davao City as proof that his policy works");
                System.out.println("We're fighting crime too, you know. Let's show them");
                System.out.println("In a few months? That's ridiculous! Get me on TV and I'll put him on blast");
                System.out.println("My policy focus on improving the economy addresses the root of crime rather than just putting a band-aid on it");
                switch(sc.nextInt()){
                    case 1:
                        yourscore += policy/4;
                        opscore +=7;
                        break;
                    case 2:
                        yourscore +=5;
                        opscore +=5;
                        break;
                    default:
                    yourscore +=policy/2;
                    opscore +=6;
                }
                
                calcPercent(yourscore, opscore, otherscore);
                System.out.println("\nDuterte's fiery and controversial rhetoric is polarizing the nation. Maybe we can take advantage of this.");
                System.out.println("Let's depict Duterte as a divisive figure and position myself as a unity candidate.");
                System.out.println("It seems to be effective in pulling voters. Give me a fiery speech of my own");
                System.out.println("Stay out of this and keep on making the same rhetoric to look more reasonable");
                switch(sc.nextInt()){
                    case 1:
                        yourscore += 5;
                        opscore +=4;
                        break;
                    case 2:
                        yourscore +=1;
                        opscore +=8;
                        break;
                    default:
                    yourscore +=4;
                    opscore +=7;
                }
                
                calcPercent(yourscore, opscore, otherscore);
                System.out.println("\nDuterte has managed to consolidate the support of the majority of Mindanao. Should we continue campaigning there or is it a lost cause?");
                System.out.println("Of course we'll keep campaigning there. Let's hold a couple rallies and motorcades. Heck, let's do one in Davao City");
                System.out.println("Mindanao is a lost cause. Let's focus on consolidating Visayas and Luzon");
                System.out.println("Let's find allies in Davao politicians. Phone every congressman and Governor in the south now!");
                switch(sc.nextInt()){
                    case 1:
                        yourscore += 6;
                        opscore +=5;
                        break;
                    case 2:
                        yourscore +=3;
                        opscore +=7;
                        break;
                    default:
                    yourscore +=4;
                    opscore +=6;
                }
                
                calcPercent(yourscore, opscore, otherscore);
                System.out.println("\nDuterte's populist rhetoric has proven effective to gain the support of low-income and rural voters.");
                System.out.println("Let's not surrender the fight to get the working class vote. Show them the economic improvements under my government, that I'm giving them a chance.");
                System.out.println("Let's focus on consolidating the middle class and urban votes. Those are really what matter");
                System.out.println("We've done so much to help the lower income bracket. Let's show them specific programs we've done");
                switch(sc.nextInt()){
                    case 1:
                        yourscore += policy/2;
                        opscore +=5;
                        break;
                    case 2:
                        yourscore +=3;
                        opscore +=5;
                        break;
                    default:
                    yourscore +=policy/4;
                    opscore +=7;
                }
                
                calcPercent(yourscore, opscore, otherscore);
                System.out.println("\nCritics have accused Duterte of encouraging and even participating in extra-judicial killings when he was mayor in Davao.");
                System.out.println("Let's take advantage of this. 'Duterte Mamamatay Tao' in every corner in the country.");
                System.out.println("These are mostly hearsay theories. No basis other than word of mouth. Let's not focus on this");
                System.out.println("I heard he ran a death squad there. Real suspicious. Get the NBI, DILG, whoever else to investigate this guy");
                switch(sc.nextInt()){
                    case 1:
                        yourscore += 6;
                        opscore +=4;
                        break;
                    case 2:
                        yourscore +=3;
                        opscore +=5;
                        break;
                    default:
                    yourscore +=1;
                    opscore +=10;
                }
                
                calcPercent(yourscore, opscore, otherscore);
                System.out.println("\nDuterte has framed himself as a man who understands the struggle of the Filipino people while framing you as an out of touch career politician");
                System.out.println("That's not true. Schedule a rally and motorcade, maybe walk through a couple palengkes");
                System.out.println("And who's the one who's improved the economy? It was me. If I was out of touch, the economy would've gotten worse");
                System.out.println("Ignore this. It's just campaign rhetoric.");
                switch(sc.nextInt()){
                    case 1:
                        yourscore += 1;
                        opscore +=7;
                        break;
                    case 2:
                        yourscore +=policy/2;
                        opscore +=4;
                        break;
                    default:
                    yourscore +=1;
                    opscore +=5;
                }
                
                calcPercent(yourscore, opscore, otherscore);
                System.out.println("\nThe debate between you and Duterte is coming up. What's the strategy");
                System.out.println("Let's focus on policy. I've achieved a lot already.");
                System.out.println("Match his rhetoric. Split the media's attention");
                System.out.println("Write up a strategy that'll make me sound reasonable compared to Duterte");
                switch(sc.nextInt()){
                    case 1:
                        yourscore += policy/2;
                        opscore +=7;
                        break;
                    case 2:
                        yourscore +=2;
                        opscore +=8;
                        break;
                    default:
                    yourscore +=5;
                    opscore +=6;
                }
                    break;
                case 7:
                    calcPercent(yourscore, opscore, otherscore);
                System.out.println("\nFPJ leverages his fame and mass appeal to connect with voters, especially the working class");
                System.out.println("Let's use our administration's achievements as leverage of our own. We helped so much with the economy");
                System.out.println("Are you serious? I'm the one with political experience here. He's just an actor.");
                switch(sc.nextInt()){
                    case 1:
                        yourscore += policy/2;
                        opscore +=7;
                        break;
                    default:
                    yourscore +=1;
                    opscore +=11;
                }
                
                calcPercent(yourscore, opscore, otherscore);
                System.out.println("\nFPJ’s campaign focuses on broad, populist promises to uplift the poor");
                System.out.println("We've done so much to help the impoverished. Give me interviews, make TV ads");
                System.out.println("Most of his promises are unrealistic. Focus our ads on that");
                System.out.println("Let's make promises of our own. Give me six years and I'll reduce poverty in two!");
                switch(sc.nextInt()){
                    case 1:
                        yourscore += policy/4;
                        opscore +=7;
                        break;
                    case 2:
                        yourscore +=4;
                        opscore +=6;
                        break;
                    default:
                    yourscore +=5;
                    opscore +=5;
                }
                
                calcPercent(yourscore, opscore, otherscore);
                System.out.println("\nFPJ's rallies draw tens of thousands of people, putting our own campaign to shame.");
                System.out.println("Let's hold rallies of our own in our strongholds. Get the media to watch us");
                System.out.println("I'm a much more experienced and competent person. I don't care if he can pull massive crowds");
                switch(sc.nextInt()){
                    case 1:
                        yourscore += 5;
                        opscore +=5;
                        break;
                    default:
                    yourscore +=2;
                    opscore +=7;
                }
                
                calcPercent(yourscore, opscore, otherscore);
                System.out.println("\nIt's about time we put FPJ's inexperience on blast.");
                System.out.println("Get me on TV, call interviews, do rallies, pull all stops. We're turning this up to 11");
                System.out.println("Make a few small jabs and jokes on his inexperience but nothing more");
                System.out.println("Let's not do mudslinging. Focus on my achievements so far as president and why I should be voted");
                switch(sc.nextInt()){
                    case 1:
                        yourscore += 5;
                        opscore +=6;
                        break;
                    case 2:
                        yourscore +=3;
                        opscore +=6;
                        break;
                    default:
                    yourscore +=policy/2;
                    opscore +=7;
                }
                
                calcPercent(yourscore, opscore, otherscore);
                System.out.println("\nFPJ has painted you as the candidate of the elite, out of touch from the struggles of the common Filipino");
                System.out.println("Lies! I'm very much aware of the daily struggles. Let's do a couple stunts in palengkes and jeepneys");
                System.out.println("Me? Out of touch? I fought for the people's democracy in 2001, and the rich actor endorsed by the guy I ousted is calling me out of touch?");
                System.out.println("Ignore this. Just keep focusing on promoting my policies");
                switch(sc.nextInt()){
                    case 1:
                        yourscore += 1;
                        opscore +=8;
                        break;
                    case 2:
                        yourscore +=7;
                        opscore +=2;
                        break;
                    default:
                    yourscore +=3;
                    opscore +=6;
                }
                
                calcPercent(yourscore, opscore, otherscore);
                System.out.println("\nFPJ is trying to frame himself as the unity candidate, using his star power to gain the support of Filipinos from all backgrounds");
                System.out.println("Ridiculous! I'm the one who led the 2001 EDSA Revolution. I'm the clear symbol of unity for this country");
                System.out.println("His camp isn't so united if you take a good look. Tip the press");
                switch(sc.nextInt()){
                    case 1:
                        yourscore += 5;
                        opscore +=7;
                        break;
                    default:
                    yourscore +=4;
                    opscore +=4;
                }
                
                calcPercent(yourscore, opscore, otherscore);
                System.out.println("\nMany famous Filipino celebrities have come out in support of FPJ");
                System.out.println("Let's call this out for what it is: Nepotism and cronyism. Mark my words if FPJ gets elected, he'll make the cabinet look like a film casting!");
                System.out.println("We don't need these endorsements. The people will see through them and vote for us, the stability candidate.");
                switch(sc.nextInt()){
                    case 1:
                        yourscore += 3;
                        opscore +=8;
                        break;
                    default:
                    yourscore +=4;
                    opscore +=8;
                }
                    break;
                case 8:
                    calcPercent(yourscore, opscore, otherscore);
                System.out.println("\nSantiago campaigns on her reputation as a champion of the Constitution and the rule of law, promising to clean up government institutions");
                System.out.println("As if we haven't done government reform and cleaned up corruption? Let's show them what we've achieved");
                System.out.println("My government has been incredibly stable so far. Let's keep the way things are currently.");
                System.out.println("The economy's been prospering under me. If our institutions were so inefficient, why is that?");
                switch(sc.nextInt()){
                    case 1:
                        yourscore += policy/4;
                        opscore +=7;
                        break;
                    case 2:
                        yourscore +=1;
                        opscore +=9;
                        break;
                    default:
                    yourscore +=policy/2;
                    opscore +=7;
                }
                
                calcPercent(yourscore, opscore, otherscore);
                System.out.println("\nSantiago has a reputation for being a fiery debater. Should we confront her head on or should we avoid debates?");
                System.out.println("What are you saying? Of course we'll debate her head on");
                System.out.println("The media'll just eat it all up and clip everything out of context. It's best to avoid debating her");
                switch(sc.nextInt()){
                    case 1:
                        yourscore += 4;
                        opscore +=8;
                        break;
                    default:
                    yourscore +=1;
                    opscore +=11;
                }
                
                calcPercent(yourscore, opscore, otherscore);
                System.out.println("\nSantiago frames herself as the candidate unafraid to challenge the political elite, including you");
                System.out.println("Santiago isn't so grassroots herself. May I remind everyone that she was the most ardent defender of the corrupt Estrada?");
                System.out.println("I am a very pragmatic leader and understand the interests and struggles of the common people. Santiago doesn't know what she's talking about");
                System.out.println("Ignore her attacks, it won't serve us anything but giving her more attention.");
                switch(sc.nextInt()){
                    case 1:
                        yourscore += 5;
                        opscore +=4;
                        break;
                    case 2:
                        yourscore +=4;
                        opscore +=7;
                        break;
                    default:
                    yourscore +=3;
                    opscore +=6;
                }
                
                calcPercent(yourscore, opscore, otherscore);
                System.out.println("\nMiriam’s ability to combine populist rhetoric with intellectual rigor appeals to a broad spectrum of voters");
                System.out.println("Her ideas are unrealistic and absurd. Unlike my plans for future economic growth which are far more reasonable");
                System.out.println("Let's match her populism with our own messaging. I led EDSA Dos, I am the one who restored dignity and integrity to this government");
                System.out.println("Let's show off our own economic successes and promise six more years of prosperity");
                switch(sc.nextInt()){
                    case 1:
                        yourscore += 4;
                        opscore +=8;
                        break;
                    case 2:
                        yourscore +=4;
                        opscore +=6;
                        break;
                    default:
                    yourscore +=policy/2;
                    opscore +=8;
                }
                
                calcPercent(yourscore, opscore, otherscore);
                System.out.println("\nSantiago is polling high with young voters thanks to her no-nonsense attitude. Do we keep fighting for this demographic or do we fortify our support in others?");
                System.out.println("We have to fight for the vote of EVERY demographic, not just a few. Run a few ads that appeal to youth voters.");
                System.out.println("We can do without them. Focus on the older voters who remember EDSA 1");
                System.out.println("Let's not fight this demographic war. Let's hold a rally in the provinces to garner the rural vote");
                switch(sc.nextInt()){
                    case 1:
                        yourscore += 4;
                        opscore +=5;
                        break;
                    case 2:
                        yourscore +=3;
                        opscore +=7;
                        break;
                    default:
                    yourscore +=5;
                    opscore +=8;
                }
                
                calcPercent(yourscore, opscore, otherscore);
                System.out.println("\nMiriam recently made a statement that quickly became polarizing. this is just the latest in a series of statements that have made her a controversial figure");
                System.out.println("Let's capitalize on this by portraying myself as a less controversial and more mainstream candidate");
                System.out.println("Let's throw our own hat into the fray. Criticize her directly in ads, rallies and interviews!");
                switch(sc.nextInt()){
                    case 1:
                        yourscore += 6;
                        opscore +=6;
                        break;
                    default:
                    yourscore +=3;
                    opscore +=5;
                }
                
                calcPercent(yourscore, opscore, otherscore);
                System.out.println("\nMiriam's holding a massive rally in Manila a few weeks from now, and it's expected to draw tens of thousands of supporters and capture the media's attention.");
                System.out.println("Let's hold our own rally in Manila, show her what for");
                System.out.println("let's do a rally and a motorcade in the wider NCR, really get that media attention");
                System.out.println("Let's do a series of small rallies across Southern Luzon, spread out and get as much support from these areas as possible");
                switch(sc.nextInt()){
                    case 1:
                        yourscore += 5;
                        opscore +=8;
                        break;
                    case 2:
                        yourscore +=4;
                        opscore +=6;
                        break;
                    default:
                    yourscore +=6;
                    opscore +=7;
                }
                    break;
        }
        
        calcPercent(yourscore, opscore, otherscore);
        System.out.println("You have a friend in COMELEC that could help you when the vote counting starts. He's just a phone call away.");
        System.out.println("Hello, Garci?");
        System.out.println("He is unnecessary");
        switch(sc.nextInt()){
            case 1:
                yourscore += opscore/2;
                break;
            default: 
                
        }
        
        calcPercent(yourscore, opscore, otherscore);
        detWin(yourpercent,oppercent,otherpercent);
	}
}
