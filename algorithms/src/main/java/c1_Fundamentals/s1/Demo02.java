package c1_Fundamentals.s1;

/**
 * 典型的数组处理代码。
 * 以整数数组为例。
 */
public class Demo02 {

    public static int getMax(int[] arr){
        int max = arr[0];
        for(int i = 0; i < arr.length; i++)
            if(arr[i] > max) max = arr[i];
        return max;
    }

    public static double getAvg(int[] arr){
        double sum = 0.0;
        for(int i =0; i < arr.length; i++)
            sum += arr[i];
        double average = sum/arr.length;
        return average;
    }

    public static int[] copyArr(int[] arr){
        int[] brr = new int[arr.length];
        for(int i = 0; i < arr.length; i++)
            brr[i] = arr[i];
        return brr;
    }

    public static int[] reverArr(int[] arr){
        for(int i = 0; i < arr.length/2; i++){
            int temp = arr[i];
            arr[i] = arr[arr.length-1-i];
            arr[arr.length-1-i] = temp;
        }
        return arr;
    }
    // 方阵的乘积
    public static int[][] mulArr(int[][] arr,int[][] brr){
        int N = arr.length;
        int[][] crr = new int[N][N];
        for(int i = 0; i < N; i++)
            for(int j = 0; j < N; j++){
                for(int k = 0; k < N; k++)
                    crr[i][j] += arr[i][k]*brr[k][j];
            }
        return crr;
    }
}
