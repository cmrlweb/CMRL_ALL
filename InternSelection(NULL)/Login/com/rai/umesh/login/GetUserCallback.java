package com.rai.umesh.login;

interface GetUserCallback {

    /**
     * Invoked when background task is completed
     */

    void done(User returnedUser);
}