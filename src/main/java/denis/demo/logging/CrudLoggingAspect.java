package denis.demo.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CrudLoggingAspect {

    private final ExternalLogService logService;

    public CrudLoggingAspect(ExternalLogService logService) {
        this.logService = logService;
    }

    @AfterReturning("execution(* denis.demo.controller.ItemController.create(..)) || " +
            "execution(* denis.demo.controller.ItemController.update(..)) || " +
            "execution(* denis.demo.controller.ItemController.delete(..)) || " +
            "execution(* denis.demo.controller.ItemController.getAll(..)) || " +
            "execution(* denis.demo.controller.ItemController.getById(..))")
    public void afterCrud(JoinPoint jp) {
        String method = jp.getSignature().getName();
        logService.log("Залогирован метод: " + method);
    }
}
