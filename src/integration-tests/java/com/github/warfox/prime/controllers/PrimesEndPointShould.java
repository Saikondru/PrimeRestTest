package com.github.warfox.prime.controllers;

import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class PrimesEndPointShould {
	
	/* Setting the base uri as local host */
	
	@BeforeClass
	  public static void setBaseUri () {

	    RestAssured.baseURI = "http://localhost:8080";
	  }
	
	/*  Testing the default limit as 10  */

	@Test
	public void returnsPrimeNumbersBelow10ByDefault() {
		when().
		  get("/primes").
		then().
		  contentType(ContentType.JSON).
		  statusCode(200).
		and().
		  body("limit", equalTo(10)).
		  body("primes", hasSize(4)).
		  body("primes", hasItems(2, 3, 5, 7));

	}
	
	/*  Testing the default limit as 10 for xml */

	@Test
	public void returnsPrimeNumbersBelow10ByDefaultForXml() {
		when().
		  get("/primes.xml").
		then().
		  contentType(ContentType.XML).
		  statusCode(200).
		and().
		 body("prime-response.limit", equalTo("10")).
		 body("prime-response.primes.prime", hasItems("2", "3", "5", "7"));

	}
	
	/*  Testing the response with limit as 2 and response with just one number */

	@Test
	public void returnsPrimeNumberWithOneNumber() {
		when().
		  get("/primes/2").
		then().
		  contentType(ContentType.JSON).
		  statusCode(200).
		and().
		  body("limit", equalTo(2)).
		  body("primes", hasSize(1)).
		  body("primes", hasItem(2));

	}
	
	/*  Testing the response with no prime number */

	@Test
	public void returnsNoNumber() {
		when().
		  get("/primes/1").
		then().
		  contentType(ContentType.JSON).
		  statusCode(200).
		and().
		  body("limit", equalTo(1)).
		  body("primes", hasSize(0)).
		  body("primes", hasItems());

	}
	
	/*  Testing the response for negative number as limit */

	@Test
	public void returnsNoNumberForLimitAsNagativeInteger() {
		when().
		  get("/primes/-1").
		then().
		  contentType(ContentType.JSON).
		  statusCode(200).
		and().
		  body("limit", equalTo(-1)).
		  body("primes", hasSize(0)).
		  body("primes", hasItems());

	}
	
	/*  Testing the default content type with limit as 20 */

	@Test
	public void returnsPrimeNumbersInJSONByDefaultWithLimit() {
		when().
		  get("/primes/20").
		then().
		  contentType(ContentType.JSON).
		  header("Cache-Control", "max-age=31536000").
		  statusCode(200).
		and().
		  body("limit", equalTo(20)).
		  body("primes", hasSize(8)).
		  body("primes", hasItems(2, 3, 5, 7, 11, 13, 17, 19));

	}
	
	/*  Testing the response with header as XML  */

	@Test
	public void returnsPrimeNumbersInXMLForXMLHeader() {
		given().
		  header("Accept", "application/xml").
		when().
		  get("/primes/25").
		then().
		  contentType(ContentType.XML).
		  header("Cache-Control", "max-age=31536000").
		  statusCode(200).
		and().
	      body("prime-response.limit", equalTo("25")).
		  body("prime-response.primes.prime", hasItems("2", "3", "5", "7", "11", "13", "17", "19", "23"));

	}
	
	/* Testing the response with path extension as xml  */

	@Test
	public void returnsPrimeNumbersInXMLForXmlExtension() {
		when().
		  get("/primes/25.xml").
		then().
		  contentType(ContentType.XML).
		  header("Cache-Control", "max-age=31536000").
		  statusCode(200).
		and().
		  body("prime-response.limit", equalTo("25")).
		  body("prime-response.primes.prime", hasItems("2", "3", "5", "7", "11", "13", "17", "19", "23"));

	}
	
	/*  Testing the response with header as JSON  */

	//@Test
	public void returnsPrimeNumbersInJsonForJsonHeader() {
		given().
		  header("Accept", "application/json").
		when().
		  get("/primes/25").
		then().
		  contentType(ContentType.JSON).
		  header("Cache-Control", "max-age=31536000").
		  statusCode(200).
		and().
		  body("primes", hasSize(9)).
		  body("limit", equalTo(25)).
		  body("primes", hasItems(2, 3, 5, 7, 11, 13, 17, 19, 23));

	}
	
	/* Testing the response with path extension as json  */

	@Test
	public void returnsPrimeNumbersInJsonForJsonExtension() {
		when().
		  get("/primes/25.json").
		then().
		  contentType(ContentType.JSON).
		  header("Cache-Control", "max-age=31536000").
		  statusCode(200).
		and().
		  body("primes", hasSize(9)).
		  body("limit", equalTo(25)).
		  body("primes", hasItems(2, 3, 5, 7, 11, 13, 17, 19, 23));

	}
	
	/*  Testing the response type for media type as json  */

	@Test
	public void returnsPrimeNumbersInJsonForMediaTypeJson() {
		given().
		  param("mediaType", "json").
		when().
		  get("/primes/25").
		then().
		  contentType(ContentType.JSON).
		  header("Cache-Control", "max-age=31536000").
		  statusCode(200).
		and().
		  body("primes", hasSize(9)).
		  body("limit", equalTo(25)).
		  body("primes", hasItems(2, 3, 5, 7, 11, 13, 17, 19, 23));

	}
	
	/*  Testing the response type for media type as xml  */

	@Test
	public void returnsPrimeNumbersInXMLForMediaTypeXML() {
		given().
		  param("mediaType", "xml").
		when().
		  get("/primes/25").
		then().
		  contentType(ContentType.XML).
		  header("Cache-Control", "max-age=31536000").
		  statusCode(200).
		and().
		  body("prime-response.limit", equalTo("25")).
		  body("prime-response.primes.prime", hasItems("2", "3", "5", "7", "11", "13", "17", "19", "23"));

	}
	
	/* Testing Etag and cache-Control headers in the default response which is json */
	
	@Test
	public void returnsPrimeNumbersAndDefaultResponseWithHeadersEtagCacheControl() {
		when().
	      get("/primes/45").
	    then().
	      contentType(ContentType.JSON).
	      header("Cache-Control", "max-age=31536000").
	      header("Etag", "\"01eadeaa2cceb223972c1336c1c9ee7a3\"").
	      statusCode(200).
	    and().
	      body("primes", hasSize(14)).
		  body("limit", equalTo(45)).
		  body("primes", hasItems(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43));
	      
	}
	
	/* Testing Etag and Cache-Control headers in the response with request header as json */
	
	@Test
	public void returnsPrimeNumbersAndJsonResponseWithHeadersEtagCacheControl() {
		given().
		  header("Accept", "application/json").
		when().
	      get("/primes/45").
	    then().
	      contentType(ContentType.JSON).
	      header("Cache-Control", "max-age=31536000").
	      header("Etag", "\"01eadeaa2cceb223972c1336c1c9ee7a3\"").
	      statusCode(200).
	    and().
	      body("primes", hasSize(14)).
		  body("limit", equalTo(45)).
		  body("primes", hasItems(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43));
			  
	      
	}
	
	/* Testing Etag and Cache-Control header in the response with request header as xml */
	
	@Test
	public void returnsPrimeNumbersAndXmlResponseWithHeaderEtagCacheControl() {
		given().
		  header("Accept", "application/xml").
		when().
	      get("/primes/45").
	    then().
	      contentType(ContentType.XML).
	      header("Cache-Control", "max-age=31536000").
	      header("Etag", "\"0613760bfe19126cc286737035a8a8628\"").
	      statusCode(200).
	    and().
	      body("prime-response.limit", equalTo("45")).
		  body("prime-response.primes.prime", hasItems("2", "3", "5", "7", "11", "13", "17", "19", "23", "29", "31", "37", "41", "43"));
	      
	}
	
	/* Testing response with invalid path parameter */
    
    //@Test
	public void returnsPrimeNumbersInJsonForInvalidExtension() {
		when().
		  get("/primes/25.invalid").
		then().
		  contentType(ContentType.JSON).
		  header("Cache-Control", "max-age=31536000").
		  statusCode(200).
		and().
		  body("primes", hasSize(9)).
		  body("limit", equalTo(25)).
		  body("primes", hasItems(2, 3, 5, 7, 11, 13, 17, 19, 23));

	}
	
    /* Testing performance with in 3 seconds */
	
	@Test
	public void returnsPrimeNumbersWithinGoodTime() {
		given().
		  header("Accept", "application/json").
		when().
	      get("/primes/100").
	    then().
	      contentType(ContentType.JSON).
	      header("Cache-Control", "max-age=31536000").
	      statusCode(200).
	    and().
	      body("primes", hasSize(25)).
		  body("limit", equalTo(100)).
		  body("primes", hasItems(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89,97)).
		  time(lessThan(3000L));
		  
	}
	
	/* Testing the response when content types of header as json and  path extension as xml  */

	@Test
	public void returnsPrimeNumbersForDifferentHeaderAsJsonPathAsXml() {
		given().
		  header("Accept", "application/json").
		when().
		  get("/primes/25.xml").
		then().
		  contentType(ContentType.XML).
		  header("Cache-Control", "max-age=31536000").
		  statusCode(200).
		and().
		  body("prime-response.limit", equalTo("25")).
		  body("prime-response.primes.prime", hasItems("2", "3", "5", "7", "11", "13", "17", "19", "23"));

	}
	
	/* Testing the response when content types of header as xml and path extension as json  */

	@Test
	public void returnsPrimeNumbersForDifferentHeaderAsXmlPathAsJson() {
		given().
		  header("Accept", "application/xml").
		when().
		  get("/primes/25.json").
		then().
		  contentType(ContentType.JSON).
		  header("Cache-Control", "max-age=31536000").
		  statusCode(200).
		and().
		  body("primes", hasSize(9)).
		  body("limit", equalTo(25)).
		  body("primes", hasItems(2, 3, 5, 7, 11, 13, 17, 19, 23));

	}
	
	/* Negative Scenarios as follows */
	
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
		
    /* Testing response with invalid limit as ten instead of 10 */
		
		@Test
		public void returnsErrorForInvalidLimit() {
			when().
			  get("/primes/ten").
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
		
		
 }
