package com.example.martinruiz.snapnotes.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.martinruiz.snapnotes.R;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingsActivity extends AppCompatActivity {

    @BindView(R.id.textViewMadeIn)TextView textViewMadeIn;
    @BindView(R.id.textViewLogOut)TextView textViewLogOut;

    public static final String MEXICO = "🇲🇽";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);

        textViewMadeIn.setText("Made in México "+MEXICO);
        textViewLogOut.setOnClickListener(view -> {
            AuthUI.getInstance().signOut(this)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Intent loginIntent = new Intent(SettingsActivity.this, LogInActivity.class);
                            startActivity(loginIntent);
                            finish();
                        }
                    });
        });
    }
}
