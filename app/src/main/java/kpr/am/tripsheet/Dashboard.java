package kpr.am.tripsheet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }

    public void upc(View view) {
        Intent intent3 = new Intent(Dashboard.this, ViewAll.class);
        startActivity(intent3);
    }
}