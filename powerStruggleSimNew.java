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
    
    
	public static void main(String[] args) {
		System.out.println("Hello World");
	}
}
