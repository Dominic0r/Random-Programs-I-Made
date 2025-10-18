import java.util.*;
public class Main
{
    public static Random ra = new Random();
    
    
    
    
    public static class Bot{
        Map<Choice, Integer> botChoices = new LinkedHashMap<>();
        List<Choice> playerHistory = new List<>();
        public Bot(){
            botChoices.put(rock, 33);
            botChoices.put(paper, 33);
            botChoices.put(scissors, 33);
        }
        
        public Choice makeMove(){
            int total = 0;
            
            for(Choice chance : botChoices.keySet()){
                total += botChoices.get(chance);
            }
            int roll = ra.nextInt(total);
            int cumulative = 0;
            for(Choice chance : botChoices.keySet()){
                cumulative += botChoices.get(chance);
                if(roll < cumulative){
                    return chance;
                }
            }
            
            return null;
        }
        
        public void addToHistory(Choice playerMove){
            playerHistory.add(playerMove);
        }
        
        
        
        public void reviewLastTurn(Choice winningMove, boolean won){
            int temp = 0;
            Choice tmp = null;
            for(Choice chance : botChoices.keySet()){
                if(winningMove == chance){
                    temp = botChoices.get(chance);
                    tmp = chance;
                    
                }else{
                    botChoices.put(chance, botChoices.get(chance)-2); // unexpected type error here
                }
            }
            
            if(temp != 0){
                botChoices.remove(temp);
                if(won){
                    botChoices.put(tmp, temp+4);
                }else{
                    botChoices.put(tmp.beat.beat, temp+4);
                }
            }
        }
        
        public void debugChances(){
            for(Choice chance : botChoices.keySet()){
                System.out.println(chance.name + ": "+ botChoices.get(chance));
            }
        }
    }
    
    public static class Choice{
        public String name;
        public Choice beat; // what move this choice beats
        public Choice(String name){
            this.name = name;
        }
        
        public void beats(Choice move){
            beat = move;
        }
    }
    
    public static Choice rock = new Choice("Rock");
    public static Choice paper = new Choice("Paper");
    public static Choice scissors = new Choice("Scissors");
    
    public static void declareBeats(){
        rock.beats(scissors);
        scissors.beats(paper);
        paper.beats(rock);
    }
    
    public static Bot RPSBot = new Bot();
    
    public static Choice playerMove(){
        boolean madeChoice = false;
        Choice plChoice = null;
        Scanner sc = new Scanner(System.in);
        do{
            madeChoice = true;
            System.out.println("1- Rock, 2- Paper, 3- Scissors");
            switch(sc.nextInt()){
                case 1: plChoice = rock;
                break;
                case 2: plChoice = paper;
                break;
                case 3: plChoice = scissors; 
                break;
                default:
                System.out.println("Invalid input");
                madeChoice = false;
            }
            sc.nextLine();
        }while(!madeChoice);
        return plChoice;
    }
    
    public static int playerScore = 0;
    public static int botScore = 0;
    
	public static void main(String[] args) {
	    declareBeats();
		while(true){
		    System.out.println("Player: "+ playerScore+ " || Bot: "+ botScore);
		    //RPSBot.debugChances();
		    Choice player = playerMove();
		    Choice bot = RPSBot.makeMove();
		    Choice winningMove = null;
		    boolean won = false;
		    
		    System.out.println("\nPlayer Move: "+ player.name);
		    System.out.println("Bot Move   : "+ bot.name);
		    if(bot == player.beat){
		        playerScore++;
		        winningMove = player;
		        System.out.println("A");
		    } else if(bot.beat == player){
		        botScore++;
		        winningMove = bot;
		        won = true;
		        System.out.println("B");
		    }else{
		        System.out.println("C");
		        winningMove = player;
		        
		    }
		    if(winningMove!=null){
		        RPSBot.reviewLastTurn(winningMove, won);
		    }
		    
		}
	}
}
