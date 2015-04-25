package mn.cinema.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.List;


/**
 * The persistent class for the MOVIE database table.
 * 
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "MOVIE.findAllMoviesInACinema", query = " SELECT m FROM Cinema c , Room r , Viewmovie v  , Movie m   " +
												 " WHERE r.cinema.cinemaId = c.cinemaId AND v.room.roomId = r.roomId AND m.movieId = v.movie.movieId AND c.cinemaId = :selectedCinema ORDER BY m.movieId" ),
    @NamedQuery(name = "MOVIE.findViewMovieInACinema", query = "SELECT NEW  mn.cinema.model.MovieInCinema(m,v,r) FROM Cinema c , Room r , Viewmovie v  , Movie m   " +
												 " WHERE r.cinema.cinemaId = c.cinemaId AND v.room.roomId = r.roomId AND m.movieId = v.movie.movieId AND c.cinemaId = :selectedCinema AND m.movieId = :selectedMovie" )												 
})
@XmlRootElement
public class Movie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="MOVIE_ID")
	private int movieId;

	private String info;

	@Column(name="MOVIE_NAME")
	private String movieName;

	//bi-directional many-to-one association to Viewmovie
	@OneToMany(mappedBy="movie")
	private List<Viewmovie> viewmovies;

	public Movie() {
	}

	public int getMovieId() {
		return this.movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public String getInfo() {
		return this.info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getMovieName() {
		return this.movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public List<Viewmovie> getViewmovies() {
		return this.viewmovies;
	}

	public void setViewmovies(List<Viewmovie> viewmovies) {
		this.viewmovies = viewmovies;
	}

	public Viewmovie addViewmovy(Viewmovie viewmovy) {
		getViewmovies().add(viewmovy);
		viewmovy.setMovie(this);

		return viewmovy;
	}

	public Viewmovie removeViewmovy(Viewmovie viewmovy) {
		getViewmovies().remove(viewmovy);
		viewmovy.setMovie(null);

		return viewmovy;
	}

}