package com.github.yohannestz.QuickRecipe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.SystemMetricsAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication()
@EntityScan("com.github.yohannestz.QuickRecipe.domain")
public class QuickRecipeApplication {
	public static void main(String[] args) {
		SpringApplication.run(QuickRecipeApplication.class, args);
	}
}
