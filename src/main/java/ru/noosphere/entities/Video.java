package ru.noosphere.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "video")
public class Video {

    private Long id;
    private String fileUrl;
    private String nooSphereUrl;
    private List<Image> imageList;
    private String path;


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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "video", fetch = FetchType.LAZY)
    @JsonManagedReference
    public List<Image> getImageList() {
        return imageList;
    }

    public void setImageList(List<Image> imageList) {
        this.imageList = imageList;
    }

    public void addImage(Image image){
        image.setVideo(this);
        this.imageList.add(image);
    }

    @Column(name = "path")
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
