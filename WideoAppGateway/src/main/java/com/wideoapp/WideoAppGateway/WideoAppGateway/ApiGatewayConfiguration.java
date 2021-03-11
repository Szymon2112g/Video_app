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
                /**
                 *
                 *  WideoAppDatabase
                 *
                 */
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
                /**
                 *
                 * WideoAppSecurity
                 *
                 */
                .route(p -> p.path("/dislike/add/video/**")
                        .uri("lb://wideoappsecurity")
                )
                .route(p -> p.path("/dislike/video/**")
                        .uri("lb://wideoappsecurity")
                )
                .route(p -> p.path("/dislike/subtract/video/**")
                        .uri("lb://wideoappsecurity")
                )
                .route(p -> p.path("/history/video/**")
                        .uri("lb://wideoappsecurity")
                )
                .route(p -> p.path("/like/add/video/**")
                        .uri("lb://wideoappsecurity")
                )
                .route(p -> p.path("/like/video/**")
                        .uri("lb://wideoappsecurity")
                )
                .route(p -> p.path("/like/subtract/video/**")
                        .uri("lb://wideoappsecurity")
                )
                .route(p -> p.path("/review/add/**")
                        .uri("lb://wideoappsecurity")
                )
                .route(p -> p.path("/subscription/get/**")
                        .uri("lb://wideoappsecurity")
                )
                .route(p -> p.path("/subscription/add/user/**")
                        .uri("lb://wideoappsecurity")
                )
                .route(p -> p.path("/subscription/subtract/user/**")
                        .uri("lb://wideoappsecurity")
                )
                .route(p -> p.path("/video/file/add/db/**")
                        .uri("lb://wideoappsecurity")
                )
                .route(p -> p.path("/video/feed/history/**")
                        .uri("lb://wideoappsecurity")
                )
                .route(p -> p.path("/video/feed/liked/**")
                        .uri("lb://wideoappsecurity")
                )
                .route(p -> p.path("/video/feed/subscription/**")
                        .uri("lb://wideoappsecurity")
                )
                .route(p -> p.path("/video/send-file/**")
                        .uri("lb://wideoappsecurity")
                )

                .build();
    }

}

