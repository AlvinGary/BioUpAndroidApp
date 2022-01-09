package com.aras.bioup.view.LoginView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.aras.bioup.R;
import com.aras.bioup.helper.SharedPreferenceHelper;
import com.aras.bioup.view.HomeView.HomeActivity;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {
    private TextView text_register_login, tv_sb;
    private TextInputLayout text_input_username_login, text_input_password_login;
    private Button btn_login_login;
    private LoginViewModel lvm;
    private SharedPreferenceHelper helper;
    private ProgressDialog dialog;
    private boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        text_register_login = findViewById(R.id.text_register_login);
        text_input_username_login = findViewById(R.id.text_input_username_login);
        text_input_password_login = findViewById(R.id.text_input_password_login);
        btn_login_login = findViewById(R.id.btn_login_login);
        text_register_login.setOnClickListener(view -> {
            startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse("http://www.google.com")));
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });
        helper = SharedPreferenceHelper.getInstance(this);
        lvm = new ViewModelProvider(this).get(LoginViewModel.class);
        btn_login_login.setOnClickListener(view -> {
            dialog = ProgressDialog.show(this, "", "Loading. Please wait...", true);
            dialog.show();
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
            if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                    connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                if (!text_input_username_login.getEditText().getText().toString().isEmpty() &&
                        !text_input_password_login.getEditText().getText().toString().isEmpty()) {
                    String email = text_input_username_login.getEditText().getText().toString().trim();
                    String password = text_input_password_login.getEditText().getText().toString().trim();
                    lvm.login(email, password).observe(LoginActivity.this, tokenResponse -> {
                        if (tokenResponse != null) {
                            if (tokenResponse.getAccess_token() != null) {
                                helper.saveAccessToken(tokenResponse.getAuthorization());
                                finish();
                                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                                Toast.makeText(LoginActivity.this, "Berhasil Masuk!", Toast.LENGTH_SHORT).show();
                            } else {
                                Snackbar.make(view, tokenResponse.getMessage(), Snackbar.LENGTH_LONG).show();
                                dialog.dismiss();
                            }
                        } else {
                            Snackbar.make(view, "Ups, ada gangguan saat melakukan login. Silakan coba lagi", Snackbar.LENGTH_LONG).show();
                            dialog.dismiss();
                        }
                    });
                } else {
                    Snackbar.make(view, "Tidak boleh kosong", Snackbar.LENGTH_LONG).show();
                    dialog.dismiss();
                }
            } else {
                Toast.makeText(this, "Pastikan kamu terhubung ke jaringan internet.", Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Klik tombol kembali sekali lagi untuk keluar dari aplikasi", Toast.LENGTH_SHORT).show();

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
}