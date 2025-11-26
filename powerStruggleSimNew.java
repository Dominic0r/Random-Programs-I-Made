import java.util.*;
public class Main
{
    public static Random ra = new Random();
    public static Scanner sc = new Scanner(System.in);
    
    
    public static int month=0;
    public static String[] Months = {"January", "February","March", "April", "May", "June","July","August","September","October","November","December"};
    public static int year = 1920;
    
    public static class Person{
        String name, ideology;
        int age;
        int influence, polpower, military, admin, ideo; //Chairman, President, Chief General, Premiere, and Communications Head respectively
        int personality; // determines relations
        public Person(String name){
            this.name = name;
            this.age = ra.nextInt(20)+30;
            this.personality = ra.nextInt(100);
            switch(ra.nextInt(4)){
                case 1: ideology = "Reformer";
                    break;
                case 2:ideology = "Moderate";
                    break;
                case 3:ideology = "Conservative";
                    break;
                default:ideology = "Radical";
            }
            
            influence = ra.nextInt(100);
            polpower = ra.nextInt(100);
            military = ra.nextInt(100);
            admin = ra.nextInt(100);
            ideo = ra.nextInt(100);
            
        }
        
        public void updateInf(){
            if(this == chairman){
                influence += 10;
            }
            influence += ra.nextInt(10)-ra.nextInt(10);
        }
        
        public void updatePol(){
            if(this == president){
                polpower += 10;
            }
            polpower += ra.nextInt(10)-ra.nextInt(10);
        }
        
        public void updateMil(){
            if(this == chief){
                military += 10;
            }
            military += ra.nextInt(10)-ra.nextInt(10);
        }
        
        public void updateAdmin(){
            if(this == premiere){
                admin += 10;
            }
            admin += ra.nextInt(10)-ra.nextInt(10);
        }
        
        public void updateIdeo(){
            if(this == comms){
                ideo += 10;
            }
            ideo += ra.nextInt(10)-ra.nextInt(10);
        }
        
        public void checkNums(){
            if(influence>100){
                influence = 100;
            }
            if(influence<0){
                influence = 0;
            }
            
            if(polpower>100){
                polpower = 100;
            }
            if(polpower<0){
                polpower = 0;
            }
            
            if(military>100){
                military = 100;
            }
            if(military<0){
                military = 0;
            }
            
            if(admin>100){
                admin = 100;
            }
            if(admin<0){
                admin = 0;
            }
            
            if(ideo>100){
                ideo = 100;
            }
            if(ideo<0){
                ideo = 0;
            }
        }
        
        public String leadDisplay(){
            return name + " ("+ ideology+ ")";
        }
        
        @Override
        public String toString(){
            return name + ", "+ age+ " ("+ ideology+ ")";
        }
    }
    
    
    
    public static Person president;
    public static Person vicePres;
    
    public static Person chairman;
    public static Person depChair;
    
    public static Person chief;
    public static Person second;
    
    public static Person premiere;
    public static Person depPrem;
    
    public static Person comms;
    public static Person viceCom;
    
    public static String nameGen() {
         String[] SURNAMES = {
        "Li", "Wang", "Zhang", "Liu", "Chen", "Yang", "Huang", "Zhao", "Wu", "Zhou",
        "Xu", "Sun", "Ma", "Zhu", "Guo", "He", "Lin", "Gao", "Zheng", "Luo", "Shen"
    };

    String[] GIVEN_NAME_SYLLABLES = {
        "Wei", "Feng", "Jian", "Min", "Lei", "Yan", "Hua", "Jun", "Qing", "Mei",
        "Rong", "Hao", "Bo", "Ting", "Yi", "Xiu", "Shi", "Kai", "Chun", "Jing",
        "Shan", "Tao", "Zhi", "Long", "Fei", "Yu", "Wen", "Zhen", "Ming", "Xing"
    };
    
    String[] GIVEN_NAME_SYLLABLES_NOCAP = {
        "wei", "feng", "jian", "min", "lei", "yan", "hua", "jun", "qing", "mei",
        "rong", "hao", "bo", "ting", "yi", "xiu", "shi", "kai", "chun", "jing",
        "shan", "tao", "zhi", "long", "fei", "yu", "wen", "zhen", "ming", "xing"
    };

        String surname = SURNAMES[ra.nextInt(SURNAMES.length)];

        int givenNameLength = ra.nextInt(3) < 2 ? 2 : 1; 

        StringBuilder givenName = new StringBuilder();
        for (int i = 0; i < givenNameLength; i++) {
            String syllable =(i == 0)? GIVEN_NAME_SYLLABLES[ra.nextInt(GIVEN_NAME_SYLLABLES.length)]:GIVEN_NAME_SYLLABLES_NOCAP[ra.nextInt(GIVEN_NAME_SYLLABLES_NOCAP.length)];
            if(syllable.equalsIgnoreCase(givenName.toString())){
                break;
            }
            givenName.append(syllable);
        }

        return surname + " " + givenName.toString();
    }
    
    public static List<Person> standingCommittee = new ArrayList<>();
    
    
    public static int relationCalc(Person per1, Person per2){
        int finalrel = Math.abs(per1.personality - per2.personality)/3;
        finalrel += Math.abs(per1.age-per2.age);
        finalrel+= (per1.ideology.equalsIgnoreCase(per2.ideology))? finalrel/50:0;
        //System.out.println(finalrel);
        return finalrel;
    }
    
    public static void electPres(){
        int topNum = 0;
        Person winner = null;
        
        for(Person per : standingCommittee){
            if(per.polpower > topNum){
                topNum = per.polpower;
                winner = per;
                
            }
            
        }
        
        president = winner;
    }
    
    public static void appointVP(){
        int topnum = 0;
        Person winner = null;
        
        for(Person per : standingCommittee){
            if(relationCalc(president, per)> topnum && per!=president && hasNoPosition(per)){
                topnum = relationCalc(president,per);
                winner = per;
            }
        }
        
        vicePres = winner;
    }
    
    public static void electChair(){
        int topnum = 0;
        Person winner = null;
        
        for(Person per : standingCommittee){
            if(per.influence > topnum){
                topnum = per.influence;
                winner = per;
            }
        }
        
        chairman = winner;
    }
    
    public static void appointDC(){
        int topnum = 0;
        Person winner = null;
        
        for(Person per : standingCommittee){
            if(relationCalc(chairman, per)> topnum && per!=chairman && hasNoPosition(per)){
                topnum = relationCalc(chairman,per);
                winner = per;
            }
        }
        
        depChair = winner;
    }
    
    
    public static void electChief(){
        int topnum = 0;
        Person winner = null;
        
        for(Person per : standingCommittee){
            if(per.military > topnum){
                topnum = per.military;
                winner = per;
            }
        }
        
        chief = winner;
    }
    
    public static void appointSecond(){
        int topnum = 0;
        Person winner = null;
        
        for(Person per : standingCommittee){
            if(relationCalc(chief, per)> topnum && per!=chief && hasNoPosition(per)){
                topnum = relationCalc(chief,per);
                winner = per;
            }
        }
        
        second = winner;
    }
    
    
    public static void electPrem(){
        int topnum = 0;
        Person winner = null;
        
        for(Person per : standingCommittee){
            if(per.admin > topnum){
                topnum = per.admin;
                winner = per;
            }
        }
        
        premiere = winner;
    }
    
    public static void appointDPM(){
        int topnum = 0;
        Person winner = null;
        
        for(Person per : standingCommittee){
            if(relationCalc(premiere, per)> topnum && per!=premiere && hasNoPosition(per)){
                topnum = relationCalc(premiere,per);
                winner = per;
            }
        }
        
        depPrem = winner;
    }
    
    
    public static void electCom(){
        int topnum = 0;
        Person winner = null;
        
        for(Person per : standingCommittee){
            if(per.ideo > topnum){
                topnum = per.ideo;
                winner = per;
            }
        }
        
        comms = winner;
    }
    
    public static void appointViceCom(){
        int topnum = 0;
        Person winner = null;
        
        for(Person per : standingCommittee){
            if(relationCalc(comms, per)> topnum && per!=comms && hasNoPosition(per)){
                topnum = relationCalc(comms,per);
                winner = per;
            }
        }
        
        viceCom = winner;
    }
    
    public static void updateAllThings(Person per){
        per.updateIdeo();
        per.updateAdmin();
        per.updateMil();
        per.updatePol();
        per.updateInf();
    }
    
    public static void monthly(){
        List<Person> toRemove = new ArrayList<>();
        for(Person per: standingCommittee){
            updateAllThings(per);
            
            if(per == president){
                per.polpower += 20;
                per.influence+=10;
                per.military+=10;
                per.admin+=10;
                per.ideo+=10;
            }
            
            if(per == chairman){
                per.polpower += 10;
                per.influence+=20;
                per.military+=10;
                per.admin+=10;
                per.ideo+=10;
            }
            
            if(per == chief){
                per.polpower += 10;
                per.influence+=10;
                per.military+=20;
                per.admin+=10;
                per.ideo+=10;
            }
            
            if(per == premiere){
                per.polpower += 10;
                per.influence+=10;
                per.military+=10;
                per.admin+=20;
                per.ideo+=10;
            }
            
            if(per == comms){
                per.polpower += 10;
                per.influence+=10;
                per.military+=10;
                per.admin+=10;
                per.ideo+=20;
            }
            
            if(per.age < 40 || per.age > 75){
                per.polpower-=10;
                per.influence-=10;
                per.military-=10;
                per.admin-=10;
                per.ideo-=10;
            }
            
            if(per.ideology.equalsIgnoreCase(trending)){
                per.polpower+=5;
                per.influence+=5;
                per.military+=5;
                per.admin+=5;
                per.ideo+=5;
            }
            
            if(checkAge(per)){
                toRemove.add(per);
            }
            
        }
        
        standingCommittee.removeAll(toRemove);
        
        if(toRemove.contains(president)){
            president = vicePres;
            appointVP();
        }
        
        if(toRemove.contains(chairman)){
            chairman = depChair;
            appointDC();
        }
        
        if(toRemove.contains(chief)){
            chief = second;
            appointSecond();
        }
        
        if(toRemove.contains(premiere)){
            premiere = depPrem;
            appointDPM();
        }
        
        if(toRemove.contains(comms)){
            comms = viceCom;
            appointViceCom();
        }
        
        if(toRemove.contains(vicePres)){
            appointVP();
        }
        if(toRemove.contains(depChair)){
            appointDC();
        }
        if(toRemove.contains(second)){
            appointSecond();
        }
        if(toRemove.contains(depPrem)){
            appointDPM();
        }
        if(toRemove.contains(viceCom)){
            appointViceCom();
        }
        
        for(int i=0; i<toRemove.size(); i++){
            standingCommittee.add(new Person(nameGen()));
        }
        
        toRemove.clear();
        
        presCdown--;
        chairCdown--;
        chiefCdown--;
        PMCdown--;
        comCdown--;
        
        if(presCdown == 0){
            electPres();
            appointVP();
            presCdown = 84;
        }
        
        if(chairCdown == 0){
            electChair();
            appointDC();
            chairCdown = 36;
        }
        
        if(chiefCdown == 0){
            electChief();
            appointSecond();
            chiefCdown = 48;
        }
        
        if(PMCdown == 0){
            electPrem();
            appointDPM();
            PMCdown = 60;
        }
        
        if(comCdown == 0){
            electCom();
            appointViceCom();
            comCdown = 24;
        }
        
        month++;
        if(month== 12){
            year++;
            month = 0;
            detTrend();
            for(Person per: standingCommittee){
                per.age++;
            }
        }
    }
    
    
    public static boolean checkAge(Person per){
        int points = per.age-70;
        if(points > ra.nextInt(30)){
            return true;
        }else{
            return false;
        }
    }
    
    public static boolean hasNoPosition(Person per){
        return (president == per || chairman == per || chief == per || premiere == per || comms == per || vicePres == per || depChair == per || second == per || depPrem == per || viceCom == per)? false:true;
    }
    
    
    public static void paramountLeader(){
        int winnum = 500;
        Person winner = null;
        for(Person per: standingCommittee){
            int totPoints = per.influence+per.polpower+per.military+per.admin+per.ideo;
            
            if(totPoints > winnum*2){
                winnum = totPoints;
                winner = per;
            }
        }
        
        if(winner != null){
            System.out.println("Paramount Leader: "+ winner.leadDisplay());
        }
    }
    
    public static String trending = "";
    
    public static void detTrend(){
        switch(ra.nextInt(5)){
            case 1:trending = "Reformer";
                break;
            case 2:trending =  "Moderate";
                break;
            case 3:trending = "Conservative";
                break;
            case 4:trending = "Radical";
            break;
            default:trending = "None";
        }
    }
    
    public static int presCdown = 84;
    public static int chairCdown = 36;
    public static int chiefCdown = 48;
    public static int PMCdown = 60;
    public static int comCdown = 24;
    
    public static String uput="";
    
    
	public static void main(String[] args) {
	    for(int i=0; i<20;i++){
		    standingCommittee.add(new Person(nameGen()));
		}
	    electPres();
	    appointVP();
	    
	    electChair();
	    appointDC();
	    
	    electChief();
	    appointSecond();
	    
	    electPrem();
	    appointDPM();
	    
	    electCom();
	    appointViceCom();
	    
	    while(true){
	    System.out.println(Months[month]+ ", "+ year);
	    
	    paramountLeader();
	    System.out.println("\n\nPresident: "+ president.leadDisplay());
	    System.out.println("    Vice-President: "+ vicePres.leadDisplay());
	    
	    System.out.println("\nParty Chairman: "+ chairman.leadDisplay());
	    System.out.println("    Deputy Chairman: "+ depChair.leadDisplay());
	    
	    System.out.println("\nPrime Minister: "+ premiere.leadDisplay());
	    System.out.println("    Deputy PM: "+ depPrem.leadDisplay());
	    
	    System.out.println("\nDefense Chief: "+ chief.leadDisplay());
	    System.out.println("    Second Chief: "+ second.leadDisplay());
	    
	    System.out.println("\nCommunications Head: "+ comms.leadDisplay());
	    System.out.println("    Vice Head: "+ viceCom.leadDisplay());
		
		System.out.println(standingCommittee.size());
		for(Person per : standingCommittee){
		//    System.out.println(per);
		}
		
		sc.nextLine();
		monthly();
	    }
	}
}
