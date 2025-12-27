package org.threepixeldev.saungeraadmin.shared.data.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.threepixeldev.saungeraadmin.shared.data.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface ProductJpaRepository extends JpaRepository<Product, Long> {

    boolean existsBySkuAndDeletedAtIsNull(String sku);
    boolean existsBySkuAndIdNotAndDeletedAtIsNull(String sku, Long id);

    @Query("""
        SELECT DISTINCT p FROM Product p
        LEFT JOIN p.productCategories pc
        WHERE p.deletedAt IS NULL
          AND (
               (:status IS NULL OR :status = '')
            OR (:status = 'Low Stock' AND p.quantity > 0 AND p.quantity <= 10)
            OR (:status = 'Out of Stock' AND p.quantity = 0)
            OR (:status NOT IN ('Low Stock', 'Out of Stock') AND p.status = :status)
          )
          AND (:categoryId IS NULL OR pc.category.id = :categoryId)
          AND (
               :keyword IS NULL OR :keyword = ''
            OR LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%'))
            OR LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%'))
            OR LOWER(p.sku) LIKE LOWER(CONCAT('%', :keyword, '%'))
          )
    """)
    Page<Product> searchProducts(
            @Param("keyword") String keyword,
            @Param("status") String status,
            @Param("categoryId") Long categoryId,
            Pageable pageable
    );
}