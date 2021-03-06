package de.titanium.enterprise.Sprite.Animation;

import java.util.Queue;
import java.util.concurrent.LinkedTransferQueue;

public class AnimationQueue {

    private final Queue<Animator> animators = new LinkedTransferQueue<>();

    private Animator defaultAnimator = null;
    private Animator current = null;

    public AnimationQueue(Animation defaultAnimation) {
        this.current = defaultAnimation.createAnimator();
        this.defaultAnimator = this.current;
    }

    /**
     * Diese Methode fuegt der Animation-Queue die uebergebene Animation zurueck.
     * @param animation
     */
    public void add(Animation animation) {

        this.animators.offer(animation.createAnimator());

    }

    /**
     * Diese Methode gitb die aktulle Animaton zurueck, die aktuell gezeichnet werden soll.
     *
     * Sollte die aktuelle Animation am Ende sein, wird die naechste Animation aus der Queue genommen, sollte die Queue
     * leer sein, wird auf die defaultAnimation zurueckgegriffen.
     * Diese wurde dem Konstrukt uebergeben.
     * @return
     */
    public Animator element() {

        if (this.current.getIndex() + 1 == this.current.getAmount()) {
            if (this.animators.isEmpty()) {
                this.current = this.defaultAnimator;
                this.current.setIndex(0);
            } else {
                this.current = this.animators.element();
            }
            this.animators.poll();
        }

        return this.current;

    }

    /**
     * Gibt die defaultAnimation der Queue zurueck.
     * @return
     */
    public Animation getDefaultAnimation() {
        return this.defaultAnimator.getType();
    }

    @Override
    public String toString() {
        return String.format("{animators: %s, current: %s, defaultAnimation: %s}", this.animators, this.current, this.defaultAnimator.getType());
    }

}
