package cz.etn.etnshop.model;

import cz.etn.etnshop.dao.Product;

public class ProductModel {

    private int id;
    private String name;
    private String serialNumber;

    public ProductModel() { }

    public ProductModel(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.serialNumber = product.getSerialNumber();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
        return id == 0;
    }
}
