package com.easyTeach.client.presenter;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * <p>
 * The HelpPresenter class keeps all the different help messages for the
 * different User Interfaces in one place. This way different parts of help 
 * messages can be reused and all help-messages are easily maintainable. 
 * </p>
 * 
 * @author Morten Faarkrog
 * @version 1.0
 * @date 28. November, 2013
 * @obvious Most methods are not commented as they are self explanatory 
 * (For example: getLoginTitle and getLoginHelp).  
 */

public class HelpPresenter {
    
    // General Help
    public Icon getHelpIcon() {
        return new ImageIcon("images/helpIcon.png");
    }
    
    // Login UI Help
    public String getLoginTitle() {
        return "EasyTeach - Login Help";
    }
    
    public String getLoginHelp() {
        return "I will be a very descriptive help message when \n"
                + "I grow up! I simply cannot wait!";
    }
    
}
