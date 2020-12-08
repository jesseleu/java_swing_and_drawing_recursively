/**
 *  main class.
 *
 * @author Jesse Leu
 * @version 2018.11.23
 */
public class main {
    /**
     * Constructor for objects of class main
     * @param args String[] args
     */
    public static void main(String[] args) {
        GenerateFractal gen = new  GenerateFractal();
        GUI myGui = new GUI(gen);
        Display display = new Display(gen);        
    }
}
