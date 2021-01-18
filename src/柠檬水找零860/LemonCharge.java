package 柠檬水找零860;

import java.util.HashMap;
import java.util.Map;

public class LemonCharge {
}
class Solution {

    //个人思路：按照map，按照数量进行统计即可
    //必须先是5才行
    public boolean lemonadeChange(int[] bills) {

       int five = 0,ten = 0;
       for (int bill : bills){
           if (bill == 5){
               five++;
           }else if (bill == 10){
               if (five == 0){
                   return false;
               }
               five--;
               ten++;
           }else {
               if (five > 0 && ten > 0){
                   five--;
                   ten--;
               }else if (five >= 3){
                   five -= 3;
               }
               else {
                   return false;
               }
           }
       }

        return true;

    }


}