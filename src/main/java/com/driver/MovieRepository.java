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
        if(moviedb.containsKey(movie.getName()))
        {
            return "Already Exist" ;
        }
        moviedb.put(movie.getName() , movie) ;
        return "Movie added Successfully " ;
    }
    // add Director
    public String addDirector( Director director){
        if(directordb.containsKey(director.getName())){
            return "Already Exist" ;
        }
        directordb.put(director.getName(), director) ;
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
    public List<String> getMoviesByDirectorName( String director ){
        if(!movieDirdb.containsKey(director))
        {
            return null ;
        }

        return movieDirdb.get(director) ;
    }
    //Get List of all movies added
    public  List<String>  findAllMovies(){
        return new ArrayList<>(moviedb.keySet())  ;
        // return new ArrayList<>(moviedb.Keyset()) ;
    }
    // delete director and its movie
    public String deleteDirectorByName( String director) {
        List<String> movies = new ArrayList<>()  ;
        if(movieDirdb.containsKey(director))
        {
            movies = movieDirdb.get(director) ;
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
        // iterating through list
        for(String movie :movieDirdb.get(director))
        {
            movieSet.add(movie) ;
        }
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
