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
VALUES ('admin', 'admin', 'admin', 0, '2024-01-01'),
	('employee2', 'employee2', 'employee', 0, '2024-01-01'),
	('employee3', 'employee3', 'employee', 0, '2024-01-01'),
	('employee4', 'employee4', 'employee', 0, '2024-01-01'),
	('employee5', 'employee5', 'employee', 0, '2024-01-01'),
	('employee6', 'employee6', 'employee', 0, '2024-01-01');

-- Chèn Accounts user va employee
DECLARE @CreatedAt DATE = '2024-01-01';
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
	AccountId int REFERENCES Accounts(Id) ON DELETE CASCADE,
	Email varchar(30),
	PhoneNumber varchar(20),
	Address varchar(100),
)
-- Chèn admin, employees
INSERT INTO Employees (Name, AccountId, Email, PhoneNumber, Address)
VALUES ('Nguyen Viet Nhat Admin', 1, 'admin@example.com', '123-456-7890', 'Soc Son'),
	('Employee2', 2, 'Employee2@example.com', '123-456-7890', 'Ha Noi'),
	('Employee3', 3, 'Employee3@example.com', '123-456-7890', 'Ha Noi'),
	('Employee4', 4, 'Employee4@example.com', '123-456-7890', 'Ha Noi'),
	('Employee5', 5, 'Employee5@example.com', '123-456-7890', 'Ha Noi'),
	('Employee6', 6, 'Employee6@example.com', '123-456-7890', 'Ha Noi');

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
	DECLARE @Name VARCHAR(20) = 'Computer '+CAST(@i+1 AS VARCHAR(5))
	DECLARE @Price INT = 10000;
    INSERT INTO Computers (Name, Price, CreatedAt)
    VALUES (@Name, @Price, '2024-01-01')
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
VALUES 
    ('Espresso', 30000, 0, '/images/product1.jpg', '2024-01-01'),
    ('Americano', 30000, 0, '/images/product2.jpg', '2024-01-01'),
    ('Cappuccino', 35000, 0, '/images/product3.jpg', '2024-01-01'),
    ('Latte', 35000, 0, '/images/product4.jpg', '2024-01-01'),
    ('Mocha', 40000, 0, '/images/product5.jpg', '2024-01-01'),
    ('Macchiato', 35000, 0, '/images/product6.jpg', '2024-01-01'),
    ('Ca phe sua da', 25000, 0, '/images/product7.jpg', '2024-01-01'),
    ('Tra den', 25000, 0, '/images/product8.jpg', '2024-01-01'),
    ('Tra xanh', 25000, 0, '/images/product9.jpg', '2024-01-01'),
    ('Tra oolong', 30000, 0, '/images/product10.jpg', '2024-01-01'),
    ('Tra hoa cuc', 30000, 0, '/images/product11.jpg', '2024-01-01'),
    ('Tra chanh', 25000, 0, '/images/product12.jpg', '2024-01-01'),
    ('Tra sua da', 30000, 0, '/images/product13.jpg', '2024-01-01'),
    ('Nuoc ep trai cay tuoi', 35000, 0, '/images/product14.jpg', '2024-01-01'),
    ('Smoothie trai cay', 40000, 0, '/images/product15.jpg', '2024-01-01'),
    ('Socola nong', 30000, 0, '/images/product16.jpg', '2024-01-01'),
    ('Soda Y', 25000, 0, '/images/product17.jpg', '2024-01-01'),
    ('Nuoc dua tuoi', 35000, 0, '/images/product18.jpg', '2024-01-01'),
    ('Banh mi sandwich', 30000, 0, '/images/product19.jpg', '2024-01-01'),
    ('Banh mi nuong mo hanh', 25000, 0, '/images/product20.jpg', '2024-01-01'),
    ('Banh quy va banh ngot', 20000, 0, '/images/product21.jpg', '2024-01-01'),
    ('Trai cay cat lat', 25000, 0, '/images/product22.jpg', '2024-01-01'),
    ('Mi goi hai san', 35000, 0, '/images/product23.jpg', '2024-01-01'),
    ('Khoai tay chien', 25000, 0, '/images/product24.jpg', '2024-01-01'),
    ('Popcorn', 20000, 0, '/images/product25.jpg', '2024-01-01'),
    ('Nachos voi sot cay', 30000, 0, '/images/product26.jpg', '2024-01-01'),
    ('Banh pizza mini', 35000, 0, '/images/product27.jpg', '2024-01-01'),
    ('Banh hamburger', 30000, 0, '/images/product28.jpg', '2024-01-01'),
    ('Xoi ga', 25000, 0, '/images/product29.jpg', '2024-01-01'),
    ('Nuoc tang luc co ga', 30000, CAST(RAND() * 100 AS INT), '/images/product30.jpg', '2024-01-01'),
    ('Nuoc tang luc khong ga', 30000, CAST(RAND() * 100 AS INT), '/images/product31.jpg', '2024-01-01'),
    ('Choco Caramel Frappe', 40000, 0, '/images/product32.jpg', '2024-01-01'),
    ('Matcha Latte', 35000, 0, '/images/product33.jpg', '2024-01-01'),
    ('Mi ga cay', 35000, 0, '/images/product34.jpg', '2024-01-01')

-- Tạo bảng Invoices
CREATE TABLE Invoices
(
	Id INT IDENTITY(1,1) PRIMARY KEY,
	AccountId INT REFERENCES Accounts(Id) ON DELETE CASCADE, 
	ComputerId INT REFERENCES Computers(Id) ON DELETE CASCADE,
	Total INT,
	CreatedAt DATETIME,
	Status VARCHAR(20) CHECK (Status IN ('uncompleted', 'completed', 'rejected')),
	CreatedBy INT,
)

-- Tạo bảng InvoiceDetails
CREATE TABLE InvoiceDetails
(
	InvoiceId INT REFERENCES Invoices(Id) ON DELETE CASCADE,
	ProductId INT REFERENCES Products(Id) ON DELETE CASCADE,
	Price INT,
	Quantity INT,
	Amount INT
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
DECLARE @InvoiceCreatedAt DATETIME = '2024-01-01 00:00:00';
DECLARE @InvoiceId INT = 1;
DECLARE @createdby INT = 1;
WHILE 1=1
BEGIN
	-- Tao invoice truoc vi co rang buoc (mac dinh id = @ProductId)
	INSERT INTO Invoices DEFAULT VALUES
	-- Tao hoa don chi tiet
	DECLARE @ProductId INT = CAST(RAND() * @ProductCount AS INT)+1;
	WHILE 1=1
	BEGIN
		SELECT @Price = Price FROM Products WHERE Id = @ProductId
		DECLARE @Quantity INT = CAST(RAND() * 3 AS INT)+1;
		-- Chen InvoiceDetails
		INSERT INTO InvoiceDetails
		VALUES (@InvoiceId, @ProductId, @Price, @Quantity, @Price*@Quantity)
		-- Chon mot product khac
		SET @ProductId = @ProductId + CAST(RAND() * 5 AS INT) + 1
		IF @ProductId > @ProductCount
		BEGIN
			BREAK;
		END
	END
	-- Update Invoice vua tao
	DECLARE @status VARCHAR(20) = CASE WHEN RAND() <= 0.1 THEN 'rejected' ELSE 'completed' END
	-- neu la nhung hoa don duoc tao trong gio hien tại thi hoa don co the chua hoan thanh
	IF DATEPART(HOUR, @InvoiceCreatedAt) = DATEPART(HOUR, GETDATE())
	BEGIN
		SET @status = CASE WHEN RAND() < 0.5 THEN 'uncompleted'
						WHEN RAND() < 0.4 THEN 'completed'
						ELSE 'rejected' END
	END
	DECLARE @Total INT
	SELECT @Total = SUM(Amount) FROM InvoiceDetails WHERE InvoiceId = @InvoiceId
	UPDATE Invoices
	SET AccountId=CAST(RAND()*@AccountCount AS INT)+1, ComputerId=CAST(RAND()*@ComputerCount AS INT)+1, Total=@Total, CreatedAt=@InvoiceCreatedAt, Status=@status, CreatedBy=@createdby
	WHERE Id = @InvoiceId;
		
	SET @InvoiceId = @InvoiceId + 1
	-- tang thoi diem tao hoa don len mot khoang thoi gian ngau nhien
	SET @InvoiceCreatedAt = DATEADD(SECOND, CAST(RAND() * 300 AS INT), @InvoiceCreatedAt);
	IF DATEPART(DAY, @InvoiceCreatedAt) = DATEPART(DAY, GETDATE())
	BEGIN
		SET @InvoiceCreatedAt = DATEADD(SECOND, CAST(RAND() * 300 AS INT), @InvoiceCreatedAt);
	END
	ELSE
	BEGIN
		SET @InvoiceCreatedAt = DATEADD(SECOND, CAST(RAND() * 3600 AS INT), @InvoiceCreatedAt);
	END
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