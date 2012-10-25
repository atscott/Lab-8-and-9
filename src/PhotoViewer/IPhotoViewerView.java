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
    public void AddPhotos(ArrayList<File> pictures);
    public void showErrorMessage(String message);
}
