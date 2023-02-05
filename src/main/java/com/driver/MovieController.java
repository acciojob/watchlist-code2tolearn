package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {
    @Autowired
    MovieService movieService ;
    // add movie
    @PostMapping("/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie){
         String response = movieService.addMovie(movie) ;

        return new ResponseEntity<>(response, HttpStatus.CREATED) ;
    }
    // add  director
    @PostMapping("/add-director")
    public ResponseEntity<String> addDirector(@RequestBody Director director){
       String response = movieService.addDirector(director) ;
       return new  ResponseEntity<>(response , HttpStatus.CREATED) ;
    }
    //Pair an existing movie and director
    @PutMapping("/add-movie-director-pair")
    public  ResponseEntity<String> addMovieDirectorPair(@RequestParam("movie") String movie , @RequestParam("director") String director){
         movieService.addMovieDirectorPair(movie , director) ;
        return new ResponseEntity<>("New movie-director pair added successfully" , HttpStatus.CREATED) ;
    }
    // get movie by name
    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable("name") String name){
        Movie response = movieService.getMovieByName(name) ;
        return new ResponseEntity<>(movieService.getMovieByName(name) , HttpStatus.FOUND) ;
    }
    //Get Director by director name
    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity<Director>  getDirectorByName(@PathVariable("name") String name){

      return new ResponseEntity<>(movieService.getDirectorByName(name) , HttpStatus.FOUND) ;
    }
    //Get List of movies name for a given director name
    @GetMapping("/get-movies-by-director-name/{director}")
    public ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable("director") String name ){
      return  new ResponseEntity<>(movieService.getMoviesByDirectorName(name ) , HttpStatus.FOUND) ;
    }
    // Get List of all movies added:
    @GetMapping("/get-all-movies")
    public  ResponseEntity<List<String>>  findAllMovies(){
        return  new ResponseEntity<>(movieService.findAllMovies(), HttpStatus.FOUND) ;
    }
    // Delete a director and its movies from the records
    @DeleteMapping("/movies/delete-director-by-name")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam("name") String name){
      return new ResponseEntity<>(movieService.deleteDirectorByName(name) , HttpStatus.FOUND) ;
    }
    // Delete all directors and all movies by them from the records:
    @DeleteMapping("/movies/delete-all-directors")
    public  ResponseEntity<String> deleteAllDirectors(){
      return new ResponseEntity<>(movieService.deleteAllDirectors() , HttpStatus.ACCEPTED) ;
    }
}
