package com.thrillio.entities;

import static org.junit.Assert.*;

import org.junit.Test;

import com.thrillio.constants.MovieGenre;
import com.thrillio.managers.BookmarkManager;

public class MovieTest {

	@Test
	public void testIsKidFriendlyEligible() {
		// Test case 1: - Movie gener horror
		Movie movie = BookmarkManager.getInstance().createMovie(3000, "Citizen Kane", "", 1941,
				new String[] { "Orson Welles", "Joseph Cotten" }, new String[] { "Orson Welles" }, MovieGenre.HORROR,
				8.5);

		boolean iskidFriendly = movie.isKidFriendlyEligible();

		assertFalse("Movie genre is horrer - isKidFriendlyEligible() return flase", iskidFriendly);

		// Test case 2: movie genre thriller
		movie = BookmarkManager.getInstance().createMovie(3000, "Citizen Kane", "", 1941,
				new String[] { "Orson Welles", "Joseph Cotten" }, new String[] { "Orson Welles" }, MovieGenre.THRILLERS,
				8.5);

		iskidFriendly = movie.isKidFriendlyEligible();

		assertFalse("Movie genre is thriller - isKidFriendlyEligible() return flase", iskidFriendly);
	}

}
