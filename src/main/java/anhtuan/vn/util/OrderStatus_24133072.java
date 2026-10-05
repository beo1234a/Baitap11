package anhtuan.vn.util;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public final class OrderStatus_24133072 {

    public static final String NEW = "NEW";
    public static final String CONFIRMED = "CONFIRMED";
    public static final String PREPARING = "PREPARING";
    public static final String SHIPPING = "SHIPPING";
    public static final String DELIVERING = "DELIVERING";
    public static final String DELIVERED = "DELIVERED";
    public static final String CANCELLED = "CANCELLED";
    public static final String RETURNED = "RETURNED";

    private static final Map<String, String> LABELS;

    static {
        LinkedHashMap<String, String> labels = new LinkedHashMap<>();
        labels.put(NEW, "Đơn hàng mới");
        labels.put(CONFIRMED, "Đã xác nhận");
        labels.put(PREPARING, "Chuẩn bị hàng");
        labels.put(SHIPPING, "Vận chuyển");
        labels.put(DELIVERING, "Giao hàng");
        labels.put(DELIVERED, "Đã giao");
        labels.put(CANCELLED, "Đơn hàng hủy");
        labels.put(RETURNED, "Đơn hàng hoàn");
        LABELS = Collections.unmodifiableMap(labels);
    }

    private OrderStatus_24133072() {
    }

    public static Map<String, String> getLabels() {
        return LABELS;
    }

    public static boolean isValid(String status) {
        return status == null || status.isBlank() || LABELS.containsKey(status);
    }
}
