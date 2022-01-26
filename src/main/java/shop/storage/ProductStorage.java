package shop.storage;

import shop.data.DataClass;
import shop.model.Product;

import java.util.LinkedList;
import java.util.List;

public class ProductStorage {
    private List<Product> productList = new LinkedList<>();

    public void add(Product product) {
        productList.add(product);
        serial();
    }


    public void print() {
        for (Product product : productList) {
            System.out.println(product);
        }

    }

    public Product getProductByName(String buy) {
        for (Product product : productList) {
            if (product.getName().equalsIgnoreCase(buy)) {
                return product;
            }
        }
        return null;
    }

    public void upDateStorage() {
        for (Product product : productList) {
            if (product.getCount() == 0) {
                productList.remove(product);
                System.out.println("Shop updated!");
                serial();
            }
        }

    }

    public void serial(){
        DataClass.serializeProduct(productList);
    }

    public void initData(){
        List<Product> list=DataClass.deSerializeProducts();
        if (list!=null){
            productList=list;
        }
    }
}

