package 设计哈希映射706;

import java.util.Iterator;
import java.util.LinkedList;

public class DesignHashMap {
}
class MyHashMap {

    //设计键值对，这里不能用开放地址法，因为键的类型并不确定，还要考虑值的存入,要设计一个内部类

    private class Pair{

        private int key;
        private int value;

        public Pair(int key, int value){
            this.key = key;
            this.value = value;
        }

        public int getKey(){
            return key;
        }

        public int getValue(){
            return value;
        }


        //key的值存入后不会改变，所以只考虑value值的改变
        public void setValue(int value){

            this.value = value;

        }


    }

    private static final int BASE = 769;

    private LinkedList[] data;


    /** Initialize your data structure here. */
    public MyHashMap() {

        //先给每个0--769每个数创建一个linkedlist
        data = new LinkedList[BASE];

        for (int i = 0; i < BASE; i++){

            //每个元素都先有一个linkedList
            data[i] = new LinkedList<Pair>();
        }

    }

    /** value will always be non-negative. */
    public void put(int key, int value) {

        int h  = hash(key);
        Iterator<Pair> iterator = data[h].iterator();
        while (iterator.hasNext()){
            Pair pair = iterator.next();
            if (pair.getKey() == key){
                pair.setValue(value);
                return;
            }
        }

        data[h].addLast(new Pair(key,value));

    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {

        int h = hash(key);
        Iterator<Pair> iterator = data[h].iterator();
        while (iterator.hasNext()){
            Pair pair = iterator.next();
            if (pair.getKey() == key){
                return pair.value;
            }
        }

        //表示没有
        return -1;


    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int h = hash(key);
        Iterator<Pair> iterator = data[h].iterator();
        while (iterator.hasNext()) {
            Pair pair = iterator.next();
            if (pair.key == key) {
                data[h].remove(pair);
                return;
            }
        }



    }

    //求余函数
    public static int hash(int key){
        return key % BASE;
    }
}