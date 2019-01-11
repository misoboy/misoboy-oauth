package kr.misoboy.oauth.server.service.impl;

import com.google.common.collect.Lists;
import kr.misoboy.oauth.server.model.UserAuthorVo;
import kr.misoboy.oauth.server.model.UserVo;
import kr.misoboy.oauth.server.service.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

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
@Service("customUserDetailService")
public class CustomUserDetailServiceImpl implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        logger.debug("User 정보 조회");
        logger.debug("username : {}", username);

        // 사용자 정보 조회
        UserVo userVo = new UserVo();
        userVo.setEmplyrId(username);
        UserVo user = userMapper.selectUser(userVo);

        List<UserAuthorVo> userAuthorList = userMapper.selectUserAuthorList(userVo);

        // 사용자 권한 목록 조회
        Collection<GrantedAuthority> grantedAuthorities = Lists.newArrayList();
        userAuthorList.forEach(userAuthor -> grantedAuthorities.add(new SimpleGrantedAuthority(userAuthor.getAuthor())));

        return new User(user.getEmplyrId(), user.getPassword(), grantedAuthorities);
    }

}
