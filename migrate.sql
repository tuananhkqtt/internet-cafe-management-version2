use master
drop database INTERNETCAFEMANAGEMENT
CREATE DATABASE INTERNETCAFEMANAGEMENT
GO
USE INTERNETCAFEMANAGEMENT
--Tạo bảng Accounts
CREATE TABLE Accounts
(
	Id INT IDENTITY(1,1) PRIMARY KEY,
	Username VARCHAR(20) UNIQUE,
	Password VARCHAR(20),
	Role VARCHAR(10) CHECK (Role IN ('admin', 'employee', 'user')),
	Balance INT,
	CreatedAt DATE,
)
-- Chèn tài khoản admin
INSERT INTO Accounts (Username, Password, Role, Balance, CreatedAt)
VALUES ('admin', 'admin', 'admin', 0, '2023-12-01'),
	('employee1', 'employee1', 'employee', 0, '2023-12-01'),
	('employee2', 'employee2', 'employee', 0, '2023-12-01'),
	('employee3', 'employee3', 'employee', 0, '2023-12-01'),
	('employee4', 'employee4', 'employee', 0, '2023-12-01'),
	('employee5', 'employee5', 'employee', 0, '2023-12-01');

-- Chèn Accounts user va employee
DECLARE @CreatedAt DATE = '2023-12-01';
DECLARE @i INT = 0;
WHILE 1=1
BEGIN
	DECLARE @j INT = 0;
	WHILE @j < CAST(RAND() * 10 AS INT)
	BEGIN
		DECLARE @Username VARCHAR(20) = LEFT(CAST(NEWID() AS NVARCHAR(36)), ABS(CHECKSUM(NEWID())) % 10 + 1) + CAST(@i as VARCHAR)
		DECLARE @Password VARCHAR(20) = LEFT(CAST(NEWID() AS NVARCHAR(36)), ABS(CHECKSUM(NEWID())) % 10 + 1) + CAST(@i as VARCHAR)
		DECLARE @Balance INT = CAST(RAND() * 100000 AS INT)
		INSERT INTO Accounts (Username, Password, Role, Balance, CreatedAt)
		VALUES (@Username, @Password, 'user', @Balance, @CreatedAt)
		SET @i = @i + 1;
		SET @j = @j + 1;
	END
	SET @CreatedAt = DATEADD(DAY, 1, @CreatedAt)
	IF @CreatedAt = CAST(GETDATE() AS DATE)
    BEGIN
        BREAK;
    END
END;

-- Tạo bảng Employees
CREATE TABLE Employees
(
	Id INT IDENTITY(1,1) PRIMARY KEY,
	Name VARCHAR(100),
	AccountId int,
	Email varchar(30),
	PhoneNumber varchar(20),
	Address varchar(100),
	CONSTRAINT FK_Account_Id FOREIGN KEY (AccountId) REFERENCES Accounts(Id)
)
-- Chèn admin, employees
INSERT INTO Employees (Name, AccountId, Email, PhoneNumber, Address)
VALUES ('Nguyen Viet Nhat Admin', 1, 'admin@example.com', '123-456-7890', 'Soc Son'),
	('Employee1', 1, 'Employee1@example.com', '123-456-7890', 'Soc Son'),
	('Employee2', 2, 'Employee2@example.com', '123-456-7890', 'Ha Noi'),
	('Employee3', 3, 'Employee3@example.com', '123-456-7890', 'Ha Noi'),
	('Employee4', 4, 'Employee4@example.com', '123-456-7890', 'Ha Noi'),
	('Employee5', 5, 'Employee5@example.com', '123-456-7890', 'Ha Noi');

-- Tạo bảng Computers
CREATE TABLE Computers
(
	Id INT IDENTITY(1,1) PRIMARY KEY,
	Name VARCHAR(20) UNIQUE,
	Price INT,
	CreatedAt DATE,
)

-- Chèn Computers
SET @i = 0;
WHILE @i < 50
BEGIN
	DECLARE @Name VARCHAR(20) = 'Computer'+CAST(@i AS VARCHAR(5))
	DECLARE @Price INT = 10000;
    INSERT INTO Computers (Name, Price, CreatedAt)
    VALUES (@Name, @Price, '2023-12-01')
    SET @i = @i + 1;
END;

-- Tạo bảng Products
CREATE TABLE Products
(
	Id INT IDENTITY(1,1) PRIMARY KEY,
	Name VARCHAR(100),
	Price INT,
	Quantity INT,
	ImageUrl VARCHAR(100),
	CreatedAt DATE
)
-- Chèn Products
INSERT INTO Products (Name, Price, Quantity, ImageUrl, CreatedAt)
VALUES (N'Cơm chiên bò', 30000, 0, '/images/avatar.png', '2023-12-01'),
		(N'Cơm chiên trứng', 30000, 0, '/images/avatar.png', '2023-12-01'),
		(N'Nui xào bò', 35000, 0, '/images/loginBackground.jpg', '2023-12-01'),
		(N'Cơm trứng bò', 30000, 0, '/images/avatar.png', '2023-12-01'),
		(N'Cơm xào trứng', 30000, 0, '/images/avatar.png', '2023-12-01'),
		(N'Nui chiên bò', 35000, 0, '/images/loginBackground.jpg', '2023-12-01'),
		(N'Cơm chiên bò', 30000, 0, '/images/avatar.png', '2023-12-01'),
		(N'Cơm chiên trứng', 30000, 0, '/images/avatar.png', '2023-12-01'),
		(N'Nui xào bò', 35000, 0, '/images/loginBackground.jpg', '2023-12-01'),
		(N'Cơm chiên bò', 30000, 0, '/images/avatar.png', '2023-12-01'),
		(N'Cơm chiên trứng', 30000, 0, '/images/avatar.png', '2023-12-01'),
		(N'Nui xào bò', 35000, 0, '/images/loginBackground.jpg', '2023-12-01');

-- Tạo bảng Invoices
CREATE TABLE Invoices
(
	Id INT IDENTITY(1,1) PRIMARY KEY,
	AccountId INT, 
	ComputerId INT,
	Total INT,
	CreatedAt DATETIME,
	Status VARCHAR(20) CHECK (Status IN ('wait for accepted', 'accepted', 'served', 'is paied', 'reject')),
	CreatedBy INT,
)

-- Tạo bảng InvoiceDetails
CREATE TABLE InvoiceDetails
(
	InvoiceId INT,
	ProductId INT,
	Price INT,
	Quantity INT
)

-- Chèn InvoiceDetails
DECLARE @AccountCount INT;
SELECT @AccountCount = COUNT(Id) FROM Accounts;
DECLARE @EmployeeCount INT;
SELECT @EmployeeCount = COUNT(Id) FROM Employees;
DECLARE @ComputerCount INT;
SELECT @ComputerCount = COUNT(Id) FROM Computers;
DECLARE @ProductCount INT
SELECT @ProductCount = COUNT(Id) FROM Products;
DECLARE @InvoiceCreatedAt DATETIME = '2023-12-01 00:00:00';
DECLARE @InvoiceId INT = 1;
DECLARE @createdby INT = 1;
WHILE 1=1
BEGIN
	
	DECLARE @ProductId INT = CAST(RAND() * @ProductCount AS INT)+1;
	DECLARE @Total INT = 0
	WHILE 1=1
	BEGIN
		SELECT @Price = Price FROM Products WHERE id = @ProductId;
		DECLARE @Quantity INT = CAST(RAND() * 3 AS INT)+1;
		-- Chen InvoiceDetails
		INSERT INTO InvoiceDetails(InvoiceId, ProductId, Price, Quantity)
		VALUES (@InvoiceId, @ProductId, @Price, @Quantity)
		-- total = tong cac product
		SET @Total = @Total + @Price*@Quantity;
		-- chuyen sang mot product khac
		SET @ProductId = @ProductId + CAST(RAND() * 5 AS INT) + 1
		IF @ProductId > @ProductCount
		BEGIN
			BREAK;
		END
	END
	-- Chen Invoices
	DECLARE @status VARCHAR(20) = CASE WHEN RAND() <= 0.1 THEN 'reject' ELSE 'is paied' END
	-- neu la nhung hoa don duoc tao trong gio hien tại thi hoa don co the chua hoan thanh
	IF DATEPART(HOUR, @InvoiceCreatedAt) = DATEPART(HOUR, GETDATE())
	BEGIN
		SET @status = CASE WHEN RAND() < 0.2 THEN 'wait for accepted'
						WHEN RAND() < 0.4 THEN 'accepted'
						WHEN RAND() < 0.6 THEN 'served'
						WHEN RAND() < 0.8 THEN 'is paied'
						ELSE 'reject' END
	END
	
	INSERT INTO Invoices(AccountId, ComputerId, Total, CreatedAt, Status, CreatedBy)
	VALUES (CAST(RAND()*@AccountCount AS INT)+1, CAST(RAND()*@ComputerCount AS INT)+1, @Total, @InvoiceCreatedAt, @status, @createdby)
		
	SET @InvoiceId = @InvoiceId + 1
	-- tang thoi diem tao hoa don len mot khoang thoi gian ngau nhien
	SET @InvoiceCreatedAt = DATEADD(SECOND, CAST(RAND() * 300 AS INT), @InvoiceCreatedAt);
	-- Neu thoi diem tao hoa don lon hon thoi diem hien tai thi break

	IF @InvoiceCreatedAt > GETDATE()
	BEGIN
		BREAK;
	END
END;

select * from accounts
select * from Employees
select * from Computers
select * from Products
select * from InvoiceDetails
select * from Invoices