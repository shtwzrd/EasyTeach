package com.easyTeach.common.network.resource;


/**
 * A resource containing a Role a user of the system may be.
 * 
 * <p>
 * This is only used for when we want a Response to have a
 * Role as its payload, most likely for testing purposes.
 * </p>
 * 
 * @author Brandon Lucas
 * @version 1.0
 * @date 5. December, 2013
 */
public class RoleResource implements Resource {
	
	private static final long serialVersionUID = -844956815181244552L;
	private Role role;
	
	public RoleResource(Role role) {
		this.role = role;
	}
	
	public Role getRole() {
		return this.role;
	}
	
	/**
	 * An enumeration describing the Role of a user in the system.
	 * 
	 * @see Authenticator
	 * @author Brandon Lucas
	 * @version 1.0
	 * @date 4. December, 2013
	 */
	public enum Role {
		STUDENT, TEACHER, ADMIN;
	}

}
