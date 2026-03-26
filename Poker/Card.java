import java.util.*;
public class Main
{
    
    public static List<Card> playerHand = new ArrayList<>();
    
    public static boolean checkCommunity(List<Card> set){
        boolean getsFromHand=false;
        for(Card ca: set){
            if(playerHand.contains(ca)){
               getsFromHand=true; 
            }
        }
        return getsFromHand;
    }
    
    public static boolean onePair(List<Card> hand){
        int numofPairs=0;
        List<Card> set = new ArrayList>();
        for(Card ca: hand){
            for(Card rd: hand){
                set.clear();
                set.add(ca);
                set.add(rd);
                if(ca.Rank().equals(rd.Rank())&& rd!=ca && checkCommunity(set)){
                    numofPairs++;
                }
            }
        }
        return numofPairs==1;
    }
    
    public static boolean twoPair(List<Card> hand){
        
        int numofPairs =0;
        List<Card> set = new ArrayList>();
        for(Card ca: hand){
            for(Card rd: hand){
                set.clear();
                set.add(ca);
                set.add(rd);
                if(ca.Rank().equals(rd.Rank()) && rd!=ca && checkCommunity(set)){
                    numofPairs++;
                }
            }
        }
        return numofPairs==2;
    }
    
    public static boolean threeOfAKind(List<Card> hand){
        int numofPairs=0;
        List<Card> set = new ArrayList>();
        for(Card ca: hand){
                set.clear();
                set.add(ca);
                set.add(rd);
            for(Card rd: hand){
                if(ca.Rank().equals(rd.Rank())&& rd!=ca && checkCommunity(set)){
                    numofPairs++;
                }
            }
        }
        return numofPairs==3;
    }
    
    public static boolean straight(List<Card> hand){
        String validSequence = "23456789TJQKA";
        String sequence="";
        List<Card> set = new ArrayList>();
        for(Card ca : hand){
            sequence+= ca.Rank();
            set.add(ca);
        }
        
        return validSequence.contains(sequence) && checkCommunity(set);
    }
    
    public static boolean Flush(List<Card> hand){
        boolean sameSuit=true;
        int nuofCards = 0;
        Card ca= hand.get(0);
        List<Card> set = new ArrayList>();
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
                if(ca.Rank().equals(rd.Rank())&&ca!=rd){
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
                if(ca.Rank().equals(rd.Rank())&&ca!=rd){
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
        for(Card ca: hand){
            for(Card rd: hand){
                if(ca.Rank().equals(rd.Rank())&& rd!=ca){
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
    public static List<Card> community = new ArrayList<>();
    
    public static void setupDeck(){
        String[] suits = {"♣","♠","♥","♦"};
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
		System.out.println("Community Cards:");
		for(Card ca: community){
		    System.out.println(ca);
		}
		System.out.println("Your Cards:");
		for(Card ca: playerHand){
		    System.out.println(ca);
		}
		
		List<String> Hands = new ArrayList<>();
		List<Card> HandCheck1 = new ArrayList<>();
		List<Card> HandCheck2 = new ArrayList<>();
		List<List<Card>>allHands= new ArrayList<>();
		for(Card ca: playerHand){
		    HandCheck1.clear();
		    HandCheck2.clear();
		    HandCheck1.add(ca);
		    HandCheck2.add(ca);
		    int cardsno=0;
		    for(Card rd: community){
		        if(cardsno<3){
		            
		        HandCheck1.add(rd);
		        }
		        if(cardsno>0){
		            HandCheck2.add(rd);
		        }
		        cardsno++;
		        
		    }
		    allHands.add(new ArrayList<Card>(HandCheck1));
		    allHands.add(new ArrayList<Card>(HandCheck2));
		}
		
		for(List<Card> HandCheck : allHands){
		    if(onePair(HandCheck)){
		    Hands.add("One Pair");
		}
		if(twoPair(HandCheck)){
		    Hands.add("Two Pair");
		}
		if(threeOfAKind(HandCheck)){
		    Hands.add("Three of a Kind");
		}
		if(straight(HandCheck)){
		    Hands.add("Straight");
		}
		if(Flush(HandCheck)){
		    Hands.add("Flush");
		}
		if(fullHouse(HandCheck)){
		    Hands.add("Full House");
		}
		if(fourOfAKind(HandCheck)){
		    Hands.add("Four of a Kind");
		}
		if(straightFlush(HandCheck)){
		    Hands.add("Straight Flush");
		}
		}
		
		
		System.out.println("\nAvailable Hands:");
		for(String st : Hands){
		    System.out.println(st);
		}
	}
}
