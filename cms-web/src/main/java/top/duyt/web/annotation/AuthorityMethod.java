package top.duyt.web.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import top.duyt.model.Emur.RoleType;

/**
 * 本注解标注的方法，是指定的角色才可以访问的
 * @author Alvin Du
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthorityMethod {
	
	public RoleType[] roles();

}
