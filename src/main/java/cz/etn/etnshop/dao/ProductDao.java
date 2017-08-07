package cz.etn.etnshop.dao;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductDao {

	@Transactional
	Product findById(int id);

	@Transactional(readOnly = true)
	List<Product> getProducts();

	@Transactional
	int save(Product product);

}
