package com.thrillio.entities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.thrillio.managers.BookmarkManager;

/*Keyword : porn , adult
 * porn in {url ,title} return false
 * 
 * adult in {host} return false
 * 
 */
public class WebLinkTest {

	@Test
	public void testIsKidFriendlyEligible() {
		// 1.Test case : Porin in url - false
		Weblink weblink = BookmarkManager.getInstance().createWeblink(2000, "Taming Tiger,Part 2", "",
				"http://www.javaworld.com/article/2072759/core-java/porn-tiger--part-2.html",
				"http://www.javaworld.com");
		boolean isKidFriendlyEligible = weblink.isKidFriendlyEligible();
		assertFalse("For porn in url - isKidFriendlyEligible() must return false", isKidFriendlyEligible);

		// 2.Test case : porn in title - false

		weblink = BookmarkManager.getInstance().createWeblink(2000, "Taming porn,Part 2", "",
				"http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html",
				"http://www.javaworld.com");
		isKidFriendlyEligible = weblink.isKidFriendlyEligible();
		assertFalse("For porn in tile - isKidFriendlyEligible() must return false", isKidFriendlyEligible);

		// 3.Test case : adult in host - false
		weblink = BookmarkManager.getInstance().createWeblink(2000, "Taming taming,Part 2", "",
				"http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html", "http://www.adult.com");
		isKidFriendlyEligible = weblink.isKidFriendlyEligible();
		assertFalse("For adult in host - isKidFriendlyEligible() must return false", isKidFriendlyEligible);

		// 4.Test case : adult in url - true

		weblink = BookmarkManager.getInstance().createWeblink(2000, "Taming taming,Part 2", "",
				"http://www.javaworld.com/adult/2072759/core-java/taming-tiger--part-2.html",
				"http://www.javaworld.com");
		isKidFriendlyEligible = weblink.isKidFriendlyEligible();
		assertTrue("For adult in url - isKidFriendlyEligible() must return true", isKidFriendlyEligible);

		// 5.Test case : adult in title - true
		weblink = BookmarkManager.getInstance().createWeblink(2000, "Taming adult,Part 2", "",
				"http://www.javaworld.com/gaming/2072759/core-java/taming-tiger--part-2.html",
				"http://www.javaworld.com");
		isKidFriendlyEligible = weblink.isKidFriendlyEligible();
		assertTrue("For adult in title - isKidFriendlyEligible() must return true", isKidFriendlyEligible);
	}

}
