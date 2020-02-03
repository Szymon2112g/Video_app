drop schema if exists `wideo-app-db`;
create schema `wideo-app-db`;
use `wideo-app-db`;

set FOREIGN_KEY_CHECKS = 0;

drop table if exists `user`;
create table `user` (
	`id` int(16) not null auto_increment,
    `first_name` varchar(64) default null,
    `last_name` varchar(64) default null,
    `email` varchar(64) default null,
    `password` varchar(64) default null,
    primary key(`id`)
    )engine=InnoDB auto_increment=1 default charset=latin1;
    
drop table if exists `video`;
create table `video` (
	`id` int(32) not null auto_increment,
    `url` varchar(256) default null,
    `title` varchar(64) default null,
    `description` varchar(256) default null,
    `user_id` int(16) default null,
    primary key(`id`),
    
	constraint `FK_USER`
    foreign key (`user_id`)
    references `user`(`id`)
    )engine=InnoDB auto_increment=1 default charset=latin1;