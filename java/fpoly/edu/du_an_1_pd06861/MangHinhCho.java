package fpoly.edu.du_an_1_pd06861;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


public class MangHinhCho extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mang_hinh_cho);
        ImageView ivLogo=findViewById(R.id.ivLogo);
        Glide.with(this).load(R.drawable.loading).into(ivLogo);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MangHinhCho.this, LoginActivity.class));
            }
        },2000);

    }
}