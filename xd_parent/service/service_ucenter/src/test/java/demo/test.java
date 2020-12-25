package demo;

import java.util.*;

public class test {

    public static int firstUniqChar(String s) {
        int n = s.length();
        boolean[] v = new boolean[n];
        char[] c = s.toCharArray();
        if(n==1) return 0;
        for(int i=0;i<n-1;i++){
            for(int j=i+1;j<n;j++){
                if(c[i]==c[j]&&!v[i]){
                    v[j]=true;
                }
            }
            if(!v[i]){
                return i;
            }
        }
        return -1;
    }








    public static void main(String[] args) {


        System.out.println(firstUniqChar("aadadaad"));

    }
}
