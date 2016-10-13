package tube.web.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import tube.entities.Playlist;
import tube.persistence.PlaylistDAO;
import tube.persistence.UserDAO;

@Controller
public class PlaylistController {

	@Autowired
	private PlaylistDAO playlistDao;
	
	@Autowired
	private UserDAO userDao;
	
	@RequestMapping(value="/playlist/{playlistId}", method = GET)
	public String getPlaylist(@PathVariable(value="playlistId") int playlistId, Model model) {
		Playlist playlist = playlistDao.findOne(playlistId);
		model.addAttribute("playlist", playlist);
		return "playlist";
	}
	
	@RequestMapping(value = "/playlists/{username}", method = GET)
	public String getPlaylists(@PathVariable(value="username") String username, Model model) {
		System.out.println(username);
		Set<Playlist> playlists = userDao.findByUsername(username).getPlaylists();
		for (Playlist playlist : playlists) {
			System.out.println(playlist.getName());			
		}
		model.addAttribute("user", userDao.findByUsername(username));
		return "userPlaylists";
	}
	
}
