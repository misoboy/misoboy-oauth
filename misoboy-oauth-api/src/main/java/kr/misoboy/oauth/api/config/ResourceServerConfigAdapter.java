package kr.misoboy.oauth.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

/**
 * @author misoboy
 * @version 1.0
 * @see <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일          수정자        수정내용
 *  -----------   ------------    ---------------------------
 *   2019-01-11       misoboy         최초 생성
 * </pre>
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfigAdapter extends ResourceServerConfigurerAdapter {

    @Value("${oauth.server.host}")
    private String oauthServerHost;

    @Value("${oauth.client.id}")
    private String oauthClientId;

    @Value("${oauth.client.secret}")
    private String oauthClientSecret;

    @Primary
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
        StringBuilder serverUrlBuilder = new StringBuilder();
        remoteTokenServices.setCheckTokenEndpointUrl(
                serverUrlBuilder.append(oauthServerHost).append("/oauth/check_token").toString()
        );
        remoteTokenServices.setClientId(oauthClientId);
        remoteTokenServices.setClientSecret(oauthClientSecret);

        resources.tokenServices(remoteTokenServices);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/dummy").permitAll()
                .antMatchers("/dummy2").hasRole("SUPER")
                .anyRequest()
                .authenticated();
    }
}
