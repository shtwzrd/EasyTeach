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
    public static Icon getHelpIcon() {
        return new ImageIcon("images/helpIcon.png");
    }
    
    // LoginUI Help
    public static String getLoginTitle() {
        return "EasyTeach - Login Help";
    }
    
    public static String getLoginHelp() {
        return "Add info here!";
    }
    
    // AddExerciseUI Help
    public static String getAddExerciseTitle() {
        return "EasyTeach - Add Exercise Help";
    }
    
    public static String getAddExerciseHelp() {
        return "Add info here!";
    }
    
    // ManageUserUI Help
    public static String getManageUserTitle() {
        return "EasyTeach - Manage User Help";
    }
    
    public static String getManageUserHelp() {
        return "Add info here!";
    }
    
}
