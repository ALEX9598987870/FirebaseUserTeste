package alexoliveira.com.firebaseuserteste;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by alexoliveira on 29/03/17.
 */

public class ConfiguracaoFirebase {

    private static DatabaseReference ref;
    private static FirebaseAuth auth;

    public static DatabaseReference getReference() {
        if (ref == null) {
            ref = FirebaseDatabase.getInstance().getReference();
        }
        return ref;
    }

    public static FirebaseAuth getFirebaseAuth() {
        if (auth == null) {
            auth = FirebaseAuth.getInstance();
        }
        return auth;
    }
}
