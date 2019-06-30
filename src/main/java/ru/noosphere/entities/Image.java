package ru.noosphere.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "image")
public class Image {
    private Long id;
    private String path;
    private Video video;
    private List<Person> personList;
    private Boolean scanned;

    public Image() {
        this.scanned = false;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "path")
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video")
    @JsonBackReference
    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "image_person", joinColumns = @JoinColumn(name = "image"), inverseJoinColumns = @JoinColumn(name = "person"))
    @JsonManagedReference
    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    public void addPerson(Person person){personList.add(person);}

    public void addAllPersons(List<Person> persons){personList.addAll(persons);}

    @Column(name = "scanned")
    public Boolean getScanned() {
        return scanned;
    }

    public void setScanned(Boolean scanned) {
        this.scanned = scanned;
    }
}
