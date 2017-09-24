package stringdevelopment.com.br.gopizza;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        btnBack = (Button) findViewById(R.id.btn_back);

        btnConfirm = (Button) findViewById(R.id.btn_confirm);



        //Insere novo usuário BD Firebase
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                authentication = FirebaseAuth.getInstance();

                //CADASTRO
                authentication.createUserWithEmailAndPassword("edu_rodriguesdias@hotmail.com", "x3f96771")
                        .addOnCompleteListener(NewUser.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    //SUCESSO AO CADASTRAR
                                   new  SweetAlertDialog(NewUser.this, SweetAlertDialog.SUCCESS_TYPE)
                                           .setTitleText("Sucesso!")
                                           .setContentText("Usuário criado com sucesso!").show();
                                }else{
                                    new  SweetAlertDialog(NewUser.this, SweetAlertDialog.ERROR_TYPE)
                                            .setTitleText("Opss...!")
                                            .setContentText("Erro ao cadastrar usuário! Tente novamente.").show();
                                }
                            }
                        });
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
