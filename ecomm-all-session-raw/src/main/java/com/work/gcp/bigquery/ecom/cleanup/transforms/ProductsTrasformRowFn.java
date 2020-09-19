package com.work.gcp.bigquery.ecom.cleanup.transforms;

import org.apache.beam.sdk.transforms.DoFn;

import com.google.api.services.bigquery.model.TableRow;
import com.work.gcp.bigquery.ecom.cleanup.common.CommonUtils;
import com.work.gcp.bigquery.ecom.cleanup.common.SchemaConstants;

/**
 * A transformation class for products 
 * table
 *
 * @author spaldewar
 *
 */
public class ProductsTrasformRowFn extends DoFn<TableRow, TableRow> {

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

		row.put(SchemaConstants.SKU, 
				CommonUtils.transformObjectToRequiredStringValue(row.get(SchemaConstants.SKU)));
		row.put(SchemaConstants.NAME, 
				CommonUtils.transformObjectToRequiredStringValue(row.get(SchemaConstants.NAME)));
		row.put(SchemaConstants.ORDERED_QUANTITY, 
				CommonUtils.transformObjectToRequiredIntegerValue(row.get(SchemaConstants.ORDERED_QUANTITY)));
		row.put(SchemaConstants.STOCK_LEVEL, 
				CommonUtils.transformObjectToRequiredIntegerValue(row.get(SchemaConstants.STOCK_LEVEL)));
		row.put(SchemaConstants.RESTOCKING_LEAD_TIME, 
				CommonUtils.transformObjectToRequiredIntegerValue(row.get(SchemaConstants.RESTOCKING_LEAD_TIME)));
		row.put(SchemaConstants.SENTIMENT_SCORE, 
				CommonUtils.transformObjectToRequiredDoubleValue(row.get(SchemaConstants.SENTIMENT_SCORE)));
		row.put(SchemaConstants.SENTIMENT_MAGNITUDE, 
				CommonUtils.transformObjectToRequiredDoubleValue(row.get(SchemaConstants.SENTIMENT_MAGNITUDE)));
		// get these rows as output PCollection
		context.output(row);
	}






}
