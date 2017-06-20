package semanaeng.studio.com.semanadeengenhariamaua.activity;

import android.app.ProgressDialog;
import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.RadioGroup;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import semanaeng.studio.com.semanadeengenhariamaua.R;
import semanaeng.studio.com.semanadeengenhariamaua.funcoes.AppController;

import semanaeng.studio.com.semanadeengenhariamaua.funcoes.SessionManager;


public class cadastro extends AppCompatActivity {
    private Button cadastrar;
    private Button back;
    private ProgressDialog pDialog;
    private SessionManager session;
    private EditText nomeEdit;
    private EditText emailEdit;
    private EditText passEdit;
    private EditText cpfEdit;
    private EditText rgEdit;
    private EditText dataEdit;
    private EditText telEdit;
    private EditText celEdit;
    private String URL_CADASTRO = "https://ancient-bastion-16380.herokuapp.com/cadastro.php";
    private RadioGroup sexo;
    private String S = "indefinido";
    private static final String TAG = cadastro.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        cadastrar = (Button) findViewById(R.id.button_cadastro);
        back = (Button) findViewById(R.id.button_back);
        nomeEdit = (EditText) findViewById(R.id.editText_nome);
        emailEdit = (EditText) findViewById(R.id.editText_email);
        passEdit = (EditText) findViewById(R.id.editText_pw);
        cpfEdit = (EditText) findViewById(R.id.editText_cpf);
        rgEdit = (EditText) findViewById(R.id.editText_rg);
        dataEdit = (EditText) findViewById(R.id.editText_data);
        telEdit = (EditText) findViewById(R.id.editText_tel);
        celEdit = (EditText) findViewById(R.id.editText_cel);
        sexo = (RadioGroup) findViewById(R.id.radio_sexo);


        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);


        // Session manager
        session = new SessionManager(getApplicationContext());



        // Check if user is already logged in or not
        if (session.isLoggedIn()) {
            // User is already logged in. Take him to main activity
            Intent intent = new Intent(cadastro.this,
                    MainActivity.class);
            startActivity(intent);
            finish();
        }


        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = nomeEdit.getText().toString().trim();
                String email = emailEdit.getText().toString().trim();
                String password = passEdit.getText().toString().trim();
                String cpf = cpfEdit.getText().toString().trim();
                String rg = rgEdit.getText().toString().trim();
                String data = dataEdit.getText().toString().trim();
                String tel = telEdit.getText().toString().trim();
                String cel = celEdit.getText().toString().trim();
                switch (sexo.getCheckedRadioButtonId()){
                    case R.id.radioBut_M:
                         S = "Masculino";
                        break;
                    case R.id.radioBut_F:
                         S = "Feminino";
                        break;
                    default:
                         S = "Indefinido";
                        break;
                }


                if (!nome.isEmpty() && !email.isEmpty() && !password.isEmpty()&& !cpf.isEmpty()) {
                    registerUser(nome, email, password,cpf,rg,data,tel,cel);
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Please enter your details!", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }
    private void registerUser(final String nome, final String email,
                              final String password, final String cpf,
                              final String rg, final String data,
                              final String tel, final String cel){
        // Tag used to cancel the request
        String tag_string_req = "req_register";

        pDialog.setMessage("Registrando ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                URL_CADASTRO, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Register Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    if (!error) {

                        Toast.makeText(getApplicationContext(), "Usuario Cadastrado com Sucesso!!", Toast.LENGTH_LONG).show();

                        // Launch login activity
                        Intent intent = new Intent(
                                cadastro.this,
                                login.class);
                        startActivity(intent);
                        finish();
                    } else {

                        // Error occurred in registration. Get the error
                        // message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Registration Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> params = new HashMap<String, String>();
                params.put("nome", nome);
                params.put("email", email);
                params.put("password", password);
                params.put("cpf",cpf);
                params.put("rg",rg);
                params.put("data",data);
                params.put("tel",tel);
                params.put("cel",cel);
                params.put("sexo",S);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.getWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.progressdialog_style));
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

}
