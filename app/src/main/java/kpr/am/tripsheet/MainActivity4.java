package kpr.am.tripsheet;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity4 extends AppCompatActivity {

    Button b3;
    CheckBox ck;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private EditText editTextName;
    private EditText editTextPass, editTextCompPass, edcabname, edcabnum;
    private String Name, Loc, Phone, Pass, Cpass, cabname, cannum;
    TextView editTextPhone;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        db = FirebaseFirestore.getInstance();
        sharedPreferences = getSharedPreferences("sftrip", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editTextName = findViewById(R.id.editTextTextPersonName2);
      //  editTextMail = findViewById(R.id.editTextTextEmailAddress);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextPass = findViewById(R.id.editTextTextPassword2);
        editTextCompPass = findViewById(R.id.editTextTextPassword3);
        edcabname = findViewById(R.id.editTextTextPersonName3);
        edcabnum = findViewById(R.id.editTextNumber);
        ck = findViewById(R.id.checkBox);

        editTextPhone.setText(getIntent().getStringExtra("num"));
    }

    public void reg(View view) {
        // getting data from edittext fields.
        Name = editTextName.getText().toString();
    //    Loc = editTextMail.getText().toString();
        Phone = editTextPhone.getText().toString();
        Pass = editTextPass.getText().toString();
        Cpass = editTextCompPass.getText().toString();
        cabname = edcabname.getText().toString();
        cannum = edcabnum.getText().toString();

        // validating the text fields if empty or not.
        if (TextUtils.isEmpty(Name)) {
            editTextName.setError("Please enter Name");
        } else if (TextUtils.isEmpty(Loc)) {
     //       editTextMail.setError("Please enter Location");
        } else if (TextUtils.isEmpty(Phone)) {
            editTextPhone.setError("Please enter valid number");
        } else if (TextUtils.isEmpty(cabname)) {
            editTextPhone.setError("Please enter valid number");
        } else if (TextUtils.isEmpty(cannum)) {
            editTextPhone.setError("Please enter valid number");
        } else if (!Pass.equals(Cpass)) {
            editTextCompPass.setError("Password doesn't match");
        } else if (!ck.isChecked()) {
            Toast.makeText(this, "Please agree to terms!", Toast.LENGTH_SHORT).show();
        } else {
            // calling method to add data to Firebase Firestore.
            addDataToFirestore(Name, Loc, Phone, Cpass, cabname, cannum);
        }
    }

    private void addDataToFirestore(String Name, String Loc, String Phone, String pass, String cbnam, String cbnum) {

        // Create a new user with a first, middle, and last name
        Map<String, Object> user = new HashMap<>();
        user.put("name", Name);
        user.put("location", Loc);
        user.put("phone", Phone);
        user.put("password", pass);
        user.put("cabname", cbnam);
        user.put("cabnumber", cbnum);

// Add a new document with a generated ID
        db.collection(Phone)
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("123acTAG", "DocumentSnapshot added with ID: " + documentReference.getId());
                        Toast.makeText(MainActivity4.this, "Sign up success!", Toast.LENGTH_SHORT).show();
                        editor.putString("mobnum",Phone);
                        editor.commit();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("123acTAG", "Error adding document", e);
                        Toast.makeText(MainActivity4.this, "Error!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    /*public void custdia() {
// Create custom dialog object
        final Dialog dialog = new Dialog(MainActivity4.this);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.setTitle("Cab Details");

        EditText cabname = dialog.findViewById(R.id.editTextNumber);
        EditText cabnumber = dialog.findViewById(R.id.editTextTextPersonName3);

        dialog.show();
        Button crereqbtn = dialog.findViewById(R.id.createreqbtn);
        crereqbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editor.putString("cabname", cabname.getText().toString());
                editor.putString("cabnumber", cabnumber.getText().toString());
                editor.commit();


                Toast.makeText(MainActivity4.this, "Details Added", Toast.LENGTH_SHORT).show();
                // Intent intent= new Intent(MainActivity4.this,MainActivity6.class);
                Intent intent = new Intent(MainActivity4.this, MainActivity3.class);
                editor.putString("mobnum", Phone).commit();
                intent.putExtra("num", Phone);
                startActivity(intent);
            }
        });

    }*/
}