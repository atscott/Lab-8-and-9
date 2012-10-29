package PhotoViewer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * User: atscott
 * Date: 10/24/12
 * Time: 7:49 AM
 */
public interface IAlbumModel {
    public String GetName();

    public void Save() throws IOException;

    public void AddListener(IController controller);

    public void Open() throws IOException;

    public ArrayList<File> getPictures();

    public void ToggleSlideshow();

    public void SetTimeBetweenImages(int time);

    public void SetSlideshowOrder(Album.SlideshowOrder slideshowOrder);

    public boolean AddPhoto(File photo) throws IOException;

    public void RemovePhoto(File photo);
}
