package com.driver;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Repository
public class MovieRepository {

    HashMap<String , Movie> moviedb ;

    HashMap<String , Director> directordb ;

    HashMap<String , List<String>>  movieDirdb ;

    public MovieRepository(HashMap<String, Movie> moviedb, HashMap<String, Director> directordb, HashMap<String, List<String>> movieDirdb) {
        this.moviedb =new HashMap<>();
        this.directordb = new HashMap<>();
        this.movieDirdb = new HashMap<>();
    }


    public String addMovie(Movie movie){
        if(moviedb.containsKey(movie.name))
        {
            return "Already Exist" ;
        }
        moviedb.put(movie.name , movie) ;
        return "Movie added Successfully " ;
    }
    // add Director
    public String addDirector( Director director){
        if(directordb.containsKey(director.name)){
            return "Already Exist" ;
        }
        directordb.put(director.name , director) ;
        return "Director added Successfully " ;
    }
    // add movie Director pair
    public void addMovieDirectorPair(String movie , String director) {
        List<String> currentmovies = new ArrayList<>();
        if (moviedb.containsKey(movie) && directordb.containsKey(director)) {
           moviedb.put(movie , moviedb.get(movie)) ;
           directordb.put(director, directordb.get(director)) ;
           if(movieDirdb.containsKey(director))
           {
               currentmovies = movieDirdb.get(director) ;
           }
           currentmovies.add(movie) ;
           movieDirdb.put(director, currentmovies) ;
        }
    }
    public Movie getMovieByName(String name){
       if(!moviedb.containsKey(name)){
           return null ;
       }
      return   moviedb.get(name) ;
    }
    // get director
    public Director getDirectorByName(String name){
        if(!directordb.containsKey(name))
        {
            return null ;
        }
        return directordb.get(name) ;
    }
    // get list
    public List<String> getMoviesByDirectorName( String name ){
        if(!movieDirdb.containsKey(name))
        {
            return null ;
        }

        return movieDirdb.get(name) ;
    }

    //
    public  List<Movie>  findAllMovies(){
        List<Movie> allMovie = new ArrayList<>() ;
        for(Movie movie: moviedb.values())
        {
            allMovie.add(movie) ;
        }
        return allMovie ;
        // return new ArrayList<>(moviedb.Keyset()) ;
    }
    // delete director and its movie
    public String deleteDirectorByName( String name) {
        List<String> movies = new ArrayList<>()  ;
        if(movieDirdb.containsKey(name))
        {
            movies = movieDirdb.get(name) ;
        }
        for(String movie: movies)
        {
            if(moviedb.containsKey(movie))
            {
                moviedb.remove(movie) ;
            }
        }
        return "Deleted Successfully" ;
    }
 //
 public  String deleteAllDirectors(){
        HashSet<String> movieSet = new HashSet<>() ;
    for(String director : movieDirdb.keySet()) {
        movieSet.addAll(movieDirdb.get(director));
    }
    for(String movie : movieSet )
    {
       if(moviedb.containsKey(movie))
       {
            moviedb.remove(movie) ;
       }
    }
    return "All the directors and the movies by them deleted Successfully" ;
 }
// remove all director ..........
}
