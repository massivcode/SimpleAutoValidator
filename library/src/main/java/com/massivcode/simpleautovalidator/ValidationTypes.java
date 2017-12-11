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

import android.support.annotation.IntDef;

/**
 * Created by massivcode@gmail.com on 2017. 12. 8. 14:07
 */

@IntDef({MIN, MAX, EMAIL, REQUIRED, URL, IP, TEXT_ONLY, INTEGER_ONLY, DECIMAL_NUMBER_ONLY,
    NUMBER_ONLY, REGEX, RANGE})
public @interface ValidationTypes {

  int MIN = 0;
  int MAX = 1;
  int EMAIL = 2;
  int REQUIRED = 3;
  int URL = 4;
  int IP = 5;
  int TEXT_ONLY = 6;
  int INTEGER_ONLY = 7;
  int DECIMAL_NUMBER_ONLY = 8;
  int NUMBER_ONLY = 9;
  int REGEX = 10;
  int RANGE = 11;
}
