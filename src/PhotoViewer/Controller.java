package PhotoViewer;

import java.io.File;
import java.io.IOException;

/**
 * User: atscott
 * Date: 10/24/12
 * Time: 7:43 AM
 */
public class Controller implements IController {
    IAlbumModel albumModel;
    IPhotoViewerView view;

    public Controller(IAlbumModel model, IPhotoViewerView view) {
        this.view = view;
        this.albumModel = model;
        this.view.AddListener(this);
    }

    @Override
    public void OnNewAlbum() {
        String fileName = this.view.RetrieveNewAlbumName();
        if (fileName != null) {
            try {
                albumModel = new Album(fileName);
                this.tellViewToShowAlbumInfo();
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }

    @Override
    public void OnOpenAlbum() {
        this.tellViewToShowAlbumInfo();
        //To change body of implemented methods use File | Settings | File Templates.
    }

    private void tellViewToShowAlbumInfo(){

    }


}
