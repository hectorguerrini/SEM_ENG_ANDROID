package semanaeng.studio.com.semanadeengenhariamaua.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;



import semanaeng.studio.com.semanadeengenhariamaua.R;

public class cadastro extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        EditText emailEdit = (EditText) findViewById(R.id.editText_email);
        EditText passEdit = (EditText) findViewById(R.id.editText_pw);
        String email = emailEdit.getText().toString();
        String pw = passEdit.getText().toString();


    }
}
