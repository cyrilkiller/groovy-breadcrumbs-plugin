package breadcrumbs.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import breadcrumbs.scope.BreadCrumbsScopeEnum;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BreadCrumbs {


    /**
     * The scope ti retrieve actionName and controlerName
     * @return int
     */
    BreadCrumbsScopeEnum scope() default BreadCrumbsScopeEnum.STATIC;

    
    /**
     * Indique au {@link BreadCrumbFilters} que l'"actionName"
     * se trouve en session dans l'espace breadcrumbs.actionName
     * 
     * @return {@link Boolean}
     */
    String actionName() default "";

    /**
     * Indique au {@link BreadCrumbFilters} que le "controllerName"
     * se trouve en session dans l'espace breadcrumbs.controllerName
     * 
     * @return {@link Boolean}
     */
    String controllerName() default "";
    
}
