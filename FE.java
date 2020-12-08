import java.awt.Color;
import java.awt.Graphics;
/**
 * Write a description of class FE here.
 *
 * @author Jesse Leu
 * @version 2018.11.23
 */
public class FE {
    /**X value of center   */
    private int x;
    /**Y value of center   */
    private int y;
    /** radius of  circle  of center   */
    private int radius;
    /** color   color of circle   */
    private Color color;
    /**
     * Constructor for objects of class FE
     * @param  x X value of center 
     * @param  y Y value of center
     * @param  radius  radius of  circle 
     * @param  color   color of circle
     */
    public FE(int x, int y, int radius, Color color) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
    }

    /**
     * draw as the instruction
     * @param  g   Graphics
     */
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x - radius,y - radius,radius * 2,radius * 2);
    }
}
