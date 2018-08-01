package com.test.stepdefs;

import com.test.utils.ZapImpl;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.zaproxy.clientapi.core.ClientApiException;

import java.io.IOException;

public class ZapScannerSteps {

    ZapImpl zapIm = new ZapImpl();

    @When("^Spider scan is triggered and allowed to complete$")
    public void spider_scan_is_triggered_and_allowed_to_complete() throws ClientApiException, InterruptedException, IOException {
        zapIm.SpiderScan();
    }

    @When("^Active scan is triggered and allowed to complete$")
    public void active_scan_is_triggered_and_allowed_to_complete() throws IOException, ClientApiException, InterruptedException {
        zapIm.ActiveScan();
    }

    @When("^Zap scan results are exported to an external xml and html file$")
    public void zap_scan_results_are_exported_to_an_external_xml_and_html_file() throws ClientApiException, IOException {

        zapIm.ExportZapReport();
    }

    @Then("^Zap scan results are generated successfully$")
    public void zap_scan_results_are_generated_successfully() {
        zapIm.verifyZapReportGenerated();

    }


}
