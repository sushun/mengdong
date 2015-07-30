package cn.md.trainclient.api;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiField {
    /**
     * Parameter name of API
     */
    String paramName();
}
