package org.example.springdata;

import co.elastic.clients.elasticsearch._types.query_dsl.*;
import jakarta.annotation.Resource;
import org.example.springdata.es.dao.Product;
import org.example.springdata.es.dao.ProductDao;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilterBuilder;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.domain.Sort.by;

public class TestEsSearchDAO extends BaseTest{

    @Resource
    private ElasticsearchOperations elasticsearchOperations;


    @Resource
    private ProductDao productDao;

    @Test
    public void findById(){
        Optional<Product> byId = productDao.findById(1233L);
        Product product = byId.get();
        System.out.println("findById:"+product);
    }


    @Test
    public void findAll(){
        Iterable<Product> all = productDao.findAll();
        for (Product product : all) {
            System.out.println("findAll:"+product);
        }
    }

    //分頁查詢
    @Test
    public void pageRequest(){
        Sort sort = by(Sort.Direction.ASC, "id");
        int from = 0;//當前頁
        int size = 2;//每頁多少條
        PageRequest pageRequest = PageRequest.of(from,size,sort);
        Iterable<Product> all = productDao.findAll(pageRequest);
        for (Product product : all) {
            System.out.println("pageRequest:"+product);
        }
    }

    //條件查詢 在DAO創建 Query
    @Test
    public void findByTitleQuery(){
        List<Product> byTitle = productDao.findByTitle("產品");
        for (Product product : byTitle) {
            System.out.println(product);
        }
    }

    //條件查詢 使用 QueryBuilders
    @Test
    public void searchByTitle() {
        Sort sort = by(Sort.Direction.ASC, "id");
        int from = 0;//當前頁
        int size = 1;//每頁多少條
        PageRequest pageRequest = PageRequest.of(from,size,sort);
        NativeQuery query = NativeQuery.builder()
                .withSourceFilter(new FetchSourceFilterBuilder().withIncludes().build())
//                .withQuery(QueryBuilders.match(m -> m.field("title").query("產品")))
                .withQuery(QueryBuilders.match().field("title").query("產品").build()._toQuery())
                .withPageable(pageRequest)
                .build();
        SearchHits<Product> search = elasticsearchOperations.search(query, Product.class);
        search.forEach(hit -> {
            System.out.println(hit.getContent());
        });

    }





}
