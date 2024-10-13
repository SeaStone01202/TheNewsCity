package com.thenews.utils;

import org.apache.commons.lang3.StringEscapeUtils; // Nhập thư viện

public class RemoteHtmlTag {
    public static String removeHtmlTags(String content) {
        String noHtml = content.replaceAll("<[^>]+>", ""); // Xóa thẻ HTML
        return StringEscapeUtils.unescapeHtml4(noHtml); // Chuyển đổi thực thể HTML
    }

    public static void main(String[] args) {
        String s = "<h2>Hồ thuỷ điện Trị An sẽ tiếp tục xả lũ lần thứ 2 trong năm nay, thời gian thực hiện từ 10h ng&agrave;y mai (12/10).</h2>\n" +
                "\n" +
                "<p>C&ocirc;ng ty Thủy điện Trị An h&ocirc;m nay (11/10) th&ocirc;ng b&aacute;o sẽ tiến h&agrave;nh xả lũ qua đập tr&agrave;n lần 2 trong năm 2024.</p>\n" +
                "\n" +
                "<p><img alt=\"W-thuy dien tri an7.jpg\" src=\"data:image/gif;base64,R0lGODlhAQABAAAAACH5BAEKAAEALAAAAAABAAEAAAICTAEAOw==\" /></p>\n" +
                "\n" +
                "<p>Thủy điện Trị An trong lần xả lũ đầu ti&ecirc;n của năm 2024. Ảnh: Ho&agrave;ng Anh</p>\n" +
                "\n" +
                "<p>Theo c&ocirc;ng ty, hiện mức nước thượng lưu hồ đạt hơn 61,3m, sắp chạm mực nước d&acirc;ng b&igrave;nh thường. Do đ&oacute;, c&ocirc;ng ty sẽ xả lũ qua đập tr&agrave;n từ 10h ng&agrave;y 12/10.</p>\n" +
                "\n" +
                "<p>Dự kiến, lưu lượng nước xả qua đập tr&agrave;n l&agrave; 160m3/s, lưu lượng nước qua tua bin ph&aacute;t điện từ 750-820m3/s. Tổng lưu lượng nước xả xuống hạ du từ 910-980m3/s.</p>\n" +
                "\n" +
                "<p>T&ugrave;y theo diễn biến mực nước hồ chứa v&agrave; mực nước hạ du s&ocirc;ng Đồng Nai, C&ocirc;ng ty Thủy điện Trị An c&oacute; thể thay đổi lưu lượng nước xả qua tr&agrave;n.</p>\n" +
                "\n" +
                "<p>Trước đ&oacute;, ng&agrave;y 23/9, C&ocirc;ng ty Thủy điện Trị An đ&atilde; xả lũ đợt đầu để điều tiết hồ chứa trong 1 tuần.</p>\n";
        System.out.println(removeHtmlTags(s)); // In kết quả
    }
}
