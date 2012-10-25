package PhotoViewer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * User: atscott
 * Date: 10/24/12
 * Time: 7:43 AM
 */
public class Album implements IAlbumModel {
    final File me;
    private ArrayList<File> pictures = new ArrayList<File>();
    private ArrayList<File> randomizedPictures = new ArrayList<File>();
    Timer timer;
    int timeBetweenImages = 1;

    /**
     * This is the controller that the album is linked to
     */
    private IController controller;

    /**
     * This constant indicates that the slideshow will iterate through images sequentially
     */
    public static final int SEQUENTIAL = 0;

    /**
     * This constant indicates that the slideshow will go through the images in a random order
     */
    public static final int RANDOM = 1;

    private int slideshowOrder = 0;

    private int indexOfLastShownFile = 0;

    public Album(File file) {
        this.me = file;
    }

    @Override
    public String GetName() {
        return me.getName();
    }

    @Override
    public void Save() {
        try {
            // Create file
            FileWriter fstream = new FileWriter(me.getPath());
            BufferedWriter out = new BufferedWriter(fstream);

            //save all the pictures to the file, one per line.
            for (File picture : pictures) {
                out.write(picture.getPath() + "\n");
            }

            //Close the output stream
            out.close();
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
    }

    @Override
    public boolean AddImage(File image) {
        // TODO check if its really an image
        pictures.add(image);
        return true;
    }

    @Override
    public void AddListener(IController controller) {
        this.controller = controller;
    }

    @Override
    public ArrayList<File> getPictures() {
        return this.pictures;
    }

    public void setSlideshowOrder(int order) {
        if (order == Album.RANDOM) {
            this.slideshowOrder = Album.RANDOM;
        } else {
            this.slideshowOrder = Album.SEQUENTIAL;
        }
    }

    @Override
    public void ToggleSlideshow() {
        if (timer == null) {
            if (this.slideshowOrder == Album.RANDOM) {
                this.createRandomizedList();
            }

            timer = new Timer();
            timer.schedule(new NextImage(), timeBetweenImages * 1000);
        } else {
            timer.cancel();
            timer = null;
        }
    }

    private void createRandomizedList() {
        this.randomizedPictures.clear();
        ArrayList<File> temp = new ArrayList<File>();
        for (File file : this.pictures) {
            temp.add(file);
        }

        while (temp.size() > 0) {
            Random rand = new Random();
            int num = rand.nextInt(temp.size());
            randomizedPictures.add(temp.remove(num));
        }
    }

    class NextImage extends TimerTask {
        public void run() {
            File picture;
            if (slideshowOrder == Album.SEQUENTIAL) {
                picture = getNextImageFromList(pictures);
            } else {
                picture = getNextImageFromList(randomizedPictures);
            }
            controller.ShowImage(picture);
            timer.schedule(new NextImage(), timeBetweenImages * 1000);
        }
    }

    private File getNextImageFromList(ArrayList<File> list) {
        File retVal = null;

        if (list.size() > 0) {
            if (this.indexOfLastShownFile == list.size() - 1) {
                retVal = list.get(0);
                this.indexOfLastShownFile = 0;
            } else {
                retVal = list.get(this.indexOfLastShownFile + 1);
                this.indexOfLastShownFile++;
            }
        }

        return retVal;
    }


}
