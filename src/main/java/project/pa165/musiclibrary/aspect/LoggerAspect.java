package project.pa165.musiclibrary.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * Aspect to log all execution of methods in project.pa165.musiclibrary package,
 * using log4j logger to log all information.
 * <p>
 * Signature of print:
 * methodName(arguments): resultOfMethodExecution
 * </p>
 *
 * @author Milan
 */
@Aspect
public class LoggerAspect {

    private final Logger LOG = LoggerFactory.getLogger(LoggerAspect.class);

    @Around(value = "execution(* project.pa165.musiclibrary..*(..)) )")
    public Object onMethodExecution(ProceedingJoinPoint joinPoint) {
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            //LOG.fatal(joinPoint.getSignature().getName() + " " + Arrays.toString(joinPoint.getArgs()), throwable);
        }
        logMessage(joinPoint.getSignature().getName(), joinPoint.getArgs(), result);
        return result;
    }

    private void logMessage(String methodName, Object[] methodArguments, Object result) {
        StringBuilder messageBuilder = new StringBuilder(methodName).append("(");
        messageBuilder.append(Arrays.toString(methodArguments));
        messageBuilder.append("): ").append(result);
        LOG.info(messageBuilder.toString());
    }
}
