package com.techbuzz.anindya.companyprofile;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

/**
 * Created by Angry_Birds on 9/2/2016.
 */

public class SingleItemView extends AppCompatActivity {

    private AdView mAdMobAdView;

    String Name;
    String Desc1;
    String Id;
    String Born;
    String Desc2;
    String Picture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // animation for activity entry
        this.overridePendingTransition(R.anim.animation_enter, R.anim.animation_exit);

        setContentView(R.layout.singleitemview);

        // Set up the toolbar.
        Toolbar toolbar5 = (Toolbar) findViewById(R.id.toolbar5);
        setSupportActionBar(toolbar5);
        ActionBar ab = getSupportActionBar();
        ab.setTitle(R.string.personal_details);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowHomeEnabled(true);

        // Initializing Google AdMob
        mAdMobAdView = (AdView)findViewById(R.id.admob_adview);
        AdRequest adRequest = new AdRequest.Builder()
                /*.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("1797D2757F5140AA8F98809B458DB26F")// real device id here*/

                .build();
        mAdMobAdView.loadAd(adRequest);

        Intent intent = getIntent();
        // Get the result of name
        Name = intent.getStringExtra("name");
        // Get the result of born
        Born = intent.getStringExtra("born");
        // Get the result of id
        Id = intent.getStringExtra("id");
        // Get the result of desc1
        Desc1 = intent.getStringExtra("desc1");
        // Get the result of desc2
        Desc2 = intent.getStringExtra("desc2");
        // Get the result of picture
        Picture = intent.getStringExtra("picture");

        // Locate the TextViews and ImageView in singleitemview.xml
        TextView txtName = (TextView) findViewById(R.id.user_name);
        TextView txtBorn = (TextView) findViewById(R.id.user_born);
        TextView txtId = (TextView) findViewById(R.id.user_id);
        TextView txtDesc1 = (TextView) findViewById(R.id.user_desc1);
        TextView txtDesc2 = (TextView) findViewById(R.id.user_desc2);
        ImageView imgPicture = (ImageView) findViewById(R.id.profile_image);

        //Convert The String Picture to Resource Res existed in the drawable folder
        String picture = intent.getStringExtra("picture");
        int Res = this.getResources().getIdentifier(picture, "drawable" , this.getPackageName());

        // Set results to the TextViews
        txtName.setText(Name);
        txtBorn.setText(Born);
        txtId.setText(Id);
        txtDesc1.setText(Desc1);
        txtDesc2.setText(Desc2);
        imgPicture.setImageResource(Res);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // animation for activity exit
        overridePendingTransition(R.anim.animation_exit, R.anim.animation_enter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            showThanksDialogToUser(SingleItemView.this);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    // Create Dialog popup for Rating
    public static void showThanksDialogToUser(final Context context){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle("Thanks")
                .setMessage("We are happy to see your interest to rate us")
                .setNegativeButton(" Ok ", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                        dialog.cancel();
                    }
                });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

}