package ai.sodoku;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author SONY VAIO
 */
public class SodokuGUI extends JPanel implements MouseListener, KeyListener {

    public static final int CELL_SIZE = 40;
    public static final int CELL_SPACE = 2;
    public static final String [] COLOR_CODE = {"#FF0000", "#00FFFF", "#0000FF", "#0000A0",
    "#FF0080", "#800080", "#FFFF00", "#00FF00", "#FF00FF", "#C0C0C0", "#808080", "#FF8040",
    "#804000", "#800000", "#808000", "#408080", "#F6358A", "#F535AA", "#E238EC", "#461B7E"};

    private int xmp = -1, ymp = -1;
    private final String opcode = "0123456789ABCDEFGHIJKLMNOPQRSTVWXYZ";

    private Sodoku ksdk = null;
    private int size;


    private Thread GuiThread = null;
    private Thread thread = null;
    private long TimeSleep = 200;


    public SodokuGUI(int size)
    {
        MainPanel(size);
        System.out.println(this.getName());
        this.addMouseListener(this);
        GuiThread = new Thread(new Runnable() {
            public void run() {
                while(true)
                {
                    SodokuGUI.this.repaint();
                    try {
                        Thread.sleep(TimeSleep);
                    } catch (InterruptedException IEx) {
                        JOptionPane.showMessageDialog(SodokuGUI.this, "Error call Thread.sleep() in GuiThread", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }});
        GuiThread.start();
    }

    public void MainPanel(int size)
    {
        this.ksdk = new Sodoku(size);
        this.ksdk.solver();
        this.ksdk.print();
        this.ksdk.Generate();
        this.size = size;
    }

    public void paint(Graphics g)
    {
        int sqrt = (int)Math.sqrt(size);
        g.setFont(Font.decode("Arial-Bold-15"));
        g.setColor(Color.red);
        g.fillRect(0, 0, size*CELL_SIZE+2*(sqrt)*CELL_SPACE, size*CELL_SIZE+2*(sqrt)*CELL_SPACE);
        for(int ix=0; ix<size; ix++)
            for(int iy=0; iy<size; iy++) {
                int ixp = ix-ix%sqrt;
                int iyp = iy-iy%sqrt;
                g.setColor(Color.WHITE);
                g.fillRect(iy*CELL_SIZE + iyp*CELL_SPACE, ix*CELL_SIZE + ixp*CELL_SPACE, CELL_SIZE, CELL_SIZE);
                g.setColor(Color.GRAY);
                g.drawRect(iy*CELL_SIZE + iyp*CELL_SPACE, ix*CELL_SIZE + ixp*CELL_SPACE, CELL_SIZE, CELL_SIZE);
                g.setColor(Color.decode(COLOR_CODE[ ksdk.get(ix, iy) ]));
                if(ksdk.get(ix, iy) != 0)
                    g.drawString(String.format("%d", ksdk.get(ix, iy)),
                            iy*CELL_SIZE + iyp*CELL_SPACE + (int)(CELL_SIZE/2.5) - 3,
                            ix*CELL_SIZE + ixp*CELL_SPACE + (int)(CELL_SIZE/1.8) + 3);
            }
        if(xmp != -1) {
            int yp = ymp-ymp%sqrt;
            int xp = xmp-xmp%sqrt;
            g.setColor(Color.YELLOW);
            g.drawRect(xmp*CELL_SIZE + xp*CELL_SPACE, ymp*CELL_SIZE + yp*CELL_SPACE, CELL_SIZE, CELL_SIZE);
            g.drawRoundRect(xmp*CELL_SIZE + xp*CELL_SPACE + 2, ymp*CELL_SIZE + yp*CELL_SPACE + 2, CELL_SIZE-4, CELL_SIZE-4, 6, 6);
        }
    }

    public void start()
    {
        if(thread != null)
            thread.stop();

        thread = new Thread( new Runnable() {
            public void run() {
                ksdk.solver(TimeSleep);
            }
        });
        thread.start();
    }

    public void stop()
    {
        if(thread != null)
        {
            thread.stop();
        }
    }

    public static void main(String args[])
    {
        SodokuGUI panel = new SodokuGUI(16);
        int w = panel.size*SodokuGUI.CELL_SIZE + (int)(Math.sqrt(panel.size)-1) * SodokuGUI.CELL_SPACE + 60;
        int h = w - 20;

        JFrame frame = new JFrame("Solver Sodoku Game ::: THANH DUY");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds((screenSize.width-w)/2, (screenSize.height-h)/2, w-40, h);

        frame.add(panel);
        frame.addKeyListener(panel);
    }

    public void mouseClicked(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void mousePressed(MouseEvent e) {
//        int sqrt = (int)Math.sqrt(size);
        xmp = (e.getX() - (e.getX()*CELL_SPACE/(CELL_SIZE+CELL_SPACE))) / CELL_SIZE;
        ymp = (e.getY() - (e.getY()*CELL_SPACE/(CELL_SIZE+CELL_SPACE))) / CELL_SIZE;
        repaint();
    }

    public void mouseReleased(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void mouseEntered(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void mouseExited(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyChar();
        int value = opcode.toLowerCase().indexOf(key);
        if( xmp != -1)
            if(ksdk.IsValidValue(value, ymp, xmp))
            {
                ksdk.RemoveConstraint(ymp, xmp);
                ksdk.set(value, ymp, xmp);
                ksdk.ApplyConstraint(ymp, xmp);
                return;
            }
        Toolkit.getDefaultToolkit().beep();
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key)
        {
            case KeyEvent.VK_F2:
                stop();
                MainPanel(size);
                break;
            case KeyEvent.VK_F5:
                start();
                break;
            case KeyEvent.VK_F6:
                stop();
                break;
            case KeyEvent.VK_F12:
                stop();
                ksdk.clean();
                break;
            case KeyEvent.VK_LEFT:
                if(xmp-1 >= 0 && ymp >= 0) xmp--;
                this.repaint();
                break;
            case KeyEvent.VK_RIGHT:
                if(xmp+1 < size && ymp >= 0) xmp++;
                this.repaint();
                break;
            case KeyEvent.VK_UP:
                if(ymp-1 >= 0 && xmp >= 0) ymp--;
                this.repaint();
                break;
            case KeyEvent.VK_DOWN:
                if(ymp+1 < size && xmp >= 0) ymp++;
                this.repaint();
                break;
        }
    }
}

