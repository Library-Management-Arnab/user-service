package com.lms.us.rest.config;

import com.lms.us.rest.service.UserClientDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.InMemoryApprovalStore;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@SuppressWarnings("deprecation")
@Configuration
@EnableAuthorizationServer
@AllArgsConstructor
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
//	private static final Logger LOG = LoggerFactory.getLogger(AuthorizationServerConfig.class);
//	private static final String CLIENT_ID = "POSTMAN_CLIENT";
//	private static final String CLIENT_SECRET = "POSTMAN_SECRET";
//	private static final String GRANT_TYPE_PASSWORD = "password";
//	private static final String AUTHORIZATION_CODE = "authorization_code";
//	private static final String REFRESH_TOKEN = "refresh_token";
//	private static final String IMPLICIT = "implicit";
//	private static final String SCOPE_READ = "read";
//	private static final String SCOPE_WRITE = "write";
//	private static final String TRUST = "trust";
//	private static final int ACCESS_TOKEN_VALIDITY_SECONDS = 1 * 60 * 60;
//	private static final int REFRESH_TOKEN_VALIDITY_SECONDS = 6 * 60 * 60;
	
	private UserClientDetailsService userClientDetailsService;

    /*@Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource oauthDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public JdbcClientDetailsService clientDetailsService() {
        return new JdbcClientDetailsService(oauthDataSource());
    }*/

    @Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }

    @Bean
    public ApprovalStore approvalStore() {
        return new InMemoryApprovalStore();
    }

    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        return new InMemoryAuthorizationCodeServices();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(userClientDetailsService);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {

    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .approvalStore(approvalStore())
                .authorizationCodeServices(authorizationCodeServices())
                .tokenStore(tokenStore());
    }

}
