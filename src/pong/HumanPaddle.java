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
public class HumanPaddle implements Paddle{
    final double GRAVITY = 0.94;
    double y, yVel;
    boolean upAccel, downAccel;
    int player, x;
    public HumanPaddle(int player)    {
        this.player = player;
        upAccel = false; downAccel = false;
        y = 210; yVel = 0;
        //if player == 1, x = 20, else x = 660
        x = (player == 1) ? 20 : 660;
    }
    public void draw(Graphics g)    {
        g.setColor(Color.GREEN);
        g.fillRect(x, (int)y, 20, 80);
        if(player == 1) {
        g.drawString("P1:  " + Tennis.p1Score, 100, 470);
        }
        else if(player == 2) {
            g.drawString("P2:  " + Tennis.p2Score, 600, 470);
        }
    }
    public void move()  {
        if(upAccel) {
            yVel -= 2;
        } else if(downAccel)    {
            yVel += 2;
        } else if(!upAccel && !downAccel) {
            yVel *= GRAVITY;
        }
        if(yVel >= 5)   {
            yVel = 5;
        } else if(yVel <= -5)   {
            yVel = -5;
        }
        y += yVel;
        
        y = (y < 40) ? 40: y; 
        y = (y > 410) ? 410: y;
    }
    
    public void setUpAccel(boolean input)   {
        upAccel = input;
    }
    
    public void setDownAccel(boolean input)   {
        downAccel = input;
    }
    
    public int getY()  {
        return (int)y;
    }
    public int getX()   {
        return x;
    }
}
