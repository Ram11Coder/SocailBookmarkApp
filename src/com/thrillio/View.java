package com.thrillio;

import com.thrillio.constants.KidFriendlyStatus;
import com.thrillio.constants.UserType;
import com.thrillio.controllers.BookmarkController;
import com.thrillio.entities.Bookmark;
import com.thrillio.entities.User;

public class View {
	public static void Browse(User user, Bookmark[][] bookmarks) {
		System.out.println("\n " + user.getEmail() + " is Browsing ... ");

		int bookmarkCount = 0;

		for (Bookmark[] bookmarksList : bookmarks) {
			for (Bookmark bookmark : bookmarksList) {
				if (bookmarkCount < DataStore.USER_BOOKMARK_LIMIT) {

					boolean isBookmarked = getBookmarkDecision(bookmark);
					if (isBookmarked) {
						bookmarkCount++;
						BookmarkController.getInstance().saveUserBookmark(user, bookmark);
						System.out.println("New item bookmarked -- " + bookmark);
					}
				}

				if (user.getUserType().equals(UserType.EDITOR) || user.getUserType().equals(UserType.CHIEF_EDITOR)) {

					if (bookmark.isKidFriendlyEligible()
							&& bookmark.getKidFriendlyStatus().equals(KidFriendlyStatus.UNKNOWN)) {
						String kidFriendlyStatus = getKidFriendlyStatusDecision();
						if (!kidFriendlyStatus.equals(KidFriendlyStatus.UNKNOWN)) {
							bookmark.setKidFriendlyStatus(kidFriendlyStatus);
							System.out.println("KidFriendly status - " + kidFriendlyStatus + ", " + bookmark);
						}
					}
				}

			}
		}

		/*
		 * for (int i = 0; i < DataStore.USER_BOOKMARK_LIMIT; i++) {
		 * 
		 * int typeOffset = (int) (Math.random() * DataStore.BOOKMARK_TYPES_COUNT); int
		 * bookmarkOffset = (int) (Math.random() * DataStore.BOOKMARK_COUNT_PER_TYPE);
		 * 
		 * Bookmark bookmark = bookmarks[typeOffset][bookmarkOffset];
		 * BookmarkController.getInstance().saveUserBookmark(user, bookmark);
		 * System.out.println(bookmark); }
		 */
	}

	private static String getKidFriendlyStatusDecision() {
		double random = Math.random();
		return (random < 0.4) ? KidFriendlyStatus.APPROVED
				: (random >= 0.4 && random <= 0.8) ? KidFriendlyStatus.REJECTED : KidFriendlyStatus.UNKNOWN;
	}

	private static boolean getBookmarkDecision(Bookmark bookmark) {

		return Math.random() < 0.5 ? true : false;
	}

	/**
	 * public static void Bookmark(User user, Bookmark[][] bookmarks) {
	 * System.out.println("\n " + user.getEmail() + " is booking ");
	 * 
	 * for (int i = 0; i < DataStore.USER_BOOKMARK_LIMIT; i++) {
	 * 
	 * int typeOffset = (int) (Math.random() * DataStore.BOOKMARK_TYPES_COUNT); int
	 * bookmarkOffset = (int) (Math.random() * DataStore.BOOKMARK_COUNT_PER_TYPE);
	 * 
	 * Bookmark bookmark = bookmarks[typeOffset][bookmarkOffset];
	 * BookmarkController.getInstance().saveUserBookmark(user, bookmark);
	 * System.out.println(bookmark); } }
	 */
}
