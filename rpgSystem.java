import java.util.*;

public class Main
{
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
    
    public static class statusEffect{
        String name, description;
        whenEffect when;
        statusType type;
        effectOn onWhat;
        boolean decays; // whether or not it decays every turn 
        int limit;
        
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
        public whenEffect applyOn(){ when;}
        public statusType getType(){ type;}
        public effectOn getEffectOn(){ onWhat;}
        public boolean Decays() { return decays;}
        public int getLimit() { return limit;}
        
    }
    
    public static class appliedEffect{
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
        
    }
    
	public static void main(String[] args) {
		System.out.println("Hello World");
	}
}
