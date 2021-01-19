package com.shopnow.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
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
import com.shopnow.exception.UserNotfoundException;
import com.shopnow.exception.ItemNotfoundException;
import com.shopnow.exception.CartNotfoundException;
import com.shopnow.exception.OrderNotfoundException;
import com.shopnow.configuration.RestResponsePage;
import com.shopnow.model.Cart;
import com.shopnow.model.Item;
import com.shopnow.model.Orders;
import com.shopnow.model.Payment;
import com.shopnow.model.User;

@RestController
@RequestMapping("/ShopNow")
public class ShopNowUser {

	@Autowired
	private RestTemplate restTemplate;
	Logger logger = LoggerFactory.getLogger(ShopNowUser.class);

	/**
	 * validating users data before Logging In
	 */
	@GetMapping(value = "/validateUsers", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public void getUserData() {
		String targetUrl = UriComponentsBuilder.fromUriString("http://localhost:9092").path("/validateUser")
				.path("/{email}").path("/{password}").build().toString();
		RestTemplate restTemplate = new RestTemplate();
		Map<String, String> params = new HashMap<String, String>();
		params.put("email", "Tam@gmail.com");
		params.put("password", "12345");
		ResponseEntity<String> result = restTemplate.getForEntity(targetUrl, String.class, params);
		if (Objects.isNull(result)) {
			logger.error("error found in getUserData");
			throw new UserNotfoundException("No Users Found.!");
		}
		logger.info("ShopNowUser getUserData() response{}", result);
		System.out.println(result.getBody());
	}

	/**
	 * Users Viewing items by their model
	 */
	@GetMapping(value = "/addCart")
	public void getItemByModel() {
		String targetUrl = UriComponentsBuilder.fromUriString("http://localhost:9092").path("/User").path("/addCart")
				.path("/{model}").build().toString();
		Map<String, String> params = new HashMap<String, String>();
		params.put("model", "1025");
		ResponseEntity<Item> result = restTemplate.getForEntity(targetUrl, Item.class, params);
		if (Objects.isNull(result)) {
			logger.error("error found in getItemByModel");
			throw new ItemNotfoundException("Item not Found.!");
		}
		logger.info("ShopNowUser getItemByModel() response{}", result);
		System.out.println(result.getBody());

	}

	/**
	 * Items currently in the user carts
	 */
	@GetMapping(value = "/getCartById")
	public void getCartById() {
		String targetUrl = UriComponentsBuilder.fromUriString("http://localhost:9092").path("/User").path("/getCart")
				.path("/{id}").build().toString();

		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "10");
		ResponseEntity<String> result = restTemplate.getForEntity(targetUrl, String.class, params);
		if (Objects.isNull(result)) {
			logger.error("error found in getCartById");
			throw new CartNotfoundException("Cart not Found.!");
		}
		logger.info("ShopNowUser getCartById() response{}", result);
		System.out.println(result.getBody());

	}

	/**
	 * Post Method Saving or Registering new User using Exchange
	 */
	@PostMapping(value = "/saveUser")
	public void saveUserDatausingExchange() {

		// setting up the request headers
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		String targetUrl = UriComponentsBuilder.fromUriString("http://localhost:9092").path("/saveUsers").build()
				.toString();
		User user = new User("nanu3@gmail.com", "Ninu3", (long) 906614460, "nanu3", "User");

		HttpEntity<User> requestEntity = new HttpEntity<>(user, requestHeaders);

		ResponseEntity<User> result = restTemplate.exchange(targetUrl, HttpMethod.POST, requestEntity, User.class);
		if (Objects.isNull(result)) {
			logger.error("error found in saveUserDatausingExchange");
			throw new UserNotfoundException("User Data not Found.!");
		}
		logger.info("ShopNowUser saveUserDatausingExchange() response{}", result);
		System.out.println(result);
	}

	/**
	 * Updating User Data
	 */
	@PutMapping(value = "/updateUser")
	public void updateAdminDatausingExchange() {

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		String targetUrl = UriComponentsBuilder.fromUriString("http://localhost:9092").path("/updateAdmin").build()
				.toString();
		User user = new User(157, "nanu3@gmail.com", "NinuPatikar", (long) 906614460, "nanu3", "User");

		HttpEntity<User> requestEntity = new HttpEntity<>(user, requestHeaders);

		ResponseEntity<User> result = restTemplate.exchange(targetUrl, HttpMethod.PUT, requestEntity, User.class);
		if (Objects.isNull(result)) {
			logger.error("error found in updateAdminDatausingExchange");
			throw new UserNotfoundException("User Data Updation Failed.!");
		}
		logger.info("ShopNowUser updateAdminDatausingExchange() response{}", result);
		System.out.println(result);
	}

	/**
	 * Selected Items saved to user's cart
	 */
	@PostMapping(value = "/saveCart")
	public void saveCartDatausingExchange() {

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		String targetUrl = UriComponentsBuilder.fromUriString("http://localhost:9092").path("/User").path("/saveCart")
				.build().toString();
		ParameterizedTypeReference<RestResponsePage<Cart>> responsePage = new ParameterizedTypeReference<RestResponsePage<Cart>>() {
		};
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "10");
		Cart cart = new Cart(10, "kl@gmail.com", "MI_2A", 1025, 3, 1200, 1200);

		HttpEntity<Cart> requestEntity = new HttpEntity<>(cart, requestHeaders);
		ResponseEntity<RestResponsePage<Cart>> response = restTemplate.exchange(targetUrl, HttpMethod.POST,
				requestEntity, responsePage, params);
		if (Objects.isNull(response)) {
			logger.error("error found in saveCartDatausingExchange");
			throw new CartNotfoundException("Saving Cart Data Failed.!");
		}
		
		System.out.println(response.getBody());
		Page<Cart> pageOfItem = response.getBody();
		logger.info("ShopNowUser saveCartDatausingExchange() response{}", pageOfItem);
		System.out.println("Size " + pageOfItem.getSize());
		System.out.println(pageOfItem.getContent());
		List<Cart> cartList = pageOfItem.getContent();
		System.out.println("Cart List Data after Adding " + cartList);

	}

	/**
	 * Selected Items deleted from user's cart
	 */
	@DeleteMapping(value = "/deleteCart")
	public void deleteCartData() {

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		String targetUrl = UriComponentsBuilder.fromUriString("http://localhost:9092").path("/User").path("/remove")
				.path("/{id}").path("/{cartid}").build().toString();
		ParameterizedTypeReference<RestResponsePage<Cart>> responsePage = new ParameterizedTypeReference<RestResponsePage<Cart>>() {
		};
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "10");
		params.put("cartid", "159");
		ResponseEntity<RestResponsePage<Cart>> response = restTemplate.exchange(targetUrl, HttpMethod.DELETE, null,
				responsePage, params);
		if (Objects.isNull(response)) {
			logger.error("error found in deleteCartData");
			throw new CartNotfoundException("Deletion of Cart Data Failed.!");
		}
		System.out.println(response.getBody());
		Page<Cart> pageOfItem = response.getBody();
		logger.info("ShopNowUser deleteCartData() response{}", pageOfItem);
		System.out.println("Size of Page " + pageOfItem.getSize());
		System.out.println(pageOfItem.getContent());
		List<Cart> cartList = pageOfItem.getContent();
		System.out.println("Cart List Data after Delete " + cartList);
		System.out.println("Size of cartList " + cartList.size());

	}

	/**
	 * Selected Items saved from cart to user's order table
	 */
	@PostMapping(value = "/saveOrder")
	public void saveOrdersDatausingExchange() {

		String targetUrl = UriComponentsBuilder.fromUriString("http://localhost:9092").path("/User").path("/saveOrders")
				.path("/{id}").build().toString();
		ParameterizedTypeReference<RestResponsePage<Orders>> responsePage = new ParameterizedTypeReference<RestResponsePage<Orders>>() {
		};
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "10");

		ResponseEntity<RestResponsePage<Orders>> response = restTemplate.exchange(targetUrl, HttpMethod.POST, null,
				responsePage, params);
		if (Objects.isNull(response)) {
			logger.error("error found in saveOrdersDatausingExchange");
			throw new OrderNotfoundException("Saving of Order Data Failed.!");
		}
		System.out.println(response.getBody());
		Page<Orders> pageOfItem = response.getBody();
		logger.info("ShopNowUser saveOrdersDatausingExchange() response{}", pageOfItem);
		System.out.println("page Size " + pageOfItem.getSize());
		System.out.println(pageOfItem.getContent());
		List<Orders> orderList = pageOfItem.getContent();
		System.out.println("Orders List Data after Adding " + orderList);

	}

	/**
	 * Selected Items deleted from user's order table
	 */
	@DeleteMapping(value = "/deleteOrder")
	public void deleteOrderData() {

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		String targetUrl = UriComponentsBuilder.fromUriString("http://localhost:9092").path("/User")
				.path("/removeOrder").path("/{id}").path("/{orderid}").build().toString();
		ParameterizedTypeReference<RestResponsePage<Orders>> responsePage = new ParameterizedTypeReference<RestResponsePage<Orders>>() {
		};
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "10");
		params.put("orderid", "162");
		ResponseEntity<RestResponsePage<Orders>> response = restTemplate.exchange(targetUrl, HttpMethod.DELETE, null,
				responsePage, params);
		if (Objects.isNull(response)) {
			logger.error("error found in deleteOrderData");
			throw new OrderNotfoundException("Deletion of Order Data Failed.!");
		}
		System.out.println(response.getBody());
		Page<Orders> pageOfItem = response.getBody();
		logger.info("ShopNowUser deleteOrderData() response{}", pageOfItem);
		
		System.out.println("Size of Page " + pageOfItem.getSize());
		System.out.println(pageOfItem.getContent());
		List<Orders> ordersList = pageOfItem.getContent();
		System.out.println("Orders List Data after Delete " + ordersList);
		System.out.println("Size of OrdersList " + ordersList.size());

	}

	/**
	 * Displaying saved Items from user's order table which is yet to be paid
	 */
	@GetMapping(value = "/unpaidOrders")
	public void getUnPaidOrdersDatausingExchange() {

		String targetUrl = UriComponentsBuilder.fromUriString("http://localhost:9092").path("/User")
				.path("/getAllOrder").path("/{id}").build().toString();
		ParameterizedTypeReference<RestResponsePage<Orders>> responsePage = new ParameterizedTypeReference<RestResponsePage<Orders>>() {
		};
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "10");

		ResponseEntity<RestResponsePage<Orders>> response = restTemplate.exchange(targetUrl, HttpMethod.GET, null,
				responsePage, params);
		if (Objects.isNull(response)) {
			logger.error("error found in getUnPaidOrdersDatausingExchange");
			throw new OrderNotfoundException("Incorrect Details Entered or No Data to Display UnPaidOrders!");
		}
		System.out.println(response.getBody());
		Page<Orders> pageOfItem = response.getBody();
		logger.info("ShopNowUser getUnPaidOrdersDatausingExchange() response{}", pageOfItem);
		
		System.out.println("page Size " + pageOfItem.getSize());
		System.out.println(pageOfItem.getContent());
		List<Orders> orderList = pageOfItem.getContent();
		System.out.println("Un Paid Orders List Data after Adding " + orderList);

	}

	/**
	 * All Items from user's order table are Sent to payment section with Users
	 * Other Data.
	 */
	@PostMapping(value = "/savePayment")
	public void savePaymentDatausingExchange() {

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		String targetUrl = UriComponentsBuilder.fromUriString("http://localhost:9092").path("/User").path("/savePay")
				.path("/{id}").build().toString();
		ParameterizedTypeReference<RestResponsePage<Payment>> responsePage = new ParameterizedTypeReference<RestResponsePage<Payment>>() {
		};
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "10");
		Payment payment = new Payment("Nandau", "Dasarahaliii", "Bangalore", "loan");
		HttpEntity<Payment> requestEntity = new HttpEntity<>(payment, requestHeaders);

		ResponseEntity<RestResponsePage<Payment>> response = restTemplate.exchange(targetUrl, HttpMethod.POST,
				requestEntity, responsePage, params);
		if (Objects.isNull(response)) {
			logger.error("error found in savePaymentDatausingExchange");
			throw new OrderNotfoundException("Saving of Payment Data Failed.!");
		}
		System.out.println(response.getBody());
		Page<Payment> pageOfItem = response.getBody();
		logger.info("ShopNowUser savePaymentDatausingExchange() response{}", pageOfItem);
		
		System.out.println("page Size " + pageOfItem.getSize());
		System.out.println(pageOfItem.getContent());
		List<Payment> orderList = pageOfItem.getContent();
		System.out.println("Paid Orders List Data after Adding " + orderList);

	}

	/**
	 * Displaying saved Items from user's order table which successfully paid
	 */
	@GetMapping(value = "/paidOrders")
	public void getPaidOrdersDatausingExchange() {

		String targetUrl = UriComponentsBuilder.fromUriString("http://localhost:9092").path("/User").path("/getOrder")
				.path("/{id}").build().toString();
		ParameterizedTypeReference<RestResponsePage<Payment>> responsePage = new ParameterizedTypeReference<RestResponsePage<Payment>>() {
		};
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "10");

		ResponseEntity<RestResponsePage<Payment>> response = restTemplate.exchange(targetUrl, HttpMethod.GET, null,
				responsePage, params);
		if (Objects.isNull(response)) {
			logger.error("error found in getUnPaidOrdersDatausingExchange");
			throw new CartNotfoundException("Incorrect Details Entered or No Data to Display PaidOrders!");
		}
		System.out.println(response.getBody());
		Page<Payment> pageOfItem = response.getBody();
		logger.info("ShopNowUser getUnPaidOrdersDatausingExchange() response{}", pageOfItem);
		
		System.out.println("page Size " + pageOfItem.getSize());
		System.out.println(pageOfItem.getContent());
		List<Payment> orderList = pageOfItem.getContent();
		System.out.println("Paid Orders List Data after Adding " + orderList);

	}

	/**
	 * Displaying Searched Items based on user's choice
	 */
	@GetMapping(value = "/searchItem")
	public void getSearchedItemDatausingExchange() {

		String targetUrl = UriComponentsBuilder.fromUriString("http://localhost:9092").path("/User").path("/search")
				.path("/{searchItem}").build().toString();
		ParameterizedTypeReference<RestResponsePage<Item>> responsePage = new ParameterizedTypeReference<RestResponsePage<Item>>() {
		};
		Map<String, String> params = new HashMap<String, String>();
		params.put("searchItem", "apple");

		ResponseEntity<RestResponsePage<Item>> response = restTemplate.exchange(targetUrl, HttpMethod.GET, null,
				responsePage, params);
		if (Objects.isNull(response)) {
			logger.error("error found in getSearchedItemDatausingExchange");
			throw new ItemNotfoundException("Incorrect Details Entered or No Data to Display Searched Item!");
		}
		System.out.println(response.getBody());
		Page<Item> pageOfItem = response.getBody();
		logger.info("ShopNowUser getUserData() getSearchedItemDatausingExchange{}", pageOfItem);
		
		System.out.println("page Size " + pageOfItem.getSize());
		System.out.println(pageOfItem.getContent());
		List<Item> searchList = pageOfItem.getContent();
		System.out.println("searched Item List Data " + searchList);

	}
}
