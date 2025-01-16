package org.example.springdata;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.indices.*;
import jakarta.annotation.Resource;
import org.example.springdata.es.dao.Product;
import org.example.springdata.es.dao.ProductDao;
import org.junit.jupiter.api.Test;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.IndexOperations;

import java.io.IOException;



public class TestEsIndexDAO extends BaseTest{

    @Resource
    private ElasticsearchOperations elasticsearchOperations;



    //依照物件產索引 @Document(indexName = "product")
    @Test
    public void createIndex(){
        System.out.println("創建索引");
    }


    //依照物刪除索引
    @Test
    public void deleteIndex() {
        System.out.println("刪除索引");
        IndexOperations indexOps = elasticsearchOperations.indexOps(Product.class);
        if (indexOps.exists()) {
            boolean deleted = indexOps.delete();
            System.out.println("索引刪除成功: " + deleted);
        } else {
            System.out.println("索引不存在");
        }
    }



}
