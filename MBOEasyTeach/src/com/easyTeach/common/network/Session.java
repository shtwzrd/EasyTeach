package com.easyTeach.common.network;

import java.io.Serializable;

/** Singleton containing the username and password of the user of
 *  interest.
 *  <li>
 *  <p>
 *  The Session object is attached to a Request and consumed by
 *  the Authenticator on the Server-side, both for Authenticating
 *  and Authorizing purposes. Requests sent without a Session would
 *  be ignored by the Server.
 *  </p>
 * @see Request
 * @see Authenticator
 * @see EasyTeachServer
 * 
 * @author Brandon Lucas
 * @version 1.0
 * @date 5. December, 2013
 */
public final class Session implements Serializable {
    
	private static final long serialVersionUID = 755503070261426219L;

	private static Session instance = null;
    private String username;
    private String password;
    
    /** 
     * Private constructor made so that an instance of Session can
     * only be created from within the class itself. 
     */
    private Session(String user, String pw) {
    	this.username = user;
    	this.password = pw;
    }

    /** 
     * Used to get the instance of the Session when needed 
     * in other classes. If the instance is not already instantiated 
     * (i.e. if it is null), it will be. 
     * @return the Session instance 
     */
    public static synchronized Session getInstance(String user, String pw) {
        if (instance == null) { 
            instance = new Session(user, pw);
        }
        return instance;
    }

     public static synchronized Session getInstance() {
        return instance;
    }   

    /**
     * Closes the Session and set's the global
     * Session attributes to null.
     */
    public void close() {
        System.out.println("Closing session");
           this.username = null; 
           this.password = null;
    }
    
    public String getUsername() {
    	return this.username;
    }
    
    public String getPassword() {
    	return this.password;
    }
    
}
