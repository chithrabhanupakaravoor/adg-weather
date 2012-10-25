package com.adg.weather.test;

import com.adg.weather.DateFinder;

import junit.framework.TestCase;

public class DateFinderTest extends TestCase {
	
	DateFinder dateFinder;
	String dateValid; // format: mm-dd-yyyy
	String dateInvalid; 
	String[] daysOfWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
	
	
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		dateFinder = new DateFinder();
		dateValid = "2010-09-10";
		dateInvalid = null;
		
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	

	public void testDayOfWeek() {
		assertEquals(daysOfWeek[3], dateFinder.dayOfWeek(dateValid));
		
	}
}
