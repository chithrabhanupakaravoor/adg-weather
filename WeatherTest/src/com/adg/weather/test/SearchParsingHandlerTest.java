package com.adg.weather.test;

import com.adg.parser.JSONparser;
import com.adg.parser.SearchParsingHandler;

import junit.framework.TestCase;

public class SearchParsingHandlerTest extends TestCase {
	
	SearchParsingHandler test;
	JSONparser jParser;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		test = new SearchParsingHandler("Chicago");
		jParser = new JSONparser();
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testUrl() throws Exception {
		test.startParsing();
		//assertEquals(true, test.getUrl()!=null);
		assertEquals(true, test.getSo()!=null);
	}

}
