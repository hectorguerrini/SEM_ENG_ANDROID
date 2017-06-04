package semanaeng.studio.com.semanadeengenhariamaua.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import semanaeng.studio.com.semanadeengenhariamaua.R;

public class cadastro extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        EditText emailEdit = (EditText) findViewById(R.id.editText_email);
        EditText passEdit = (EditText) findViewById(R.id.editText_pw);
        String email = emailEdit.getText().toString();
        String pw = passEdit.getText().toString();
        firebaseAuth = FirebaseAuth.getInstance();

        firebaseAuth.createUserWithEmailAndPassword(email,pw)
                .addOnCompleteListener(cadastro.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(cadastro.this, "ERRO",
                                    Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(cadastro.this, "Cadastrado com Sucesso!",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }
}
