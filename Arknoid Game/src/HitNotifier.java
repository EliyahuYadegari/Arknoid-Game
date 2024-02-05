/**
 * interface for notifier.
 */
public interface HitNotifier {

    /**
     * Adds a listener to hit events.
     *
     * @param hl given listener
     */
    void addHitListener(HitListener hl);

    /**
     * removes a listener to hit events.
     *
     * @param hl given listener
     */
    void removeHitListener(HitListener hl);
}