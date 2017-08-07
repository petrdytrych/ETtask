package cz.etn.etnshop.service;

import cz.etn.etnshop.model.ProductModel;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductService {

	@Transactional(readOnly = true)
	List<ProductModel> getProducts();

}
