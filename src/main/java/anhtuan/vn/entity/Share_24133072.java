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
@Table(name = "Shares")
public class Share_24133072 implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ShareId")
    private Integer shareId;

    @Column(name = "Emails", length = 50)
    private String emails;

    @Column(name = "SharedDate")
    private LocalDate sharedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Username")
    private User_24133072 user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VideoId")
    private Video_24133072 video;

    public Share_24133072() {
    }

    public Integer getShareId() {
        return shareId;
    }

    public void setShareId(Integer shareId) {
        this.shareId = shareId;
    }

    public String getEmails() {
        return emails;
    }

    public void setEmails(String emails) {
        this.emails = emails;
    }

    public LocalDate getSharedDate() {
        return sharedDate;
    }

    public void setSharedDate(LocalDate sharedDate) {
        this.sharedDate = sharedDate;
    }

    public User_24133072 getUser() {
        return user;
    }

    public void setUser(User_24133072 user) {
        this.user = user;
    }

    public Video_24133072 getVideo() {
        return video;
    }

    public void setVideo(Video_24133072 video) {
        this.video = video;
    }
}