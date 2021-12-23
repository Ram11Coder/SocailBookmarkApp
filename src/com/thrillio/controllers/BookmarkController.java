package com.thrillio.controllers;

import com.thrillio.entities.Bookmark;
import com.thrillio.entities.User;
import com.thrillio.managers.BookmarkManager;

public class BookmarkController {
	private static BookmarkController instance = new BookmarkController();

	private BookmarkController() {
	}

	public static BookmarkController getInstance() {
		return instance;
	}

	public void saveUserBookmark(User user, Bookmark bookmark) {
		BookmarkManager.getInstance().saveUserBookmark(user, bookmark);

	}
}
