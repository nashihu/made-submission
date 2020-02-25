package made.sub3.rest;

import android.util.Log;

import retrofit2.Call;

public class BaseFunction {

    public static void log(final String tag, final String msg) {
        final StackTraceElement stackTrace = new Exception().getStackTrace()[1];

        String fileName = stackTrace.getFileName();
        if (fileName == null) fileName="";  // It is necessary if you want to use proguard obfuscation.

        final String info = stackTrace.getMethodName() + " (" + fileName + ":"
                + stackTrace.getLineNumber() + ")";

        Log.e(tag, info + ": " + msg);
    }

    public static void proceed(Call call) {
        //we can't call method log here because the link will be lead to BaseFunction class, not client class
        final StackTraceElement stackTrace = new Exception().getStackTrace()[1];

        String fileName = stackTrace.getFileName();
        if (fileName == null) fileName="";  // It is necessary if you want to use proguard obfuscation.

        final String info = stackTrace.getMethodName() + " (" + fileName + ":"
                + stackTrace.getLineNumber() + ")";

        Log.e("url", info + ": url>> " + call.request().url().toString());
    }

    public static void proceed(Throwable t) {
        final StackTraceElement stackTrace = new Exception().getStackTrace()[1];

        String fileName = stackTrace.getFileName();
        if (fileName == null) fileName="";  // It is necessary if you want to use proguard obfuscation.

        final String info = stackTrace.getMethodName() + " (" + fileName + ":"
                + stackTrace.getLineNumber() + ")";
        Log.e("error",info + ": " +"check error message below");
        Log.e("error", t.getMessage());
    }

}
