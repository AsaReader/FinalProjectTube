package tube.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tube.entities.Video;

public class VideoJDBCTemplate {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;


	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}


	public int addVideo(Video video) {
		final String SQL = "INSERT INTO videos (file_name, title, description, user_id) VALUES (?,?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();

		this.jdbcTemplateObject.update(new PreparedStatementCreator() {
			
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pst = con.prepareStatement(SQL, new String[] { "id" });
				pst.setString(1, video.getFileName());
				pst.setString(2, video.getTitle());
				pst.setString(3, video.getDescription());
				pst.setInt(4, video.getUserID());
				return pst;
			}
		}, keyHolder);

		return keyHolder.getKey().intValue();
	}


	public List<Video> getClips() {
		String SQL = "SELECT * FROM videos";
		List<Video> videoClips = jdbcTemplateObject.query(SQL, new VideoMapper());
		return videoClips;
	}
	
	public Video getClip(int id) {
		String SQL = "SELECT * FROM videos WHERE id = ?";
		Video video = jdbcTemplateObject.queryForObject(SQL, new Object[] { id }, new VideoMapper() {
			 public Video mapRow(ResultSet rs, int rowNum) throws SQLException {

				Video video = new Video();
				video.setId(rs.getInt("id"));
				video.setFileName(rs.getString("file_name"));
				video.setTitle(rs.getString("title"));
				video.setDescription(rs.getString("description"));
				video.setUserID(rs.getInt("user_id"));

				return video;
			 }
		                });
		return video;
	}
}
