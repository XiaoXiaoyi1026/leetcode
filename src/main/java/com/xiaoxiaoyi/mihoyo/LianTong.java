package com.xiaoxiaoyi.mihoyo;

import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

/**
 * @author xiaoxiaoyi
 * @version 1.0
 * @description 连通图
 * @date 3/19/2023 8:24 PM
 */
public class LianTong {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] matrix1 = new int[n][m];
        int[][] matrix2 = new int[n][m];
        String line;
        for (int i = 0; i < n; i++) {
            line = sc.next();
            for (int j = 0; j < m; j++) {
                if (line.charAt(j) == 'R') {
                    matrix1[i][j] = 1;
                    matrix2[i][j] = 1;
                } else if (line.charAt(j) == 'G') {
                    matrix1[i][j] = 2;
                    matrix2[i][j] = 2;
                } else {
                    matrix1[i][j] = 3;
                    matrix2[i][j] = 2;
                }
            }
        }
        System.out.println(getAns(matrix1) - getAns(matrix2));
    }

    public static int getAns(@NotNull int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] != 4) {
                    ans++;
                    ganRan(matrix, i, j, matrix[i][j]);
                }
            }
        }
        return ans;
    }

    public static void ganRan(@NotNull int[][] matrix, int row, int col, int target) {
        if (row >= matrix.length || row < 0 || col >= matrix[0].length || col < 0) {
            return;
        }
        if (matrix[row][col] == target) {
            matrix[row][col] = 4;
            ganRan(matrix, row + 1, col, target);
            ganRan(matrix, row - 1, col, target);
            ganRan(matrix, row, col + 1, target);
            ganRan(matrix, row, col - 1, target);
        }
    }

}
