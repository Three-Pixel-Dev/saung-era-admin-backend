package org.threepixeldev.saungeraadmin.features.user.service;

import java.util.Map;

import org.springframework.data.domain.Pageable;

public interface UserService {
	Map<String, Object> getAllUsers(String keyword,Pageable pageable);
}
