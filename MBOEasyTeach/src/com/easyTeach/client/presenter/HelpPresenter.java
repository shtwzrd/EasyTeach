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
    public static String getManageExerciseTitle() {
        return "EasyTeach - Manage Exercise Help";
    }
    
    public static String getManageExerciseHelp() {
        return "Add info here!";
    }
    
    // ManageUserUI Help
    public static String getManageUserTitle() {
        return "EasyTeach - Manage User Help";
    }
    
    public static String getManageUserHelp() {
        return "Add info here!";
    }
    
    // ExerciseManagerUI Help
    public static String getExerciseManagerTitle() {
        return "EasyTeach - Exercise Manager Help";
    }
    
    public static String getExerciseManagerHelp() {
        return "Add info here!";
    }
    
    // QuestionManagerUI Help
    public static String getQuestionManagerTitle() {
        return "EasyTeach - Question Manager Help";
    }
    
    public static String getQuestionManagerHelp() {
        return "Add info here!";
    }
    
    // ManageUserUI Help
    public static String getManageClassTitle() {
        return "EasyTeach - Manage Class Help";
    }
    
    public static String getManageClassHelp() {
        return "Add info here!";
    }
    
    // AdminManagerUI Help
    public static String getAdminManagerTitle() {
        return "EasyTeach - Admin Manager Help";
    }
    
    public static String getAdminManagerHelp() {
        return "Add info here!";
    }
    
    // ManageCourseUI Help
    public static String getManageCourseTitle() {
        return "EasyTeach - Manage Course Help";
    }
    
    public static String getManageCourseHelp() {
        return "Add info here!";
    }
    
}
