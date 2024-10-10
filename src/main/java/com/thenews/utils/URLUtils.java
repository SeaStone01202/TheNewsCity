package com.thenews.utils;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class URLUtils extends SimpleTagSupport {
    private String input;

    public void setInput(String input) {
        this.input = input;
    }

    @Override
    public void doTag() throws JspException, IOException {
        if (input != null) {
            String slug = normalizeString(input); // Chuyển đổi chuỗi
            slug = slug.toLowerCase().trim()
                    .replaceAll("[^a-z0-9\\s-]", "") // Loại bỏ ký tự đặc biệt
                    .replaceAll("\\s+", "-"); // Thay thế khoảng trắng bằng dấu gạch ngang
            getJspContext().getOut().write(slug); // In ra slug
        }
    }

    // Phương thức chuyển đổi chữ có dấu sang không dấu
    private String normalizeString(String input) {
        // Bảng chuyển đổi chữ có dấu sang không dấu
        String[] accents = {"á", "à", "ả", "ã", "ạ", "â", "ấ", "ầ", "ẩ", "ẫ", "ậ",
                "ê", "ế", "ề", "ể", "ễ", "ệ", "í", "ì", "ỉ", "ĩ", "ị",
                "ó", "ò", "ỏ", "õ", "ọ", "ô", "ố", "ồ", "ổ", "ỗ", "ộ",
                "ú", "ù", "ủ", "ũ", "ụ", "ư", "ứ", "ừ", "ử", "ữ", "ự",
                "đ", "Á", "À", "Ả", "Ã", "Ạ", "Â", "Ấ", "Ầ", "Ẩ", "Ẫ",
                "Ậ", "Ê", "Ế", "Ề", "Ể", "Ễ", "Ệ", "Í", "Ì", "Ỉ", "Ĩ",
                "Ị", "Ó", "Ò", "Ỏ", "Õ", "Ọ", "Ô", "Ố", "Ồ", "Ổ", "Ỗ",
                "Ộ", "Ú", "Ù", "Ủ", "Ũ", "Ụ", "Ư", "Ứ", "Ừ", "Ử", "Ữ",
                "Ự", "Đ"};

        String[] withoutAccents = {"a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a",
                "e", "e", "e", "e", "e", "e", "i", "i", "i", "i", "i",
                "o", "o", "o", "o", "o", "o", "o", "o", "o", "o", "o",
                "u", "u", "u", "u", "u", "u", "u", "u", "u", "u", "u",
                "d", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A",
                "A", "E", "E", "E", "E", "E", "E", "I", "I", "I", "I",
                "I", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O",
                "O", "U", "U", "U", "U", "U", "U", "U", "U", "U", "U",
                "U", "D"};

        for (int i = 0; i < accents.length; i++) {
            input = input.replace(accents[i], withoutAccents[i]);
        }
        return input;
    }

}
