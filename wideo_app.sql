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
    `password` char(128) default null,   
    
    primary key(`id`)

)engine=InnoDB auto_increment=1 default charset=latin1;
    
drop table if exists `subscribe`;
create table `subscribe` (
	`id` int(32) not null auto_increment,
    `user_id` int(32) default null,
    `subscribe_id` int(32) default null,
    primary key(`id`),
    
    constraint `FK_USER_SUB`  
    foreign key (`user_id`)
    references `user`(`id`)
)engine=InnoDB auto_increment=1 default charset=latin1;
    
drop table if exists `video`;
create table `video` (
	`id` int(32) not null auto_increment,
    `url` varchar(256) default null,
    `title` varchar(64) default null,
    `description` varchar(256) default null,
    `user_id` int(16) default null,
    `photo_url` varchar(256) default null,
    `likes` int(11) default null,
    `dislikes` int(11) default null,
    `display` int(16) default null,
	`date` varchar(32) default null,
    primary key(`id`),
    
	constraint `FK_USER`
    foreign key (`user_id`)
    references `user`(`id`)
)engine=InnoDB auto_increment=1 default charset=latin1;

drop table if exists `review`;
create table `review` (
	`id` int(11) not null auto_increment,
    `comment` varchar(256) default null,
    `video_id` int(11) default null,
	`user_id` int(11) default null,
    
    primary key(`id`),
    
    constraint `FK_VIDEO`
    foreign key (`video_id`)
    references `video`(`id`),
        
	constraint `FK_USER_ID`
    foreign key (`user_id`)
    references `user`(`id`)
    
)engine=InnoDB auto_increment=1 default charset=latin1;
