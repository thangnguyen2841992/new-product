package com.regain.product.model.dto;

import com.regain.product.service.friend.IFriendService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@NoArgsConstructor
public class FriendRequest {
    private Long formUserId;
    private String formFullname;
    private String formAvatar;

    private Long toUserId;
    private String toFullname;
    private String toAvatar;

    private String dateCreated;

    public Long getFormUserId() {
        return formUserId;
    }

    public void setFormUserId(Long formUserId) {
        this.formUserId = formUserId;
    }

    public String getFormFullname() {
        return formFullname;
    }

    public void setFormFullname(String formFullname) {
        this.formFullname = formFullname;
    }

    public String getFormAvatar() {
        return formAvatar;
    }

    public void setFormAvatar(String formAvatar) {
        this.formAvatar = formAvatar;
    }

    public Long getToUserId() {
        return toUserId;
    }

    public void setToUserId(Long toUserId) {
        this.toUserId = toUserId;
    }

    public String getToFullname() {
        return toFullname;
    }

    public void setToFullname(String toFullname) {
        this.toFullname = toFullname;
    }

    public String getToAvatar() {
        return toAvatar;
    }

    public void setToAvatar(String toAvatar) {
        this.toAvatar = toAvatar;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }
}
