package me.peoplexrcb.magiccrystal.utils;

public class MathUtil {
    public static int random(int min, int max) {
        int range = max - min;
        return (int) (Math.random() * range) + min;
    }
}