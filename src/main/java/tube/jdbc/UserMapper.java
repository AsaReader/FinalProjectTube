package tube.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tube.entities.User;

public class UserMapper implements RowMapper<User> {
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = null;
		user = new User(rs.getLong("id"), rs.getString("username"), rs.getString("email"), rs.getString("password"));
		return user;
	}
}
