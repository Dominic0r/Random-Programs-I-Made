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
        COU // count status type. used for ammunition and counter status for other statuses
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
        public int stack getStack(){ return getStack;}
        
        public int decayStack(){
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
        toWhom target;
        String description;
        
        public Coin(statusEffect effect, int amtPot, int amtStack, toWhom target, String description){
            this.effect = effect;
            this.amtPot = amtPot;
            this.amtStack = amtStack;
            this.target = target;
            this.description = description;
        }
        
        public statusEffect applies(){ return effect;}
        public int getAmtPot(){ return amtPot;}
        public int getAmtStack(){ return amtStack;}
        public toWhom targets(){ return target;}
        public String getDesc(){ return description;}
        
        
        public boolean coinToss(int morale){
            tresh = 50 + morale;
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
    
	public static void main(String[] args) {
		System.out.println("Hello World");
	}
}
