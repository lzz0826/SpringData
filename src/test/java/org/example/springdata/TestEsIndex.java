package org.example.springdata;

import co.elastic.clients.elasticsearch.ElasticsearchAsyncClient;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.indices.CreateIndexRequest;
import co.elastic.clients.elasticsearch.indices.DeleteIndexResponse;
import co.elastic.clients.elasticsearch.indices.ElasticsearchIndicesClient;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TestEsIndex extends BaseTest {


    //同步客戶端
    @Resource
    private ElasticsearchClient elasticsearchClient;


    //異步客戶端
    @Resource
    private ElasticsearchAsyncClient elasticsearchAsyncClient;



    @Test
    public void createIndexAsyncClient() throws IOException, InterruptedException {
        elasticsearchAsyncClient.indices().create(req ->{
            req.index("test");
            return req;
        })
//       .thenAccept(rept -> rept.acknowledged()) //中间可以在处理一些事再把 rept 返回给 whenComplete 
            .whenComplete((rep,err) ->{
            System.out.println("收到回調");
            if(rep != null){
                System.out.println(rep);
            }else {
                System.out.println(err.getMessage());
            }
        });

        System.out.println("主線程....");
        Thread.sleep(1000);
    }


    //使用 ElasticsearchClient創建索引
    @Test
    public void createIndexElasticsearchClient() throws IOException {
        String indexName = "ttoonnyc222v";
        ElasticsearchIndicesClient indices = elasticsearchClient.indices();

//        ExistsRequest build1 = new ExistsRequest.Builder().index(indexName).build();

        boolean value = indices.exists(m -> m.index(indexName)).value();

        if (value) {
            System.out.println("索引已在");
        }else {
            CreateIndexRequest build = new CreateIndexRequest.Builder()
                    .index(indexName)
                    .build();

            indices.create(build);
        }

        System.out.println(value);
    }

    //使用 ElasticsearchClient刪除索引
    @Test
    public void deleteElasticsearchClient() throws IOException {
        String indexName = "ttoonnyc222v";
        ElasticsearchIndicesClient indices = elasticsearchClient.indices();

        DeleteIndexResponse delete = indices.delete(d -> d.index(indexName));

        boolean acknowledged = delete.acknowledged();

        System.out.println("索引刪除:" + acknowledged);

    }
}
