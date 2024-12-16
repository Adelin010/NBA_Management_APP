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
    age int,
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
    age int,
    email varchar(255),
    pf int,
    constraint Primary_Key_Sponsor primary key(id)
);

create table Season(
    id int identity(1, 1),
    name varchar(255) not null,
    year int,
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

insert into Team values ('Lakers', 1), ('Chicago Bulls', 2), ('Golden State Warrios', 1), ('Boston Celtics', 2)

insert into Manager values ('Michael Gates', 45,1), ('Adelin Cojocaru', 38,2), ('Andrei Blaj', 50,3), ('Mario Lopez', 42,4)

insert into Season values ('NBA2023',2023),('NBA2024',2024)

insert into Player
values
    ('LeBron James', 39, 'PF', 1000000, 25000, 5000, 2000, 1),
    ('Stephen Curry', 36, 'PG', 900000, 21000, 4500, 3000, 2),
    ('Kawhi Leonard', 32, 'SF', 800000, 18000, 4000, 2500, 3),
    ('Giannis Antetokounmpo', 29, 'PF', 1100000, 30000, 7000, 3500, 4);
insert into Game(team1_id, team2_id, score_team1, score_team2, game_type, season_id) 
values(1, 3, 121, 119, 'RS', 1), (2, 3, 121, 119, 'RS', 1), (1, 5, 123, 120, 'RS', 1)

insert into Sponsor
values
    ('Converse', NULL, 'converse@gmail.com', 0),
    ('Cola', 45, 'cola@gmail.com', 1),
    ('Nike', 35, 'nike@gmail.com', 1),
    ('Adidas', 50, 'adidas@gmail.com', 1);

insert into Found values(1, 2, 3000.00), (5, 2, 4000.00), (4, 1, 12000.00);

select * from Found
