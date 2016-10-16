package tube.web.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import tube.entities.Playlist;
import tube.entities.User;
import tube.persistence.PlaylistDAO;
import tube.persistence.UserDAO;
import tube.persistence.VideoDAO;
import tube.security.SecurityUser;

@Controller
public class PlaylistController {

	@Autowired
	private PlaylistDAO playlistDao;

	@Autowired
	private UserDAO userDao;

	@Autowired
	private VideoDAO videoDao;

	@RequestMapping(value = "/playlist/{playlistId}", method = GET)
	public String getPlaylist(@PathVariable(value = "playlistId") int playlistId, Model model) {
		Playlist playlist = playlistDao.findOne(playlistId);
		model.addAttribute("playlist", playlist);
		return "playlist";
	}

	@RequestMapping(value = "/playlists/{username}", method = GET)
	public String getPlaylists(@PathVariable(value="username") String username, Model model) {
		User user = userDao.findByUsername(username);
		List<Playlist> playlists = playlistDao.findByUserId(user.getId());
		model.addAttribute("user", user);
		model.addAttribute("playlists", playlists);
		return "userPlaylists";
	}

	@RequestMapping(value = "/video/addToPlaylist", method = POST)
	public @ResponseBody String addVideoToPlaylist(@RequestParam("playlistId") int playlistId,
			@RequestParam("videoId") int videoId) {
		Playlist playlist = playlistDao.findOne(playlistId);
		//check if video is in playlist - returns true if present, false if absent
		boolean addStatus = playlist.getVideos().stream().anyMatch((video) -> video.getId() == videoId);
		String buttonValue = "";
		if (addStatus) {
			playlist.getVideos().remove(videoDao.findOne(videoId));
			buttonValue = "Add to " + playlist.getName();
		} else {
			playlist.getVideos().add(videoDao.findOne(videoId));
			buttonValue = "Remove from " + playlist.getName();
		}
		playlistDao.save(playlist);
		return buttonValue;

	}

	@RequestMapping(value = "/video/changePlaylistButtonValue", method = POST)
	public @ResponseBody String changePlaylistButtonValue(@RequestParam("playlistId") int playlistId,
			@RequestParam("videoId") int videoId) {
		Playlist playlist = playlistDao.findOne(playlistId);
		System.out.println(playlist);
		// check if video is in playlist, returns true if present, false if
		// absent
		boolean addStatus = playlist.getVideos().stream().anyMatch((video) -> video.getId() == videoId);
		String buttonValue = "";
		if (addStatus) {

			buttonValue = "Remove from " + playlist.getName();
		} else {
			buttonValue = "Add to " + playlist.getName();

		}

		return buttonValue;

	}

	@RequestMapping(value = "/video/getPlaylists", method = POST)
	public @ResponseBody List<Playlist> getAddablePlaylists() {
		SecurityUser user = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Playlist> playlists = playlistDao.findByUserId(user.getId());

		return playlists;
	}
	
	@RequestMapping(value = "/newPlaylist", method = POST)
	public String createNewPlaylist(Principal principal, @RequestParam("playlistName") String playlistName) {
		User user = userDao.findByUsername(principal.getName());
		Playlist newPlaylist = new Playlist(user, true, playlistName);
		playlistDao.insertPlaylist(newPlaylist);
		return "redirect:/playlists/" + principal.getName();
	}
}
