package kr.misoboy.oauth.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author misoboy
 * @version 1.0
 * @see <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일          수정자        수정내용
 *  -----------   ------------    ---------------------------
 *   2019-01-10       misoboy         최초 생성
 * </pre>
 */
@SpringBootApplication
@ComponentScan(basePackages = "kr.misoboy")
public class ApiApplication extends SpringBootServletInitializer {

    /**
     * Application 구동
     * @param args
     */
    public static void main( String [] args ){
        SpringApplication.run(ApiApplication.class, args);
    }

    /**
     * Standard alone WAS 를 사용하기 위함
     * @param application
     * @return
     */
    @Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        setRegisterErrorPageFilter(false);
		return application.sources(ApiApplication.class);
	}
}
