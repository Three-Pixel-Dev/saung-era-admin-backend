package org.threepixeldev.saungeraadmin.features.user.service;

import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.threepixeldev.saungeraadmin.features.user.dto.UserResponseDetails;

public interface UserService {
	Map<String, Object> getAllUsers(String keyword,Pageable pageable,String status);
	UserResponseDetails getUserById(Long id);
	void blockUser(Long id, Long deletedBy);
	String unblockUser(Long id, Long restoredBy);
}
