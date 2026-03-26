public class Card{
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
