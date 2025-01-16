package org.example.springdata;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.BulkRequest;
import co.elastic.clients.elasticsearch.core.BulkResponse;
import co.elastic.clients.elasticsearch.core.CreateRequest;
import co.elastic.clients.elasticsearch.core.CreateResponse;
import co.elastic.clients.elasticsearch.core.bulk.BulkOperation;
import co.elastic.clients.elasticsearch.core.bulk.CreateOperation;
import jakarta.annotation.Resource;
import org.example.springdata.es.dao.Product;
import org.example.springdata.es.dao.ProductDao;
import org.example.springdata.es.obj.User;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestEsSave extends BaseTest{


    @Resource
    private ElasticsearchClient elasticsearchClient;


    //添加
    @Test
    public void save() throws IOException {
        String index = "test333";
        User user = new User();
        user.setId(2);
        user.setName("u");
        user.setAge(22);

        CreateRequest<User> build = new CreateRequest.Builder<User>()
                .index(index).id("1001")
                .document(user)
                .build();
        CreateResponse createResponse = elasticsearchClient.create(build);
        System.out.println(createResponse.toString());

    }


    //添加 批量添加
    @Test
    public void saveAll() throws IOException {
        String index = "test333";
        List<BulkOperation> list = new ArrayList<BulkOperation>();
        for(int i = 0 ; i < 5 ; i++){
            User user = new User();
            user.setId(i);
            user.setName("u");
            user.setAge(22+i);
            CreateOperation<User> createOperation = new CreateOperation.
                    Builder<User>().
                    index(index).
                    id(String.valueOf(i)).
                    document(user).
                    build();
            BulkOperation operation = new BulkOperation.
                    Builder().
                    create(createOperation).
                    build();
            list.add(operation);
        }
        BulkRequest bulkRequest = new BulkRequest.Builder().
                operations(list)
                .build();
        BulkResponse bulk = elasticsearchClient.bulk(bulkRequest);
        System.out.println(bulk.toString());

    }



    //添加 批量添加 隐式函数
    @Test
    public void saveAll2() throws IOException {
        String index = "test333";

        List<User> users = List.of(
                new User(1, "Alice", 25),
                new User(2, "Bob", 30)
        );
        // 批量插入文档
        BulkResponse bulkResponse = elasticsearchClient.bulk(
                req -> {
                    users.forEach(user ->
                            req.operations(op -> op.create(create -> create
                                    .index(index)
                                    .id(String.valueOf(300 + user.getId()))
                                    .document(user))
                            )
                    );
                    return req;
                }
        );
        System.out.println(bulkResponse);
    }




}
