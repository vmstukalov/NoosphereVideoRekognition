# NoosphereVideoRekognition
Распознавание лицо на видео в базе Ноосферы

Используется https://aws.amazon.com/ru/rekognition/

Чтобы запустить проект у себя, нужно:

1. Получить ключ api на amazon web services и вставить их в классе ru.noosphere.impl.RecognizerServiceImpl в строке 32

2. Создать базу данных MySQL, скрипт:

create schema if not exists noosphere collate utf8_general_ci;

create table if not exists image
(
	id bigint auto_increment
		primary key,
	path varchar(250) not null,
	video bigint not null,
	scanned tinyint(1) default 0 not null
);

create table if not exists image_person
(
	id bigint auto_increment
		primary key,
	image bigint not null,
	person bigint not null
);

create table if not exists nooSphereUrl
(
	id bigint auto_increment
		primary key,
	_value varchar(255) not null,
	video bigint null
);

create table if not exists person
(
	id bigint auto_increment
		primary key,
	_name varchar(255) not null,
	imdb varchar(255) null,
	celebrityId varchar(255) not null,
	constraint person_celebityId_uindex
		unique (celebrityId)
);

create table if not exists video
(
	id bigint auto_increment
		primary key,
	fileUrl varchar(255) not null,
	nooSphereUrl varchar(255) not null,
	path varchar(250) null
);

Подключение к базе данных прописывается в файле ru.noosphere.impl.App, метод dataSource()

