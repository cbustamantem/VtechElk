/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.utils;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.ws.rs.Produces;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * @author cbustamante
 */
@Model
@Singleton
public class LoggerProducer {

   
   @Produces Logger createLogger(final InjectionPoint ip){
			return LoggerFactory.getLogger(ip.getMember().getDeclaringClass());
		}

}
