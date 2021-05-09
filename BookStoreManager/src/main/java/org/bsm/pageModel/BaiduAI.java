package org.bsm.pageModel;

import java.io.File;

public class BaiduAI {
    private File upload;// 定义一个File ,变量名要与jsp中的input标签的name一致
    private String uploadContentType;// 上传文件的mimeType类型
    private String uploadFileName;// 上传文件的名称

    public File getUpload() {
        return upload;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public String getUploadContentType() {
        return uploadContentType;
    }

    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    @Override
    public String toString() {
        return "BaiduAI [upload=" + upload + ", uploadContentType=" + uploadContentType + ", uploadFileName="
                + uploadFileName + "]";
    }
}
