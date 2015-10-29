/**
 * Created by administrator on 28/10/15.
 */
package com.example.administrator.logincmrl;

interface GetUserCallback {

    /**
     * Invoked when background task is completed
     */

    void done(User returnedUser);
}