import java.util.*;
import java.util.regex.*;

public class Main
{
    public static Random ra = new Random();
    public static Scanner sc = new Scanner(System.in);
    
    public static class Stock {
        String name;
        int price;
        int owned;
        int trend; // 1 upward, -1 downward
        
        Stock(String name){
            this.name = name;
            this.price = (ra.nextInt(10)*100) + (ra.nextInt(10)*10)+ ra.nextInt(10);
            this.owned = 0;
            this.trend = (ra.nextInt(10)>5)? 1:-1;
        }
        
        public void changePrice(){
            this.price += (ra.nextInt(Math.round((price*100)/25)+1)) * trend;
        }
        
        public void changeTrend(){
            this.trend = (this.trend == 1)? -1 : 1;
        }
        
        public void buy(int amount){
            this.owned = amount;
        }
        
        public int sell(int amount){
            this.owned -= amount;
            return this.price*amount;
        }
        
        public int getWorth(){
            return this.owned*this.price;
        }
        
        public int getOwned(){
            return this.owned;
        }
        
        public void display(){
            
            if(this.price >=100){
                System.out.println(this.name+ "   | "+ this.price+ "   | "+ this.owned);
            }else if(this.price >=10 &&this.price <100){
                System.out.println(this.name+ "   | "+ this.price+ "    | "+ this.owned);
            }else if(this.price <10){
                System.out.println(this.name+ "   | "+ this.price+ "     | "+ this.owned);
            }
            
        }
    }
    
    public class Player{
        String name;
        int cash;
        int worth;
        
        Player(String name){
            this.name = name;
            this.cash = 10000;
            this.worth = this.cash;
        }
        
        public void updateWorth(){
            int invested=0;
            for(Stock s : stocks){
                invested+=s.getWorth();
            }
            this.worth = invested+cash;
        }
        
        public void updateCash(int change){
            this.cash += change;
        }
    }
    
    public static String[] Days = {
        "SUNDAY",
        "MONDAY",
        "TUESDAY",
        "WEDNESDAY",
        "THURSDAY",
        "FRIDAY",
        "SATURDAY"
    };
    public static int dayIndex = 0;
    
    public static ArrayList<Stock> stocks = new ArrayList<>();
    
    public static String[] abc = new String[26];
    
    static{
        for (int i = 0; i < 26; i++) {
        abc[i] = String.valueOf((char) ('A' + i));
        }
    }
    
    public static void generateStock(){
        int abcsize = abc.length;
        String name =abc[ra.nextInt(abcsize)]+abc[ra.nextInt(abcsize)]+abc[ra.nextInt(abcsize)];
        stocks.add(new Stock(name));
    }
    
	public static void main(String[] args) {
		for(int i=0; i<ra.nextInt(10)+5;i++){
		    generateStock();
		    try{
		    Thread.sleep(500);
		    }catch(Exception e){
		        System.exit(0);
		    }
		}
		boolean game = true;
		boolean valInp = false;
		String uput = "";
		do{
		System.out.println("STOCK | PRICE | OWNED");
		for(Stock s : stocks){
		    s.display();
		}
		
		
		uput = sc.nextLine();
		
		}while(game);
	}
}
