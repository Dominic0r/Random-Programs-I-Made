import java.util.*;

public class Main
{
    public static Random ra = new Random();
    public static Scanner sc = new Scanner(System.in);
    
    enum whenEffect{
        DURING, // status gives effect during combat
        AFTER, // status gives effect after combat
        ONHIT // status gives effect on hit
    }
    
    enum statusType{
        DAM, // damage status type 
        HEA,  // heal status type 
        COU // count status type. used for ammunition and counter status for other statugses
    }
    
    enum effectOn{
        HLT, // health 
        MOR, // morale
        SHK, // shock
        STT, // on another stat's count 
    }
    
    enum toWhom{
        ENM, // applied to enemy | enemy
        ALY, // applied to allies | ally 
        SLF, // applied to self | self 
        ALL, // applied to all | all 
        TEM, // applied to self and allies | team 
        SUI, // applied to self and enemies | suicidal 
        ABM // applied to allies and enemies | all but me 
    }
    
    public static class statusEffect{
        String name, description;
        whenEffect when;
        statusType type;
        effectOn onWhat;
        boolean decays; // whether or not it decays every turn 
        int limit;
        toWhom target;
        
        
        public statusEffect(String name, whenEffect when, statusType type, effectOn onWhat, boolean decays, int limit, String description){
            this.name = name;
            this.when = when;
            this.type = type;
            this.onWhat = onWhat;
            this.description = description;
            this.decays = decays;
            this.limit = limit;
            
        }
        
        public String getName(){ return name;}
        public String getDesc(){ return description;}
        public whenEffect applyOn(){return when;}
        public statusType getType(){ return type;}
        public effectOn getEffectOn(){ onWhat;}
        public boolean Decays() { return decays;}
        public int getLimit() { return limit;}
    }
    
    public static class appliedEffect{ // created on unit
        statusEffect effect;
        int potency, // the amount of the effect per stack 
        stack; // the total amount of stack 
        
        public appliedEffect(statusEffect effect, int potency, int stack){
            this.effect = effect;
            this.potency = potency;
            this.stack = stack;
        }
        
        public statusEffect stat(){ return effect;}
        public int getPotency(){ return potency;}
        public int stack getStack(){ return stack;}
        
        public void decayStack(){
            if(effect.Decays()){
                stack--;
            }
        }
        
        public boolean stackIsEmpty(){ return stack <=0;}
        
        public void changePotency(int changeBy){
            potency += changeBy;
        }
        
        public void incrementPotency(){ potency++;}
        public void decrementPotency(){ potency--;}
        
        public void changeStack(int changeBy){
            stack += changeBy;
        }
        
        public void incrementStack(){ stack++;}
        public void decrementStack(){ stack--;}
        
        public void keepInBounds(){
            if(stack < 0){
                stack = 0;
            }
            
            if(potency < 1){
                potency = 1;
            }
            
            if(potency > effect.getLimit()){
                potency = effect.getLimit();
            }
            
        }
        
    }
    
    public static class Coin{
        statusEffect effect;
        int amtPot, // amount of potency to apply
        amtStack; // amount of stack to apply
        int atkPoints; // attack points
        
        toWhom target;
        String description;
        
        public Coin(statusEffect effect, int amtPot, int amtStack, int atkPoints, toWhom target, String description){
            this.effect = effect;
            this.amtPot = amtPot;
            this.amtStack = amtStack;
            this.target = target;
            this.description = description;
            this.atkPoints = atkPoints;
        }
        
        public statusEffect applies(){ return effect;}
        public int getAmtPot(){ return amtPot;}
        public int getAmtStack(){ return amtStack;}
        public toWhom targets(){ return target;}
        public String getDesc(){ return description;}
        public int getAtkPoints() { return atkPoints;}
        
        public boolean coinToss(int morale){
            int tresh = 50 + morale;
            return ra.nextInt(100)< tresh;
        }
    }
    
    public static class Move{ 
        String name;
        String description;
        List <Coin> coinSet = new ArrayList<>();
        
        public Move(String name, String description){
            this.name = name;
            this.description = description;
        }
        
        public String getName(){return name;}
        public String getDesc(){ return description;}
        
        public void addCoin(Coin toAdd){
            coinSet.add(toAdd);
        }
        
        public List<Coin> getCoinSet(){ return coinSet;}
        
    }
    
    /*
    NOTE FOR CLASH FUNCTION 
    I plan on implementing a separate clash function that will take into account the moves of both sides. here is its process:
    1: get the number of coins that each move has 
    2: toss the coins, for every head, add the coin's atkPoints to its respective total atkPoint variables
    3: determine who has the most atkPoints and set them as Winner
    4: decrement 1 coin from the loser;
    5: if both sides still have 1 or more coins left, repeat step 1, if not proceed to step 6
    6: begin rolling the remaining coins of Winner
    7: if a coin succesfully rolls, deal its atkPoint and apply the status effect if it has any to its respective targets.
    
    */
    
	public static void main(String[] args) {
		System.out.println("Hello World");
	}
}
