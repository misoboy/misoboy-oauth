package kr.misoboy.oauth.server.service;

import kr.misoboy.oauth.server.model.UserAuthorVo;
import kr.misoboy.oauth.server.model.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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
@Mapper
public interface UserMapper {

    @Select("SELECT EMPLYR_ID, PASSWORD, EMPLYR_NM, REGIST_DT, UPDDE_DT FROM TB_USER WHERE EMPLYR_ID = #{emplyrId}")
    UserVo selectUser(UserVo userVo);


    @Select("SELECT EMPLYR_ID, AUTHOR, REGIST_DT FROM TB_AUTHOR WHERE EMPLYR_ID = #{emplyrId}")
    List<UserAuthorVo> selectUserAuthorList(UserVo userVo);
}
