package com.jiv.target_mvd.data;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.content.ContextCompat;

import com.jiv.target_mvd.R;

public class ResourcesManager {
    private static ResourcesManager instance;
    private Context context;

    public static void initialize(Context context) {
        instance = new ResourcesManager(context);
    }

    public static ResourcesManager getInstance() {
        return instance;
    }

    public String getString(int resource) {
        return context.getString(resource);
    }

    public int getColor(int resource) {
        int color;
        try {
            color = ContextCompat.getColor(context, resource);
        } catch (Resources.NotFoundException e) {
            //Return default white
            color = ContextCompat.getColor(context, R.color.colorAccent);
        }

        return color;
    }

    private ResourcesManager(Context context) {
        this.context = context;
    }
}
