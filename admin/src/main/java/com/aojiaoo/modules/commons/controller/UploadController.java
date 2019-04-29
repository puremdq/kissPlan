package com.aojiaoo.modules.commons.controller;

import com.aojiaoo.core.base.BaseController;
import com.aojiaoo.core.common.GlobalProperties;
import com.aojiaoo.core.common.ServerResponse;
import com.aojiaoo.utils.WebUtils;
import com.google.gson.Gson;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/")
public class UploadController extends BaseController {

    /*TODO 限制上传文件的大小和类型*/
    @ResponseBody
    @PostMapping("upload")
    public ServerResponse fileUpload2(@RequestParam("file") CommonsMultipartFile file) throws IOException {

        if (file == null) {
            return ServerResponse.createByErrorMessage("非法参数");
        }
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        String accessKey = GlobalProperties.get("qiniu.accessKey");
        String secretKey = GlobalProperties.get("qiniu.secretKey");
        String bucket = GlobalProperties.get("qiniu.bucket");
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {

            Response response = uploadManager.
                    put(file.getInputStream(), null, upToken, null,
                            file.getContentType());
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            return ServerResponse.createBySuccess(WebUtils.spliceUrl(GlobalProperties.get("imgServer"), putRet.key));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ServerResponse.createByErrorMessage("上传失败");
    }

}
