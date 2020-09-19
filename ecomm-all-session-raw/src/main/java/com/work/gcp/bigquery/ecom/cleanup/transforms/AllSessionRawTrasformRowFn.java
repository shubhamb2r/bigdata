package com.work.gcp.bigquery.ecom.cleanup.transforms;

import org.apache.beam.sdk.transforms.DoFn;

import com.google.api.services.bigquery.model.TableRow;
import com.work.gcp.bigquery.ecom.cleanup.common.ApplicationConstants;
import com.work.gcp.bigquery.ecom.cleanup.common.CommonUtils;
import com.work.gcp.bigquery.ecom.cleanup.common.SchemaConstants;

/**
 * A transformation class for all_session_raw 
 * table
 * 
 * @author spaldewar
 *
 */
public class AllSessionRawTrasformRowFn extends DoFn<TableRow, TableRow> {

	private static final long serialVersionUID = 6370771400604013101L;

	/**
	 * method to process the input tableRows and
	 * provides a desired output tableRow
	 * 
	 * 
	 * @param context
	 */
	@ProcessElement
	public void processElement(ProcessContext context) {
		TableRow row = context.element();


		Double totalTransactionRevenue = 
				CommonUtils.transformObjectToRequiredDoubleValue(row.get(SchemaConstants.TOTAL_TRANSACTION_REVENUE));

		if((Double.compare(totalTransactionRevenue, ApplicationConstants.ZERO) != 0) 
				&& ApplicationConstants.PAGE.equals(row.get(SchemaConstants.TYPE))) {

			row.put(SchemaConstants.CITY, 
					CommonUtils.transformObjectToRequiredStringValue(row.get(SchemaConstants.CITY)));
			row.put(SchemaConstants.PRODUCT_VARIENT, 
					CommonUtils.transformObjectToRequiredStringValue(row.get(SchemaConstants.PRODUCT_VARIENT)));
			row.put(SchemaConstants.ECOM_ACT_OPTION, 
					CommonUtils.transformObjectToRequiredStringValue(row.get(SchemaConstants.ECOM_ACT_OPTION)));
			row.put(SchemaConstants.TOTAL_TRANSACTION_REVENUE, totalTransactionRevenue);
			row.put(SchemaConstants.TRANSACTION, 
					CommonUtils.transformObjectToRequiredIntegerValue(row.get(SchemaConstants.TRANSACTION)));
			row.put(SchemaConstants.TIME_ON_SITE, 
					CommonUtils.transformObjectToRequiredIntegerValue(row.get(SchemaConstants.TIME_ON_SITE)));
			row.put(SchemaConstants.TIME_ON_SITE, 
					CommonUtils.transformObjectToRequiredIntegerValue(row.get(SchemaConstants.TIME_ON_SITE)));
			row.put(SchemaConstants.SESSION_QUALITY_DIM, 
					CommonUtils.transformObjectToRequiredIntegerValue(row.get(SchemaConstants.SESSION_QUALITY_DIM)));
			row.put(SchemaConstants.PRODUCT_REFUND_AMT, 
					CommonUtils.transformObjectToRequiredDoubleValue(row.get(SchemaConstants.PRODUCT_REFUND_AMT)));
			row.put(SchemaConstants.PRODUCT_QUANTITY, 
					CommonUtils.transformObjectToRequiredIntegerValue(row.get(SchemaConstants.PRODUCT_QUANTITY)));
			row.put(SchemaConstants.PRODUCT_REVENUE, 
					CommonUtils.transformObjectToRequiredDoubleValue(row.get(SchemaConstants.PRODUCT_REVENUE)));
			row.put(SchemaConstants.TRANSACTION_REVENUE, 
					CommonUtils.transformObjectToRequiredLongValue(row.get(SchemaConstants.TRANSACTION_REVENUE)));
			row.put(SchemaConstants.TRANSACTION_ID, 
					CommonUtils.transformObjectToRequiredStringValue(row.get(SchemaConstants.TRANSACTION_ID)));
			row.put(SchemaConstants.ECOM_ACT_OPTION, 
					CommonUtils.transformObjectToRequiredStringValue(row.get(SchemaConstants.ECOM_ACT_OPTION)));
			row.put(SchemaConstants.DATE, 
					CommonUtils.convertObjectToDateInString(row.get(SchemaConstants.DATE)));


			row.put(SchemaConstants.UNIQUE_SESSION_ID_C, CommonUtils.createUniqueSessionId(
					row.get(SchemaConstants.FULL_VISITOR_ID), row.get(SchemaConstants.VISIT_ID)));

			row.put(SchemaConstants.ECOM_ACT_LABEL_C, getActionLable(row.get(SchemaConstants.ECOM_ACT_TYPE)));
			row.put(SchemaConstants.TOTAL_TRANSACTION_REVENUE_MANIPULATED, 
					((Double) row.get(SchemaConstants.TOTAL_TRANSACTION_REVENUE) / ApplicationConstants.MILLION));

			// get these rows as output PCollection
			context.output(row);
		}
	}

	/**
	 * @param ecommActionType
	 * @return string
	 */
	private static String getActionLable(Object ecommActionType) {
		String result = null;
		Integer input = (null != ecommActionType) ? Integer.valueOf(ecommActionType.toString()) : 0;
		switch(input) {
		case 1:
			result = "Click through of product lists";
			break;
		case 2:
			result = "Product detail views";
			break;
		case 3:
			result = "Add product(s) to cart";
			break;
		case 4:
			result = "Remove product(s) from cart";
			break;
		case 5:
			result = "Check out";
			break;
		case 6:
			result = "Completed purchase";
			break;
		case 7:
			result = "Refund of purchase";
			break;
		case 8:
			result = "Checkout options";
			break;
		default:
			result = "Unknown";
			break;
		}
		return result;
	}
	
	

}
