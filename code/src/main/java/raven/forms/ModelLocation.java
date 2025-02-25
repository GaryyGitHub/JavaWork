package raven.forms;

/**
 * @author ikun568
 * @version 1.0
 * @Description ModelLocation
 **/
public class ModelLocation {

    private String title;
    private String description;
    private String videoPath;

    public ModelLocation(String title, String description, String videoPath) {
        this.title = title;
        this.description = description;
        this.videoPath = videoPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }
}
