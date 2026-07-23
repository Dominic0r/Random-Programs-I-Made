import java.util.*;
public class Main
{
    public static Scanner sc = new Scanner(System.in);
    public static Random ra = new Random();
    
    public static class Person{
        String fname, lname;
        int ideology, personality, ambition, pragmatism, competence;
        int age;
        
        Map <Person, int> additionalRelations = new HashMap<>();
        
        public Person(String fname, String lname){
            this.fname = fname;
            this.lname = lname;
            ideology = ra.nextInt(100);
            personality = ra.nextInt(100);
            ambition = ra.nextInt(100);
            pragmatism = ra.nextInt(100);
            competence = ra.nextInt(100);
            age += ra.nextInt(30)+30;
        }
        
        public String fullName(){return fname+" "+ lname;}
        
        public String firstName(){return fname;}
        
        public String lastName(){return lname;}
        
        public int getIdeology(){return ideology;}
        
        public int getPersonality(){return personality;}
        
        public int getAmbition(){return ambition;}
        
        public int getPragmatism(){return pragmatism;}
        
        public int getCompetence(){return competence;}
        
        public int getAge(){return age;}
        
        public int getOpinion(Person per){
            int totwei = 0;
            int opin = 0;
            int pragmaPoint = ((100-pragmatism)/10);
            
            opin = (100-Math.abs(per.getIdeology()-this.ideology))* (3+pragmaPoint);
            totwei += 3+pragmaPoint;
            
            opin += (100-Math.abs(per.getPersonality()- this.personality)) * (1+pragmaPoint);
            totwei += 1+pragmaPoint;
            
            opin = opin/totwei;
            
            opin += this.ambition - per.getAmbition();
            
            opin += per.getCompetence() * (this.pragmatism/20);
            
            opin += per.geAge()-this.age;
            
            if(additionalRelations.keySet().contains(per)){
                opin += additionalRelations.get(opin);
            }
            
            return opin;
        }
    }
    
    public static class Faction{
        String name;
        int ideomin, ideomax;
        int members;
        
        List<Person> members = new ArrayList<>();
        
        Person leader;
        
        public Faction(String name, ideomin, ideomax){
            this.name = name;
            this.ideomin = ideomin;
            this.ideomax = ideomax;
        }
        
        public String getName(){return name;}
        public int getIdeoMin(){return ideomin;}
        public int getIdeMax(){return ideomax;}
        
        public boolean withinBounds(Person per){
            return per.getIdeology()> ideomin && per.getIdeology() < ideomax;
        }
        
        public void resetMemberList(){ members.clear();}
        
        public void addMember(Person per){
            if(!members.contains(per)){
                members.add(per);
            }
        }
        
        
    }
    
	public static void main(String[] args) {
		System.out.println("Hello World");
	}
}
