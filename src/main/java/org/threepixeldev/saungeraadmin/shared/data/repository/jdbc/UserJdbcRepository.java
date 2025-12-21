package org.threepixeldev.saungeraadmin.shared.data.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.threepixeldev.saungeraadmin.features.user.dto.UserResponseDetails;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserJdbcRepository {
	private final JdbcTemplate jdbcTemplate; // Inject this via constructor

	public UserResponseDetails getUserById(Long id) {
		String sql = """
				    SELECT
				        u.id, u.name, u.username, u.email, u.phone_number, u.deleted_at,
				        p.address, p.kyc, p.date_of_birth, p.points, p.referral_code,
				        (SELECT COUNT(*) FROM orders o WHERE o.user_id = u.id) as total_order
				    FROM users u
				    LEFT JOIN profile_details p ON p.id = u.id
				    WHERE u.id = ?
				""";

		try {
			return jdbcTemplate.queryForObject(sql, new UserDetailsRowMapper(), id);
		} catch (EmptyResultDataAccessException e) {
			throw new RuntimeException("User not found with id: " + id);
		}
	}

	private static class UserDetailsRowMapper implements RowMapper<UserResponseDetails> {
		@Override
		public UserResponseDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserResponseDetails dto = new UserResponseDetails();

			dto.setId(rs.getLong("id"));
			dto.setName(rs.getString("name"));
			dto.setUsername(rs.getString("username"));
			dto.setEmail(rs.getString("email"));
			dto.setPhoneNumber(rs.getString("phone_number"));

			Timestamp deletedAt = rs.getTimestamp("deleted_at");
			dto.setStatus(deletedAt == null ? "ACTIVE" : "BLOCKED");

			dto.setAddress(rs.getString("address"));
			dto.setKyc(rs.getString("kyc"));

			java.sql.Date dob = rs.getDate("date_of_birth");
			if (dob != null) {
				dto.setDateOfBirth(dob.toLocalDate());
			}

			dto.setPoints(rs.getObject("points", Integer.class));
			dto.setReferralCode(rs.getString("referral_code"));

			dto.setTotalOrder(rs.getInt("total_order"));

			return dto;
		}
	}
}
