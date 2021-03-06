package com.javasampleapproach.fcm.pushnotif.service;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AndroidPushNotificationsService {
	//server key of your own firebase project
	private static final String FIREBASE_SERVER_KEY = "AAAA1w8ozvo:APA91bHVxiYI9Z0LNU7zV3maC5HiGuPdnBHeDSWkrBo-gkcjZBmwStYgoReniL4HCncDWo30CzAVMQOXmXcDu6fNdKeAHF_1NllXm7eSSzCLWtU0SDx1qBbeFTVsig7md5960ZqrtahU";
	private static final String FIREBASE_API_URL = "https://fcm.googleapis.com/fcm/send";

	@Async
	public CompletableFuture<String> send(HttpEntity<String> entity) {

		RestTemplate restTemplate = new RestTemplate();

		ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
		interceptors.add(new HeaderRequestInterceptor("Authorization", "key=" + FIREBASE_SERVER_KEY));
		interceptors.add(new HeaderRequestInterceptor("Content-Type", "application/json"));
		restTemplate.setInterceptors(interceptors);

		String firebaseResponse = restTemplate.postForObject(FIREBASE_API_URL, entity, String.class);
		System.out.println("inService"+entity);

		return CompletableFuture.completedFuture(firebaseResponse);
	}
}
