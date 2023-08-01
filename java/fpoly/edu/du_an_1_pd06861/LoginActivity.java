package fpoly.edu.du_an_1_pd06861;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {
    IntentFilter intentFilter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);





        TextInputEditText edusername=findViewById(R.id.edUsername);
        TextInputEditText edpassword=findViewById(R.id.edPassword);
        Button btnlogin=findViewById(R.id.btnLogin);
        CheckBox chkRenemberPass =findViewById(R.id.chkRememberPass);
        intentFilter=new IntentFilter();
        intentFilter.addAction("kiemtradangnhap");
        intentFilter.addAction("...");

//        SharedPreferences pref=getSharedPreferences("USER_FILE",MODE_PRIVATE);
//        String user=pref.getString("USERNAME","");
//        String pass=pref.getString("PASSWORD", "");
//        Boolean rem=pref.getBoolean("REMEMBER",false);
//
//        edusername.setText(user);
//        edpassword.setText(pass);
//        chkRenemberPass.setChecked(rem);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=edusername.getText().toString();
                String pass=edpassword.getText().toString();
                Intent intent=new Intent(LoginActivity.this,KTlogin.class);
                Bundle bundle=new Bundle();
                bundle.putString("user", user);
                bundle.putString("pass",pass);
                intent.putExtras(bundle);
                startService(intent);
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(MyBroadcast, intentFilter);
    }
    public BroadcastReceiver MyBroadcast =new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()){
                case "kiemtradangnhap":
                    Bundle bundle=intent.getExtras();
                    boolean check=bundle.getBoolean("check");
                    if (check){
                        startActivity(new Intent(LoginActivity.this,MainActivity.class));

                    }else {
                        Toast.makeText(context, "dang nhap khong thanh cong", Toast.LENGTH_SHORT).show();
                    }
                    break;
                default:
                    break;
            }
        }
    };
}