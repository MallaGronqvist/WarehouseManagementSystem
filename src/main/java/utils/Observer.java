package utils;

import product.Product;

import java.util.List;

public interface Observer {
    void update(List<Product> allProducts);
}
