package visualizer;

import graphics.Picture;
import sorting.algorithm.MasterSorter;
import sorting.algorithm.Pixel;
import sorting.algorithm.SortingMethod;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.Collections;

//Display class displays to the screen, the picture that is to be sorted
//Extends Canvas class since Canvas class is used to draw stuff to the screen
//Implements Runnable since we're creating different threads -- Runnable allows us to run separate threads
public class Display extends Canvas implements Runnable {
    private static final long serialVersionUID = 1L;

    private JFrame frame; //creates JFrame object named frame -- this object is needed to draw stuff to the canvas
    private Thread thread;
    private static String title = "Sorting Algorithm Visualizer";//title of the JFrame -- this title will appear on top of window
    private int width = 1000;
    private int height = 600;
    private boolean running = false, sorted = false;
    private Picture picture;

    private BufferedImage image;
    private int[] pixels;
    private Pixel[] myPixels;

    public Display(){
        frame = new JFrame();
        picture = new Picture("/images/bb.jpg");

        width = picture.getWidth();
        height = picture.getHeight();

        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
        myPixels = new Pixel[width*height];

        Dimension size = new Dimension(400,(int)(400.0/width*height));
        this.setPreferredSize(size);

        initPixels();
    }

    private void initPixels(){
        for (int i = 0; i < pixels.length; i++){
            myPixels[i]= new Pixel(picture.getPixels()[i],i);
        }
        randomizePixels();
    }//end of initPixels method

    private void randomizePixels(){

        ArrayList<Pixel> pixelList = new ArrayList<Pixel>();

        for(int i = 0; i < myPixels.length; i++) {
            pixelList.add(myPixels[i]);
        }

        Collections.shuffle(pixelList);

        for (int i = 0; i < myPixels.length; i++){
            myPixels[i]= pixelList.get(i);
        }

    }//end of randomizePixels method

    public synchronized void start(){
        running = true;
        thread = new Thread(this, "Display");
        thread.start();

    }
    public synchronized void stop(){
        running = false;
        try{
            thread.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double nanosecs = 100000000000000.0/999999999;
        double delta = 0;
        int updates = 0;
        int frames = 0;

        while(running){
            long now = System.nanoTime();
            delta += (now-lastTime)/nanosecs;
            lastTime = now;
            while(delta>=1){
            update();
            updates++;
            delta--;
            }
            render();
            frames++;

            if(System.currentTimeMillis()-timer>=1000){
                timer += 1000;
                frame.setTitle(title + " | " + frames + " fps");
                frames = 0;
                updates = 0;
            }
        }
    }
    private void render(){
        BufferStrategy buffStrat = this.getBufferStrategy();
        if (buffStrat == null){
            this.createBufferStrategy(3);
            return;
        }
        for (int i = 0; i < pixels.length; i++){
            pixels[i] = myPixels[i].color;
        }

        Graphics g = buffStrat.getDrawGraphics();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        g.dispose();
        buffStrat.show();
    }//end of render method

    private void update(){
        // Sorting methods, run as required
        // (to run an algorithm, de-comment it and comment others)
        // will need to change value of final double nanosecs for bubble sort to run efficiently

        /*
        if(!sorted){
            sorted = MasterSorter.sort(myPixels, SortingMethod.SelectionSort, 999999999);
        }
        */

        if(!sorted){
            sorted = MasterSorter.sort(myPixels, SortingMethod.InsertionSort, 999999999);
        }

        /*
        if(!sorted){
            sorted = MasterSorter.sort(myPixels, SortingMethod.BubbleSort, 999999999);
        }
        */

    }
    public static void main(String[] args){
        Display display = new Display();
        display.frame.setTitle(title);
        display.frame.add(display);
        display.frame.pack();
        display.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        display.frame.setLocationRelativeTo(null);
        display.frame.setVisible(true);

        display.start();
    }
}
