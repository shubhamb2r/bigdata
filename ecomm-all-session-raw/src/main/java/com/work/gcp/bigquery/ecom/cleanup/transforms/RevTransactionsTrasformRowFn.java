package com.work.gcp.bigquery.ecom.cleanup.transforms;

import org.apache.beam.sdk.transforms.DoFn;

import com.google.api.services.bigquery.model.TableRow;
import com.work.gcp.bigquery.ecom.cleanup.common.ApplicationConstants;
import com.work.gcp.bigquery.ecom.cleanup.common.CommonUtils;
import com.work.gcp.bigquery.ecom.cleanup.common.SchemaConstants;

public class RevTransactionsTrasformRowFn extends DoFn<TableRow, TableRow> {

	private static final long serialVersionUID = 6370771400604013101L;

	@ProcessElement
	public void processElement(ProcessContext context) {
		TableRow row = context.element();


		Double totalsTotalTransactionRevenue = 
				CommonUtils.convertStringToOutputDouble(row.get(SchemaConstants.TOTALS_TOTAL_TRAN_REVENUE));

		if((Double.compare(totalsTotalTransactionRevenue, ApplicationConstants.ZERO) != 0) 
				&& ApplicationConstants.PAGE.equals(row.get(SchemaConstants.HITS_TYPE))) {

			row.put(SchemaConstants.GEONETWORK_CITY, 
					CommonUtils.convertStringToOutputString(row.get(SchemaConstants.GEONETWORK_CITY)));
			row.put(SchemaConstants.HITS_PRODUCT_PRODUCTVARIENT, 
					CommonUtils.convertStringToOutputString(row.get(SchemaConstants.HITS_PRODUCT_PRODUCTVARIENT)));
			row.put(SchemaConstants.TOTALS_TOTAL_TRAN_REVENUE, totalsTotalTransactionRevenue);
			row.put(SchemaConstants.TOTALS_TRANSACTIONS, 
					CommonUtils.convertStringToOutputInteger(row.get(SchemaConstants.TOTALS_TRANSACTIONS)));
			row.put(SchemaConstants.TOTALS_TIMEONSITE, 
					CommonUtils.convertStringToOutputInteger(row.get(SchemaConstants.TOTALS_TIMEONSITE)));
			row.put(SchemaConstants.HITS_PRODUCT_PRODUCTREFUNDAMT, 
					CommonUtils.convertStringToOutputDouble(row.get(SchemaConstants.HITS_PRODUCT_PRODUCTREFUNDAMT)));
			row.put(SchemaConstants.HITS_PRODUCT_PRODUCTQUANTITY, 
					CommonUtils.convertStringToOutputInteger(row.get(SchemaConstants.HITS_PRODUCT_PRODUCTQUANTITY)));
			row.put(SchemaConstants.HITS_PRODUCT_PRODUCTREVENUE, 
					CommonUtils.convertStringToOutputDouble(row.get(SchemaConstants.HITS_PRODUCT_PRODUCTREVENUE)));
			row.put(SchemaConstants.HITS_TRANSACTION_TRANSACTIONREVENUE, 
					CommonUtils.convertStringToOutputInteger(row.get(SchemaConstants.HITS_TRANSACTION_TRANSACTIONREVENUE)));
			row.put(SchemaConstants.HITS_TRANSACTION_TRANSACTIONID, 
					CommonUtils.convertStringToOutputString(row.get(SchemaConstants.HITS_TRANSACTION_TRANSACTIONID)));
			row.put(SchemaConstants.DATE, 
					CommonUtils.convertToDate(row.get(SchemaConstants.DATE)));


			row.put(SchemaConstants.UNIQUE_SESSION_ID_C, CommonUtils.createUniqueSessionId(
					row.get(SchemaConstants.FULL_VISITOR_ID), row.get(SchemaConstants.VISIT_ID)));

			row.put(SchemaConstants.TOTALS_TOTAL_TRAN_REVENUE_MANIPULATED, 
					((Double) row.get(SchemaConstants.TOTALS_TOTAL_TRAN_REVENUE) / ApplicationConstants.MILLION));

			// get these rows as output PCollection
			context.output(row);
		}
	}


}
