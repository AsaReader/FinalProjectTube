package tube.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tube.entities.Video;

public class VideoMapper implements RowMapper<Video> {

	public Video mapRow(ResultSet rs, int rowNum) throws SQLException {
		Video video = null;
		video = new Video(rs.getInt("id"), rs.getString("description"), rs.getString("file_name"), rs.getString("title"), rs.getInt("user_id"));
		return video;
	}
}
