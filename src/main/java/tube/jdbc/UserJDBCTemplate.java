package tube.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tube.entities.User;

public class UserJDBCTemplate{
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	public int registerNewUser(final User user) {
		final String SQL = "INSERT INTO users (username, email, password, isAdmin, isBanned) VALUES (?, ?, ?, ?, ?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.jdbcTemplateObject.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pst = con.prepareStatement(SQL, new String[] { "id" });
				pst.setString(1, user.getUsername());
				pst.setString(2, user.getEmail());
				pst.setString(3, user.getPassword());
				pst.setBoolean(4, user.isAdmin());
				pst.setBoolean(5, user.isBanned());
				return pst;
			}
		}, keyHolder);

		return keyHolder.getKey().intValue();
	}

	public User login(String username) {
		String SQL = "SELECT * FROM users where username = ?";

		try {
		      User user = jdbcTemplateObject.queryForObject(SQL, new Object[] { username }, new UserMapper() {
			 public User mapRow(ResultSet rs, int rowNum) throws SQLException {

				User user = new User();
				user.setId(rs.getLong("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setAdmin(rs.getBoolean("isAdmin"));
				user.setBanned(rs.getBoolean("isBanned"));

				return user;
			 }
		                });
		      return user;

		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}

	public User get(int id) {
		String SQL = "select * from users where user_id = ?";
		User user = jdbcTemplateObject.queryForObject(SQL, new Object[] { id }, new UserMapper());
		return user;
	}

	public User get(String email) {
		String SQL = "select * from users where email = ?";
		User user = jdbcTemplateObject.queryForObject(SQL, new Object[] { email }, new UserMapper());
		return user;
	}

	public boolean userNameExists(String userName) {
		String SQL = "SELECT count(*) FROM users WHERE user_name = ?";
		Integer cnt = jdbcTemplateObject.queryForObject(SQL, Integer.class, userName);
		return cnt != null && cnt > 0;
	}

	public boolean emailExists(String email) {
		String SQL = "SELECT count(*) FROM users WHERE email = ?";
		Integer cnt = jdbcTemplateObject.queryForObject(SQL, Integer.class, email);
		return cnt != null && cnt > 0;
	}

	public List<User> listUsers() {
		String SQL = "select * from users";
		List<User> users = jdbcTemplateObject.query(SQL, new UserMapper());
		return users;
	}

}
