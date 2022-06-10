package br.com.foursys.fourcamp.fourstore.interfaces;

import java.util.HashMap;

import br.com.foursys.fourcamp.fourstore.model.Product;

public interface DataInterface {
	
	public void save(Object object);
	public void delete(Object object);
	public void update(Object object, Boolean alternative);
	public HashMap<Product, Integer> listAll();

}
