package kpr.am.tripsheet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity6 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
    }

    public void newbooking(View view) {
        Intent intent3 = new Intent(MainActivity6.this, AddEntry.class);
        startActivity(intent3);
    }  public void tripsh(View view) {
        Intent intent3 = new Intent(MainActivity6.this, TripSheet.class);
        startActivity(intent3);
    }  public void dash(View view) {
        Intent intent3 = new Intent(MainActivity6.this, Dashboard.class);
        startActivity(intent3);
    }
}