package kr.misoboy.oauth.server.model;

import java.util.Date;
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
public class UserVo {

    private String emplyrId;
    private String password;
    private String emplyrNm;
    private Date registDt;
    private Date upddeDt;

    public String getEmplyrId() {
        return emplyrId;
    }

    public void setEmplyrId(String emplyrId) {
        this.emplyrId = emplyrId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmplyrNm() {
        return emplyrNm;
    }

    public void setEmplyrNm(String emplyrNm) {
        this.emplyrNm = emplyrNm;
    }

    public Date getRegistDt() {
        return registDt;
    }

    public void setRegistDt(Date registDt) {
        this.registDt = registDt;
    }

    public Date getUpddeDt() {
        return upddeDt;
    }

    public void setUpddeDt(Date upddeDt) {
        this.upddeDt = upddeDt;
    }
}
