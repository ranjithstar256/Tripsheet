package kpr.am.tripsheet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import kpr.am.tripsheet.databinding.ActivityAddEntryBinding;

public class AddEntry extends AppCompatActivity {

    TextView tv;
    private FirebaseFirestore db;
    ActivityAddEntryBinding aeb;
    SharedPreferences sharedPreferences;
    String Name, Phone, jourdate,cusname,cusnum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        aeb = DataBindingUtil.setContentView(this, R.layout.activity_add_entry);
        sharedPreferences = getSharedPreferences("sftrip", MODE_PRIVATE);

        Phone = sharedPreferences.getString("mobnum", "9876543210");
        db = FirebaseFirestore.getInstance();

    }

    public void da(View view) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(AddEntry.this, onDateSetListener, 2010, 0, 06);
        datePickerDialog.show();
    }


    DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            Toast.makeText(AddEntry.this, year + "/" + month + "/" + dayOfMonth, Toast.LENGTH_SHORT).show();
            aeb.textView6.setText(dayOfMonth + "/" + (1 + month) + "/" + year);
            jourdate = aeb.textView6.getText().toString();
        }
    };

    public void savebtn(View view) {
        cusname = aeb.editTextTextPersonName4.getText().toString();
        cusnum = aeb.editTextTextPersonName5.getText().toString();

        if (TextUtils.isEmpty(cusname)) {
            aeb.editTextTextPersonName4.setError("Please enter Name");
        } else if (TextUtils.isEmpty(cusnum)) {
            aeb.editTextTextPersonName5.setError("Please enter valid number");
        } else if (cusnum.length()<10) {
            aeb.editTextTextPersonName5.setError("Please enter valid number");
        } else {

            Map<String, Object> user = new HashMap<>();
            user.put("cuname", cusname);
            user.put("cunum", cusnum);
            user.put("jourdate", jourdate);
            Log.d("asfas123", "cc "+Phone + "" +cusnum);

            db.collection(Phone)
                    .document("customers").collection(cusnum)
                    .add(user)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d("123acTAG", "DocumentSnapshot added with ID: " + documentReference.getId());
                            Toast.makeText(AddEntry.this, "Sign up success!", Toast.LENGTH_SHORT).show();
                            ActivityCompat.requestPermissions(AddEntry.this,                                    new String[]{Manifest.permission.SEND_SMS},                                    1);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w("123acTAG", "Error adding document", e);
                            Toast.makeText(AddEntry.this, "Error!", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(Phone, null, "Thanks for choosing THAI CABS your cab has booked on " + jourdate, null, null);
                    Toast.makeText(AddEntry.this, "permission given and sms sent", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddEntry.this, "Permission denied to send sms", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }
}