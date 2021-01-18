package 杨辉三角118;

import java.util.ArrayList;
import java.util.List;

public class YangHuiTriangle {
}
class Solution {

    //个人思路：根据传入的行数，定义对应长度的数组，然后由上到下进行计算，前两行的值是固定的
    public List<List<Integer>> generate(int numRows) {

        if(numRows == 0){


            List<List<Integer>> listAll = new ArrayList<>();


            return listAll;
        }

        if (numRows == 1){
            List<Integer> list = new ArrayList<>();
            list.add(1);
            List<List<Integer>> listAll = new ArrayList<>();
            listAll.add(list);

            return listAll;
        }

        if (numRows == 2){

            List<Integer> list = new ArrayList<>();
            list.add(1);

            List<Integer> list2 = new ArrayList<>();
            list2.add(1);
            list2.add(1);

            List<List<Integer>> listAll = new ArrayList<>();
            listAll.add(list);
            listAll.add(list2);

            return listAll;

        }

        //从这一步开始计算杨辉三角

        List<Integer> list = new ArrayList<>();
        list.add(1);

        List<Integer> list2 = new ArrayList<>();
        list2.add(1);
        list2.add(1);

        List<List<Integer>> listAll = new ArrayList<>();
        listAll.add(list);
        listAll.add(list2);

        for (int i = 3; i <= numRows; i++){

            int size = listAll.size();

            //双指针
            List<Integer> newlist = new ArrayList<>();

            //拿到上一行
            List<Integer> addList = listAll.get(size - 1);

            newlist.add(1);


            for (int start = 0,next = 1; next < addList.size(); start++,next++){

                newlist.add(addList.get(start) + addList.get(next));

            }

            newlist.add(1);

            listAll.add(newlist);

        }

        return listAll;


    }
}