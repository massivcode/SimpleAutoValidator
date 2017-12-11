[![](https://jitpack.io/v/prChoe/SimpleAutoValidator.svg)](https://jitpack.io/#prChoe/SimpleAutoValidator)

# SimpleAutoValidator : Android TextInputLayout/TextInputEditText form value validate library

When we use TextInputLayout/TextInputEditText, we should coding data validation logic.

That is very very annoying and boring job.

Besides TextInputLayout doesn't support success message.

It only support displaying error message.

This library supports most used validation logic and displaying success message.

When data changed it checks all related input forms and displaying messages. 

Also when submit view was clicked it perform above jobs and notify validation states.

# Preview (Youtube)
[![Preview](https://img.youtube.com/vi/8SkbcXVwG4U/0.jpg)](https://www.youtube.com/watch?v=8SkbcXVwG4U)

# Setup

```gradle
allprojects {
	repositories {
		maven { url 'https://jitpack.io' }
	}
}
```
```gradle
dependencies {
	compile 'com.github.prChoe:SimpleAutoValidator:0.1'
}
```

# How to use

## 0. Define Error/SuccessTextAppearance at styles.xml

```xml

...

<style name="ErrorTextAppearance" parent="@android:style/TextAppearance">
  <item name="android:textColor">#F44336</item>
  <item name="android:textSize">12sp</item>
</style>

<style name="SuccessTextAppearance" parent="@android:style/TextAppearance">
  <item name="android:textColor">#2196f3</item>
  <item name="android:textSize">12sp</item>
</style>
```

## 1. Setup validation strategies

```java
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
```

ValidationStrategyHolder is data holder for save ValidationStrategy.
You could add ValidationStrategy into ValidationStrategyHolder for future use.

* When set only ErrorTextAppearance, it can displaying only error message even though success message was setted.

## 2. Add target views for data validating

```java
...
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
...
```

You should add target views and ValidationStrategy for auto data validating.

For setting ValidationStrategy at addTarget method, you could use tag name for getting ValidationStrategy from ValidationStrategyHolder or just create ValidationStrategy when you need it.

# Details

## 1. ValidationStrategy.Generator

```java
public Generator(@StyleRes int errorTextAppearance)
public Generator(@StyleRes int errorTextAppearance, @StyleRes int successTextAppearance)
```

```java
public ValidationStrategy minLength(@IntRange(from = 0) int minLength, @StringRes int errorText)
public ValidationStrategy minLength(@IntRange(from = 0) int minLength, @StringRes int errorText, @StringRes int successText)
public ValidationStrategy maxLength(@IntRange(from = 0) int maxLength, @StringRes int errorText)
public ValidationStrategy maxLength(@IntRange(from = 0) int maxLength, @StringRes int errorText, @StringRes int successText)
public ValidationStrategy email(@StringRes int errorText)
public ValidationStrategy email(@StringRes int errorText, @StringRes int successText)
public ValidationStrategy email(@NonNull String regex, @StringRes int errorText, @StringRes int successText)
public ValidationStrategy required(@StringRes int errorText)
public ValidationStrategy required(@StringRes int errorText, @StringRes int successText)
public ValidationStrategy url(@StringRes int errorText)
public ValidationStrategy url(@StringRes int errorText, @StringRes int successText)
public ValidationStrategy ip(boolean isIpV6, @StringRes int errorText)
public ValidationStrategy ip(boolean isIpV6, @StringRes int errorText, @StringRes int successText)
public ValidationStrategy textOnly(@StringRes int errorText)
public ValidationStrategy textOnly(@StringRes int errorText, @StringRes int successText)
public ValidationStrategy integerOnly(@StringRes int errorText)
public ValidationStrategy integerOnly(int errorText, int successText)
public ValidationStrategy decimalNumberOnly(@StringRes int errorText)
public ValidationStrategy decimalNumberOnly(@StringRes int errorText, @StringRes int successText)
public ValidationStrategy numberOnly(@StringRes int errorText)
public ValidationStrategy numberOnly(@StringRes int errorText, @StringRes int successText)
public ValidationStrategy regex(@NonNull String regex, @StringRes int errorText)
public ValidationStrategy regex(@NonNull String regex, @StringRes int errorText, @StringRes int successText)
public ValidationStrategy range(@IntRange(from = 0) int minLength, @IntRange(from = 0) int maxLength, @StringRes int errorText)
public ValidationStrategy range(@IntRange(from = 0) int minLength, @IntRange(from = 0) int maxLength, @StringRes int errorText, @StringRes int successText)
```

## 2. ValidationStrategyHolder

```java
public static ValidationStrategyHolder getInstance()
```
```java
public ValidationStrategyHolder addValidationStrategy(String tag, ValidationStrategy strategy)
public ValidationStrategy findByTag(String tag)
```

## 3. AutoValidator

```java
public static AutoValidator newInstance(View submitView, OnSubmitListener onSubmitListener)
```
```java
public AutoValidator addTarget(TextInputLayout textInputLayout, TextInputEditText textInputEditText, String tag)
public AutoValidator addTarget(TextInputLayout textInputLayout, TextInputEditText textInputEditText, ValidationStrategy validationStrategy)
```

# License 
 ```code
MIT License

Copyright (c) 2017 massivcode

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

```

