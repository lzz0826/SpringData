package org.example.springdata;

import jakarta.annotation.Resource;
import org.example.springdata.es.dao.Product;
import org.example.springdata.es.dao.ProductDao;
import org.junit.jupiter.api.Test;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

import java.util.ArrayList;
import java.util.List;

public class TestEsSaveDAO extends BaseTest{


    @Resource
    private ProductDao productDao;


    //添加
    @Test
    public void save() {
        Product product = new Product();
        product.setId(12343L);
        product.setTitle("哈哈");
        product.setCategory("類類");
        product.setPrice(123.123);
        product.setImages("https://github.com/lzz0826/GO-AdminPro/blob/main/img/005.png");
        productDao.save(product);
        System.out.println("創建產品數據");
    }


    //添加 批量添加
    @Test
    public void saveAll(){
        List<Product> productList = new ArrayList<>();
        Product product1 = new Product();
        product1.setId(111L);
        product1.setTitle("產品ee");
        product1.setCategory("類類");
        product1.setPrice(123.123);
        product1.setImages("https://github.com/lzz0826/GO-AdminPro/blob/main/img/005.png");

        Product product2 = new Product();
        product2.setId(222L);
        product2.setTitle("產品ee");
        product2.setCategory("類類");
        product2.setPrice(123.123);
        product2.setImages("https://github.com/lzz0826/GO-AdminPro/blob/main/img/005.png");
        productList.add(product1);
        productList.add(product2);
        Iterable<Product> products = productDao.saveAll(productList);
        System.out.println("批量添加");

    }




}
