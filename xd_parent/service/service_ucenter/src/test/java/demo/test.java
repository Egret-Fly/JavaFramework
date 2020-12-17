package demo;

import java.util.*;

public class test {

    public static  boolean rotate(int[] nums) {
        int n = nums.length;
        int far = 0 ;
        for(int i=0;i<n;i++){
            if(i<=far){
                int tempfar = i + nums[i];
                far=Math.max(far , tempfar);
            }else{
                return false;
            }

        }
            return true;
    }




    public static void main(String[] args) {


        System.out.println(rotate(new int[]{3,2,1,0,4}));
    }
}
