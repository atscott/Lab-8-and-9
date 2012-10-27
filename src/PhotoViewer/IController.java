package PhotoViewer;

import java.io.File;

/**
 * User: atscott
 * Date: 10/24/12
 * Time: 7:51 AM
 */
public interface IController {

    public void OnNewAlbum();

    public void OnOpenAlbum();

    public void OnAddPhoto();

    public boolean ToggleSlideshow();

    public void ShowImage(File file);

}
