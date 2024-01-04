###### BÀI TẬP LỚN QUẢN LÝ DỊCH VỤ INTERNET CÔNG CỘNG

# Giới thiệu
    Phần mềm quản lý dịch vụ internet công cộng (thường gọi là cybergame, internet cafe, cyber cafe) (phía server)

## Cài đặt
    Chạy file migrate.sql trong MS SQL Server (bao gồm khởi tạo các bảng, và chèn một số bản ghi ngẫu nhiên)
    Chạy thử file src/main/java/main/Main.java, màn hình config kết nối với MS SQL Server hiện ra.
    Cấu hình các thông tin MS SQL Server (tên Server, tên Database, cổng kết nối, tài khoản, mật khẩu) trùng với thông tin trong project

## Cách sử dụng
    Chạy file src/main/java/main/Main.java

## Công nghệ được sử dụng
    Java, JavaSwing, JDBC, MS SQL Server

## Các tính năng chính
    Đăng nhập, đăng xuất, crud cơ bản với các đối tượng

### [Database](https://dbdiagram.io/d/INTERNETCAFEMANAGEMENT-6583a6e856d8064ca06c1753)