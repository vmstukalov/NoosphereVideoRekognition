package ru.noosphere.entities;

import javax.persistence.*;

@Entity
@Table(name = "video")
public class Video {

    private Long id;
    private String fileUrl;
    private String nooSphereUrl;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getNooSphereUrl() {
        return nooSphereUrl;
    }

    public void setNooSphereUrl(String nooSphereUrl) {
        this.nooSphereUrl = nooSphereUrl;
    }
}
