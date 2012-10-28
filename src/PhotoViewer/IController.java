package PhotoViewer;

import java.io.File;

import PhotoViewer.Album.SlideshowOrder;

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

    public void onTimeChange(int newTime);

    public void ShowImage(File file);
    
    public void OnOrderSelection(SlideshowOrder o);

}
