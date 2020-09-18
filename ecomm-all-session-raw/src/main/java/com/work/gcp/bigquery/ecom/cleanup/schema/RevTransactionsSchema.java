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
public final class RevTransactionsSchema {
	
	//BigQuery dataTypes
	private static final String STRING = "STRING";
	private static final String INT64 = "INT64";
	private static final String FLOAT64 = "FLOAT64";
	private static final String DATE = "DATE";
	
	private RevTransactionsSchema() {}
	
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
				new TableFieldSchema().setName(SchemaConstants.HITS_TIME).setType(INT64),
				new TableFieldSchema().setName(SchemaConstants.GEONETWORK_COUNTRY).setType(STRING),
				new TableFieldSchema().setName(SchemaConstants.GEONETWORK_CITY).setType(STRING),
				new TableFieldSchema().setName(SchemaConstants.TOTALS_TOTAL_TRAN_REVENUE).setType(FLOAT64),
				new TableFieldSchema().setName(SchemaConstants.TOTALS_TRANSACTIONS).setType(INT64),
				new TableFieldSchema().setName(SchemaConstants.TOTALS_TIMEONSITE).setType(INT64),
				new TableFieldSchema().setName(SchemaConstants.TOTALS_PAGEVIEWS).setType(INT64),
				new TableFieldSchema().setName(SchemaConstants.DATE).setType(DATE),
				new TableFieldSchema().setName(SchemaConstants.VISIT_ID).setType(INT64),
				new TableFieldSchema().setName(SchemaConstants.HITS_TYPE).setType(STRING),
				new TableFieldSchema().setName(SchemaConstants.HITS_PRODUCT_PRODUCTREFUNDAMT).setType(FLOAT64),
				new TableFieldSchema().setName(SchemaConstants.HITS_PRODUCT_PRODUCTQUANTITY).setType(INT64),
				new TableFieldSchema().setName(SchemaConstants.HITS_PRODUCT_PRODUCTPRICE).setType(FLOAT64),
				new TableFieldSchema().setName(SchemaConstants.HITS_PRODUCT_PRODUCTREVENUE).setType(FLOAT64),
				new TableFieldSchema().setName(SchemaConstants.HITS_PRODUCT_PRODUCTSKU).setType(STRING),
				new TableFieldSchema().setName(SchemaConstants.HITS_PRODUCT_PRODUCTNAME).setType(STRING),
				new TableFieldSchema().setName(SchemaConstants.HITS_PRODUCT_PRODUCTCATEGORY).setType(STRING),
				new TableFieldSchema().setName(SchemaConstants.HITS_PRODUCT_PRODUCTVARIENT).setType(STRING),
				new TableFieldSchema().setName(SchemaConstants.HITS_ITEM_CURR_CODE).setType(STRING),
				new TableFieldSchema().setName(SchemaConstants.HITS_TRANSACTION_TRANSACTIONREVENUE).setType(INT64),
				new TableFieldSchema().setName(SchemaConstants.HITS_TRANSACTION_TRANSACTIONID).setType(STRING),
				new TableFieldSchema().setName(SchemaConstants.HITS_PAGE_PAGETITLE).setType(STRING),
				new TableFieldSchema().setName(SchemaConstants.HITS_PAGE_PAGEPATHLEVEL1).setType(STRING),
				
				//adding new required columns
				new TableFieldSchema().setName(SchemaConstants.UNIQUE_SESSION_ID_C).setType(STRING),
				new TableFieldSchema().setName(SchemaConstants.TOTALS_TOTAL_TRAN_REVENUE_MANIPULATED).setType(FLOAT64)
			));
	} 
}
