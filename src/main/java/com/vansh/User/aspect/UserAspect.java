package com.vansh.User.aspect;

import com.vansh.User.dto.CreateUserResponseDTO;
import com.vansh.User.dto.GetUserResponseDTO;
import com.vansh.User.model.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Aspect
@Component
public class UserAspect {
    private final Logger logger = LoggerFactory.getLogger(UserAspect.class);
    @Before("execution(* com.vansh.User.service.UserService.*(..))")
    public void logBeforeMethod(JoinPoint joinPoint) {
        logger.info("Calling " + joinPoint.getSignature().getName() + " with args : " + Arrays.toString(joinPoint.getArgs()));
        if(joinPoint.getSignature().getName().equals("delete") || joinPoint.getSignature().getName().equals("softDeleteUser")) {
            logger.warn("Attempting to delete user with id=" + joinPoint.getArgs()[0]);
        }
    }

    @AfterReturning(
            pointcut = "execution(* com.vansh.User.service.UserService.createUser(..)) || " +
            "execution(* com.vansh.User.service.UserService.getUser(..)) ||" +
            "execution(* com.vansh.User.service.UserService.getUsers(..))",
            returning = "data"
    )

    public void logAfterReturnMethod(JoinPoint joinPoint, Object data) {
        if(joinPoint.getSignature().getName().equals("createUser")) {
            CreateUserResponseDTO getUser = (CreateUserResponseDTO)data;
            logger.info("New user created with id:" + getUser.getId());
        } else if(joinPoint.getSignature().getName().equals("getUser")) {
            GetUserResponseDTO getUser = (GetUserResponseDTO)data;
            logger.info("Retrieving info of user : " + getUser.getId());
        } else if(joinPoint.getSignature().getName().equals("getUsers")) {
            List<GetUserResponseDTO> users = (List<GetUserResponseDTO>) data;
            users.forEach(user ->
                    logger.info("Retrieved user with id: " + user.getId())
            );
        }
    }

    @AfterThrowing(
            pointcut = "execution(* com.vansh.User.service..*.*(..))",
            throwing = "ex"
    )
    public void logException(JoinPoint joinPoint, Throwable ex) {
        logger.error("Exception happened in method : " + joinPoint.getSignature().getName());
    }

    @After(
            "execution(* com.vansh.User..*.*(..))"
    )
    public void logFinal(JoinPoint joinPoint) {
        logger.info("Finish execution of : " + joinPoint.getSignature().getName());
    }

    @Around(
            "execution(* com.vansh.User..*.*(..))"
    )
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable{
        long curTime = System.nanoTime();
        try {
            return joinPoint.proceed();
        } finally {
            long timeElapsed = (System.nanoTime() - curTime) / 1_000_000;
            String info = joinPoint.getSignature().getName() + " took " + timeElapsed + " ms";
            if(timeElapsed < 500)logger.info(info);
            else logger.warn(info);
        }
    }

}
