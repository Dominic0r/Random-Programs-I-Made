import java.util.*;

public class Main
{
    public static class Card{
        String suit;
        String rank;
        
        public Card(String suit, String rank){
            this.suit=suit;
            this.rank=rank;
        }
        
        public String Suit(){
            return suit;
        }
        
        public String Rank(){
            return rank;
        }
    }
    
    public static List<Card> playerHand = new ArrayList<>();
    
    public static boolean onePair(List<Card> hand){
        boolean isTrue=false;
        for(Card ca : hand){
            for(Card rd: hand){
                if(ca.rank()== rd.rank()){
                    isTrue=true;
                }
            }
        }
        return isTrue;
    }
    
    public static boolean twoPair(List<Card> hand){
        
        numofPairs =0;
        for(Card ca: hand){
            for(Card rd: hand){
                if(ca.rank()==rd.rank()){
                    numofPairs++;
                }
            }
        }
        return numofPairs==2;
    }
    
    public static boolean threeOfAKind(List<Card> hand){
        numofPairs=0;
        for(Card ca: hand){
            for(Card rd: hand){
                if(ca.Rank()==rd.rank()){
                    numofPairs++;
                }
            }
        }
        return numofPairs==3;
    }
    
    public static boolean straight(List<Card> hand){
        String validSequence = "23456789TJQKA";
        String sequence="";
        for(Card ca : hand){
            sequence+= ca.Rank();
        }
        
        return validSequence.contains(sequence);
    }
    
    public static boolean Flush(List<Card> hand){
        boolean sameSuit=true;
        
        for(Card ca: hand){
            for(Card rd: hand){
                if(ca.Suit() != rd.Suit()){
                    sameSuit=false;
                }
            }
        }
        
        return sameSuit;
    }
    
    public static boolean fullHouse(List<Card> hand){
        int numOfCards1 = 0;
        int numOfCards2 = 0;
        
        for(Card ca: hand){
            for(Card rd: hand){
                if(ca.Rank()==rd.Rank()){
                    numOfCards1++;
                }
            }
        }
        
        for(Card ca: hand){
            for(Card rd: hand){
                if(ca.Rank()==rd.Rank()){
                    numOfCards2++;
                }
            }
        }
        
        boolean valid = false;
        if((numOfCards1==3 && numOfCards2==2)||(numOfCards2==3 && numOfCards1==2) ){
            valid= true;
        }
        return valid;
    }
    
    public static boolean fourOfAKind(List<Card> hand){
        numofPairs=0;
        for(Card ca: hand){
            for(Card rd: hand){
                if(ca.Rank()==rd.rank()){
                    numofPairs++;
                }
            }
        }
        return numofPairs==4;
    }
    
    public static boolean straightFlush(List<Card> hand){
        boolean SFlush = straight(hand) && Flush(hand);
        return SFlush;
    }
    
    public static boolean RoyalFlush(List<Card> hand){
        boolean RFlush = Flush(hand);
        String validSequence="TJQKA";
        
        String sequence="";
        for(Card ca: hand){
            sequence+= ca.Rank();
        }
        RFlush = validSequence.equals(sequence);
        return RFlush;
        
    }
    
    public static List<Card> deck= new ArrayList<>();
    
    public static void setupDeck(){
        String[] suits = {"C","S","H","D"};
        String[] ranks = {"2","3","4","5","6","7","8","9","T","J","Q","K","A"};
        for(int i=0; i<4;i++){
            for(int c=0; c<13;c++){
                deck.add(new Card(suits[i],ranks[c]));
            }
        }
    }
    
	public static void main(String[] args) {
		
	}
}
