package cz.etn.etnshop.model;

import cz.etn.etnshop.dao.Product;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ProductModel {

    private Integer id;
    private String name;
    private String serialNumber;

    public ProductModel() { }

    public ProductModel(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.serialNumber = product.getSerialNumber();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public boolean isNew() {
        return id == null;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
