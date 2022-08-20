package com.hexi.kotlindemo.arithmetic;

public class QuickSort {
    public void quickSort(int[] origin) {
        if (origin.length <= 1) {
            return;
        }
        int standardIndex = sort(origin);
        
    }
    public int sort(int[] origin) {
        if (origin.length == 1) {
            return 0;
        }
        int standardIndex = 0;
        int targetIndex = origin.length - 1;
        while (standardIndex != targetIndex) {
            int standardV = origin[standardIndex];
            int targetV = origin[targetIndex];

            if (standardIndex < targetIndex && standardV > targetV
                    || standardIndex > targetIndex && standardV < targetV) {
                // 交换值
                exchange(origin, standardIndex, targetIndex);
                // 交换下标
                int tmp = standardIndex;
                standardIndex = targetIndex;
                targetIndex = tmp;
                if (standardIndex > targetIndex) {
                    targetIndex++;
                } else {
                    targetIndex--;
                }
            } else {
                if (standardIndex > targetIndex) {
                    targetIndex++;
                } else {
                    targetIndex--;
                }
            }
        }
        return standardIndex;
    }

    private void exchange(int[] origin, int currentIndex, int targetIndex) {
        int tmp = origin[currentIndex];
        origin[currentIndex] = origin[targetIndex];
        origin[targetIndex] = tmp;
    }
}
