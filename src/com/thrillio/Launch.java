package com.thrillio;

import com.thrillio.entities.Bookmark;
import com.thrillio.entities.User;
import com.thrillio.managers.BookmarkManager;
import com.thrillio.managers.UserManager;

public class Launch {
	private static User users[];
	private static Bookmark bookmarks[][];

	private static void loadData() {

		System.out.println("1.Loading data ...");
		DataStore.loadData();

		users = UserManager.getInstance().getUsers();
		bookmarks = BookmarkManager.getInstance().getBookmarks();

		System.out.println("\n Printing data ...");
		printUserData();
		printBookmarkData();
	}

	private static void printBookmarkData() {
		for (Bookmark[] bookmarkList : bookmarks) {
			for (Bookmark bookmarks : bookmarkList) {
				System.out.println(bookmarks);
			}
		}
	}

	private static void printUserData() {
		for (User user : users) {
			System.out.println(user);
		}
	}

	public static void main(String[] args) {

		loadData();
		startBookmarking();

	}

	private static void startBookmarking() {
		System.out.println("\n 2.Start Bookmarking ...");
		for (User user : users) {
			View.Bookmark(user, bookmarks);
		}
	}

}
