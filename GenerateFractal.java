import java.awt.Color;
/**
 * Generate Fractal .
 *
 * @author Jesse Leu
 * @version 2018.11.23
 */
public class GenerateFractal implements Subject {
    /** default x  */
    public static final int DEFAULTX = 250;
    /** default y  */
    public static final int DEFAULTY = 350;
    /** default radius  */
    public static final int DEFAULTRADIUS = 60;
    /** arrayList of observers  */
    private ArrayList<Observer> observers;
    /** arrayList of fractal elements  */
    private ArrayList<FE> elements;
    /** color of last fractal elements  */
    private Color pearColor;
    /**
     * Constructor for objects of class GenerateFractal
     */
    public GenerateFractal() {
        elements = new ArrayList<FE>();
        observers = new ArrayList<Observer>();
    }

    /**
     * notify AllObservers
     *
     */
    public void notifyAllObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    /**
     * add Observer
     * @Param observer new observer
     */
    public void addObserver(Observer observer) {
        observers.add(observer);   
    }

    /**
     * remove observer
     * @Param observer  observer
     */
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    /**
     * set state
     *
     * @param  depth many shapes will be drawn 
     * @param  ratio  child radius to parent radius 
     * @param  angle  angle of the children 
     * @param  color  pad color
     * @param  lastColor  pear color
     */
    public void setState(int depth,int ratio,int angle,Color color,Color lastColor) {
        elements.clear();
        this.pearColor = lastColor;
        createCactus( depth,ratio,angle,color);
        notifyAllObservers();
    }

    /**
     * return the FE arraylist
     * @return    FE arraylist
     */
    public ArrayList<FE> getState() {
        return elements;
    }

    /**
     * create the Fractal elements and store into arrayList
     * @param  depth how many shapes will be drawn 
     * @param  ratio child radius to parent radius 
     * @param  angle  angle of the children
     * @param  color   color of pad
     */
    public void createCactus(int depth,int ratio,int angle,Color color) {
        int radius = DEFAULTRADIUS;
        int x = DEFAULTX, y = DEFAULTY;
        if (angle > 65){
            y -= 50;  
        }
        int orinAngle = angle;
        int angleRight = angle - 90;
        int angleLeft = -angle - 90;
        createCactus(x, y,angleRight,radius,ratio,depth,color,orinAngle);
        createCactus(x, y, angleLeft,radius,ratio,depth,color,orinAngle);
    }

    /**
     * create the Fractal elements and store into arrayList
     * @param  x X value of center 
     * @param  y Y value of center
     * @param  angle  angle of the children
     * @param  radius  radius of  circle 
     * @param  ratio child radius to parent radius 
     * @param  depth  how many shapes will be drawn 
     * @param  color   color of circle
     * @param  orinAngle  angle of the first children 
     */
    private  void createCactus(int x, int y,int angle,int radius,int ratio,int depth,Color color,int orinAngle) {
        if (depth > 0 && radius > 0 ) {
            if (depth == 1 || radius == 1 ) {
                color = pearColor;          
            }
            double ratioPercent = (double) (ratio) / 100;
            double angleForRot = angle * (Math.PI / 180);
            double x1 =  Math.cos(angleForRot) * (radius * (1 + ratioPercent))  + x ; 
            double y1 = Math.sin(angleForRot) * (radius * (1 + ratioPercent))  + y ;
            int newX = (int) Math.round(x1);
            int newY = (int) Math.round(y1);
            elements.add(new FE(x, y, radius, color));
            createCactus(newX,newY,angle + orinAngle ,(int)Math.round(radius * ratioPercent),ratio,depth - 1,color, orinAngle );
            createCactus(newX,newY,angle - orinAngle ,(int)Math.round(radius * ratioPercent),ratio,depth - 1,color, orinAngle );
        }
    }
}
