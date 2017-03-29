package alexoliveira.com.firebaseuserteste;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistrarActivity extends AppCompatActivity {

    private EditText nome;
    private EditText sobrenome;
    private EditText email;
    private EditText senha;
    private Button cadastrar;
    private Button cancelar;

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        nome = (EditText) findViewById(R.id.registrar_nome_id);
        sobrenome = (EditText) findViewById(R.id.registrar_sobrenome_id);
        email = (EditText) findViewById(R.id.registrar_email_id);
        senha = (EditText) findViewById(R.id.registrar_senha_id);
        cadastrar = (Button) findViewById(R.id.cadastrar);
        cancelar = (Button) findViewById(R.id.cancelar);


        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                user = new User();
                user.setNome(nome.getText().toString());
                user.setSobrenome(sobrenome.getText().toString());
                user.setEmail(email.getText().toString());
                user.setSenha(senha.getText().toString());

                cadastrarUsuario();

                nome.setText("");
                sobrenome.setText("");
                email.setText("");
                senha.setText("");

                Intent intent = new Intent(RegistrarActivity.this, LoginActivity.class);
                startActivity(intent);

                Toast.makeText(RegistrarActivity.this,
                        "Usuário de " + user.getNome().toString() + " " + user.getSobrenome() + " criado com sucesso!",
                        Toast.LENGTH_SHORT).show();
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistrarActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }

    private void cadastrarUsuario() {
        final FirebaseAuth auth = ConfiguracaoFirebase.getFirebaseAuth();

        auth.createUserWithEmailAndPassword(
                user.getEmail(),
                user.getSenha()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                FirebaseUser firebaseUser = task.getResult().getUser();
                user.setId(firebaseUser.getUid());
                UserDAO dao = new UserDAO();
                dao.salvarUsuario(user);

                auth.signOut();
            }
        });

    }
}
