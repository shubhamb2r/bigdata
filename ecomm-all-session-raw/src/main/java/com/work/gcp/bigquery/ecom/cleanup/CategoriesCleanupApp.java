package com.work.gcp.bigquery.ecom.cleanup;

import org.apache.beam.sdk.io.gcp.bigquery.BigQueryIO;
import org.apache.beam.sdk.io.gcp.bigquery.BigQueryIO.Write.CreateDisposition;
import org.apache.beam.sdk.io.gcp.bigquery.BigQueryIO.Write.WriteDisposition;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.PCollection;

import com.google.api.services.bigquery.model.TableRow;
import com.work.gcp.bigquery.ecom.cleanup.schema.CategoriesSchema;
import com.work.gcp.bigquery.ecom.cleanup.transforms.CategoriesTransformRowFn;

/**
 * pipeline for categories table
 * 
 * left with deduplication and formatting the category colunm
 * 
 * @author spaldewar
 *
 */
public class CategoriesCleanupApp {
	
	public static final String SELECT_ALL_CATAGORIES
			= "SELECT * FROM `data-to-insights.ecommerce.categories`";

	public static void main( String[] args) {
		
		PipelineOptions options = PipelineOptionsFactory
				.fromArgs(args).withValidation().as(PipelineOptions.class);
		options.setJobName("cleanup-ecomm-catagories");

		org.apache.beam.sdk.Pipeline pipeline = org.apache.beam.sdk.Pipeline.create(options);

		PCollection<TableRow> inputRows = pipeline.apply("Reading Data", BigQueryIO.readTableRows()
				.fromQuery(SELECT_ALL_CATAGORIES).usingStandardSql());

		PCollection<TableRow> transformedRows 
			= inputRows.apply("Transforming Rows", ParDo.of(new CategoriesTransformRowFn()));


		transformedRows.apply("Writing Output", 
				BigQueryIO.writeTableRows().to("focus-task-271908:ecommerce.categories_dataflow")
				.withSchema(CategoriesSchema.createSchema())
				.withCreateDisposition(CreateDisposition.CREATE_IF_NEEDED)
				.withWriteDisposition(WriteDisposition.WRITE_APPEND).withoutValidation());


		pipeline.run().waitUntilFinish();


	}
	
	
}
