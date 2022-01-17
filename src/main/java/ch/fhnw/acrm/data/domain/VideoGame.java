package ch.fhnw.acrm.data.domain;

import javax.persistence.*;

@Entity
@Table(name = "video_game")
public class VideoGame {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "developer", nullable = false)
    private String developer;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "online_multiplayer")
    private Boolean onlineMultiplayer;

    @Column(name = "local_multiplayer")
    private Boolean localMultiplayer;

    @Column(name = "played", nullable = false)
    private Boolean played = false;

    public Boolean getPlayed() {
        return played;
    }

    public void setPlayed(Boolean played) {
        this.played = played;
    }

    public Boolean getLocalMultiplayer() {
        return localMultiplayer;
    }

    public void setLocalMultiplayer(Boolean localMultiplayer) {
        this.localMultiplayer = localMultiplayer;
    }

    public Boolean getOnlineMultiplayer() {
        return onlineMultiplayer;
    }

    public void setOnlineMultiplayer(Boolean onlineMultiplayer) {
        this.onlineMultiplayer = onlineMultiplayer;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}