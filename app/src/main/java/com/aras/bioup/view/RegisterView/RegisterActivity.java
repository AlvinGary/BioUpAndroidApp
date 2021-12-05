package com.aras.bioup.view.RegisterView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.aras.bioup.R;
import com.aras.bioup.model.RegisterResponse;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Response;


public class RegisterActivity extends AppCompatActivity {

    private TextView text_login_register;
    private TextInputLayout text_input_name_register,text_input_city_register,
            text_input_birthyear_register,text_input_school_register,text_input_username_register,
            text_input_email_register,text_input_confirm_pass_register,text_input_password_register;
    private Button btn_register_register;
    private RegisterViewModel rvm;
    private String message ="";

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        text_login_register = findViewById(R.id.text_register_login);
        text_login_register.setOnClickListener(view -> finish());
        text_input_name_register = findViewById(R.id.text_input_name_register);
        text_input_city_register = findViewById(R.id.text_input_city_register);
        text_input_birthyear_register = findViewById(R.id.text_input_birthyear_register);
        text_input_school_register = findViewById(R.id.text_input_school_register);
        text_input_username_register = findViewById(R.id.text_input_username_register);
        text_input_email_register = findViewById(R.id.text_input_email_register);
        text_input_confirm_pass_register = findViewById(R.id.text_input_confirm_pass_register);
        text_input_password_register = findViewById(R.id.text_input_password_register);
        btn_register_register = findViewById(R.id.btn_register_register);
        rvm = new ViewModelProvider(this).get(RegisterViewModel.class);

        btn_register_register.setOnClickListener(view -> {
            if (!text_input_name_register.getEditText().getText().toString().isEmpty()&&
                    !text_input_email_register.getEditText().getText().toString().isEmpty()&&
                    !text_input_username_register.getEditText().getText().toString().isEmpty()&&
                    !text_input_password_register.getEditText().getText().toString().isEmpty()&&
                    !text_input_confirm_pass_register.getEditText().getText().toString().isEmpty()&&
                    !text_input_city_register.getEditText().getText().toString().isEmpty()&&
                    !text_input_school_register.getEditText().getText().toString().isEmpty()&&
                    !text_input_birthyear_register.getEditText().getText().toString().isEmpty()){
                String name = text_input_name_register.getEditText().getText().toString().trim();
                String email = text_input_email_register.getEditText().getText().toString().trim();
                String username = text_input_username_register.getEditText().getText().toString().trim();
                String pass = text_input_password_register.getEditText().getText().toString().trim();
                String cpass = text_input_confirm_pass_register.getEditText().getText().toString().trim();
                String city = text_input_city_register.getEditText().getText().toString().trim();
                String school = text_input_school_register.getEditText().getText().toString().trim();
                String birthyear = text_input_birthyear_register.getEditText().getText().toString().trim();
                rvm.register(username,email,pass,cpass,name,school,city,birthyear).observe(RegisterActivity.this,registerResponse -> {
                    if (registerResponse!=null){
                        finish();
                        Toast.makeText(RegisterActivity.this, "Yey, akun berhasil dibuat!", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(RegisterActivity.this, "Ups. Akun gagal dibuat. Silakan coba beberapa saat lagi", Toast.LENGTH_SHORT).show();
                    }
                });
            }else{
                Toast.makeText(RegisterActivity.this, "Tolong diisi semua", Toast.LENGTH_SHORT).show();
            }
        });
    }


}