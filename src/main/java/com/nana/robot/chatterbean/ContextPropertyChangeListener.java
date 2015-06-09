
package com.nana.robot.chatterbean;

import java.beans.PropertyChangeListener;

public abstract class ContextPropertyChangeListener implements
		PropertyChangeListener {
	/*
	 * Attribute Section
	 */

	/** Name of the property whose changes to listen for. */
	private String name;

	/*
	 * Constructor Section
	 */

	/**
	 * Creates a new change listener for the named Context Property.
	 * 
	 * @param name
	 *            The name of the property whose changes this object listens
	 *            for.
	 */
	public ContextPropertyChangeListener(String name) {
		this.name = name;
	}

	/*
	 * Accessor Section
	 */

	/**
	 * Returns the name of the property whose changes this object listens for.
	 * 
	 * @return The name of the property whose changes this object listens for.
	 */
	public String name() {
		return name;
	}

}
