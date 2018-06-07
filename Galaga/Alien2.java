import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Alein2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Alien2 extends Alien
{
    public Alien2()
    {
        super();
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
            ((GalagaWorld) getWorld()).addScore(250);
            ((GalagaWorld) getWorld()).subtractFromEnemies();
            getWorld().removeObject(this) ;
     }
    }
}
