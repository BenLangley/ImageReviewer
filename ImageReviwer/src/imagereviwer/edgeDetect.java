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
    int topLeftRightBottom;
    int top;
    int left;
    int right;
    int bottom;

    /**
     *
     * @param imageMap
     * @param x
     * @param y
     * @return
     */
    public int detect(int imageMap[][][], int x, int y){
        edgeDetect shape = new edgeDetect();
        int searchRight = shape.searchRight(x, y, imageMap);
        shape.top = x;
        
        int bottom = shape.searchDown(x, y, imageMap);
        System.out.println(searchRight);
        
        for (int i = x; i < searchRight; i++) {
            int temp = shape.searchDown(i, y, imageMap);
            if(temp > bottom){
                bottom = temp;
            }
        }
        System.out.println(bottom);
        System.out.println("the bottom is " + bottom);
        //System.exit(bottom);
                
        return searchRight; 
    }
     public int searchDown(int x, int y, int imageMap[][][]){
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
    public int searchRight(int x, int y, int imageMap[][][]){
        boolean isEdge = false;
        while(!isEdge){
            if(1 == imageMap[x][y][0]){
                if(imageMap[x][y].length <= (y+1)){
                    isEdge = true;
                }
                else{
                    y += 1;
                }                
            }
            else{
                isEdge = true;
            }
        }
        return y;
    } 
    public int searchLeft(int x, int y, int imageMap[][][]){
        boolean isEdge = false;
        while(!isEdge){
            if(1 == imageMap[x][y][0]){
                if(0 >= (y-1)){
                    isEdge = true;
                }
                else{
                    y -= 1;
                }                
            }
            else{
                isEdge = true;
            }
        }
        return y;
    } 
}
