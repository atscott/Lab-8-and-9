package PhotoViewer;

import java.io.File;

/**
 * User: atscott
 * Date: 10/24/12
 * Time: 7:49 AM
 */
public interface IPhotoViewerView {
    public void AddListener(IController controller);
    public String RetrieveNewAlbumName();
}
