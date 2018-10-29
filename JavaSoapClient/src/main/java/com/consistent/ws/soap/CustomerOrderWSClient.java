package com.consistent.ws.soap;

import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.example.CreateOrdersRequest;
import org.example.CreateOrdersResponse;
import org.example.CustomerOrdersPortType;
import org.example.GetOrdersRequest;
import org.example.GetOrdersResponse;
import org.example.Order;
import org.example.Product;

import com.ctc.wstx.util.BijectiveNsMap;

public class CustomerOrderWSClient {
	List<Order> orders = new ArrayList<>();
	static Order order = new Order();
	static BigInteger customerId;
	public CustomerOrderWSClient() {
		init();
	}
	
	public void init() {

		order.setId(BigInteger.valueOf(1));
		
		Product product = new Product();
		product.setId("1");
		product.setDescription("Iphone");
		product.setQuantity(BigInteger.valueOf(3));
		order.getProduct().add(product);
		orders.add(order);
		
	}
	
	
	public static void main(String[] args) throws MalformedURLException {

		CustomerOrdersWSImplService service = new CustomerOrdersWSImplService(new URL("http://localhost:8080/wsdlfirstws/customerorderservice?wsdl"));
		CustomerOrdersPortType customerOrdersWSImplPort = service.getCustomerOrdersWSImplPort();
		
		GetOrdersRequest request = new GetOrdersRequest();
		request.setCustomerId(BigInteger.valueOf(1));
		GetOrdersResponse response = customerOrdersWSImplPort.getOrders(request);
		
		List<Order> orders = response.getOrder();
		 System.out.println("Numero de ordenes que hay en l proveedor son: "+orders.size());
		 
		 CreateOrdersRequest request2 = new CreateOrdersRequest();
		 
		 customerId = request.getCustomerId();//Obtener el id del consumidor
			order = request2.getOrder();// obtener la orden
			
			
			CreateOrdersResponse response2 = new CreateOrdersResponse(); //Crear una instancia de la respuesta
			response2.setResult(true);//enviar el valor
					
			System.out.println("The response after CreateOrder is "+response2.isResult());
			System.out.println("The size is: "+orders.size());
			
	}
	
}
