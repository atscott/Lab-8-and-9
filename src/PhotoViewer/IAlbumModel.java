package PhotoViewer;

import java.io.File;
import java.util.ArrayList;

/**
 * User: atscott
 * Date: 10/24/12
 * Time: 7:49 AM
 */
public interface IAlbumModel {
    public String GetName();

    public void Save();

    public boolean AddImage(File image);

    public void AddListener(IController controller);
    public ArrayList<File> getPictures();
    public void ToggleSlideshow();
}
