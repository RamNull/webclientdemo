package com.example.webclientdemo.externalconnector;

import java.time.Duration;
import java.util.Map;

import org.springframework.context.annotation.DependsOn;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;
import org.springframework.http.*;

import com.fasterxml.jackson.databind.JsonNode;

import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;
import reactor.netty.transport.ProxyProvider.Proxy;

@Component
@DependsOn("initializer")
public class WebClientConnector {

    private static final long RESPONSE_TIME = 30;
    private static final int POOL_MAX_CONNECTIONS = 500;

    private static final ConnectionProvider CONNECTION_PROVIDER = ConnectionProvider.create("connection-provider", POOL_MAX_CONNECTIONS, false);
    private static final HttpClient HTTP_CLIENT = !GlobalInitalizerProperties.isProxy() ? HttpClient.create(CONNECTION_PROVIDER)
            : HttpClient.create(CONNECTION_PROVIDER).proxy(proxy -> proxy.type(Proxy.HTTP).host(GlobalInitalizerProperties.getProxyURL())
                    .port(GlobalInitalizerProperties.getport()));

    private static final ReactorClientHttpConnector CONNECTOR = new ReactorClientHttpConnector(HTTP_CLIENT.responseTimeout(Duration.ofSeconds(RESPONSE_TIME)));

    private static final WebClient WEB_CLIENT = WebClient.builder().defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).
                                                clientConnector(CONNECTOR).build();

    public ResponseEntity<String> getRequest(Map<String, ?> headers, String url) {
        RequestHeadersSpec<?> requestHeadersSpec = null;
        if (headers == null || headers.isEmpty())
            requestHeadersSpec = WEB_CLIENT.get().uri(url);
        else
            requestHeadersSpec = WEB_CLIENT.get().uri(url, headers);
        return requestHeadersSpec.retrieve().toEntity(String.class).block();
    }


  
    public JsonNode postRequest( String url,Map<String, ?> headers, JsonNode body)
    {

        RequestHeadersSpec<?> requestHeadersSpec = null;
        if (headers == null || headers.isEmpty())
            requestHeadersSpec = WEB_CLIENT.post().uri(url).body(Mono.just(body), JsonNode.class);
        else
            requestHeadersSpec = WEB_CLIENT.post().uri(url, headers).body(Mono.just(body), JsonNode.class);
        
        return requestHeadersSpec.retrieve().bodyToMono(JsonNode.class).block();
    }
}