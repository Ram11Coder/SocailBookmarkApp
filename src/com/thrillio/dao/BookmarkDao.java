package com.thrillio.dao;

import java.util.List;

import com.thrillio.DataStore;
import com.thrillio.entities.Bookmark;
import com.thrillio.entities.UserBookmark;

public class BookmarkDao {
	public List<List<Bookmark>>  getBookmarks() {
		return DataStore.getBookmarks();
	}

	public static void saveUserBookmark(UserBookmark userBookmark) {
		DataStore.add(userBookmark);

	}
}
