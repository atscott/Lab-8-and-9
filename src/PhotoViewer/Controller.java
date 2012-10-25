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
        this.tellViewToShowAlbumInfo();
    }

    @Override
    public void OnAddPicture() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(null);
        File image = fileChooser.getSelectedFile();
        if (albumModel.AddImage(image)) {
            view.AddPhoto(image);
        }
        // TODO if it's the first image display it
    }

    private void tellViewToShowAlbumInfo() {
        this.view.DisplayAlbumName(this.albumModel.GetName());

    @Override
    public void ShowImage(File file) {
        this.view.showImage(file);
    }

    private void tellViewToShowAlbumInfo() {
        this.view.ClearEverything();
        this.view.DisplayAlbumName(this.albumModel.GetName());
        for (File picture : this.albumModel.getPictures()) {
            this.view.AddPhoto(picture);
        }
    }


}
