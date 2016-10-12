-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Drop Schema youtubeDB
-- -----------------------------------------------------
DROP DATABASE IF EXISTS youtubeDB;
-- -----------------------------------------------------
-- Schema youtubeDB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `youtubeDB` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `youtubeDB` ;

-- -----------------------------------------------------
-- Table `youtubeDB`.`users`
-- -----------------------------------------------------
CREATE TABLE `users` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `username` VARCHAR(45) CHARACTER SET 'utf8' NOT NULL ,
  `email` VARCHAR(45) NOT NULL ,
  `password` VARCHAR(200) NOT NULL ,
  `isAdmin` BOOLEAN NOT NULL ,
  `isBanned` BOOLEAN NOT NULL ,
  UNIQUE INDEX `user_name` (`username` ASC),
  UNIQUE INDEX `email` (`email` ASC),
  UNIQUE INDEX `id` (`id` ASC))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `youtubeDB`.`subscribers`
-- -----------------------------------------------------
CREATE TABLE `subscribers` (
  `user_id` INT NOT NULL ,
  `subscribe_user_id` INT NOT NULL ,
  PRIMARY KEY (`user_id`, `subscribe_user_id`),
  FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  FOREIGN KEY (`subscribe_user_id`) REFERENCES `users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `youtubeDB`.`videos`
-- -----------------------------------------------------
CREATE TABLE `videos` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `file_name` VARCHAR(100) NOT NULL,
  `title` VARCHAR(100) NOT NULL,
  `description` VARCHAR(150),
  `date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `user_id` INT NOT NULL,
  UNIQUE INDEX `video_id` (`id` ASC),
  UNIQUE INDEX `file_name` (`file_name` ASC),
  INDEX `user_id` (`user_id` ASC),
  FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `youtubeDB`.`playlist`
-- -----------------------------------------------------
CREATE TABLE `playlist` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `user_id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `isDeletable` BOOLEAN NULL,
  INDEX `user_id` (`user_id` ASC)  COMMENT '',
  FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `youtubeDB`.`playlist_videos`
-- -----------------------------------------------------
CREATE TABLE `playlist_videos` (
  `playlist_id` INT NOT NULL,
  `video_id` INT NOT NULL,
  PRIMARY KEY (`playlist_id`, `video_id`),
  INDEX `fk_playlist_has_video_video1_idx` (`video_id` ASC),
  INDEX `fk_playlist_has_video_playlist1_idx` (`playlist_id` ASC),
  CONSTRAINT `fk_playlist_has_video_playlist1`
    FOREIGN KEY (`playlist_id`)
    REFERENCES `playlist` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_playlist_has_video_video1`
    FOREIGN KEY (`video_id`)
    REFERENCES `videos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `youtubeDB`.`tags`
-- -----------------------------------------------------
CREATE TABLE `tags` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `name` VARCHAR(45) NOT NULL ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `youtubeDB`.`video_has_tags`
-- -----------------------------------------------------
CREATE TABLE `video_has_tags` (
  `video_id` INT NOT NULL,
  `tags_id` INT NOT NULL,
  PRIMARY KEY (`video_id`, `tags_id`),
  INDEX `fk_video_has_tags_tags1_idx` (`tags_id` ASC),
  INDEX `fk_video_has_tags_video1_idx` (`video_id` ASC),
  CONSTRAINT `fk_video_has_tags_video1`
    FOREIGN KEY (`video_id`)
    REFERENCES `videos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_video_has_tags_tags1`
    FOREIGN KEY (`tags_id`)
    REFERENCES `tags` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `youtubeDB`.`user_likes`
-- -----------------------------------------------------
CREATE TABLE `user_likes` (
  `user_id` INT NOT NULL ,
  `video_id` INT NOT NULL ,
  `likeStatus` TINYINT(1) NOT NULL ,
  PRIMARY KEY (`user_id`, `video_id`),
  INDEX `video_id_idx` (`video_id` ASC),
  CONSTRAINT `user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `video_id`
    FOREIGN KEY (`video_id`)
    REFERENCES `videos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `youtubeDB`.`comments`
-- -----------------------------------------------------
CREATE TABLE `comments` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `user_id` INT NULL ,
  `video_id` INT NULL ,
  `text` TEXT(500) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `user_id` (`user_id` ASC),
  INDEX `video_id` (`video_id` ASC),
  FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
 FOREIGN KEY (`video_id`)
    REFERENCES `videos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
