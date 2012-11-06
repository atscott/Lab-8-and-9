package Tests;

import PhotoViewer.IController;
import PhotoViewer.IPhotoViewerView;

import java.io.File;
import java.util.EventObject;

/**
 * User: scottat
 * Date: 11/3/12
 * Time: 3:56 PM
 */
class CustomView implements IPhotoViewerView {
    /**
     * Allows us to test the controller methods by creating a "fake" view that implements the interface and sets
     * attributes to indicate what methods were called.
     */

    IController addListenerCalledWith = null;

    String displayAlbumNameCalledWith = null;

    File addPhotoCalledWith = null;

    String showErrorMessageCalledWith = null;

    boolean clearEverythingCalled = false;

    File showImageCalledawith = null;
    int numberOfTimesShowImageCalled = 0;

    boolean setEnabledCalled = false;
    boolean setEndabledCalledWith = false;

    File removePhotoCalledWith = null;

    CustomView() {
    }

    @Override
    public void AddListener(IController controller) {
        this.addListenerCalledWith = controller;
    }

    @Override
    public void DisplayAlbumName(String name) {
        this.displayAlbumNameCalledWith = name;
    }

    @Override
    public void AddPhoto(File picture) {
        this.addPhotoCalledWith = picture;
    }

    @Override
    public void ShowErrorMessage(String message) {
        this.showErrorMessageCalledWith = message;
    }

    @Override
    public void ClearEverything() {
        this.clearEverythingCalled = true;
    }

    @Override
    public void ShowImage(File file) {
        this.showImageCalledawith = file;
        numberOfTimesShowImageCalled++;
        fireEvent();
    }

    @Override
    public void SetEnabled(boolean enabled) {
        this.setEnabledCalled = true;
        this.setEndabledCalledWith = enabled;
    }

    @Override
    public void RemovePhoto(File photo) {
        this.removePhotoCalledWith = photo;
    }

    public class ShowImageCalled extends java.util.EventObject {
        public ShowImageCalled(Object source) {
            super(source);
        }
    }

    public interface ShowImageCalledListener {
        public void handleShowImageCalled(EventObject e);
    }

    public ShowImageCalledListener listener;

    private synchronized void fireEvent() {
        ShowImageCalled event = new ShowImageCalled(this);
        if (listener != null) {
            this.listener.handleShowImageCalled(event);
        }
    }

}


