package com.xivSolutions.DateTimePeriod;


public enum DatePeriodTypes {
	GENERIC (0),
	DAY (1),
	WEEK (2),
	MONTH (3),
	QUARTER (4),
	YEAR (5),
	FISCAL_YEAR (6),
	HOUR (7),
	MINUTE (8),
	SECOND (9);

	
	private int periodTypeId;
	
	DatePeriodTypes(int Id){
		periodTypeId = Id;
	}
	
	public int getNumericIdentifier(){return periodTypeId;}
	
	
}
