import java.util.*;
public class Main
{
	public static void main(String[] args) {
	    Random ra = new Random();
		int totalbet = 100;
		int numofcoins = 10;
		int winpercoin= (totalbet*(25-numofcoins))/numofcoins;
		int totalwins = 0;
		for(int i=0;i<numofcoins;i++){
		    if(ra.nextBoolean()){
		        System.out.print(" Heads (+"+ winpercoin+")");
		        totalwins+= winpercoin;
		    }else{
		        System.out.print(" Tails (-"+ (winpercoin/2)+")");
		        totalwins-=winpercoin/2;
		    }
		}
		
		int coindeduction = (numofcoins/2)+ (totalbet/100);
		System.out.println("\nBet: "+ totalbet);
		System.out.println("Won: "+ totalwins);
		System.out.println("Coin Deduction: "+ coindeduction);
		System.out.println("Profit/Loss: "+ ((totalwins-totalbet)-coindeduction));
		System.out.println("Coins After: "+ (totalwins-coindeduction) );
	}
}
