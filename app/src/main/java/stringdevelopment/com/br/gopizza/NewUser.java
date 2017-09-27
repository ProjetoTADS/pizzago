package stringdevelopment.com.br.gopizza;

import android.content.Intent;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class NewUser extends AppCompatActivity {

    private Button btnBack;

    private Button btnConfirm;

    private FirebaseAuth authentication;

    private EditText email;

    private EditText password;

    private  EditText name;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        btnBack = (Button) findViewById(R.id.btn_back);

        btnConfirm = (Button) findViewById(R.id.btn_confirm);

        email = (EditText) findViewById(R.id.txtEmail);

        password = (EditText) findViewById(R.id.txtPassword);

        name = (EditText) findViewById(R.id.txtNome);


        //Insere novo usuário BD Firebase
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String usermail = email.getText().toString();
                final String userpass = password.getText().toString();
                final String username = name.getText().toString();

                if(usermail.isEmpty() || userpass.isEmpty() || username.isEmpty()){
                    new  SweetAlertDialog(NewUser.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Opss...!")
                            .setContentText("Preencha todos os campos antes de continuar!")
                            .show();
                }else{
                    authentication = FirebaseAuth.getInstance();


                    //CADASTRO
                    authentication.createUserWithEmailAndPassword(usermail, userpass)
                            .addOnCompleteListener(NewUser.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        //SUCESSO AO CADASTRAR
                                        new SweetAlertDialog(NewUser.this, SweetAlertDialog.SUCCESS_TYPE)
                                                .setTitleText("Sucesso!")
                                                .setContentText("Usuário cadastrado!")
                                                .setConfirmText("Yes,delete it!")
                                                .showCancelButton(true)
                                                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                    @Override
                                                    public void onClick(SweetAlertDialog sDialog) {
                                                        Intent intent = new Intent(NewUser.this, permitionScreen.class);

                                                        startActivity(intent);
                                                        finish();
                                                    }
                                                })
                                                .show();
                                    }else{
                                        //ERRO AO CADASTRAR

                                        new  SweetAlertDialog(NewUser.this, SweetAlertDialog.ERROR_TYPE)
                                                .setTitleText("Opss...!")
                                                .setContentText("Erro ao cadastrar usuário: "+usermail+" com senha: "+userpass+"! Tente novamente. Error: ")
                                                .show();
                                    }
                                }
                            });
                }



            }
        });

        //Volta para a tela de login
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NewUser.this, MainActivity.class));
            }
        });

    }
}
