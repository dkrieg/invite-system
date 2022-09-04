package com.invite;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.netflix.appinfo.ApplicationInfoManager;
import io.swagger.v3.core.util.Json;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.cloud.gateway.filter.factory.rewrite.RewriteFunction;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

import java.io.UncheckedIOException;
import java.util.List;

import static org.springdoc.core.Constants.DEFAULT_API_DOCS_URL;

@Configuration
class GatewayOpenApiRouteLocatorConfiguration {
    static final String ALL_API_DOCS_PATTERN = DEFAULT_API_DOCS_URL + "/**";
    static final String ALL_API_DOCS_REGEX = DEFAULT_API_DOCS_URL + "/(?<path>.*)";
    static final String ALL_API_DOCS_REPLACEMENT = "/$\\{path}" + DEFAULT_API_DOCS_URL;


    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder, ApplicationInfoManager info) {
        String url = info.getInfo().getHomePageUrl();
        return builder.routes()
                .route("openapi", r -> r.path(ALL_API_DOCS_PATTERN)
                        .filters(f -> f
                                .rewritePath(ALL_API_DOCS_REGEX, ALL_API_DOCS_REPLACEMENT)
                                .modifyResponseBody(String.class, String.class, modifyOpenAPI(url)))
                        .uri(url))
                .build();
    }

    RewriteFunction<String, String> modifyOpenAPI(String url) {
        return (exchange, json) -> {
            try {
                String path = exchange.getRequest().getPath().value().replace(DEFAULT_API_DOCS_URL, "");
                OpenAPI openAPI = Json.mapper().readValue(json, OpenAPI.class);
                openAPI.servers(List.of(new Server().url(url)));
                Paths paths = new Paths();
                openAPI.getPaths().forEach((key, value) -> paths.addPathItem(path + key, value));
                openAPI.setPaths(paths);
                return Mono.just(Json.mapper().writeValueAsString(openAPI));
            } catch (JsonProcessingException ex) {
                throw new UncheckedIOException(ex);
            }
        };
    }
}