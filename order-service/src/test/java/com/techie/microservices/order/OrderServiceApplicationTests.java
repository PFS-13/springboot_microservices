//package com.techie.microservices.order;
//
//import com.techie.microservices.order.client.InventoryClient;
//import io.restassured.RestAssured;
//import org.hamcrest.Matchers;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.server.LocalServerPort;
//import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
//import org.testcontainers.containers.MySQLContainer;
//import com.techie.microservices.order.stubs.InventoryClientStub;
//
//
//import static org.hamcrest.MatcherAssert.assertThat;
//
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureWireMock(port = 0)
//class OrderServiceApplicationTests {
//
//	@ServiceConnection
//	static MySQLContainer mySQLContainer = new MySQLContainer("mysql:8.3.0");
//	@LocalServerPort
//	private Integer port;
//    @Autowired
//    private InventoryClient inventoryClient;
//
//	@BeforeEach
//	void setup() {
//		RestAssured.baseURI = "http://localhost";
//		RestAssured.port = port;
//	}
//
//	static {
//		mySQLContainer.start();
//	}
//
//	@Test
//	void shouldSubmitOrder() {
//		String submitOrderJson = """
//                {
//                     "skuCode": "iphone_15",
//                     "price": 1000,
//                     "quantity": 1
//                }
//                """;
//		InventoryClientStub.stubInventoryCall("iphone_15",1);
//		var responseBodyString = RestAssured.given()
//				.contentType("application/json")
//				.body(submitOrderJson)
//				.when()
//				.post("/api/order")
//				.then()
//				.log().all()
//				.statusCode(201)
//				.extract()
//				.body().asString();
//
//		assertThat(responseBodyString, Matchers.is("Order Placed Successfully"));
//	}
//}
////
////In Part 3 of this **Spring Boot Microservices Tutorial** series, we will implement the API Gateway pattern using the Spring Cloud Gateway MVC library.
////
////		## What is an API Gateway?
////
////An API Gateway also called an Edge Server, acts as an entry point for our microservices, so that external clients can access the services easily. It also helps us to handle cross-cutting concerns like Monitoring, Security, etc. In some instances, API Gateway also acts as Load Balancers.
////
////## Why to use API Gateway?
////
////In our microservice project landscape, we have 3 services accessible to the user:
////
////		- Product Service
////
////- Order Service
////
////- Inventory service
////
////		For example, imagine that external clients like Web and Mobile applications consume these three independent services through the exposed endpoints. If the internal implementation of these services changes, then also the clients need to update the Endpoints on their side.
////
////To workaround this issue, we use an API Gateway as the facade that provides an abstraction over the internal microservices.
////
////		## Spring Cloud Gateway MVC
////
////**Spring Cloud Gateway MVC** is a library under the Spring Cloud project, that provides the API Gateway functionality. Let's go ahead and create the API Gateway for our project, as usual, we use the start.spring.io website to create the project.
////
////		![Start.spring.io for creating Spring Cloud Gateway](images/image-6-1024x663.png)
////
////Make sure you use the above configuration and click on Generate Project to download the source code to your machine.
////
////As we learned before, an API Gateway acts as an abstraction over the microservices, and it forwards the request from the client to the relevant microservices.
////
////To implement this feature, Spring Cloud Gateway uses the below building blocks:
////
////		- Routes
////
////- Predicates
////
////- Filters
////
////### Routes
////
////A Route is the basic building block of the gateway, it can be defined using a uniqueId, a destination URI, and a collection of predicates and filters
////
////### Predicates
////
////A Predicate is nothing but a criteria or a condition that you define to match against the incoming HTTP Request, for example, you can create a routing rule where you want to route the requests that have a specific Header and Request Parameter to Service A, then you can consider the headers and request parameters you want to match against the request as predicates.
////
////### Filters
////
////Filters are components that allow you to modify the requests and responses before they are sent to the destination.
////
////		Let's see how we can implement the API Gateway in our project using Spring Cloud Gateway MVC.
