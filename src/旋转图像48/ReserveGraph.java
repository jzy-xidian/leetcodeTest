package 旋转图像48;

import java.util.ArrayList;
import java.util.List;

public class ReserveGraph {
}
class Solution {

    //不能开辟空间，直接在原矩阵上将矩阵旋转90度
    public void rotate(int[][] matrix) {

        List<Integer> list = new ArrayList<>();

        //采用list进行存储，然后再重新复制
        for (int i = 0; i < matrix[0].length; i++){

            for (int j = matrix.length -1; j >= 0; j--){
                list.add(matrix[j][i]);
            }

        }

        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[0].length; j++){
                matrix[i][j] = list.get(0);
                list.remove(0);
            }
        }
    }
}