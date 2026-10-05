package anhtuan.vn.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
public class User_24133072 implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "Username", length = 50)
    private String username;

    @Column(name = "Password", length = 50)
    private String password;

    @Column(name = "Phone", length = 15)
    private String phone;

    @Column(name = "Fullname", length = 50)
    private String fullname;

    @Column(name = "Email", length = 150)
    private String email;

    @Column(name = "Admin")
    private Boolean admin;

    @Column(name = "Active")
    private Boolean active;

    @Column(name = "Images", length = 500)
    private String images;

    public User_24133072() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }
}