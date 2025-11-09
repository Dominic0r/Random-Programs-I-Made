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
			}
		public int getSocial(){
			return social;
		}
		
		public int getEconomic(){
			return economic;
		}
		
		public int getAutho(){
			return autho;
		}
		
		public String getName(){
			return name;
		}
		
		public double distanceTo(ideology other){
			int ds = this.social - other.social;
			int de = this.economic - other.economic;
			int da = this.autho - other.autho;
			return (Math.sqrt((ds*ds) + (de*de) + (da*da)))/17.3205081; // <- max distance
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
	
	
	
	public static class party{
		ideology Ideology;
		
		String name;
		int age;
		double seats;
		
		
		public party(String name, ideology Ideology){
			this.name = name;
			this.Ideology = Ideology;
			
			this.age = 0;
			this.seats = 0;
			
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
		
		public double getSeats(){
			return seats;
		}
		
		public String displaySeats(){
			return String.format("%.2f", seats); // 
			
		}
		
		
		
		//setters ** miscfunctons
		public void clearSeats(){
			seats = 0;
		}
		public void setSeats(double seat){
			seats = seat;
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
	
	
	public static party democrats = new party("Democatic Party",SocLib);
	public static party republicans = new party("Republican Party",NeoCon);
	
	public static List<party> allParties = new ArrayList<>();
	public static List<socialClass> allClasses =new ArrayList<>();
	
	public static void partyListMake(){
		party kpd = new party("KPD",LeftPop);
		party spd = new party("SPD",SocDem);
		party ddp = new party("DDP",SocLib);
		party dvp = new party("DVP",MarkLib);
		party z = new party("Z",ModCon);
		party dnvp= new party("DNVP",NeoCon);
		party nsdap = new party("NSDAP",RightPop);
		
		allParties.add(kpd);
		allParties.add(spd);
		allParties.add(ddp);
		allParties.add(dvp);
		allParties.add(z);
		allParties.add(dnvp);
		allParties.add(nsdap);
	}
	
	public static void classListMake(){
		allClasses.add(workingClass);
		allClasses.add(newMidClass);
		allClasses.add(oldMidClass);
		allClasses.add(rural);
		allClasses.add(upperClass);
	}
	
	
	public static void election(){
		HashMap<party, Integer> parties = new HashMap<>();
		int toAdd = 0;
		for(party par: allParties){
			toAdd = 0;
			System.out.println("\n"+par.getName());
			for(socialClass cla : allClasses){
				double ideodif =cla.getSize()-((par.getIdeo().distanceTo(cla.getIdeo()))*cla.getSize());
				toAdd += ideodif;
				System.out.println(cla.getName()+ ": "+ideodif);
			}
			
			parties.put(par, toAdd);
		}
		
		int total = 0;
		for(party par: allParties){
			total += parties.get(par);
		}
		
		for(party par: allParties){
			par.setSeats((double) (parties.get(par)*100)/total);
		}
		
		for(party par: allParties){
			System.out.println(par.getName()+ ": "+ par.displaySeats()+ "% of Seats");
		}
	}
	
	
	public static void main(String[] args){
		classListMake();
		partyListMake();
		election();
	}
}
