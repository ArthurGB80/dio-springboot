package dio.springbootweb;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private static final String CONTACT_NAME = "Your Name";
    private static final String CONTACT_URL = "http://www.yourwebsite.com";
    private static final String CONTACT_EMAIL = "you@yourwebsite.com";
    private static final String API_TITLE = "Your API Title";
    private static final String API_DESCRIPTION = "Description of your API";
    private static final String API_VERSION = "1.0";
    private static final String TERMS_OF_SERVICE_URL = "Terms of Service URL";
    private static final String LICENSE_NAME = "Your License Name";
    private static final String LICENSE_URL = "http://www.yourlicenseurl.com";

    private static Contact createContactInformation() {
        return new Contact(CONTACT_NAME, CONTACT_URL, CONTACT_EMAIL);
    }

    private static ApiInfoBuilder createApiInformation() {
        return new ApiInfoBuilder()
                .title(API_TITLE)
                .description(API_DESCRIPTION)
                .version(API_VERSION)
                .termsOfServiceUrl(TERMS_OF_SERVICE_URL)
                .license(LICENSE_NAME)
                .licenseUrl(LICENSE_URL)
                .contact(createContactInformation());
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("dio.springbootweb.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(createApiInformation().build())
                .consumes(new HashSet<>(Arrays.asList("application/json")))
                .produces(new HashSet<>(Arrays.asList("application/json")));
    }
}