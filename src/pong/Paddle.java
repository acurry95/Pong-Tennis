/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong;
import java.awt.Graphics;
/**
 *
 * @author acurr
 */
public interface Paddle {
    //allows classes to implement Paddle
    public void draw(Graphics g);
    public void move();
    public int getY();
}
