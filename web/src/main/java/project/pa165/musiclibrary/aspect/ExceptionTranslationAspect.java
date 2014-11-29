package project.pa165.musiclibrary.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Repository;
import project.pa165.musiclibrary.exception.PersistenceException;
import project.pa165.musiclibrary.exception.ServiceException;

/**
 * @author Milan
 */
@Aspect
@Repository
public class ExceptionTranslationAspect {

    @Pointcut("execution(* project.pa165.musiclibrary.services..*(..))")
    public void DataAccessOperation() {
    }

    @Pointcut("execution(* project.pa165.musiclibrary.dao..*(..))")
    public void ServiceLayerOperation() {
    }

    @Around("DataAccessOperation()")
    public Object translateToPersistentException(ProceedingJoinPoint joinPoint) throws PersistenceException {
        try {
            return joinPoint.proceed();
        } catch (Exception exception) {
            throw new PersistenceException(exception);
        } catch (Throwable throwable) {
            throw new PersistenceException("error during execution on dao layer", throwable);
        }
    }

    @Around("ServiceLayerOperation()")
    public Object translateToServiceException(ProceedingJoinPoint joinPoint) throws ServiceException {
        try {
            return joinPoint.proceed();
        } catch (PersistenceException exception) {
            throw new ServiceException(exception);
        } catch (Throwable throwable) {
            throw new ServiceException("error during execution on service layer", throwable);
        }
    }
}
