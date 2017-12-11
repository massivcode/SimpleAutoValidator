package com.massivcode.simpleautovalidator;

import java.util.HashMap;

/**
 * Created by massivcode@gmail.com on 2017. 12. 9. 16:21
 */

public class ValidationStrategyHolder {

  private static ValidationStrategyHolder sInstance = null;
  private HashMap<String, ValidationStrategy> mValidationStrategyMap;

  private ValidationStrategyHolder() {
    mValidationStrategyMap = new HashMap<>();
  }

  public static ValidationStrategyHolder getInstance() {
    if (sInstance == null) {
      sInstance = new ValidationStrategyHolder();
    }

    return sInstance;
  }

  public ValidationStrategyHolder addValidationStrategy(String tag, ValidationStrategy strategy) {
    mValidationStrategyMap.put(tag, strategy);
    return sInstance;
  }

  public ValidationStrategy findByTag(String tag) {
    return mValidationStrategyMap.get(tag);
  }
}
