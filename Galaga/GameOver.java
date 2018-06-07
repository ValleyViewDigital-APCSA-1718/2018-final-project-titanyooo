import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameOverImage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameOver extends Actor
{
    public void act() 
    {
    }
    
    public void displayGameOver(){
        Greenfoot.delay(100);
        this.setImage("GameOverImage2.bmp");
        Greenfoot.delay(100);
        this.setImage("GameOverImage1.bmp");
        Greenfoot.delay(100);
        this.setImage("GameOverImage2.bmp");
        Greenfoot.delay(100);
    }
}
