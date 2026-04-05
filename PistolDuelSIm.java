import java.util.*;
public class Main
{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Random ra = new Random();
		long startTime = System.currentTimeMillis();
		long timeLeft=15000;
		long nextPace=1000;
		long endTime;
		long duration;
		
		boolean locked = false;
		while(true){
		    
		    startTime = System.currentTimeMillis();
		    System.out.println("Time until Firing: "+ timeLeft);
		    System.out.println("Time until next pace: "+ nextPace);
		    System.out.println("===================================");
		    System.out.println("1- Heavily Breathe");
		    System.out.println("2- Throw Insult");
		    if(locked){
		        System.out.println("3- Fire");
		    }else{
		        System.out.println("3- Lock Pistol");
		    }
		    sc.nextLine();
		    
		    endTime = System.currentTimeMillis();
		    duration = endTime-startTime;
		    System.out.println(duration+ " milliseconds");
		    nextPace-=duration;
		    timeLeft-=duration;
		    if(nextPace<=0){
		        nextPace=5000;
		    }
		    if(timeLeft<=0){
		        break;
		    }
		}
		startTime = System.currentTimeMillis();
		System.out.println("FIRE");
		sc.nextLine();
		endTime = System.currentTimeMillis();
		duration = endTime-startTime;
		if(timeLeft<0){
		    duration-=timeLeft;
		}
		if(duration <=500 + ra.nextInt(10)){
		    System.out.println("YOU WIN!");
		}else{
		    System.out.println("YOU LOSE!");
		}
	}
}
