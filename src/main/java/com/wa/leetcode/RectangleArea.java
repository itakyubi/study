package com.wa.leetcode;

/**
 * RectangleArea
 * 2020-07-31 17:26
 *
 * @author wuao
 */
public class RectangleArea {

    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int area1 = (C - A) * (D - B);
        int area2 = (G - E) * (H - F);
        int area3 = 0;

        int x1 = Math.max(A, E);
        int y1 = Math.max(B, F);
        int x2 = Math.min(C, G);
        int y2 = Math.min(D, H);
        if (x1 < x2 && y1 < y2) {
            area3 = (y2 - y1) * (x2 - x1);
        }

        return area1 + area2 - area3;
    }
}
