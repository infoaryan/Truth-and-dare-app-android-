package com.example.truthdare;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.security.PrivateKey;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Random random=new Random();
    private int from;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    // rotation method by using ANIMATION
    //This is called each time the button is pressed
    public void rotate(View view){
        ImageView bottle=findViewById(R.id.image);
        final Button button=findViewById(R.id.button);

        int to=random.nextInt(2000);
        float pivotx=(float)bottle.getWidth()/2;
        float pivoty=(float)bottle.getHeight()/2;
        Animation rotation =new RotateAnimation(from,to,pivotx,pivoty);
        from=to; //This is to set the next start position as the initial one
        //otherwise it will start from zero again

        rotation.setDuration(2500);
        rotation.setFillAfter(true);
        bottle.startAnimation(rotation);

        //listeners for toast and button disabling and enabling
        rotation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Toast.makeText(getApplicationContext(),".....ROTATING...",Toast.LENGTH_SHORT).show();
                button.setEnabled(false);

            }

            @Override
            public void onAnimationEnd(Animation animation) {

               new Handler().postDelayed(new Runnable() {
                   @Override
                   public void run() {
                       AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                       builder.setTitle("Hey there bait !!");
                       builder.setMessage("Please choose between Truth and Dare  ");
                       builder.setPositiveButton("TRUTH", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int which) {
                               button.setEnabled(true);
                               Intent intent = new Intent(MainActivity.this,Cardshow.class);
                               intent.putExtra("decide","truth");
                               startActivity(intent);

                               overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                           }
                       });
                       builder.setNegativeButton("DARE", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int which) {
                               button.setEnabled(true);
                               Intent intent = new Intent(MainActivity.this,Cardshow.class);
                               intent.putExtra("decide","dare");
                               startActivity(intent);

                               overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                           }
                       });
                       builder.setCancelable(false);
                       AlertDialog dialog = builder.create();
                       dialog.show();
                   }
               },700);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });





    }
}
