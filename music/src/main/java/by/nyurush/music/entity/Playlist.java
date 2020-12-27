package by.nyurush.music.entity;

import java.util.Objects;

public class Playlist extends Entity {
    private String playlistName;
    private Boolean visible;
    private User user;

    public Playlist(Integer id) {
        super(id);
    }

    public Playlist(Integer id, String playlistName, Boolean visible, User user) {
        super(id);
        this.playlistName = playlistName;
        this.visible = visible;
        this.user = user;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Playlist playlist = (Playlist) o;
        return Objects.equals(playlistName, playlist.playlistName) &&
                Objects.equals(visible, playlist.visible) &&
                Objects.equals(user, playlist.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), playlistName, visible, user);
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "playlistName='" + playlistName + '\'' +
                ", visible=" + visible +
                ", user=" + user +
                '}';
    }
}
