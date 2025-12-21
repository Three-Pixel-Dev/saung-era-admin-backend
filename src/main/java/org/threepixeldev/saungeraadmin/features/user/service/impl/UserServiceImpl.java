package org.threepixeldev.saungeraadmin.features.user.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.threepixeldev.saungeraadmin.features.user.dto.UserResponse;
import org.threepixeldev.saungeraadmin.features.user.dto.UserResponseDetails;
import org.threepixeldev.saungeraadmin.features.user.service.UserService;
import org.threepixeldev.saungeraadmin.shared.data.model.User;
import org.threepixeldev.saungeraadmin.shared.data.repository.jdbc.UserJdbcRepository;
import org.threepixeldev.saungeraadmin.shared.data.repository.jpa.UserJpaRepository;
import org.threepixeldev.saungeraadmin.shared.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	private static final String CACHE_NAME = "users";
	private static final String CACHE_KEY_ALL = "'all'";
	private static final String CACHE_KEY_BY_ID = "'user:' + #id";
	private static final String CACHE_PAGINATION_KEY = "#keyword + '_' + #pageable.pageNumber + '_' + #pageable.pageSize + '_' + #status";
	private final UserJpaRepository userJpaRepository;
	private final UserJdbcRepository userJdbcRepository;
	private final UserMapper userMapper;

	@Override
	@Cacheable(value = CACHE_NAME, key = CACHE_PAGINATION_KEY, unless = "#result.isEmpty()")
	@Transactional(readOnly = true)
	public Map<String, Object> getAllUsers(String keyword, Pageable pageable, String status) {
		Page<User> userPage = userJpaRepository.findAllFilteredWithStatus(keyword, pageable, status);
		List<? extends UserResponse> content = userPage.getContent().stream()
			    .map((u) -> UserResponse.builder()
			        .id(u.getId())
			        .name(u.getName())
			        .username(u.getUsername())
			        .email(u.getEmail())
			        .phoneNumber(u.getPhoneNumber())
			        .status(u.getDeletedAt() == null ? "ACTIVE" : "BLOCKED")
			        .createdAt(u.getCreatedAt())
			        .build()).toList();

		Map<String, Object> response = new HashMap<>();
		response.put("content", content);
		response.put("totalElements", userPage.getTotalElements());
		response.put("totalPages", userPage.getTotalPages());
		return response;
	}

	@Override
	@Cacheable(value = CACHE_NAME, key = CACHE_KEY_BY_ID, unless = "#result == null")
	@Transactional(readOnly = true)
	public UserResponseDetails getUserById(Long id) {
		User user = userJpaRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("User not found with id: " + id));
		UserResponseDetails response = userJdbcRepository.getUserById(user.getId());
		return response;
	}

	@Override
	@CacheEvict(value = CACHE_NAME, allEntries = true)
	public void blockUser(Long id, Long deletedBy) {
		User user = userJpaRepository.findByIdNotDeleted(id)
				.orElseThrow(() -> new RuntimeException("User not found with id: " + id));

		user.delete(deletedBy);
		userJpaRepository.save(user);
	}

	@Override
	@CacheEvict(value = CACHE_NAME, allEntries = true)
	public String unblockUser(Long id, Long restoredBy) {
		User user = userJpaRepository.findByIdDeleted(id)
				.orElseThrow(() -> new RuntimeException("Blocked user not found with id: " + id));

		user.restore();
		user.setUpdatedBy(restoredBy);

		userJpaRepository.save(user);
		
		return "Unblock success.";
	}
}
