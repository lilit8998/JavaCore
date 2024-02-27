package onlineShop.storage;

import onlineShop.models.Order;
import onlineShop.models.Product;
import onlineShop.util.StorageSerializeUtil;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ProductStorage {

    private Set<Product> products = new HashSet<>();

    public void addProduct(Product product) {
       products.add(product);
        StorageSerializeUtil.serializeProduct(this);
    }

    public void printProducts(){
        for (Product product : products) {
            System.out.println(product);
        }
    }
public void delete(String productID){
    Iterator<Product> iterator = products.iterator();
    while (iterator.hasNext()){
        Product next = iterator.next();
        if (next.getId().equals(productID));
    }
}
    public Product getById(String id){
        for(Product product : products){
            if (product.getId().equals(id)){
                return product;
            }
        }
        return null;
    }
}
