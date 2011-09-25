package com.xivSolutions.DateTimePeriod;


public enum DatePeriodIntersectionTypes{
	NONE(0),
	
	/*
	 * A second period completely contains the comparison period:
	 */
	OTHER_PERIOD_CONTAINS (1),
	
	/*
	 * A second period is completely contained by the comparison period:
	 */
	OTHER_PERIOD_CONTAINED (2),
	
	/*
	 * A second period begins within the comparison period, and
	 * ends after the end of the comparison period:
	 */
	OTHER_PERIOD_PRO_IN (3), 
	
	/*
	 * A second period begins before the comparison period, and 
	 * ends within the comparison period:
	 */
	OTHER_PERIOD_PRO_OUT (4),
	
	/*
	 * A second period begins before the comparison period, and 
	 * abuts the beginning of the comparison period such that the
	 * difference between the end date of the second period is less
	 * than 1 second before the beginning of the comparison period:
	 */
	OTHER_PERIOD_PRECEDES(5),
	
	/*
	 * A second period begins immediately after the comparison period such 
	 * that the difference between the end of the comparison period is less than 
	 * one second prior to the beginning of the second period:
	 */
	OTHER_PERIOD_FOLLOWS (6);
	
	
	private final int numericConstant;
	
	//Constructor:
	DatePeriodIntersectionTypes(int intersectionId){
		this.numericConstant = intersectionId;
	}
	
	//Property to return the numeric value:
	public int getNumericValue(){return numericConstant;}
	
}
