package com.ka.krishnaaqua.SessionManagement;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManagement {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String SHARED_PREF_NAME = "session";
    String SESSION_KEY = "session_user";

    public SessionManagement(Context context){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }
    public void saveSession(User user){
//            Save the session of the user who logged in
        int id = user.getId ( );

        editor.putInt ( SESSION_KEY , id ).apply ( );
    }

    public int getSession() {
//        return user whose session is saved
        return sharedPreferences.getInt ( SESSION_KEY , -1 );
    }

    public void removeSession() {
        editor.putInt ( SESSION_KEY , -1 );
        editor.commit ( );
    }

}
