package kr.misoboy.oauth.api.controller;

import com.google.common.collect.Maps;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

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
@RestController
public class DummyController {

    @GetMapping(value = "/dummy", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Map<String, String>> getDummy(
            HttpServletRequest request,
            HttpServletResponse response
    ) {

        Map<String, String> dataMap = Maps.newHashMap();
        dataMap.put("title", "Hello, Api Server");

        return new ResponseEntity<>(dataMap, HttpStatus.OK);
    }

    @GetMapping(value = "/dummy2", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Map<String, String>> getDummy2(
            HttpServletRequest request,
            HttpServletResponse response
    ) {

        Map<String, String> dataMap = Maps.newHashMap();
        dataMap.put("title", "Hello, Api Server2");

        return new ResponseEntity<>(dataMap, HttpStatus.OK);
    }
}
