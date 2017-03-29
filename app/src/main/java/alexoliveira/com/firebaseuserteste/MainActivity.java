package alexoliveira.com.firebaseuserteste;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView id;
    private TextView nome;
    private TextView sobrenome;
    private TextView email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id = (TextView) findViewById(R.id.user_id);
        nome = (TextView) findViewById(R.id.user_nome);
        sobrenome = (TextView) findViewById(R.id.user_sobrenome);
        email = (TextView) findViewById(R.id.user_email);

        FirebaseAuth auth = ConfiguracaoFirebase.getFirebaseAuth();

        FirebaseUser currentUser = auth.getCurrentUser();

        String uid = currentUser.getUid();

        DatabaseReference ref = ConfiguracaoFirebase.getReference();

        ref.child("usuarios").orderByChild("id").equalTo(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    id.setText(snapshot.child("id").getValue().toString());
                    nome.setText(snapshot.child("nome").getValue().toString());
                    sobrenome.setText(snapshot.child("sobrenome").getValue().toString());
                    email.setText(snapshot.child("email").getValue().toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i("disgraça", databaseError.toException().toString());
            }
        });
    }

}
