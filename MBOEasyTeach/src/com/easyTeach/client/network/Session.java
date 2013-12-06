package com.easyTeach.client.network;

public final class Session {
    
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
