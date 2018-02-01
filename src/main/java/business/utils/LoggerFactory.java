/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.utils;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.ws.rs.Produces;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 *
 * @author cbustamante
 */
@ApplicationScoped
public class LoggerFactory {

    static {
        BasicConfigurator.configure();
    }

    @Produces
    public Logger produceLog(InjectionPoint ip) {
        return Logger.getLogger(ip.getMember().getDeclaringClass().getName());
    }

    @Produces
    @LogType(loggerType = LogType.LoggerType.CRITICAL)
    public Logger produceCriticalLogger(InjectionPoint injectionPoint) {
        return Logger.getLogger("critical");
    }

    @Produces
    @LogType(loggerType = LogType.LoggerType.SERVICE_TIMING)
    public Logger produceServiceTimingLogger(InjectionPoint injectionPoint) {
        return Logger.getLogger("serviceTiming");
    }

    @Produces
    @LogType(loggerType = LogType.LoggerType.HEALTH)
    public Logger produceHealthLogger(InjectionPoint injectionPoint) {
        return Logger.getLogger("health");
    }

    @Produces
    @LogType(loggerType = LogType.LoggerType.HTTP_HEADER)
    public Logger produceHttpLogger(InjectionPoint injectionPoint) {
        return Logger.getLogger("httpHeader");
    }

    @Produces
    @LogType(loggerType = LogType.LoggerType.POSTED_REQUEST)
    public Logger producePostRequestLogger(InjectionPoint injectionPoint) {
        System.out.println(" ********* getAnnotated" + injectionPoint.getAnnotated());
        System.out.println(injectionPoint.toString());

        return Logger.getLogger("postedRequest");
    }

    @Produces
    @LogType(loggerType = LogType.LoggerType.DB_TIMING)
    public Logger produceDbTimingLogger(InjectionPoint injectionPoint) {
        return Logger.getLogger("dbTiming");
    }

    @Produces
    @LogType(loggerType = LogType.LoggerType.STACK_TRACE)
    public Logger produceStackTraceLogger(InjectionPoint injectionPoint) {
        return Logger.getLogger("stackTrace");
    }

    @Produces
    @LogType(loggerType = LogType.LoggerType.TRANSACTIONAL)
    public Logger produceTransactionsLogger(InjectionPoint injectionPoint) {
        return Logger.getLogger("transactions");
    }
}
