package tube.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public class FileBucket {
	MultipartFile file;
	
	@NotNull
	@Size(min=5, max=90, message="{bucket.size}")
	@Pattern(regexp="[a-zA-Z0-9_-]+", message="{bucket.pattern}")
	private String title;
	
	@NotNull
	@Size(max=150, message="{bucket.size2}")
	@Pattern(regexp="[a-zA-Z0-9_-]+", message="{bucket.patternDescr}")
	private String descr;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}
}
