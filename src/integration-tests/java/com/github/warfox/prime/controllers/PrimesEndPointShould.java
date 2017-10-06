package com.github.warfox.prime.controllers;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.specification.ResponseSpecification;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PrimesEndPointShould {

	public static ResponseSpecBuilder builder;
	public static ResponseSpecification jsonResponseSpec;
	public static ResponseSpecification xmlResponseSpec;
	public static final String xml25response = "23571113171923";
	public static final String json25response = "[2, 3, 5, 7, 11, 13, 17, 19, 23]";
	
    /* Setting the base uri as local host */
	
    @BeforeClass
	public static void setBaseUri () {

    	RestAssured.baseURI = "http://localhost:8080";
	  }
	
    /* For Code re usability to check json content type, Cache control header and status code */
	
	@BeforeClass
    public static void setupJSONResponseSpecBuilder()
    {
        builder = new ResponseSpecBuilder();
        
        builder.expectStatusCode(200);
        
        builder.expectHeader("Cache-Control", "max-age=31536000");
         
        builder.expectContentType(ContentType.JSON);
        
        jsonResponseSpec = builder.build();
    }
	
    /* For Code re usability to check xml content type, Cache control header and status code */
	
	@BeforeClass
    public static void setupXMLResponseSpecBuilder()
    {
        builder = new ResponseSpecBuilder();
        
        builder.expectStatusCode(200);
        
        builder.expectHeader("Cache-Control", "max-age=31536000");
        
        builder.expectContentType(ContentType.XML);
        
        xmlResponseSpec = builder.build();
    }
	
	/*  Testing the default limit as 10  */

    @Test
	public void returnsPrimeNumbersBelow10ByDefault() {
		when().
		  get("/primes").
		then().
		  spec(jsonResponseSpec).
		and().
		  body("limit", equalTo(10)).
		  body("primes", hasSize(4)).
		  body("primes", hasItems(2, 3, 5, 7));

	}
	
	/*  Testing the default limit as 10 for xml */

	@Test
	public void returnsPrimeNumbersBelow10ByDefaultForXml() {
		given().
		  header("Accept", "application/xml").
		when().
		  get("/primes").
		then().
		  spec(xmlResponseSpec).
		and().
		 body("prime-response.limit", equalTo("10")).
		 body("prime-response.primes.prime", hasItems("2", "3", "5", "7"));

	}
	
	/*  Testing the response for the only even prime number within the limit  */

	@Test
	public void returnsOnePrimeNumber() {
		when().
		  get("/primes/2").
		then().
		  spec(jsonResponseSpec).
		and().
		  body("limit", equalTo(2)).
		  body("primes", hasSize(1)).
		  body("primes", hasItem(2));

	}
	
	/*  Testing the response for no prime number within the limit */

	@Test
	public void returnsNoNumber() {
		when().
		  get("/primes/1").
		then().
		  spec(jsonResponseSpec).
		and().
		  body("limit", equalTo(1)).
		  body("primes", hasSize(0));
		 
	}
	
	/*  Testing the response for no prime number with negative number as limit */

	@Test
	public void returnsNoNumberForLimitAsNagativeInteger() {
		when().
		  get("/primes/-1").
		then().
		  spec(jsonResponseSpec).
		and().
		  body("limit", equalTo(-1)).
		  body("primes", hasSize(0));
		  
	}
	
	/*  Testing the response for no prime number with negative number 10 as limit */

	@Test
	public void returnsNoNumberForLimitAsNagativeInteger10() {
		when().
		  get("/primes/-10").
		then().
		  spec(jsonResponseSpec).
		and().
		  body("limit", equalTo(-10)).
		  body("primes", hasSize(0));
		  
	}
	
	/*  Testing the response for no prime number with decimal as limit */

	@Test
	public void returnsNoNumberForLimitAsDecimal() {
		when().
		  get("/primes/0.9").
		then().
		  spec(jsonResponseSpec).
		and().
		  body("limit", equalTo(0)).
		  body("primes", hasSize(0));
		  
	}
	
	/*  Testing the response for limit as decimal 10.89 for xml */

	@Test
	public void returnsPrimeNumbersBelow10ForDecmal10ForXml() {
		given().
		  header("Accept", "application/xml").
		when().
		  get("/primes/10.89").
		then().
		  spec(xmlResponseSpec).
		and().
		 body("prime-response.limit", equalTo("10")).
		 body("prime-response.primes.prime", hasItems("2", "3", "5", "7"));

	}
	
	/*  Testing the response for prime number with Hex 0x3B0 which is 59 as limit */

	@Test
	public void returnsPrimeNumbersForLimitAsHex59() {
		when().
		  get("/primes/0x3B").
		then().
		  spec(jsonResponseSpec).
		and().
		  body("limit", equalTo(59)).
		  body("primes", hasSize(17)).
		  body("primes", hasItems(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59));
		  
	}
	
	/*  Testing the response for prime number with Hex 0x3B0 which is 59 as limit and content type xml */

	@Test
	public void returnsPrimeNumbersForLimitAsHex59ContentTypeXml() {
		when().
		  get("/primes/0x3B.xml").
		then().
		  spec(xmlResponseSpec).
		and().
		  body("prime-response.limit", equalTo("59")).
		  body("prime-response.primes.prime", hasItems("2", "3", "5", "7", "11", "13", "17", "19", "23", "29", "31", "37", "41", "43", "47", "53", "59"));
		  
	}
		
	/*  Testing the default content type with 20 as limit */

	@Test
	public void returnsPrimeNumbersInJSONByDefaultWithLimit() {
		when().
		  get("/primes/20").
		then().
		  spec(jsonResponseSpec).
		and().
		  body("limit", equalTo(20)).
		  body("primes", hasSize(8)).
		  body("primes", hasItems(2, 3, 5, 7, 11, 13, 17, 19));

	}
	
	/*  Testing the response with header as json  */

	@Test
	public void returnsPrimeNumbersInJsonForJsonHeader() {
		given().
		  header("Accept", "application/json").
		when().
		  get("/primes/25").
		then().
		  spec(jsonResponseSpec).
		and().
		  body("primes", hasSize(9)).
		  body("limit", equalTo(25)).
		  body("primes", hasItems(2, 3, 5, 7, 11, 13, 17, 19, 23));

	}
	
	/*  Testing the response with header as xml  */

	@Test
	public void returnsPrimeNumbersInXMLForXMLHeader() {
		given().
		  header("Accept", "application/xml").
		when().
		  get("/primes/30").
		then().
		  spec(xmlResponseSpec).
		and().
	      body("prime-response.limit", equalTo("30")).
		  body("prime-response.primes.prime", hasItems("2", "3", "5", "7", "11", "13", "17", "19", "23", "29"));

	}
	
	/* Testing the response with path extension as json  */

	@Test
	public void returnsPrimeNumbersInJsonForJsonExtension() {
		when().
		  get("/primes/35.json").
		then().
		  spec(jsonResponseSpec).
		and().
		  body("primes", hasSize(11)).
		  body("limit", equalTo(35)).
		  body("primes", hasItems(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31));

	}
	
	/* Testing the response with path extension as xml  */

	@Test
	public void returnsPrimeNumbersInXMLForXmlExtension() {
		when().
		  get("/primes/40.xml").
		then().
		  spec(xmlResponseSpec).
		and().
		  body("prime-response.limit", equalTo("40")).
		  body("prime-response.primes.prime", hasItems("2", "3", "5", "7", "11", "13", "17", "19", "23", "29", "31", "37"));

	}
	
	/*  Testing the response type for media type as json  */

	@Test
	public void returnsPrimeNumbersInJsonForMediaTypeJson() {
		given().
		  param("mediaType", "json").
		when().
		  get("/primes/45").
		then().
		  spec(jsonResponseSpec).
		and().
		  body("primes", hasSize(14)).
		  body("limit", equalTo(45)).
		  body("primes", hasItems(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43));

	}
	
	/*  Testing the response type for media type as xml  */

	@Test
	public void returnsPrimeNumbersInXMLForMediaTypeXML() {
		given().
		  param("mediaType", "xml").
		when().
		  get("/primes/50").
		then().
		  spec(xmlResponseSpec).
		and().
		  body("prime-response.limit", equalTo("50")).
		  body("prime-response.primes.prime", hasItems("2", "3", "5", "7", "11", "13", "17", "19", "23", "29", "31", "37", "41", "43", "47"));

	}
	
	/* Testing Etag header in the default response(json) */
	
	@Test
	public void returnsPrimeNumbersAndDefaultResponseWithHeadersEtag() {
		when().
	      get("/primes").
	    then().
	      spec(jsonResponseSpec).
	      header("Etag", "\"005ac9c4b7a0623691dabe61f78e26f8e\"").
	    and().
	      body("primes", hasSize(4)).
		  body("limit", equalTo(10)).
		  body("primes", hasItems(2, 3, 5, 7));
	      
	}
	
	/* Testing Etag header in the response with request header as json and with a limit */
	/* Here I am also checking whether Etag has changed compared to the previous request */
	
	@Test
	public void returnsPrimeNumbersAndJsonResponseWithHeaderEtag() {
		given().
		  header("Accept", "application/json").
		when().
	      get("/primes/55").
	    then().
	      spec(jsonResponseSpec).
	      header("Etag", "\"09781ebb1e6ee53b6ee323a369bdc54f8\"").
	    and().
	      body("primes", hasSize(16)).
		  body("limit", equalTo(55)).
		  body("primes", hasItems(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53));
			  
	      
	}
	
	/* Testing Etag header in the response with request header as xml */
	/* Here I am passing the same limit but the content type as xml to check whether Etag has changed compared to the previous request */
	
	@Test
	public void returnsPrimeNumbersAndXmlResponseWithHeaderEtag() {
		given().
		  header("Accept", "application/xml").
		when().
	      get("/primes/55").
	    then().
	      spec(xmlResponseSpec).
	      header("Etag", "\"09550dca24444df9e1916664b40bc4cff\"").
	    and().
	      body("prime-response.limit", equalTo("55")).
		  body("prime-response.primes.prime", hasItems("2", "3", "5", "7", "11", "13", "17", "19", "23", "29", "31", "37", "41", "43", "47", "53"));
	      
	}
	
	/* Test to check the header Content Length for default response(json)  */
	
	@Test
	public void returnsPrimeNumbersAndJsonResponseWithHeaderContentLength() {
		when().
	      get("/primes").
	    then().
	      spec(jsonResponseSpec).
	      header("Content-Length", "31").
	    and().
	      body("primes", hasSize(4)).
		  body("limit", equalTo(10)).
		  body("primes", hasItems(2, 3, 5, 7));
	}
	
    /* Test to check the header Content Length for xml response  */
	/* Here I am also checking whether the content length is different for content type xml with same limit as json */
	
	@Test
	public void returnsPrimeNumbersAndXmlResponseWithHeaderContentLength() {
		given().
		  header("Accept", "application/xml").
		when().
	      get("/primes").
	    then().
	      spec(xmlResponseSpec).
	      header("Content-Length", "131").
	    and().
	      body("prime-response.limit", equalTo("10")).
		  body("prime-response.primes.prime", hasItems("2", "3", "5", "7"));
	}
	
    /* Test to check the header content length with a limit, for a json response  */
	
	@Test
	public void returnsPrimeNumbersAndJsonResponseWithHeaderContentLengthForLimit() {
		when().
	      get("/primes/60").
	    then().
	      spec(jsonResponseSpec).
	      header("Content-Length", "70").
	    and().
	      body("primes", hasSize(17)).
		  body("limit", equalTo(60)).
		  body("primes", hasItems(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59));
	}
	
	/* Test to check the header content length with a limit, for a xml response  */
	
	@Test
	public void returnsPrimeNumbersAndXmlResponseWithHeaderContentLengthForLimit() {
		given().
		  header("Accept", "application/xml").
		when().
	      get("/primes/65").
	    then().
	      spec(xmlResponseSpec).
	      header("Content-Length", "369").
	    and().
	      body("prime-response.limit", equalTo("65")).
		  body("prime-response.primes.prime", hasItems("2", "3", "5", "7", "11", "13", "17", "19", "23", "29", "31", "37", "41", "43", "47", "53", "59", "61"));
	}
	
	
	/* Testing response with invalid path parameter for default limit  */
    
    @Test
	public void returnsPrimeNumbersInJsonForInvalidExtension() {
		when().
		  get("/primes.invalid").
		then().
		  spec(jsonResponseSpec).
		and().
		  body("primes", hasSize(4)).
		  body("limit", equalTo(10)).
		  body("primes", hasItems(2, 3, 5, 7));

	}
    
    /* Testing response with invalid path parameter with a limit  */
    
    @Test
	public void returnsPrimeNumbersInJsonForInvalidExtensionwithLimit() {
		when().
		  get("/primes/15.invalid").
		then().
		  spec(jsonResponseSpec).
		and().
		  body("primes", hasSize(6)).
		  body("limit", equalTo(15)).
		  body("primes", hasItems(2, 3, 5, 7, 11, 13));

	}
    
    /* Assuming 3 seconds as good response time */
	
    /* Testing performance within 3 seconds for json */
    	
	@Test
	public void returnsPrimeNumbersWithinGoodTimeForJson() {
		given().
		  header("Accept", "application/json").
		when().
	      get("/primes/100").
	    then().
	      spec(jsonResponseSpec).
	    and().
	      body("primes", hasSize(25)).
		  body("limit", equalTo(100)).
		  body("primes", hasItems(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89,97)).
		  time(lessThan(3000L));
		  
	}
	
    /* Testing performance within 3 seconds for xml */
	
	@Test
	public void returnsPrimeNumbersWithinGoodTimeForXml() {
		given().
		  header("Accept", "application/xml").
		when().
	      get("/primes/100").
	    then().
	      spec(xmlResponseSpec).
	    and().
	      body("prime-response.limit", equalTo("100")).
		  body("prime-response.primes.prime", hasItems("2", "3", "5", "7", "11", "13", "17", "19", "23", "29", "31", "37", "41", "43", "47", "53", "59", "61", "67", "71", "73", "79", "83", "89", "97")).
		  time(lessThan(3000L));
		  
	}
	
	/* Testing the response when the header is json and path extension is xml */

	@Test
	public void returnsPrimeNumbersForHeaderAsJsonAndPathAsXml() {
		given().
		  header("Accept", "application/json").
		when().
		  get("/primes/25.xml").
		then().
		  spec(xmlResponseSpec).
		and().
		  body("prime-response.limit", equalTo("25")).
		  body("prime-response.primes.prime", hasItems("2", "3", "5", "7", "11", "13", "17", "19", "23"));

	}
	
	/* Testing the response when the header is xml and path extension is json */

	@Test
	public void returnsPrimeNumbersForHeaderAsXmlAndPathAsJson() {
		given().
		  header("Accept", "application/xml").
		when().
		  get("/primes/25.json").
		then().
		  spec(jsonResponseSpec).
		and().
		  body("primes", hasSize(9)).
		  body("limit", equalTo(25)).
		  body("primes", hasItems(2, 3, 5, 7, 11, 13, 17, 19, 23));

	}
	
	/* Testing the response when media Type is json and path extension is xml */
		
	@Test
	public void returnsPrimeNumbersForMediaTypeAsJsonPathAsXml() {
		given().
		  param("mediaType", "json").
		when().
		  get("/primes/25.xml").
		then().
		  spec(xmlResponseSpec).
		and().
		  body("prime-response.limit", equalTo("25")).
		  body("prime-response.primes.prime", hasToString(xml25response));
				
	}
	
	/* Testing the response when media Type is xml and path extension is json */

	@Test
	public void returnsPrimeNumbersForMediaTypeAsXmlPathAsJson() {
		given().
		  param("mediaType", "xml").
		when().
		  get("/primes/25.json").
		then().
		  spec(jsonResponseSpec).
		and().
		  body("primes", hasSize(9)).
		  body("limit", equalTo(25)).
		  body("primes", hasToString(json25response));

	}
	
	/* Testing the response when media Type is xml and header is json */
	
	@Test
	public void returnsPrimeNumbersForMediaTypeAsXmlHeaderAsJson() {
		given().
		  header("Accept", "application/json").
		  param("mediaType", "xml").
		when().
		  get("/primes/25").
		then().
		  spec(xmlResponseSpec).
		and().
		  body("prime-response.limit", equalTo("25")).
		  body("prime-response.primes.prime", hasToString(xml25response));

	}
	
	/* Testing the response when media Type is xml, header is xml and path extension is json */ 

	@Test
	public void returnsPrimeNumbersForMediaTypeAsJsonHeaderAsXml() {
		given().
		  header("Accept", "application/xml").
		  param("mediaType", "xml").
		when().
		  get("/primes/25.json").
		then().
		  spec(jsonResponseSpec).
		and().
		  body("primes", hasSize(9)).
		  body("limit", equalTo(25)).
		  body("primes", hasToString(json25response));

	}
	
	/* Testing the response when media Type is json, header is json and Path extension is xml */

	@Test
	public void returnsPrimeNumbersForMediaTypeAsJsonHeaderAsJsonParamXml() {
		given().
		  header("Accept", "application/json").
		  param("mediaType", "json").
		when().
		  get("/primes/25.xml").
		then().
		  spec(xmlResponseSpec).
		and().
		  body("prime-response.limit", equalTo("25")).
		  body("prime-response.primes.prime", hasToString(xml25response));

	}
	
	/* Testing the response when media Type is xml, header is xml and Path extension is json */

	@Test
	public void returnsPrimeNumbersForMediaTypeAsXmlHeaderAsXmlParamJson() {
		given().
		  header("Accept", "application/xml").
		  param("mediaType", "xml").
		when().
		  get("/primes/25.json").
		then().
		  spec(jsonResponseSpec).
		and().
		  body("primes", hasSize(9)).
		  body("limit", equalTo(25)).
		  body("primes", hasToString(json25response));

	}
	
	/* Testing response for limit of 10000 */
	
	@Test
	public void returnBigResponseTenThousand() {
		when().
		  get("/primes/10000").
		then().
		  spec(jsonResponseSpec);
					
	}
	
	/* Testing response for limit of 20000 */
	
	@Test
	public void returnBigResponseTwentyThousand() {
		when().
		  get("/primes/20000").
		then().
		  spec(jsonResponseSpec);
					
	}
	
    /* Testing response for date header as todays date */
	
    @Test
	public void returnsDate() {
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
		sdf = new SimpleDateFormat("EEE, dd MMM yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		Response response = when().get("/primes");
		String date = response.getHeader("Date");
		assertTrue(date.contains(sdf.format(now)));
	
				
	}
	
	/* Testing response for maximum limit */
	
	/* This test is commented as it will timeout and abort */
	
	//@Test
	public void returnsErrorForMaximumLimit() {
		when().
		  get("/primes/2147483647").
		then().
		  contentType(ContentType.JSON).
		  statusCode(200);
				
	}   
	
	/***********     Negative Scenarios as follows     **********/
	
	/* Testing response with invalid header */
	
    @Test
	public void returnsErrorForInvalidHeader() {
		given().
		  header("Accept", "application/invalid").
		when().
		  get("/primes").		   
		then().
		  statusCode(406).
		  header("Cache-Control", "must-revalidate,no-cache,no-store").
          header("Content-Length", "0");
						  
		}
	    
	/* Testing response with invalid media type */
		
    @Test
	public void returnsErrorForInvalidMediaType() {
		given().
		  param("mediaType", "invalid").
	    when().
		  get("/primes").
		then().
		  contentType(ContentType.HTML).
		  header("Cache-Control", "must-revalidate,no-cache,no-store").
		  statusCode(406).
		and().
		  assertThat().
		  body(containsString("Error 406 Not Acceptable")).
		  body(containsString("HTTP ERROR 406"));
			  
			
		}
		
    /* Testing response with string rather than integer 10 */
		
	@Test
	public void returnsErrorForStringLimit() {
		when().
		  get("/primes/ten").
		then().
		  contentType(ContentType.JSON).
		  statusCode(400).
		and().
		  body("error", equalTo("Bad Request")).
		  body("message", equalTo("Bad Request"));
			  
			
		}
	
	/* Testing response with combination of integer and string  */
	
	@Test
	public void returnsErrorForIntegerStringLimit() {
		when().
		  get("/primes/10ten").
		then().
		  contentType(ContentType.JSON).
		  statusCode(400).
		and().
		  body("error", equalTo("Bad Request")).
		  body("message", equalTo("Bad Request"));
			  
			
		}
	
    /* Testing response with combination of string and integer  */
	
	@Test
	public void returnsErrorForStringIntegerLimit() {
		when().
		  get("/primes/ten10").
		then().
		  contentType(ContentType.JSON).
		  statusCode(400).
		and().
		  body("error", equalTo("Bad Request")).
		  body("message", equalTo("Bad Request"));
			  
			
		}
		
    /* Testing response for maximum limit plus 1 */
		
    @Test
	public void returnsErrorForexceedingMaximumLimit() {
		when().
		  get("/primes/2147483648").
		then().
		  contentType(ContentType.JSON).
		  statusCode(400).
		and().
		  body("error", equalTo("Bad Request")).
		  body("message", equalTo("Bad Request"));
			  
			
		}   

  
 }
