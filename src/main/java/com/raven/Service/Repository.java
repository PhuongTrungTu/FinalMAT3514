package com.raven.Service;

import com.raven.frame.HashMap;

/**
 * Represents a repository with a link and title.
 */
public class Repository {
	private String link = "";
	private String title = "";

	/**
	 * Constructs a Repository object with the specified link and title.
	 *
	 * @param link  The link of the repository.
	 * @param title The title of the repository.
	 */
	public Repository(String link , String title) {
		this.link = link;
		this.title = title;
	}

	/**
	 * Constructs a Repository object with the specified link, using the link as the title.
	 *
	 * @param link The link of the repository.
	 */
	public Repository(String link) {
		this.link = link;
		this.title = link;
	}

	/**
	 * Gets the link of the repository.
	 *
	 * @return The link of the repository.
	 */

	public String getLink() {
		return link;
	}

	/**
	 * Sets the link of the repository.
	 *
	 * @param link The new link for the repository.
	 */
	public void setLink(String link) {
		this.link = link;
	}

	/**
	 * Gets the title of the repository.
	 *
	 * @return The title of the repository.
	 */

	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title of the repository.
	 *
	 * @param title The new title for the repository.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Returns a string representation of the repository using the mapping.
	 *
	 * @return A string representation of the repository.
	 */
	@Override
	public String toString() {
		return mapping().toString();
	}

	/**
	 * Maps the title and link of the repository to a HashMap.
	 *
	 * @return A HashMap containing the title and link as keys with their respective values.
	 */
	public HashMap<String,String> mapping() {
		HashMap<String,String> map = new HashMap<>();
		map.put("Title" , title);
		map.put("Link" , link);
		return map;
	}
}
