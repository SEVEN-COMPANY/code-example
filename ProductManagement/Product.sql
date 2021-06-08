CREATE DATABASE ProductManagement;
GO
USE ProductManagement;
GO

CREATE TABLE PRODUCTS (
	ProductId nvarchar(20) PRIMARY KEY, 
	ProductName nvarchar(100) NOT NULL, 
	ProductDescription nvarchar(255), 
	Quantity int, 
	Price decimal, 
	ImageUrl nvarchar(100));

GO
