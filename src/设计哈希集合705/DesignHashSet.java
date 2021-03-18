package 设计哈希集合705;

import java.util.Iterator;
import java.util.LinkedList;

public class DesignHashSet {

    public static void main(String[] args) {
        MyHashSet myHashSet = new MyHashSet();
        myHashSet.add(1);      // set = [1]
        myHashSet.add(2);      // set = [1, 2]
        myHashSet.contains(1); // 返回 True
        myHashSet.contains(3); // 返回 False ，（未找到）
        myHashSet.add(2);      // set = [1, 2]
        myHashSet.contains(2); // 返回 True
        myHashSet.remove(2);   // set = [1]
        myHashSet.contains(2);


    }
}
//class MyHashSet {
//
//    private StringBuffer stringBuffer = new StringBuffer();
//
//
//
//    /** Initialize your data structure here. */
//    public MyHashSet() {
//
//    }
//
//    public void add(int key) {
//
//        if (stringBuffer.length() == 0){
//            stringBuffer.append(key);
//            System.out.println(stringBuffer.toString());
//            return;
//        }
//
//        for (int i = 0; i < stringBuffer.length(); i++){
//
//            if (stringBuffer.charAt(i) - '0' == key){
//                System.out.println("已经有这个元素了");
//
//                return;
//            }
//
//        }
//
//        stringBuffer.append(key);
//        System.out.println(stringBuffer.toString());
//
//
//    }
//
//    public void remove(int key) {
//
//        if (stringBuffer.length() == 0){
//            return;
//        }
//
//        for (int i = 0; i < stringBuffer.length(); i++){
//
//            if (stringBuffer.charAt(i) - '0' == key){
//
//                System.out.println("准备移除：" + stringBuffer.charAt(i));
//                System.out.println(stringBuffer.toString());
//                stringBuffer.deleteCharAt(i);
//                System.out.println("移除后的结果：" + stringBuffer.toString());
//
//            }
//
//        }
//
//        //stringBuffer.append(key);
//
//
//
//    }
//
//    /** Returns true if this set contains the specified element */
//    public boolean contains(int key) {
//
//        if (stringBuffer.length() == 0){
//            System.out.println("不包含这个元素");
//            return false;
//        }
//
//
//        for (int i = 0; i < stringBuffer.length(); i++){
//
//            if (stringBuffer.charAt(i) - '0' == key){
//                System.out.println("包含元素：" + stringBuffer.charAt(i));
//                return true;
//            }
//
//        }
//
//        System.out.println("不包含这个元素");
//        return false;
//
//    }
//}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */

//好像写的有点问题，看看题解
//链地址法：为每个哈希值维护一个链表，并将具有相同哈希值的元素都放入这一链表当中。
//开放地址法：当发现哈希值 hh 处产生冲突时，根据某种策略，从 hh 出发找到下一个不冲突的位置。例如，一种最简单的策略是，不断地检查 h+1,h+2,h+3,\ldotsh+1,h+2,h+3,… 这些整数对应的位置。
//
//再哈希法：当发现哈希冲突后，使用另一个哈希函数产生一个新的地址

//这里采用链地址法设哈希表的大小为 base，则可以设计一个简单的哈希函数：hash(x)=x mod base
//

class MyHashSet {
    private static final int BASE = 769;
    private LinkedList[] data;

    /** Initialize your data structure here. */
    public MyHashSet() {
        data = new LinkedList[BASE];
        for (int i = 0; i < BASE; ++i) {
            data[i] = new LinkedList<Integer>();
        }
    }

    public void add(int key) {

        //计算哈希值
        int h = hash(key);
        Iterator<Integer> iterator = data[h].iterator();
        while (iterator.hasNext()) {
            Integer element = iterator.next();
            if (element == key) {
                return;
            }
        }
        data[h].offerLast(key);
    }

    public void remove(int key) {
        int h = hash(key);
        Iterator<Integer> iterator = data[h].iterator();
        while (iterator.hasNext()) {
            Integer element = iterator.next();
            if (element == key) {
                data[h].remove(element);
                return;
            }
        }
    }

    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        int h = hash(key);
        Iterator<Integer> iterator = data[h].iterator();
        while (iterator.hasNext()) {
            Integer element = iterator.next();
            if (element == key) {
                return true;
            }
        }
        return false;
    }

    private static int hash(int key) {
        return key % BASE;
    }
}
