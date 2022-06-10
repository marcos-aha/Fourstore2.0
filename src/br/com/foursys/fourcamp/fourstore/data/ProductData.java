package br.com.foursys.fourcamp.fourstore.data;

import java.util.HashMap;

import br.com.foursys.fourcamp.fourstore.interfaces.DataInterface;
import br.com.foursys.fourcamp.fourstore.model.Product;
import br.com.foursys.fourcamp.fourstore.model.Stock;

public class ProductData implements DataInterface  {
	
	private static HashMap<Product, Integer> listProduct = new HashMap<>();

	@Override
	public void save(Object object) {
		Stock stock = (Stock)object;
		listProduct.put(stock.getProduct(), stock.getQuantity());	
	}

	@Override
	public void delete(Object object) {
		Product product = (Product) object;
		listProduct.remove(product);
	}

	public void reduceProductStock(Product product, int quantity) {
		listProduct.replace(product, quantity);
	}
	
	@Override
	public void update(Object object, Boolean alternative) {
		Stock stock = (Stock) object;
		if (alternative) {
		listProduct.put(stock.getProduct(), stock.getQuantity());
		} else {
			for(HashMap.Entry<Product, Integer> product : listProduct.entrySet()) {
				if(stock.getProduct().getSku().equals(product.getKey().getSku())){
					product.getKey().setBuyPrice(stock.getProduct().getBuyPrice());
					product.getKey().setSellPrice(stock.getProduct().getSellPrice());
					product.getKey().setDescription(stock.getProduct().getDescription());
					product.setValue(stock.getQuantity());
				}
			}
		}
	}

	@Override
	public HashMap<Product, Integer> listAll() {
		return listProduct;
	}
	
	
}