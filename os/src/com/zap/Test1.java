package com.zap;

import org.junit.Test;

import java.nio.file.Paths;
import java.util.Arrays;

/**
 * @author 张庆品
 * @version 1.0
 * @date 2020/11/13 下午 7:43
 */

public class Test1 {
    public void need(int[][] jcSum, int[][] allocation, int[][] need) {
        for(int i = 0; i < jcSum.length; i++) {
            for (int  j = 0; j < jcSum[i].length; j++) {
                need[i][j] = jcSum[i][j] - allocation[i][j];
            }
        }
    }

    public void sysResource(int[][] allocation, int[] sumResource, int[] ky) {
        for(int i = 0; i < 3; i++) {
            int sum = 0;
            for (int j = 0; j < 4; j++) {
                sum += allocation[j][i];
            }
            ky[i] = sumResource[i] - sum;
        }
    }
    //挥手资源
    public void hc(int[][] sunResource, int[] ky,int jc) {
        for (int i = 0; i < sunResource[jc].length; i++) {
            ky[i] = sunResource[jc][i] + ky[i];
        }
    }
    //找寻当前系统的安全性
    public void jc(boolean[] js, int[][] allocation, int[][] need, int[] ky,int[][] sunResource) {
        int  i = 0;

        /**
         * 当分配完资源之后从第一台机器开启监测 如果确定 则挥手资源，继续检测
         */
        while(i < allocation.length) {
            boolean flag1 = false;
            for (int j = 0; j < allocation.length; j++) {
                boolean flag = true;
                if (!js[j]) {
                    for (int  k = 0; k < allocation[j].length; k++) {
                        //找到一个可用的资源
                        if (need[j][k] > ky[k]) {
                            flag = false;
                            break;
                        }
                    }
                } else {
                    flag  = false;
                }
                if (flag) {
                    js[j] = true;
                    hc(sunResource, ky, j);
                    i = 0;
                    flag1 = true;
                    break;
                }
            }
            if (!flag1) {
                i++;
            }
            int count = 0;
            for (int  i1 = 0; i1 < js.length; i1++) {
                if (js[i1]) {
                    count++;
                }
            }
            if (count == js.length) {
                break;
            }
        }
    }
    //模拟系统分配
    public void yhj(boolean[] js, int[][] allocation, int[][] need, int[] ky, int[] fp, int jc, int[] sumResource,int[][] jcResource) {
        for (int  i = 0; i < allocation[jc].length; i++) {
            allocation[jc][i] += fp[i];
        }
        //计算系统当前剩余资源
        this.sysResource(allocation, sumResource, ky);
        //计算当前剩余需要资源
        this.need(jcResource, allocation, need);
        //计算当前系统的安全性能
        this.jc(js, allocation, need, ky, jcResource);
    }


    @Test
    public void test1() {
        int[] sumResource = {9,3,6};
        int[][] jcSum = {{3,2,2},{6,1,3},{3,1,4},{4,2,2}};
        int[][] allocation = {{1,0,0},{6,1,2},{2,1,1},{0,0,2}};
        int[][] need = new int[4][3];
        int[] ky =  new int[3];
        this.need(jcSum, allocation, need);
        this.sysResource(allocation, sumResource, ky);
        boolean[] js = new boolean[4];
        Arrays.fill(js, false);
        int[] pf = {0,1,0};
        this.yhj(js, allocation, need, ky, pf, 0, sumResource, jcSum);
        System.out.println(Arrays.toString(js));
        System.out.println(Arrays.toString(need));
    }


    @Test
    public void test2() {
        byte i = 127;
        System.out.println(i << 1);
    }


}
