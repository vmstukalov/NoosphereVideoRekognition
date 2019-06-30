package ru.noosphere.entities;

import javax.persistence.*;

@Entity
@Table(name = "nooSphereUrl")
public class NooSphereURL {

    private Long id;
    private String value;
    private Long video;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "_value")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Column(name = "video")
    public Long getVideo() {
        return video;
    }

    public void setVideo(Long video) {
        this.video = video;
    }
}
