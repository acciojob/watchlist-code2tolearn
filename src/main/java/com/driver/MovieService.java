package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository ;

    public String addMovie(Movie movie){
         return movieRepository.addMovie(movie) ;
    }

    public String addDirector( Director director){
        return  movieRepository.addDirector(director) ;
    }
    // movie director pair
    public void addMovieDirectorPair(String movie ,  String director){
         movieRepository.addMovieDirectorPair(movie , director)  ;
    }
 // get movie
    public Movie getMovieByName( String name){
        return movieRepository.getMovieByName(name) ;
    }
    // get director by director name
    public Director getDirectorByName(String name){
       return movieRepository.getDirectorByName(name) ;
    }
    // list of movie from director name
    public List<String> getMoviesByDirectorName( String name ){
       return movieRepository.getMoviesByDirectorName(name) ;
    }
    // list of all movie added
    public  List<Movie>  findAllMovies(){
         return movieRepository.findAllMovies() ;
    }
    // delete director and its movie
    public String deleteDirectorByName( String name){
      return movieRepository.deleteDirectorByName(name) ;
    }
    public String deleteAllDirectors(){
       return movieRepository.deleteAllDirectors() ;
    }

}
