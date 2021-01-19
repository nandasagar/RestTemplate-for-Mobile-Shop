package com.shopnow.controller;

import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.data.domain.Page;

import com.shopnow.configuration.RestResponsePage;
import com.shopnow.exception.UserNotfoundException;
import com.shopnow.exception.ItemNotfoundException;
import com.shopnow.exception.OrderNotfoundException;
import com.shopnow.model.Item;
import com.shopnow.model.Payment;
import com.shopnow.model.User;

@RestController
@RequestMapping("/ShopNow")
public class ShopNowAdmin {

	@Autowired
	private RestTemplate restTemplate;
	Logger logger = LoggerFactory.getLogger(ShopNowAdmin.class);

	/**
	 * validating Admin data before Logging In
	 */
	@GetMapping(value = "/validateAdmin", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public void getUserData() {
		String targetUrl = UriComponentsBuilder.fromUriString("http://localhost:9092").path("/validateAdmin")
				.path("/{email}").path("/{password}").build().toString();
		RestTemplate restTemplate = new RestTemplate();
		Map<String, String> params = new HashMap<String, String>();
		params.put("email", "sagar@gmail.com");
		params.put("password", "sagar123");
		ResponseEntity<String> result = restTemplate.getForEntity(targetUrl, String.class, params);
		if (Objects.isNull(result)) {
			logger.error("error found in getUserData");
			throw new UserNotfoundException("No Users Found.!");
		}
		logger.info("ShopNowAdmin getUserData() response{}", result.getBody());
		System.out.println(result.getBody());
	}

	/**
	 * Admin saving new User Data.
	 */
	@PostMapping(value = "/saveAdmin")
	public void saveAdminDatausingExchange() {

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		String targetUrl = UriComponentsBuilder.fromUriString("http://localhost:9092").path("/saveAdmin").build()
				.toString();
		User user = new User("Ashwini@gmail.com", "NinuNanu", (long) 906616795, "we3", "Admin");

		HttpEntity<User> requestEntity = new HttpEntity<>(user, requestHeaders);

		ResponseEntity<User> result = restTemplate.exchange(targetUrl, HttpMethod.POST, requestEntity, User.class);
		if (Objects.isNull(result)) {
			logger.error("error found in saveAdminDatausingExchange");
			throw new UserNotfoundException("Registration Failed.!");
		}
		logger.info("ShopNowAdmin saveAdminDatausingExchange() response{}", result);
		System.out.println(result);
	}

	/**
	 * Admin Updating particular User Data.
	 */
	@PutMapping(value = "/updateAdmin")
	public void updateAdminDatausingExchange() {

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		String targetUrl = UriComponentsBuilder.fromUriString("http://localhost:9092").path("/updateAdmin").build()
				.toString();
		User user = new User(187, "Ashwini@gmail.com", "Nanu", (long) 906616795, "we2", "Admin");
		HttpEntity<User> requestEntity = new HttpEntity<>(user, requestHeaders);

		ResponseEntity<User> result = restTemplate.exchange(targetUrl, HttpMethod.PUT, requestEntity, User.class);
		if (Objects.isNull(result)) {
			logger.error("error found in updateAdminDatausingExchange");
			throw new UserNotfoundException("Upadation Failed.!");
		}
		logger.info("ShopNowAdmin updateAdminDatausingExchange() response{}", result);
		System.out.println(result);
	}

	/**
	 * Admin viewing all user's Orders Data.
	 */
	@GetMapping(value = "/allOrders")
	public void getOrders() {
		URI targetUrl = UriComponentsBuilder.fromUriString("http://localhost:9092").path("/Admin").path("/ordersAdmin")
				.build().toUri();
		ParameterizedTypeReference<RestResponsePage<Payment>> responsePage = new ParameterizedTypeReference<RestResponsePage<Payment>>() {
		};

		ResponseEntity<RestResponsePage<Payment>> response = restTemplate.exchange(targetUrl, HttpMethod.GET, null,
				responsePage);
		if (Objects.isNull(response)) {
			logger.error("error found in getOrders");
			throw new OrderNotfoundException("No Orders Found.!");
		}

		System.out.println(response.getBody());
		Page<Payment> pageOfOrders = response.getBody();
		logger.info("ShopNowAdmin getOrders() response{}", pageOfOrders);
		System.out.println("Size " + pageOfOrders.getSize());
		System.out.println(pageOfOrders.getContent());
		List<Payment> orders = pageOfOrders.getContent();
		System.out.println("Orders List Data" + orders);
	}

	/**
	 * Admin viewing all Items Data.
	 */
	@GetMapping(value = "/allProducts")
	public void getAllItems() {

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		URI targetUrl = UriComponentsBuilder.fromUriString("http://localhost:9092").path("/Admin").path("/products")
				.build().toUri();
		ParameterizedTypeReference<RestResponsePage<Item>> responsePage = new ParameterizedTypeReference<RestResponsePage<Item>>() {
		};
		ResponseEntity<RestResponsePage<Item>> response = restTemplate.exchange(targetUrl, HttpMethod.GET, null,
				responsePage);
		if (Objects.isNull(response)) {
			logger.error("error found in getAllItems");
			throw new ItemNotfoundException("No Items Found.!");
		}

		System.out.println(response.getBody());
		Page<Item> pageOfItem = response.getBody();
		logger.info("ShopNowAdmin getAllItems() response{}", pageOfItem);
		
		System.out.println("Size " + pageOfItem.getSize());
		System.out.println(pageOfItem.getContent());
		List<Item> itemList = pageOfItem.getContent();
		System.out.println("Item List Data" + itemList);
	}

	/**
	 * Admin viewing one particular Item Data.
	 */
	@GetMapping(value = "/oneProduct")
	public void getItemByModel() {

		String targetUrl = UriComponentsBuilder.fromUriString("http://localhost:9092").path("/User").path("/addcart")
				.path("/{model}").build().toString();

		Map<String, String> params = new HashMap<String, String>();
		params.put("model", "1015");
		// Using getForEntity
		ResponseEntity<Item> result = restTemplate.getForEntity(targetUrl, Item.class, params);
		// Using Exchange
		ResponseEntity<Item> resultExchange = restTemplate.exchange(targetUrl, HttpMethod.GET, null, Item.class,
				params);
		if (Objects.isNull(resultExchange)) {
			logger.error("error found in getItemByModel");
			throw new ItemNotfoundException("No Item Found.!");
		}
		logger.info("ShopNowAdmin getItemByModel() response{}", resultExchange.getBody());
		
		System.out.println("Using Exchange " + resultExchange.getBody());
		System.out.println("Using getforEntity " + result.getBody());
	}

	/**
	 * Admin viewing all users Data.
	 */
	@GetMapping(value = "/allUsers")
	public void getAllUsersByExchange() {
		URI targetUrl = UriComponentsBuilder.fromUriString("http://localhost:9092").path("/Admin").path("/users")
				.build().toUri();
		ParameterizedTypeReference<RestResponsePage<User>> responsePage = new ParameterizedTypeReference<RestResponsePage<User>>() {
		};
		ResponseEntity<RestResponsePage<User>> response = restTemplate.exchange(targetUrl, HttpMethod.GET, null,
				responsePage);
		if (Objects.isNull(response)) {
			logger.error("error found in getAllUsersByExchange");
			throw new UserNotfoundException("No Users Found.!");
		}

		System.out.println(response.getBody());
		Page<User> pageOfUser = response.getBody();
		logger.info("ShopNowAdmin getAllUsersByExchange() response{}", pageOfUser);
		
		System.out.println("Size " + pageOfUser.getSize());
		System.out.println(pageOfUser.getContent());
		List<User> userList = pageOfUser.getContent();
		System.out.println("Users List Data" + userList);
		for (User temp : userList) {
			if (temp.getRolename().contains("Admin"))
				System.out.println(temp);
		}
	}

	/**
	 * Admin Updating particular Item Data.
	 */
	@PutMapping(value = "/updateProducts")
	public void updateItem() {

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		String targetUrl = UriComponentsBuilder.fromUriString("http://localhost:9092").path("/Admin").path("/update")
				.path("/{model}").build().toString();
		Item updateItem = new Item(1003, "APPLE_IPHONE_12", "Black", 90000, "128 GB", "APPLE_IPHONE_12.jpg", 11, 1, 250,
				"applemobile");

		ParameterizedTypeReference<RestResponsePage<Item>> responsePage = new ParameterizedTypeReference<RestResponsePage<Item>>() {
		};
		HttpEntity<Item> requestEntity = new HttpEntity<Item>(updateItem, requestHeaders);
		Map<String, String> params = new HashMap<String, String>();
		params.put("model", "1003");
		ResponseEntity<RestResponsePage<Item>> response = restTemplate.exchange(targetUrl, HttpMethod.PUT,
				requestEntity, responsePage, params);
		if (Objects.isNull(response)) {
			logger.error("error found in updateItem");
			throw new ItemNotfoundException("Item Updation Failed.!");
		}
		System.out.println(response.getBody());
		Page<Item> pageOfItem = response.getBody();
		logger.info("ShopNowAdmin updateItem() response{}", pageOfItem);
		
		System.out.println("Size " + pageOfItem.getSize());
		System.out.println(pageOfItem.getContent());
		List<Item> itemList = pageOfItem.getContent();
		System.out.println("Item List Data " + itemList);
	}

	/**
	 * All Items from user's order table are Sent to payment section with Users
	 * Other Data.
	 */
	@PostMapping(value = "/newProduct")
	public void addNewItem() {

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		String targetUrl = UriComponentsBuilder.fromUriString("http://localhost:9092").path("/Admin").path("/saveItem")
				.build().toString();
		Item updateItem = new Item(1036, "DEll_USB", "Black", 1800, " fast charging", "DEll_USB.jpg", 22, 2, 250,
				"usb");

		ParameterizedTypeReference<RestResponsePage<Item>> responsePage = new ParameterizedTypeReference<RestResponsePage<Item>>() {
		};
		HttpEntity<Item> requestEntity = new HttpEntity<Item>(updateItem, requestHeaders);
		ResponseEntity<RestResponsePage<Item>> response = restTemplate.exchange(targetUrl, HttpMethod.POST,
				requestEntity, responsePage);
		if (Objects.isNull(response)) {
			logger.error("error found in addNewItem");
			throw new ItemNotfoundException("Item Addition Failed.!");
		}
		System.out.println(response.getBody());
		Page<Item> pageOfItem = response.getBody();
		logger.info("ShopNowAdmin addNewItem() response{}", pageOfItem);
		
		System.out.println("Size " + pageOfItem.getSize());
		System.out.println(pageOfItem.getContent());
		List<Item> itemList = pageOfItem.getContent();
		System.out.println("Item List Data after adding " + itemList);
	}

	/**
	 * All Items from user's order table are Sent to payment section with Users
	 * Other Data.
	 */
	@DeleteMapping(value = "/removeProduct")
	public void removeItem() {

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		String targetUrl = UriComponentsBuilder.fromUriString("http://localhost:9092").path("/Admin").path("/delete")
				.path("/{model}")/* .path("/{page}") */ .build().toString();

		ParameterizedTypeReference<RestResponsePage<Item>> responsePage = new ParameterizedTypeReference<RestResponsePage<Item>>() {
		};
		Map<String, String> params = new HashMap<String, String>();
		params.put("model", "1036");
		ResponseEntity<RestResponsePage<Item>> response = restTemplate.exchange(targetUrl, HttpMethod.DELETE, null,
				responsePage, params);
		if (Objects.isNull(response)) {
			logger.error("error found in removeItem");
			throw new ItemNotfoundException("Item Removal Failed.!");
		}
		System.out.println(response.getBody());
		Page<Item> pageOfItem = response.getBody();
		logger.info("ShopNowAdmin removeItem() response{}", pageOfItem);
		
		System.out.println("Size " + pageOfItem.getSize());
		System.out.println(pageOfItem.getContent());
		List<Item> itemList = pageOfItem.getContent();
		System.out.println("Item List Data after Delete " + itemList);
	}

}