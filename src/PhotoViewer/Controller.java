package PhotoViewer;

import PhotoViewer.Album.SlideshowOrder;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.Timer;

/**
 * User: atscott
 * Date: 10/24/12
 * Time: 7:43 AM
 */
public class Controller implements IController {

    /**
     * This controller's album
     */
    private IAlbumModel albumModel;

    /**
     * This controller's view
     */
    private IPhotoViewerView view;

    /**
     * The time between images shown in the slideshow
     */
    private int timeBetweenImages = 1;

    /**
     * Timer used to wait between showing slideshow images
     */
    private Timer timer;

    /**
     * Controller state. Album open or closed
     */
    private enum ControllerState {
        ALBUM_OPENED, ALBUM_CLOSED
    }

    private enum slideshowStates {
        SLIDESHOW_RUNNING, SLIDESHOW_STOPPED
    }

    /**
     * The controller's initial state is album closed
     */
    private ControllerState state = ControllerState.ALBUM_CLOSED;

    private slideshowStates slideshowState = slideshowStates.SLIDESHOW_STOPPED;

    /**
     * Creates the controller with the given view and model. Adds this controller as a listener to the view
     *
     * @param model the model for this controller
     * @param view  The view for this controller
     */
    public Controller(IAlbumModel model, IPhotoViewerView view) {
        this.view = view;
        this.albumModel = model;
        this.view.AddListener(this);
        this.view.SetEnabled(false);
    }

    /**
     * Gets a file name for the new album, creates the file, and opens the album. Tells view to display an error message
     * if the album couldn't be created.
     */
    @Override
    public void OnNewAlbum() {
        JFileChooser fc = new JFileChooser("C:\\");
        fc.setFileFilter(new FileNameExtensionFilter("Album File (*.alb)", "alb"));
        fc.showSaveDialog(null);
        File file = fc.getSelectedFile();
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

                this.albumModel = new Album(file);
                this.albumModel.AddListener(this);
                this.state = ControllerState.ALBUM_OPENED;
                this.tellViewToShowAlbumInfo();
                this.view.SetEnabled(true);
            } catch (IOException e) {
                view.ShowErrorMessage("Error creating album: " + e.getMessage());
            }
        }
    }

    /**
     * Given the album file, attempts to create an albumModel and add this controller as the listener. Updates the state
     * to indicate an album is open. Also calls tellViewToShowAlbumInfo so the view shows the album information.
     */
    @Override
    public void OnOpenAlbum() {
        JFileChooser fc = new JFileChooser("C:\\");
        fc.setFileFilter(new FileNameExtensionFilter("Album File (*.alb)", "alb"));
        fc.showOpenDialog(null);
        File file = fc.getSelectedFile();
        if (file != null) {
            this.albumModel = new Album(file);
            try {
                this.albumModel.Open();
                this.albumModel.AddListener(this);
                this.state = ControllerState.ALBUM_OPENED;
                this.tellViewToShowAlbumInfo();
                this.view.SetEnabled(true);
            } catch (FileNotFoundException e) {
                this.view.ShowErrorMessage("Unable to open album: File not found.");
            } catch (IOException e) {
                this.view.ShowErrorMessage("Unable to open album: " + e.getMessage());
            }
        }

    }

    /**
     * Tells the model to save the album
     */
    @Override
    public void OnSaveAlbum() {
        if(this.state == ControllerState.ALBUM_OPENED) {
            try {
                this.albumModel.Save();
            } catch (IOException e) {
                this.view.ShowErrorMessage("Unable to save album: " + e.getMessage());
            }
        } else {
            view.ShowErrorMessage("Cannot save album because no album is open.");
        }
    }

    /**
     * Handles a request to add a photo. Adds the photo to the album and tells the view to also display the added photo
     */
    @Override
    public void OnAddPhoto() {
        if(this.state == ControllerState.ALBUM_OPENED) {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "jpeg");
            fileChooser.setFileFilter(filter);
            int returnVal = fileChooser.showOpenDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File photo = fileChooser.getSelectedFile();
                try {
                    if (albumModel.AddPhoto(photo)) {
                        this.tellViewToAddPhoto(photo);
                    } else {
                        this.view.ShowErrorMessage("Unable to add photo. Photo is already in the album.");
                    }
                } catch (IOException e) {
                    this.view.ShowErrorMessage("Unable to add image:\n" + e.getMessage());
                }
            }
        } else {
            view.ShowErrorMessage("Cannot add photo because no album is open.");
        }
    }

    /**
     * Deletes the photo from the album and calls the tellViewToRemovePhoto method
     */
    @Override
    public void OnDeletePhoto(File photo) {
        if (this.state == ControllerState.ALBUM_OPENED) {
            this.albumModel.RemovePhoto(photo);
            this.tellViewToRemovePhoto(photo);
        } else {
            view.ShowErrorMessage("Cannot delete photo because no album is open.");
        }
    }

    /**
     * Tells the view to remove the given photo
     *
     * @param photo The photo File to remove from the view
     */
    private void tellViewToRemovePhoto(File photo) {
        this.view.RemovePhoto(photo);
    }

    /**
     * If controller has an album open (state), tells the AlbumModel to toggle its slideshow. otherwise tells the view
     * to show an error message.
     */
    @Override
    public boolean ToggleSlideshow() {
        boolean toggled = false;
        if (this.state == ControllerState.ALBUM_OPENED) {
            if (this.slideshowState == slideshowStates.SLIDESHOW_STOPPED) {
                this.slideshowState = slideshowStates.SLIDESHOW_RUNNING;
                timer = new java.util.Timer();
                timer.schedule(new NextImage(), 0);
                toggled = true;
            } else {
                this.slideshowState = slideshowStates.SLIDESHOW_RUNNING;
                timer.cancel();
                toggled = true;
            }
        } else {
            view.ShowErrorMessage("Cannot toggle slideshow because no album is open.");
        }

        return toggled;
    }

    /**
     * tells the controller to show the next image. THen schedules a new timer task NextImage to continue the loop.
     */
    class NextImage extends TimerTask {
        public void run() {
            if (slideshowState == slideshowStates.SLIDESHOW_RUNNING) {
                File picture = albumModel.getNextPicture();

                ShowImage(picture);
                try {
                    timer.schedule(new NextImage(), timeBetweenImages * 1000);
                } catch (IllegalStateException e) {
                    //timer is already cancelled so don't start a new task
                }
            }
        }
    }

    /**
     * Tells the view to display the specified image file.
     */
    @Override
    public void ShowImage(File file) {
        if (this.state == ControllerState.ALBUM_OPENED) {
            this.view.ShowImage(file);
        } else {
            view.ShowErrorMessage("Cannot show photo because no album is open.");
        }
    }

    /**
     * Tells the view to display all the album information
     */
    private void tellViewToShowAlbumInfo() {
        if (this.state == ControllerState.ALBUM_OPENED) {
            this.view.ClearEverything();
            this.view.DisplayAlbumName(this.albumModel.GetName());
            for (File picture : this.albumModel.getPictures()) {
                this.tellViewToAddPhoto(picture);
            }
        }
    }

    /**
     * Tells the view that a new photo was added to the album and requests that it be displayed
     *
     * @param photo The photo that was added to the album
     */
    private void tellViewToAddPhoto(File photo) {
        if (this.state == ControllerState.ALBUM_OPENED) {
            this.view.AddPhoto(photo);
        }
    }

    /**
     * If an album is open, tells the album model to set the time between images
     */
    @Override
    public void OnTimeChange(int newTime) {
        if (newTime > 0) {
            timeBetweenImages = newTime;
        } else {
            timeBetweenImages = 1;
        }
    }

    /**
     * tells the alum model to change slideshow order
     */
    @Override
    public void OnOrderSelection(SlideshowOrder o) {

        albumModel.SetSlideshowOrder(o);

    }

}
