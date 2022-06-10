package br.com.foursys.fourcamp.fourstore.interfaces;

import java.util.HashMap;

import br.com.foursys.fourcamp.fourstore.model.Customer;


public interface TransactionInterface {
	public void save(int value, Object object);
	public void delete(Object object);
	public void update(Object object, Boolean alternative);
	public HashMap<Integer, Customer> listAll();
}
