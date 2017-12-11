package com.massivcode.simpleautovalidator;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.text.TextUtils;

/**
 * Created by massivcode@gmail.com on 2017. 12. 8. 14:06
 */

public class ValidationStrategy {

  private ValidationConfig mValidationConfig;

  private ValidationStrategy(ValidationConfig validationConfig) {
    mValidationConfig = validationConfig;
  }

  public ValidationConfig getConfig() {
    return mValidationConfig;
  }

  public static class Generator {

    private ValidationConfig mValidationConfig;

    @StyleRes
    private int mErrorTextAppearance;

    @StyleRes
    private int mSuccessTextAppearance;

    public Generator(@StyleRes int errorTextAppearance) {
      this(errorTextAppearance, -1);
    }

    public Generator(@StyleRes int errorTextAppearance, @StyleRes int successTextAppearance) {
      mErrorTextAppearance = errorTextAppearance;
      mSuccessTextAppearance = successTextAppearance;
    }

    public ValidationStrategy minLength(@IntRange(from = 0) int minLength,
        @StringRes int errorText) {
      return minLength(minLength, errorText, -1);
    }

    public ValidationStrategy minLength(@IntRange(from = 0) int minLength, @StringRes int errorText,
        @StringRes int successText) {

      if (minLength < 0) {
        throw new IllegalArgumentException("minLength can't negative!");
      }

      int validationType = ValidationTypes.MIN;

      mValidationConfig
          = new ValidationConfig(validationType, minLength, mErrorTextAppearance, errorText,
          mSuccessTextAppearance, successText);

      return build();
    }

    public ValidationStrategy maxLength(@IntRange(from = 0) int maxLength,
        @StringRes int errorText) {
      return maxLength(maxLength, errorText, -1);
    }

    public ValidationStrategy maxLength(@IntRange(from = 0) int maxLength, @StringRes int errorText,
        @StringRes int successText) {

      if (maxLength < 0) {
        throw new IllegalArgumentException("maxLength can't negative!");
      }

      int validationType = ValidationTypes.MAX;

      mValidationConfig
          = new ValidationConfig(validationType, maxLength, mErrorTextAppearance, errorText,
          mSuccessTextAppearance, successText);

      return build();
    }

    public ValidationStrategy email(@StringRes int errorText) {
      return email("\\w+@\\w+\\.\\w+(\\.\\w+)?", errorText, -1);
    }

    public ValidationStrategy email(@StringRes int errorText, @StringRes int successText) {
      return email("\\w+@\\w+\\.\\w+(\\.\\w+)?", errorText, successText);
    }

    public ValidationStrategy email(@NonNull String regex, @StringRes int errorText,
        @StringRes int successText) {

      if (TextUtils.isEmpty(regex)) {
        throw new IllegalArgumentException("regex can't empty or null!");
      }

      int validationType = ValidationTypes.EMAIL;

      mValidationConfig
          = new ValidationConfig(validationType, regex, mErrorTextAppearance, errorText,
          mSuccessTextAppearance, successText);

      return build();
    }

    public ValidationStrategy required(@StringRes int errorText) {
      return required(errorText, -1);
    }

    public ValidationStrategy required(@StringRes int errorText, @StringRes int successText) {
      int validationType = ValidationTypes.REQUIRED;

      mValidationConfig
          = new ValidationConfig(validationType, null, mErrorTextAppearance, errorText,
          mSuccessTextAppearance, successText);

      return build();
    }

    public ValidationStrategy url(@StringRes int errorText) {
      return url(errorText, -1);
    }

    public ValidationStrategy url(@StringRes int errorText, @StringRes int successText) {
      int validationType = ValidationTypes.URL;
      mValidationConfig
          = new ValidationConfig(validationType,
          "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]",
          mErrorTextAppearance, errorText, mSuccessTextAppearance, successText);

      return build();
    }

    public ValidationStrategy ip(boolean isIpV6, @StringRes int errorText) {
      return ip(isIpV6, errorText, -1);
    }

    public ValidationStrategy ip(boolean isIpV6, @StringRes int errorText,
        @StringRes int successText) {

      int validationType = ValidationTypes.IP;

      String regex = isIpV6
          ? "^(([0-9a-fA-F]{1,4}:){7,7}[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,7}:|([0-9a-fA-F]{1,4}:){1,6}:[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,5}(:[0-9a-fA-F]{1,4}){1,2}|([0-9a-fA-F]{1,4}:){1,4}(:[0-9a-fA-F]{1,4}){1,3}|([0-9a-fA-F]{1,4}:){1,3}(:[0-9a-fA-F]{1,4}){1,4}|([0-9a-fA-F]{1,4}:){1,2}(:[0-9a-fA-F]{1,4}){1,5}|[0-9a-fA-F]{1,4}:((:[0-9a-fA-F]{1,4}){1,6})|:((:[0-9a-fA-F]{1,4}){1,7}|:)|fe80:(:[0-9a-fA-F]{0,4}){0,4}%[0-9a-zA-Z]{1,}|::(ffff(:0{1,4}){0,1}:){0,1}((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9]).){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])|([0-9a-fA-F]{1,4}:){1,4}:((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9]).){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9]))$"
          : "^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";

      mValidationConfig
          = new ValidationConfig(validationType, regex, mErrorTextAppearance, errorText,
          mSuccessTextAppearance, successText);

      return build();
    }

    public ValidationStrategy textOnly(@StringRes int errorText) {
      return textOnly(errorText, -1);
    }

    public ValidationStrategy textOnly(@StringRes int errorText, @StringRes int successText) {
      int validationType = ValidationTypes.TEXT_ONLY;

      mValidationConfig
          = new ValidationConfig(validationType, "[^0-9]+", mErrorTextAppearance, errorText,
          mSuccessTextAppearance, successText);

      return build();
    }

    public ValidationStrategy integerOnly(@StringRes int errorText) {
      return integerOnly(errorText, -1);
    }

    public ValidationStrategy integerOnly(int errorText, int successText) {
      int validationType = ValidationTypes.INTEGER_ONLY;

      mValidationConfig
          = new ValidationConfig(validationType, "[0-9]+", mErrorTextAppearance, errorText,
          mSuccessTextAppearance, successText);

      return build();
    }

    public ValidationStrategy decimalNumberOnly(@StringRes int errorText) {
      return decimalNumberOnly(errorText, -1);
    }

    public ValidationStrategy decimalNumberOnly(@StringRes int errorText,
        @StringRes int successText) {
      int validationType = ValidationTypes.DECIMAL_NUMBER_ONLY;

      mValidationConfig =
          new ValidationConfig(validationType, "[0-9]+(.[0-9]+)", mErrorTextAppearance, errorText,
              mSuccessTextAppearance, successText);

      return build();
    }

    public ValidationStrategy numberOnly(@StringRes int errorText) {
      return numberOnly(errorText, -1);
    }

    public ValidationStrategy numberOnly(@StringRes int errorText, @StringRes int successText) {
      int validationType = ValidationTypes.NUMBER_ONLY;

      mValidationConfig =
          new ValidationConfig(validationType, "[0-9]+(.?[0-9]+)", mErrorTextAppearance, errorText,
              mSuccessTextAppearance, successText);

      return build();
    }

    public ValidationStrategy regex(@NonNull String regex, @StringRes int errorText) {
      return regex(regex, errorText, -1);
    }

    public ValidationStrategy regex(@NonNull String regex, @StringRes int errorText,
        @StringRes int successText) {

      if (TextUtils.isEmpty(regex)) {
        throw new IllegalArgumentException("regex can't empty or null!");
      }

      int validationType = ValidationTypes.REGEX;

      mValidationConfig
          = new ValidationConfig(validationType, regex, mErrorTextAppearance, errorText,
          mSuccessTextAppearance, successText);

      return build();
    }

    public ValidationStrategy range(@IntRange(from = 0) int minLength,
        @IntRange(from = 0) int maxLength, @StringRes int errorText) {
      return range(minLength, maxLength, errorText, -1);
    }

    public ValidationStrategy range(@IntRange(from = 0) int minLength,
        @IntRange(from = 0) int maxLength, @StringRes int errorText,
        @StringRes int successText) {
      if (minLength < 0 || maxLength < 0) {
        throw new IllegalArgumentException("minLength or maxLength can't negative!");
      }

      if (maxLength <= minLength) {
        throw new IllegalArgumentException("maxLength can't lte to minLength");
      }

      mValidationConfig = new ValidationConfig(ValidationTypes.RANGE, minLength + "," + maxLength,
          mErrorTextAppearance, errorText, mSuccessTextAppearance, successText);

      return build();
    }

    private ValidationStrategy build() {
      return new ValidationStrategy(mValidationConfig);
    }
  }
}
