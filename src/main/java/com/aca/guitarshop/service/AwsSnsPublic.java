package com.aca.guitarshop.service;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;

public class AwsSnsPublic {

	public static String publishMessage(String subject, String message) {

		PublishRequest publishRequest = new PublishRequest();
		publishRequest.setTopicArn(SnsClient.GUITAR_TOPIC_ARN);
		publishRequest.setSubject(subject);
		publishRequest.setMessage(message);

		AmazonSNS client = SnsClient.getAwsClient();
		PublishResult result = client.publish(publishRequest);

		System.out.println("messageID: " + result.getMessageId());

		return result.getMessageId();
	}
}
