package PhotoViewer;

import PhotoViewer.Album.SlideshowOrder;

import java.io.File;

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

    void OnDeletePhoto(File photo);

    public boolean ToggleSlideshow();

    public void OnTimeChange(int newTime);

    public void ShowImage(File file);

    public void OnOrderSelection(SlideshowOrder o);

}
