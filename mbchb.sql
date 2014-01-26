SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `mbchb` ;
CREATE SCHEMA IF NOT EXISTS `mbchb` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `mbchb` ;

-- -----------------------------------------------------
-- Table `mbchb`.`Channel`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mbchb`.`Channel` ;

CREATE TABLE IF NOT EXISTS `mbchb`.`Channel` (
  `ChannelID` INT NOT NULL AUTO_INCREMENT,
  `Title` TEXT NOT NULL,
  `Link` TEXT NULL,
  `Description` TEXT NULL,
  `LastBuild` DATETIME NULL,
  `Language` TEXT NULL,
  `UpdatePeriod` TEXT NULL,
  `UpdateFrequency` INT NULL,
  `URLGenerator` TEXT NULL,
  PRIMARY KEY (`ChannelID`))
ENGINE = InnoDB
PACK_KEYS = DEFAULT;


-- -----------------------------------------------------
-- Table `mbchb`.`ItemTable`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mbchb`.`ItemTable` ;

CREATE TABLE IF NOT EXISTS `mbchb`.`ItemTable` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Title` TEXT NULL,
  `Link` TEXT NULL,
  `Comments` TEXT NULL,
  `PubDate` TEXT NULL,
  `Creator` TEXT NULL,
  `Category` TEXT NULL,
  `Description` TEXT NULL,
  `CommentRSS` TEXT NULL,
  `ChannelID` INT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_ItemTable_1_idx` (`ChannelID` ASC),
  CONSTRAINT `fk_ItemTable_1`
    FOREIGN KEY (`ChannelID`)
    REFERENCES `mbchb`.`Channel` (`ChannelID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mbchb`.`CardSubject`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mbchb`.`CardSubject` ;

CREATE TABLE IF NOT EXISTS `mbchb`.`CardSubject` (
  `idCardSubjectID` INT NOT NULL AUTO_INCREMENT,
  `CardSubject` TEXT NULL,
  `LectureNum` TEXT NULL,
  PRIMARY KEY (`idCardSubjectID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mbchb`.`Flashcards`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mbchb`.`Flashcards` ;

CREATE TABLE IF NOT EXISTS `mbchb`.`Flashcards` (
  `CardID` INT NOT NULL AUTO_INCREMENT,
  `CardSubject` INT NULL,
  `QuestionNumber` INT NULL,
  `Question` TEXT NULL,
  `Answer` TEXT NULL,
  `QImg` BLOB NULL,
  `AImg` BLOB NULL,
  PRIMARY KEY (`CardID`),
  INDEX `fk_Flashcards_1_idx` (`CardSubject` ASC),
  CONSTRAINT `fk_Flashcards_1`
    FOREIGN KEY (`CardSubject`)
    REFERENCES `mbchb`.`CardSubject` (`idCardSubjectID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
