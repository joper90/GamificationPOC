SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `gamePoc` ;
CREATE SCHEMA IF NOT EXISTS `gamePoc` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `gamePoc` ;

-- -----------------------------------------------------
-- Table `gamePoc`.`player_information`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gamePoc`.`player_information` ;

CREATE  TABLE IF NOT EXISTS `gamePoc`.`player_information` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL ,
  `guid` VARCHAR(45) NOT NULL ,
  `avg_time` INT NOT NULL ,
  `count` INT NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gamePoc`.`achivements`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gamePoc`.`achivements` ;

CREATE  TABLE IF NOT EXISTS `gamePoc`.`achivements` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL ,
  `count` INT NOT NULL ,
  `neg_img` VARCHAR(45) NOT NULL ,
  `got_img` VARCHAR(45) NOT NULL ,
  `description` VARCHAR(45) NOT NULL ,
  `guid` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) ,
  UNIQUE INDEX `guid_UNIQUE` (`guid` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gamePoc`.`progress`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gamePoc`.`progress` ;

CREATE  TABLE IF NOT EXISTS `gamePoc`.`progress` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `player_guid` VARCHAR(45) NOT NULL ,
  `progress` INT NOT NULL ,
  `achivement_guid` VARCHAR(45) NOT NULL ,
  `completed_date` MEDIUMTEXT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gamePoc`.`stepProgress`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gamePoc`.`stepProgress` ;

CREATE  TABLE IF NOT EXISTS `gamePoc`.`stepProgress` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `step_id` VARCHAR(45) NOT NULL ,
  `step_name` VARCHAR(45) NOT NULL ,
  `step_version` VARCHAR(45) NOT NULL ,
  `step_progress` VARCHAR(45) NOT NULL ,
  `completed` TINYINT(1) NOT NULL ,
  `time_taken` VARCHAR(45) NULL ,
  `user_guid` VARCHAR(45) NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;

USE `gamePoc` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
