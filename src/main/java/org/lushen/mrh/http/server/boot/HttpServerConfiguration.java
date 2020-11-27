package org.lushen.mrh.http.server.boot;

import java.util.List;
import java.util.stream.Collectors;

import org.lushen.mrh.http.server.netty.HttpServerConfig;
import org.lushen.mrh.http.server.netty.HttpFilter;
import org.lushen.mrh.http.server.netty.HttpResolver;
import org.lushen.mrh.http.server.netty.crypto.CryptoProvider;
import org.lushen.mrh.http.server.netty.crypto.none.NoneCryptoProvider;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * http server configuration
 * 
 * @author hlm
 */
@Configuration
@EnableConfigurationProperties
public class HttpServerConfiguration {

	@Bean
	@ConfigurationProperties(prefix="org.lushen.mrh.http.server")
	public HttpServerConfig httpServerConfig() {
		return new HttpServerConfig();
	}

	@Bean
	@ConditionalOnMissingBean(CryptoProvider.class)
	public CryptoProvider cryptoProvider() {
		return new NoneCryptoProvider();
	}

	@Bean
	@ConditionalOnMissingBean(HttpResolver.class)
	public HttpResolver httpServerResolver() {
		return new HttpServerBusiness();
	}

	@Bean
	public HttpServerBean httpServerBean(
			@Autowired HttpServerConfig serverConfig,
			@Autowired CryptoProvider cryptoProvider,
			@Autowired ObjectProvider<HttpFilter> filtersProvider,
			@Autowired HttpResolver serverResolver) {
		List<HttpFilter> serverFilters = filtersProvider.stream().collect(Collectors.toList());
		return new HttpServerBean(serverConfig, cryptoProvider, serverFilters, serverResolver);
	}

}
