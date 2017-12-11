package com.massivcode.example.simpleautovalidator;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;
import com.massivcode.simpleautovalidator.AutoValidator;
import com.massivcode.simpleautovalidator.OnSubmitListener;
import com.massivcode.simpleautovalidator.ValidationStrategy.Generator;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    TextInputLayout emailTextInputLayout = findViewById(R.id.emailTil);
    TextInputLayout ipTextInputLayout = findViewById(R.id.ipTil);
    TextInputLayout urlTextInputLayout = findViewById(R.id.urlTil);
    TextInputLayout integerOnlyTextInputLayout = findViewById(R.id.integerOnlyTil);
    TextInputLayout customRegexTextInputLayout = findViewById(R.id.customRegexTil);
    TextInputLayout min3max12TextInputLayout = findViewById(R.id.min3max12Til);

    TextInputEditText emailTextInputEditText = findViewById(R.id.emailTiet);
    TextInputEditText ipTextInputEditText = findViewById(R.id.ipTiet);
    TextInputEditText urlTextInputEditText = findViewById(R.id.urlTiet);
    TextInputEditText integerOnlyTextInputEditText = findViewById(R.id.integerOnlyTiet);
    TextInputEditText customRegexTextInputEditText = findViewById(R.id.customRegexTiet);
    TextInputEditText min3max12TextInputEditText = findViewById(R.id.min3max12Tiet);

    TextView submitView = findViewById(R.id.submitView);

    AutoValidator autoValidator = AutoValidator.newInstance(submitView, mOnSubmitListener);
    autoValidator
        .addTarget(emailTextInputLayout, emailTextInputEditText, "email")
        .addTarget(ipTextInputLayout, ipTextInputEditText, "ip")
        .addTarget(urlTextInputLayout, urlTextInputEditText, "url")
        .addTarget(integerOnlyTextInputLayout, integerOnlyTextInputEditText, "integerOnly")
        .addTarget(customRegexTextInputLayout, customRegexTextInputEditText,
            new Generator(R.style.ErrorTextAppearance,
                R.style.SuccessTextAppearance)
                .regex("^[a-zA-Z]+$", R.string.customRegexErrorMessage,
                    R.string.customRegexSuccessMessage))
        .addTarget(min3max12TextInputLayout, min3max12TextInputEditText,
            new Generator(R.style.ErrorTextAppearance,
                R.style.SuccessTextAppearance)
                .range(3, 12, R.string.minMaxErrorMessage, R.string.minMaxSuccessMessage));

  }

  private OnSubmitListener mOnSubmitListener = new OnSubmitListener() {
    @Override
    public void onValidationSuccess() {
      Toast.makeText(MainActivity.this, "onValidationSuccess", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onValidationFailed() {
      Toast.makeText(MainActivity.this, "onValidationFailed", Toast.LENGTH_SHORT).show();
    }
  };
}
