/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagereviwer;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
/**
 *
 * @author blangley
 */
public class ImageReviwer {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException{
        BufferedImage img;
        int imageMap[][][];
        img = ImageIO.read(new File("images/test.jpg"));
        imageMap = new int[img.getWidth()][img.getHeight()][1];
        /*to create new sub image*/
        BufferedImage subimage = img.getSubimage(207, 74, 50, 50);
        //ImageIO.write(subimage, "png", new File("/home/blangley/BensWork/StephImage/subTest.png"));
        /*************************/
        edgeDetect edge = new edgeDetect();

        for (int y = 0; y < img.getHeight(); y++) { // cycle through every Y pixle
            for (int x = 0; x < img.getWidth(); x++) { // cycle through every X pixle
                /* get RGB elements */
                Color mycolor = new Color(img.getRGB(x, y)); 
                int red = mycolor.getRed(); 
                int green = mycolor.getGreen();
                int blue = mycolor.getBlue();
                /********************/

                /* discounting the grey dish May need to look at this later */
                int count = 0;
                if(red > 160 && red < 180) count++;
                if(green > 160 && green < 180) count++;
                if(blue > 160 && blue < 180) count++;
                /************************************************************/
                if(count < 3){
                    imageMap[x][y][0] = 1;
                }
            }
        }
        for (int x = 0; x < imageMap.length; x++) { 
            for (int y = 0; y < imageMap[x].length; y++) {
                //System.out.print(imageMap[x][y][0] + ",");
                if(imageMap[x][y][0] == 1){
                    
                    System.out.println(edge.detect(imageMap, x, y));
                }
            }
            //System.out.println(""); 
        }
    }
}

