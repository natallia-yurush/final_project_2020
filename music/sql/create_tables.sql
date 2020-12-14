USE 'music';


-- -----------------------------------------------------
-- Table `music`.`country`
-- -----------------------------------------------------
CREATE TABLE `music`.`country` (
  `country_code` CHAR(2) NOT NULL,
  PRIMARY KEY (`country_code`);

-- -----------------------------------------------------
-- Table `music`.`account`
-- -----------------------------------------------------
CREATE TABLE `music`.`account` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `role` ENUM('ADMIN', 'CLIENT') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE);

-- -----------------------------------------------------
-- Table `music`.`user`
-- -----------------------------------------------------
CREATE TABLE `music`.`user` (
  `account_id` INT NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `birth_date` DATE NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `subscription` TINYINT NOT NULL DEFAULT 0,
  `country_code` CHAR(2),
  PRIMARY KEY (`account_id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  INDEX `fk_country_code_idx` (`country_code` ASC) VISIBLE,
  CONSTRAINT `fk_country_code`
    FOREIGN KEY (`country_code`)
    REFERENCES `music`.`country` (`country_code`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `fk_country_id`
    FOREIGN KEY (`country_id`)
    REFERENCES `music`.`country` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE;

-- -----------------------------------------------------
-- Table `music`.`genre`
-- -----------------------------------------------------
CREATE TABLE `music`.`genre` (
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`name`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE;

-- -----------------------------------------------------
-- Table `music`.`artist`
-- -----------------------------------------------------
CREATE TABLE `music`.`artist` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));

-- -----------------------------------------------------
-- Table `music`.`album`
-- -----------------------------------------------------
CREATE TABLE `music`.`album` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `year` INT NOT NULL,
  `number_of_likes` INT NULL,
  `artist_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `artist_id_idx` (`artist_id` ASC) VISIBLE,
  CONSTRAINT `fk_album_artist_id`
    FOREIGN KEY (`artist_id`)
    REFERENCES `music`.`artist` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE;

-- -----------------------------------------------------
-- Table `music`.`track`
-- -----------------------------------------------------
CREATE TABLE `music`.`track` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `audio_path` VARCHAR(60) NOT NULL,
  `number_of_likes` INT NULL,
  `genre_id` INT NULL,
  `album_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `genre_id_idx` (`genre_id` ASC) VISIBLE,
  INDEX `album_id_idx` (`album_id` ASC) VISIBLE,
  CONSTRAINT `album_id_FK`
    FOREIGN KEY (`album_id`)
    REFERENCES `music`.`album` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE;
  CONSTRAINT `genre_name_FK`
    FOREIGN KEY (`genre_name`)
    REFERENCES `music`.`genre` (`name`)
    ON DELETE CASCADE
    ON UPDATE CASCADE;


-- -----------------------------------------------------
-- Table `music`.`artist_track`
-- -----------------------------------------------------
CREATE TABLE `music`.`artist_track` (
  `artist_id` INT NOT NULL,
  `track_id` INT NOT NULL,
  INDEX `artist_id_idx` (`artist_id` ASC) VISIBLE,
  INDEX `fk_track_id_idx` (`track_id` ASC) VISIBLE,
  CONSTRAINT `fk_artist_id`
    FOREIGN KEY (`artist_id`)
    REFERENCES `music`.`artist` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_track_id`
    FOREIGN KEY (`track_id`)
    REFERENCES `music`.`track` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- -----------------------------------------------------
-- Table `music`.`playlist`
-- -----------------------------------------------------
CREATE TABLE `music`.`playlist` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `visible` TINYINT NOT NULL DEFAULT 0,
  `user_id` INT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE,
  INDEX `fk_playlist_user_id_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_playlist_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `music`.`user` (`account_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE;

-- -----------------------------------------------------
-- Table `music`.`playlist_track`
-- -----------------------------------------------------
CREATE TABLE `music`.`playlist_track` (
  `playlist_id` INT NOT NULL,
  `track_id` INT NOT NULL,
  INDEX `fk_playlist_id_idx` (`playlist_id` ASC) VISIBLE,
  INDEX `fk_track_id_idx` (`track_id` ASC) VISIBLE,
  CONSTRAINT `fk_playlist_id`
    FOREIGN KEY (`playlist_id`)
    REFERENCES `music`.`playlist` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_track_playlist_id`
    FOREIGN KEY (`track_id`)
    REFERENCES `music`.`track` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

-- -----------------------------------------------------
-- Table `music`.`subscriber`
-- -----------------------------------------------------
CREATE TABLE `music`.`subscriber` (
  `user_id` INT NOT NULL,
  `subscriber_id` INT NOT NULL,
  INDEX `fk_subscriber_user_id_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_subscriber_id_idx` (`subscriber_id` ASC) VISIBLE,
  CONSTRAINT `fk_subscriber_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `music`.`user` (`account_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_subscriber_id`
    FOREIGN KEY (`subscriber_id`)
    REFERENCES `music`.`user` (`account_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);