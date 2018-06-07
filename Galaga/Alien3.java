import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemy3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Alien3 extends Alien
{
    boolean hitOnce = false ; //hasn't been hit yet    
    int x = 0 ; //changes to one if hit once
    
    public Alien3(){
        super() ;
    }
    
    public void act() 
    {
        move();
        moveCount();
        explosionCount() ;
        isHit() ;
        hitOnce(x) ;
        attackShipCount() ;
        shot() ;
        shouldAttackShip();
        firstSecond() ;
        isCollided();
    }
    
    public void hit(){
        if(hitOnce == false)
        {
            this.setImage("Enemy4.gif");
            x = 1 ;
        }
        if(hitOnce == true)
        {   
           isHit = true ;
           if(explosionCount == 5 && explode1 == true)
           {
                explosionCount = 0 ;
                explode1 = false ;
                finishExplosion = true ;
           }
           if(explosionCount == 5 && finishExplosion == true)
           {
               ((GalagaWorld) getWorld()).addScore(500);
               ((GalagaWorld) getWorld()).subtractFromEnemies();
               getWorld().removeObject(this) ;
           }  
        }
    }
    
    void hitOnce(int x){
        if(x == 0){
            hitOnce = false ;
        }
        if(x == 1){
            hitOnce = true ;
        }
    }
    
}
