package com.cart.app.config;
import org.springframework.context.annotation.Import;
import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(JobEngineImportSelector.class)
public @interface EnableJobCart {
}
