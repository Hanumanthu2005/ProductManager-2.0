package org.example;

import java.util.List;

public class Main
{
    public static void main(String[] args)
    {

        ProductService service = new ProductService();


//        service.addProduct(new Product("macbook", "laptop", "bag", 2026));
//        service.addProduct(new Product("lenovo thinkpad", "laptop", "table", 2022));
//        service.addProduct(new Product("lenovo laptop", "laptop", "shelf", 2018));
//        service.addProduct(new Product("zebronics mouse", "mouse", "table", 2022));
//        service.addProduct(new Product("lapcare keyboard", "keyboard", "shelf", 2024));
//        service.addProduct(new Product("macbook charger", "charger", "lenovo table", 2026));
//        service.addProduct(new Product("ear phones", "TWS", "bag", 2025));

        List<Product> products = service.getAllProducts();

        System.out.println("gets all products");

        products.forEach(System.out::println);

        System.out.println("=====================================================================");

        System.out.println("get product by name");

        System.out.println(service.getProduct("macbook charger"));

        System.out.println("=====================================================================");

        System.out.println("get product by place");

        List<Product> placeProducts = service.getProductsByPlace("table");

        placeProducts.forEach(System.out::println);

        System.out.println("=====================================================================");

        System.out.println("product not covers the warrenty");

        List<Product> notWarrenty = service.getProductsNotUnderWarrenty(2025);

        notWarrenty.forEach(System.out::println);

        System.out.println("=====================================================================");

        System.out.println("all products contains word");

        List<Product> containsWord = service.getProductsContainsWord("lenovo");

        containsWord.forEach(System.out::println);

    }
}