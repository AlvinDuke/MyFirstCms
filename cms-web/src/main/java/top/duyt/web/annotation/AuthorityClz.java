package top.duyt.web.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 本注解标注哪些类含有需要特定角色访问的方法
 * @author Alvin Du
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthorityClz {
	
	public String value() default "";

}
