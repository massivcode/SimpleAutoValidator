package com.massivcode.simpleautovalidator;

import static com.massivcode.simpleautovalidator.ValidationTypes.DECIMAL_NUMBER_ONLY;
import static com.massivcode.simpleautovalidator.ValidationTypes.EMAIL;
import static com.massivcode.simpleautovalidator.ValidationTypes.INTEGER_ONLY;
import static com.massivcode.simpleautovalidator.ValidationTypes.IP;
import static com.massivcode.simpleautovalidator.ValidationTypes.MAX;
import static com.massivcode.simpleautovalidator.ValidationTypes.MIN;
import static com.massivcode.simpleautovalidator.ValidationTypes.NUMBER_ONLY;
import static com.massivcode.simpleautovalidator.ValidationTypes.RANGE;
import static com.massivcode.simpleautovalidator.ValidationTypes.REGEX;
import static com.massivcode.simpleautovalidator.ValidationTypes.REQUIRED;
import static com.massivcode.simpleautovalidator.ValidationTypes.TEXT_ONLY;
import static com.massivcode.simpleautovalidator.ValidationTypes.URL;

import android.support.annotation.StringRes;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Created by massivcode@gmail.com on 2017. 12. 8. 14:01
 */

public class AutoValidator {

  private List<TextInputLayout> mTargetTextInputLayouts;
  private List<TextInputEditText> mTargetTextInputEditTexts;
  private View mSubmitView;
  private OnSubmitListener mOnSubmitListener;

  private AutoValidator(View submitView, OnSubmitListener onSubmitListener) {
    mTargetTextInputLayouts = new ArrayList<>();
    mTargetTextInputEditTexts = new ArrayList<>();
    mSubmitView = submitView;
    mOnSubmitListener = onSubmitListener;
    mSubmitView.setOnClickListener(mOnSubmitViewClickListener);
  }

  public static AutoValidator newInstance(View submitView, OnSubmitListener onSubmitListener) {
    return new AutoValidator(submitView, onSubmitListener);
  }

  public AutoValidator addTarget(TextInputLayout textInputLayout,
      TextInputEditText textInputEditText, String tag) {
    return addTarget(textInputLayout, textInputEditText,
        ValidationStrategyHolder.getInstance().findByTag(tag));
  }

  public AutoValidator addTarget(final TextInputLayout textInputLayout,
      TextInputEditText textInputEditText, final ValidationStrategy validationStrategy) {
    textInputLayout.setTag(validationStrategy);
    textInputLayout.setErrorEnabled(true);
    mTargetTextInputLayouts.add(textInputLayout);

    TextWatcher textWatcher = new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

      }

      @Override
      public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

      }

      @Override
      public void afterTextChanged(Editable editable) {
        if (editable == null || editable.length() == 0) {
          textInputLayout.setError(null);
          return;
        }

        validate(editable.toString(), textInputLayout, validationStrategy.getConfig());
      }
    };

    textInputEditText.addTextChangedListener(textWatcher);
    mTargetTextInputEditTexts.add(textInputEditText);
    return this;
  }

  private boolean validate(String inputValue, TextInputLayout textInputLayout,
      ValidationConfig validationConfig) {
    boolean validateResult = true;

    switch (validationConfig.getType()) {
      case MIN:
        int minLength = (int) validationConfig.getValidCondition();

        if (inputValue.length() < minLength) {
          setErrorMessage(validationConfig, textInputLayout);
          validateResult = false;
        } else if (validationConfig.isSuccessTextAvailable()) {
          setSuccessMessage(validationConfig, textInputLayout);
        } else {
          clearErrorMessage(textInputLayout);
        }

        break;
      case MAX:
        int maxLength = (int) validationConfig.getValidCondition();

        if (inputValue.length() > maxLength) {
          setErrorMessage(validationConfig, textInputLayout);
          validateResult = false;
        } else if (validationConfig.isSuccessTextAvailable()) {
          setSuccessMessage(validationConfig, textInputLayout);
        } else {
          clearErrorMessage(textInputLayout);
        }

        break;
      case EMAIL:
        String emailRegex = (String) validationConfig.getValidCondition();

        if (TextUtils.isEmpty(emailRegex)) {
          throw new IllegalStateException("Regex null!");
        }

        if (!Pattern.matches(emailRegex, inputValue)) {
          setErrorMessage(validationConfig, textInputLayout);
          validateResult = false;
        } else if (validationConfig.isSuccessTextAvailable()) {
          setSuccessMessage(validationConfig, textInputLayout);
        } else {
          clearErrorMessage(textInputLayout);
        }
        break;
      case REQUIRED:
        if (TextUtils.isEmpty(inputValue)) {
          setErrorMessage(validationConfig, textInputLayout);
          validateResult = false;
        } else if (validationConfig.isSuccessTextAvailable()) {
          setSuccessMessage(validationConfig, textInputLayout);
        } else {
          clearErrorMessage(textInputLayout);
        }
        break;
      case URL:
        String urlRegex = (String) validationConfig.getValidCondition();

        if (TextUtils.isEmpty(urlRegex)) {
          throw new IllegalStateException("Regex null!");
        }

        if (!Pattern.matches(urlRegex, inputValue)) {
          setErrorMessage(validationConfig, textInputLayout);
          validateResult = false;
        } else if (validationConfig.isSuccessTextAvailable()) {
          setSuccessMessage(validationConfig, textInputLayout);
        } else {
          clearErrorMessage(textInputLayout);
        }

        break;
      case IP:
        String ipRegex = (String) validationConfig.getValidCondition();

        if (TextUtils.isEmpty(ipRegex)) {
          throw new IllegalStateException("Regex null!");
        }

        if (!Pattern.matches(ipRegex, inputValue)) {
          setErrorMessage(validationConfig, textInputLayout);
          validateResult = false;
        } else if (validationConfig.isSuccessTextAvailable()) {
          setSuccessMessage(validationConfig, textInputLayout);
        } else {
          clearErrorMessage(textInputLayout);
        }
        break;
      case TEXT_ONLY:
        String textOnlyRegex = (String) validationConfig.getValidCondition();

        if (TextUtils.isEmpty(textOnlyRegex)) {
          throw new IllegalStateException("Regex null!");
        }

        if (!Pattern.matches(textOnlyRegex, inputValue)) {
          setErrorMessage(validationConfig, textInputLayout);
          validateResult = false;
        } else if (validationConfig.isSuccessTextAvailable()) {
          setSuccessMessage(validationConfig, textInputLayout);
        } else {
          clearErrorMessage(textInputLayout);
        }
        break;
      case INTEGER_ONLY:
        String integerOnlyRegex = (String) validationConfig.getValidCondition();

        if (TextUtils.isEmpty(integerOnlyRegex)) {
          throw new IllegalStateException("Regex null!");
        }

        if (!Pattern.matches(integerOnlyRegex, inputValue)) {
          setErrorMessage(validationConfig, textInputLayout);
          validateResult = false;
        } else if (validationConfig.isSuccessTextAvailable()) {
          setSuccessMessage(validationConfig, textInputLayout);
        } else {
          clearErrorMessage(textInputLayout);
        }
        break;
      case DECIMAL_NUMBER_ONLY:
        String decimalNumberOnlyRegex = (String) validationConfig.getValidCondition();

        if (TextUtils.isEmpty(decimalNumberOnlyRegex)) {
          throw new IllegalStateException("Regex null!");
        }

        if (!Pattern.matches(decimalNumberOnlyRegex, inputValue)) {
          setErrorMessage(validationConfig, textInputLayout);
          validateResult = false;
        } else if (validationConfig.isSuccessTextAvailable()) {
          setSuccessMessage(validationConfig, textInputLayout);
        } else {
          clearErrorMessage(textInputLayout);
        }
        break;
      case NUMBER_ONLY:
        String numberOnlyRegex = (String) validationConfig.getValidCondition();

        if (TextUtils.isEmpty(numberOnlyRegex)) {
          throw new IllegalStateException("Regex null!");
        }

        if (!Pattern.matches(numberOnlyRegex, inputValue)) {
          setErrorMessage(validationConfig, textInputLayout);
          validateResult = false;
        } else if (validationConfig.isSuccessTextAvailable()) {
          setSuccessMessage(validationConfig, textInputLayout);
        } else {
          clearErrorMessage(textInputLayout);
        }
        break;
      case REGEX:
        String customRegex = (String) validationConfig.getValidCondition();

        if (TextUtils.isEmpty(customRegex)) {
          throw new IllegalStateException("Regex null!");
        }

        if (!Pattern.matches(customRegex, inputValue)) {
          setErrorMessage(validationConfig, textInputLayout);
          validateResult = false;
        } else if (validationConfig.isSuccessTextAvailable()) {
          setSuccessMessage(validationConfig, textInputLayout);
        } else {
          clearErrorMessage(textInputLayout);
        }

        break;
      case RANGE:
        String rangeString = (String) validationConfig.getValidCondition();
        String[] split = rangeString.split(",");

        int minLengthInteger = Integer.parseInt(split[0]);
        int maxLengthInteger = Integer.parseInt(split[1]);

        int inputValueLength = inputValue.length();

        if (inputValueLength < minLengthInteger || inputValueLength > maxLengthInteger) {
          setErrorMessage(validationConfig, textInputLayout);
          validateResult = false;
        } else if (validationConfig.isSuccessTextAvailable()) {
          setSuccessMessage(validationConfig, textInputLayout);
        } else {
          clearErrorMessage(textInputLayout);
        }

        break;
    }

    return validateResult;
  }

  private String getString(TextInputLayout textInputLayout, @StringRes int messageRes) {
    return textInputLayout.getContext().getString(messageRes);
  }

  private void setErrorMessage(ValidationConfig validationConfig, TextInputLayout textInputLayout) {
    textInputLayout.setErrorTextAppearance(validationConfig.getErrorTextAppearance());
    textInputLayout.setError(getString(textInputLayout, validationConfig.getErrorText()));
  }

  private void setSuccessMessage(ValidationConfig validationConfig,
      TextInputLayout textInputLayout) {
    textInputLayout.setErrorTextAppearance(validationConfig.getSuccessTextAppearance());
    textInputLayout.setError(getString(textInputLayout, validationConfig.getSuccessText()));
  }

  private void clearErrorMessage(TextInputLayout textInputLayout) {
    textInputLayout.setError(null);
  }

  private OnClickListener mOnSubmitViewClickListener = new OnClickListener() {
    @Override
    public void onClick(View view) {
      if (validateAllViews()) {
        mOnSubmitListener.onValidationSuccess();
      } else {
        mOnSubmitListener.onValidationFailed();
      }
    }
  };

  private boolean validateAllViews() {
    Set<Boolean> isValidAll = new HashSet<>();

    for (int i = 0; i < mTargetTextInputLayouts.size(); i++) {
      TextInputLayout eachTextInputLayout = mTargetTextInputLayouts.get(i);
      ValidationStrategy validationStrategy = (ValidationStrategy) eachTextInputLayout.getTag();

      if (validationStrategy == null) {
        throw new IllegalStateException("ValidationStrategy null!");
      }

      TextInputEditText eachTextInputEditText = mTargetTextInputEditTexts.get(i);
      String inputValue = eachTextInputEditText.getText().toString();

      isValidAll.add(validate(inputValue, eachTextInputLayout, validationStrategy.getConfig()));
    }

    return !isValidAll.contains(false);
  }
}
