package PhotoViewer;

import PhotoViewer.Album.SlideshowOrder;

import java.io.File;
import java.util.List;

/**
 * User: atscott
 * Date: 10/24/12
 * Time: 7:51 AM
 */
public interface IController {

    public void OnNewAlbum(File file);

    public void OnOpenAlbum(File file);

    public void OnSaveAlbum();

    public void OnAddPhoto();

    public void AddPhoto(List<File> transferData);

    void OnDeletePhoto(File photo);

    public int ToggleSlideshow();

    public boolean StopSlideshow();

    public boolean StartSlideshow();

    public void OnTimeChange(int newTime);

    public void ShowImage(File file);

    public void OnOrderSelection(SlideshowOrder o);

}
