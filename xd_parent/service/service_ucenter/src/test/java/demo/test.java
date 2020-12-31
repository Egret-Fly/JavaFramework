package demo;

import java.util.*;

public class test {

    public static int firstUniqChar(String s) {
        int n=10;
        int[] dp = new int[n+1];
        dp[0]=0;
        dp[1]=1;
        for(int i=2;i<=n;++i)
            for(int j=0;j<i;++j){
                dp[i] += dp[j]*dp[i-j-1];
            }

        return dp[n];
    }








    public static void main(String[] args) {


        System.out.println(firstUniqChar("aadadaad"));

    }
}
