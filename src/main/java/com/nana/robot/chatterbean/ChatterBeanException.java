package com.nana.robot.chatterbean;

/**
 * Basic exception class for exceptions thrown from ChatterBean's main class.
 */
public class ChatterBeanException extends RuntimeException {
	/**
	 * Version class identifier for the serialization engine. Matches the number
	 * of the last revision where the class was created / modified.
	 */
	// private static final long serialVersionUID = 8L;//这到底是什么东西，干什么用的？？？？

	public ChatterBeanException(String message) {
		super(message);
	}

	public ChatterBeanException(Exception cause) {
		super(cause);
	}
}
