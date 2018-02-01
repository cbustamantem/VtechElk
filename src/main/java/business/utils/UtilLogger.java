/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package business.utils;

/**
 *
 * @author dlopez
 */
import java.text.SimpleDateFormat;
import java.util.Properties;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.Logger;


public class UtilLogger
{
   
   public static Logger logger = Logger.getLogger("log");
   public static boolean configured= false;
    
    public static void init()
    {
        
    	 Properties log4jProperties = new Properties();
         log4jProperties.setProperty("log4j.rootLogger", "ERROR, vtechlogs");
         log4jProperties.setProperty("log4j.rootLogger", "INFO, vtechlogs");
         log4jProperties.setProperty("log4j.rootLogger", "DEBUG, vtechlogs");
         log4jProperties.setProperty("log4j.rootLogger", "WARNING, vtechlogs");
         log4jProperties.setProperty("log4j.appender.vtechlogs", "org.apache.log4j.DailyRollingFileAppender");
           SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
         log4jProperties.setProperty("log4j.appender.vtechlogs.File", System.getProperty("user.home") + "/logs/vtechlogs.log");
         log4jProperties.setProperty("log4j.appender.vtechlogs.DatePattern", ".yyyy-MM-dd");
         //log4jProperties.setProperty("log4j.appender.vtechlogs.DatePattern", "drv/'.'mtsnpi.log");
         //log4jProperties.setProperty("log4j.appender.vtechlogs.ImmediateFlush", "true");
         //log4jProperties.setProperty("log4j.appender.vtechlogs.Threshold", "debug");
         //log4jProperties.setProperty("log4j.appender.vtechlogs.Append", "true");
         log4jProperties.setProperty("log4j.appender.vtechlogs.layout", "org.apache.log4j.PatternLayout");
         log4jProperties.setProperty("log4j.appender.vtechlogs.layout.ConversionPattern", "%d|%-5p|%m%n");        
         log4jProperties.setProperty("log4j.appender.vtechlogs.rollingFile.File", "%d{yyyy-MM-dd}_logs.log.gz");
         log4jProperties.setProperty("log4j.appender.vtechlogs.rollingFile.MaxFileSize", "2MB");
         //log4j.appender.FILE.ImmediateFlush=true
         //log4jProperties.setProperty("log4j.rootLogger", "INFO, vtechlogs");
        
       
         PropertyConfigurator.configure(log4jProperties);
        

         logger.debug("debug message");
         logger.info(" Inicio de actividades en log");
         configured=true;
                 
        //  LoggerGx  test = new LoggerGx();
        //test.makeLog();
    }
    public static void info (String log)
    {
        log(log);
    }
    public static void log(String log)
    {
    	if (!configured)    	
    	{
    		init();
    	}
    	logger.info("INF |"+ log);	
    	System.out.println("INF |"+ log);
        
    }
    public static void error(String log)
    {
    	if (!configured)    	
    	{
    		init();
    	}
    	logger.error("ERROR |"+ log);	
    	System.out.println("ERROR |"+ log);
        
    }
    public static void error(String log,Exception ex)
    {
    	if (!configured)    	
    	{
    		init();
    	}
        String errorMsg="";
        if(ex instanceof NullPointerException)
        {
            NullPointerException n = (NullPointerException)ex;
            StackTraceElement stackTrace = n.getStackTrace()[0];
            errorMsg= ex.getMessage() + "Exception at Class:" + stackTrace.getClassName() + " method:" +stackTrace.getMethodName() + " line:" + stackTrace.getLineNumber();
        }
        else
        {
            errorMsg=ex.toString();
        }
    	logger.error("ERROR |"+ log +" " + errorMsg);	
    	System.out.println("ERROR |"+ log + " " + ex.getMessage() + " causa:" + ex.getCause() +errorMsg);
        
    }
}
