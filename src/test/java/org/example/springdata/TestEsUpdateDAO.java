package org.example.springdata;

import jakarta.annotation.Resource;
import org.example.springdata.es.dao.Product;
import org.example.springdata.es.dao.ProductDao;
import org.junit.jupiter.api.Test;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

public class TestEsUpdateDAO extends BaseTest{

    @Resource
    private ElasticsearchOperations elasticsearchOperations;


    @Resource
    private ProductDao productDao;

    @Test
    //id 相同會是修改
    public void update() {
        Product product = new Product();
        product.setId(1233L);
        product.setTitle("產品ee");
        product.setCategory("類類");
        product.setPrice(123.123);
        product.setImages("https://github.com/lzz0826/GO-AdminPro/blob/main/img/005.png");
        productDao.save(product);
        System.out.println("創建產品數據");
    }


}
