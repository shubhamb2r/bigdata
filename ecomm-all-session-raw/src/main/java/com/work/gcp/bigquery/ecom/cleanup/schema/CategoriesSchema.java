package com.work.gcp.bigquery.ecom.cleanup.schema;

import java.util.Arrays;

import com.google.api.services.bigquery.model.TableFieldSchema;
import com.google.api.services.bigquery.model.TableSchema;
import com.work.gcp.bigquery.ecom.cleanup.common.SchemaConstants;

/**
 * schema for categories table
 * 
 * @author spaldewar
 *
 */
public final class CategoriesSchema {

	//BigQuery dataTypes
	private static final String STRING = "STRING";

	private CategoriesSchema() {}
	
	/**
	 * Method to get schema for 
	 * BigQuery table
	 * 
	 * @return TableSchema
	 */
	public static TableSchema createSchema() {
		return new TableSchema().setFields(Arrays.asList(
				new TableFieldSchema().setName(SchemaConstants.PRODUCT_SKU).setType(STRING),
				new TableFieldSchema().setName(SchemaConstants.CATEGORIES).setType(STRING)
			));
	} 

}
