package by.nyurush.music.entity;

import java.util.Objects;

public class Album extends Entity {
    private String albumName;
    private Integer year;
    private Integer numberOfLikes;
    private Artist artist;

    public Album(Integer id, String albumName, Integer year, Integer numberOfLikes, Artist artist) {
        super(id);
        this.albumName = albumName;
        this.year = year;
        this.numberOfLikes = numberOfLikes;
        this.artist = artist;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getNumberOfLikes() {
        return numberOfLikes;
    }

    public void setNumberOfLikes(Integer numberOfLikes) {
        this.numberOfLikes = numberOfLikes;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Album album = (Album) o;
        return Objects.equals(albumName, album.albumName) &&
                Objects.equals(year, album.year) &&
                Objects.equals(numberOfLikes, album.numberOfLikes) &&
                Objects.equals(artist, album.artist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), albumName, year, numberOfLikes, artist);
    }
}
