package com.example.s;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class XmlTransformer {
	public static void transformXml(Document inputDoc, Document outputDoc) {
		Element root = outputDoc.createElement("FIXML");
		root.setAttribute("xmlns", "http://www.finacle.com/fixml");
		outputDoc.appendChild(root);

		Element header = outputDoc.createElement("Header");
		Element requestHeader = outputDoc.createElement("RequestHeader");
		header.appendChild(requestHeader);

		Element messageKey = outputDoc.createElement("MessageKey");
		requestHeader.appendChild(messageKey);

		Element requestUUID = outputDoc.createElement("RequestUUID");
		requestUUID.setTextContent("TIP-f0db643b-8ade-4b93-97bd-ab1be5e7444c");
		messageKey.appendChild(requestUUID);

		Element serviceRequestId = outputDoc.createElement("ServiceRequestId");
		serviceRequestId.setTextContent("XferTrnAdd");
		messageKey.appendChild(serviceRequestId);

		Element serviceRequestVersion = outputDoc.createElement("ServiceRequestVersion");
		serviceRequestVersion.setTextContent("10.2");
		messageKey.appendChild(serviceRequestVersion);

		Element channelId = outputDoc.createElement("ChannelId");
		channelId.setTextContent("TIP");
		messageKey.appendChild(channelId);

		Element requestMessageInfo = outputDoc.createElement("RequestMessageInfo");
		requestHeader.appendChild(requestMessageInfo);

		Element bankId = outputDoc.createElement("BankId");
		bankId.setTextContent("01");
		requestMessageInfo.appendChild(bankId);

		Element timeZone = outputDoc.createElement("TimeZone");
		timeZone.setTextContent("GMT+05:30");
		requestMessageInfo.appendChild(timeZone);

		Element messageDateTime = outputDoc.createElement("MessageDateTime");
		messageDateTime.setTextContent("2019-02-11T17:12:45.169");
		requestMessageInfo.appendChild(messageDateTime);

		Element body = outputDoc.createElement("Body");
		root.appendChild(body);

		Element xferTrnAddRequest = outputDoc.createElement("XferTrnAddRequest");
		body.appendChild(xferTrnAddRequest);

		Element xferTrnAddRq = outputDoc.createElement("XferTrnAddRq");
		xferTrnAddRequest.appendChild(xferTrnAddRq);

		Element xferTrnHdr = outputDoc.createElement("XferTrnHdr");
		xferTrnAddRq.appendChild(xferTrnHdr);

		Element trnType = outputDoc.createElement("TrnType");
		trnType.setTextContent("T");
		xferTrnHdr.appendChild(trnType);

		Element trnSubType = outputDoc.createElement("TrnSubType");
		trnSubType.setTextContent("BI");
		xferTrnHdr.appendChild(trnSubType);

		Element xferTrnDetail = outputDoc.createElement("XferTrnDetail");
		xferTrnDetail.setTextContent("this frist add");
		xferTrnAddRq.appendChild(xferTrnDetail);

		Element partTrnRecList = outputDoc.createElement("PartTrnRecList");
		xferTrnDetail.appendChild(partTrnRecList);

		NodeList postings = inputDoc.getElementsByTagName("ns2:Posting");

		for (int i = 0; i < postings.getLength(); i++) {
			Element posting = (Element) postings.item(i);

			String accountNumber = posting.getElementsByTagName("ns2:AccountNumber").item(0).getTextContent();
			String debitCreditFlag = posting.getElementsByTagName("ns2:DebitCreditFlag").item(0).getTextContent();
			String postingAmount = posting.getElementsByTagName("ns2:PostingAmount").item(0).getTextContent();
			String postingCcy = posting.getElementsByTagName("ns2:PostingCcy").item(0).getTextContent();
			String postingNarrative1 = posting.getElementsByTagName("ns2:PostingNarrative1").item(0).getTextContent();
			String postingSeqNo = posting.getElementsByTagName("ns2:TransactionSeqNo").item(0).getTextContent();
			String valueDate = posting.getElementsByTagName("ns2:ValueDate").item(0).getTextContent();

			Element partTrnRec = outputDoc.createElement("PartTrnRec");

			Element acctId = outputDoc.createElement("AcctId");
			Element acctIdContent = outputDoc.createElement("AcctId");
			acctIdContent.setTextContent(accountNumber);
			acctId.appendChild(acctIdContent);
			partTrnRec.appendChild(acctId);

			Element creditDebitFlg = outputDoc.createElement("CreditDebitFlg");
			creditDebitFlg.setTextContent(debitCreditFlag);
			partTrnRec.appendChild(creditDebitFlg);

			Element trnAmt = outputDoc.createElement("TrnAmt");
			Element amountValue = outputDoc.createElement("amountValue");
			amountValue.setTextContent(String.valueOf(Double.parseDouble(postingAmount) / 100));
			trnAmt.appendChild(amountValue);
			Element currencyCode = outputDoc.createElement("currencyCode");
			currencyCode.setTextContent(postingCcy);
			trnAmt.appendChild(currencyCode);
			partTrnRec.appendChild(trnAmt);

			Element trnParticulars = outputDoc.createElement("TrnParticulars");
			trnParticulars.setTextContent(postingNarrative1);
			partTrnRec.appendChild(trnParticulars);

			Element partTrnRmks = outputDoc.createElement("PartTrnRmks");
			partTrnRec.appendChild(partTrnRmks);

			Element valueDt = outputDoc.createElement("ValueDt");
			valueDt.setTextContent(valueDate + "T17:12:45.408");
			partTrnRec.appendChild(valueDt);

			Element serialNum = outputDoc.createElement("SerialNum");
			serialNum.setTextContent(postingSeqNo);
			partTrnRec.appendChild(serialNum);

			partTrnRecList.appendChild(partTrnRec);
		}
	}
}
