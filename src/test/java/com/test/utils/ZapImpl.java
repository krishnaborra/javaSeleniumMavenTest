package com.test.utils;

import org.zaproxy.clientapi.core.ClientApi;
import org.zaproxy.clientapi.core.ClientApiException;
import org.zaproxy.clientapi.core.ApiResponse;
import org.zaproxy.clientapi.core.ApiResponseElement;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ZapImpl {

    String ZAP_PROXYHOST  = "localhost";
    Integer ZAP_PROXYPORT = 11000;
    String ZAP_APIKEY = "2q6oo8ql47fqduqoirfjhfnqcu";

    ClientApi api = new ClientApi(ZAP_PROXYHOST, ZAP_PROXYPORT);
    String reportPath  = System.getProperty("user.dir") + "/zapReport.";


    public void scana(String scanId,  ApiResponse status, String scanType) throws ClientApiException, InterruptedException {
        
        Integer progress = 0;
        while (progress < 100) {
            Thread.sleep(1000);
            progress = Integer.parseInt(((ApiResponseElement)api.ascan.status(scanId)).getValue());
            System.out.println(scanType+" Scan progress : " + progress + "%");
        }
        System.out.println(scanType+" Scan complete");
    }

    public void ActiveScan() throws IOException, ClientApiException, InterruptedException{

        Property property = new Property();

        String scanId = ((ApiResponseElement)api.ascan.scan(property.getProperty("targetApp"),
                "True", "False", null,
                null, null)).getValue();

        scana(scanId, api.ascan.status(scanId), "Active");

    }

    public void scans(String scanId,  ApiResponse status, String scanType) throws ClientApiException, InterruptedException {

        Integer progress = 0;
        while (progress < 100) {
            Thread.sleep(1000);
            progress = Integer.parseInt(((ApiResponseElement)api.spider.status(scanId)).getValue());
            System.out.println(scanType+" Scan progress : " + progress + "%");
        }
        System.out.println(scanType+" Scan complete");
    }

    public void SpiderScan() throws IOException, ClientApiException, InterruptedException{

        Property property = new Property();

        String scanId = ((ApiResponseElement)api.spider.scan(null,
                property.getProperty("targetApp"), null, null,
                null, null)).getValue();

        scans(scanId, api.spider.status(scanId), "Spider");

    }

    public void ExportZapReport() throws ClientApiException, IOException {
        exportReport(api.core.xmlreport(), "xml");
        exportReport(api.core.htmlreport(), "html");
    }

    private void exportReport(byte[] report, String ext) throws IOException {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(reportPath + ext));
            bos.write(report);
            bos.close(); // You may end up with 0 bytes file if not calling close.
    }

    public void verifyZapReportGenerated() {
        Files.exists(Paths.get(reportPath + "xml"));
        Files.exists(Paths.get(reportPath + "html"));
    }

}
