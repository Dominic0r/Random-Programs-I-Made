import java.util.*;

public class Main
{
    public static Random ra = new Random();
    public static Scanner sc = new Scanner(System.in);
    public static class statusEffect{
        String name, description;
        
        boolean decays; // whether or not it decays every turn 
        int limit;
        
        private Consumer<Battlefield> onTurnStart;
        private Consumer<Battlefield> onHitReceived;
        private Consumer<Battlefield> onTurnEnd;
        private Consumer<Battlefield> onClash;
        private Consumer<Battlefield> onHitGive;
        
        public statusEffect(String name, boolean decays, int limit, String description){
            this.name = name;
            this.description = description;
            this.decays = decays;
            this.limit = limit;
            
        }
        
        public StatusEffect setOnTurnStart(Consumer<Battlefield> hook) {
            this.onTurnStart = hook;
            return this;
        }

        public StatusEffect setOnHitReceived(Consumer<Battlefield> hook) {
            this.onHitReceived = hook;
            return this;
        }
    
        public StatusEffect setOnTurnEnd(Consumer<Battlefield> hook) {
            this.onTurnEnd = hook;
            return this;
        }
        
        public StatusEffect setOnClash(Consumer<Battlefield> hook) {
            this.onClash = hook;
            return this;
        }
        
        public StatusEffect setOnHitGive(Consumer<Battlefield> hook) {
            this.onHitGive = hook;
            return this;
        }
        
        public void triggerTurnStart(Battlefield field) {
            if (onTurnStart != null) onTurnStart.accept(field);
        }
    
        public void triggerOnHitReceived(Battlefield field) {
            if (onHitReceived != null) onHitReceived.accept(field);
        }
    
        public void triggerTurnEnd(Battlefield field) {
            if (onTurnEnd != null) onTurnEnd.accept(field);
        }
        
        public void triggerOnClash(Battlefield field){
            if(onClash != null) onClash.accept(field);
        }
        
        public void triggerOnHitGive(Battlefield field){
            if(onHitGive != null) onHitGive.accept(field);
        }
        
        public String getName(){ return name;}
        public String getDesc(){ return description;}
        public boolean Decays() { return decays;}
        public int getLimit() { return limit;}
    }
    
    public static class appliedEffect{ // created on unit
        statusEffect effect;
        int potency, // the amount of the effect per stack 
        stack; // the total amount of stack 
        Unit appliedBy;
        
        public appliedEffect(statusEffect effect, int potency, int stack, Unit appliedBy){
            this.effect = effect;
            this.potency = potency;
            this.stack = stack;
            this.appliedBy = appliedBy;
        }
        
        public statusEffect stat(){ return effect;}
        public int getPotency(){ return potency;}
        public int stack getStack(){ return stack;}
        public Unit effectSource(){return appliedBy;}
        
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
        
        private Consumer<combatContext> onHitEffect;
        int atkPoints; // attack points
        
        String description;
        
        public Coin(int atkPoints, String description){
            this(atkPoints, description, ctx ->{});
        }
        
        public Coin (int atkPoints, String description, Consumer<combatContext> onHitEffect){
            this.atkPoints = atkPoints;
            this.description = description;
            this.onHitEffect = onHitEffect;
        }
        
        public void triggerOnHit(combatContext context){
            if(onHitEffect !=null){
                onHitEffect.accept(context);
            }
        }
        
        public String getDesc(){ return description;}
        public int getAtkPoints() { return atkPoints;}
        
        public boolean coinToss(int morale){
            int tresh = 50 + morale;
            return ra.nextInt(100)< tresh;
        }
        
        public int getCoinPower(int morale){
            int tresh = 50+morale;
            if(ra.nextInt(100)< tresh){
                return atkPoints;
            }else{
                return 0;
            }
        }
    }
    
    public static class Move{ 
        String name;
        String description;
        List <Coin> coinSet = new ArrayList<>();
        int baseatk;
        
        public Move(String name, int baseatk, String description){
            this.name = name;
            this.description = description;
            this.baseatk = baseatk;
        }
        
        public String getName(){return name;}
        public String getDesc(){ return description;}
        public int getBaseAtk(){ return baseatk;}
        
        public void addCoin(Coin toAdd){
            coinSet.add(toAdd);
        }
        
        public List<Coin> getCoinSet(){ return coinSet;}
        
        
        public List<Coin> getClashCoins(){ // USE THIS FOR BATTLES
            return new ArrayList<>(this.coinSet);
        }
    }
    
    public static class Unit{
        int maxHP, hp, morale, speed;
        String name, description;
        
        List<appliedEffect> effectsOnUnit = new ArrayList<>();
        
        List<Move> moveSet = new ArrayList<>();
        
        public Unit(int hp, int morale, int speed, String name, String description, List<Move> moveSet{
            this.hp = hp;
            this.maxHP = hp;
            this.morale = moralel
            this.speed = speed;
            this.name = name;
            this.description = description;
            this.moveSet = moveSet;
        }
        
        public int getHP(){ return hp;}
        public int getMorale(){return morale;}
        public int getSpeed(){return speed;}
        public String getName(){return name;}
        public description getDesc(){return desc;}
        public List<Move> getMoveSet(){ return moveSet;}
        public List<appliedEffect> getEffectList(){ return effectsOnUnit;}
        
        
        public void applyEffect(statusEffect effect, int potency, int stack){
            boolean isAlreadyApplied = false;
            for(appliedEffect AE : effectsOnUnit){
                if(AE.stat() == effect){
                    isAlreadyApplied = true;
                    AE.changeStack(stack);
                    AE.changePotency(potency);
                    
                }
            }
            if(!isAlreadyApplied){
            effectsOnUnit.add(new appliedEffect(effect, potency, stack));
            }
        }
        
        public void takeHPDamage(int dam){
            hp -= dam;
        }
        public void takeMoraleDamage(int dam){
            morale -= dam;
        }
        
        public void statLimiter(){
            if(morale < -45){
                morale = -45;
            }
            if(morale > 45){
                morale = 45;
            }
            
            if(hp > maxHP){
                hp = maxHP;
            }
        }
    }
    
    public static class Battlefield{
        List<Unit> allies = new ArrayList<>();
        List<Unit> enemies = new ArrayList<>();
        int turnCount;
        
        public Battlefield(List<Unit> allies, List<Unit> enemies, int turnCount){
            this.allies = allies;
            this.enemies = enemies;
            this.turnCount = turnCount;
        }
        
        public List<Unit> getAllies(){return allies;}
        public List<Unit> getEnemies(){return enemies;}
        public int getTurnCount(){return turnCount;}
    }
    
    public static class combatContext{
        Unit attacker;
        Move attackerMove;
        Unit defender;
        Move defenderMove;
        int totalDamage;
        Battlefield field;
        
        public combatContext (Unit attacker, Move attackerMove, Unit defender, Move defenderMove, Battlefield field){
            this.attacker = attacker;
            this.attackerMove = attackerMove;
            this.defender = defender;
            this.defenderMove = defenderMove;
            this.field = field;
        }
        public Unit getAttacker(){ return attacker;}
        public Move getAttackerMove(){ return attackerMove;}
        public Unit getDefender(){ return defender;}
        public Move getDefenderMove(){ return defenderMove;}
        public int totalDamage(){ return totalDamage;}
        public Battlefield field() {return field;}
        
    }
    
    
    public static class clashResult{
        Unit winner;
        Unit loser;
        int remainingCoins;
        
        public clashResult(Unit winner, Unit loser, int remainingCoins){
            this.winner = winner;
            this.loser = loser;
            this.remainingCoins = remainingCoins;
        }
        
        public Unit getWinner(){ return winner;}
        public Unit getLoser(){ return loser;}
        public int getRemCoins() { return remainingCoins;}
    }
    
    public clashResult clashFunction(Battlefield field, combatContext comctx){
        List<Coin> attackerCoinSet = comctx.getAttackerMove().getClashCoins();
        List<Coin> defenderCoinSet = comctx.getDefenderMove().getClashCoins();
        
        int attackerCoinCount = attackerCoinSet.size();
        int defenderCoinCount = defenderCoinSet.size();
        
        int currentAttackerPoints = 0;
        int currentDefenderPoints = 0;
        
        boolean bothStillHaveCoins = true;
        do{
            currentAttackerPoints = 0;
            currentDefenderPoints = 0;
            
            for(Coin co: attackerCoinSet){
                currentAttackerPoints += co.getCoinPower();
            }
            
            for(Coin co: defenderCoinSet){
                currentDefenderPoints += co.getCoinPower();
            }
            
            if(currentAttackerPoints == currentDefenderPoints){
                
            }else{
                if(currentAttackerPoints > currentDefenderPoints){
                    defenderCoinSet.remove(defenderCoinSet.getSize()-1);
                    defenderCoinCount--;
                }else{
                    attackerCoinSet.remove(attackerCoinSet.getSize()-1);
                    attackerCoinCount--;
                }
            }
            
            
            for(appliedEffect app : ctx.getAttacker().getEffectList()){
                app.stat().triggerOnClash();
            }
            
            for(appliedEffect app : ctx.getDefender().getEffectList()){
                app.stat().triggerOnClash();
            }
            
            
            bothStillHaveCoins = (attackerCoinCount> 0) && (defenderCoinCount > 0);
        }while(bothStillHaveCoins);
        Unit winner, loser;
        int remainingCoins;
        
        if(attackerCoinCount >0){
            winner = comctx.getAttacker();
            loser = comctx.getDefender();
            remainingCoins = attackerCoinCount;
        }else{
            winner = comctx.getDefender();
            loser = comctx.getAttacker();
            remainingCoins = defenderCoinCount;
        }
        
        return new clashResult(winner, loser, remainingCoins);
    }
    
    public void turnStart(Battlefield field){
        for(Unit un : field.getAllies()){
            for(appliedEffect app : un.getEffectList()){
                app.stat().triggerTurnStart();
            }
        }
        for(Unit un : field.getEnemies()){
            for(appliedEffect app : un.getEffectList()){
                app.stat().triggerTurnStart();
            }
        }
    }
    
    public void turnEnd(Battlefield field){
        for(Unit un : field.getAllies()){
            for(appliedEffect app : un.getEffectList()){
                app.stat().triggerTurnEnd();
            }
        }
        for(Unit un : field.getEnemies()){
            for(appliedEffect app : un.getEffectList()){
                app.stat().triggerTurnEnd();
            }
        }
    }
    
    
    /*
    NOTE FOR CLASH FUNCTION 
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
