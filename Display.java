import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Color;
/**
 * Display Class.
 *
 * @author Jesse Leu
 * @version 2018.11.23
 */
public class Display extends JPanel implements Observer {
    /** subject  */
    private Subject subject;
    /** arrayList of elements  */
    private ArrayList<FE> elements;
    /** JFrame  */
    private JFrame frame;
    /**
     * Constructor for objects of class Display
     * @param subject subject
     */
    public Display(Subject subject) {
        this.subject = subject;
        this.subject.addObserver(this);
        elements = new ArrayList<FE>();
        frame = new JFrame(); 
        frame.setSize(500,500);
        frame.setResizable(false);
        frame.setTitle("Display");
        frame.setBackground(Color.WHITE);
        setBackground(Color.WHITE);
        frame.getContentPane().add(this);
    }

    /**
     * update and paint
     *
     */
    public void update() {
        elements = subject.getState();
        repaint();
        frame.setVisible(true);     
    }

    /**
     * override paintComponent 
     * @Param g Graphics g 
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (FE fe:elements){
            fe.draw(g);
        }
    }
}
