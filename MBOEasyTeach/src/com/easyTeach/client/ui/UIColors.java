package com.easyTeach.client.ui;

import java.awt.Color;

/**
 * <p>
 * Class used to keep track of the colours used throughout the application.
 * For example, if one wanted to change all light blues to whites, one would
 * only have to change this in one place. 
 * Easy to maintain and highly reusable.
 * </p>
 * 
 * <p>
 * Note: Final is used for the class because it will never be modified 
 * during runtime. This makes it faster.
 * </p>
 *
 * @author Morten Faarkrog
 * @version 1.0
 * @date 5. December, 2013
 */

public final class UIColors {

    public static final Color lightBlue = new Color(186, 202, 218);
    public static final Color darkBlue = new Color(54, 96, 139);
    public static final Color white = new Color(255, 255, 255);
    public static final Color lightBrown = new Color(230, 225, 217);
    public static final Color darkBrown = new Color(175, 170, 157);
    
}

