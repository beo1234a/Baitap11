package anhtuan.vn.entity;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Favorites")
public class Favorite_24133072 implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FavoriteId")
    private Integer favoriteId;

    @Column(name = "LikedDate")
    private LocalDate likedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VideoId")
    private Video_24133072 video;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Username")
    private User_24133072 user;

    public Favorite_24133072() {
    }

    public Integer getFavoriteId() {
        return favoriteId;
    }

    public void setFavoriteId(Integer favoriteId) {
        this.favoriteId = favoriteId;
    }

    public LocalDate getLikedDate() {
        return likedDate;
    }

    public void setLikedDate(LocalDate likedDate) {
        this.likedDate = likedDate;
    }

    public Video_24133072 getVideo() {
        return video;
    }

    public void setVideo(Video_24133072 video) {
        this.video = video;
    }

    public User_24133072 getUser() {
        return user;
    }

    public void setUser(User_24133072 user) {
        this.user = user;
    }
}