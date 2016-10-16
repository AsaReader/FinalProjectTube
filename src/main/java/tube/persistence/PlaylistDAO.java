package tube.persistence;



import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import tube.entities.Playlist;

public interface PlaylistDAO extends JpaRepository<Playlist, Integer> {
	
		@Cacheable("playlistCache")
		List<Playlist> getByUserIdAndName(Integer userId,String name);
		
		@CacheEvict(value = "playlistCache",key="#result.id")
		@Override
		<S extends Playlist> S save(S arg0);
		
		@Override
		@Cacheable("playlistCache")
		Playlist findOne(Integer id);

		List<Playlist> findByUserId(Integer id);
		
		void insertPlaylist(Playlist playlist);
}
