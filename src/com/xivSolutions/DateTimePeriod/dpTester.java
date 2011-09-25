package com.xivSolutions.DateTimePeriod;

import java.util.Calendar;

public class dpTester 
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
		
		IDatePeriod mainPeriod = null;
		IDatePeriod otherPeriod = null;
		
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		Calendar calendar3 = Calendar.getInstance();
		Calendar calendar4 = Calendar.getInstance();

		
		calendar1.set(Calendar.YEAR, 2011);
		calendar1.set(Calendar.MONTH, Calendar.JANUARY);
		calendar1.set(Calendar.DAY_OF_MONTH, 1);

		calendar2.set(Calendar.YEAR, 2011);
		calendar2.set(Calendar.MONTH, Calendar.DECEMBER);
		calendar2.set(Calendar.DAY_OF_MONTH, 31);

		calendar3.set(Calendar.YEAR, 2010);
		calendar3.set(Calendar.MONTH, Calendar.JANUARY);
		calendar3.set(Calendar.DAY_OF_MONTH, 1);
		
		calendar4.set(Calendar.YEAR, 2010);
		calendar4.set(Calendar.MONTH, Calendar.DECEMBER);
		calendar4.set(Calendar.DAY_OF_MONTH, 31);
		
		
		mainPeriod = DatePeriodFactory.newDatePeriod(calendar1, DatePeriodTypes.YEAR);

//		try 
//		{
//			mainPeriod = new DatePeriod(calendar1,2, 30);
//
//		}
//		catch (DatePeriodPrecedenceException e) 
//		{
//			System.out.println("***** The error is: " + e.getStackTrace()[0].toString());
//			e.printStackTrace();
//		}
	
		
		otherPeriod = new CalendarYear(calendar3);

//		try 
//		{
//			otherPeriod = new DatePeriod(calendar3, 4, 15);
//
//		}
//		catch (DatePeriodPrecedenceException e) 
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		System.out.println("Main Period:");
		testInitialization(mainPeriod);
		System.out.println("");
		
		System.out.println("Other Period:");
		testInitialization(otherPeriod);
			
		System.out.println("Intersection Tests");
		dpTester.testDoesIntersect(mainPeriod, otherPeriod);
		dpTester.testIntersectionType(mainPeriod, otherPeriod);
		
		if(mainPeriod.hasIntersection(otherPeriod))
		{
			dpTester.testGetIntersection(mainPeriod, otherPeriod);
			System.out.println("");
			
			System.out.println("SubPeriod Tests - Intersection");
			IDatePeriod intersection = mainPeriod.getIntersection(otherPeriod);
			dpTester.testGetSubPeriods(intersection, DatePeriodTypes.MONTH);
		}

		

		if(mainPeriod.hasUnion(otherPeriod))
		{
			System.out.println("Union Tests");
			dpTester.testGetUnion(mainPeriod, otherPeriod);
			System.out.println("");

			System.out.println("SubPeriod Tests - mainPeriod");
			dpTester.testGetSubPeriods(mainPeriod, DatePeriodTypes.MONTH);
			
			System.out.println("SubPeriod Tests - union");
			IDatePeriod union = mainPeriod.getUnion(otherPeriod);
			dpTester.testGetSubPeriods(union, DatePeriodTypes.MONTH);			
		}

		
	}
	
	
	public static void testInitialization(IDatePeriod mainPeriod)
	{

		System.out.println(dpTester.START_DATE_MESSAGE + mainPeriod.getStartDateString(DateFormatStrings.MM_DD_yyyy_HH24_mm_ss_milli) );
		System.out.println(dpTester.END_DATE_MESSAGE + mainPeriod.getEndDateString(DateFormatStrings.MM_DD_yyyy_HH24_mm_ss_milli) );
		System.out.println("");
		System.out.println(dpTester.DAYS_IN_PERIOD_MESSAGE_INCLUSIVE + mainPeriod.getDaysInPeriodInclusive());
		System.out.println(dpTester.DAYS_IN_PERIOD_MESSAGE_EXCLLUSIVE + mainPeriod.getDaysInPeriodExclusive());
		System.out.println(dpTester.WEEKS_IN_PERIOD_MESSAGE + mainPeriod.getCountOfWeeks());
		System.out.println(dpTester.MONTHS_IN_PERIOD_MESSAGE + mainPeriod.getCountOfMonths());
		System.out.println(dpTester.YEARS_IN_PERIOD_MESSAGE + mainPeriod.getCountOfYears());
		System.out.println("");
		
	}
	
	

	
	
	
	public static void testDoesIntersect(IDatePeriod mainPeriod, IDatePeriod otherPeriod)
	{
		System.out.println(dpTester.DOES_INTERSECT_MESSAGE + mainPeriod.hasIntersection(otherPeriod));
	}
	
	
	public static void testIntersectionType(IDatePeriod mainPeriod, IDatePeriod otherPeriod)
	{
		System.out.println(dpTester.INTERSECTION_TYPE_MESSAGE + mainPeriod.getIntersectionType(otherPeriod));
	}
	
	
	public static void testGetIntersection(IDatePeriod mainPeriod, IDatePeriod otherPeriod)
	{
		System.out.println(dpTester.INTERSECTION_MESSAGE + mainPeriod.getIntersection(otherPeriod).toString());

	}
	
	
	public static void testGetUnion(IDatePeriod mainPeriod, IDatePeriod otherPeriod)
	{
		System.out.println(dpTester.UNION_MESSAGE + mainPeriod.getUnion(otherPeriod).toString());

	}
	
	
	public static void testGetSubPeriods(IDatePeriod mainPeriod, DatePeriodTypes subPeriodType)
	{
		for(IDatePeriod subPeriod : mainPeriod.getSubPeriods(subPeriodType))
		{
			System.out.println("\t" + dpTester.SUBPERIODS_MESSAGE + subPeriod.toString());
		}
	}

	
}
