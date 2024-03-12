package cn.sichu.controller;

import cn.sichu.entity.FundTransaction;
import cn.sichu.service.IFundHistoryNavService;
import cn.sichu.service.IFundInformationService;
import cn.sichu.service.IFundTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author sichu huang
 * @date 2024/03/09
 **/
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private IFundTransactionService fundTransactionService;
    @Autowired
    private IFundInformationService fundInformationService;
    @Autowired
    private IFundHistoryNavService fundHistoryNavService;

    @GetMapping("/01")
    public void test01() {
        List<FundTransaction> fundTransactions = fundTransactionService.selectAllFundTransaction();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        for (FundTransaction fundTransaction : fundTransactions) {
            System.err.println(fundTransaction.getCode());
            System.err.println(fundTransaction.getShortName());
            System.err.println(dateFormat.format(fundTransaction.getApplicationDate()));
            System.err.println(dateFormat.format(fundTransaction.getConfirmationDate()));
            System.err.println(dateFormat.format(fundTransaction.getSettlementDate()));
            System.err.println(fundTransaction.getFee());
            System.err.println(fundTransaction.getShare());
            System.err.println(fundTransaction.getNav());
            System.err.println(fundTransaction.getAmount());
            System.err.println(fundTransaction.getType());
            System.err.println(fundTransaction.getTradingPlatform());
        }
    }

    @PostMapping("/02")
    public void test02(@RequestParam("code") String code, @RequestParam("applicationDate") String applicationDate,
        @RequestParam("amount") String amount, @RequestParam("type") Integer type,
        @RequestParam("tradingPlatform") String tradingPlatform) throws ParseException, IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date parseApplicationDate = sdf.parse(applicationDate);
        fundTransactionService.insertFundPurchaseTransactionByConditions(code, parseApplicationDate, amount, type,
            tradingPlatform);
    }

    @PostMapping("/03")
    public void test03(@RequestParam("code") String code, @RequestParam("startDate") String startDate,
        @RequestParam("endDate") String endDate, @RequestParam("callback") String callback)
        throws ParseException, IOException {
        fundHistoryNavService.insertFundHistoryNavInformation(code, startDate, endDate, callback);
    }
}