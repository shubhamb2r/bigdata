package com.work.gcp.bigquery.ecom.cleanup;

import org.apache.beam.sdk.io.gcp.bigquery.BigQueryIO;
import org.apache.beam.sdk.io.gcp.bigquery.BigQueryIO.Write.CreateDisposition;
import org.apache.beam.sdk.io.gcp.bigquery.BigQueryIO.Write.WriteDisposition;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.PCollection;

import com.google.api.services.bigquery.model.TableRow;
import com.work.gcp.bigquery.ecom.cleanup.schema.AllSessionRawSchema;
import com.work.gcp.bigquery.ecom.cleanup.transforms.AllSessionRawTrasformRowFn;

/**
 * Hello world!
 *
 */
public class AllSessionRawCleanupApp {

	/* This is to test 1000 records and it worked */
	public static final String SELECT_ALL_RECORDS 
	= "SELECT * FROM `data-to-insights.ecommerce.all_sessions_raw`";

	/* This is for removing duplicate sessions not tested yet but on bigquery validated well*/
	private static final String SELECT_DEDUPLICATED_RECORDS = "WITH cte as (SELECT" + 
			"    ROW_NUMBER() over (PARTITION BY `fullVisitorId`, `visitId`) as idx," + 
			"    *" + 
			" FROM `data-to-insights.ecommerce.all_sessions_raw`) " + 
			"SELECT * FROM cte WHERE idx = 1";

	public static void main( String[] args) {


		PipelineOptions options = PipelineOptionsFactory
				.fromArgs(args).withValidation().as(PipelineOptions.class);
		options.setJobName("cleanup-ecomm-all-sessions-row");

		org.apache.beam.sdk.Pipeline pipeline = org.apache.beam.sdk.Pipeline.create(options);

		PCollection<TableRow> inputRows = pipeline.apply("Reading Data", BigQueryIO.readTableRows()
				.fromQuery(SELECT_ALL_RECORDS).usingStandardSql());

		PCollection<TableRow> transformedRows 
			= inputRows.apply("Transforming Rows", ParDo.of(new AllSessionRawTrasformRowFn()));

		//deduplication pending

		//writing output
		transformedRows.apply("Writing Output", 
				BigQueryIO.writeTableRows().to("focus-task-271908:ecommerce.all_sessions_raw_dataflow")
				.withSchema(AllSessionRawSchema.createSchema())
				.withCreateDisposition(CreateDisposition.CREATE_IF_NEEDED)
				.withWriteDisposition(WriteDisposition.WRITE_APPEND).withoutValidation());

		pipeline.run().waitUntilFinish();


	}
}
