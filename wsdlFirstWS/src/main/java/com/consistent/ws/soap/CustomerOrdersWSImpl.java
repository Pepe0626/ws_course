package com.consistent.ws.soap;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.cxf.feature.Features;
import org.example.CreateOrdersRequest;
import org.example.CreateOrdersResponse;
import org.example.CustomerOrdersPortType;
import org.example.DelOrderRequest;
import org.example.DelOrderResponse;
import org.example.GetOrdersRequest;
import org.example.GetOrdersResponse;
import org.example.Order;
import org.example.Product;

import com.ctc.wstx.util.BijectiveNsMap;

@Features(features="org.apache.cxf.feature.LoggingFeature")
public class CustomerOrdersWSImpl implements CustomerOrdersPortType {
	
	Map<BigInteger, List <Order>> customerOrders = new HashMap<>();
	int currentId;
	
	public CustomerOrdersWSImpl() {
		init();
	}
	
	public void init() {
		List<Order> orders = new ArrayList<>();
		Order order = new Order();
		order.setId(BigInteger.valueOf(1));
		
		Product product = new Product();
		product.setId("1");
		product.setDescription("Iphone");
		product.setQuantity(BigInteger.valueOf(3));
		order.getProduct().add(product);
		orders.add(order);
		
		customerOrders.put(BigInteger.valueOf(++currentId), orders);
	}
	
	@Override
	public GetOrdersResponse getOrders(GetOrdersRequest request) {
		
		BigInteger customerId = request.getCustomerId(); //Obtener el Id cel consumidor
		List<Order> orders = customerOrders.get(customerId); //lista de pedidos del cliente con id
		
		GetOrdersResponse response = new GetOrdersResponse();
		response .getOrder().addAll(orders);
		
		return response;
	}

	@Override
	public CreateOrdersResponse createOrders(CreateOrdersRequest request) {
		
		BigInteger customerId = request.getCustomerId();//Obtener el id del consumidor
		Order order = request.getOrder();// obtener la orden
		
		List<Order> orders = customerOrders.get(customerId);// agregar las ordenes por ID
		orders.add(order);//agregar las ordenes a la lista
		
		CreateOrdersResponse response = new CreateOrdersResponse(); //Crear una instancia de la respuesta
		response.setResult(true);//enviar el valor
				
		return response;
	}

	@Override
	public DelOrderResponse delOrders(DelOrderRequest request) {
		
		BigInteger customerId = request.getCustomerId();
		List<Order> orders = customerOrders.get(customerId);
		
		DelOrderResponse response = new DelOrderResponse();
		
		for(int i=0;i<=orders.size()-1; i++) {
			orders.remove(orders.get(i));
		}
		response.setResult(true);
		return response;
	}
	
	//delete (id)

}
