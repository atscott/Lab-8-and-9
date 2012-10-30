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

    public void OnNewAlbum();

    public void OnOpenAlbum();

    public void OnSaveAlbum();

    public void OnAddPhoto();

    public void OnAddPhoto(List<File> transferData);

    void OnDeletePhoto(File photo);

    public int ToggleSlideshow();

    public boolean stopSlideshow();

    public boolean startSlideshow();

    public void OnTimeChange(int newTime);

    public void ShowImage(File file);

    public void OnOrderSelection(SlideshowOrder o);

}
