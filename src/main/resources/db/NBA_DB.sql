use NBA;


create table Conference(
    id int identity(1, 1),
    name varchar(255) not null,
    constraint Primary_Key_Constraint primary key(id)
);

create table Team(
    id int identity(1, 1),
    name varchar(255) not null,
    conference_id int,
    constraint Primary_Key_Team primary key(id),
    constraint Foreign_Key_Conference foreign key(conference_id) references Conference(id)
);

create table Player(
    id int identity(1, 1),
    name varchar(255),
    age int not null,
    position varchar(10),
    salary numeric(10, 2),
    points int,
    rebounds int,
    assists int,
    team_id int,
    constraint Primary_Key_Player primary key(id),
    constraint Foreign_Key_Team_Player foreign key(team_id) references Team(id)
);




create table Manager(
    id int identity(1, 1),
    name varchar(255),
    team_id int,
    constraint Primary_Key_Manager primary key(id),
    constraint Foreign_Key_Team foreign key(team_id) references Team(id)
);

create table Game(

    id int identity(1, 1),
    game_date datetime default current_timestamp,
    team1_id int not null,
    team2_id int not null,
    score_team1 int not null,
    score_team2 int not null,
    game_type varchar(255),
    season_id int,
    constraint Primary_Key_Game primary key(id),
    constraint ForKey_Team1 foreign key(team1_id) references Team(id),
    constraint ForKey_Team2 foreign key(team1_id) references Team(id),
    constraint ForKey_Season foreign key(season_id) references Season(id) on delete cascade

);


create table Sponsor(
    id int identity(1, 1),
    name varchar(255) not null,
    constraint Primary_Key_Sponsor primary key(id)
);

create table Season(
    id int identity(1, 1),
    name varchar(255) not null,
    constraint Primary_Key_Season primary key(id)
);

create table Found(
    id int identity(1, 1),
    team_id int not null,
    sponsor_id int not null,
    amount int,
    constraint Primary_Key_Found primary key(id),
    constraint ForKey_Team foreign key(team_id) references Team(id),
    constraint ForKey_Sponsor foreign key(sponsor_id) references Sponsor(id),
);


insert into Conference values ('East'), ('West')

insert into Team values ('Team1', 1), ('Team2', 1), ('Team3', 2), ('Team4', 1), ('Team5', 2)

insert into Manager values ('Manager1', 1), ('Manager2', null), ('Manager3', 5), ('Manager4', 2), ('Manager5', 3), ('Manager6', 4)

insert into Season values ('Winter2024')

insert into Player 
values('Player1', 24, 'PG', 12030.00, 32, 4, 3, 1), ('Player2', 34, 'SG', 21000.00, 54, 13, 2, 5), 
('Player3', 27, 'C', 32450.00, 43, 6, 5, 3), ('Player4', 24, 'SF', 43570.00, 54, 6, 23, 4), ('Player5', 24, 'PF', 51370.00, 45, 12, 23, 4),
('Player6', 32, 'C', 43880.00, 12, 3, 12, 2), ('Player7', 25, 'C', 11232.00, 11, 1, 22, 2), ('Player8', 25, 'PF', 76794.00, 54, 6, 23, 1)

insert into Game(team1_id, team2_id, score_team1, score_team2, game_type, season_id) 
values(1, 3, 121, 119, 'RS', 1), (2, 3, 121, 119, 'RS', 1), (1, 5, 123, 120, 'RS', 1)

insert into Sponsor values('Sponsor1'), ('Sponsor2')

insert into Found values(1, 2, 3000.00), (5, 2, 4000.00), (4, 1, 12000.00);

select * from Found

