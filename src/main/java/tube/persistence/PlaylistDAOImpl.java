package tube.persistence;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import tube.entities.Playlist;

public class PlaylistDAOImpl {
	
	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	public void insertPlaylist(Playlist playlist) {
		em.merge(playlist);
	}

}
