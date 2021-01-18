package 距离顺序排列矩阵单元格1030;

import java.util.*;

public class SortAllCell {

    public static void main(String[] args) {

        Solution solution = new Solution();
        int[][] result = solution.allCellsDistOrder(1,2,0,0);
        for (int i = 0; i < result.length; i++){
            System.out.println(Arrays.toString(result[i]));
        }
    }
}
class Solution {
    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {

        int[][] result = new int[R*C][2];

        //先讨论为空和只有一格的情况
        if(R == 0 || C == 0){
            return null;
        }

        if (R == 1 && C == 1){
            result[0][0] =0;
            result[0][1] =0;
            return result;
        }

        //存储距离
        Map<Integer, List<int[]>> map = new TreeMap<>();

        for (int i = 0; i < R; i++){

            for (int j = 0; j < C; j++){

                int distance = Math.abs((r0 - i)) + Math.abs((c0 - j));

                int[] save = {i , j};

                if (map.containsKey(distance)){

                    List<int[]> list = map.get(distance);
                    list.add(save);
                    map.put(distance,list);

                }else {

                    List<int[]> list = new ArrayList<>();
                    list.add(save);
                    map.put(distance,list);

                }

            }

        }

        List<int[]> list = new ArrayList<>();

        for (Map.Entry<Integer, List<int[]>> entry : map.entrySet()){
            list.addAll(entry.getValue());

        }

        for (int i = 0; i < R*C; i++){
            result[i] = list.get(i);
        }

        return result;


    }

    //看了下答案，自己写的什么垃圾哦

    public int[][] allCellsDistOrder2(int R, int C, int r0, int c0){

        int[][] ret = new int[R * C][];

        for (int i = 0; i < R; i ++){
            for (int j = 0; j < C; j++) {
                ret[i * C + j] = new int[]{i, j};
            }

        }

        Arrays.sort(ret, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return (Math.abs(a[0] - r0) + Math.abs(a[1] - c0)) - (Math.abs(b[0] - r0) + Math.abs(b[1] - c0));
            }
        });
        return ret;


    }
}