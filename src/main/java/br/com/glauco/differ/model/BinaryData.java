package br.com.glauco.differ.model;

import javax.persistence.*;

@Entity
public class BinaryData {

    @EmbeddedId
    private FileId id;

    @Column(name = "data")
    @Lob
    private String data;

    public FileId getId() {
        return id;
    }

    public void setId(FileId id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
