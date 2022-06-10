package br.com.foursys.fourcamp.fourstore.data;

import java.util.HashMap;


import br.com.foursys.fourcamp.fourstore.interfaces.TransactionInterface;
import br.com.foursys.fourcamp.fourstore.model.Customer;

public class TransactionData implements TransactionInterface {

	private static HashMap<Integer, Customer> purchaseHistory = new HashMap<>();

	@Override
	public void save(int value, Object object) {
		Customer customer = (Customer) object;
		purchaseHistory.put(value, customer);	
	}

	@Override
	public void delete(Object object) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Object object, Boolean alternative) {
		// TODO Auto-generated method stub

	}

	@Override
	public HashMap<Integer, Customer> listAll() {
		return purchaseHistory;
	}

}
