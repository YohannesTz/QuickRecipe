package com.github.yohannestz.QuickRecipe.controllers;

import com.github.yohannestz.QuickRecipe.domain.Ingredient;
import com.github.yohannestz.QuickRecipe.domain.Instruction;
import com.github.yohannestz.QuickRecipe.domain.Recipe;
import com.github.yohannestz.QuickRecipe.exception.ResourceNotFoundException;
import com.github.yohannestz.QuickRecipe.repositories.RecipeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/recipe")
public class RecipeController {
    private final RecipeRepository recipeRepository;

    public RecipeController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @GetMapping
    public List<Recipe> getAllRecipe() {
        return recipeRepository.findAll();
    }

    @GetMapping("/id/{id}")
    public Recipe getRecipeById(@PathVariable Long id) throws ResourceNotFoundException {
        return recipeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe not found!"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Recipe createRecipe(@RequestBody Recipe recipe) {
        System.out.println(recipe.toString());
        List<Instruction> instructions = recipe.getInstructions();
        for (Instruction instruction : instructions) {
            instruction.setRecipe(recipe);
        }
        List<Ingredient> ingredients = recipe.getIngredients();
        for (Ingredient ingredient: ingredients) {
            ingredient.setRecipe(recipe);
        }
        recipe.setInstructions(instructions);
        recipe.setIngredients(ingredients);
        return recipeRepository.save(recipe);
    }

    @DeleteMapping("/id/{id}")
    public Map<String, String> deleteRecipe(@PathVariable(value = "id") Long recipeId) throws ResourceNotFoundException {
        Recipe candidateRecipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe not found"));

        recipeRepository.delete(candidateRecipe);
        Map<String, String> response = new HashMap<>();
        response.put("message", "successfully deleted recipe");
        return response;
    }

    @GetMapping("/userId/{id}")
    public List<Recipe> getRecipesByUserId(@PathVariable(value = "id") Long userId) {
        return recipeRepository.findRecipeByUserId(userId);
    }
}
