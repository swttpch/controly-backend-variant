package controly.backend.config;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Configuration
@RestController
@Hidden
public class OpenApiConfig {
  @Bean
  public OpenAPI usersMicroserviceOpenAPI() {
    return new OpenAPI()
        .info(new Info().title("Your API Title")
            .description("Your API Description")
            .version("1.0"));
  }

  @Hidden
  @RequestMapping("/docs")
  public void apiDocumentation(HttpServletResponse response) throws IOException {
    response.sendRedirect("swagger-ui/index.html");
  }
  @Hidden
  @RequestMapping("/doc")
  public void apiDocumentation2(HttpServletResponse response) throws IOException {
    response.sendRedirect("swagger-ui/index.html");
  }
}