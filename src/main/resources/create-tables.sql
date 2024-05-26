
CREATE TABLE  IF NOT EXISTS farm (
    name varchar(50) primary key,
    area int not null,
    version int default 0
);

CREATE TABLE  IF NOT EXISTS crop (
    id int auto_increment primary key,
    type    varchar(50) not null,
    farm varchar(50) not null,
    expected int not null,
    season int not null,
    harvested int,
    version int default 0
    );