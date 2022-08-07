package kpr.am.tripsheet;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity3 extends AppCompatActivity {

    Button b2, b3;    private FirebaseFirestore db;    private EditText editTextName, editTextPass;    private String Phon,Pass,PassFire;    SharedPreferences sharedPreferences;    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sgnup);
        db = FirebaseFirestore.getInstance();
        editTextName = findViewById(R.id.editTextTextPersonName);        editTextPass = findViewById(R.id.editTextTextPassword);
        sharedPreferences = getSharedPreferences("sftrip", MODE_PRIVATE);        editor = sharedPreferences.edit();
        b2 = findViewById(R.id.button1);        b3 = findViewById(R.id.button2);

        b2.setOnClickListener(v -> {
            Phon = editTextName.getText().toString();            Pass = editTextPass.getText().toString();

            CollectionReference questionRef = db.collection(Phon);
            questionRef.whereEqualTo("phone", Phon).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    PassFire= queryDocumentSnapshots.getDocuments().get(0).get("password").toString();
                    Log.i("asddTAG", "onSuccess: \n"+PassFire+"--\n"+Pass);
                    if (PassFire.equals(Pass)){
                        Intent intent2 = new Intent(MainActivity3.this, MainActivity6.class);
                        editor.putString("mobnum",Phon).commit();
                        startActivity(intent2);
                    } else {
                        Toast.makeText(MainActivity3.this, "Invalid credentials!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        });

     /*   b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });*/
    }

    public void nerw(View view) {
        Intent intent3 = new Intent(MainActivity3.this, MainActivity5.class);
        startActivity(intent3);
    }
}