package project.pa165.musiclibrary.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import project.pa165.musiclibrary.exception.PersistenceException;
import project.pa165.musiclibrary.exception.ServiceException;

import javax.inject.Named;

/**
 * @author Milan
 */
@Aspect
@Named
public class ExceptionTranslationAspect {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionTranslationAspect.class);

    @Pointcut("execution(* project.pa165.musiclibrary.dao..*(..))")
    public void DataAccessOperation() {
    }

    @Pointcut("execution(* project.pa165.musiclibrary.services..*(..))")
    public void ServiceLayerOperation() {
    }

    @Around("DataAccessOperation()")
    public Object translateToPersistentException(ProceedingJoinPoint joinPoint) throws PersistenceException {
        try {
            logger.debug("before running method={}, with arguments={}",
                    joinPoint.getSignature().getName(), joinPoint.getArgs());
            return joinPoint.proceed();
        } catch (Exception exception) {
            logger.error("exception during execution on dao layer:", exception);
            throw new PersistenceException(exception);
        } catch (Throwable throwable) {
            logger.error("error during execution on dao layer:", throwable);
            throw new PersistenceException(throwable);
        }
    }

    @Around("ServiceLayerOperation()")
    public Object translateToServiceException(ProceedingJoinPoint joinPoint) throws ServiceException {
        try {
            logger.debug("before running method={}, with arguments={}",
                    joinPoint.getSignature().getName(), joinPoint.getArgs());
            return joinPoint.proceed();
        } catch (PersistenceException exception) {
            logger.error("exception during execution on service layer:", exception);
            throw new ServiceException(exception);
        } catch (Throwable throwable) {
            logger.error("error during execution on service layer:", throwable);
            throw new ServiceException(throwable);
        }
    }
}
