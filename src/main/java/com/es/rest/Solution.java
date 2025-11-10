package com.es.rest;

import java.util.ArrayList;
import java.util.List;

class Solution {

    public static void main(String[] args) {
        int[] mountain = {1,4,3,8,5};
        List<Integer> peaks = findPeaks(mountain);
        System.out.println(peaks);
    }

    public static List<Integer> findPeaks(int[] mountain) {
        List<Integer> result = new ArrayList<>();
        if (mountain.length < 3) return result;
        int pre, curr, next;
        for (int i = 1; i < mountain.length - 1; i++) {
            pre = mountain[i -1];
            curr = mountain[i];
            next = mountain[i + 1];
            if (pre < curr && curr > next) {
                result.add(i);
            }
        }
        return result;
    }
}