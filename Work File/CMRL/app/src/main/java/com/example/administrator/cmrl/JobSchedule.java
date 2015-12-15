package com.example.administrator.cmrl;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.example.administrator.cmrl.helper.Pendingtasks;

/**
 * Created by administrator on 14/12/15.
 */
public class JobSchedule extends JobService {
    private Pendingtasks tasksdb = Pendingtasks.getHelper(this);
    private UpdateAppsAsyncTask updateTask = new UpdateAppsAsyncTask();
    @Override
    public boolean onStartJob(JobParameters params) {
        updateTask.execute(params);
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        boolean shouldReschedule = updateTask.stopJob(params);
        return shouldReschedule;
    }
    private class UpdateAppsAsyncTask extends AsyncTask<JobParameters, Void, JobParameters[]> {


        @Override
        protected JobParameters[] doInBackground(JobParameters... params) {
            // Do updating and stopping logical here.
            return params;
        }

        @Override
        protected void onPostExecute(JobParameters[] result) {
            for (JobParameters params : result) {
                if (!hasJobBeenStopped(params)) {
                    jobFinished(params, false);
                }
            }
        }

        private boolean hasJobBeenStopped(JobParameters params) {
            // Logic for checking stop.
            return false;
        }

        public boolean stopJob(JobParameters params) {
            // Logic for stopping a job. return true if job should be rescheduled.
            return false;
        }

    }

}
