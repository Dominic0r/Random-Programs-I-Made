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
        
        @Override
        public String toString(){
            return suit+rank;
        }
    }
    
    public static List<Card> playerHand = new ArrayList<>();
    
    public static boolean onePair(List<Card> hand){
        int numofPairs=0;
        Card ca= hand.get(0);
            for(Card rd: hand){
                if(ca.Rank().equals(rd.Rank())&& rd!=ca ){
                    numofPairs++;
                }
            }
        
        return numofPairs==1;
    }
    
    public static boolean twoPair(List<Card> hand){
        
        int numofPairs =0;
        Card ca= hand.get(0);
            for(Card rd: hand){
                if(ca.Rank().equals(rd.Rank()) && rd!=ca){
                    numofPairs++;
                }
            }
        
        return numofPairs==2;
    }
    
    public static boolean threeOfAKind(List<Card> hand){
        int numofPairs=0;
        Card ca= hand.get(0);
            for(Card rd: hand){
                if(ca.Rank().equals(rd.Rank())&& rd!=ca){
                    numofPairs++;
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
        int nuofCards = 0;
        Card ca= hand.get(0);
            for(Card rd: hand){
                if(!ca.Suit().equals(rd.Suit())){
                    sameSuit=false;
                }else{
                    nuofCards++;
                }
            }
        
        
        return sameSuit&&nuofCards>=5;
    }
    
    public static boolean fullHouse(List<Card> hand){
        int numOfCards1 = 0;
        int numOfCards2 = 0;
        Card ca= hand.get(0);
            for(Card rd: hand){
                if(ca.Rank().equals(rd.Rank())){
                    numOfCards1++;
                }
            }
        
        for(Card cd : hand){
            if(cd !=ca)
            {
                ca = cd;
            }
        }
            for(Card rd: hand){
                if(ca.Rank().equals(rd.Rank())){
                    numOfCards2++;
                }
            }
        
        
        boolean valid = false;
        if((numOfCards1==3 && numOfCards2==2)||(numOfCards2==3 && numOfCards1==2) ){
            valid= true;
        }
        return valid;
    }
    
    public static boolean fourOfAKind(List<Card> hand){
        int numofPairs=0;
        Card ca= hand.get(0);
            for(Card rd: hand){
                if(ca.Rank().equals(rd.Rank())&& rd!=ca){
                    numofPairs++;
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
    public static List<Card> community = new ArrayList<>();
    
    public static void setupDeck(){
        String[] suits = {"C","S","H","D"};
        String[] ranks = {"2","3","4","5","6","7","8","9","T","J","Q","K","A"};
        for(int i=0; i<4;i++){
            for(int c=0; c<13;c++){
                deck.add(new Card(suits[i],ranks[c]));
            }
        }
    }
    
    public static void setupCommunity(){
        Random ra= new Random();
        Card toGet=null;
        for(int i=0; i<5;i++){
            toGet=deck.get(ra.nextInt(deck.size()));
            community.add(toGet);
            deck.remove(toGet);
        }
    }
    
    public static void dealPlayerHand(){
        Random ra= new Random();
        Card toGet=null;
        for(int i=0; i<2;i++){
            toGet=deck.get(ra.nextInt(deck.size()));
            playerHand.add(toGet);
            deck.remove(toGet);
        }
    }
    
	public static void main(String[] args) {
		setupDeck();
		setupCommunity();
		dealPlayerHand();
		for(Card ca: playerHand){
		    System.out.println(ca);
		}
		
		List<String> Hands = new ArrayList<>();
		
		
		if(onePair(playerHand)){
		    Hands.add("One Pair");
		}
		if(twoPair(playerHand)){
		    Hands.add("Two Pair");
		}
		if(threeOfAKind(playerHand)){
		    Hands.add("Three of a Kind");
		}
		if(straight(playerHand)){
		    Hands.add("Straight");
		}
		if(Flush(playerHand)){
		    Hands.add("Flush");
		}
		if(fullHouse(playerHand)){
		    Hands.add("Full House");
		}
		if(fourOfAKind(playerHand)){
		    Hands.add("Four of a Kind");
		}
		if(straightFlush(playerHand)){
		    Hands.add("Straight Flush");
		}
		
		System.out.println("\nAvailable Hands:");
		for(String st : Hands){
		    System.out.println(st);
		}
	}
}
