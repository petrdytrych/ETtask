package cz.etn.etnshop.service;

import cz.etn.etnshop.model.ProductModel;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductService {

	ProductModel findById(int id);

	@Transactional(readOnly = true)
	List<ProductModel> getProducts();

	int save(ProductModel productModel);
}
