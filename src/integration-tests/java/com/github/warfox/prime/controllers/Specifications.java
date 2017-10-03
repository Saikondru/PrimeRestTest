package com.github.warfox.prime.controllers;

import org.junit.BeforeClass;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

public class Specifications {
	
	public static ResponseSpecBuilder builder;
	public static ResponseSpecification jsonResponseSpec;
	public static ResponseSpecification xmlResponseSpec;
	
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

}
