package de.titanium.enterprise.Sprite.Animation;

import java.util.Queue;
import java.util.concurrent.LinkedTransferQueue;

/**
 * Created by Yonas on 17.03.2016.
 */
public class AnimationQueue {

    private final Queue<Animator> animators = new LinkedTransferQueue<>();

    private Animator current = null;
    private final Animation defaultAnimation;

    public AnimationQueue(Animation defaultAnimation) {
        this.defaultAnimation = defaultAnimation;
    }

    /**
     * Diese Methode f�gt der Animation-Queue die �bergebene Animation zur�ck.
     * @param animation
     */
    public void add(Animation animation) {

        Animator animator = animation.createAnimator();
        if(this.current == null) {
            this.current = animator;
        }

        this.animators.offer(animator);

    }

    /**
     * Diese Methode gitb die aktulle Animaton zur�ck, die aktuell gezeichnet werden soll.
     *
     * Sollte die aktuelle Animation am Ende sein, wird die n�chste Animation aus der Queue genommen, sollte die Queue
     * leer sein, wird auf die defaultAnimation zur�ckgegriffen.
     * Diese wurde dem Konstrukt �bergeben.
     * @return
     */
    public Animator element() {

        // @Watch: Es gab eine NoSuchElementException bei this.animations.element(), obwohl vorher gepr�ft
        // wurde, ob die Queue leer ist. Sollte druch das "synchronized" gel�st werden, ist aber nicht 100%ig sicher.
        synchronized (this.animators) {

            if (this.current == null) {
                this.current = this.defaultAnimation.createAnimator();
            } else if (this.current.getIndex() + 1 == this.current.getAmount()) {
                if (this.animators.isEmpty()) {
                    this.current = this.defaultAnimation.createAnimator();
                } else {
                    this.current = this.animators.element();
                }
                this.animators.poll();
            }

            return this.current;
        }

    }

    @Override
    public String toString() {
        return String.format("{animators: %s, current: %s, defaultAnimation: %s}", this.animators, this.current, this.defaultAnimation);
    }

}
