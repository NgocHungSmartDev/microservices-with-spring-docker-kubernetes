package com.smartdev.account;

import com.smartdev.account.dto.AccountsContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = {AccountsContactInfoDto.class})
@OpenAPIDefinition(
    info =
        @Info(
            title = "Accounts microservice REST API Documentation",
            description = "Bank Accounts microservice REST API Documentation",
            version = "v1",
            contact =
                @Contact(
                    name = "Smart Dev",
                    email = "hoang.ngoc.hungit@gmail.com",
                    url = "fb/NgocHungUSD"),
            license = @License(name = "Apache 2.0", url = "https://www.ui.com")),
    externalDocs =
        @ExternalDocumentation(
            description = "Bank Accounts microservice REST API Documentation",
            url = "fb/NgocHungUSD"))
public class AccountApplication {

  public static void main(String[] args) {
    SpringApplication.run(AccountApplication.class, args);
  }
}
