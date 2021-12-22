package com.thrillio.managers;

import com.thrillio.entities.Book;
import com.thrillio.entities.Movie;
import com.thrillio.entities.Weblink;

public class BookmarkManager {
	private static BookmarkManager instance = new BookmarkManager();

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

}
