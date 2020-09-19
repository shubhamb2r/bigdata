package com.work.gcp.bigquery.ecom.cleanup.schema;

import java.util.Arrays;

import com.google.api.services.bigquery.model.TableFieldSchema;
import com.google.api.services.bigquery.model.TableSchema;
import com.work.gcp.bigquery.ecom.cleanup.common.SchemaConstants;

/**
 * schema for products table
 * 
 * @author spaldewar
 *
 */
public class ProductsSchema {

	//BigQuery dataTypes
	private static final String STRING = "STRING";
	private static final String INT64 = "INT64";
	private static final String FLOAT64 = "FLOAT64";

	private ProductsSchema() {}

	/**
	 * Method to get schema for 
	 * BigQuery table
	 * 
	 * @return TableSchema
	 */
	public static TableSchema createSchema() {
		return new TableSchema().setFields(Arrays.asList(
				new TableFieldSchema().setName(SchemaConstants.SKU).setType(STRING),
				new TableFieldSchema().setName(SchemaConstants.NAME).setType(STRING),
				new TableFieldSchema().setName(SchemaConstants.ORDERED_QUANTITY).setType(INT64),
				new TableFieldSchema().setName(SchemaConstants.STOCK_LEVEL).setType(INT64),
				new TableFieldSchema().setName(SchemaConstants.RESTOCKING_LEAD_TIME).setType(INT64),
				new TableFieldSchema().setName(SchemaConstants.SENTIMENT_SCORE).setType(FLOAT64),
				new TableFieldSchema().setName(SchemaConstants.SENTIMENT_MAGNITUDE).setType(FLOAT64)
			));
	} 

}
