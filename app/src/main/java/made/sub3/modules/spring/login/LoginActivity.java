package made.sub3.modules.spring.login;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import made.sub3.R;
import made.sub3.model.User;
import made.sub3.modules.spring.student.StudentActivity;

public class LoginActivity extends AppCompatActivity {

    EditText editTextEmail;
    EditText editTextPass;
    Button loginButton;
    LoginViewModel viewModel;
    private final Observer<String> observer = new Observer<String>() {
        @Override
        public void onChanged(@Nullable String aBoolean) {
            if(aBoolean!=null) {
                startActivity(new Intent(LoginActivity.this, StudentActivity.class)
                        .putExtra("token",aBoolean)
                );
            } else {
                Toast.makeText(LoginActivity.this, "password salah", Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        viewModel = new LoginViewModel();
        editTextEmail = findViewById(R.id.springEmail);
        editTextPass = findViewById(R.id.springPass);
        loginButton = findViewById(R.id.springLoginBtn);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login(editTextEmail.getText().toString(),editTextPass.getText().toString());
            }
        });
        viewModel.getData().observe(this,observer);

    }

    void login(String email, String pass) {
        if(email.isEmpty()) {
            Toast.makeText(this, "Email tidak boleh kosong", Toast.LENGTH_LONG).show();
            return;
        }
        if(pass.isEmpty()) {
            Toast.makeText(this, "Pass tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }
        viewModel.login(User.builder()
                .username(email)
                .password(pass)
                .build());
    }
}
