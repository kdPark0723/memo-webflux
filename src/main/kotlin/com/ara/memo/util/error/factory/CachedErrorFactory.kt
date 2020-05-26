package com.ara.memo.util.error.factory

import com.ara.memo.util.cache.WeakCache
import com.ara.memo.util.cache.getOrSet
import com.ara.memo.util.error.Error
import com.ara.memo.util.error.MultipleError
import com.ara.memo.util.error.SingleError

class CachedErrorFactory : ErrorFactory {
    private val singleErrorCache = WeakCache<String, SingleError>()
    private val multipleErrorCache = WeakCache<Collection<Error>, MultipleError>()

    override fun from(message: String?) = singleErrorCache.getOrSet(message) { SingleError(message) }
    override fun from(errors: Collection<SingleError>) = multipleErrorCache.getOrSet(errors) { MultipleError(errors) }
}