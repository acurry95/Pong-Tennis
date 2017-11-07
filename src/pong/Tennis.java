package pong;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author acurr
 * 
 * create pong game
 */
public class Tennis extends JFrame implements Runnable, KeyListener {
    //size of game window
    final int WIDTH = 700, HEIGHT = 500;
    
    //tracks player scores
    public static int p1Score = 0, p2Score = 0;
    
    Thread thread;
    //creates panel for game window
    JPanel panel;
    //creates objects of paddles and ball
    HumanPaddle p1, p3;
    Ball b1;
    
    //stops game when desired
    boolean gameStarted = false;
    //use image for cleaner graphics
    Graphics gfx;
    Image img;
    
    public Tennis() {
        //constructor calls init class 
        init();
    }
    //creates window which allows user to see the game
    public void init()  {
        //all used to add the panel
        panel = new JPanel();
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        add(panel);
        //adds key listener 
        this.addKeyListener(this);
        //creates humanpaddle ball and AI paddle
        p1 = new HumanPaddle(1);
        p3 = new HumanPaddle(2);
        b1 = new Ball(p1, p3);
        
        //creates image which is used for graphics
        img = createImage(WIDTH, HEIGHT);
        gfx = img.getGraphics();
        //thread calls run() which starts the game loop
        thread = new Thread(this);
        thread.start();
    }
    @Override
    public void paint(Graphics g) {
        //sets Background color
        gfx.setColor(Color.BLACK);
        gfx.fillRect(0,0,WIDTH,HEIGHT);
        
        //if/elseif are called when a player scores
        if(b1.getX() < -10) {
            //increments player score and calls score method which resets game
            p2Score++;
            Score();
        } else if(b1.getX() > 710) {
            p1Score++;
            Score();  
        }
        //if no player is scoring, draw the objects
        else    {
        p1.draw(gfx);
        b1.draw(gfx);
        p3.draw(gfx);
        }
        //if game is not started and no one has won, tell user how to start
        if(!gameStarted && p1Score < 7 && p2Score < 7)    {
            gfx.setColor(Color.MAGENTA);
            gfx.drawString("Press Enter to Begin...", 290, 200);
            //called if player wins
        }else if(p1Score == 7)    {
            gfx.drawString("Player 1 Wins: 7 - " + p2Score, 280, 190);
        }
        else if(p2Score == 7)    {
            gfx.drawString("Player 2 Wins: 7 - " + p1Score, 280, 190);
        }
        //update image for current graphics
        g.drawImage(img, 0, 0, this);
    }
    //calls paint method passing g as Graphics
    public void update(Graphics g)    {
        paint(g);
    }
    //run method called when thread was started
    public void run()   {
        //infinate game loop
        for(;;) {
            //if game is currently running
            if(gameStarted) {
            //calls move() methods which contain formulas for moving ball and paddles
            p1.move();
            b1.move();
            p3.move();
            //calls checkP..() to make sure ball does not go through paddles
            b1.checkPaddleCollision(p1, p3);
            }
            //calls repaint to redraw objects in their new location
            repaint();
            
            //sets a .01 second delay for loop
            try {
            Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
//action event listeners for keys being pressed    
    public void keyPressed(KeyEvent e)   {
        if(e.getKeyCode() == KeyEvent.VK_W)    {
            p1.setUpAccel(true);
        }else if(e.getKeyCode() == KeyEvent.VK_S)    {
            p1.setDownAccel(true);
        } else if(e.getKeyCode() == KeyEvent.VK_ENTER)  {
            if(p1Score < 7 && p2Score < 7)  {
            gameStarted = true;
            }
        } else if(e.getKeyCode() == KeyEvent.VK_UP) {
            p3.setUpAccel(true);
        } else if(e.getKeyCode() == KeyEvent.VK_DOWN)   {
            p3.setDownAccel(true);
        }
    }
    public void keyReleased(KeyEvent e)  {
        if(e.getKeyCode() == KeyEvent.VK_W)    {
           p1.setDownAccel(false);
           p1.setUpAccel(false);
        }else if(e.getKeyCode() == KeyEvent.VK_S)    {
            p1.setUpAccel(false);
            p1.setDownAccel(false);
        } 
        else if(e.getKeyCode() == KeyEvent.VK_UP) {
            p3.setUpAccel(false);
            p3.setDownAccel(false);
        } else if(e.getKeyCode() == KeyEvent.VK_DOWN)   {
            p3.setUpAccel(false);
            p3.setDownAccel(false);
        }
    }
    public void keyTyped(KeyEvent arg0) {   
    }
    //called whenever a user scores to reset gameplay
    public void Score()  {
        gameStarted = false;
        
        //reset ball position;
        b1.x = 350;
        b1.y = 250;
        b1.xVel = 2 * b1.getDirection();
        b1.yVel = b1.getDirection();
        //reset paddle possitions
        p1.y = 210;
        p3.y = 210;
        
    }
    //main class creates Tennis() object
    public static void main(String[] args)  {
        new Tennis();
        
    }
}
