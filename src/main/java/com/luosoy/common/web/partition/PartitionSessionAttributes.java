/**
 * 
 */
package com.luosoy.common.web.partition;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface PartitionSessionAttributes {

    /**
     * The names of session attributes in the model, to be stored in the
     * session or some conversational storage.
     * <p>Note: This indicates the model attribute names. The session attribute
     * names may or may not match the model attribute names; applications should
     * not rely on the session attribute names but rather operate on the model only.
     * 
     * @return the string[]
     */
    String[] value() default {};

}