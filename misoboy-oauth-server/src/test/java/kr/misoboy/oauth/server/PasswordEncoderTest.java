package kr.misoboy.oauth.server;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

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
@RunWith(SpringRunner.class)
@SpringBootTest
public class PasswordEncoderTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void passwordEncodeTest(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        String encodePass = bCryptPasswordEncoder.encode("1234");

        logger.debug("Encode Password : {}", encodePass);
        Assert.assertNotNull(encodePass);
    }
}
