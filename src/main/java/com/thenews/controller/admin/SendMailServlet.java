    package com.thenews.controller.admin;

    import com.thenews.utils.CodeRandom;
    import com.thenews.utils.MailUtil;
    import jakarta.mail.MessagingException;
    import jakarta.servlet.ServletException;
    import jakarta.servlet.annotation.WebServlet;
    import jakarta.servlet.http.HttpServlet;
    import jakarta.servlet.http.HttpServletRequest;
    import jakarta.servlet.http.HttpServletResponse;

    import java.io.IOException;

    @WebServlet("/user/personal/sendVerificationCode")
    public class SendMailServlet extends HttpServlet {
        private static final long serialVersionUID = 1L;

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            // Lấy email từ yêu cầu
            String email = req.getParameter("email");

            // Kiểm tra nếu email null hoặc rỗng
            if (email == null || email.isEmpty()) {
                resp.setContentType("text/plain");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("Email không hợp lệ!");
                return;
            }

            // Sinh mã xác thực ngẫu nhiên
            String randomCode = CodeRandom.randomNumeric(6);

            try {
                // Gửi mã xác thực qua email
                MailUtil.sending(email, "OTP change information", "Your code is: " + randomCode);

                // Phản hồi thành công
                resp.setContentType("text/plain");
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().write("Mã xác thực đã được gửi thành công!");
                req.getSession().setAttribute("codeMail", randomCode);
            } catch (MessagingException e) {
                // Phản hồi nếu có lỗi khi gửi email
                resp.setContentType("text/plain");
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                resp.getWriter().write("Có lỗi xảy ra khi gửi mã xác thực!");
                e.printStackTrace();
            }
        }
    }
