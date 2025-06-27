package com.regain.product.model.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ApproFriendRequest {

    private Long formUserId;

    private Long toUserId;

    private int isAccepted;

    private Long notificationId;

    public Long getFormUserId() {
        return formUserId;
    }

    public void setFormUserId(Long formUserId) {
        this.formUserId = formUserId;
    }

    public Long getToUserId() {
        return toUserId;
    }

    public void setToUserId(Long toUserId) {
        this.toUserId = toUserId;
    }

    public int getIsAccepted() {
        return isAccepted;
    }

    public void setIsAccepted(int isAccepted) {
        this.isAccepted = isAccepted;
    }

    public Long getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Long notificationId) {
        this.notificationId = notificationId;
    }
}
