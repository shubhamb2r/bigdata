package com.work.gcp.bigquery.ecom.cleanup.schema;

import java.util.Arrays;

import com.google.api.services.bigquery.model.TableFieldSchema;
import com.google.api.services.bigquery.model.TableSchema;
import com.work.gcp.bigquery.ecom.cleanup.common.SchemaConstants;

/**
 * Class to create schema for BigQuery table
 * 
 * @author spaldewar
 *
 */
public final class AllSessionRawSchema {
	
	//BigQuery dataTypes
	private static final String STRING = "STRING";
	private static final String INT64 = "INT64";
	private static final String FLOAT64 = "FLOAT64";
	private static final String DATE = "DATE";
	
	private AllSessionRawSchema() {}
	
	/**
	 * Method to get schema for 
	 * BigQuery table
	 * 
	 * @return TableSchema
	 */
	public static TableSchema createSchema() {
		return new TableSchema().setFields(Arrays.asList(
				new TableFieldSchema().setName(SchemaConstants.FULL_VISITOR_ID).setType(STRING),
				new TableFieldSchema().setName(SchemaConstants.CHANNEL_GROUP).setType(STRING),
				new TableFieldSchema().setName(SchemaConstants.TIME).setType(INT64),
				new TableFieldSchema().setName(SchemaConstants.COUNTRY).setType(STRING),
				new TableFieldSchema().setName(SchemaConstants.CITY).setType(STRING),
				new TableFieldSchema().setName(SchemaConstants.TOTAL_TRANSACTION_REVENUE).setType(FLOAT64),
				new TableFieldSchema().setName(SchemaConstants.TRANSACTION).setType(INT64),
				new TableFieldSchema().setName(SchemaConstants.TIME_ON_SITE).setType(INT64),
				new TableFieldSchema().setName(SchemaConstants.PAGEVIEWS).setType(INT64),
				new TableFieldSchema().setName(SchemaConstants.SESSION_QUALITY_DIM).setType(INT64),
				new TableFieldSchema().setName(SchemaConstants.DATE).setType(DATE),
				new TableFieldSchema().setName(SchemaConstants.VISIT_ID).setType(INT64),
				new TableFieldSchema().setName(SchemaConstants.TYPE).setType(STRING),
				new TableFieldSchema().setName(SchemaConstants.PRODUCT_REFUND_AMT).setType(FLOAT64),
				new TableFieldSchema().setName(SchemaConstants.PRODUCT_PRICE).setType(FLOAT64),
				new TableFieldSchema().setName(SchemaConstants.PRODUCT_QUANTITY).setType(INT64),
				new TableFieldSchema().setName(SchemaConstants.PRODUCT_REVENUE).setType(FLOAT64),
				new TableFieldSchema().setName(SchemaConstants.PRODUCT_SKU).setType(STRING),
				new TableFieldSchema().setName(SchemaConstants.PRODUCT_NAME).setType(STRING),
				new TableFieldSchema().setName(SchemaConstants.PRODUCT_CATAGORY).setType(STRING),
				new TableFieldSchema().setName(SchemaConstants.PRODUCT_VARIENT).setType(STRING),
				new TableFieldSchema().setName(SchemaConstants.CURRENCY_CODE).setType(STRING),
				new TableFieldSchema().setName(SchemaConstants.TRANSACTION_REVENUE).setType(INT64),
				new TableFieldSchema().setName(SchemaConstants.TRANSACTION_ID).setType(STRING),
				new TableFieldSchema().setName(SchemaConstants.PAGE_TITLE).setType(STRING),
				new TableFieldSchema().setName(SchemaConstants.PAGE_PATH_LEVEL).setType(STRING),
				new TableFieldSchema().setName(SchemaConstants.ECOM_ACT_TYPE).setType(STRING),
				new TableFieldSchema().setName(SchemaConstants.ECOM_ACT_STEP).setType(INT64),
				new TableFieldSchema().setName(SchemaConstants.ECOM_ACT_OPTION).setType(STRING),
				
				//adding new required columns
				new TableFieldSchema().setName(SchemaConstants.UNIQUE_SESSION_ID_C).setType(STRING),
				new TableFieldSchema().setName(SchemaConstants.ECOM_ACT_LABEL_C).setType(STRING),
				new TableFieldSchema().setName(SchemaConstants.TOTAL_TRANSACTION_REVENUE_MANIPULATED).setType(FLOAT64)
			));
	} 
}
