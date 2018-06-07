import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bullet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bullet extends Actor
{
    public void act() 
    {
       this.setLocation(getX(), getY() - 1);
       if(getY() <= 1)
       {
            this.getWorld().removeObject(this);
       }
       else
       {
           Alien aliens = (Alien) getOneIntersectingObject(Alien.class);
           if(aliens != null)
           {
            getWorld().removeObject(this);
            aliens.hit() ;
           }
           
       } 
    }
}
