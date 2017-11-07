/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author acurr
 */
public class Ball{
    double xVel, yVel, x, y;
    //for ball control
    double friction1, friction2;
    HumanPaddle p;
    HumanPaddle p2;
    
    public Ball(HumanPaddle p1, HumanPaddle p2)   {
        this.p = p1;
        this.p2 = p2;
        x = 350;
        y = 250;
        xVel = 2 * getDirection();
        yVel = getDirection();
    }
    
    //get direction of movement
    public int getDirection()    {
        int rand = (int)(Math.random() * 2);
                if(rand == 1)   {
                    return 1;
                } else  {
                    return -1;
                }
    }
    
    public void move()  {
        x += xVel;
        y += yVel;
        //Allows player to control ball trajectory by deflecting the ball while
        //moving
        friction1 = p.yVel;
        friction2 = p2.yVel;
        
        yVel = (y < 50) ? - yVel: yVel;
        yVel = (y > 480) ? - yVel: yVel;
    }
    public void draw(Graphics g)    {
        g.setColor(Color.WHITE);
        //starts at top left corner (,,,) use x and y to find position and size
        g.fillOval((int)x - 10, (int) y - 10, 20, 20);
    }
    
    public void checkPaddleCollision(Paddle p1, Paddle p2)  {
        if (x <= 50 && x >= 30)    {
            if(y > p1.getY() && (y < p1.getY() + 80)) {
                //speeds up ball each time paddle is hit
                xVel = xVel - .5;
                xVel = - xVel;
                yVel = (yVel + friction1) / 1.4;
            }
        } else if(x >= 650 && x <= 680) {
            if(y >= p2.getY() && y <= p2.getY() + 80) {
                //speeds up ball each time a paddle is hit
                xVel = xVel + .5;
                xVel = - xVel;
                yVel = (yVel + friction2) / 1.4;
            }
    }
}
    public int getX()   {
        
        return (int) x;
    }
    public int getY()   {
     return (int) y;   
    }
    
}
