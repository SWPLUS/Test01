package com.mygdx.game;

import java.util.Date;
import java.util.List;

/**
 * Created by Ivan on 25/02/15.
 */
public class Player {

    public boolean Logged;
    public String UserId;
    public String Name;
    public String LastName;
    public String Mail;
    public String Password;
    public String Username;
    public Date Birthday;
    public String Street;
    public boolean Gender;
    public String token;
    public Date tokenExpireDate;

    public List<GameData> Data;


    public Player() {
        Logged = false;

    }

}
