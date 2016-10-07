package tube.persistence;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tube.entities.Playlist;

public interface PlaylistDAO extends JpaRepository<Playlist, Integer> {
	
		List<Playlist> getByUserIdAndName(Integer userId,String name);
		
}
