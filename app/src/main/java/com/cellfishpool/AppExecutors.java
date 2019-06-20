package com.cellfishpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class AppExecutors {
    public static AppExecutors instance;

    public static AppExecutors getInstance(){
        if(instance==null){
            instance=new AppExecutors();
        }
        return instance;
    }

    private final ScheduledExecutorService mNetworkIO = Executors.newScheduledThreadPool(3);

    public ScheduledExecutorService NetworkIO(){
        return mNetworkIO;
    }

    /*private Executor mBackgroundExecutor = Executors.newSingleThreadExecutor();
    public com.cellfishpool.AppExecutors(){
        mBackgroundExecutor.execute(new Runnable() {
            @Override
            public void run() {

            }
        });
    }*/
}
