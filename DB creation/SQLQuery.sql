IF DB_ID('Organizer') IS NOT NULL DROP DATABASE Organizer;
GO
CREATE DATABASE Organizer;
GO
USE Organizer;
GO
EXEC sp_changedbowner 'sa';
GO
CREATE SCHEMA Entries;
GO
CREATE TABLE Entries.Users
(
	id			INT				NOT NULL IDENTITY(1,1) PRIMARY KEY,
	name		NVARCHAR(20)	NOT NULL UNIQUE,
	pswd		NVARCHAR(20)	NOT NULL
);
GO
CREATE TABLE Entries.Notes
(
	id			int				NOT NULL IDENTITY(1,1) PRIMARY KEY,
	userid		int				NOT NULL,
	note		NVARCHAR(2000)	NOT NULL,
	noteDate	SMALLDATETIME	NOT NULL DEFAULT SYSDATETIME()

	CONSTRAINT FK_Notes_Users
	FOREIGN KEY(userid)
	REFERENCES Entries.Users(id)
);
GO
CREATE PROC Entries.GetUserCredentials
@name AS NVARCHAR(20)
AS
	SELECT id, name AS username, pswd AS password
	FROM Entries.Users
	WHERE name=@name;
GO
CREATE PROC Entries.AddNote
@userid AS int,
@note AS NVARCHAR(2000) = N'The note is empty.',
@noteDate AS SMALLDATETIME
AS
	INSERT INTO Entries.Notes (userid, note, noteDate)
	VALUES (@userid, @note, @noteDate);
GO
CREATE PROC Entries.GetLatNote
@userid AS int
AS
	SELECT TOP 1 id, note, noteDate
	FROM Entries.Notes
	WHERE userid = @userid
	ORDER BY noteDate DESC, id DESC;
GO

INSERT INTO Entries.Users (name, pswd)
VALUES (N'username', N'userpassword');