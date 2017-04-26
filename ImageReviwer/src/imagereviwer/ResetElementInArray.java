/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagereviwer;

/**
 *
 * @author blangley
 */
public class ResetElementInArray {
    public int[][][] reset(int imageMap[][][], int[] topLeftRightBottom){
        int top = topLeftRightBottom[0];
        int left = topLeftRightBottom[1];
        int right = topLeftRightBottom[2];
        int bottom = topLeftRightBottom[3];
        
        for(int x = top; x <= bottom; x++) {
            for(int y = left;y <= right;y++){
                imageMap[x][y][0] = 0;
            }
        }
        return imageMap;        
    }
}
