package com.kinocode.erporatepariwisatayogyakarta;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.kinocode.erporatepariwisatayogyakarta.adapter.PariwisataListAdapter;
import com.kinocode.erporatepariwisatayogyakarta.api.APIClient;
import com.kinocode.erporatepariwisatayogyakarta.api.APIInterface;
import com.kinocode.erporatepariwisatayogyakarta.model.Pariwisata;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rv_list)RecyclerView rvList;
    @BindView(R.id.toolbar_main)
    Toolbar mainToolbar;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private GoogleSignInClient mGoogleSignInClient;

    APIInterface apiInterface;
    PariwisataListAdapter wisatalistAdapter;
    List<Pariwisata.Datum> wisataList;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        setSupportActionBar(mainToolbar);
        getSupportActionBar().setTitle("Pariwisata Jogja");
        progressDialog = new ProgressDialog(this);
        apiInterface = APIClient.getClient().create(APIInterface.class);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mAuth = FirebaseAuth.getInstance();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        loadData();
    }

    private void loadData(){
        Call<Pariwisata> apiservice = apiInterface.getPariwisataList();
        apiservice.enqueue(new Callback<Pariwisata>() {
            @Override
            public void onResponse(Call<Pariwisata> call, Response<Pariwisata> response) {
                progressDialog.setMessage("Loading Data.. Please wait");
                progressDialog.setIndeterminate(false);
                progressDialog.setCancelable(false);
                progressDialog.show();

                if (response.isSuccessful()){
                    progressDialog.dismiss();

                    wisataList =response.body().getData();
                    Log.d("TAG", "Response = " + wisataList);
                    wisatalistAdapter = new PariwisataListAdapter(getApplicationContext(), wisataList);
                    RecyclerView.LayoutManager layoutmanager = new LinearLayoutManager(getApplicationContext());
                    rvList.setLayoutManager(layoutmanager);
                    rvList.setItemAnimator(new DefaultItemAnimator());
                    rvList.setAdapter(wisatalistAdapter);

                }
            }

            @Override
            public void onFailure(Call<Pariwisata> call, Throwable t) {
                Log.d("TAG_MAIN", "Response Failure = " + t.toString());
                progressDialog.dismiss();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_signout){
            signOut();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListener != null){
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void signOut(){
        mAuth.signOut();

        mGoogleSignInClient.signOut().addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                startActivity(new Intent(MainActivity.this, LoginActivity.class));

            }
        });
    }
}
