CREATE SCHEMA `album` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `album`.`user` (
  `ID` INT NOT NULL,
  `USERNAME` VARCHAR(45) NOT NULL,
  `PASSWORD` VARCHAR(45) NOT NULL,
  `EMAIL` VARCHAR(45) NULL,
  `CREATETIME` VARCHAR(45) NULL,
  `UPDATETIME` VARCHAR(45) NULL)
ENGINE = InnoDB;


CREATE TABLE `album`.`album` (
  `ID` INT NOT NULL,
  `NAME` VARCHAR(45) NOT NULL,
  `DESCRIPTION` VARCHAR(200) NULL,
  `USERID` INT NOT NULL,
  `COVER` INT NOT NULL,
  `CREATETIME` VARCHAR(45) NOT NULL,
  `UPDATETIME` VARCHAR(45) NULL,
  `VISTOR` VARCHAR(500) NULL);

  
CREATE TABLE `album`.`photo` (
  `ID` INT NOT NULL,
  `ALBUM_ID` INT NOT NULL,
  `NAME` VARCHAR(45) NOT NULL,
  `DESCRIPTION` VARCHAR(200) NULL,
  `COMMENT` VARCHAR(100) NULL,
  `CREATETIME` VARCHAR(45) NULL,
  `UPDATETIME` VARCHAR(45) NULL,
  `VISITOR` VARCHAR(1000) NULL,
  PRIMARY KEY (`ID`));

