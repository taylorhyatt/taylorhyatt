package com.aca.guitarshop.service;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.amazonaws.services.sns.model.SubscribeResult;
// requesting to send out info to subscribers to accept
public class AwsSnsSubscription {

	public static String subscribeEmail(String email) {
		SubscribeRequest request = new SubscribeRequest();
		request.setEndpoint(email);
		;
		request.setProtocol("email");
		request.setTopicArn(SnsClient.GUITAR_TOPIC_ARN);

		AmazonSNS client = SnsClient.getAwsClient();
		SubscribeResult result = client.subscribe(request);

		System.out.println("subscribe arn: " + result.getSubscriptionArn());

		return result.getSubscriptionArn();
	}

	public static String subscribePhoneNumber(String phoneNumber) {
		SubscribeRequest request = new SubscribeRequest();
		request.setEndpoint("+1" + phoneNumber);
		request.setProtocol("sms");
		request.setTopicArn(SnsClient.GUITAR_TOPIC_ARN);

		AmazonSNS client = SnsClient.getAwsClient();
		SubscribeResult result = client.subscribe(request);

		System.out.println("subscribe arn: " + result.getSubscriptionArn());

		return result.getSubscriptionArn();

	}

}
