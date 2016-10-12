package tube.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.sf.ehcache.config.CacheConfiguration;

@Configuration
@EnableCaching
public class CachingConfig implements CachingConfigurer {

	@Bean(destroyMethod = "shutdown")
	public net.sf.ehcache.CacheManager ehCacheManager() {
		net.sf.ehcache.config.Configuration config = new net.sf.ehcache.config.Configuration();
		
		config.addCache(configureCahce("userCache"));
		config.addCache(configureCahce("videoCache"));
		config.addCache(configureCahce("tagCache"));
		config.addCache(configureCahce("playlistCache"));
		config.addCache(configureCahce("commentCache"));

		return net.sf.ehcache.CacheManager.newInstance(config);
	}

	private CacheConfiguration configureCahce(String cacheName) {
		CacheConfiguration cacheConfiguration = new CacheConfiguration();
		cacheConfiguration.setName(cacheName);
		cacheConfiguration.setMemoryStoreEvictionPolicy("LRU");
		cacheConfiguration.setMaxEntriesLocalHeap(1000);
		cacheConfiguration.timeToLiveSeconds(10000);
		return cacheConfiguration;
	}

	@Bean
	@Override
	public CacheManager cacheManager() {
		return new EhCacheCacheManager(ehCacheManager());
	}

	@Override
	public CacheResolver cacheResolver() {
		return null;
	}

	@Bean
	@Override
	public KeyGenerator keyGenerator() {
		return new SimpleKeyGenerator();
	}

	@Override
	public CacheErrorHandler errorHandler() {
		return null;
	}
}
