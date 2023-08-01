package com.github.yohannestz.QuickRecipe;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication()
@EntityScan("com.github.yohannestz.QuickRecipe.domain")
@OpenAPIDefinition(info = @Info(title = "Quick Recipe", version = "1.0", description = "a simple spring-boot REST API application that allows users to view and post recipes"))
public class QuickRecipeApplication {
	public static void main(String[] args) {
		SpringApplication.run(QuickRecipeApplication.class, args);
	}
}
