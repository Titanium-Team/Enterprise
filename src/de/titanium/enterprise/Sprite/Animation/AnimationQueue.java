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

    public void add(Animation animation) {

        Animator animator = animation.getAnimator();
        if(this.current == null) {
            this.current = animator;
        }

        this.animators.offer(animator);

    }

    public Animator element() {

        if(this.current == null) {
            this.current = this.defaultAnimation.getAnimator();
        } else if(this.current.getIndex()+1 == this.current.getAmount()) {
            if(this.animators.isEmpty()) {
                this.current = this.defaultAnimation.getAnimator();
            } else {
                this.current = this.animators.element();
            }
            this.animators.poll();
        }

        return this.current;
    }


}
