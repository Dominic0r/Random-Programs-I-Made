import java.util.*;
public class Main
{
    public static Random ra = new Random();
    public static Scanner sc = new Scanner(System.in);
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
        
        @Override
        public String toString(){
            return name + ", "+ age+ " ("+ ideology+ ")";
        }
    }
    
    public static String trending ="";
    
    public static Person president;
    public static Person chairman;
    public static Person chief;
    public static Person premiere;
    public static Person comms;
    
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
	public static void main(String[] args) {
		for(int i=0; i<10;i++){
		    standingCommittee.add(new Person(nameGen()));
		}
		
		for(Person per : standingCommittee){
		    System.out.println(per);
		}
	}
}
