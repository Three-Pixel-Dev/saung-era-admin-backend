package org.threepixeldev.saungeraadmin.shared.data.repository.jpa;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.threepixeldev.saungeraadmin.shared.data.model.User;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long> {
	
	@Query("""
		    SELECT u FROM User u
		    WHERE u.deletedAt IS NULL
		      AND (
		           :keyword IS NULL
		        OR LOWER(u.name) LIKE LOWER(CONCAT('%', :keyword, '%'))
		        OR LOWER(u.username) LIKE LOWER(CONCAT('%', :keyword, '%'))
		        OR LOWER(u.email) LIKE LOWER(CONCAT('%', :keyword, '%'))
		        OR u.phoneNumber LIKE CONCAT('%', :keyword, '%')
		      )
		""")
		Page<User> findAllFilteredNotDeleted(
		        @Param("keyword") String keyword,
		        Pageable pageable
		);
	
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
