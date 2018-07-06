package jawale.ankita.elaundromat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignIn extends MainActivity implements View.OnClickListener{

    private EditText name, email, pwd, address, city, state, zipCode, mobile;
    private Button signInBtn;
    private String nameTxt, emailTxt, addressTxt, pwdTxt, cityTxt, stateTxt, zipcodeTxt, mobileTxt, intentExtra = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_sign_in);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_sign_in, null, true);
        //drawer.removeAllViews();
        drawer.removeViewAt(0);
        drawer.addView(contentView, 0);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        intentExtra = extras.getString("class");

        name = (EditText) contentView.findViewById(R.id.name);
        email = (EditText) contentView.findViewById(R.id.email);
        pwd = (EditText) contentView.findViewById(R.id.pwd);
        address = (EditText) contentView.findViewById(R.id.address);
        city = (EditText) contentView.findViewById(R.id.city);
        state = (EditText) contentView.findViewById(R.id.state);
        zipCode = (EditText) contentView.findViewById(R.id.zipcode);
        mobile = (EditText) contentView.findViewById(R.id.mobile);
        signInBtn = (Button) contentView.findViewById(R.id.signInBtn);
        signInBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        nameTxt = name.getText().toString().trim();
        emailTxt = email.getText().toString().trim();
        pwdTxt = pwd.getText().toString().trim();
        addressTxt = address.getText().toString().trim();
        cityTxt = city.getText().toString().trim();
        stateTxt = state.getText().toString().trim();
        zipcodeTxt = zipCode.getText().toString().trim();
        mobileTxt = mobile.getText().toString().trim();
        if(!textViewisEmpty()){
            saveInSharedPref();
            GlobalClass.isLoggedIn = true;
            GlobalClass.storeBoolean("isLoggedIn",true);
            if (intentExtra.equalsIgnoreCase(SignIn.this.getResources().getString(R.string.title_activity_review))){
                Intent i = new Intent(SignIn.this,ReviewActivity.class);
                i.putExtra("class",SignIn.this.getResources().getString(R.string.title_activity_sign_in));
                startActivity(i);
            }else {
                Toast.makeText(SignIn.this,"Details saved!!",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(SignIn.this, MainActivity.class);
                startActivity(i);
            }
        }
    }

    private boolean textViewisEmpty(){
        boolean flag = true;
        if (nameTxt.isEmpty() || emailTxt.isEmpty() || pwdTxt.isEmpty() || addressTxt.isEmpty() || cityTxt.isEmpty() ||
                stateTxt.isEmpty() || zipcodeTxt.isEmpty() || mobileTxt.isEmpty()) {
            flag = true;
            Toast.makeText(this,"Enter all the information", Toast.LENGTH_SHORT).show();
        }else if(!emailTxt.equalsIgnoreCase("")){
            flag = !(!TextUtils.isEmpty(emailTxt) && Patterns.EMAIL_ADDRESS.matcher(emailTxt).matches());
            if(flag)
                Toast.makeText(SignIn.this,"Enter valid Email ID", Toast.LENGTH_SHORT).show();
        }else{
            flag = false;
        }
        return flag;
    }

    private void saveInSharedPref(){
        if (GlobalClass.pref == null){
            GlobalClass.initSharedPref(SignIn.this);
        }else{
            GlobalClass.storeString(SignIn.this.getResources().getString(R.string.name),nameTxt);
            GlobalClass.storeString(SignIn.this.getResources().getString(R.string.email_login),emailTxt);
            GlobalClass.storeString(SignIn.this.getResources().getString(R.string.pwd_login),pwdTxt);
            GlobalClass.storeString(SignIn.this.getResources().getString(R.string.address),addressTxt);
            GlobalClass.storeString(SignIn.this.getResources().getString(R.string.city),cityTxt);
            GlobalClass.storeString(SignIn.this.getResources().getString(R.string.state),stateTxt);
            GlobalClass.storeString(SignIn.this.getResources().getString(R.string.zipcode),zipcodeTxt);
            GlobalClass.storeString(SignIn.this.getResources().getString(R.string.mobile),mobileTxt);
        }

    }
}
