package com.grofers.luckydraw.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "ticket_count")
    private Integer noOfRaffleTicket;

    @Column(name = "winning_date")
    private LocalDateTime winningDateTime;

    public LocalDateTime getWinningDateTime() {
        return winningDateTime;
    }

    public void setWinningDateTime(LocalDateTime winningDateTime) {
        this.winningDateTime = winningDateTime;
    }

    private String email;

    private String password;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getNoOfRaffleTicket() {
        return noOfRaffleTicket;
    }

    public void setNoOfRaffleTicket(Integer noOfRaffleTicket) {
        this.noOfRaffleTicket = noOfRaffleTicket;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
