package algo.sorts;

import java.util.Arrays;

public class Sorts {
    public static void main(String[] args) {
        int[] arr = genArr(10);
        System.out.println(Arrays.toString(arr));
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
        arr = genArr(10);
        System.out.println(Arrays.toString(arr));
        insertSort(arr);
        System.out.println(Arrays.toString(arr));
        arr = genArr(10);
        System.out.println(Arrays.toString(arr));
        selectSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 选择排序
     * @param arr
     */
    public static void selectSort(int[] arr){
        if(arr.length<=1) return;
        for(int i = 0; i < arr.length -1; i++){
            // 查找最小值
            int minIndex = i;
            for(int j = i+1; j < arr.length; j++){
                if(arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            // 交换
            int tmp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = tmp;
        }
    }
    /**
     * 插入排序
     * @param arr
     */
    public static void insertSort(int[] arr){
        if(arr.length <= 1) return;
        for(int i = 1 ; i< arr.length ; i++){
            int value = arr[i];
            int j = i-1;
            // 查找插入的位置
            for(; j >= 0; --j){
                if(arr[j] > value){
                    arr[j+1] = arr[j]; // 数据移动
                }else{
                    break;
                }
            }
            arr[j+1] = value;   // 插入数据
        }
    }
    /**
     * 冒泡排序
     * @param arr
     */
    public static void bubbleSort(int[] arr){
        if(arr.length <= 1) return;
        for(int i = 0; i < arr.length; i++){
            // 提前退出冒泡循环的标志位
            boolean flag = false;
            for(int j = 0; j < arr.length-1-i; j++){
                if(arr[j] > arr[j+1]){// 交换
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    flag = true; // 表示有数据交换
                }
            }
            if(!flag) break; // 没有数据交换，提前退出
        }
    }
    public static int[] genArr(int length){
        if(length<=0) return null;
        int[] arr = new int[length];
        for(int i = 0; i < length; i++)
            arr[i] = (int)(Math.random()*100);
        return arr;
    }
}
