package com.workmarket.androidtest.tests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Created by hkhaliq on 2017-02-16.
 */
public abstract class ShellCommand {
    public static final Logger log = Logger.getLogger(Class.class.getName());

    public static String executeQuietly(String command) {
        return executeAndLog(command, false, false);
    }

    public static String executeQuietlyWithNewlines(String command) {
        return executeAndLog(command, false, true);
    }

    public static String execute(String command) {
        return executeAndLog(command, true, false);
    }

    public static String executeAndLog(String command, boolean verbose, boolean newlines) {
        final StringBuffer output = new StringBuffer();
        Process p = null;
        BufferedReader reader = null;
        log.info(String.format("Executing cmd: %s", command));
        try {
            p = Runtime.getRuntime().exec(command);
            if (!p.waitFor(300, TimeUnit.SECONDS)){
                p.destroyForcibly();
                log.severe(String.format("Command '%s' timed out!", command));
            }
            reader = new BufferedReader(new InputStreamReader(p.getInputStream(), Charset.forName("UTF-8")));
            String line = "";
            while ((line = reader.readLine()) != null) {
                output.append(line);
                if (newlines) {
                    output.append("\n");
                }
            }
            reader.close();
        } catch (IOException | InterruptedException e) {
            log.severe(e.toString());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    log.warning("Error closing buffered reader " + e.toString());
                }
            }
            if (p != null) {
                p.destroy();
            }
        }
        if (verbose) {
            log.info(output.toString());
        }
        return output.toString();
    }

    public static boolean executeBoolQuietly(String command) {
        return executeBoolAndLog(command, false);
    }

    public static boolean executeBool(String command) {
        return executeBoolAndLog(command, true);
    }

    public static boolean executeBoolAndLog(String command, boolean verbose) {
        Process p = null;
        boolean result = true;
        log.info(String.format("Executing cmd: %s", command));
        try {
            p = Runtime.getRuntime().exec(command);
            final boolean processFinished = p.waitFor(300, TimeUnit.SECONDS);
            if (!processFinished){
                p.destroyForcibly();
                log.severe(String.format("Command '%s' timed out!", command));
                result = false;
            } else if (p.exitValue() != 0) {
                log.severe(String.format("Command '%s' exited with non-zero status!", command));
                result = false;
            }
            if (verbose) {
                printExecLogs(p);
            }
        } catch (IOException | InterruptedException e) {
            log.severe(e.toString());
            result = false;
        } catch (IllegalThreadStateException e) {
            log.severe(String.format("Could not get exit value for command: '%s'", command));
            result = false;
        } finally {
            if (p != null) {
                p.destroy();
            }
        }
        return result;
    }

    /**
     * Log the process std out and std err
     * Note: This will clear the output buffers of the process
     * @param proc
     */
    private static void printExecLogs(Process proc){
        BufferedReader stdInput = null;
        BufferedReader stdError = null;
        try {
            stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream(), Charset.forName("UTF-8")));
            stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream(), Charset.forName("UTF-8")));
            String s;
            while ((s = stdInput.readLine()) != null) {
                log.info(s);
            }
            while ((s = stdError.readLine()) != null) {
                log.warning(s);
            }
        } catch (IOException e) {
            log.severe("Problem getting process logs!");
        } finally {
            try {
                if (stdInput!=null){
                    stdInput.close();
                }
            } catch (IOException e) {
                log.warning("Problem closing input stream readers");
            }
            try {
                if (stdError!=null) {
                    stdError.close();
                }
            } catch (IOException e) {
                log.warning("Problem closing error stream readers");
            }
        }
    }
}
