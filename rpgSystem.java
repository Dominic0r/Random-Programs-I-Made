import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
public class Main
{
    public static Random ra = new Random();
    public static Scanner sc = new Scanner(System.in);
    public static class statusEffect{
        String name, description;
        
        boolean decays; // whether or not it decays every turn 
        int limit;
        
        private BiConsumer<Battlefield, Unit> onTurnStart;
        private BiConsumer<Battlefield, Unit> onHitReceived;
        private BiConsumer<Battlefield, Unit> onTurnEnd;
        private BiConsumer<Battlefield, Unit> onClash;
        private BiConsumer<Battlefield, Unit> onHitGive;
        
        public statusEffect(String name, boolean decays, int limit, String description){
            this.name = name;
            this.description = description;
            this.decays = decays;
            this.limit = limit;
            
        }
        
        public statusEffect setOnTurnStart(BiConsumer<Battlefield, Unit> hook) {
            this.onTurnStart = hook;
            return this;
        }

        public statusEffect setOnHitReceived(BiConsumer<Battlefield, Unit> hook) {
            this.onHitReceived = hook;
            return this;
        }
    
        public statusEffect setOnTurnEnd(BiConsumer<Battlefield, Unit> hook) {
            this.onTurnEnd = hook;
            return this;
        }
        
        public statusEffect setOnClash(BiConsumer<Battlefield, Unit> hook) {
            this.onClash = hook;
            return this;
        }
        
        public statusEffect setOnHitGive(BiConsumer<Battlefield, Unit> hook) {
            this.onHitGive = hook;
            return this;
        }
        
        public void triggerTurnStart(Battlefield field, Unit un) {
            if (onTurnStart != null) onTurnStart.accept(field, un);
        }
    
        public void triggerOnHitReceived(Battlefield field, Unit un) {
            if (onHitReceived != null) onHitReceived.accept(field, un);
        }
    
        public void triggerTurnEnd(Battlefield field, Unit un) {
            if (onTurnEnd != null) onTurnEnd.accept(field, un);
        }
        
        public void triggerOnClash(Battlefield field, Unit un){
            if(onClash != null) onClash.accept(field, un);
        }
        
        public void triggerOnHitGive(Battlefield field, Unit un){
            if(onHitGive != null) onHitGive.accept(field, un);
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
        public int getStack(){ return stack;}
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
    
    public static enum Type {ADD, REMOVE, MOD_POTENCY, MOD_STACK};
    public static class mutation{
        
        final Type type;
        final int amount;
        final statusEffect effect;
        final Unit source;
        
        public mutation(Type type, int amount, statusEffect effect, Unit source){
            this.type = type;
            this.amount = amount;
            this.effect = effect;
            this.source = source;
        }
        
        public Type getType(){return type;}
        public int getAmount(){ return amount;}
        public statusEffect getEffect(){ return effect;}
        public Unit getSource(){ return source;}
        
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
        
        public int getTotalPoints(){
            int tot = baseatk;
            for(Coin co: coinSet){
                tot+= co.getAtkPoints();
            }
            return tot;
        }
        
        public void addCoin(Coin toAdd){
            coinSet.add(toAdd);
        }
        
        public List<Coin> getCoinSet(){ return coinSet;}
        
        
        public List<Coin> getClashCoins(){ // USE THIS FOR BATTLES
            return new ArrayList<>(this.coinSet);
        }
    }
    
    public static class Unit{
        int maxHP, hp, morale, speed, staggerTresh;
        String name, description;
        
        List<appliedEffect> effectsOnUnit = new ArrayList<>();
        List<mutation> pendingMutations = new ArrayList<>();
        
        
        
        List<Move> moveSet = new ArrayList<>();
        
        boolean isStaggered = false;
        
        
        Move unopposed;
        public Unit(int hp, int morale, int speed, int staggerTresh, String name, String description, List<Move> moveSet){
            this.hp = hp;
            this.maxHP = hp;
            this.morale = morale;
            this.speed = speed;
            this.name = name;
            this.description = description;
            this.moveSet = moveSet;
            this.staggerTresh = staggerTresh;
            unopposed = new Move("...",0,"...");
            unopposed.addCoin(new Coin(0,"..."));
        }
        
        public int getHP(){ return hp;}
        public int getMorale(){return morale;}
        public int getSpeed(){return speed;}
        public int getStaggerTresh(){ return staggerTresh;}
        public String getName(){return name;}
        public String getDesc(){return description;}
        public List<Move> getMoveSet(){ return moveSet;}
        public List<appliedEffect> getEffectList(){ return effectsOnUnit;}
        public boolean staggered(){ return isStaggered;}
        
        public Move unop(){return unopposed;}
        
        public void checkStagger(){
            if(!isStaggered){
                if(hp < staggerTresh){
                    isStaggered = true;
                }
            }else{
                isStaggered = false;
            }
        }
        
        public void setStaggerOn(){ isStaggered = true;}
        
        
        
        public void queueMutation(mutation newMut){
            pendingMutations.add(newMut);
        }
        
        public void applyPendingMutations(){
            for(mutation mut: pendingMutations){
                switch(mut.getType()){
                    case ADD:
                        break;
                    case REMOVE:
                        break;
                    case MOD_STACK:
                        break;
                    case MOD_POTENCY:
                        break;
                }
            }
        }
        
        public void addNewMut(mutation mut){
            applyEffect(mut.getEffect(), 1,1,mut.getSource());
        }
        
        public void removeMut(mutation mut){
            appliedEffect toRemove = null;
            for(appliedEffect app : effectsOnUnit){
                if(app.stat() == mut.getEffect()){
                    toRemove = app;
                }
            }
            effectsOnUnit.remove(toRemove);
        }
        
        public void modifyStack(mutation mut){
            for(appliedEffect app : effectsOnUnit){
                if(app.stat() == mut.getEffect()){
                    app.changeStack(mut.getAmount());
                }
            }
        }
        
        public void modifyPotency(mutation mut){
            for(appliedEffect app : effectsOnUnit){
                if(app.stat() == mut.getEffect()){
                    app.changePotency(mut.getAmount());
                }
            }
        }
        
        public void applyEffect(statusEffect effect, int potency, int stack, Unit source){
            boolean isAlreadyApplied = false;
            for(appliedEffect AE : effectsOnUnit){
                if(AE.stat() == effect){
                    isAlreadyApplied = true;
                    AE.changeStack(stack);
                    AE.changePotency(potency);
                    
                }
            }
            if(!isAlreadyApplied){
            effectsOnUnit.add(new appliedEffect(effect, potency, stack, source));
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
        List<Coin> winnerCoinSet = new ArrayList<>();
        
        public clashResult(Unit winner, Unit loser, int remainingCoins, List<Coin> winnerCoinSet){
            this.winner = winner;
            this.loser = loser;
            this.remainingCoins = remainingCoins;
            this.winnerCoinSet = winnerCoinSet;
        }
        
        public Unit getWinner(){ return winner;}
        public Unit getLoser(){ return loser;}
        public int getRemCoins() { return remainingCoins;}
        public List<Coin> getCoinSet(){ return winnerCoinSet;}
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
                currentAttackerPoints += co.getCoinPower(comctx.getAttacker().getMorale());
            }
            
            for(Coin co: defenderCoinSet){
                currentDefenderPoints += co.getCoinPower(comctx.getDefender().getMorale());
            }
            
            if(currentAttackerPoints == currentDefenderPoints){
                
            }else{
                if(currentAttackerPoints > currentDefenderPoints){
                    defenderCoinSet.remove(defenderCoinSet.size()-1);
                    defenderCoinCount--;
                }else{
                    attackerCoinSet.remove(attackerCoinSet.size()-1);
                    attackerCoinCount--;
                }
            }
            
            
            for(appliedEffect app : comctx.getAttacker().getEffectList()){
                app.stat().triggerOnClash(field, comctx.getAttacker());
            }
            
            for(appliedEffect app : comctx.getDefender().getEffectList()){
                app.stat().triggerOnClash(field, comctx.getDefender());
            }
            
            
            bothStillHaveCoins = (attackerCoinCount> 0) && (defenderCoinCount > 0);
        }while(bothStillHaveCoins);
        Unit winner, loser;
        int remainingCoins;
        List<Coin> winnerCoinSet = new ArrayList<>();
        
        if(attackerCoinCount >0){
            winner = comctx.getAttacker();
            loser = comctx.getDefender();
            remainingCoins = attackerCoinCount;
            winnerCoinSet = attackerCoinSet;
        }else{
            winner = comctx.getDefender();
            loser = comctx.getAttacker();
            remainingCoins = defenderCoinCount;
            winnerCoinSet = defenderCoinSet;
        }
        
        clashResult finalResult = new clashResult(winner, loser, remainingCoins, winnerCoinSet);
        return finalResult;
    }
    
    public void afterClash(clashResult result, Battlefield field, combatContext comctx){
        for(Coin co: result.getCoinSet()){
            co.triggerOnHit(comctx);
            if(co.getCoinPower(result.getWinner().getMorale()) >0){
                
                
                for(appliedEffect app : result.getWinner().getEffectList()){
                    app.stat().triggerOnHitGive(field, result.getWinner());
                }
                
                for(appliedEffect app : result.getLoser().getEffectList()){
                    app.stat().triggerOnHitReceived(field, result.getLoser());
                }
            }
        }
    }
    
    public void turnStart(Battlefield field){
        for(Unit un : field.getAllies()){
            for(appliedEffect app : un.getEffectList()){
                app.stat().triggerTurnStart(field, un);
            }
        }
        for(Unit un : field.getEnemies()){
            for(appliedEffect app : un.getEffectList()){
                app.stat().triggerTurnStart(field, un);
            }
        }
    }
    
    public void turnEnd(Battlefield field){
        for(Unit un : field.getAllies()){
            for(appliedEffect app : un.getEffectList()){
                app.stat().triggerTurnEnd(field, un);
            }
        }
        for(Unit un : field.getEnemies()){
            for(appliedEffect app : un.getEffectList()){
                app.stat().triggerTurnEnd(field, un);
            }
        }
    }
    
    public void keepAllAppliedEffectsInBounds(Battlefield field){
        for(Unit un : field.getAllies()){
            for(appliedEffect app : un.getEffectList()){
                app.keepInBounds();
            }
        }
        
        for(Unit un : field.getEnemies()){
            for(appliedEffect app : un.getEffectList()){
                app.keepInBounds();
            }
        }
        for(appliedEffect app: playerUnit.getEffectList()){
            app.keepInBounds();
        }
    }
    
    public combatContext playerChoose(Battlefield field){
        int counter = 1;
        int coincounter=1;
        System.out.println("Your moves: ");
        for(Move mov : playerUnit.getMoveSet()){
            System.out.println(counter+ ": "+ mov.getName()+ "\n"+mov.getDesc());
            for(Coin co: mov.getCoinSet()){
                System.out.println("Coin "+ coincounter+ ": "+ co.getDesc());
                coincounter++;
            }
            counter++;
            coincounter = 1;
        }
        
        boolean validInput = false;
        int choice;
        do{
            choice = Integer.parseInt(sc.nextLine());
            
            if(choice > counter){
                System.out.println("Invalid input");
                validInput = false;
            }else{
                validInput = true;
            }
            
        }while(!validInput);
        
        Move plyrMv = playerUnit.getMoveSet().get(choice-1);
        counter = 1;
        System.out.println("Choose a target: ");
        for(Unit un: field.getEnemies()){
            System.out.println(counter+": "+ un.getName()+ "\n"+un.getDesc());
            counter++;
        }
        
        do{
            choice = Integer.parseInt(sc.nextLine());
            
            if(choice > counter){
                System.out.println("Invalid input");
                validInput = false;
            }else{
                validInput = true;
            }
            
        }while(!validInput);
        
        Unit targetEnemy = field.getEnemies().get(choice-1);
        
        Move targetMove;
        
        int maxnum=Integer.MIN_VALUE;
        Move maxMove=null;
        for(Move mov: targetEnemy.getMoveSet()){
            if(mov.getTotalPoints()> maxnum){
                maxnum = mov.getTotalPoints();
                maxMove = mov;
            }
        }
        
        targetMove = maxMove;
        combatContext finalCC = new combatContext(playerUnit, plyrMv, targetEnemy, targetMove, field);
        return finalCC;
        
    }
    //public combatContext (Unit attacker, Move attackerMove, Unit defender, Move defenderMove, Battlefield field){
    
    public combatContext allyMove(Battlefield field, Unit un, List<combatContext> attackQueue){
        int maxnum=Integer.MIN_VALUE; // pick their move
        Move maxMove=null;
        for(Move mov: un.getMoveSet()){
            if(mov.getTotalPoints()> maxnum){
                maxnum = mov.getTotalPoints();
                maxMove = mov;
            }
        }
        Move aMov = maxMove; 
        
        Unit targetUn=null;
        
        //pick their target
        boolean alreadyTargeted = false;
        for(Unit enun : field.getEnemies()){
            alreadyTargeted = false;
            for(combatContext comctx : attackQueue){
                if(comctx.getDefender() == enun){
                    alreadyTargeted = true;
                }
            }
            if(!alreadyTargeted){
                targetUn = enun;
            }
        }
        
        boolean secondary = false;
        if(targetUn == null){
            targetUn = field.getEnemies().get(ra.nextInt(field.getEnemies().size()));
            secondary = true;
        }
        
        
        maxnum = Integer.MIN_VALUE;
        for(Move mov: targetUn.getMoveSet()){
            if(mov.getTotalPoints()> maxnum){
                maxnum = mov.getTotalPoints();
                maxMove = mov;
            }
        }
        Move eMov = maxMove; 
        if(secondary){
            eMov = targetUn.unop();
        }
        
        
        combatContext finalCC = new combatContext(un, aMov, targetUn, eMov, field);
        return finalCC;
        
        
    }
    
    public combatContext enemMove(Battlefield field, Unit un, List<combatContext> attackQueue){
        int maxnum=Integer.MIN_VALUE; // pick their move
        Move maxMove=null;
        for(Move mov: un.getMoveSet()){
            if(mov.getTotalPoints()> maxnum){
                maxnum = mov.getTotalPoints();
                maxMove = mov;
            }
        }
        Move aMov = maxMove; 
        Move eMov= null;
        Unit targetUn=null;
        boolean secondary=false;
        //pick their target
        if(field.getAllies().size() >0){
            boolean alreadyTargeted = false;
            for(Unit enun : field.getAllies()){
                alreadyTargeted = false;
                for(combatContext comctx : attackQueue){
                    if(comctx.getDefender() == enun){
                        alreadyTargeted = true;
                    }
                }
                if(!alreadyTargeted){
                    targetUn = enun;
                }
            }
            
            secondary = false;
            if(targetUn == null){
                targetUn = field.getAllies().get(ra.nextInt(field.getAllies().size()));
                secondary = true;
            }
            
            maxnum = Integer.MIN_VALUE;
            for(Move mov: targetUn.getMoveSet()){
                if(mov.getTotalPoints()> maxnum){
                    maxnum = mov.getTotalPoints();
                    maxMove = mov;
                }
            }
            eMov = maxMove; 
        }else{
            targetUn = playerUnit;
        }
        if(secondary){
            eMov = targetUn.unop();
        }
        combatContext finalCC = new combatContext(un, aMov, targetUn, eMov, field);
        return finalCC;
        
        
    }
    
    //public Battlefield(List<Unit> allies, List<Unit> enemies, int turnCount){
    public static Battlefield genBatContext(){
        batContext = new Battlefield(allies, enemies, 1);
        return batContext;
    }
    
    public static Battlefield batContext = genBatContext();
    
    public static List<Unit> allies = new ArrayList<>();
    public static List<Unit> enemies = new ArrayList<>();
    public static int turnCount = 1;
    
    public void battleFlow(Battlefield field){
        List<combatContext> attackQueue = new ArrayList<>();
        turnStart(field);
        keepAllAppliedEffectsInBounds(field);
        
        
        if(!playerUnit.staggered()){
            combatContext playerMove = playerChoose(field);// have player and all NPCs pick move and target. generates combatContext 
            attackQueue.add(playerMove);
        }
        if(field.getAllies().size()+1 > field.getEnemies().size()){
            if(field.getAllies().size()>0){
                for(Unit un: field.getAllies()){
                    if(!un.staggered()){
                    attackQueue.add(allyMove(field, un, attackQueue));
                    }
                }
            }
        }else{
            if(field.getEnemies().size()>0){
                for(Unit un: field.getEnemies()){
                    if(!un.staggered()){
                    attackQueue.add(allyMove(field, un, attackQueue));
                    }
                }
            }
        }
        
        for(combatContext cctx : attackQueue){
            clashResult finalResult = clashFunction(field, cctx);
            
            keepAllAppliedEffectsInBounds(field);
            
            afterClash(finalResult, field, cctx);
            
            keepAllAppliedEffectsInBounds(field);
        }
        
        turnEnd(field);
        
        keepAllAppliedEffectsInBounds(field);
        
    }
    
    public static void checkAllStagger(Battlefield field){
        for(Unit un: field.getAllies()){
            un.checkStagger();
        }
        
        for(Unit un: field.getEnemies()){
            un.checkStagger();
        }
        playerUnit.checkStagger();
    }
    //public Unit(int hp, int morale, int speed, int staggerTresh, String name, String description, List<Move> moveSet){
    
    //public Move(String name, int baseatk, String description){
    //public Coin (int atkPoints, String description, Consumer<combatContext> onHitEffect){
    public static Unit playerUnit;
    
    public static void defPlayerUnit(){
        List<Move> playerMoveSet = new ArrayList<>();
        
        Move multiPunch = new Move("Multi-Punch", 5, "Punches the Enemy 3 times");
        multiPunch.addCoin(new Coin(2, "Punch!", ctx ->{
            ctx.getDefender().takeHPDamage(2);
        }));
        
        multiPunch.addCoin(new Coin(1, "Punch Again!", ctx ->{
            ctx.getDefender().takeHPDamage(1);
        }));
        
        multiPunch.addCoin(new Coin(3, "Upper Cut!", ctx ->{
            ctx.getDefender().takeHPDamage(3);
        }));
        
        Move roundhouse = new Move("Roundhouse Kick", 3, "Kicks the enemy hard");
        roundhouse.addCoin(new Coin(10, "Kick!", ctx ->{
            ctx.getDefender().takeHPDamage(10);
        }));
        
        Move stab = new Move("Stab", 4, "Stabs the enemy twice");
        stab.addCoin(new Coin(3, "Swish!", ctx ->{
            ctx.getDefender().takeHPDamage(3);
        }));
        
        stab.addCoin(new Coin(3, "Slash! - Inflicts 3 bleed potency", ctx ->{
            ctx.getDefender().takeHPDamage(3);
            for(appliedEffect app: ctx.getDefender().getEffectList()){
                if(app.stat()==defaultStatusEffects.get(0)){
                    mutation mut = new mutation(Type.MOD_POTENCY, 3,defaultStatusEffects.get(0), ctx.getAttacker());
                    ctx.getDefender().queueMutation(mut);
                }else{
                    
                }
            }
        }));
        playerMoveSet.add(multiPunch);
        playerMoveSet.add(roundhouse);
        playerMoveSet.add(stab);
        
        playerUnit = new Unit(100, 0, 5, 30, "Player", "Description", playerMoveSet);
    }
    //public statusEffect(String name, boolean decays, int limit, String description){
    //public mutation(Type type, int amount, statusEffect effect, Unit source){
    public static List<statusEffect> defaultStatusEffects = new ArrayList<>();
    public static void defDefaultStats(){
        statusEffect bleed = new statusEffect("Bleed", false, 99, "Take fixed damage every coin toss");
        
        bleed.setOnClash((field, un)->{
            int damagetaken=0;
            for(appliedEffect app : un.getEffectList()){
                if(app.stat() == bleed){
                    damagetaken = app.getPotency();
                    un.takeHPDamage(damagetaken);
                    app.decrementStack();
                }
            }
        });
        
        defaultStatusEffects.add(bleed);
    }
	public static void main(String[] args) {
	    defPlayerUnit();
	    defDefaultStats();
		for(Move mov : playerUnit.getMoveSet()){
		    System.out.println("\n"+mov.getName()+ ": "+ mov.getBaseAtk()+ " (Base) | "+ mov.getTotalPoints()+ " (total)\n"+ mov.getDesc());
		    for(Coin co: mov.getCoinSet()){
		        System.out.println(co.getDesc()+" " + co.getAtkPoints());
		    }
		}
	}
}
