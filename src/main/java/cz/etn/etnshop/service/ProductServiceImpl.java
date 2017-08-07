package cz.etn.etnshop.service;

import cz.etn.etnshop.dao.Product;
import cz.etn.etnshop.dao.ProductDao;
import cz.etn.etnshop.model.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service("productService")
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;

	@Override
	public List<ProductModel> getProducts() {
		List<Product> products = productDao.getProducts();
		if (products == null || products.isEmpty()) {
			return Collections.emptyList();
		} else {
			return getProductsMappedToModel(products);
		}
	}

	private List<ProductModel> getProductsMappedToModel(List<Product> products) {
		return products.stream().map(ProductModel::new).collect(Collectors.toList());
	}

}
