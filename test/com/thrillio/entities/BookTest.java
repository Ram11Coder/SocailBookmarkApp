package com.thrillio.entities;

import static org.junit.Assert.*;

import org.junit.Test;

import com.thrillio.DataStore;
import com.thrillio.constants.BookGenre;
import com.thrillio.managers.BookmarkManager;

public class BookTest {

	@Test
	public void testIsKidFriendlyEligible() {

		// Testcase : 1 - genre self help
		Book book = BookmarkManager.getInstance().createBook(4000, "Walden", "", 1854, "Wilder Publication",
				new String[] { "Henry Davi Thoreau" }, BookGenre.PHILOSOPHY, 4.3);
		boolean iskidFriendlyStatus = book.isKidFriendlyEligible();
		assertFalse("Movie gener is philosophy return false", iskidFriendlyStatus);
		// Testcase : 2 - gener philisophy
		book = BookmarkManager.getInstance().createBook(4000, "Walden", "", 1854, "Wilder Publication",
				new String[] { "Henry Davi Thoreau" }, BookGenre.SELF_HELP, 4.3);
		iskidFriendlyStatus = book.isKidFriendlyEligible();
		assertFalse("Movie gener is selfhelp return false", iskidFriendlyStatus);

	}

}
