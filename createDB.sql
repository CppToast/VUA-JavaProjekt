/*
use master
go
drop database RetroGameNews
go
*/

create database RetroGameNews
go

use RetroGameNews
go



/* TABLES */

create table Users (
	IDUser int primary key identity,
	Username nvarchar(200),
	Pass nvarchar(200), -- this is horrible, don't store passwords as plain text
	IsAdmin bit
)
go

create table Categories (
	IDCategory int primary key identity,
	Name nvarchar(200)
)
go

create table Articles (
	IDArticle int primary key identity,
	Title nvarchar(200),
	Body nvarchar(4000),
	PublishedDate date,
	ImagePath nvarchar(100)
)
go

create table CategoryAssignments (
	IDCategoryAssignment int primary key identity,
	CategoryID int foreign key references Categories(IDCategory),
	ArticleID int foreign key references Articles(IDArticle)
)
go



/* PROCEDURES */

--Users
create procedure createUser
	@Username nvarchar(200),
	@Pass nvarchar(200),
	@IDUser int output
as
begin
	if exists (
		select Username from Users where Username = @Username
	)
	begin
		set @IDUser = -1
		return
	end

	insert into Users values (@Username, @Pass, 0)
	set @IDUser = SCOPE_IDENTITY()
end
go


create procedure updateUser
	@IDUser int,
	@Username nvarchar(200),
	@Pass nvarchar(200)
as
begin
	update Users
	set Username = @Username, Pass = @Pass
	where IDUser = @IDUser
end
go


create procedure deleteUser
	@IDUser int
as
begin
	delete from Users where IDUser = @IDUser
end
go


create procedure selectAllUsers
as
begin
	select * from Users
end
go


create procedure selectUser
	@IDUser int
as
begin
	select * from Users where IDUser = @IDUser
end
go


create procedure authenticateAsUser
	@Username nvarchar(200),
	@Pass nvarchar(200),
	@Success int output
as
begin
	set @Success = 0
	if exists (
		select IDUser from Users where Username = @Username and Pass = @Pass
	) 
		set @Success = 1
end
go

create procedure authenticateAsAdmin
	@Username nvarchar(200),
	@Pass nvarchar(200),
	@Success int output
as
begin
	set @Success = 0
	if exists (
		select IDUser from Users where Username = @Username and Pass = @Pass and IsAdmin = 1
	) 
		set @Success = 1
end
go



--Categories
create procedure createCategory
	@Name nvarchar(200),
	@IDCategory int output
as
begin
	insert into Categories values (@Name)
	set @IDCategory = SCOPE_IDENTITY()
end
go


create procedure updateCategory
	@IDCategory int,
	@Name nvarchar(200)
as
begin
	update Categories
	set Name = @Name
	where IDCategory = @IDCategory
end
go


create procedure deleteCategory
	@IDCategory int
as
begin
	delete from Categories where IDCategory = @IDCategory
end
go


create procedure selectAllCategories
as
begin
	select * from Categories
end
go


create procedure selectCategory
	@IDCategory int
as
begin
	select * from Categories where IDCategory = @IDCategory
end
go

create procedure searchCategoryByName
	@Name nvarchar(200)
as
begin
	select * from Categories where Name = @Name
end
go

create procedure searchCategoriesByName
	@Name nvarchar(200)
as
begin
	select * from Categories where Name like CONCAT('%', CONCAT(@Name, '%'))
end
go




--Articles
create procedure createArticle
	@Title nvarchar(200),
	@Body nvarchar(4000),
	@PublishedDate date,
	@ImagePath nvarchar(100),
	@IDArticle int output
as
begin
	insert into Articles values (@Title, @Body, @PublishedDate, @ImagePath)
	set @IDArticle = SCOPE_IDENTITY()
end
go


create procedure updateArticle
	@IDArticle int,
	@Title nvarchar(200),
	@Body nvarchar(4000),
	@PublishedDate date,
	@ImagePath nvarchar(100)
as
begin
	update Articles
	set Title = @Title, Body = @Body, PublishedDate = @PublishedDate, ImagePath = @ImagePath
	where IDArticle = @IDArticle
end
go


create procedure deleteArticle
	@IDArticle int
as
begin
	delete from Articles where IDArticle = @IDArticle
end
go


create procedure selectAllArticles
as
begin
	select * from Articles
end
go


create procedure selectArticle
	@IDArticle int
as
begin
	select * from Articles where IDArticle = @IDArticle
end
go



--CategoryAssignments
create procedure createCategoryAssignment
	@CategoryID int,
	@ArticleID int,
	@IDCategoryAssignment int output
as
begin
	insert into CategoryAssignments values (@CategoryID, @ArticleID)
	set @IDCategoryAssignment = SCOPE_IDENTITY()
end
go


create procedure updateCategoryAssignment
	@IDCategoryAssignment int,
	@CategoryID int,
	@ArticleID int	
as
begin
	update CategoryAssignments
	set CategoryID = @CategoryID, ArticleID = @ArticleID
	where IDCategoryAssignment = @IDCategoryAssignment
end
go


create procedure deleteCategoryAssignment
	@IDCategoryAssignment int
as
begin
	delete from CategoryAssignments where IDCategoryAssignment = @IDCategoryAssignment
end
go


create procedure selectAllCategoryAssignments
as
begin
	select * from CategoryAssignments
end
go


create procedure selectCategoryAssignment
	@IDCategoryAssignment int
as
begin
	select * from CategoryAssignments where IDCategoryAssignment = @IDCategoryAssignment
end
go


create procedure selectCategoryByArticle
	@IDArticle int
as
begin
	select * from Categories where IDCategory in (
		select CategoryID from CategoryAssignments where ArticleID = @IDArticle
	)
end
go


create procedure deleteAllAssignmentsOfArticle
	@IDArticle int
as
begin
	delete from CategoryAssignments where ArticleID = @IDArticle
end
go

create procedure deleteAllAssignmentsOfCategory
	@IDCategory int
as
begin
	delete from CategoryAssignments where CategoryID = @IDCategory
end
go

create procedure deleteCategoryAssignments
	@IDCategory int,
	@IDArticle int
as
begin
	delete from CategoryAssignments where CategoryID = @IDCategory and ArticleID = @IDArticle
end
go


create procedure deleteAllData as
begin
	delete from CategoryAssignments where 1=1
	delete from Users where 1=1
	delete from Categories where 1=1
	delete from Articles where 1=1
	insert into Users values ('Admin', 'admin', 1)
end
go



/* ADD ADMIN USER */
insert into Users values ('Admin', 'admin', 1)
