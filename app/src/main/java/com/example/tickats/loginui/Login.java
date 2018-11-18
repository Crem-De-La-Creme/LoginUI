package com.example.tickats.loginui;


import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Login extends AppCompatActivity {
    private EditText Username;
    private EditText Password;
    private TextView Info;
    private Button Login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_login);
        Username=findViewById(R.id.etUsername);
        Password=findViewById(R.id.etPassword);
        Login=findViewById(R.id.btnLogin);

        Info=findViewById(R.id.etInfo);


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(view);

            }

        });
    }
    private String md5(String userPassword) {
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(userPassword.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;

    }

    private void validate(View view) {
        String username = Username.getText().toString();
        String password = Password.getText().toString();
        String md5password=md5(password);
        String type = "login";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, username, md5password);

    }}
        /**StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        String md5password = md5(userPassword);
        String db = "tickats";
        String usr = "tickats";
        String pwd = "tickats";
        String ip = "107.180.50.163:3306";
        //String link = "http://myphpmysqlweb.hostei.com/login.php?username="+usr+"& password="+pwd;
        String link = "http://107.180.50.163/login.php";
        String ConnectionURL = "jdbc:mysql://107.180.50.163:3306/tickats";
        ResultSet rs = null;
        try {
            //Connection connection = connectionClass(usr, pwd, db, ip);
            Connection connection = DriverManager.getConnection(ConnectionURL, usr, pwd);
            if (connection == null) {
                Info.setText("Check your internet connection");

            }
            else {
                String query = "SELECT Password FROM tickats.Field_Worker_Emp_Table WHERE Username= ?";
                //Statement stmt = connection.createStatement();
                //rs = stmt.executeQuery(query);
                java.sql.PreparedStatement prst = connection.prepareStatement(query);
                prst.setString(1, userName);
                rs = prst.executeQuery();
                String pass = rs.getString("Password");

                if (md5password==pass) {
                    Intent intent = new Intent(Login.this, Account.class);
                    startActivity(intent);

                }
                else{
                    Info.setText("Credentials  was not correct");
                }

            }

        } catch (SQLException e) {
            System.out.println(e);
            Info.setText("Couldn't connect to database");

        }}}






    @SuppressLint("NewApi")
    public Connection connectionClass(String user,String password, String database, String server) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn = null;
        String ConnectionURL = null;
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL = "jdbc:jtds:sqlserver://" + server + ";databaseName=" + database + ";user=" + user + ";password=" + password + ";";
            conn = DriverManager.getConnection(ConnectionURL);
            String user1 = "MoonMan";
            ConnectionURL = "jdbc:mysql://107.180.50.163:3306/tickats";
            String usr = "tickats";
            String pwd = "tickats";
            Connection con = DriverManager.getConnection(ConnectionURL, usr, pwd);
            String sql = "SELECT FWid, First, Last FROM tickats.Field_Worker_Table WHERE Username = ?";
            java.sql.PreparedStatement prst = con.prepareStatement(sql);
            prst.setString(1, user1);
            ResultSet rs = prst.executeQuery();
        } catch (SQLException se) {
            Log.e("error1 is here:", se.getMessage());


        } catch (ClassNotFoundException e) {
            Log.e("error2 is here:", e.getMessage());


        } catch (Exception e) {
            Log.e("error1 is here:", e.getMessage());
        }
        return conn;

    }
    }
*/

