package fr.exia.instanevehicles.model.element.motionless;

import fr.exia.insanevehicles.share.Permeability;
import fr.exia.insanevehicles.share.Sprite;

/**
 * <h1>The Class Ditch.</h1>
 *
 * @author Jade
 * @version 0.2
 */
class DitchRightTurnLeft extends MotionlessElement {

    /** The Constant SPRITE. */
    private static final Sprite SPRITE = new Sprite('}', "DitchRightTurnLeft.jpg");

    /**
     * Instantiates a new ditch.
     */
    DitchRightTurnLeft() {
        super(SPRITE, Permeability.BLOCKING);
    }
}
