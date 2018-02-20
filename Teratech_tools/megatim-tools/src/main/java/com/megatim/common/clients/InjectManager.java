package com.megatim.common.clients;

import java.lang.annotation.*;


@Retention(RetentionPolicy.RUNTIME)
@Target(value={ElementType.FIELD})
@Inherited
public @interface InjectManager {
       
	    
	   //Service name
	   String name() default " ";   
	   
	   String scope() default "local" ;
}
