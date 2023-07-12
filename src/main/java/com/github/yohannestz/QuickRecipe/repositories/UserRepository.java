package com.github.yohannestz.QuickRecipe.repositories;

import com.github.yohannestz.QuickRecipe.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
