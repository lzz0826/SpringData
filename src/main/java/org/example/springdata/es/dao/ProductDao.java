package org.example.springdata.es.dao;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductDao extends ElasticsearchRepository<Product,Long> {


    // `?0` 是參數佔位符，對應方法中的第一個參數。
    @Query("{\"match\": {\"title\": {\"query\": \"?0\"}}}")
    List<Product> findByTitle(String keyword);

    @Query("{\"bool\": {\"must\": [" +
            "{\"match\": {\"title\": {\"query\": \"?0\"}}}," +
            "{\"match\": {\"category\": {\"query\": \"?1\"}}}" +
            "]}}")
    List<Product> findByTitleAndCategory(String titleKeyword, String categoryKeyword);

}



