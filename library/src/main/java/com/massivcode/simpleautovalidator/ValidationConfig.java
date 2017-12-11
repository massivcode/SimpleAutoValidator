package com.massivcode.simpleautovalidator;

import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;

/**
 * Created by massivcode@gmail.com on 2017. 12. 8. 14:22
 */

public class ValidationConfig {

  @ValidationTypes
  private int type;
  private Object validCondition;

  @StyleRes
  private int errorTextAppearance;

  @StringRes
  private int errorText;

  @StyleRes
  private int successTextAppearance;

  @StringRes
  private int successText;

  public ValidationConfig(@ValidationTypes int type, Object validCondition, @StyleRes int
      errorTextAppearance,
      @StringRes int errorText) {
    this.type = type;
    this.validCondition = validCondition;
    this.errorTextAppearance = errorTextAppearance;
    this.errorText = errorText;
  }

  public ValidationConfig(@ValidationTypes int type, Object validCondition,
      @StyleRes int errorTextAppearance, @StringRes int errorText,
      @StyleRes int successTextAppearance, @StringRes int successText) {
    this.type = type;
    this.validCondition = validCondition;
    this.errorTextAppearance = errorTextAppearance;
    this.errorText = errorText;
    this.successTextAppearance = successTextAppearance;
    this.successText = successText;
  }

  @ValidationTypes
  public int getType() {
    return type;
  }

  public Object getValidCondition() {
    return validCondition;
  }

  @StyleRes
  public int getErrorTextAppearance() {
    return errorTextAppearance;
  }

  @StringRes
  public int getErrorText() {
    return errorText;
  }

  @StyleRes
  public int getSuccessTextAppearance() {
    return successTextAppearance;
  }

  @StringRes
  public int getSuccessText() {
    return successText;
  }

  public boolean isSuccessTextAvailable() {
    return successText != -1 && successTextAppearance != -1;
  }

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("ValidationConfig{");
    sb.append("type=").append(type);
    sb.append(", validCondition=").append(validCondition);
    sb.append(", errorTextAppearance=").append(errorTextAppearance);
    sb.append(", errorText=").append(errorText);
    sb.append(", successTextAppearance=").append(successTextAppearance);
    sb.append(", successText=").append(successText);
    sb.append('}');
    return sb.toString();
  }
}
