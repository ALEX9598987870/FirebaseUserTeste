package alexoliveira.com.firebaseuserteste;


import com.google.firebase.database.DatabaseReference;

/**
 * Created by alexoliveira on 29/03/17.
 */

class UserDAO {

    private DatabaseReference ref;

    public boolean salvarUsuario(User user) {
        boolean saved = false;

        try {
            ref = ConfiguracaoFirebase.getReference();

            ref.child("usuarios").child(user.getId()).setValue(user);

            saved = true;

        } catch (Exception e) {
            e.getMessage();
        }

        return saved;
    }
}
