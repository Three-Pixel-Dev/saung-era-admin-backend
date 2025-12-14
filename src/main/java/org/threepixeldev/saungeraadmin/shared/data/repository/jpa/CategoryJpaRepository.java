package org.threepixeldev.saungeraadmin.shared.data.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.threepixeldev.saungeraadmin.shared.data.model.Category;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryJpaRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);
    
    @Query("SELECT c FROM Category c WHERE c.deletedAt IS NULL")
    List<Category> findAllNotDeleted();
    
    @Query("SELECT c FROM Category c WHERE c.id = :id AND c.deletedAt IS NULL")
    Optional<Category> findByIdNotDeleted(Long id);
}
