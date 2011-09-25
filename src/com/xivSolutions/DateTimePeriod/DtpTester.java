package com.xivSolutions.DateTimePeriod;

import java.util.Calendar;

public class DtpTester 
{

	
	static final String START_DATE_MESSAGE = "the startDate is: ";
	static final String END_DATE_MESSAGE = "The end date is: ";
	static final String DAYS_IN_PERIOD_MESSAGE_INCLUSIVE = "Total days in period (inclusive): ";
	static final String DAYS_IN_PERIOD_MESSAGE_EXCLLUSIVE = "Total days in the period (exclusive): ";
	static final String WEEKS_IN_PERIOD_MESSAGE = "Total weeks in the period: ";
	static final String MONTHS_IN_PERIOD_MESSAGE = "Approximate months in the period: ";

	static final String YEARS_IN_PERIOD_MESSAGE = "Total years in the period: ";
	static final String SUBPERIODS_MESSAGE = "Top-level sub-periods are: ";
	static final String DOES_INTERSECT_MESSAGE = "The main period and the other period intersect: ";

	static final String INTERSECTION_TYPE_MESSAGE = "The intersection type is: ";
	static final String INTERSECTION_MESSAGE = "The intersection of the two periods is: ";
	static final String UNION_MESSAGE = "The union of the two periods is: ";
	static final String FACTORY_MESSAGE = "The output from the DatePeriodFactory is: ";
	static final String FACTORY_OUTPUT_TYPE_MESSAGE = "The factory output type is : ";
	
	static final String START_TIME_MESSAGE = "The start time is : ";
	static final String END_TIME_MESSAGE = "The end time is : ";
	

	
	
	public static void main(String[] args) throws DatePeriodPrecedenceException
	{
		
		IDateTimePeriod mainPeriod = null;
		IDateTimePeriod otherPeriod = null;
		
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		Calendar calendar3 = Calendar.getInstance();
		Calendar calendar4 = Calendar.getInstance();

		
		calendar1.set(Calendar.YEAR, 2010);
		calendar1.set(Calendar.MONTH, Calendar.DECEMBER);
		calendar1.set(Calendar.DAY_OF_MONTH, 30);

		calendar2.set(Calendar.YEAR, 2010);
		calendar2.set(Calendar.MONTH, Calendar.DECEMBER);
		calendar2.set(Calendar.DAY_OF_MONTH, 31);

		calendar3.set(Calendar.YEAR, 2010);
		calendar3.set(Calendar.MONTH, Calendar.DECEMBER);
		calendar3.set(Calendar.DAY_OF_MONTH, 15);
		
		calendar4.set(Calendar.YEAR, 2011);
		calendar4.set(Calendar.MONTH, Calendar.JANUARY);
		calendar4.set(Calendar.DAY_OF_MONTH, 15);
		
		
		try 
		{
			mainPeriod = new DateTimePeriod(calendar1, calendar2);
			mainPeriod.setStartTime(2, 35);
			mainPeriod.setEndTime(2, 35);
		}
		catch (DatePeriodPrecedenceException e) 
		{
			System.out.println("***** The error is: " + e.getStackTrace()[0].toString());
			e.printStackTrace();
		}
		
		try 
		{
			otherPeriod = new DateTimePeriod(calendar3, calendar4);
			otherPeriod.setEndTime(3, 50);
		}
		catch (DatePeriodPrecedenceException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Main Period:");
		testInitialization(mainPeriod);
		System.out.println("");
		
		System.out.println("Other Period:");
		testInitialization(otherPeriod);
			
		System.out.println("Intersection Tests");
		DtpTester.testDoesIntersect(mainPeriod, otherPeriod);
		DtpTester.testIntersectionType(mainPeriod, otherPeriod);
		DtpTester.testGetIntersection(mainPeriod, otherPeriod);
		System.out.println("");

		System.out.println("Union Tests");
		DtpTester.testGetUnion(mainPeriod, otherPeriod);
		System.out.println("");

		System.out.println("SubPeriod Tests");
		DtpTester.testGetSubPeriods(mainPeriod, DatePeriodTypes.DAY);
		
	}
	
	
	public static void testInitialization(IDateTimePeriod mainPeriod)
	{

		System.out.println(DtpTester.START_DATE_MESSAGE + mainPeriod.getStartDateString(DateFormatStrings.MM_DD_yyyy_HH24_mm_ss_milli) );
		System.out.println(DtpTester.END_DATE_MESSAGE + mainPeriod.getEndDateString(DateFormatStrings.MM_DD_yyyy_HH24_mm_ss_milli) );
		System.out.println("");
		System.out.println(DtpTester.DAYS_IN_PERIOD_MESSAGE_INCLUSIVE + mainPeriod.getDaysInPeriodInclusive());
		System.out.println(DtpTester.DAYS_IN_PERIOD_MESSAGE_EXCLLUSIVE + mainPeriod.getDaysInPeriodExclusive());
		System.out.println(DtpTester.WEEKS_IN_PERIOD_MESSAGE + mainPeriod.getCountOfWeeks());
		System.out.println(DtpTester.MONTHS_IN_PERIOD_MESSAGE + mainPeriod.getCountOfMonths());
		System.out.println(DtpTester.YEARS_IN_PERIOD_MESSAGE + mainPeriod.getCountOfYears());
		System.out.println("");
		
	}
	
	

	
	
	
	public static void testDoesIntersect(IDateTimePeriod mainPeriod, IDateTimePeriod otherPeriod)
	{
		System.out.println(DtpTester.DOES_INTERSECT_MESSAGE + mainPeriod.hasIntersection(otherPeriod));
	}
	
	
	public static void testIntersectionType(IDateTimePeriod mainPeriod, IDateTimePeriod otherPeriod)
	{
		System.out.println(DtpTester.INTERSECTION_TYPE_MESSAGE + mainPeriod.getIntersectionType(otherPeriod));
	}
	
	
	public static void testGetIntersection(IDateTimePeriod mainPeriod, IDateTimePeriod otherPeriod)
	{
		System.out.println(DtpTester.INTERSECTION_MESSAGE + mainPeriod.getIntersection(otherPeriod).toString());

	}
	
	
	public static void testGetUnion(IDateTimePeriod mainPeriod, IDateTimePeriod otherPeriod)
	{
		System.out.println(DtpTester.UNION_MESSAGE + mainPeriod.getUnion(otherPeriod).toString());

	}
	
	
	public static void testGetSubPeriods(IDateTimePeriod mainPeriod, DatePeriodTypes subPeriodType)
	{
		for(IDatePeriod subPeriod : mainPeriod.getSubPeriods(subPeriodType))
		{
			System.out.println("\t" + DtpTester.SUBPERIODS_MESSAGE + subPeriod.toString());
		}
	}

}
