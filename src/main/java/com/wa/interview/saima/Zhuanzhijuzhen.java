package com.wa.interview.saima;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Zhuanzhijuzhen
 *
 * @author: wuao
 * @time: 2023/4/23 21:16
 **/
public class Zhuanzhijuzhen {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<ArrayList<Integer>> grid = new ArrayList<ArrayList<Integer>>();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] numStr = line.split(" ");
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i < numStr.length; i++) {
                list.add(Integer.parseInt(numStr[i]));
            }
            grid.add(list);
        }

        int row = grid.size();
        int col = grid.get(0).size();
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                System.out.print(grid.get(j).get(i));
                System.out.print(" ");
            }
            System.out.println();
        }


    }

}
