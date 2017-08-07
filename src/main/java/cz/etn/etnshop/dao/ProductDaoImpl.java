package cz.etn.etnshop.dao;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("productDao")
public class ProductDaoImpl extends AbstractDao implements ProductDao {

	@SuppressWarnings("unchecked")
	public List<Product> getProducts() {
		Criteria criteria = getSession().createCriteria(Product.class);
		return (List<Product>) criteria.list();
	}

	@Override
	public int save(Product product) {
		getSession().saveOrUpdate(product);
		return product.getId();
	}

}
