package org.threepixeldev.saungeraadmin.shared.data.repository.jpa;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.threepixeldev.saungeraadmin.shared.data.model.Category;
import org.threepixeldev.saungeraadmin.shared.data.model.User;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long> {
	
	@Query("""
		    SELECT u FROM User u
		    WHERE 
		      (
		           :keyword IS NULL
		        OR LOWER(u.name) LIKE LOWER(CONCAT('%', :keyword, '%'))
		        OR LOWER(u.username) LIKE LOWER(CONCAT('%', :keyword, '%'))
		        OR LOWER(u.email) LIKE LOWER(CONCAT('%', :keyword, '%'))
		        OR u.phoneNumber LIKE CONCAT('%', :keyword, '%')
		      )
		      AND (
		           (:status IS NULL)                                   
		        OR (:status = 'ACTIVE' AND u.deletedAt IS NULL)        
		        OR (:status = 'BLOCKED' AND u.deletedAt IS NOT NULL)   
		      )
		""")
		Page<User> findAllFilteredWithStatus(
		        @Param("keyword") String keyword,
		        Pageable pageable, String status
		);
	@Query("SELECT u FROM User u WHERE u.id = :id AND u.deletedAt IS NULL")
    Optional<User> findByIdNotDeleted(Long id);
	
    @Query("SELECT u FROM User u WHERE u.id = :id AND u.deletedAt IS NOT NULL")
    Optional<User> findByIdDeleted(Long id);
    
    @Query("SELECT u FROM User u WHERE u.id = :id")
    Optional<User> findById(Long id);
    
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
