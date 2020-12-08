import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Dimension;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JSlider;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JColorChooser;
/**
 * GUI class.
 *
 * @author Jesse Leu
 * @version 2018.11.23
 */
public class GUI extends JFrame {
    /** GenerateFractal    */
    private GenerateFractal gen;
    /** ratio of child   */
    private int myRatio;
    /** how many shapes will be drawn  */
    private int depth;
    /** pad color  */
    private Color padColor;
    /** pear color  */
    private Color pearColor;
    /** angle value */
    private int myAngle;
    /** is user make change after last drawing */
    private boolean isChanged;
    /**
     * Constructor for objects of class GUI
     * @param gen GenerateFractal
     */
    public GUI(GenerateFractal gen) {
        this.gen = gen;
        isChanged = true;
        setTitle("GUI");
        setSize(500,450);
        setResizable(false);
        Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation((size.width - getWidth()) / 2,(size.height - getHeight()) / 2);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        getContentPane().add(panel);

        JSlider depSlide = new JSlider(0,2,10,6);
        depSlide.setPreferredSize(new Dimension(350,50));
        depSlide.setMajorTickSpacing(1);
        depSlide.setPaintTicks(true);  
        depSlide.setPaintLabels(true);  
        getContentPane().add(depSlide);

        JLabel depLabel = new JLabel("Depth: ");
        getContentPane().add(depLabel);

        JTextField text = new JTextField(3);
        getContentPane().add(text);
        text.setText("6");
        depth = 6;
        depSlide.addChangeListener(new ChangeListener() {
                public void stateChanged(ChangeEvent e) {
                    text.setText("" + depSlide.getValue());
                    depth = depSlide.getValue();
                    isChanged = true;
                }
            }
        );

        JSlider ratio = new JSlider(0,40,70,55);
        ratio.setPreferredSize(new Dimension(350,50));
        ratio.setMajorTickSpacing(5);
        ratio.setPaintTicks(true);  
        ratio.setPaintLabels(true);  
        getContentPane().add(ratio);

        JLabel labelRatio = new JLabel("Ratio: ");
        getContentPane().add(labelRatio);

        JTextField textRatio = new JTextField(3);
        getContentPane().add(textRatio);
        textRatio.setText("55");
        myRatio= 55;
        ratio.addChangeListener(new ChangeListener() {
                public void stateChanged(ChangeEvent e) {
                    textRatio.setText("" + ratio.getValue());
                    myRatio = ratio.getValue();
                    isChanged = true;
                }
            }
        );

        JSlider angleSlide = new JSlider(0,30,90,45);
        angleSlide.setPreferredSize(new Dimension(350,50));
        angleSlide.setMajorTickSpacing(5);
        angleSlide.setPaintTicks(true);  
        angleSlide.setPaintLabels(true);  
        getContentPane().add(angleSlide);

        JLabel labelAngle = new JLabel("Angle: ");
        getContentPane().add(labelAngle);   
        JTextField angleText = new JTextField(3);
        getContentPane().add(angleText);
        angleText.setText("45");
        myAngle = 45;
        angleSlide.addChangeListener(new ChangeListener() {
                public void stateChanged(ChangeEvent e) {
                    angleText.setText("" + angleSlide.getValue());
                    myAngle = angleSlide.getValue();
                    isChanged = true;
                }
            }
        );    
        JButton colorPadsButton = new JButton("Pads Color");
        colorPadsButton.setPreferredSize(new Dimension(150,50));
        JColorChooser chooserPads = new JColorChooser();
        chooserPads.setColor(0,153,0);
        padColor = chooserPads.getColor();
        getContentPane().add(colorPadsButton);

        JButton colorPearButton = new JButton("Pear Color");
        colorPearButton.setPreferredSize(new Dimension(150,50));
        JColorChooser chooserPear = new JColorChooser();
        chooserPear.setColor(new Color(203,0,203));
        pearColor = chooserPear.getColor();
        getContentPane().add(colorPearButton);

        JButton generate = new JButton("Draw");
        generate.setPreferredSize(new Dimension(150,100));
        getContentPane().add(generate);
        generate.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    setData();
                    isChanged = false;
                }
            }
        );

        JPanel showPadColor = new JPanel(); 
        showPadColor.setPreferredSize(new Dimension(150,75));
        getContentPane().add(showPadColor);
        showPadColor.setBackground(padColor);
        colorPadsButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {  
                    padColor = chooserPads.showDialog(null, "Choose a color", new Color(0,153,0)); 
                    if (padColor == null) {
                        padColor = new Color(0,153,0);
                    }
                    showPadColor.setBackground(padColor);
                    isChanged = true;
                }
            }
        );

        JPanel showPearColor = new JPanel(); 
        showPearColor.setPreferredSize(new Dimension(150,75));
        showPearColor.setLocation(0,0);
        getContentPane().add(showPearColor);
        showPearColor.setBackground(pearColor);

        colorPearButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    pearColor = chooserPear.showDialog(null, "Choose a color", new Color(203,0,203));
                    if (pearColor == null){
                        pearColor = new Color(203,0,203);
                    }
                    showPearColor.setBackground(pearColor);
                    isChanged = true;
                }

            }
        );

        JPanel empty = new JPanel(); 
        empty.setPreferredSize(new Dimension(150,75));
        getContentPane().add(empty);
        empty.setBackground(null);

        setLayout(new FlowLayout());
        setVisible(true);
    }

    /**
     * set data to GenerateFractal instance
     *
     */
    public void setData() {
        if (isChanged) {
            gen.setState(this.depth,this.myRatio,this.myAngle,this.padColor,this.pearColor);
        }
        else {
            gen.notifyAllObservers();
        }
    }

}
