package com.cleanup.todoc.dao;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

public class TestUtilsDao {

    @NonNull
    public static <T> T getValue(@NonNull final LiveData<T> liveData) {
        liveData.observeForever(t -> {
        });

        T captured = liveData.getValue();

        if (captured == null) {
            throw new AssertionError("LiveData value is null !");
        }
        return captured;
    }
}
