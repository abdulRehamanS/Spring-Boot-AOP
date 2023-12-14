package In.Abdul.aop;

import In.Abdul.entity.Employee;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
public class EmployeeAspect {

    @Before(value = "execution(* In.Abdul.controller.EmployeeController.*(..))")
    public void beforeAdvice(JoinPoint joinPoint) {
        System.out.println(" Method called at" + joinPoint.getSignature() + " started at " + new Date());
    }

    @After(value = "execution(* In.Abdul.controller.EmployeeController.*(..))")
    public void afterAdvice(JoinPoint joinPoint) {
        System.out.println(" Method called at" + joinPoint.getSignature() + " ended at " + new Date());
    }

    @Before(value = "execution(* In.Abdul.service.EmployeeService.*(..))")
    public void beforeAdviceForService(JoinPoint joinPoint) {
        System.out.println(" Method called at" + joinPoint.getSignature() + " started at " + new Date());
    }

    @After(value = "execution(* In.Abdul.service.EmployeeService.*(..))")
    public void afterAdviceService(JoinPoint joinPoint) {
        System.out.println(" Method called at" + joinPoint.getSignature() + " ended at " + new Date());
    }


	/*
	if there is no exception then @AfterReturning will be called
	 */


    @AfterReturning(value = "execution(* In.Abdul.service.EmployeeService.addEmployee(..))", returning = "employee")
    public void afterReturingFromService(JoinPoint joinPoint, Employee employee) {
        System.out.println("business logic to save emp has been success and data saved with id:: " + employee.getId() + joinPoint.getSignature() + " ended at " + new Date());
    }


    /*
    if there is an  exception then @AfterThrowing will be called
     */
    @AfterThrowing(value = "execution(* In.Abdul.service.EmployeeService.addEmployee(..))", throwing = "exception")
    public void afterthrowingFromService(JoinPoint joinPoint, Exception exception) {
        System.out.println("Business class have thrown an exception nd exception msg is:: " + exception.getMessage() + joinPoint.getSignature() + " ended at " + new Date());
    }


    /*

    --> Around is most power full in the aop
    --> we can catch the exception in around advice
    --> in around advice the parameter is "ProceedingJoinPoint"
    --> compulsory we need to proceed the join point like this

        eg: joinPoint.proceed

    --> in around advice we can manipulate data from Ui like postman , angular etc..
    --> below is one of the example for around advice

     */

    @Around(value = ("execution(* In.Abdul.controller.EmployeeController.addEmployee(..))"))
    public Employee aroundAdviceForService(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Inside around advice method started at " + joinPoint.getSignature() + " started at " + new Date());

        try {
            Employee[] arrEmp = new Employee[1];
            Employee dummyEmp = new Employee();
            dummyEmp.setName("dum");
            arrEmp[0] = dummyEmp;
            Employee proceed = (Employee) joinPoint.proceed(arrEmp);
            // joinPoint.proceed();
            return proceed;
        } catch (Throwable e) {
            System.out.println("Inside around advice method has failed " + e.getMessage());
        }
        System.out.println("Inside around advice method " + joinPoint.getSignature() + " ended at " + new Date());
        return null;
    }



    /*
     *
     * @Before(value =
     * ("execution(* com.Abdul.SpringBootAOP.controller.EmployeeController.*(..))"))
     *
     * @Before(value=
     * ("execution(for any return type PackageName.ClassName.ForEveryMethod(HavingAnyArgs))"
     * )
     *
     *
     */


}
