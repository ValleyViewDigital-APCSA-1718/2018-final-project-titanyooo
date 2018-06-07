import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GalagaWorld extends World
{
    GifImage space = new GifImage("space.gif");//Calls the GifImage class and creates the space variable
    int score = 0 ; //score of game
    int level = 1 ; //level player is on
    int amountOfEnemies = 20 ; //amount of enemies on screen, this is for levels 1-3
    int amountOfLives = 2 ; //amount of lives, may go up or down
    int extraLifeScore = 5000 ;//amount of points needed to get an extra life
    boolean gameOverCheck = true ;//changes false so game over displayed only once
    Score shipScore = new Score();
    Level ActorLevel = new Level();
    NextLevel nextLevelImage = new NextLevel();
    GameOver gameOverImage = new GameOver();
    Life life1 = new Life(); //always start with this life
    Life life2 = new Life(); //always start with this life
    Life life3 = new Life(); //gained if earned extra life

    /**
     * Constructor for objects of class MyWorld.
     */
    
    public GalagaWorld()// Creates a world with 20x20 cels with cell size of 10x10 pixels.
    {    
        super(50, 50, 10); 
            for(int i = 0 ; i < 8 ; i++){ //this adds all enemies
            
        addObject(new Alien1(), 7 + 5*i, 15) ;
        addObject(new Alien2(), 7 + 5*i, 10) ;
        if(i % 2 == 0)
            addObject(new Alien3(), 9 + 5*i, 5) ;
        }
        addObject(new Ship(), 25, 45);//Adds the ship actor to MyWorld
        addObject(this.life1, 1, 48);
        addObject(this.life2, 4, 48);
        
    }
    
    
    //Background animation
    public void gifAnimation()//Sets the background to the space image
    {
        setBackground(space.getCurrentImage());
    }
    
     public void act(){
            drawScore();
            drawLevel();
            checkIfNextLevel();
            checkIfGameOver();
            if(score >= extraLifeScore){
                checkExtraLife();
            }
            gifAnimation();
    }
   
    public void addScore(int points)
    {
       score = score + points;
    }
    public void addToLevel(){
        level = level + 1 ; //level can only go up one at a time
    }

    public void drawScore(){
        addObject(this.shipScore, 3, 1) ;
        shipScore.getImage().clear();
        shipScore.getImage().drawString("Score: " + score, 20, 20) ;
    }
    
    public void drawLevel(){
        addObject(this.ActorLevel, 45, 1) ;
        ActorLevel.getImage().clear();
        ActorLevel.getImage().drawString("Level " + level, 10, 20);
    }

    public void subtractFromEnemies(){
        amountOfEnemies--;
    }
    
    public int getAmountEnemies(){
        return amountOfEnemies ;
    }
    
    public void checkIfNextLevel(){
        if(amountOfEnemies == 0 && level < 20){
            removeObjects(getObjects(Bullet.class));
            removeObjects(getObjects(Alien_Bullet.class));
            displayNextLevel();
            addToLevel();
            repaintEnemies();
            if(level < 3){
                amountOfEnemies = 20 ;
            }
            if(level == 3 || level == 4){
                amountOfEnemies = 28 ;
            }
            if(level >= 5 && level < 10){
                amountOfEnemies = 32 ;
            }
            if(level >= 10 && level < 20){
                amountOfEnemies = 40 ;
            }
            if(level == 20){
                amountOfEnemies = 1 ;
            }
        }
    }
    
    public void checkIfGameOver(){
        if(amountOfLives < 0 && gameOverCheck == true){
            displayGameOver();
            removeObjects(getObjects(Alien.class));
            gameOverCheck = false ;
        }
    }
    
    public void repaintEnemies(){
        if(level >= 1 && level < 20){
            for(int i = 0 ; i < 8 ; i++){
                addObject(new Alien1(), 7 + 5*i, 15) ;
                addObject(new Alien2(), 7 + 5*i, 10) ;
                if(level == 3 || level == 4){ //Alien amount increases after level three
                    addObject(new Alien1(), 7 + 5*i, 20) ;
                    amountOfEnemies = 28 ;
                }
                if(level >= 5 && level < 10){
                    addObject(new Alien1(), 7 + 5*i, 20) ;
                    if(i % 2 == 0){
                        addObject(new Alien3(), 9 + 5*i, 2) ;
                    }
                }
                if(level >= 10){
                    addObject(new Alien1(), 7 + 5*i, 20) ;
                    addObject(new Alien1(), 7 + 5*i, 25) ;
                    if(i % 2 == 0){
                        addObject(new Alien3(), 9 + 5*i, 2) ;
                    }
                }
                if(i % 2 == 0){
                    addObject(new Alien3(), 9 + 5*i, 5) ;
                }
            }
        }
  
    }
    
    public void displayNextLevel(){
        addObject(this.nextLevelImage, 25, 25);
        this.nextLevelImage.displayNextLevel();
        removeObject(this.nextLevelImage);
    }
    
    public void displayGameOver(){
        addObject(this.gameOverImage, 25, 25);
        this.gameOverImage.displayGameOver();
        removeObject(this.gameOverImage);
    }
    
    public void subtractFromLives(){
        amountOfLives--;
    }
    
    public int getAmountOfLives(){
        return amountOfLives ;
    }
    
    public void loseLife(){
        if(amountOfLives == 2){
            removeObject(this.life3);
        }
        if(amountOfLives == 1){ 
            removeObject(this.life2);
        }
        if(amountOfLives == 0){
            removeObject(this.life1);
        }
    }
    
    public void checkExtraLife(){
        if(amountOfLives < 3){
            addExtraLife();
        }
        else{
            extraLifeScore = extraLifeScore + 5000 ;
        }
    }
    
    public void addExtraLife(){
        if(amountOfLives == 2){
            addObject(this.life3, 7, 48) ;
        }
        if(amountOfLives == 1){
            addObject(this.life2, 4, 48) ;
        }
        if(amountOfLives == 0){
            addObject(this.life1, 1, 48) ;
        }
        amountOfLives++;
        extraLifeScore = extraLifeScore + 5000;
    }
    
    public void makeNewShip(){
        removeObjects(getObjects(Alien_Bullet.class));
        addObject(new Ship(), 25, 45) ;
    }
    
    
}
