package project.pa165.musiclibrary.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import project.pa165.musiclibrary.exception.DaoException;

/**
 * @author Milan
 */
@Aspect
public class ExceptionTranslationAspect {

    @Around("execution(* project.pa165.musiclibrary.dao..*(..))")
    public Object translateToDaoException(ProceedingJoinPoint joinPoint) {
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            throw new DaoException("error during execution: ", throwable);
        }
        return result;
    }
}
