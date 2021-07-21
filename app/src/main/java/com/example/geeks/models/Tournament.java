package com.example.geeks.models;

import android.util.Log;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.lang.reflect.Array;
import java.util.Date;


@ParseClassName("Tournament")
public class Tournament extends ParseObject {

    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_IMAGE = "gameImage";
    public static final String KEY_USER = "user";
    public static final String KEY_GAME_NAME = "gameName";
    public static final String KEY_NUM_OF_PLAYERS = "num_of_players";
    public static final String KEY_REGISTERED_PLAYERS = "registered_users";
    public static final String KEY_GAME_DATE = "game_day";
    public static final String KEY_END_LINE = "end_line";
    public static final String KEY_PLATFORM = "platform";
    public static final String KEY_EXTRA_DETAILS = "extra_details";

    // Make getters and setters

    public String getGameName(){
        return getString(KEY_GAME_NAME);
    }

    public void setGameName(String gameName){
        put(KEY_GAME_NAME, gameName);
    }

    public String getDescription(){
        return getString(KEY_DESCRIPTION);
    }

    public void setDescription(String description){
        put(KEY_DESCRIPTION, description);
    }

    public ParseFile getImage(){
        return getParseFile(KEY_IMAGE);
    }

    public void setImage(ParseFile image){ put(KEY_IMAGE, image); }

    public ParseUser getUser(){ return getParseUser(KEY_USER); }

    public void setUser(ParseUser user){ put(KEY_USER, user); }

    public Integer getNumberofPlayers(){ return getInt(KEY_NUM_OF_PLAYERS); }

    public void setNumberofPlayers(Integer num_of_players){ put(KEY_NUM_OF_PLAYERS, num_of_players); }

    // need fix the array of the registered players
    //public Array getRegisteredUser(){return getJSONArray(KEY_REGISTERED_PLAYERS);}
    //public void setGameName(String gameName){put(KEY_GAME_NAME, gameName);}

    public Date getGameDay(){return getDate(KEY_GAME_DATE);}

    public void setGameDate(Date gameDate){
        put(KEY_GAME_DATE, gameDate);
    }

    public Date gameEndLine(){return getDate(KEY_END_LINE);}

    public void setEndLine(Date endLine){
        put(KEY_END_LINE, endLine);
    }

    public String getPlatform(){
        return getString(KEY_PLATFORM);
    }

    public void setPlatform(String platform){
        put(KEY_PLATFORM, platform);
    }

    public String getExtraDetails(){
        return getString(KEY_EXTRA_DETAILS);
    }

    public void setExtraDetials(String details){
        put(KEY_EXTRA_DETAILS, details);
    }
}