package com.es.lsapp.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/lschannel")
@Slf4j
public class LsChannelSwitchController {

    @RequestMapping("/switch")
    public void switchChannel(@RequestParam("file") MultipartFile file, HttpServletResponse response) {
        try {
            EasyExcelOrderListener listener = new EasyExcelOrderListener();
            EasyExcel.read(file.getInputStream(), LsChannelSwitchDto.class, listener).sheet().doRead();
            List<LsChannelSwitchDto> list = listener.getList();
            System.out.println(JSON.toJSONString(list));
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("UTF-8");
            String fileName = "Sss.xlsx";
            response.setHeader("Content-disposition", "attachment;filename=" + fileName);

            EasyExcel.write(response.getOutputStream(), LsChannelSwitchDto.class)
                    .registerWriteHandler(new HorizontalCellStyleStrategy())
                    .sheet()
                    .doWrite(list);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 下载pdf文件
     */
    @RequestMapping("/downloadPdf")
    public static void downloadPdf(@RequestParam("pdfFileUrl") String pdfFileUrl, HttpServletResponse response) {
        String fileName = System.currentTimeMillis()/1000+".pdf";
        ServletOutputStream outputStream = null;
        InputStream inputStream = null;
        try {
            URL urlFile = new URL(pdfFileUrl);
            HttpURLConnection conn = (HttpURLConnection) urlFile.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5 * 1000);

            inputStream = conn.getInputStream();

            response.reset();
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "utf-8"));

            outputStream = response.getOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }
            outputStream.flush();
        } catch (Exception e) {
            log.error("【下载PDF文件失败】原因", e);
        } finally {
            try {
                if (null != inputStream) {
                    inputStream.close();
                }
                if (null != outputStream) {
                    outputStream.close();
                }
            } catch (Exception ignored) {

            }
        }
    }
}

