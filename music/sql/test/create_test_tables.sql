USE test_music;

-- -----------------------------------------------------
-- Table `country`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `country` (
  `country_code` CHAR(2) NOT NULL,
  PRIMARY KEY (`country_code`));

-- -----------------------------------------------------
-- Table `account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `account` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `role` ENUM('ADMIN', 'CLIENT') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE);

-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user` (
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
    REFERENCES `country` (`country_code`)
    ON DELETE SET NULL
    ON UPDATE CASCADE);

-- -----------------------------------------------------
-- Table `genre`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `genre` (
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`name`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE);

-- -----------------------------------------------------
-- Table `artist`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `artist` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));

-- -----------------------------------------------------
-- Table `album`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `album` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `year` INT NOT NULL,
  `number_of_likes` INT NULL,
  `artist_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `artist_id_idx` (`artist_id` ASC) VISIBLE,
  CONSTRAINT `fk_album_artist_id`
    FOREIGN KEY (`artist_id`)
    REFERENCES `artist` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE);

-- -----------------------------------------------------
-- Table `track`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `track` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `audio_path` VARCHAR(60) NOT NULL,
  `number_of_likes` INT NULL,
  `genre_name` VARCHAR(45) NULL,
  `album_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `genre_id_idx` (`genre_name` ASC) VISIBLE,
  INDEX `album_id_idx` (`genre_name` ASC) VISIBLE,
  CONSTRAINT `album_id_FK`
    FOREIGN KEY (`album_id`)
    REFERENCES `album` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `genre_name_FK`
    FOREIGN KEY (`genre_name`)
    REFERENCES `genre` (`name`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);


-- -----------------------------------------------------
-- Table `artist_track`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `artist_track` (
  `artist_id` INT NOT NULL,
  `track_id` INT NOT NULL,
  INDEX `artist_id_idx` (`artist_id` ASC) VISIBLE,
  INDEX `fk_track_id_idx` (`track_id` ASC) VISIBLE,
  CONSTRAINT `fk_artist_id`
    FOREIGN KEY (`artist_id`)
    REFERENCES `artist` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_track_id`
    FOREIGN KEY (`track_id`)
    REFERENCES `track` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- -----------------------------------------------------
-- Table `playlist`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `playlist` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `visible` TINYINT NOT NULL DEFAULT 0,
  `user_id` INT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE,
  INDEX `fk_playlist_user_id_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_playlist_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`account_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE);

-- -----------------------------------------------------
-- Table `playlist_track`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `playlist_track` (
  `playlist_id` INT NOT NULL,
  `track_id` INT NOT NULL,
  INDEX `fk_playlist_id_idx` (`playlist_id` ASC) VISIBLE,
  INDEX `fk_track_id_idx` (`track_id` ASC) VISIBLE,
  CONSTRAINT `fk_playlist_id`
    FOREIGN KEY (`playlist_id`)
    REFERENCES `playlist` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_track_playlist_id`
    FOREIGN KEY (`track_id`)
    REFERENCES `track` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

-- -----------------------------------------------------
-- Table `subscriber`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `subscriber` (
  `user_id` INT NOT NULL,
  `subscriber_id` INT NOT NULL,
  INDEX `fk_subscriber_user_id_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_subscriber_id_idx` (`subscriber_id` ASC) VISIBLE,
  CONSTRAINT `fk_subscriber_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`account_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_subscriber_id`
    FOREIGN KEY (`subscriber_id`)
    REFERENCES `user` (`account_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);