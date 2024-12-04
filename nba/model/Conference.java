Use PremierLeague
go
--Aufgabe1 
create function ValidatePlayerName(@PlayerName varchar(50))
returns bit
as 
begin
	return case when len(@PlayerName) >= 3 then 1 else 0 end;
end;
go
CREATE OR ALTER FUNCTION dbo.IsValidAge(@PlayerAge INT)
RETURNS BIT
AS
BEGIN
    RETURN CASE WHEN @PlayerAge BETWEEN 18 AND 40 THEN 1 ELSE 0 END;
END;
GO

create or alter procedure dbo.InsertPlayer
	@PlayerID int,
	@PlayerName varchar(50),
	@TeamID int ,
	@Nationality varchar(50),
	@Goals int,
	@PlayerAge int,
	@TransferValue int
as
begin
	if dbo.ValidatePlayerName(@PlayerName)=1 and dbo.IsValidAge(@PlayerAge)=1
	begin
		insert into Player(PlayerID,PlayerName,TeamID,Nationality,Goals,PlayerAge,TransferValue)
		values(@PlayerID,@PlayerName,@TeamID,@Nationality,@Goals,@PlayerAge,@TransferValue);
		print 'Player added!';
		end
	else
	begin
	print 'Incorrect data';
	end
end;
go
EXEC dbo.InsertPlayer 
    @PlayerID = 27,
    @PlayerName = 'Messi',
    @TeamID = 4,
    @Nationality = 'Argentina',
    @Goals = 700,
	@PlayerAge=25,
	@TransferValue = 30;
select *
from Player
go
--Aufgabe2
create or alter view Playerstats1 as
select
	p.PlayerID,
	p.PlayerName,
	ps.TeamID,
	sum(ps.Goals) as TotalGoals,
	sum(ps.MatchesPlayed) as MatchesNum
from
	PlayerStats ps
inner join
	Player p on ps.PlayerID = p.PlayerID
group by 
	p.PlayerID,p.PlayerName,ps.TeamID;
go

create OR alter Function TeamtotalGoals(@TeamID INT)
RETURNS TABLE
AS
RETURN
    SELECT
        ps.TeamID,
        SUM(ps.Goals) AS TotalGoals
    FROM
        PlayerStats ps
    WHERE
        ps.TeamID = @TeamID
    GROUP BY
        ps.TeamID;
GO
WITH TeamPerform AS (
    SELECT
        ps.TeamID,
        SUM(ps.Goals) AS TotalGoals,
        SUM(ps.MatchesPlayed) AS TotalMatches
    FROM
        PlayerStats ps
    GROUP BY
        ps.TeamID
), 
RankedTeams AS (
    SELECT
        tp.TeamID,
        tp.TotalGoals,
        tp.TotalMatches,
        CAST(tp.TotalGoals AS FLOAT) / tp.TotalMatches AS AvgGoalsPerMatch,
        RANK() OVER (PARTITION BY tp.TeamID ORDER BY CAST(tp.TotalGoals AS FLOAT) / tp.TotalMatches DESC) AS TeamRank
    FROM
        TeamPerform tp
)
SELECT
    rt.TeamID,
    t.TeamName,
    rt.TotalGoals,
    rt.TotalMatches,
    rt.AvgGoalsPerMatch,
    rt.TeamRank
FROM
    RankedTeams rt
INNER JOIN
    Teams t ON rt.TeamID = t.TeamID
ORDER BY
    rt.TeamRank;
GO



--Aufgabe3
drop table LogTable;
CREATE TABLE LogTable (
    LogID INT IDENTITY (1,1) PRIMARY KEY,
    ExecutionDateTime DATETIME,
    StatementType CHAR(1),
    TableName NVARCHAR(50),
    AffectedRows INT);
GO
CREATE OR ALTER TRIGGER tr_Users_Log
ON Player
AFTER INSERT, UPDATE, DELETE
AS
BEGIN
    DECLARE @StatementType CHAR(1);
    DECLARE @TableName NVARCHAR(50);
    DECLARE @AffectedRows INT;

    IF EXISTS (SELECT * FROM inserted)
    BEGIN
        IF EXISTS (SELECT * FROM deleted)
            SET @StatementType = 'U';
        ELSE
            SET @StatementType = 'I';
    END
    ELSE
        SET @StatementType = 'D';

    SET @TableName = 'Players';

    SET @AffectedRows = 
		CASE
			WHEN @StatementType = 'U' THEN (SELECT COUNT(*) FROM inserted)
			WHEN @StatementType = 'I' THEN (SELECT COUNT(*) FROM inserted)
			WHEN @StatementType = 'D' THEN (SELECT COUNT(*) FROM deleted)
			ELSE 0
		END;

    INSERT INTO LogTable (ExecutionDateTime, StatementType, TableName, AffectedRows)
    VALUES (GETDATE(), @StatementType, @TableName, @AffectedRows);
END;
GO

SELECT *
FROM LogTable
GO

INSERT INTO Player (PlayerID, PlayerName, TeamID, Nationality,Goals,TransferValue)
VALUES (27, 'Ronaldo', 1, 'Portugal',12,24);
UPDATE Player
SET PlayerName = 'RonALDO'
WHERE PlayerID = 27;
DELETE FROM Player WHERE PlayerID = 25;
go
--Aufgabe4

create or alter procedure ProcessTransfer(@PlayerID int,@NewTeamID int,@TransferValue int)
as 
begin
	update Player
	set TeamID = @NewTeamID
	where PlayerID = @PlayerID;
	insert into Transfer(PlayerID,NewTeamID,TransferValue)
	values(@PlayerID,@NewTeamID,@TransferValue);
end;
go
declare @PlayerID int, @NewTeamID int,@TransferValue int;
declare TransferCursor Cursor for
select PlayerID,NewTeamID, TransferValue
from Transfer
where NewTeamID is not null and TransferValue >0;
open TransferCursor
fetch next from TransferCursor into @PlayerID, @NewTeamID, @TransferValue;
while @@FETCH_STATUS=0
begin
	exec ProcessTransfer @PlayerID, @NewTeamID, @TransferValue;
    fetch next from TransferCursor into @PlayerID,@NewTeamID,@TransferValue;
end
close TransferCursor;
deallocate TransferCursor;
select * from Transfer;

