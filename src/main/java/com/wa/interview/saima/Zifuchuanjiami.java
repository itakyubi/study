package com.wa.interview.saima;

import java.util.Arrays;

/**
 * Zifuchuanjiami
 * 2023/5/3 4:01 下午
 *
 * @author wuao
 */
public class Zifuchuanjiami {

    public static void main(String[] args) {
        String secretKey = "DCAB";
        String myString = "I love China";
        System.out.println(Encrypt(secretKey,myString));

    }

    public static String Encrypt(String secretKey, String myString) {
        myString = myString.replace(" ", "");

        int n = secretKey.length();

        StringBuilder[] sbs = new StringBuilder[n];
        for (int i=0;i<sbs.length;i++){
            sbs[i] = new StringBuilder();
        }
        for (int i = 0; i < myString.length(); i++) {
            char c = myString.charAt(i);
            sbs[i % n].append(String.valueOf(c).toUpperCase());
        }

        Integer[][] arrays = new Integer[n][2];
        for (int i = 0; i < n; i++) {
            arrays[i][0] = secretKey.charAt(i) - 'a';
            arrays[i][1] = i;
        }
        Arrays.sort(arrays, (a, b) -> a[0] - b[0]);
        StringBuilder sb = new StringBuilder();
        int length = sbs[0].length();
        for (int i = 0; i < n; i++) {
            sb.append(sbs[arrays[i][1]]);
            if (sbs[arrays[i][1]].length()!=length){
                sb.append("E");
            }
        }
        return sb.toString();
    }
}
