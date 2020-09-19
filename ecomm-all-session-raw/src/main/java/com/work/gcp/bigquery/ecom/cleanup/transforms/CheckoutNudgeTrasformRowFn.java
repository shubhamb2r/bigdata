package com.work.gcp.bigquery.ecom.cleanup.transforms;

import org.apache.beam.sdk.transforms.DoFn;

import com.google.api.services.bigquery.model.TableRow;
import com.work.gcp.bigquery.ecom.cleanup.common.CommonUtils;
import com.work.gcp.bigquery.ecom.cleanup.common.SchemaConstants;

/**
 * A transformation class for checkout_nudge 
 * table
 *
 * @author spaldewar
 *
 */
public class CheckoutNudgeTrasformRowFn extends DoFn<TableRow, TableRow> {

	private static final long serialVersionUID = 6370771400604013101L;

	/**
	 * method to process the input tableRows and
	 * provides a desired output tableRow
	 * 
	 * @param context
	 */
	@ProcessElement
	public void processElement(ProcessContext context) {
		TableRow row = context.element();
		
		Object fullVisitorId =  row.get(SchemaConstants.CHECKOUT_NUDGE_FULL_VISITOR_ID);

		if (fullVisitorId != null) {

			row.put(SchemaConstants.CHECKOUT_NUDGE_FULL_VISITOR_ID, 
					CommonUtils.transformObjectToRequiredStringValue(row.get(SchemaConstants.CHECKOUT_NUDGE_FULL_VISITOR_ID)));
			row.put(SchemaConstants.NO_OF_SESSIONS, 
					CommonUtils.transformObjectToRequiredIntegerValue(row.get(SchemaConstants.NO_OF_SESSIONS)));
			row.put(SchemaConstants.NO_OF_PRODUCTS_VIEWED, 
					CommonUtils.transformObjectToRequiredIntegerValue(row.get(SchemaConstants.NO_OF_PRODUCTS_VIEWED)));
			row.put(SchemaConstants.SESSION_TIME_ON_SITE_MINUTE_MAX, 
					CommonUtils.transformObjectToRequiredDoubleValue(row.get(SchemaConstants.SESSION_TIME_ON_SITE_MINUTE_MAX)));
			row.put(SchemaConstants.ECOMM_ACTION_TYPE_MAX, 
					CommonUtils.transformObjectToRequiredIntegerValue(row.get(SchemaConstants.ECOMM_ACTION_TYPE_MAX)));
			// get these rows as output PCollection
			context.output(row);
		}
	}

}
