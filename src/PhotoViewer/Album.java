package PhotoViewer;

import java.io.*;
import java.util.*;

/**
 * User: atscott
 * Date: 10/24/12
 * Time: 7:43 AM
 */
public class Album implements IAlbumModel {

    /**
     * The album File
     */
    final File me;

    /**
     * The pictures in this album
     */
    private ArrayList<File> pictures = new ArrayList<File>();

    /**
     * The pictures in this album, in a random order
     */
    private ArrayList<File> randomizedPictures = new ArrayList<File>();

    /**
     * Timer used to wait between showing slideshow images
     */
    Timer timer;

    /**
     * The time between images shown in the slideshow
     */
    private int timeBetweenImages = 1;

    /**
     * This is the controller that the album is linked to
     */
    private IController controller;

    /**
     * slideshow will be shown in random or sequential order
     */
    public static enum SlideshowOrder {
        SEQUENTIAL, RANDOM
    }

    /**
     * indicates if slideshow is running or stopped
     */
    private static enum AlbumState {
        SLIDESHOW_RUNNING, SLIDESHOW_STOPPED
    }

    /**
     * The order of the slideshow. initially sequential
     */
    private SlideshowOrder order = SlideshowOrder.SEQUENTIAL;

    /**
     * The state of this album. initially slideshow stopped
     */
    private AlbumState state = AlbumState.SLIDESHOW_STOPPED;

    /**
     * The index in the array of the last file that was displayed in the slideshow
     */
    private int indexOfLastShownFile = 0;

    /**
     * Initializes an album with the given file name
     *
     * @param file What the album is called
     */
    public Album(File file) {
        this.me = file;
    }

    public void Open() {
        try {
            // Read file
            FileReader fstream = new FileReader(me.getPath());
            BufferedReader in = new BufferedReader(fstream);
            Boolean hasLines = true;
            while (hasLines) {
                String line = in.readLine();
                if (line == null) {
                    hasLines = false;
                } else {
                    pictures.add(new File(line));
                }
            }

            //Close the input stream
            in.close();
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
    }

    @Override
    /**
     * returns the file name of this album
     */
    public String GetName() {
        return me.getName();
    }

    @Override
    /**
     * Saves the album to a file
     */
    public void Save() {
        try {
            // Create file
            FileWriter fstream = new FileWriter(me.getPath());
            BufferedWriter out = new BufferedWriter(fstream);

            //save all the pictures to the file, one per line.
            for (File picture : pictures) {
                out.write(picture.getPath() + "\r\n");
            }

            //Close the output stream
            out.close();
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
    }

    @Override
    /**
     * Adds the given controller to this model
     */
    public void AddListener(IController controller) {
        this.controller = controller;
    }

    @Override
    /**
     * returns the list of pictures
     */
    public ArrayList<File> getPictures() {
        return this.pictures;
    }

    /**
     * Changes the slideshow order
     *
     * @param order The order to show the pictures in during the slideshow
     */
    public void setSlideshowOrder(SlideshowOrder order) {
        this.order = order;
    }

    @Override
    /**
     * Toggles the slideshow. If running, stops. If not running, starts the slideshow timer.
     */
    public void ToggleSlideshow() {
        if (state == AlbumState.SLIDESHOW_STOPPED) {

            timer = new Timer();
            this.state = AlbumState.SLIDESHOW_RUNNING;
            timer.schedule(new NextImage(), timeBetweenImages * 1000);
        } else {
            indexOfLastShownFile = -1;
            timer.cancel();
            this.state = AlbumState.SLIDESHOW_STOPPED;
        }

    }

    @Override
    public void setTimeBetweenImages(int time) {
        timeBetweenImages = time;
    }

    @Override
    public boolean AddPhoto(File photo) throws IOException {
        boolean imageFound = false;
        for (int i = 0; !imageFound && i < pictures.size(); i++) {
            imageFound = pictures.get(i).equals(photo);
        }
        if (!imageFound) {
            byte[] jpegIdBytes = {-1, -40};
            byte[] signature = new byte[2];
            FileInputStream fileInputStream = new FileInputStream(photo);
            if (fileInputStream.read(signature, 0, 2) == 2 && Arrays.equals(signature, jpegIdBytes)) {
                pictures.add(photo);
                this.createRandomizedList();
            } else {
                throw new IOException("File not a JPEG image.");
            }
        }
        return !imageFound;
    }

    @Override
    public void RemovePhoto(File photo) {
        pictures.remove(photo);
        this.createRandomizedList();
    }

    /**
     * creates an ArrayList of the pictures in random order
     */
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

    /**
     * tells the controller to show the next image. THen schedules a new timer task NextImage to continue the loop.
     */
    class NextImage extends TimerTask {
        public void run() {
            File picture;
            if (order == SlideshowOrder.SEQUENTIAL) {
                picture = getNextImageFromList(pictures);
            } else {
                picture = getNextImageFromList(randomizedPictures);
            }
            controller.ShowImage(picture);
            timer.schedule(new NextImage(), timeBetweenImages * 1000);
        }
    }

    /**
     * Retrieves the next File from a given list. If at the end of the list, gets the first item. Updates the
     * attribute indexofLastShownFile to keep track of the last file that was shown in the slideshow.
     *
     * @param list The list to get the picture from
     * @return The next image in the list
     */
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
