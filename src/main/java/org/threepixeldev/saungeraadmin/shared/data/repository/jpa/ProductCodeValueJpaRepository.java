package org.threepixeldev.saungeraadmin.shared.data.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.threepixeldev.saungeraadmin.shared.data.model.ProductCodeValue;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductCodeValueJpaRepository extends JpaRepository<ProductCodeValue, Long> {
    List<ProductCodeValue> findByProductId(Long productId);
    List<ProductCodeValue> findByCodeValueId(Long codeValueId);
    Optional<ProductCodeValue> findByProductIdAndCodeValueId(Long productId, Long codeValueId);
    void deleteByProductId(Long productId);
    void deleteByCodeValueId(Long codeValueId);
}
