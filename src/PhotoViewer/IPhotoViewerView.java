package PhotoViewer;

import java.io.File;

/**
 * User: atscott
 * Date: 10/24/12
 * Time: 7:49 AM
 */
public interface IPhotoViewerView {
    public void AddListener(IController controller);

    public void DisplayAlbumName(String name);

    public void AddPhoto(File picture);

    public void ShowErrorMessage(String message);

    public void ClearEverything();

    public void ShowImage(File file);

    public void DisableAllFunctions();

    public void EnableAllFunctions();

    public void RemovePhoto(File photo);

    public void SetPictureLabel(String label);
}
