package com.test.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
@Parameters({
        @Parameter(name = "serviceName", description = "Service Name", required = true, example = "serviceName", in = ParameterIn.HEADER),
        @Parameter(name = "originalApp", description = "Original App", required = true, example = "originalApp", in = ParameterIn.HEADER),
        @Parameter(name = "targetApp", description = "Target App", required = true, example = "targetApp", in = ParameterIn.HEADER),
        @Parameter(name = "canal", description = "Canal", required = true, example = "canal", in = ParameterIn.HEADER)
})
public @interface DefaultHeaders {

}