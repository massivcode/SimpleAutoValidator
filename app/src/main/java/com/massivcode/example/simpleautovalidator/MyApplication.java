package com.massivcode.example.simpleautovalidator;

import android.app.Application;
import com.massivcode.simpleautovalidator.ValidationStrategy.Generator;
import com.massivcode.simpleautovalidator.ValidationStrategyHolder;

/**
 * Created by massivcode@gmail.com on 2017. 12. 11. 12:05
 */

public class MyApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();

    ValidationStrategyHolder
        .getInstance()
        .addValidationStrategy("email", new Generator(R.style.ErrorTextAppearance,
            R.style.SuccessTextAppearance)
            .email(R.string.emailErrorMessage, R.string.emailSuccessMessage))
        .addValidationStrategy("ip", new Generator(R.style.ErrorTextAppearance)
            .ip(false, R.string.ipErrorMessage))
        .addValidationStrategy("url", new Generator(R.style.ErrorTextAppearance,
            R.style.SuccessTextAppearance)
            .url(R.string.urlErrorMessage))
        .addValidationStrategy("integerOnly",
            new Generator(R.style.ErrorTextAppearance,
                R.style.SuccessTextAppearance)
                .integerOnly(R.string.integerOnlyErrorMessage, R.string.integerOnlySuccessMessage));
  }

}
