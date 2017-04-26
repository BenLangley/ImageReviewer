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
public class edgeDetect {
    int[] topLeftRightBottom = new int[4];


    /**
     *
     * @param imageMap
     * @param x
     * @param y
     * @return
     */
    public int[] detect(int imageMap[][][], int x, int y){
        
        /*   NOTE - I have got the X and Y mixed up I have not changed 
                    the names of them yet as the code seems to work I 
                    will refactor at a later date.
        */
        edgeDetect shape = new edgeDetect();
        
        int temp;
        int top = x;
        int Bottom = -1;
        int Right = -1;
        int Left = -1;
        int tBottom;
        boolean isBottom = false;
        
        tBottom = shape.searchDown(x, y, imageMap);
        if(tBottom > Bottom){
            Bottom = tBottom;
            isBottom = true;
        }
        while(isBottom){
            isBottom = false;
            Right = shape.searchRight(x, y, imageMap, Bottom);
            Left = shape.searchLeft(x, y, imageMap, Bottom);
            temp = y;
            while(temp <= Right){
                tBottom = shape.searchDown(x, temp, imageMap);
                if(tBottom > Bottom){
                    Bottom = tBottom;
                    isBottom = true;
                }
                temp++;
            }

            temp = y;
            while(temp >= Left){
                tBottom = shape.searchDown(x, temp, imageMap);
                if(tBottom > Bottom){
                    Bottom = tBottom;
                    isBottom = true;
                }
                temp--;
            }
        }
        
        shape.topLeftRightBottom[0] = top;
        shape.topLeftRightBottom[1] = Left;
        shape.topLeftRightBottom[2] = Right;
        shape.topLeftRightBottom[3] = Bottom;
        
        return shape.topLeftRightBottom; 
    }
    private int searchDown(int x, int y, int imageMap[][][]){
        boolean isEdge = false;
        while(!isEdge){
            if(1 == imageMap[x][y][0]){
                if(imageMap[x].length <= (x+1)){
                    isEdge = true;
                }
                else{
                    x += 1;
                }                
            }
            else{
                isEdge = true;
            }
        }
        return x;
    } 
    private int searchRight(int x, int y, int imageMap[][][], int tempBottom){
        int tempY = y;
        int returnY = y;
        while(x <= tempBottom){
            boolean isEdge = false;
            while(!isEdge){
                if(1 == imageMap[x][tempY][0]){
                    if(imageMap[x][tempY].length <= (tempY+1)){
                        isEdge = true;
                    }
                    else{
                        tempY += 1;
                    }                
                }
                else{
                    isEdge = true;
                }
            }
            x++;
            if(tempY > returnY){
                returnY = tempY;
            }
        }
        return returnY;
    } 
    private int searchLeft(int x, int y, int imageMap[][][], int tempBottom){        
        int tempY = y;
        int returnY = y;
        while(x <= tempBottom){
            boolean isEdge = false;
            while(!isEdge){
                if(1 == imageMap[x][tempY][0]){
                    if(imageMap[x][tempY].length >= (tempY-1)){
                        isEdge = true;
                    }
                    else{
                        tempY -= 1;
                    }                
                }
                else{
                    isEdge = true;
                }
            }
            x++;
            if(tempY < returnY){
                returnY = tempY;
            }
        }
        return returnY;
    } 
}
