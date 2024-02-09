package com.chernov.exampleproject.annotation;

import org.springframework.cache.annotation.CacheEvict;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@CacheEvict(cacheNames = {"user_filter"}, allEntries = true)
public @interface FilterCacheEvict {
}
