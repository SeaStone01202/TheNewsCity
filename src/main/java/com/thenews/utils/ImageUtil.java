package com.thenews.utils;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class ImageUtil {

    public static String saveImage(Part imagePart, ServletContext context) throws IOException {
        String fileName = getFileName(imagePart);
        if (fileName == null || !isValidImageFormat(fileName)) {
            throw new IOException("Invalid image format");
        }

        String uniqueFileName = UUID.randomUUID().toString() + "_" + fileName;

        // Lấy đường dẫn thư mục upload trong server
        String uploadDir = context.getRealPath("/") + "uploads/";
        Path filePath = Paths.get(uploadDir, uniqueFileName);

        // Tạo thư mục nếu chưa tồn tại
        if (!Files.exists(filePath.getParent())) {
            Files.createDirectories(filePath.getParent());
        }

        // Lưu file vào thư mục trên server
        Files.copy(imagePart.getInputStream(), filePath);

        return uniqueFileName; // Trả về tên file đã lưu để lưu vào database
    }

    private static String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 2, content.length() - 1);
            }
        }
        return null;
    }

    private static boolean isValidImageFormat(String fileName) {
        String[] validExtensions = { "jpg", "jpeg", "png", "gif" };
        String fileExtension = fileName.substring(fileName.lastIndexOf('.') + 1);
        for (String ext : validExtensions) {
            if (ext.equalsIgnoreCase(fileExtension)) {
                return true;
            }
        }
        return false;
    }
}
