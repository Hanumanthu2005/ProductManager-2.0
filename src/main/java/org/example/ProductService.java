package org.example;
import java.util.List;

public class ProductService {


    ProductDao dao = new ProductDao();

    public void addProduct(Product product) {
        dao.save(product);
    }

    public List<Product> getAllProducts() {
        return dao.getAll();
    }

    public Product getProduct(String name) {
         return dao.getByName(name);
    }

    public List<Product> getProductsByPlace(String place) {
        return dao.getByPlace(place);
    }

    public List<Product> getProductsNotUnderWarrenty(int currentYear) {
        return dao.getOutOfWarrentyProducts(currentYear);
    }

    public List<Product> getProductsContainsWord(String word) {
        return dao.getProductsContainsWord(word);
    }
}


