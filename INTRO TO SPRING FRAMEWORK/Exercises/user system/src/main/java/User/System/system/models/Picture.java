package User.System.system.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "picture")
@Table(name = "pictures")
public class Picture {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(
            name = "picture_id",
            nullable = false
    )
    private long id;

    private String title;

    private String caption;

    private String path;

    @ManyToMany

    private Set<Album> albums;

    public Picture() {
        this.albums = new HashSet<>();
    }

    public Picture(String title, String caption, String path) {
        this.title = title;
        this.caption = caption;
        this.path = path;
        this.albums = new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
