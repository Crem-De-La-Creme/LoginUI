package com.example.tickats.loginui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * Created by ProgrammingKnowledge on 1/5/2016.
 */


public class BackgroundWorker extends AsyncTask<String,Void,String> {
    Context context;
    AlertDialog alertDialog;
    Login l=new Login();
    private String Fid, FirstName, LastName,HoursWorked,ProfilePic;
    private ArrayList<String> passVal;









    BackgroundWorker (Context ctx) {

        context = ctx;

    }

    // Create a trust manager that does not validate certificate chains
    TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return null;
        }
        public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
        }
        public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
        }
    }
    };


    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String login_url = "https://www.tickats.live/login.php";
        String TicketStart_URL = "https://tickats.live/DisplayDataTickets.php";



        if(type.equals("login")) {
            try {

                String user_name = params[1];
                String password = params[2];


                URL url = new URL(login_url);
                SSLContext sc = SSLContext.getInstance("SSL");
                sc.init(null, trustAllCerts, new java.security.SecureRandom());
                HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
                HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
                urlConnection.setSSLSocketFactory(sc.getSocketFactory());

                // Create all-trusting host name verifier
                HostnameVerifier allHostsValid = new HostnameVerifier() {
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                };
                urlConnection.setHostnameVerifier(allHostsValid);
                //urlConnection.setRequestMethod("POST");
                urlConnection.setDoOutput(true);
                urlConnection.setDoInput(true);

                OutputStream outputStream = urlConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+"&"
                        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                //System.out.println(post_data);

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = urlConnection.getInputStream();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                }
                JSONObject responseJSON = new JSONObject(result);
                String loginMessage =responseJSON.getString("loginMessage");
                Fid= responseJSON.getString("FWid");


                FirstName=responseJSON.getString("First");
                LastName=responseJSON.getString("Last");
                HoursWorked=responseJSON.getString("Hours");
                //ProfilePic=responseJSON.getString("Photo");

                tab1.fid=Fid;
                tab1.fname=FirstName;
                tab1.lname=LastName;
                tab1.hrs=HoursWorked;


                bufferedReader.close();
                inputStream.close();
                urlConnection.disconnect();

                return loginMessage;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (KeyManagementException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else if (type.equals("TicketStart")){
            try{
                String FWid = params[1];
                URL url = new URL(TicketStart_URL);
                URLConnection conn = url.openConnection();
                conn.setDoOutput(true);
                conn.setDoInput(true);
                OutputStream outputStream = conn.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8" ));
                String post_data = URLEncoder.encode("FWid", "UTF-8") + "=" + URLEncoder.encode(FWid, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                InputStream inputStream = conn.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) !=null ){
                    result += line;

                }

                JSONObject JO = new JSONObject(result);
                JSONArray jTickets = JO.getJSONArray("Tickets");// Array name from php file

                for(int i =0; i<jTickets.length(); i++){
                    JSONObject t = jTickets.getJSONObject(i);
                    // Pulls data from the URL and puts it into a string for later insertion
                    String Tid  = t.getString("TicketID");
                    String wName  = t.getString("WorksiteName");
                    String Prior  = t.getString("Priority");
                    // Adds values from the JSON array into the relative layout
                    tab2.mTicketID.add(Tid);
                    tab2.mWorksite.add(wName);
                    tab2.mPriority.add(Prior);
                }

                bufferedReader.close();
                inputStream.close();
                outputStream.close();
                return result;

            }
            catch(MalformedURLException e){
                e.printStackTrace();
            }
            catch (IOException e){
                e.printStackTrace();
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Login Status");
    }

    @Override
    protected void onPostExecute(String result) {

        if(result.equals("Login Successful")){
            alertDialog.setMessage(result);
            alertDialog.show();
            Intent intent=new Intent(context,Account.class);
            context.startActivity(intent);


        }
        else{
            alertDialog.setMessage("Login Unsuccessful.Try again.");
            alertDialog.show();

        }

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}

