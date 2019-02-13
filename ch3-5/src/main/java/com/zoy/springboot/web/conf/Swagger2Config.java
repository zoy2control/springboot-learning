package com.zoy.springboot.web.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by zouzp on 2019/2/11.
 */
@Configuration // ·通过 @Configuration注解，让 Spring来加载该类配置
@EnableSwagger2 // ·通过 @EnableSwqgger2启动 Swagger2
public class Swagger2Config {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select() // ·返回 ApiSelectorBuilder对象来控制哪些接口暴露给 Swagger来展现
                .apis(RequestHandlerSelectors.basePackage("com.zoy.springboot"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     *   ·创建 基本api信息，会展现在文档页面中
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("zoy的Swagger2")
                .description("==========hello swagger2==========")
                .termsOfServiceUrl("https://github.com/zoy2control")
                .contact("zoy2control")
                .build();
    }
}
