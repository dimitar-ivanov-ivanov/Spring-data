package User.System.system.models;

import User.System.system.annotations.email.Email;
import User.System.system.annotations.password.Password;
import User.System.system.constants.TextConstants;
import org.hibernate.annotations.Formula;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "user")
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "user_username_unique",
                        columnNames = "username"
                )
        }
)
public class User {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(
            name = "user_id",
            nullable = false
    )
    private long id;


    @Column(
            name = "username",
            nullable = false
    )
    @Size(min = 4, max = 30, message = TextConstants.USERNAME_INCORRECT_LENGTH)
    private String username;

    @Column(nullable = false)
    @Password(
            minLength = 6,
            maxLength = 50,
            containsDigit = true,
            containsLowerCase = true,
            containsUpperCase = true,
            containsSpecialSymbol = true)
    private String password;

    @Column(nullable = false)
    @Email
    private String email;

    @Lob
    @Size(max = 1024 * 1024)
    private byte[] profilePicture;

    @Column(
            name = "registered_on"
    )
    private LocalDateTime registeredOn;

    @Column(
            name = "last_time_logged_in"
    )
    private LocalDateTime lastTimeLoggedIn;


    @Min(value = 1, message = TextConstants.AGE_TOO_LOW)
    @Max(value = 120, message = TextConstants.AGE_TOO_HIGH)
    private int age;

    @Column(
            name = "is_deleted"
    )
    private boolean isDeleted;

    @Column(
            name = "first_name",
            nullable = false
    )
    @Size(min = 2, max = 25, message = TextConstants.FIRST_NAME_INCORRECT)
    private String firstName;

    @Column(
            name = "last_name",
            nullable = false
    )
    @Size(min = 2, max = 25, message = TextConstants.LAST_NAME_INCORRECT)
    private String lastName;

    @Transient
    @Formula(value = "concat(first_name,' ', last_name)")
    private String fullName;

    @ManyToOne(optional = false)
    @JoinColumn(name = "born_town_id", referencedColumnName = "town_id")
    private Town bornTown;

    @ManyToOne()
    @JoinColumn(name = "living_town_id", referencedColumnName = "town_id")
    private Town livingTown;

    @ManyToMany
    @JoinTable(
            name = "user_friends",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "friend_id")}
    )
    private Set<User> friends;

    @ManyToMany
    @JoinTable(
            name = "user_albums",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "album_id")})
    private Set<Album> albums;

    public User() {
        this.friends = new HashSet<>();
        this.albums = new HashSet<>();
    }

    public User(@Size(min = 4, max = 30, message = TextConstants.USERNAME_INCORRECT_LENGTH) String username,
                String password,
                String email,
                @Size(max = 1024 * 1024) byte[] profilePicture,
                LocalDateTime registeredOn,
                LocalDateTime lastTimeLoggedIn,
                @Min(value = 1, message = TextConstants.AGE_TOO_LOW) @Max(value = 120, message = TextConstants.AGE_TOO_HIGH) int age,
                boolean isDeleted,
                @Size(min = 2, max = 25, message = TextConstants.FIRST_NAME_INCORRECT) String firstName,
                @Size(min = 2, max = 25, message = TextConstants.LAST_NAME_INCORRECT) String lastName) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.profilePicture = profilePicture;
        this.registeredOn = registeredOn;
        this.lastTimeLoggedIn = lastTimeLoggedIn;
        this.age = age;
        this.isDeleted = isDeleted;
        this.firstName = firstName;
        this.lastName = lastName;
        this.friends = new HashSet<>();
        this.albums = new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }

    public LocalDateTime getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(LocalDateTime registeredOn) {
        this.registeredOn = registeredOn;
    }

    public LocalDateTime getLastTimeLoggedIn() {
        return lastTimeLoggedIn;
    }

    public void setLastTimeLoggedIn(LocalDateTime lastTimeLoggedIn) {
        this.lastTimeLoggedIn = lastTimeLoggedIn;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Town getBornTown() {
        return bornTown;
    }

    public void setBornTown(Town bornTown) {
        this.bornTown = bornTown;
    }

    public Town getLivingTown() {
        return livingTown;
    }

    public void setLivingTown(Town livingTown) {
        this.livingTown = livingTown;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", profilePicture=" + Arrays.toString(profilePicture) +
                ", registeredOn=" + registeredOn +
                ", lastTimeLoggedIn=" + lastTimeLoggedIn +
                ", age=" + age +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
