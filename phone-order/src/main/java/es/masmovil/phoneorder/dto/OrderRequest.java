package es.masmovil.phoneorder.dto;

import com.google.common.base.MoreObjects;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class OrderRequest {

    @NotBlank
    private String userId;

    @NotBlank
    private String userName;

    @NotBlank
    private String userSurname;

    @Email
    @NotBlank
    private String userEmail;

    @NotEmpty
    @NotNull
    private List<String> phoneIds;

    public String getUserId() {
        return userId;
    }

    public void setUserId(final String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    public String getuserSurname() {
        return userSurname;
    }

    public void setuserSurname(final String userSurname) {
        this.userSurname = userSurname;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(final String userEmail) {
        this.userEmail = userEmail;
    }

    public List<String> getPhoneIds() {
        return phoneIds;
    }

    public void setPhoneIds(final List<String> phoneIds) {
        this.phoneIds = phoneIds;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("userId", userId)
                .add("userEmail", userEmail)
                .toString();
    }
}
