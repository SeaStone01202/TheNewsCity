package com.thenews.utils;

import java.util.Random;

public class CodeRandom {
    private static Random generator = new Random();

    // Đổi tên phương thức cho phù hợp hơn
    public static String randomNumeric(int numberOfCharacters) {
        StringBuilder sbBuilder = new StringBuilder();
        for (int i = 0; i < numberOfCharacters; i++) {
            // Sinh số ngẫu nhiên từ 0 đến 9
            int number = generator.nextInt(10); // nextInt(10) sẽ sinh số từ 0 đến 9
            sbBuilder.append(number); // Thêm số ngẫu nhiên vào StringBuilder
        }
        return sbBuilder.toString(); // Trả về chuỗi chứa các số ngẫu nhiên
    }
}
