package org.example;

import java.security.spec.ECField;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {

    Connection con = null;

    public ProductDao() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/productdb", "root", "Hanu@123");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(Product product) {

        String query = "insert into product (name, type, place, warrenty) values (?,?,?,?)";

        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, product.getName());
            statement.setString(2, product.getType());
            statement.setString(3, product.getPlace());
            statement.setInt(4, product.getWarrenty());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public List<Product> getAll() {

        List<Product> products = new ArrayList<>();
        String query = "select id, name, type, place, warrenty from product";
        try {
            PreparedStatement statement = con.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while(rs.next())
            {
                products.add(toProduct(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return products;

    }

    public Product getByName(String name) {

        Product product = new Product();
        String query = "select id, name, type, place, warrenty from product where name = ?";
        ResultSet rs;
        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, name);
            rs = statement.executeQuery();
            rs.next();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return toProduct(rs);
    }

    public List<Product> getByPlace(String place) {

        List<Product> products = new ArrayList<>();
        String query = "select id, name, type, place, warrenty from product where place = ?";

        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, place);
            ResultSet rs = statement.executeQuery();
            while(rs.next())
            {

                products.add(toProduct(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return products;
    }

    public List<Product> getOutOfWarrentyProducts(int currentYear) {
        List<Product> products = new ArrayList<>();
        String query = "select id, name, type, place, warrenty from product where warrenty < ?";
        try {
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, currentYear);
            statement.execute();
            ResultSet rs = statement.executeQuery();
            while(rs.next())
            {

                products.add(toProduct(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    public List<Product> getProductsContainsWord(String word) {

        List<Product> products = new ArrayList<>();
        String query = "select id, name, type, place, warrenty from product";
        try {
            PreparedStatement statement = con.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while(rs.next())
            {
                if(rs.getString(2).contains(word) || rs.getString(3).contains(word) || rs.getString(4).contains(word))
                {
                    products.add(toProduct(rs));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;

    }

    public static Product toProduct(ResultSet rs)
    {
        Product product = new Product();
        try
        {
            product.setId(rs.getInt(1));
            product.setName(rs.getString(2));
            product.setType(rs.getString(3));
            product.setPlace(rs.getString(4));
            product.setWarrenty(rs.getInt(5));
        }
        catch (Exception e) {}
        return product;

    }
}
