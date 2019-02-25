package algorithm.c1_Fundamentals.s1;

public class Demo01 {
    /**
     * 欧几里得算法：
     * 自然语言描述：
     * 计算两个非负整数 p 和 q 的最大公约数：若q是0，则最大公约数为p。
     * 否则，将 p 除以 q得到余数 r，p和q 的最大公约数即为 q 和 r 的最大公约数。
     * @param p
     * @param q
     * @return 最大公约数
     */
    public static int gcd(int p, int q){
        if(q==0) return p;
        int r = p%q;
        return gcd(q,r);
    }
}
