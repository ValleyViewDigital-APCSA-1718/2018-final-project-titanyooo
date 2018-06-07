import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Alien extends Actor implements AlienInterface
{
    
    int explosionCount = 0 ; //for explosion
    int moveCount = 0 ; //for moving back and forth
    int moveCountLimit = 50 ; //changes to 100 after first move
    int timesMovedLeft = 0 ; //times enemy moved left
    int timesMovedRight = 0 ; //times enemym moved right
    int attackShipCount = 0 ; //for attacking ship
    int attackShipMoveCount = 0 ; //for each movement during method
    boolean move1 = true ; //move1 should be ready to move
    boolean move2 = false ; //should move back?
    boolean firstMove = true ; //is this the first time moving?
    boolean explode1 = true ; //explosion1 should be ready to go
    boolean finishExplosion = false ; //finish explosion?
    boolean isHit = false ; //is the enemy being hit?
    boolean firstSecond = true ; //this is the first second of the game
    int randomNumber = 0 ; //changed when needed
    int originalX = 0 ; //will always change first second
    int originalY = 0 ; //will always change first second
    boolean forcedShot = false ; //will force enenmy to shoot during dive
    int score = 0;

    public void firstSecond(){ //initializes the original x and y coordinates
    if(firstSecond == true){ 
        originalX = this.getX() ;
        originalY = this.getY() ;
        firstSecond = false ;
    }
   }
   /**
     * Act - do whatever the Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        move();
        moveCount();
        explosionCount() ;
        attackShipCount() ;
        isHit() ;
        shot() ;
        shouldAttackShip();
        firstSecond() ;
        isCollided() ;
    } 
   public void move(){
        
            if(moveCount % 50 == 0 && move1 == true)
            {
                this.setLocation(getX() - 1, getY()) ;
                timesMovedLeft++ ;
                 
                if(moveCount == moveCountLimit)
                {
                   this.setLocation(getX() - 1, getY()) ;
                   timesMovedLeft++ ;
                   move1 = false ; //so can use move2
                   move2 = true ;
                   firstMove = false ;
                   moveCount = 0 ;
                }
            }
            
            if(moveCount % 50 == 0 && move2 == true)
            {
                this.setLocation(getX() + 1, getY()) ;
                timesMovedRight++ ;
            
                if(moveCount == moveCountLimit){
                    timesMovedRight++ ;
                    move1 = true ; //goes back to normal
                    move2 = false ;
                    moveCount = 0 ;
                }
            }
            
            timesMovedLeft = 0 ;
            timesMovedRight = 0 ;
        }
   public void addScore(int points)
    {
       score = score + points;
    }
    
   public void hit(){
        
     isHit = true ;
            
     if(explosionCount == 5 && explode1 == true)
     {
    
            explosionCount = 0 ;
            explode1 = false ;
            finishExplosion = true ;
     }
     if(explosionCount == 5 && finishExplosion == true)
     {
            ((GalagaWorld) getWorld()).addScore(150);
            ((GalagaWorld) getWorld()).subtractFromEnemies();
            getWorld().removeObject(this) ;
     }
   
        
    }
    
    public void explosionCount(){
        if(explosionCount < 5){
        explosionCount++ ;
    }
    else{
        explosionCount = 5;
    }
    }
    
    public void moveCount(){
        
        if(firstMove == true){
            moveCountLimit = 50 ;
        }
        if(firstMove == false){
            moveCountLimit = 100 ;
        }
        if(moveCount < moveCountLimit){
            moveCount++ ;
        }
        if(moveCount == moveCountLimit){
            moveCount = moveCountLimit ;
        }
    }
    
    
    public void isHit(){
        if(isHit == true){
            this.hit() ;
        }
    }
    
    public void shot(){
        
        randomNumber = Greenfoot.getRandomNumber(1000) ;
        
        if((randomNumber == 1 || forcedShot == true) && isHit == false){
        this.getWorld().addObject(new Alien_Bullet(), getX(), getY() + 1) ;
        }
        
    }
    
    boolean firstAttackMove = true ;
    boolean secondAttackMove = false ;
    boolean thirdAttackMove = false ;
    boolean fourthAttackMove = false ;
    boolean fifthAttackMove = false ;
    boolean sixthAttackMove = false ;
    boolean seventhAttackMove = false ;
    boolean eigthAttackMove = false ;
    boolean shouldAttackShip = false ;
    
    
    public void attackShip(){
        
        if(attackShipCount == 3 && firstAttackMove == true){ //first move turn
            if(isHit == true){
                firstAttackMove = false ;
            }
            else{
                this.setLocation(getX(), getY() - 1) ;
                attackShipCount = 0 ;
                attackShipMoveCount++;
                if(attackShipMoveCount == 3){
                    firstAttackMove = false ;
                    secondAttackMove = true ;
                    attackShipMoveCount = 0 ;
                }
            }
        }
        if(attackShipCount == 3 && secondAttackMove == true){ //second move turn
            if(isHit == true){
                secondAttackMove = false ;
            }
            else{
                this.setRotation(45);
                this.setLocation(getX() + 1, getY() - 1) ;
                attackShipCount = 0 ;
                attackShipMoveCount++ ;
                if(attackShipMoveCount == 3){
                    secondAttackMove = false ;
                    thirdAttackMove = true ;
                    attackShipMoveCount = 0 ;
                }
            }
        }
        if(attackShipCount == 3 && thirdAttackMove == true){ //third move turn
            if(isHit == true){
                thirdAttackMove = false ;
            }
            else{
                this.setRotation(90);
                this.setLocation(getX() + 1 , getY());
                attackShipCount = 0 ;
                attackShipMoveCount++ ;
                if(attackShipMoveCount == 3){
                    thirdAttackMove = false ;
                    fourthAttackMove = true ;
                    attackShipMoveCount = 0 ;
                }
            }
        }
        if(attackShipCount == 3 && fourthAttackMove == true){ //forth move turn
            if(isHit == true){
                fourthAttackMove = false ;
            }
            else{
                this.setRotation(135);
                this.setLocation(getX() + 1, getY() + 1) ;
                attackShipCount = 0 ;
                attackShipMoveCount++ ;
                if(attackShipMoveCount == 3){
                    fourthAttackMove = false ;
                    fifthAttackMove = true ;
                    attackShipMoveCount = 0 ;
                }
            }
        }
        if(attackShipCount == 3 && fifthAttackMove == true){ //fifthMove down
            if(isHit == true){
                fifthAttackMove = false ;
            }
            else{
                this.setRotation(180);
                this.setLocation(getX(), getY() + 1) ;
                attackShipCount = 0 ;
                attackShipMoveCount++ ;
                if(attackShipMoveCount == 10){
                    forcedShot = true ;
                    fifthAttackMove = false ;
                    sixthAttackMove = true ;
                    attackShipMoveCount = 0 ;
                }
            }
        }
        if(attackShipCount == 3 && sixthAttackMove == true){ //sixth move down
            if(isHit == true){
                sixthAttackMove = false ;
            }
            else{
                forcedShot = false ;
                this.setRotation(180) ;
                this.setLocation(getX() - 1, getY() + 1) ;
                attackShipCount = 0 ;
                attackShipMoveCount++ ;
                if(attackShipMoveCount == 10){
                    if(((GalagaWorld) getWorld()).getAmountEnemies() <= 10){
                        forcedShot = true ;
                    }
                    sixthAttackMove = false ;
                    seventhAttackMove = true ;
                    attackShipMoveCount = 0 ;
                }
            }
        }
        if(attackShipCount == 3 && seventhAttackMove == true){ //seventh move down
            if(isHit == true){
                seventhAttackMove = false ;
            }
            else{
                forcedShot = false ;
                this.setRotation(180) ;
                this.setLocation(getX() + 1, getY() + 1) ;
                attackShipCount = 0 ;
                attackShipMoveCount++ ;
                if(attackShipMoveCount == 10){
                    seventhAttackMove = false ;
                    eigthAttackMove = true ;
                    attackShipMoveCount = 0 ;
                }
            }
        }
        if(attackShipCount == 3 && eigthAttackMove == true){
            if(isHit == true){
                eigthAttackMove = false ;
            }
            else{
                this.setRotation(180) ;
                this.setLocation(getX(), getY() + 1) ;
                attackShipCount = 0 ;
                if(getY() > 48){
                    this.setLocation(originalX - timesMovedLeft + timesMovedRight,
                                 originalY) ;
                    this.setRotation(0) ;
                    eigthAttackMove = false ;
                    firstAttackMove = true ;
                    shouldAttackShip = false ;
                }
            }
        }
    }
    
    public void attackShipCount(){
        
        if(attackShipCount < 3){
            attackShipCount++;
        }
        else{
            attackShipCount = 3 ;
        }
    }
    
    public void shouldAttackShip(){
        
        randomNumber = Greenfoot.getRandomNumber(1000) ;
        if(randomNumber == 1){
            shouldAttackShip = true ;
        }
        
        if(shouldAttackShip == true){
            this.attackShip() ;
        }
    }
    
    public void isCollided(){
        
        if(isHit == false && shouldAttackShip == true){
        Ship ship = (Ship) getOneIntersectingObject(Ship.class);
    
        if(ship != null){
            ((GalagaWorld) getWorld()).subtractFromEnemies();
            getWorld().removeObject(this) ;
            ship.hit() ;
        }
    }
    }
    
    
}
