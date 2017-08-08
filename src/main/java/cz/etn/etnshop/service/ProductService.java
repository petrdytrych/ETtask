package cz.etn.etnshop.service;

import cz.etn.etnshop.model.ProductModel;
import cz.etn.etnshop.model.SearchModel;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductService {

	@Transactional(readOnly = true)
	ProductModel findById(int id);

	@Transactional(readOnly = true)
	List<ProductModel> findByFulltext(SearchModel search);

	@Transactional(readOnly = true)
	List<ProductModel> getProducts();

	@Transactional
	int save(ProductModel productModel);
}
