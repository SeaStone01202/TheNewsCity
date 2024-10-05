CREATE DATABASE newscity;

USE newscity;

CREATE TABLE Users
(
    Id       INT IDENTITY(1,1) PRIMARY KEY, -- Mã đăng nhập (tự tăng)
    Fullname NVARCHAR(255) NOT NULL,        -- Họ và tên
    [Password] NVARCHAR(255) NOT NULL,      -- Mật khẩu
    Username NVARCHAR(50) NOT NULL,
    Birthday DATE,                          -- Ngày sinh
    Gender   BIT,                           -- Giới tính (true: nam, false: nữ)
    Mobile   VARCHAR(20),                   -- Điện thoại
    Email    NVARCHAR(255),                 -- Email
    Role     BIT NOT NULL DEFAULT 0         -- Vai trò (true: quản trị, false: phóng viên)
);

CREATE TABLE Categories
(
    Id   INT IDENTITY(1,1) PRIMARY KEY, -- Mã loại tin (tự tăng)
    Name NVARCHAR(100) NOT NULL         -- Tên loại tin
);

CREATE TABLE News
(
    Id         INT IDENTITY(1,1) PRIMARY KEY,                                                -- Mã bản tin (tự tăng)
    Title      NVARCHAR(255) NOT NULL,                                                      -- Tiêu đề
    Content    NVARCHAR(MAX) NOT NULL,                                                      -- Nội dung
    Image      NVARCHAR(255),                                                               -- Hình ảnh/video
    PostedDate DATE DEFAULT GETDATE(),                                                      -- Ngày đăng
    Author     INT,                                                                         -- Tác giả (mã phóng viên)
    ViewCount  INT DEFAULT 0,                                                               -- Số lượt xem
    CategoryId INT,                                                                         -- Mã loại tin
    Home       BIT DEFAULT 0,                                                               -- Trang nhất (true: xuất hiện trên trang chủ)
    CONSTRAINT FK_News_Author FOREIGN KEY (Author) REFERENCES Users (Id) ON DELETE SET NULL, -- Khi xóa tác giả, đặt Author thành NULL
    CONSTRAINT FK_News_Category FOREIGN KEY (CategoryId) REFERENCES Categories (Id) ON DELETE SET NULL -- Khi xóa loại tin, đặt CategoryId thành NULL
);

CREATE TABLE Newsletters
(
    Id INT IDENTITY(1,1) PRIMARY KEY,      -- Mã bản tin (tự tăng)
    Email   NVARCHAR(255) UNIQUE,          -- Email nhận tin
    Enabled BIT DEFAULT 1                   -- Trạng thái (true: còn hiệu lực)
);
