package PhotoViewer;

import javax.swing.*;
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

    private enum ControllerState {ALBUM_OPENED, ALBUM_CLOSED;}

    private ControllerState state = ControllerState.ALBUM_CLOSED;

    public Controller(IAlbumModel model, IPhotoViewerView view) {
        this.view = view;
        this.albumModel = model;
        this.view.AddListener(this);
    }

    @Override
    public void OnNewAlbum() {
        File file = this.view.RetrieveNewAlbumName();
        if (file != null) {
            if (file.exists()) {
                file.delete();
            }
            try {
                String name = file.getPath();
                if (!name.endsWith(".alb")) {
                    file = new File(name + ".alb");
                }
                file.createNewFile();
                this.OnOpenAlbum(file);
            } catch (IOException e) {
                view.showErrorMessage("Error creating album: " + e.getMessage());
            }
        }
    }

    @Override
    public void OnOpenAlbum(File file) {
        if (file == null)
            throw new NullPointerException("File cannot be null");

        this.albumModel = new Album(file);
        this.albumModel.AddListener(this);
        this.state = ControllerState.ALBUM_OPENED;
        this.tellViewToShowAlbumInfo();
    }

    @Override
    public void OnAddPhoto() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(null);
        File photo = fileChooser.getSelectedFile();
        albumModel.AddPhoto(photo);
        this.tellViewToAddPhoto(photo);
    }

    @Override
    public boolean ToggleSlideshow() {
        boolean toggled = false;
        if (this.state == ControllerState.ALBUM_OPENED) {
            this.albumModel.ToggleSlideshow();
            toggled = true;
        } else {
            view.showErrorMessage("Cannot toggle slideshow because no album is open.");
        }

        return toggled;
    }

    @Override
    public void ShowImage(File file) {
        if (this.state == ControllerState.ALBUM_OPENED) {
            this.view.showImage(file);
        }
    }

    private void tellViewToShowAlbumInfo() {
        if (this.state == ControllerState.ALBUM_OPENED) {
            this.view.ClearEverything();
            this.view.DisplayAlbumName(this.albumModel.GetName());
            for (File picture : this.albumModel.getPictures()) {
                this.view.AddPhoto(picture);
            }
        }
    }

    private void tellViewToAddPhoto(File photo) {
        this.view.AddPhoto(photo);
    }


}
