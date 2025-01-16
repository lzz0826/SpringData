package org.example.springdata.es.conf;

import co.elastic.clients.elasticsearch.ElasticsearchAsyncClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;

@Configuration
public class NewRestClientConfig extends ElasticsearchConfiguration {

    @Value("${es.host}")
    private String host;

    @Value("${es.port}")
    private String port;

    @Value("${es.username}")
    private String username;

    @Value("${es.password}")
    private String password;

    @Override
    public ClientConfiguration clientConfiguration() {
        // 拼接主機和端口
        String elasticsearchAddress = host + ":" + port;
        return ClientConfiguration.builder()
                .connectedTo(elasticsearchAddress) // 配置連接地址
                .withBasicAuth(username, password) // 配置認證信息
                .build();
    }


    //创建异步连线
    @Bean
    public ElasticsearchAsyncClient elasticsearchAsyncClient() {
        RestClientBuilder builder = RestClient.builder(
                new HttpHost(host, Integer.parseInt(port), "http") // 根据你的实际配置调整
        );

        RestClient restClient = builder.build();
        RestClientTransport transport = new RestClientTransport(
                restClient,
                new JacksonJsonpMapper() // JSON Mapper
        );
        return new ElasticsearchAsyncClient(transport);
    }
}

