package com.github.yohannestz.QuickRecipe.repositories;

import com.github.yohannestz.QuickRecipe.domain.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    @Query(value = "SELECT * FROM recipe WHERE user_id = ?", nativeQuery = true)
    List<Recipe> findRecipeByUserId(Long userId);
}
