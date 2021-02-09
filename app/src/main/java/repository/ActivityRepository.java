package repository;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import model.Bored;

public class ActivityRepository {

    private  final String TAG = this.getClass().getCanonicalName();
    private  final String COLLECTION_NAME = "activities";
    private final FirebaseFirestore db;

    public  ActivityRepository(){
        db = FirebaseFirestore.getInstance();
    }

    public void addActivity(Bored activity){
        try {
            db.collection(COLLECTION_NAME)
                    .add(activity)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d(TAG, "Document added with ID : " + documentReference.getId());
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.e(TAG, "Error adding document to the store " + e);
                        }
                    });

        }catch (Exception ex){
            Log.e(TAG, ex.toString());
            Log.e(TAG, ex.getLocalizedMessage());
        }
    }

}
