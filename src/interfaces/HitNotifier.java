package interfaces;
/**@author Eldar Shlomi.
 * id 205616634.*/

/**
 * interface for registered notifier classes, which notify to the listener when X was occur.
 */

/**
 * method whose implement it is a notifier - he notify yhe registered listener when X was happened.
 */
public interface HitNotifier {
    /**
     * method to "register" a variable as a listener - occur by adding it to the hitlistener list.
     * @param hl - the variable to be add to the hitlistener list.
     */
    void addHitListener(HitListener hl);
    /**
     * method to delete a listener variable.
     * @param hl - the variable to be deleted to the hitlistener list.
     */
    void removeHitListener(HitListener hl);
}