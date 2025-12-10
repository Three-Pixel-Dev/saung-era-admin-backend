package org.threepixeldev.saungeraadmin.shared.data.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.threepixeldev.saungeraadmin.shared.data.model.ProfileDetails;

@Repository
public interface ProfileDetailsJpaRepository extends JpaRepository<ProfileDetails, Long> {
}
