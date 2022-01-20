package com.thrillio;

import java.util.List;

import com.thrillio.constants.KidFriendlyStatus;
import com.thrillio.constants.UserType;
import com.thrillio.controllers.BookmarkController;
import com.thrillio.entities.Bookmark;
import com.thrillio.entities.User;
import com.thrillio.partner.Shareable;

public class View {
	public static void Browse(User user, List<List<Bookmark>> bookmarks) {
		System.out.println("\n " + user.getEmail() + " is Browsing ... ");

		for (List<Bookmark> bookmarksList : bookmarks) {
			for (Bookmark bookmark : bookmarksList) {
				// if (bookmarkCount < DataStore.USER_BOOKMARK_LIMIT) {

				boolean isBookmarked = getBookmarkDecision(bookmark);
				if (isBookmarked) {
					BookmarkController.getInstance().saveUserBookmark(user, bookmark);
					System.out.println("New item bookmarked -- " + bookmark);
				}
				// }

				if (user.getUserType().equals(UserType.EDITOR) || user.getUserType().equals(UserType.CHIEF_EDITOR)) {
					// mark as kidfriendly

					if (bookmark.isKidFriendlyEligible()
							&& bookmark.getKidFriendlyStatus().equals(KidFriendlyStatus.UNKNOWN)) {
						KidFriendlyStatus kidFriendlyStatus = getKidFriendlyStatusDecision();
						if (!kidFriendlyStatus.equals(KidFriendlyStatus.UNKNOWN)) {
							/*
							 * bookmark.setKidFriendlyStatus(kidFriendlyStatus);
							 * System.out.println("KidFriendly status - " + kidFriendlyStatus + ", " +
							 * bookmark);
							 */
							BookmarkController.getInstance().setKidFriendlyStatus(user, kidFriendlyStatus, bookmark);
						}
					}

					if (bookmark.getKidFriendlyStatus().equals(KidFriendlyStatus.APPROVED)
							&& bookmark instanceof Shareable) {
						boolean isShared = shareDecision();
						if (isShared) {
							BookmarkController.getInstance().share(user, bookmark);
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

//TODO : In near future we will get the data from IO console
	private static boolean shareDecision() {

		return Math.random() < 0.4 ? true : false;
	}

	private static KidFriendlyStatus getKidFriendlyStatusDecision() {
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
