package org.example;

public class TestJava {
    public static void main(String[] args) {
        int i = 0;
        while (i <= 25) {
            for (int j = 65; j <= (65 + i); j++) {
                System.out.print((char) j);
            }
            System.out.println();
            i++;
        }
    }
}
