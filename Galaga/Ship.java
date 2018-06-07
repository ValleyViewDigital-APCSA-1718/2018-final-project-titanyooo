import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Ship here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ship extends Actor
{
    int fireCount = 0 ;
    int explosionCount = 0 ;
    boolean explode1 = true ; //explosion1 should be ready to go
    boolean explode2 = false ;
    boolean explode3 = false ;
    boolean explode4 = false ;
    boolean finishExplosion = false ; //finish explosion?
    boolean isHit = false ; //checking if hit
    boolean isDead = false ; //is ship dead?
    /**
     * Act - do whatever the Ship wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        checkKey();
        fireCount();
        explosionCount();
        isHit();
    }   
    
   public void checkKey(){
        
        if(isDead == false){  
            if(Greenfoot.isKeyDown("left")){
                this.setLocation(getX() - 1, getY()) ;
            }
            if(Greenfoot.isKeyDown("right")){
                this.setLocation(getX() + 1, getY()) ;
            }
            if(Greenfoot.isKeyDown("space") && fireCount == 30){
                this.getWorld().addObject(new Bullet(), getX(), getY() - 1) ;
                fireCount = 0 ; //restarts count
            }
        }
        
    }
    
    //Sets limitations for firing. Firing allowed at 30.
    //Called every time during act()
   public void fireCount(){
        if(fireCount < 30){
            fireCount++ ;
        }
        else{
            fireCount = 30 ;
        }
   }
    
   public void hit()
   {
        isHit = true ;
        isDead = true ;
            
        if(explosionCount == 5 && explode1 == true)
        {
            explosionCount = 0 ;
            explode1 = false ;
            explode2 = true ;
        }
        if(explosionCount == 5 && explode2 == true)
        {
            explosionCount = 0 ;
            explode2 = false ;
            explode3 = true ;
        }
        if(explosionCount == 5 && explode3 == true)
        {

            explosionCount = 0 ;
            explode3 = false ;
            explode4 = true ;
        }
        if(explosionCount == 5 && explode4 == true)
        {

            explosionCount = 0 ;
            explode4 = false ;
            finishExplosion = true ;
        }
        if(explosionCount == 5 && finishExplosion == true)
        {
            ((GalagaWorld) getWorld()).subtractFromLives() ;
            ((GalagaWorld) getWorld()).loseLife();
            checkNewLife();
            getWorld().removeObject(this) ;
        }
       
   }
    
   public void explosionCount()
   {
        if(explosionCount < 5) 
        {
            explosionCount++ ;
        }
        else
        {
            explosionCount = 5;
        }
   }
    
   public void isHit()
   {
        if(isHit == true){
            this.hit() ;
        }
   }
   public void checkNewLife()
   {
       if(((GalagaWorld) getWorld()).getAmountOfLives() >= 0){
           ((GalagaWorld) getWorld()).makeNewShip();
       }
   }
   
}
