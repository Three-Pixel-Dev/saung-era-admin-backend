package org.threepixeldev.saungeraadmin.features.user.service;

import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.threepixeldev.saungeraadmin.shared.dto.UserResponse;

public interface UserService {
	Map<String, Object> getAllUsers(String keyword,Pageable pageable);
	UserResponse getUserById(Long id);
	void blockUser(Long id, Long deletedBy);
	UserResponse unblockUser(Long id, Long restoredBy);
}
