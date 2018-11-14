package br.com.glauco.differ.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class FileId implements Serializable {

    @Column(name = "id")
    private Long id;

    @Column(name = "side")
    private SideEnum side;

    public FileId() {
    }

    public FileId(Long id, SideEnum side) {
        this.id = id;
        this.side = side;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SideEnum getSide() {
        return side;
    }

    public void setSide(SideEnum side) {
        this.side = side;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileId fileId = (FileId) o;
        return Objects.equals(id, fileId.id) &&
                side == fileId.side;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, side);
    }
}
