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
	username	NVARCHAR(20)	NOT NULL UNIQUE,
	password	NVARCHAR(20)	NOT NULL,
	enabled		BIT				NOT NULL
);
GO
CREATE TABLE Entries.UserRoles
(
	id			INT				NOT NULL IDENTITY(1,1) PRIMARY KEY,
	userid		INT				NOT NULL,
	authority	NVARCHAR(20)	NOT NULL
	
	CONSTRAINT FK_UserRoles_Users
	FOREIGN KEY(userid)
	REFERENCES Entries.Users(id)
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
@username AS NVARCHAR(20)
AS
	SELECT username, password, enabled
	FROM Entries.Users
	WHERE username = @username;
GO
CREATE PROC Entries.GetUserAuthority
@username AS NVARCHAR(20)
AS
	SELECT US.username AS username, UR.authority AS authority
	FROM Entries.Users AS US, Entries.UserRoles AS UR 
	WHERE US.id = UR.userid AND US.username = @username;
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

INSERT INTO Entries.Users (username, password, enabled)
	VALUES (N'user1', N'password1', 1);
INSERT INTO Entries.Users (username, password, enabled)
	VALUES (N'user2', N'password2', 1);
INSERT INTO Entries.Users (username, password, enabled)
	VALUES (N'user3', N'password3', 1);
INSERT INTO Entries.UserRoles (userid, authority)
	VALUES (1, N'ROLE_USER');
INSERT INTO Entries.UserRoles (userid, authority)
	VALUES (2, N'ROLE_USER');
INSERT INTO Entries.UserRoles (userid, authority)
	VALUES (3, N'ROLE_USER');