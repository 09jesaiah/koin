/*
 * Copyright 2017-Present the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.koin.dsl.fu

import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.annotation.KoinInternalApi
import org.koin.core.definition.KoinDefinition
import org.koin.core.module.Module
import org.koin.core.module.dsl.DefinitionOptions
import org.koin.core.module.dsl.onOptions
import org.koin.dsl.ScopeDSL
import kotlin.reflect.KFunction

/**
 * Koin Function DSL proposal - JVM reflect version while working on coming compiler plugin
 * Optimize, warmup & cache all reflection metadata to get as fast as possible
 *
 * DSL API - Functions Only
 * single(<KFunction>)
 *
 * @author Arnaud Giuliani
 */

@OptIn(KoinInternalApi::class)
@KoinExperimentalAPI
inline fun <reified R, reified T : R> Module.single(
    function: KFunction<R>,
    noinline options: DefinitionOptions<T>? = null,
): KoinDefinition<T> {
    cacheFunction(function)
    return single<T> { buildComponent(this, function) as T }.onOptions(options)
}

@OptIn(KoinInternalApi::class)
@KoinExperimentalAPI
inline fun <reified R, reified T : R> Module.factory(
    function: KFunction<R>,
    noinline options: DefinitionOptions<T>? = null,
): KoinDefinition<T> {
    cacheFunction(function)
    return factory<T> { buildComponent(this, function) as T }.onOptions(options)
}

@OptIn(KoinInternalApi::class)
@KoinExperimentalAPI
inline fun <reified R, reified T : R> ScopeDSL.scoped(
    function: KFunction<R>,
    noinline options: DefinitionOptions<T>? = null,
): KoinDefinition<T> {
    cacheFunction(function)
    return scoped<T> { buildComponent(this, function) as T }.onOptions(options)
}
