package PhotoViewer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * User: atscott
 * Date: 10/24/12
 * Time: 7:49 AM
 */
public interface IAlbumModel {
    public String GetName();

    public void Save();

    public void AddListener(IController controller);

    public void Open();

    public ArrayList<File> getPictures();

    public void ToggleSlideshow();

    public void setTimeBetweenImages(int time);

    public void setSlideshowOrder(Album.SlideshowOrder slideshowOrder);

    public void AddPhoto(File photo) throws IOException;

    public void RemovePhoto(File photo);
}
