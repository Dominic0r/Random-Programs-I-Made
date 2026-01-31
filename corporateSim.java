import java.util.*;
public class Main
{
    public static Scanner sc = new Scanner(System.in);
    public static Random ra = new Random();
    
    public static Person{
        int tenure;
        int influence;
        int stockSupport; // percentage of support among all stockholders
        int boardseats; // seats in the board of directors
        int momentum;
        String name;
        public Person(String name){
            this.name = name;
            tenure = stockSupport = boardseats = momentum = influence =0;
        }
        
        public static int getTenure() {return tenure;}
        public static int getInfluence(){ return influence;}
        public static int getStockSupport(){ return stockSupport;}
        public static int getBoardSeats(){ return boardseats;}
        public static int getMomentum(){ return momentum;}
        public static String getName(){return name;}
        
        public static void incrememntTenure(){
            tenure++;
        }
        
        public static void addInfluece(int toAdd){
            influence+= toAdd;
        }
        
        public static int giveMomentum(){
            momentum--;
            return momentum;
        }
        
        public static int setStocks
    }
    
	public static void main(String[] args) {
		System.out.println("Hello World");
	}
}
