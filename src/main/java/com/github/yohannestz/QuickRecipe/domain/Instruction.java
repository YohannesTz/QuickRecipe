package com.github.yohannestz.QuickRecipe.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Instruction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String content;
    private boolean isOptional;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "recipe_id")
    @JsonIgnore
    private Recipe recipe;

    public Instruction() {}

    public Instruction(String content, boolean isOptional, Recipe recipe) {
        this.content = content;
        this.isOptional = isOptional;
        this.setRecipe(recipe);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isOptional() {
        return isOptional;
    }

    public void setOptional(boolean optional) {
        isOptional = optional;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instruction that = (Instruction) o;
        return isOptional == that.isOptional && Objects.equals(content, that.content) && Objects.equals(recipe, that.recipe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, isOptional, recipe);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
