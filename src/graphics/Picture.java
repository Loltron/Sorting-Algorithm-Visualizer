package graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Picture {
    //private instances of the class
    private int[] pixels;
    private BufferedImage image;
    private String path;

    //Constructor
    public Picture(String path){
        this.path = path;
        load();
    }//end of constructor

    private void load(){
        try{
            image = ImageIO.read(Picture.class.getResource(path));
            int w = image.getWidth();
            int h = image.getHeight();
            pixels = new int[w*h];
            image.getRGB(0,0,w,h,pixels,0,w);

        }catch (IOException e){
            e.printStackTrace();
        }
    }//end of load method

    public int[] getPixels(){
        return pixels;
    }
    public int getWidth(){
        return image.getWidth();
    }
    public int getHeight(){
        return image.getHeight();
    }
}//end of class Picture
