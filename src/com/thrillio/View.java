package com.thrillio;

import com.thrillio.controllers.BookmarkController;
import com.thrillio.entities.Bookmark;
import com.thrillio.entities.User;

public class View {
	public static void Bookmark(User user, Bookmark[][] bookmarks) {
		System.out.println("\n " + user.getEmail() + " is booking ");

		for (int i = 0; i < DataStore.USER_BOOKMARK_LIMIT; i++) {

			int typeOffset = (int) (Math.random() * DataStore.BOOKMARK_TYPES_COUNT);
			int bookmarkOffset = (int) (Math.random() * DataStore.BOOKMARK_COUNT_PER_TYPE);

			Bookmark bookmark = bookmarks[typeOffset][bookmarkOffset];
			BookmarkController.getInstance().saveUserBookmark(user, bookmark);
			System.out.println(bookmark);
		}
	}
}
