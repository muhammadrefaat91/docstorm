 create database IF NOT EXISTS docstorm;
use docstorm;



CREATE TABLE IF NOT EXISTS `document` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_date` TINYBLOB NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB ;

CREATE TABLE IF NOT EXISTS `tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB ;

CREATE TABLE IF NOT EXISTS `document_tags` (
  `document_id` int(11) NOT NULL,
  `tags_id` int(11) NOT NULL,
  UNIQUE KEY `UK_ipmuna2n7pockme02x7fr6d4d` (`tags_id`),
  KEY `FK79jlxh3y4p439h000aq1ged5f` (`document_id`),
  CONSTRAINT `FK79jlxh3y4p439h000aq1ged5f` FOREIGN KEY (`document_id`) REFERENCES `document` (`id`),
  CONSTRAINT `FKdtj1985f11raye0lxeappmw1c` FOREIGN KEY (`tags_id`) REFERENCES `tag` (`id`)
) ENGINE=InnoDB  ;



CREATE TABLE IF NOT EXISTS `trans_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `language_code` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `document_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK9p7vnyrtlnvmsdmv56sfxk0fg` (`language_code`,`document_id`),
  KEY `FK4ojutkgk29xwilyw6w4wj3nww` (`document_id`),
  CONSTRAINT `FK4ojutkgk29xwilyw6w4wj3nww` FOREIGN KEY (`document_id`) REFERENCES `document` (`id`)
) ENGINE=InnoDB ;

CREATE TABLE IF NOT EXISTS `body` (
  `conten` varchar(255) NOT NULL,
  `trans_info_id` int(11) NOT NULL,
  KEY `FKix901kqgmljcgk9eqtaph4uyn` (`trans_info_id`),
  CONSTRAINT `FKix901kqgmljcgk9eqtaph4uyn` FOREIGN KEY (`trans_info_id`) REFERENCES `trans_info` (`id`)
) ENGINE=InnoDB  ;


CREATE TABLE users (
    user_id BIGINT PRIMARY KEY auto_increment,
    username VARCHAR(128) UNIQUE,
    password VARCHAR(256),
    enabled BOOL,
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

