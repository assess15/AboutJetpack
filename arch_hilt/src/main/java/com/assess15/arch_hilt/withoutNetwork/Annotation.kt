package com.assess15.arch_hilt.withoutNetwork

import javax.inject.Qualifier

/***
 * 自定义注解
 */
@Qualifier
@Retention(AnnotationRetention.RUNTIME)
internal annotation class AppHash

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
internal annotation class ActivityHash

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
internal annotation class OfferString