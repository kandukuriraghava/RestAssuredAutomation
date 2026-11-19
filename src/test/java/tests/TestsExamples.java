package tests;
import org.testng.Assert;
import org.testng.annotations.Test;

import POJOS.User;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class TestsExamples {
	
	@Test
	public void test1() {
		//Reading Response
		Response response = get("https://reqres.in/api/users/2");
		int statusCode = response.getStatusCode();
		System.out.println("Status Code:"+statusCode);
		System.out.println("Content Type:"+response.getContentType());
		System.out.println(response.getStatusLine());
		System.out.println(response.asString());
		System.out.println("Content Type:"+response.getHeader("Content-Type"));
		System.out.println(response.getTime());
		Assert.assertEquals(statusCode, 200,"Response code should be 200");
	}
	

	@Test
	public void test2() {
		baseURI = "https://reqres.in/api";
		Response response = get("/users/2");
		response.then().statusCode(200).body("data.id",equalTo(2)).log().all();
		response.then().assertThat().body(matchesJsonSchemaInClasspath("ReqRes.json"));
	}

	@Test
	public void testPost() {
		User userPayload = new User();
		userPayload.setName("Raghava");
		userPayload.setJob("Tester");	
		baseURI = "https://reqres.in/api";
		
		given().
		contentType(ContentType.JSON).
		header("x-api-key","free_user_3MHZ0s9v1itZfyOamiQNbowIWUe").
			body(userPayload).
		when().
			post("/users").then().statusCode(201);
	}
	
	@Test
	public void testPut() {
		User userPayload = new User();
		userPayload.setName("Rudvik");
		userPayload.setJob("Schooling");	
		baseURI = "https://reqres.in/api";
		
		given().
		contentType(ContentType.JSON).
		header("x-api-key","free_user_3MHZ0s9v1itZfyOamiQNbowIWUe").
			body(userPayload).
		when().
			put("/users/2").
		then().
			statusCode(200).
		log().all();
	}
	
	@Test
	public void testPatch() {
		User userPayload = new User();
		userPayload.setName("Rudvik Arya");
		baseURI = "https://reqres.in/api";
		
		given().
		contentType(ContentType.JSON).
		header("x-api-key","free_user_3MHZ0s9v1itZfyOamiQNbowIWUe").
			body(userPayload).
		when().
			patch("/users/2").
		then().
			statusCode(200).
		log().all();
	}
	
	public static void main(String[] args) {
		System.out.println("Hello World");
	}

	public void sayHello() {
		System.out.println("Hello");
	}

}
