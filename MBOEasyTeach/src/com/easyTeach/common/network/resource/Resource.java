package com.easyTeach.common.network.resource;

import java.io.Serializable;

/**
 * An interface which must be implemented by any class that is to be packed into
 * a Request or a Response. <br>
 * <p>
 * This interface allows any class to be handled polymorphically as a Resource,
 * making it transmittable within the EasyTeach networking layer. At the same
 * time, it enforces that all Resources implement the Serializable interface.
 * </p>
 * 
 * @author Brandon Lucas
 * @version 1.0
 * @date 4. December, 2013
 * @see Request
 * @see Response
 */
public interface Resource extends Serializable {
	
	public String getName();
}