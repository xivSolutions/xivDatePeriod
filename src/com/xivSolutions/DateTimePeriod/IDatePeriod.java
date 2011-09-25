package com.xivSolutions.DateTimePeriod;

import java.util.Calendar;

public interface IDatePeriod 
{

	/**
	 *  Getter for Start Date Property:
	 */
	Calendar getStartDate();
		
	
	/**
	 * Getter for EndDate Property:
	 * @return
	 */
	Calendar getEndDate();
	
			
	/**
	 * Return the number of days spanned by the current instance,
	 * including the start and end dates:
	 * @return
	 */
	int getDaysInPeriodInclusive();
	
	
	/**
	 * Return the number of days spanned by the current instance, 
	 * excluding the start and end dates:
	 * @return
	 */
	int getDaysInPeriodExclusive();
	
	
	/**
	 * Return the number of weeks spanned by the current instance. The result
	 * will return a decimal value for partial weeks.
	 * @return
	 */
	double getCountOfWeeks();
	
	
	/**
	 * Return the number of Months spanned by the current instance. The result
	 * will return a decimal value for partial months.
	 * @return
	 */
	double getCountOfMonths();
	
	
	/**
	 * Return the number of Years spanned by the current instance. The result will
	 * include a decimal value for partial years.
	 * @return
	 */
	double getCountOfYears();
	
	
	/**
	 * Returns true if any portion of the date period instance passed in 
	 * occurs within the period represented by the current instance.
	 * @return
	 */
	boolean hasIntersection(IDatePeriod otherPeriod);
	
	
	/**
	 * Returns an enum value describing the type of intersection
	 * between the current instance and the period passed in. 
	 * @return
	 */
	DatePeriodIntersectionTypes getIntersectionType(IDatePeriod otherPeriod);
	
	
	/**
	 * Returns an DatePeriod instance representing the intersection
	 * between the period passed in and the period spanned by the current
	 * instance. 
	 */
	IDatePeriod getIntersection(IDatePeriod otherPeriod);
	
	
	/**
	 * Returns true if the datePeriod passed in can be combined with the current
	 * instance to form a contiguous range of dates. The two periods must overlap, 
	 * or abut each other such that the difference between the start date of the 
	 * first chronological period and the end date of the second is less than 1 second. 
	 * @return
	 */
	boolean hasUnion(IDatePeriod otherPeriod);
	
		
	/**
	 * Returns a DatePeriod instance representing the union of the
	 * period passed in with the current instance. If two non-contiguous
	 * date ranges are joined, the result period will span from the 
	 * earliest start date thru the most recent end date. 
	 * @param otherPeriod
	 * @return
	 */
	IDatePeriod getUnion(IDatePeriod otherPeriod);
	
		
	/**
	 * Returns a DatePeriodColleciotn containing the sub-periods contained by 
	 * the current instance, of the specified subPeriodType
	 * @param subPeriodType
	 * @return
	 */
	DatePeriodCollection getSubPeriods(DatePeriodTypes subPeriodType);

		
	/**
	 * Returns a string representation of the start date
	 * in the format specified.
	 * @param formatOption
	 * @return
	 */
	String getStartDateString(DateFormatStrings formatOption);

	
	/**
	 * Returns a string representation of the start date
	 * in the format specified.
	 * @param formatOption
	 * @return
	 */
	String getEndDateString(DateFormatStrings formatOption);
	
	
	/**
	 * Override the toString method to return a description of 
	 * the date span represented by the current instance:
	 * @return
	 */
	String toString();
	
} // END INTERFACE IDatePeriod
