package tube.persistence;



import java.util.List;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import tube.entities.Playlist;

public interface PlaylistDAO extends JpaRepository<Playlist, Integer> {
	
	
		@Cacheable("tubeCache")
		List<Playlist> getByUserIdAndName(Integer userId,String name);
		
		
		@CachePut(value = "tubeCache",key="#result.id")
		@Override
		<S extends Playlist> S save(S arg0);
		
}
