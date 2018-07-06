package jawale.ankita.elaundromat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Login extends MainActivity implements View.OnClickListener{

    private EditText email, pwd;
    private Button login;
    private TextView signup;
    private String intentExtra = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_login);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_login, null, true);
        //drawer.removeAllViews();
        drawer.removeViewAt(0);
        drawer.addView(contentView, 0);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        intentExtra = extras.getString("class");

        email = (EditText) contentView.findViewById(R.id.email);
        pwd = (EditText) contentView.findViewById(R.id.password);
        login = (Button) contentView.findViewById(R.id.loginBtn);
        login.setOnClickListener(this);
        signup = (TextView) contentView.findViewById(R.id.signIn);
        signup.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.loginBtn){
            if(validate()) {
                String emailTxt = email.getText().toString().trim();
                String pwdTxt = pwd.getText().toString().trim();
                if (GlobalClass.pref == null) {
                    GlobalClass.initSharedPref(Login.this);
                }else {
                    if (emailTxt.equals(GlobalClass.retrieveString(Login.this.getResources().getString(R.string.email_login))) &&
                            pwd.equals(GlobalClass.retrieveString(Login.this.getResources().getString(R.string.pwd_login)))) {
                        GlobalClass.isLoggedIn = true;
                        Intent i;
                        if (intentExtra.equalsIgnoreCase(Login.this.getResources().getString(R.string.title_activity_review))) {
                            i = new Intent(Login.this, ReviewActivity.class);
                            i.putExtra("class",Login.this.getResources().getString(R.string.title_activity_login));
                            startActivity(i);
                        } else {
                            i = new Intent(Login.this, MainActivity.class);
                            startActivity(i);
                        }
                    }else{
                        Intent i = new Intent(Login.this, SignIn.class);
                        i.putExtra("class",intentExtra);
                        startActivity(i);
                    }
                }
            }
        }else if(v.getId() == R.id.signIn){
            Intent i = new Intent(Login.this, SignIn.class);
            i.putExtra("class",intentExtra);
            startActivity(i);
        }
    }

    private boolean validate(){
        boolean flag = false;
        if (email.getText().toString().trim().isEmpty()){
            Toast.makeText(Login.this,"Enter Email ID", Toast.LENGTH_SHORT).show();
            flag = false;
        }else if(!email.getText().toString().trim().equalsIgnoreCase("")){
            CharSequence target = email.getText().toString().trim();
            flag = (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
            if(!flag)
                Toast.makeText(Login.this,"Enter valid Email ID", Toast.LENGTH_SHORT).show();
        }else if(pwd.getText().toString().trim().isEmpty()){
            Toast.makeText(Login.this,"Enter Password", Toast.LENGTH_SHORT).show();
            flag = false;
        }
        return flag;
    }
}
