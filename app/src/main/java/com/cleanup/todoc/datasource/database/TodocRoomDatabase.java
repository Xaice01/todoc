package com.cleanup.todoc.datasource.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.cleanup.todoc.datasource.dao.ProjectDao;
import com.cleanup.todoc.datasource.dao.TaskDao;
import com.cleanup.todoc.datasource.Entity.ProjectEntity;
import com.cleanup.todoc.datasource.Entity.TaskEntity;

import java.util.Date;
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
    public static final ExecutorService executors = Executors.newFixedThreadPool(4);

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
                            .addCallback(todocRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static TodocRoomDatabase.Callback todocRoomDatabaseCallback = new TodocRoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db){
            super.onCreate(db);

            executors.execute(() -> {
                //execute in a new thread
                //populate the database in the background
                ProjectDao projectDao = INSTANCE.projectDao();
                TaskDao taskDao = INSTANCE.taskDao();
                //clean all data
                projectDao.deleteAll();

                //add a fake data to start (add project before task)

                projectDao.insert(new ProjectEntity(1L, "Projet Tartampion", 0xFFEADAD1));
                projectDao.insert(new ProjectEntity(2L, "Projet Lucidia", 0xFFB4CDBA));
                projectDao.insert(new ProjectEntity(3L, "Projet Circus", 0xFFA3CED2));

                taskDao.insert(new TaskEntity(1,2L,"Test",new Date().getTime()));


            });


        }
    };
}
