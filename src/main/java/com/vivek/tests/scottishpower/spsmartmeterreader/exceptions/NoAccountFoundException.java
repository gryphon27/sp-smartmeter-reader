package com.vivek.tests.scottishpower.spsmartmeterreader.exceptions;

public class NoAccountFoundException extends Exception {
	private static final long serialVersionUID = -1906699925214902604L;
	public NoAccountFoundException(String message){
		super(message);
	}
}
