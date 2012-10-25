package PhotoViewer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * User: atscott
 * Date: 10/24/12
 * Time: 7:43 AM
 */
public class Album implements IAlbumModel {
    final File me;
    private ArrayList<File> pictures = new ArrayList<File>();

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

    public Album(File file) throws IOException {
        String name = file.getPath();
        if (!name.endsWith(".alb")) {
            file = new File(name + ".alb");
        }
        me = file;
        if (me.exists()) {
            me.delete();
        }
        me.createNewFile();
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


}
