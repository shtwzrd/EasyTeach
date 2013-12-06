package com.easyTeach.client.presenter;

import org.jasypt.util.password.StrongPasswordEncryptor;

import com.easyTeach.client.network.EasyTeachClient;
import com.easyTeach.client.network.Session;
import com.easyTeach.common.network.Action;
import com.easyTeach.common.network.Action.ActionType;
import com.easyTeach.common.network.Request;
import com.easyTeach.common.network.Response;

/**
 * <p>
 * The LoginPresenter class is in charge of handling the different events
 * occurring in the Login User Interface (LoginUI). Some events will be
 * handled without any messages being sent to the domain logic layer,
 * whilst others will need to be sent to the "MBO EasyTeach" application's
 * server, where the domain logic resides.
 * </p>
 * 
 * @author Morten Faarkrog
 * @version 1.0
 * @date 29. November, 2013
 */

public class LoginPresenter {
    
    /**
     * Checks if username is of a valid format. This means that
     * it cannot contains spaces nor can it be empty.
     * 
     * @param usr is the username of the user trying to log in
     * @return true if the username is of a valid format, otherwise false
     */
    public boolean validateUsername(String usr) {
        if (usr.isEmpty() || usr.contains(" ")) {
            return false;
        }
        return true;
    }
    
    /**
     * Checks if the password entered by a user is of a valid format. 
     * This means that it cannot be empty.
     * 
     * @param pwd is the password of the user trying to log in
     * @return true if the password is of a valid format, otherwise false
     */
    public boolean validatePassword(char[] pwd) {
        if (pwd.length == 0) {
            return false;
        }
        return true;
    }
    
    /**
     * What is my purpose?
     * 
     * @param usr 
     * @param pwd
     * @return
     */
    public boolean canLogin(String usr, char[] pwd) {
    	if(validatePassword(pwd)) {
    		if (attemptLogin(usr, pwd)) {
    			return true;
    		}
    	}
        
        return false;
    }
    
    /**
     * I will be very descriptive once I know how I work...
     * 
     * @param usr 
     * @param pwd
     * @return true if the login details are valid and if the server 
     * and database are running.
     */
    private boolean attemptLogin(String usr, char[] pwd) {
    	Session session = Session.getInstance(usr, pwd.toString());
    	Action action = new Action(ActionType.READ);
    	Request login = new Request(session.getUsername(),
    			session.getPassword(), action);
    	
    	EasyTeachClient client = new EasyTeachClient(login);
    	client.run();
    	
    	Response back = client.getResponse();
    	System.out.println("[Response]: " + back.getStatus() + ": " + back.getResponse());
    	
        return true;
    }
    
}
