package com.wideoapp.WideoAppGateway.WideoAppGateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p.path("/reviews/**")
                        .uri("lb://wideoappdatabase")
                )
                .route(p -> p.path("/register/**")
                        .uri("lb://wideoappdatabase")
                )
                .route(p -> p.path("/user/username/**")
                        .uri("lb://wideoappdatabase")
                )
                .route(p -> p.path("/videos/category/latest")
                        .uri("lb://wideoappdatabase")
                )
                .route(p -> p.path("/videos/category/most-display")
                        .uri("lb://wideoappdatabase")
                )
                .route(p -> p.path("/videos/category/most-likes")
                        .uri("lb://wideoappdatabase")
                )
                .route(p -> p.path("/videos/category/most-dislikes")
                        .uri("lb://wideoappdatabase")
                )
                .route(p -> p.path("/video/watch/**")
                        .uri("lb://wideoappdatabase")
                )
                .route(p -> p.path("/videos/ontime")
                        .uri("lb://wideoappdatabase")
                )
                .route(p -> p.path("/search/video/tips/**")
                        .uri("lb://wideoappdatabase")
                )
                .route(p -> p.path("/search/video/key/**")
                        .uri("lb://wideoappdatabase")
                )
                .route(p -> p.path("/user/videos/**")
                        .uri("lb://wideoappdatabase")
                )
                .build();
    }



}

