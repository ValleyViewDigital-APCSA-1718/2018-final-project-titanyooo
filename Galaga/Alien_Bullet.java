import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemy_Bullet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Alien_Bullet extends Actor
{
    public void act() 
    {
        this.setLocation(getX(), getY() + 1) ;
        if(getY() > 48){
            this.getWorld().removeObject(this);
        }
        else{
        Ship ship = (Ship) getOneIntersectingObject(Ship.class);
        if(ship != null){
            getWorld().removeObject(this);
            ship.hit() ;
        }
        }
    }   
}
