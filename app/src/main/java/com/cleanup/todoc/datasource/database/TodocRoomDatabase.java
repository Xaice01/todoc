package com.cleanup.todoc.datasource.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.cleanup.todoc.datasource.dao.ProjectDao;
import com.cleanup.todoc.datasource.dao.TaskDao;
import com.cleanup.todoc.datasource.entity.ProjectEntity;
import com.cleanup.todoc.datasource.entity.TaskEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * Create or get Database
 */
@Database(entities = {ProjectEntity.class, TaskEntity.class}, version = 1, exportSchema = false)
public abstract class TodocRoomDatabase extends RoomDatabase {

    public abstract ProjectDao projectDao();
    public abstract TaskDao taskDao();
    //create executor with thread pool at 4

    //marking the instance as volatile to ensure atomic access to the variable
    private static volatile TodocRoomDatabase INSTANCE;


    /*
     * Create Singleton of TodocRoomDatabase
     */
    public static TodocRoomDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (TodocRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context,TodocRoomDatabase.class,"todoc_database")

                        .build();
                }
            }
        }
        return INSTANCE;
    }

    private static List<ProjectEntity> getProjectFormJson(Context context) {
        List<ProjectEntity> projects = new ArrayList<>();

        try {
            InputStream jsonFile = context.getAssets().open("project.json");
            byte[] buffer = new byte[jsonFile.available()];
            jsonFile.read(buffer);
            jsonFile.close();
            String json = new String(buffer, "UTF-8");

            // Parse the JSON array into Project objects
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                long id = jsonObject.getLong("id");
                int color = jsonObject.getInt("color");
                String name = jsonObject.getString("name");
                projects.add(new ProjectEntity(id, name, color));
            }
        } catch (IOException | JSONException e) {
        e.printStackTrace();
    }

        return projects;
    }
}
