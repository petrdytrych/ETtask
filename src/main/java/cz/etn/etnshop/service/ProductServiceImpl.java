package cz.etn.etnshop.service;

import cz.etn.etnshop.dao.Product;
import cz.etn.etnshop.dao.ProductDao;
import cz.etn.etnshop.model.ProductModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service("productService")
public class ProductServiceImpl implements ProductService {

    private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Autowired
	private ProductDao productDao;

    @Override
    public ProductModel findById(int id) {
        Product product = productDao.findById(id);
        logger.info("For ID {} found: {}", id, product);
        return product != null ? new ProductModel(product) : null;
    }

    @Override
	public List<ProductModel> getProducts() {
		List<Product> products = productDao.getProducts();
        if (products == null || products.isEmpty()) {
            return Collections.emptyList();
        } else {
            return getProductsMappedToModel(products);
        }
	}

	@Override
	public int save(ProductModel productModel) {
        Product product = getProductModelMappedToEntity(productModel);
        int id = productDao.save(product);
        logger.info("Product saved: {}", product);
        return id;
    }

	private Product getProductModelMappedToEntity(ProductModel productModel) {
		Product product = new Product();
        product.setId(productModel.getId());
		product.setName(productModel.getName());
		product.setSerialNumber(productModel.getSerialNumber());
		return product;
	}

	private List<ProductModel> getProductsMappedToModel(List<Product> products) {
		return products.stream().map(ProductModel::new).collect(Collectors.toList());
	}

}
