package piw.rmutsv.ac.th.firebaseregislogin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private EditText txtEmail, txtPwd;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtEmail = (EditText) findViewById(R.id.txtEmail_AddressLogin);
        txtPwd = (EditText) findViewById(R.id.txtPassword_Login);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void Login_Now(View view) {
        final ProgressDialog progressDialog = ProgressDialog.show(Login.this,"Please wait...","Processing...",true);
        (firebaseAuth.signInWithEmailAndPassword(txtEmail.getText().toString(),txtPwd.getText().toString()))
        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();

                if (task.isSuccessful()) {
                    Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Login.this, Profile.class);
                    intent.putExtra("Email", firebaseAuth.getCurrentUser().getEmail());
                    startActivity(intent);
                } else {
                    Log.e("ERROR", task.getException().toString());
                    Toast.makeText(Login.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();

                }
            }
        });
    }
}
