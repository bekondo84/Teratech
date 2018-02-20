/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatim.common.logger;

import com.megatim.common.context.ToolsContext;
import java.io.File;
import java.io.IOException;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

/**
 *
 * @author BAKARI
 */
public class CustomLogger {

    private static Logger logger;

    /**
     *
     * @param className
     * @param methodeName
     * @param msg
     * @param fileName
     * @param level
     */
    public static void logger(Class className, String methodeName, String msg, String fileName, Level level) {

        if (fileName == null || fileName.trim().isEmpty()) {
            return;
        }

        try {
            logger = Logger.getLogger(className);

            PatternLayout layout = new PatternLayout(getPatternLayout(className, methodeName));

            StringBuilder buffer = new StringBuilder(File.separator + "/logs" + File.separator);
            buffer.append(fileName);
            buffer.append(".log");
            FileAppender appender = new FileAppender(layout, System.getProperty("user.dir") + buffer.toString());

            logger.addAppender(appender);
            if (level == Level.INFO) {
                logger.info(msg);
            } else if (level.equals(Level.WARN)) {
                logger.warn(msg);
            } else if (level.equals(Level.ERROR)) {
                logger.error(msg);
            }
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(CustomLogger.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param className
     * @param methodeName
     * @param msg
     */
    public static void loggerWriteError(Class className, String methodeName, String msg) {
        try {
            logger = Logger.getLogger(className);

            logger.setLevel(Level.ERROR);

            PatternLayout layout = new PatternLayout(getPatternLayout(className, methodeName));

            if (ToolsContext.REP_LOG == null || ToolsContext.REP_LOG.trim().isEmpty()) {

            } else {
                FileAppender appender = new FileAppender(layout, ToolsContext.REP_LOG);

                logger.addAppender(appender);
                logger.error(msg);
            }

        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(CustomLogger.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    public static void loggerWriteWarning(Class className, String methodeName, String msg) throws IOException {
        logger = Logger.getLogger(className);

        logger.setLevel(Level.WARN);

        PatternLayout layout = new PatternLayout(getPatternLayout(className, methodeName));
        FileAppender appender = new FileAppender(layout, System.getProperty("user.dir") + File.separator + "/logs/error.log");
        logger.addAppender(appender);
        logger.warn(msg);
    }

    public static void loggerWriteInfos(Class className, String methodeName, String msg) throws IOException {
        logger = Logger.getLogger(className);

        logger.setLevel(Level.INFO);

        PatternLayout layout = new PatternLayout(getPatternLayout(className, methodeName));
        FileAppender appender = new FileAppender(layout, System.getProperty("user.dir") + File.separator + "/logs/infos.log");
        logger.addAppender(appender);
        logger.info(msg);
    }

    private static String getPatternLayout(Class className, String methodName) {
        StringBuilder motif = new StringBuilder();
        motif.append("Date/Hour : %d{yyyy-MM-dd HH:mm:ss.SSS}  ");
        motif.append("Gravity : %p ");
        motif.append("  ClassName: " + className.getName());
        motif.append("  MethodName :" + methodName);
        motif.append("  Message: %m %n");
        return motif.toString();
    }
}
