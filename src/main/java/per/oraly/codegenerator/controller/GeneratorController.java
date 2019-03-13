package per.oraly.codegenerator.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import per.oraly.codegenerator.dto.DDDReqParam;
import per.oraly.codegenerator.dto.MVCReqParam;
import per.oraly.codegenerator.dto.TableInfo;
import per.oraly.codegenerator.service.GeneratorService;
import per.oraly.codegenerator.util.GeneratorUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description
 *
 * @author liyuan 2019/03/09 16:21
 */
@Controller
@RequestMapping("/generator")
public class GeneratorController {

    @Autowired
    private GeneratorService generatorService;

    @GetMapping("/list")
    @ResponseBody
    public HttpEntity<Map<String, Object>> getTableList() {
        List<TableInfo> infos = generatorService.getTableListInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg","垃圾layui");
        map.put("count", infos.size());
        map.put("data", infos);
        return new HttpEntity<>(map);
    }

    @GetMapping("/ddd")
    public void generatorForDDD(HttpServletResponse response, DDDReqParam dddReqParam) {
        byte[] data = generatorService.generatorDDDCode(dddReqParam);
        response.reset();
        response.setHeader("Content-Disposition",
                "attachment; filename=\"" + dddReqParam.getProjectName() + ".zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset="+ GeneratorUtil.DEFAULT_CHARSET);
        try {
            IOUtils.write(data, response.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException("文件输出出错", e);
        }
    }

    @GetMapping("/mvc")
    public void generatorForMVC(HttpServletResponse response, MVCReqParam mvcReqParam) {
    }
}
