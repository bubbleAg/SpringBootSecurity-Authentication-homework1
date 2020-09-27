package com.koro.firstloginspringsecurity.model;

public class AppUser {

    private String nick;
    private int authCount;

    public AppUser(String nick, int authCount) {
        this.nick = nick;
        this.authCount = authCount;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public int getAuthCount() {
        return authCount;
    }

    public void setAuthCount(int authCount) {
        this.authCount = authCount;
    }
}
