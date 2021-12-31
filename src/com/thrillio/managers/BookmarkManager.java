package com.thrillio.managers;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;

import com.thrillio.dao.BookmarkDao;
import com.thrillio.entities.Book;
import com.thrillio.entities.Bookmark;
import com.thrillio.entities.Movie;
import com.thrillio.entities.User;
import com.thrillio.entities.UserBookmark;
import com.thrillio.entities.Weblink;
import com.thrillio.util.HttpConnect;
import com.thrillio.util.IOUtil;

public class BookmarkManager {
	private static BookmarkManager instance = new BookmarkManager();

	private BookmarkDao dao = new BookmarkDao();

	private BookmarkManager() {
	}

	public static BookmarkManager getInstance() {
		return instance;
	}

	public Movie createMovie(long id, String title, String profileUrl, int releaseYear, String[] cast,
			String[] directors, String genre, double imdbRating) {
		Movie movie = new Movie();
		movie.setCast(cast);
		movie.setDirectors(directors);
		movie.setGenre(genre);
		movie.setId(id);
		movie.setTitle(title);
		movie.setProfileUrl(profileUrl);
		movie.setreleaseYear(releaseYear);
		movie.setImdbRating(imdbRating);
		return movie;
	}

	public Weblink createWeblink(long id, String title, String profileUrl, String url, String host) {
		Weblink weblink = new Weblink();
		weblink.setHost(host);
		weblink.setId(id);
		weblink.setProfileUrl(profileUrl);
		weblink.setUrl(url);
		weblink.setTitle(title);
		return weblink;
	}

	public Book createBook(long id, String title, String profileUrl, int publicationYear, String publisher,
			String[] authors, String genre, double amazonRating) {
		Book book = new Book();
		book.setId(id);
		book.setTitle(title);
		book.setProfileUrl(profileUrl);
		book.setPublicationYear(publicationYear);
		book.setPublisher(publisher);
		book.setGenre(genre);
		book.setAuthors(authors);
		book.setAmazonRating(amazonRating);
		return book;
	}

	public List<List<Bookmark>>  getBookmarks() {
		return dao.getBookmarks();
	}

	public void saveUserBookmark(User user, Bookmark bookmark) {
		UserBookmark userBookmark = new UserBookmark();
		userBookmark.setBookmark(bookmark);
		userBookmark.setUser(user);

		if (bookmark instanceof Weblink) {
			try {
				String url = ((Weblink) bookmark).getUrl();
				if (!url.endsWith(".pdf")) {
					String webpage = HttpConnect.download(((Weblink) bookmark).getUrl());
					if (webpage != null) {
						IOUtil.write(webpage, bookmark.getId());
					}
				}
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		BookmarkDao.saveUserBookmark(userBookmark);
	}

	public void setKidFriendlyStatus(User user, String kidFriendlyStatus, Bookmark bookmark) {
		bookmark.setKidFriendlyStatus(kidFriendlyStatus);
		bookmark.setKidFriendlyMarkedBy(user);
		System.out.println(
				"KidFriendly status - " + kidFriendlyStatus + ", marked by " + user.getEmail() + ", " + bookmark);
	}

	public void share(User user, Bookmark bookmark) {
		System.out.println("Data item to shared :");
		bookmark.setSharedBy(user);
		if (bookmark instanceof Book) {
			System.out.println(((Book) bookmark).getDataItem());
		} else if (bookmark instanceof Weblink)
			System.out.println(((Weblink) bookmark).getDataItem());
	}
}
