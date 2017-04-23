package com.example.twitter;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;

public class TwitterService {

	private static final String TIMELINE_DISPLAY_FORMAT = "--------------------------\n%s:%s\n%s";

	private Twitter twitter;

	public TwitterService() {
		twitter = new TwitterFactory().getInstance();
	}

	public void printTimeline() throws TwitterException {
		twitter.getHomeTimeline().stream().map(this::toTimeLineDisplay).forEach(System.out::println);
	}

	public void printUserProfile() throws TwitterException {
		User user = twitter.verifyCredentials();
		System.out.println("名前：" + user.getName());
		System.out.println("表示名：" + user.getScreenName());
		System.out.println("フォロー" + user.getFriendsCount());
		System.out.println("フォロワー：" + user.getFollowersCount());
	}

	private String toTimeLineDisplay(Status status) {
		String userName = status.getUser().getName();
		String text = status.getText();
		Instant instant = status.getCreatedAt().toInstant();
		ZonedDateTime dateTime = instant.atZone(ZoneId.systemDefault());
		return String.format(TIMELINE_DISPLAY_FORMAT, userName, text, dateTime.toString());
	}

	public void tweet(String tweet) throws TwitterException {
		twitter.updateStatus(tweet);
	}
}
