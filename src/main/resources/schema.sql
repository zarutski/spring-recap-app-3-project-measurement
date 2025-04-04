create table sensor (
	id int generated by default as identity primary key,
	name varchar(100) not null unique
);

CREATE INDEX idx_sensor_name ON sensor (name); -- just because of requirements to store name as a reference

create table measurement (
	id int generated by default as identity primary key,
	value DOUBLE precision not null,
	raining boolean not null,
	created_at TIMESTAMP not null,
	sensor varchar(100) references sensor(name)
);
