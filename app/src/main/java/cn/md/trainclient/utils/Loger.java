package cn.md.trainclient.utils;


import android.util.Log;


public class Loger {
    private static String tag = "jdp";
    //日志等级  verbose输出所有log
    public static int logLevel = Log.VERBOSE;

    public static boolean isDebug = true;

    public static void i(String mTag, Object str) {
        if (isDebug) {
            info(mTag, str);
        }
    }

    public static void i(Object str) {
        if (isDebug) {
            info(null, str);
        }
    }


    public static void v(String mTag, Object str) {
        if (isDebug) {
            verbose(mTag, str);
        }
    }

    public static void v(Object str) {
        if (isDebug) {
            verbose(null, str);
        }
    }


    public static void w(String mTag, Object str) {
        if (isDebug) {
            warn(mTag, str);
        }
    }

    public static void w(Object str) {
        if (isDebug) {
            warn(null, str);
        }
    }


    public static void e(String mTag, Object str) {
        if (isDebug) {
            error(mTag, str);
        }
    }

    public static void e(Object str) {
        if (isDebug) {
            error(null, str);
        }
    }

    public static void e(String mTag, Exception ex) {
        if (isDebug) {
            error(mTag, ex);
        }
    }

    public static void e(Exception ex) {
        if (isDebug) {
            error(null, ex);
        }
    }


    public static void d(String mTag, Object str) {
        if (isDebug) {
            debug(mTag, str);
        }
    }

    public static void d(Object str) {
        if (isDebug) {
            debug(null, str);
        }
    }

    private static String getFunctionName() {
        StackTraceElement[] sts = Thread.currentThread().getStackTrace();

        if (sts == null) {
            return null;
        }


        for (StackTraceElement st : sts) {
            /*
             * 过滤掉堆栈方
        	 */
            if (st.isNativeMethod()) {
                continue;
            }
            /*
             * 过滤掉线程方
             */
            if (st.getClassName().equals(Thread.class.getName())) {
                continue;
            }
            /*
             * 过滤掉该类方
             */
            if (st.getClassName().equals(Loger.class.getName())) {
                continue;
            }

            return "[" + "ThreadId" + Thread.currentThread().getId() + ": " + st.getFileName() + ":" + st.getLineNumber() + "]";
        }

        return null;
    }


    private static void verbose(String mTag, Object str) {
        if (logLevel <= Log.VERBOSE) {
            String name = getFunctionName();
            String ls = (name == null ? str.toString() : (name + " - " + str));
            if (mTag != null) {
                Log.v(mTag, ls);
            } else {
                Log.v(tag, ls);
            }
        }
    }

    private static void info(String mTag, Object str) {
        if (logLevel <= Log.INFO) {
            String name = getFunctionName();
            String ls = (name == null ? str.toString() : (name + " - " + str));
            if (mTag != null) {
                Log.v(mTag, ls);
            } else {
                Log.v(tag, ls);
            }
        }
    }

    private static void debug(String mTag, Object str) {
        if (logLevel <= Log.DEBUG) {
            String name = getFunctionName();
            String ls = (name == null ? str.toString() : (name + " - " + str));
            if (mTag != null) {
                Log.v(mTag, ls);
            } else {
                Log.v(tag, ls);
            }
        }
    }

    private static void error(String mTag, Object str) {
        if (logLevel <= Log.ERROR) {
            String name = getFunctionName();
            String ls = (name == null ? str.toString() : (name + " - " + str));
            if (mTag != null) {
                Log.v(mTag, ls);
            } else {
                Log.v(tag, ls);
            }
        }
    }

    private static void error(String mTag, Exception ex) {
        if (logLevel <= Log.ERROR) {
            StringBuffer sb = new StringBuffer();
            String name = getFunctionName();
            StackTraceElement[] sts = ex.getStackTrace();

            if (name != null) {
                sb.append(name + " - " + ex + "\r\n");
            } else {
                sb.append(ex + "\r\n");
            }

            if (sts != null && sts.length > 0) {
                for (StackTraceElement st : sts) {
                    if (st != null) {
                        sb.append("[ " + st.getFileName() + ":" + st.getLineNumber() + " ]\r\n");
                    }
                }
            }
            if (mTag != null) {
                Log.v(mTag, sb.toString());
            } else {
                Log.v(tag, sb.toString());
            }
        }
    }

    private static void warn(String mTag, Object str) {
        if (logLevel <= Log.WARN) {
            String name = getFunctionName();
            String ls = (name == null ? str.toString() : (name + " - " + str));
            if (mTag != null) {
                Log.v(mTag, ls);
            } else {
                Log.v(tag, ls);
            }
        }
    }

}