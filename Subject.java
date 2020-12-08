/**
 *  Subject interface.
 *
 * @author Jesse Leu
 * @version 2018.11.23
 */
public interface  Subject {

    /**
     * add Observer
     * @param observer new observer
     */
    public void addObserver(Observer observer);

    /**
     * remove observer
     * @param observer  observer
     */
    public void removeObserver(Observer observer);

    /**
     * notify AllObservers
     *
     */
    public void notifyAllObservers();

    /**
     * return the FE arraylist
     * @return    FE arraylist
     */
    public ArrayList<FE> getState();
}