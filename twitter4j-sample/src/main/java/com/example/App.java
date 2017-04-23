package com.example;

import com.example.twitter.TwitterService;

import twitter4j.TwitterException;

public class App {

	public static void main(String[] args) {
		TwitterService twitterService = new TwitterService();
		try {
			twitterService.printUserProfile();
			twitterService.printTimeline();
			twitterService.tweet("test");
			twitterService.printTimeline();
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}
}
