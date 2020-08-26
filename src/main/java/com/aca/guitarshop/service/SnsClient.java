package com.aca.guitarshop.service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
// 
public class SnsClient {

	private static final String ACCESS_KEY = "AKIA443756BSVJPNRHM7";
	private static final String SECRET_KEY = "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";
	protected static final String GUITAR_TOPIC_ARN = "arn:aws:sns:us-east-1:886642176101:Hyatt-Guitar-Shop";

	public static AmazonSNS getAwsClient() {
		BasicAWSCredentials basicAwsCredentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
		AmazonSNS snsClient = AmazonSNSClient.builder().withRegion("us-east-1")
				.withCredentials(new AWSStaticCredentialsProvider(basicAwsCredentials)).build();

		return snsClient;
	}

}
