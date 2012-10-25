package PhotoViewer;

/**
 * User: atscott
 * Date: 10/24/12
 * Time: 7:49 AM
 */
public interface IAlbumModel {
    public String GetName();
    public void Save();
    public void AddListener(IController controller);
}
