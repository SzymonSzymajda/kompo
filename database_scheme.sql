IF EXISTS(SELECT * FROM sys.databases WHERE NAME='calendar_data')
DROP DATABASE calendar_data

CREATE DATABASE calendar_data

use calendar_data

CREATE TABLE People(
	pName varchar(20),
	pSurname varchar(20),
	CONSTRAINT PK_People PRIMARY KEY(pName, pSurname)
	)

CREATE TABLE Events(
	ID_column INT NOT NULL IDENTITY(1,1) PRIMARY KEY,
	date DATETIME,
	description varchar(150),
	ownerName varchar(20),
	ownerSurname varchar(20),

	CONSTRAINT FK_Events
	FOREIGN KEY(ownerName, ownerSurname)
	REFERENCES People(pName, pSurname)
	)
