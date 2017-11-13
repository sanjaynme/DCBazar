package com.dcbazar.np.helpers;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Point;
import android.provider.Settings;
import android.text.format.DateUtils;
import android.util.Base64;
import android.util.Log;
import android.util.Patterns;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by sanjay on 9/7/2015.
 */
public class CommonMethods {

    public static String getDeviceId(Activity activity) {
        return Settings.Secure.getString(activity.getContentResolver(), Settings.Secure.ANDROID_ID);
    }


    public static void setupUI(View view, final Activity baseActivity) {
        //Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(baseActivity);
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView, baseActivity);
            }
        }
    }

    public static void hideSoftKeyboard(Activity activity) {
        // Check if no view has focus: and hide the soft keyboard
        View view = activity.getCurrentFocus();
        //Checking if view!=null
        // to prevent java.lang.NullPointerException: Attempt to invoke virtual method 'android.os.IBinder android.view.View.getWindowToken()' on a null object reference
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static String printKeyHash(Activity context) {
        PackageInfo packageInfo;
        String key = null;
        try {
            //getting application package name, as defined in manifest
            String packageName = context.getApplicationContext().getPackageName();

            //Retriving package info
            packageInfo = context.getPackageManager().getPackageInfo(packageName,
                    PackageManager.GET_SIGNATURES);

            Log.e("Package Name=", context.getApplicationContext().getPackageName());

            for (Signature signature : packageInfo.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                key = new String(Base64.encode(md.digest(), 0));

                // String key = new String(Base64.encodeBytes(md.digest()));
                Log.e("Key Hash=", key);
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("Name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("No such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("Exception", e.toString());
        }

        return key;
    }

    /**
     * @return : file name with jpt extension
     */
    public static String randomFileName(String ext) {
        String fileName;
        fileName = String.format("%s.%s", System.currentTimeMillis(), ext);
        return fileName;
    }

    public static Point getDisplaySize(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        return size;
    }

    public static void expand(final View v) {
        v.measure(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        final int targetHeight = v.getMeasuredHeight();
        Log.d("height", String.valueOf(targetHeight));

        // Older versions of android (pre API 21) cancel animations for views with a height of 0.
        v.getLayoutParams().height = 1;
        v.setVisibility(View.VISIBLE);
        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().height = interpolatedTime == 1
                        ? RelativeLayout.LayoutParams.WRAP_CONTENT
                        : (int) (targetHeight * interpolatedTime);
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration(2 * (int) (targetHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }

    public static void collapse(final View v) {
        final int initialHeight = v.getMeasuredHeight();

        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    v.setVisibility(View.GONE);
                } else {
                    v.getLayoutParams().height = initialHeight - (int) (initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration(2 * (int) (initialHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }

    public static String loadJSONFromAsset(Activity activity, String jsonFileName) {
        String json = null;
        try {
            InputStream is = activity.getAssets().open(jsonFileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public static void toastMessage(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static boolean isValidEmaillId(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();

    }

    public static boolean isEmailValid(String emailAddress) {
        boolean isValidEmail = true;
        if (!emailAddress.isEmpty()) {
            isValidEmail = Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches();
        } else {
            isValidEmail = false;
        }
        return isValidEmail;
    }


    public static Date convertStringToDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = null;
        try {
            date1 = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        AppLog.d("date", date1 + "");
        return date1;
    }

    public static Date convertSurveyStartEndStringToDate(String date) {
        Date date1 = null;
        if (date != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            date1 = null;
            try {
                date1 = dateFormat.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return date1;
    }

    public static String getTimeAgo(String time) {
        // it comes out like this 2013-08-31 15:55:22 so adjust the date format
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        long epoch = 0;
        try {
            Date value = df.parse(time);
            Log.d("date", "" + value);
            epoch = value.getTime();

        } catch (ParseException e) {
            e.printStackTrace();
        }

        CharSequence timePassedString = DateUtils.getRelativeTimeSpanString(epoch, System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS);
        return timePassedString.toString();
    }

    public static Date convertLocalTimeStringToDate(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
        Date formatDate = null;
        try {
            formatDate = formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatDate;
    }

    public static String getStartDate(int recurringTypeId, Date startDate) {
        String finalStartDate = null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);

        int givenYear = calendar.get(Calendar.YEAR);
        int givenMonth = calendar.get(Calendar.MONTH) + 1;
        int givenDay = calendar.get(Calendar.DAY_OF_MONTH);
        int givenDayofWeek = calendar.get(Calendar.DAY_OF_WEEK);

        switch (recurringTypeId) {
            case Contracts.RepeatingType.ANNUAL:
                finalStartDate = givenYear + "-" + "01-01";
                break;
            case Contracts.RepeatingType.SEMI_ANNUALLY:
                if (givenMonth <= 6) {
                    finalStartDate = givenYear + "-01-01";
                } else {
                    finalStartDate = givenYear + "-07-01";
                }
                break;
            case Contracts.RepeatingType.QUARTERLY:
                if (givenMonth <= 3) {
                    finalStartDate = givenYear + "-01-01";
                } else if (givenMonth > 3 && givenMonth <= 6) {
                    finalStartDate = givenYear + "-04-01";

                } else if (givenMonth > 6 && givenMonth <= 9) {
                    finalStartDate = givenYear + "-07-01";

                } else if (givenMonth > 9 && givenMonth <= 12) {
                    finalStartDate = givenYear + "-10-01";
                }
                break;
            case Contracts.RepeatingType.MONTHLY:
                finalStartDate = givenYear + "-" + givenMonth + "-01";
                break;
            case Contracts.RepeatingType.WEEKLY:
                int firstDay = Calendar.MONDAY;

                int value = givenDayofWeek - firstDay;
                int resultDay = givenDay - value;

                finalStartDate = String.valueOf(givenYear + "-" + givenMonth + "-" + resultDay);
                break;

        }
        return finalStartDate;
    }

    public static String getEndDate(int recurringTypeId, Date endDate) {
        String finalEndDate = null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(endDate);

        int givenYear = calendar.get(Calendar.YEAR);
        int givenMonth = calendar.get(Calendar.MONTH) + 1;
        int givenDay = calendar.get(Calendar.DAY_OF_MONTH);
        int givenDayofWeek = calendar.get(Calendar.DAY_OF_WEEK);

        int lastDayOfmonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        switch (recurringTypeId) {

            case Contracts.RepeatingType.ANNUAL:
                finalEndDate = givenYear + "-" + "12/31";
                break;
            case Contracts.RepeatingType.SEMI_ANNUALLY:
                if (givenMonth <= 6) {
                    finalEndDate = givenYear + "-06" + lastDayOfmonth;
                } else {
                    finalEndDate = givenYear + "-12" + lastDayOfmonth;
                }
                break;
            case Contracts.RepeatingType.QUARTERLY:

                if (givenMonth <= 3) {
                    finalEndDate = givenYear + "-03-" + lastDayOfmonth;
                } else if (givenMonth > 3 && givenMonth <= 6) {
                    finalEndDate = givenYear + "-06-" + lastDayOfmonth;
                } else if (givenMonth > 6 && givenMonth <= 9) {
                    finalEndDate = givenYear + "-09-" + lastDayOfmonth;
                } else if (givenMonth > 9 && givenMonth <= 12) {
                    finalEndDate = givenYear + "-12-" + lastDayOfmonth;
                }
                break;
            case Contracts.RepeatingType.MONTHLY:

                finalEndDate = String.valueOf(givenYear + "-" + givenMonth + "-" + lastDayOfmonth);
                break;

            case Contracts.RepeatingType.WEEKLY:
                int lastDayOfWeek = Calendar.SATURDAY;

                int value = (lastDayOfWeek - givenDayofWeek) + 1;

                int resultDay = givenDay + value;
                finalEndDate = String.valueOf(givenYear + "-" + givenMonth + "-" + resultDay);
                break;
        }
        return finalEndDate;
    }

    public static Date convertDeviceDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
}
