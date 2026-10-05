package anhtuan.vn.model;

import java.io.Serializable;
import java.math.BigDecimal;

import anhtuan.vn.entity.Video_24133072;

public class CartItem_24133072 implements Serializable {

    private static final long serialVersionUID = 1L;

    private Video_24133072 video;
    private int quantity;

    public CartItem_24133072() {
    }

    public CartItem_24133072(Video_24133072 video, int quantity) {
        this.video = video;
        this.quantity = quantity;
    }

    public Video_24133072 getVideo() {
        return video;
    }

    public void setVideo(Video_24133072 video) {
        this.video = video;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSubtotal() {
        if (video == null || video.getPrice() == null) {
            return BigDecimal.ZERO;
        }
        return video.getPrice().multiply(BigDecimal.valueOf(quantity));
    }
}
