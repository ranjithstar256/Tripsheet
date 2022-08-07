package kpr.am.tripsheet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kpr.am.tripsheet.databinding.ActivityViewAllBinding;

public class ViewAll extends AppCompatActivity {
    private FirebaseFirestore db;
    SharedPreferences sharedPreferences;
    String Phon,data , PassFire , TAG;

    ActivityViewAllBinding avb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_view_all);
        db = FirebaseFirestore.getInstance();
        TAG="e3sadsada";
        sharedPreferences = getSharedPreferences("sftrip", MODE_PRIVATE);
        Phon = sharedPreferences.getString("mobnum", "9876543210");

        avb = DataBindingUtil.setContentView(this, R.layout.activity_view_all);

        Log.d("asdfsda", "onCreate: "+Phon);
        db.collection("9876543120").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                Log.d(TAG+"ss", task.getResult().size()+"");

                if (task.isSuccessful()) {
                    List<String> list = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        list.add(document.getId());
                        Log.d(TAG+"ssf", list.toString());

                    }
                    Log.d(TAG+"ss", list.toString());
                } else {
                    Log.d(TAG+"edr", "Error getting documents: ", task.getException());
                }
            }
        });

   /*     DocumentReference docRef = db.collection("9876543210").document("customers");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d("qweTAG", "DocumentSnapshot data: " + document.getData());
                        Log.d("qweTAG","String value: " + document.getString("names"));
                    } else {
                        Log.d("qweTAG", "No such document");
                    }
                } else {
                    Log.d("qweTAG", "get failed with ", task.getException());
                }
            }
        });

        db.collection(Phon).document("customers").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                Log.d("TAGaaa", "sfs"+task.getResult().exists());

                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        List<String> list = new ArrayList<>();

                        Map<String, Object> map = document.getData();
                        if (map != null) {
                            for (Map.Entry<String, Object> entry : map.entrySet()) {
                                list.add(entry.getValue().toString());
                            }
                        }

                        //So what you need to do with your list
                        for (String s : list) {
                            Log.d("TAGaaa", s);
                        }
                    }
                } else {
                    Log.d("TAGaaa", "sfs+");

                }
            }
        });
*/
    /*   db.collection(Phon).document("customers").get().addOnCompleteListener(task -> {
                   Log.d("dddwasasa", "onCreate: " + task.getResult().getData()+ "");

     *//*       if (task.isSuccessful()) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                    String uid = document.getId();
                    DocumentReference uidRef = db.collection("customers").document(uid);
                    uidRef.get().addOnCompleteListener(task1 -> {
                        if (task1.isSuccessful()) {
                            DocumentSnapshot document1 = task1.getResult();
                            if (document1.exists()) {
                                String icPassport = document1.getString("cuname");
                                String phone = document1.getString("cunum");
                                String ticketDate = document1.getString("ticket_date");

                                Log.d("asfa3as", "onCreate: "+icPassport+phone+ticketDate);

                            } else {

                            }
                        } else {

                        }
                    });
                }
            } else {
                Log.d("fghgjTAG", "Error getting documents: ", task.getException());
            }*/
        /*
        });*/

    /*    db.collection(Phon).get().addOnSuccessListener(queryDocumentSnapshots -> {

        });
*/

        /*
        DocumentReference questionRef = db.collection(Phon).document("customers");

        questionRef.addSnapshotListener((value, error) -> {
            avb.textView3.setText(value+"");
            Log.d("dfdsfsd", "onCreate: "+value.getData());
        });*/  /*    db.collection(Phone).document("customers").addSnapshotListener((value, error) -> {
            avb.textView3.setText(value+"");
            Log.d("dfdsfsd", "onCreate: "+value);
        });*/   /*  questionRef.whereEqualTo("customer", Phone).get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                data= queryDocumentSnapshots.getDocuments().get(0).get("jourdate").toString();
            }
        });*/
    }
}