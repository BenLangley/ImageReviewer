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
import java.util.Arrays;
import javax.imageio.ImageIO;
/**
 *
 * @author mblangley
 */
public class ImageReviwer {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException{
        BufferedImage img;
        int imageMap[][][];
        img = ImageIO.read(new File(args[0]));
        imageMap = new int[img.getWidth()][img.getHeight()][1];
        /*to create new sub image*/
        //BufferedImage subimage = img.getSubimage(207, 74, 50, 50);
        //ImageIO.write(subimage, "png", new File("images/subTest.png"));
        /*************************/
        edgeDetect edge = new edgeDetect();
        ResetElementInArray removeEmelemnt = new ResetElementInArray();
        int imageCount = 0;
        
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
                if(imageMap[x][y][0] == 1){
                    int[] topLeftRightBottom = edge.detect(imageMap, x, y);
                    System.out.println(Arrays.toString(topLeftRightBottom));
                    BufferedImage subimage = img.getSubimage((topLeftRightBottom[0]),(topLeftRightBottom[1]), ((topLeftRightBottom[2]-topLeftRightBottom[1])), ((topLeftRightBottom[3]-topLeftRightBottom[0])));
                    ImageIO.write(subimage, "png", new File("subTest" + imageCount + ".png"));
                    imageCount++;
                    imageMap = removeEmelemnt.reset(imageMap, topLeftRightBottom);
                }
            }
        }
        
    }
}

