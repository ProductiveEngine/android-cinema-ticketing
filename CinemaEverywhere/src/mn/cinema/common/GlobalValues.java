package mn.cinema.common;

import java.util.List;

import com.google.android.gms.maps.model.LatLng;

import mn.cinema.modelce.Cinema;
import mn.cinema.modelce.Movie;
import mn.cinema.modelce.Room;
import mn.cinema.modelce.Viewmovie;

public class GlobalValues {
	
	private static Movie movie;
	private static Cinema cinema;
	private static Room room;
	private static Viewmovie viewMovie;
	private static List<Movie> movieList;
	private static List<Cinema> cinemaList;
	private static LatLng userLocation;
	private static String name;
	private static String surname;
	private static int viewId;

	public static Movie getMovie() {
		return movie;
	}

	public static void setMovie(Movie movie) {
		GlobalValues.movie = movie;
	}

	public static Cinema getCinema() {
		return cinema;
	}

	public static void setCinema(Cinema cinema) {
		GlobalValues.cinema = cinema;
	}

	public static List<Movie> getMovieList() {
		return movieList;
	}

	public static void setMovieList(List<Movie> movieList) {
		GlobalValues.movieList = movieList;
	}

	public static List<Cinema> getCinemaList() {
		return cinemaList;
	}

	public static void setCinemaList(List<Cinema> cinemaList) {
		GlobalValues.cinemaList = cinemaList;
	}

	public static LatLng getUserLocation() {
		return userLocation;
	}

	public static void setUserLocation(LatLng userLocation) {
		GlobalValues.userLocation = userLocation;
	}

	public static Room getRoom() {
		return room;
	}

	public static void setRoom(Room room) {
		GlobalValues.room = room;
	}

	public static Viewmovie getViewMovie() {
		return viewMovie;
	}

	public static void setViewMovie(Viewmovie viewMovie) {
		GlobalValues.viewMovie = viewMovie;
	}

	public static String getName() {
		return name;
	}

	public static void setName(String name) {
		GlobalValues.name = name;
	}

	public static String getSurname() {
		return surname;
	}

	public static void setSurname(String surname) {
		GlobalValues.surname = surname;
	}

	public static int getViewId() {
		return viewId;
	}

	public static void setViewId(int viewId) {
		GlobalValues.viewId = viewId;
	}
	

}
