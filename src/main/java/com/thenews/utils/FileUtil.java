//package com.thenews.utils;
//
//public class FileUtil {
//    public String uploadFile(Part filePart, String uploadDir) throws IOException {
//        if (filePart == null || filePart.getSize() == 0) {
//            throw new IllegalArgumentException("No file uploaded.");
//        }
//
//        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
//        File uploadDirectory = new File(uploadDir);
//        if (!uploadDirectory.exists()) {
//            uploadDirectory.mkdirs();
//        }
//
//        String filePath = uploadDir + File.separator + fileName;
//        filePart.write(filePath);
//
//        return filePath;
//    }
//}
