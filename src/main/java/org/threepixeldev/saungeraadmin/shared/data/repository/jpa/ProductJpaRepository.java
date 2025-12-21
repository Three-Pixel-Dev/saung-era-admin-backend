package org.threepixeldev.saungeraadmin.shared.data.repository.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.threepixeldev.saungeraadmin.shared.data.model.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductJpaRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);
    List<Product> findByCountryId(Long countryId);
    List<Product> findByNameContainingIgnoreCase(String name);

    @Query("""
        SELECT p FROM Product p
        WHERE p.deletedAt IS NULL
          AND (
               :keyword IS NULL
            OR LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%'))
            OR LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%'))
            OR LOWER(p.shortDescription) LIKE LOWER(CONCAT('%', :keyword, '%'))
          )
    """)
    Page<Product> findAllFilteredNotDeleted(
            @Param("keyword") String keyword,
            Pageable pageable
    );
}
