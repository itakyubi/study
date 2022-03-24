package com.wa.leetcode;

import java.util.Arrays;

/**
 * ImageSmoother
 * https://leetcode-cn.com/problems/image-smoother/
 * 2022-03-24 09:04
 *
 * @author wuao
 */
public class ImageSmoother {

    public static void main(String[] args) {
        int[][] img = new int[][]{{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        int[][] img2 = new int[][]{{100, 200, 100}, {200, 50, 200}, {100, 200, 100}};

        System.out.println(Arrays.deepToString(imageSmoother2(img)));
        System.out.println(Arrays.deepToString(imageSmoother2(img2)));
    }

    private static int[][] imageSmoother2(int[][] img) {
        int[][] res = new int[img.length][img[0].length];

        for (int i = 0; i < img.length; i++) {
            for (int j = 0; j < img[0].length; j++) {
                int sum = 0, count = 0;

                for (int x = i - 1; x <= i + 1; x++) {
                    for (int y = j - 1; y <= j + 1; y++) {
                        if (x >= 0 && x < img.length && y >= 0 && y < img[0].length) {
                            sum += img[x][y];
                            count++;
                        }
                    }
                }

                res[i][j] = sum / count;
            }
        }

        return res;
    }

    private static int[][] imageSmoother(int[][] img) {
        int[][] res = new int[img.length][img[0].length];

        for (int i = 0; i < img.length; i++) {
            for (int j = 0; j < img[0].length; j++) {
                int sum = 0, count = 0;

                if (i > 0) {
                    sum += img[i - 1][j];
                    count++;

                    if (j > 0) {
                        sum += img[i - 1][j - 1];
                        count++;
                    }
                    if (j < img[0].length - 1) {
                        sum += img[i - 1][j + 1];
                        count++;
                    }
                }

                if (j > 0) {
                    sum += img[i][j - 1];
                    count++;
                }
                sum += img[i][j];
                count++;
                if (j < img[0].length - 1) {
                    sum += img[i][j + 1];
                    count++;
                }


                if (i < img.length - 1) {
                    sum += img[i + 1][j];
                    count++;

                    if (j > 0) {
                        sum += img[i + 1][j - 1];
                        count++;
                    }
                    if (j < img[0].length - 1) {
                        sum += img[i + 1][j + 1];
                        count++;
                    }
                }

                res[i][j] = sum / count;
            }
        }
        return res;
    }
}
