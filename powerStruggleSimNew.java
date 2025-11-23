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
    }
    
    Person president;
    Person chairman;
    Person chief;
    Person premiere;
    Person comms;
    
    public static String generateRandomEasternName() {
         String[] SURNAMES = {
        "Li", "Wang", "Zhang", "Liu", "Chen", "Yang", "Huang", "Zhao", "Wu", "Zhou",
        "Xu", "Sun", "Ma", "Zhu", "Guo", "He", "Lin", "Gao", "Zheng", "Luo", "Shen"
    };

    String[] GIVEN_NAME_SYLLABLES = {
        "Wei", "Feng", "Jian", "Min", "Lei", "Yan", "Hua", "Jun", "Qing", "Mei",
        "Rong", "Hao", "Bo", "Ting", "Yi", "Xiu", "Shi", "Kai", "Chun", "Jing",
        "Shan", "Tao", "Zhi", "Long", "Fei", "Yu", "Wen", "Zhen", "Ming", "Xing"
    };

        String surname = SURNAMES[ra.nextInt(SURNAMES.length)];

        int givenNameLength = ra.nextInt(3) < 2 ? 2 : 1; 

        StringBuilder givenName = new StringBuilder();
        for (int i = 0; i < givenNameLength; i++) {
            String syllable = GIVEN_NAME_SYLLABLES[ra.nextInt(GIVEN_NAME_SYLLABLES.length)];
            givenName.append(syllable);
        }

        return surname + " " + givenName.toString();
    }
    
    
	public static void main(String[] args) {
		System.out.println("Hello World");
	}
}
