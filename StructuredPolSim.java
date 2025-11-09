import java.util.*;

public class Main{
	
	public static class ideology{
		int social;
		int economic;
		int autho;
		String name;
		
		public ideology(int social, int economic, int autho, String name){
			this.social = social; // from 1 to 10 with 1 being the most rightist and 10 being the most leftist
			this.economic = economic; // from 1 to 10 with 1 being the most rightist and 10 being the most leftist
			this.autho = autho;
			this.name = name;
		
		public int getSocial(){
			return social;
		}
		
		public int getEconomic(){
			return economic;
		}
		
		public int getAutho(){
			return autho;
		
		public String getName(){
			return name;
		}
		
		public double distanceTo(ideology other){
			int ds = this.social - other.social;
			int de = this.economic - other.economic;
			int da = this.autho - other.autho;
			return Math.sqrt(ds*ds + de*de + da*da);
		}
	}
	
	public static class socialClass{
		ideology Ideology;
		String name;
		int size;
		public socialClass(ideology Ideology, String name, int size){
			this.Ideology = Ideology;
			this.name= name;
			this.size = size;
		}
		
		public ideology getIdeo(){
			return Ideology;
		}
		
		public String getName(){
			return name;
		}
		
		public int getSize(){
			return size;
		}
	}
	
	public static class person{
		ideology Ideology;
		String name;
		int age;
		
		public person(ideology Ideology, String name, int age){
			this.Ideology = Ideology;
			this.name = name;
			this.age = age;
		}
		
		public ideology getIdeo(){
			return Ideology;
		}
		
		public String getName(){
			return name;
		}
		
		public int getAge(){
			return age;
		}
		
		
	}
	
	public static class party{
		ideology Ideology;
		person Leader;
		String name;
		int age;
		int seats;
		double percentageOfSeats;
		
		public party(String name, ideology Ideology, person Leader){
			this.name = name;
			this.Ideology = Ideology;
			this.Leader = Leader;
			this.age = 0;
			this.seats = 0;
			this.percentageOfSeats = 0.0;
		}
		
		public ideology getIdeo(){
			return Ideology;
		}
		
		public person getLeader(){
			return Leader;
		}
		
		public String getName(){
			return name;
		}
		
		public int getAge(){
			return age;
		}
		
		public int getSeats(){
			return seats;
		}
		
		public String getPercentageOfSeats(){
			return String.format("%.2f", percentageOfSeats); // 
			
		}
		
		
		
		//setters ** miscfunctons
		public void clearSeats(){
			seats = 0;
		}
		public void addSeats(){
			seats++;
		}
		public void setPercentage(int totSeats){
			percentageOfSeats = (seats*100.0)/totSeats; 
		}
		
	}
	
	
	public static ideology LeftPop = new ideology(9,10,8,"Left-Wing Populism"); // social, economic, autho, name
	public static ideology SocDem= new ideology(7,6,2,"Social Democracy"); // social, economic, autho, name
	public static ideology Prog = new ideology(9,5,1,"Progressivism"); // social, economic, autho, name
	public static ideology SocLib = new ideology(6,4,2,"Social Liberalism"); // social, economic, autho, name
	public static ideology MarkLib = new ideology(3,2,1,"Market Liberalism"); // social, economic, autho, name
	public static ideology ModCon = new ideology(4,3,3,"Moderate Conservatism"); // social, economic, autho, name
	public static ideology NeoCon = new ideology(3,4,5,"Neoconservatism"); // social, economic, autho, name
	public static ideology RightPop = new ideology(2,1,8,"Right-Wing Populism"); // social, economic, autho, name
	public static ideology Agra = new ideology(3,6,3, "Agrarianism");
	
	public static socialClass workingClass = new socialClass(SocDem,"Working Class",500); // Ideology, name, size of group
	public static socialClass newMidClass = new socialClass(ModCon,"New Middle Class",300);
	public static socialClass oldMidClass = new socialClass(RightPop,"Old Middle Class",450);
	public static socialClass rural = new socialClass(Agra,"Rural Class",400);
	public static socialClass upperClass = new socialClass(NeoCon,"Upper Class",100);
	
	
	
	
	
	
	public static void main(String[] args){
	}
}
