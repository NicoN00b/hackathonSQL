SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS members (
    id int PRIMARY KEY auto_increment,
    name VARCHAR,
    contant VARCHAR,
    completed BOOLEAN,
    teamid INTEGER
);

CREATE TABLE IF NOT EXISTS teams (
    id int PRIMARY KEY auto_increment,
    title VARCHAR,
    description VARCHAR
)
