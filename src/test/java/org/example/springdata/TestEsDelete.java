package org.example.springdata;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.CreateRequest;
import co.elastic.clients.elasticsearch.core.CreateResponse;
import co.elastic.clients.elasticsearch.core.DeleteResponse;
import jakarta.annotation.Resource;
import org.example.springdata.es.dao.ProductDao;
import org.example.springdata.es.obj.User;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TestEsDelete extends BaseTest {


    @Resource
    private ElasticsearchClient elasticsearchClient;

    @Test
    public void deleteById() throws IOException {

        String index = "test333";

        DeleteResponse delete = elasticsearchClient.delete(d -> d.index(index).id("1011"));


        System.out.println(delete.toString());
    }


}
