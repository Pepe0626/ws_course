package com.consistent.ws.soap;

import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.example.CustomerOrdersPortType;
import org.example.GetOrdersRequest;
import org.example.GetOrdersResponse;
import org.example.Order;

public class CustomerOrderWSClient {

	public static void main(String[] args) throws MalformedURLException {

		CustomerOrdersWSImplService service = new CustomerOrdersWSImplService(new URL("http://localhost:8080/wsdlfirstws/customerorderservice?wsdl"));
		CustomerOrdersPortType customerOrdersWSImplPort = service.getCustomerOrdersWSImplPort();
		
		GetOrdersRequest request = new GetOrdersRequest();
		request.setCustomerId(BigInteger.valueOf(1));
		GetOrdersResponse response = customerOrdersWSImplPort.getOrders(request);
		
		List<Order> orders = response.getOrder();
		 System.out.println("Numero de ordenes que hay en l proveedor son: "+orders.size());
	}

}
