package by.nyurush.music.entity;

import java.util.Objects;

public class Track extends Entity {
    private String trackName;
    private String trackPath;
    private Integer numberOfLikes;
    private String genre;
    private Album album;

    public Track(Integer id) {
        super(id);
    }

    public Track(Integer id, String trackName, String trackPath, Integer numberOfLikes, String genre, Album album) {
        super(id);
        this.trackName = trackName;
        this.trackPath = trackPath;
        this.numberOfLikes = numberOfLikes;
        this.genre = genre;
        this.album = album;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getTrackPath() {
        return trackPath;
    }

    public void setTrackPath(String trackPath) {
        this.trackPath = trackPath;
    }

    public Integer getNumberOfLikes() {
        return numberOfLikes;
    }

    public void setNumberOfLikes(Integer numberOfLikes) {
        this.numberOfLikes = numberOfLikes;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Track track = (Track) o;
        return Objects.equals(trackName, track.trackName) &&
                Objects.equals(trackPath, track.trackPath) &&
                Objects.equals(numberOfLikes, track.numberOfLikes) &&
                Objects.equals(genre, track.genre) &&
                Objects.equals(album, track.album);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), trackName, trackPath, numberOfLikes, genre, album);
    }

    @Override
    public String toString() {
        return "Track{" +
                "trackName='" + trackName + '\'' +
                ", trackPath='" + trackPath + '\'' +
                ", numberOfLikes=" + numberOfLikes +
                ", genre='" + genre + '\'' +
                ", album=" + album +
                '}';
    }
}
