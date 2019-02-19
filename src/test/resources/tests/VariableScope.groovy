package test.package.name

import groovy.json.JsonOutput
import groovy.json.StringEscapeUtils
import groovy.util.slurpersupport.GPathResult
import io.infinite.carburetor.HttpRequest
import io.infinite.carburetor.TestCarburetor
import org.slf4j.LoggerFactory

@TestCarburetor
String s(Object iObject) {
    return JsonOutput.toJson(Objects.toString(iObject, ""))
}

@TestCarburetor
String n(Object iObject) {
    if (iObject == null || iObject == "") {
        return "null"
    } else {
        try {
            if (iObject.toString().contains(".")) {
                return JsonOutput.toJson(Float.valueOf(iObject.toString()))
            } else {
                return JsonOutput.toJson(Long.valueOf(iObject.toString()))
            }
        } catch (NumberFormatException numberFormatException) {
            LoggerFactory.getLogger(this.getClass()).warn("Unable to parse number: " + numberFormatException.getMessage())
            return s(iObject)
        }
    }
}



@TestCarburetor
def applyPlugin() {
    HttpRequest httpRequest = new HttpRequest()
    String header = ""
    def product = null
    def businessAccount = null
    def card = null
    def account = null
    def transaction = null
    def network = null
    def amounts = null
    def pos_merchant = null

    httpRequest.body = """{
  "TransactionNotificationRequest": {${header}
    "product": {
      "productID": ${n(product?.productID?.text())},
      "programManager": ${s(product?.programManager?.text())},
      "productName": ${s(product?.productName?.text())},
      "productCategory": ${s(product?.productCategory?.text())},
      "subProductType": ${s(product?.subProductType?.text())},
      "institutionID": ${n(product?.institutionID?.text())}
    },
    "fees": ,
    "businessAccount": {
      "sourceBusinessName": ${s(businessAccount?.sourceBusinessName?.text())},
      "businessName": ${s(businessAccount?.businessName?.text())},
      "businessAccountNumber": ${n(businessAccount?.businessAccountNumber?.text())},
      "sourceBusinessAccountNumber": ${n(businessAccount?.sourceBusinessAccountNumber?.text())}
    },
    "network": {
      "networkName": ${s(network?.networkName?.text())},
      "networkProcessingCode": ${n(network?.networkProcessingCode?.text())},
      "networkTransactionId": ${n(network?.networkTransactionId?.text())},
      "networkRRN": ${n(network?.networkRRN?.text())},
      "networkResponseCode": ${s(network?.networkResponseCode?.text())},
      "networkSTAN": ${n(network?.networkSTAN?.text())}
    },
    "amounts": {
      "transactionCurrency": ${s(amounts?.transactionCurrency?.text())},
      "transactionAmountInt": ${n(amounts?.transactionAmountInt?.text())},
      "sourcePostedAmountDec": ${n(amounts?.sourcePostedAmountDec?.text())},
      "billingAmountInt": ${n(amounts?.billingAmountInt?.text())},
      "postedAmountDec": ${n(amounts?.postedAmountDec?.text())},
      "additionalAmountDec": ${n(amounts?.additionalAmountDec?.text())},
      "sourcePostedAmountInt": ${n(amounts?.sourcePostedAmountInt?.text())},
      "additionalAmountCurrency": ${s(amounts?.additionalAmountCurrency?.text())},
      "sourcePostedCurrency": ${s(amounts?.sourcePostedCurrency?.text())},
      "postedCurrency": ${s(amounts?.postedCurrency?.text())},
      "billingCurrency": ${s(amounts?.billingCurrency?.text())},
      "billingAmountDec": ${n(amounts?.billingAmountDec?.text())},
      "postedAmountInt": ${n(amounts?.postedAmountInt?.text())},
      "additionalAmountInt": ${n(amounts?.additionalAmountInt?.text())},
      "transactionAmountDec": ${n(amounts?.transactionAmountDec?.text())}
    },
    "pos_merchant": {
      "storeNumber": ${s(pos_merchant?.storeNumber?.text())},
      "chipTransactionFlag": ${n(pos_merchant?.chipTransactionFlag?.text())},
      "posConditionCode": ${n(pos_merchant?.posConditionCode?.text())},
      "terminalId": ${s(pos_merchant?.terminalId?.text())},
      "chipCondition": ${n(pos_merchant?.chipCondition?.text())},
      "merchantName": ${s(pos_merchant?.merchantName?.text())},
      "acquirerInstitutionIDCode": ${s(pos_merchant?.acquirerInstitutionIDCode?.text())},
      "cardAcceptorIDCode": ${s(pos_merchant?.cardAcceptorIDCode?.text())},
      "cardAcceptorterminalIDCode": ${s(pos_merchant?.cardAcceptorterminalIDCode?.text())},
      "merchantId": ${s(pos_merchant?.merchantId?.text())},
      "merchantCountry": ${s(pos_merchant?.merchantCountry?.text())},
      "acquirerId": ${n(pos_merchant?.acquirerId?.text())},
      "acquirerCountry": ${s(pos_merchant?.acquirerCountry?.text())},
      "merchantType": ${s(pos_merchant?.merchantType?.text())},
      "paymentIndicator": ${s(pos_merchant?.paymentIndicator?.text())},
      "merchantCode": ${s(pos_merchant?.merchantCode?.text())},
      "cardholderId": ${n(pos_merchant?.cardholderId?.text())},
      "merchantCity": ${s(pos_merchant?.merchantCity?.text())},
      "terminalType": ${s(pos_merchant?.terminalType?.text())},
      "nationalPOSGeographicData": ${s(pos_merchant?.nationalPOSGeographicData?.text())},
      "terminalCategory": ${n(pos_merchant?.terminalCategory?.text())},
      "posPINCaptureCode": ${s(pos_merchant?.posPINCaptureCode?.text())},
      "merchantAddress": ${s(pos_merchant?.merchantAddress?.text())},
      "terminalEntry": ${n(pos_merchant?.terminalEntry?.text())},
      "posEntryMode": ${n(pos_merchant?.posEntryMode?.text())}
    },
    "card": {
      "sourceCardNumber": ${s(card?.sourceCardNumber?.text())},
      "sourceProxyNumber": ${n(card?.sourceProxyNumber?.text())},
      "proxyNumber": ${n(card?.proxyNumber?.text())},
      "cardNumber": ${s(card?.cardNumber?.text())},
      "cardSequenceNumber": ${n(card?.cardSequenceNumber?.text())},
      "bankingCustomerId": ${n(card?.bankingCustomerId?.text())}
    },
    "account": {
      "sourceAccountBalanceInt": ${n(account?.sourceAccountBalanceInt?.text())},
      "AccountCurrency": ${s(account?.AccountCurrency?.text())},
      "AccountBalanceDec": ${n(account?.AccountBalanceDec?.text())},
      "AccountBalanceInt": ${n(account?.AccountBalanceInt?.text())},
      "sourceAccountCurrency": ${s(account?.sourceAccountCurrency?.text())},
      "sourceAccountNumber": ${n(account?.sourceAccountNumber?.text())},
      "sourceAccountBalanceDec": ${n(account?.sourceAccountBalanceDec?.text())},
      "AccountNumber": ${n(account?.AccountNumber?.text())}
    },
    "transaction": {
      "postingTransactionSource": ${s(transaction?.postingTransactionSource?.text())},
      "messageTypeIdentifier": ${s(transaction?.messageTypeIdentifier?.text())},
      "transactionPostingDate": ${s(transaction?.transactionPostingDate?.text())},
      "transactionDescription": ${s(transaction?.transactionDescription?.text())},
      "transactionCode": ${s(transaction?.transactionCode?.text())},
      "transactionTimeStamp": ${s(transaction?.transactionTimeStamp?.text())},
      "transactionLocalDate": ${s(transaction?.transactionLocalDate?.text())},
      "transactionLocalTime": ${s(transaction?.transactionLocalTime?.text())},
      "transactionResponseCode": ${s(transaction?.transactionResponseCode?.text())},
      "transactionId": ${n(transaction?.transactionId?.text())},
      "originalTransactionSource": ${s(transaction?.originalTransactionSource?.text())},
      "postingFlag": ${n(transaction?.postingFlag?.text())},
      "transactionPostingTime": ${s(transaction?.transactionPostingTime?.text())},
      "transactionOriginalId": ${n(transaction?.transactionOriginalId?.text())},
      "debitCreditFlag": ${s(transaction?.debitCreditFlag?.text())},
      "transactionLifecycleState": ${s(transaction?.transactionLifecycleState?.text())},
      "postingReference": ${s(transaction?.postingReference?.text())},
      "logicModule": ${s(transaction?.logicModule?.text())}
    }
  }
}"""
}
applyPlugin()