/**
 * <p>
 * An Action is an object encapsulating the verb being issued by
 *  a @see Request object. An Action contains an @see ActionType,
 *  which are simple verbs. Additionally, an Action may have an
 *  Attribute string, which is useful in cases where additional
 *  information is required to perform an Action.
 * </p>
 *
 * @author Brandon Lucas
 * @version 1.0
 * @date 6. December, 2013
 * @obvious Comments for methods are omitted as they are self explanatory
 *          (getters/setters).
 */
package com.easyTeach.common.network;

import java.io.Serializable;

public final class Action implements Serializable {

	private static final long serialVersionUID = -6452781884558430146L;
	private ActionType type;
	private String attribute;
	
	/**
     * <p>
     * Constructor for a simple Action, containing no attribute.
     * </p>
     *
     * @param ActionType the enummerated type of Action to be performed.
     *
     * @see Request
     */
	public Action(ActionType type) {
		this.type = type;
		attribute = "";
	}
	
	/**
     * <p>
     * Constructor for an Action requiring an attribute.
     * </p>
     *
     * @param ActionType the enummerated type of Action to be performed.
     * @param attribute A string detailing any additional information required
     * to parse the Action.
     * @see Request
     */
	public Action(ActionType type, String attribute) {
		this.type = type;
		this.attribute = attribute;
	}
	
	public ActionType getType() {
		return type;
	}
	
	public String getAttribute() {
		return attribute;
	}
	
	/**
	 *	<p>
	 *	Enumeration used for describing what kind @see of Action to perform
	 *	on a @see Resource.  
	 * @author Brandon Lucas
	 * @version 1.0
	 * @date 6. December, 2013
	 */
	public enum ActionType {
		CREATE, READ, UPDATE, DELETE, CLOSE;
	}

}
