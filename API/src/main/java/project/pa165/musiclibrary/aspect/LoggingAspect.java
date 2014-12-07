package project.pa165.musiclibrary.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * @author Milan
 */
@Aspect
public class LoggingAspect {

    private final Logger LOG = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("within(project.pa165.musiclibrary..*)")
    public void inThisLayer() {
    }

    @Before("inThisLayer()")
    public void before(JoinPoint joinPoint) {
        LOG.info("before=" + getMessage(joinPoint.getSignature().getName(), joinPoint.getArgs()));
    }

    @AfterReturning(value = "inThisLayer()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        LOG.info("afterReturning=" + getMessage(joinPoint.getSignature().getName(), joinPoint.getArgs(), result));
    }

    @AfterThrowing(value = "inThisLayer()", throwing = "throwable")
    public void afterThrowing(JoinPoint joinPoint, Throwable throwable) {
        LOG.error("afterThrowing=" + getMessage(joinPoint.getSignature().getName(), joinPoint.getArgs(), throwable));
    }

    private String getMessage(String methodName, Object[] methodArguments, Object result) {
        return new StringBuilder().append("{").append(methodName).append("(").append(Arrays.toString(methodArguments)
        ).append("): ").append(result).append("}").toString();
    }

    private String getMessage(String methodName, Object[] methodArguments) {
        return new StringBuilder().append("{").append(methodName).append("(").append(Arrays.toString(methodArguments)
        ).append("}").toString();
    }
}
