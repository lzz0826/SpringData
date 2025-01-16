package org.example.springdata;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import jakarta.annotation.Resource;
import org.example.springdata.es.obj.User;
import org.junit.jupiter.api.Test;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Query;

import java.io.IOException;

public class TestEsSearch extends BaseTest{


    @Resource
    private ElasticsearchClient elasticsearchClient;


    @Test
    public void findSearch() throws IOException {

        SearchResponse<User> response = elasticsearchClient.
                search(s -> s.query(
                        q -> q.match(
                                m -> m.field("age").query(25))),
                        User.class);
        System.out.println(response);
    }





}
