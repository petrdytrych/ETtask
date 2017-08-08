package cz.etn.etnshop.dao;

import org.hibernate.Criteria;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository("productDao")
public class ProductDaoImpl extends AbstractDao implements ProductDao {

	@Override
	public Product findById(int id) {
		return (Product) getSession().get(Product.class, id);
	}

	@Override
	public List<Product> findByFulltext(String text) {
		String sql = "SELECT * FROM product WHERE MATCH (name,serial_number) AGAINST (?1 IN NATURAL LANGUAGE MODE)";
		Query query = getSession().createNativeQuery(sql, Product.class);
		query.setParameter(1, text);
		List<Product>  list = query.getResultList();
		for (Object o : list) {
			System.out.println(o);
			System.out.println(o.getClass());
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Product> getProducts() {
		Query query = getSession().createQuery("from Product");
		return (List<Product>) query.list();
	}

	@Override
	public int save(Product product) {
		getSession().saveOrUpdate(product);
		return product.getId();
	}

}
