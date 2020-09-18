package com.work.gcp.bigquery.ecom.cleanup.schema;

import java.util.Arrays;

import com.google.api.services.bigquery.model.TableFieldSchema;
import com.google.api.services.bigquery.model.TableSchema;
import com.work.gcp.bigquery.ecom.cleanup.common.SchemaConstants;

public final class CheckoutNudgeSchema {

	//BigQuery dataTypes
	private static final String STRING = "STRING";
	private static final String INT64 = "INT64";
	private static final String FLOAT64 = "FLOAT64";

	private CheckoutNudgeSchema() {}

	/**
	 * Method to get schema for 
	 * BigQuery table
	 * 
	 * @return TableSchema
	 */
	public static TableSchema createSchema() {
		return new TableSchema().setFields(Arrays.asList(
				new TableFieldSchema().setName(SchemaConstants.FULL_VISITOR_ID).setType(STRING),
				new TableFieldSchema().setName(SchemaConstants.NO_OF_SESSIONS).setType(INT64),
				new TableFieldSchema().setName(SchemaConstants.NO_OF_PRODUCTS_VIEWED).setType(INT64),
				new TableFieldSchema().setName(SchemaConstants.SESSION_TIME_ON_SITE_MINUTE_MAX).setType(FLOAT64),
				new TableFieldSchema().setName(SchemaConstants.ECOMM_ACTION_TYPE_MAX).setType(STRING)
			));
	} 

}
