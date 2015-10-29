package com.example.administrator.logincmrl;

/**
 * Created by administrator on 28/10/15.
 */
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;


public class ServerRequests {
    ProgressDialog progressDialog;
    public static final int CONNECTION_TIMEOUT = 1000 * 15;
    public static final String SERVER_ADDRESS = "http://192.168.203.20:8888/";



    public ServerRequests(Context context) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Processing...");
        progressDialog.setMessage("Please wait...");
    }

    public void storeUserDataInBackground(User user,
                                          GetUserCallback userCallBack) {
        progressDialog.show();
        new StoreUserDataAsyncTask(user, userCallBack).execute();
    }

    public void fetchUserDataAsyncTask(User user, GetUserCallback userCallBack) {
        progressDialog.show();
        new fetchUserDataAsyncTask(user, userCallBack).execute();
    }

    /**
     * parameter sent to task upon execution progress published during
     * background computation result of the background computation
     */

    public class StoreUserDataAsyncTask extends AsyncTask<Void, Void, Void> {

        private static final char PARAMETER_DELIMITER = '&';
        private static final char PARAMETER_EQUALS_CHAR = '=';
        User user;
        GetUserCallback userCallBack;

        public StoreUserDataAsyncTask(User user, GetUserCallback userCallBack) {
            this.user = user;
            this.userCallBack = userCallBack;
        }

        @Override
        protected Void doInBackground(Void... params) {
            // params comes from the execute() call: params[0] is the url.
            try {
                return downloadUrl();
            } catch (IOException e) {
                //
            }
            return null;
        }

        private Void downloadUrl() throws IOException {
            String postParameters;
            HttpURLConnection urlConnection = null;

            postParameters = "name="+ URLEncoder.encode(user.name,"UTF-8")
                    +"&age="+URLEncoder.encode(user.age+"","UTF-8")
                    +"&username="+URLEncoder.encode(user.username,"UTF-8")
                    +"&password="+URLEncoder.encode(user.password,"UTF-8");

            if (postParameters != null) {
                Log.i("Post", "POST parameters: " + postParameters);
                try {
                    URL urlToRequest = new URL(SERVER_ADDRESS+ "Register.php");
                    Log.i("Post","URL is : " + urlToRequest.toString());
                    urlConnection = (HttpURLConnection) urlToRequest.openConnection();
                    urlConnection.setReadTimeout(30000 /* milliseconds */);
                    urlConnection.setConnectTimeout(30000 /* milliseconds */);
                    urlConnection.setDoOutput(true);
                    urlConnection.setRequestMethod("POST");
                    urlConnection.setRequestProperty("Content-Type",
                            "application/x-www-form-urlencoded");
                    urlConnection.setFixedLengthStreamingMode(
                            postParameters.getBytes().length);
                    PrintWriter out = new PrintWriter(urlConnection.getOutputStream());
                    out.print(postParameters);
                    out.close();

                    // handle issues
                    int statusCode = urlConnection.getResponseCode();
                    Log.e("Response", "The response is: " + statusCode);

                    urlConnection.disconnect();

                } catch (MalformedURLException e) {
                    // handle invalid URL
                    Log.e("Response", "invalid URL");
                } catch (SocketTimeoutException e) {
                    // hadle timeout
                    Log.e("Response", "handle timeout");
                } catch (IOException e) {
                    // handle I/0
                    Log.e("Response", "handle IO problem");
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                }
            }
            return null;
        }


        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            progressDialog.dismiss();
            userCallBack.done(null);
        }

    }

    public class fetchUserDataAsyncTask extends AsyncTask<Void, Void, User> {
        User user;
        GetUserCallback userCallBack;
        private static final char PARAMETER_DELIMITER = '&';
        private static final char PARAMETER_EQUALS_CHAR = '=';

        public fetchUserDataAsyncTask(User user, GetUserCallback userCallBack) {
            this.user = user;
            this.userCallBack = userCallBack;
        }

        @Override
        protected User doInBackground(Void... params) {
            try {
                return downloadUrl();
            } catch (IOException e) {
                //
            }
            return null;
        }

        private User downloadUrl() throws IOException {
            String postParameters;
            HttpURLConnection urlConnection = null;

            postParameters = "username="+URLEncoder.encode(user.username,"UTF-8")
                    +"&password="+URLEncoder.encode(user.password,"UTF-8");

            int len = 500;
            InputStream is = null;
            User returnedUser = null;

            if (postParameters != null) {
                Log.i("Post", "POST parameters: " + postParameters);
                try {
                    URL urlToRequest = new URL(SERVER_ADDRESS+ "FetchUserData.php");
                    urlConnection = (HttpURLConnection) urlToRequest.openConnection();
                    urlConnection.setReadTimeout(30000 /* milliseconds */);
                    urlConnection.setConnectTimeout(30000 /* milliseconds */);
                    urlConnection.setDoOutput(true);
                    urlConnection.setRequestMethod("POST");
                    urlConnection.setRequestProperty("Content-Type",
                            "application/x-www-form-urlencoded");
                    urlConnection.setFixedLengthStreamingMode(
                            postParameters.getBytes().length);
                    PrintWriter out = new PrintWriter(urlConnection.getOutputStream());
                    out.print(postParameters);
                    out.close();
                    // handle issues
                    int statusCode = urlConnection.getResponseCode();
                    Log.e("Response", "The response is: " + statusCode);
                    is = urlConnection.getInputStream();

                    // Convert the InputStream into a string
                    String contentAsString = readIt(is, len);
                    Log.i("Response", "Returned Value: " + contentAsString);
                    JSONObject jObject = new JSONObject(contentAsString);

                    if (jObject.length() != 0){
                        Log.e("happened", "JSON received the value");
                        String name = jObject.getString("name");
                        int age = jObject.getInt("age");

                        returnedUser = new User(name, age, user.username,
                                user.password);
                        //Log.i("Response", "Returned user by JSON: " + returnedUser);
                    }
                } catch (MalformedURLException e) {
                    // handle invalid URL
                    Log.e("Response", "invalid URL");
                } catch (SocketTimeoutException e) {
                    // hadle timeout
                    Log.e("Response", "handle timeout");
                } catch (IOException e) {
                    // handle I/0
                    Log.e("Response", "handle IO problem");
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                }
            }
            return returnedUser;
        }

        public String readIt(InputStream stream, int len) throws IOException {
            Reader reader = null;
            reader = new InputStreamReader(stream, "UTF-8");
            char[] buffer = new char[len];
            reader.read(buffer);
            return new String(buffer);
        }

        @Override
        protected void onPostExecute(User returnedUser) {
            super.onPostExecute(returnedUser);
            progressDialog.dismiss();
            userCallBack.done(returnedUser);
        }
    }
}