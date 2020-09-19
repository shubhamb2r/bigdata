package com.work.gcp.bigquery.ecom.cleanup;

import org.apache.beam.sdk.io.gcp.bigquery.BigQueryIO;
import org.apache.beam.sdk.io.gcp.bigquery.BigQueryIO.Write.CreateDisposition;
import org.apache.beam.sdk.io.gcp.bigquery.BigQueryIO.Write.WriteDisposition;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.PCollection;

import com.google.api.services.bigquery.model.TableRow;
import com.work.gcp.bigquery.ecom.cleanup.schema.ProductsSchema;
import com.work.gcp.bigquery.ecom.cleanup.transforms.ProductsTrasformRowFn;

/**
 * A pipeline for products table
 * 
 * @author spaldewar
 *
 */
public class ProductsCleanupApp {
	
	public static final String SELECT_ALL_PRODUCTS
			= "SELECT * FROM `data-to-insights.ecommerce.products`";

	public static void main( String[] args) {
		
		PipelineOptions options = PipelineOptionsFactory
				.fromArgs(args).withValidation().as(PipelineOptions.class);
		options.setJobName("cleanup-ecomm-products");

		org.apache.beam.sdk.Pipeline pipeline = org.apache.beam.sdk.Pipeline.create(options);

		PCollection<TableRow> inputRows = pipeline.apply("Reading Data", BigQueryIO.readTableRows()
				.fromQuery(SELECT_ALL_PRODUCTS).usingStandardSql());

		PCollection<TableRow> transformedRows 
			= inputRows.apply("Transforming Rows", ParDo.of(new ProductsTrasformRowFn()));


		transformedRows.apply("Writing Output", 
				BigQueryIO.writeTableRows().to("focus-task-271908:ecommerce.products_dataflow")
				.withSchema(ProductsSchema.createSchema())
				.withCreateDisposition(CreateDisposition.CREATE_IF_NEEDED)
				.withWriteDisposition(WriteDisposition.WRITE_APPEND).withoutValidation());

		pipeline.run().waitUntilFinish();


	}
	
	
}
