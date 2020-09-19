package com.work.gcp.bigquery.ecom.cleanup.transforms;

import java.util.regex.Pattern;

import org.apache.beam.sdk.transforms.DoFn;
import org.apache.commons.lang3.StringUtils;

import com.google.api.services.bigquery.model.TableRow;
import com.work.gcp.bigquery.ecom.cleanup.common.SchemaConstants;

/**
 * 
 * A transformation class for categories 
 * table
 * 
 * @author spaldewar
 *
 */
public class CategoriesTransformRowFn extends DoFn<TableRow, TableRow> {

	private static final long serialVersionUID = 9221929370293995789L;

	private static Pattern spacePattern = Pattern.compile("\\s+");

	private static Pattern slashPattern = Pattern.compile("/");
	
	// not worked in java get data between last and last but one slash
	private static Pattern BETWEEN_LAST_TWO_SLASHES = Pattern.compile(".*\\/(.*)\\/");

	/**
	 * method to process the input tableRows and
	 * provides a desired output tableRow
	 * 
	 * @param context
	 */
	@ProcessElement
	public void processElement(ProcessContext context) {
		TableRow row = context.element();

		Object productSKUObject = row.get(SchemaConstants.PRODUCT_SKU);
		Object categoresObject = row.get(SchemaConstants.CATEGORIES);

		if(null != productSKUObject) {
			String productSKU = productSKUObject.toString();
			productSKU = spacePattern.matcher(productSKU).replaceAll(StringUtils.EMPTY);
			row.put(SchemaConstants.PRODUCT_SKU, productSKU);
		}

		if(null != categoresObject) {
			String categories = categoresObject.toString();
			categories = spacePattern.matcher(slashPattern.matcher(categories)
					.replaceAll(StringUtils.EMPTY)).replaceAll(StringUtils.EMPTY);
			row.put(SchemaConstants.CATEGORIES, categories);
		}

		if(null != productSKUObject && null != categoresObject) {
			context.output(row);
		}
	}


}
