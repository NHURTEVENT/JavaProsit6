package fr.exia.instanevehicles.model.element.motionless;

import fr.exia.insanevehicles.share.Permeability;
import fr.exia.insanevehicles.share.Sprite;
import fr.exia.instanevehicles.model.element.Element;

/**
 * <h1>The MotionlessElement Class.</h1>
 *
 * @author Jade
 * @version 0.2
 */
abstract class MotionlessElement extends Element {

    /**
     * Instantiates a new motionless element.
     *
     * @param sprite
     *            the sprite
     * @param permeability
     *            the permeability
     */
    MotionlessElement(final Sprite sprite, final Permeability permeability) {
        super(sprite, permeability);
    }

}
