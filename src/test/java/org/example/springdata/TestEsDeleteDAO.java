package org.example.springdata;

import jakarta.annotation.Resource;
import org.example.springdata.es.dao.ProductDao;
import org.junit.jupiter.api.Test;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

public class TestEsDeleteDAO {


    @Resource
    private ProductDao productDao;

    @Test
    public void deleteById(){
        productDao.deleteById(1233L);
        System.out.println("刪除依照ID");

    }


}
