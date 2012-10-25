package PhotoViewer;

import java.io.File;
import java.util.ArrayList;

/**
 * User: atscott
 * Date: 10/24/12
 * Time: 7:49 AM
 */
public interface IPhotoViewerView {
    public void AddListener(IController controller);
    public File RetrieveNewAlbumName();
    public void DisplayAlbumName(String name);
    public void AddPhoto(File picture);
    public void showErrorMessage(String message);
    public void ClearEverything();
    public void showImage(File file);
}
