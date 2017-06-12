package fr.exia.instanevehicles.model.element.motionless;

import fr.exia.insanevehicles.share.Permeability;
import fr.exia.insanevehicles.share.Sprite;

/**
 * <h1>The Tree Class.</h1>
 *
 * @author Jade
 * @version 0.2
 */
class Tree extends MotionlessElement {

    /** The Constant SPRITE. */
    private static final Sprite SPRITE = new Sprite('^', "Tree.jpg");

    /**
     * Instantiates a new tree.
     */
    Tree() {
        super(SPRITE, Permeability.BLOCKING);
    }
}
