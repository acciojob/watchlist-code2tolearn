package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MovieController {
    @Autowired
    MovieService movieService ;
    // add movie
    @PostMapping("/movies/add-movie")
    public ResponseEntity addMovie(@RequestBody Movie movie){
         String response = movieService.addMovie(movie) ;

        return new ResponseEntity<>(response, HttpStatus.CREATED) ;
    }
    // add  director
    @PostMapping("/movies/add-director")
    public ResponseEntity addDirector(@RequestBody Director director){
       String response = movieService.addDirector(director) ;
       return new  ResponseEntity<>(response , HttpStatus.CREATED) ;
    }
    //Pair an existing movie and director
    @PutMapping("/movies/add-movie-director-pair")
    public  ResponseEntity addMovieDirectorPair(@RequestParam("movie") String movie , @RequestParam("director") String director){
         movieService.addMovieDirectorPair(movie , director) ;
        return new ResponseEntity<>("New movie-director pair added successfully" , HttpStatus.CREATED) ;
    }
    // get movie by name
    @GetMapping("/movies/get-movie-by-name/{name}")
    public ResponseEntity getMovieByName(@PathVariable("name") String name){
        Movie response = movieService.getMovieByName(name) ;
        if(response == null ){
            return  new ResponseEntity<>("Movie is not present" , HttpStatus.BAD_REQUEST) ;
        }
        return new ResponseEntity<>(movieService.getMovieByName(name) , HttpStatus.FOUND) ;
    }
    //Get Director by director name
    @GetMapping("/movies/get-director-by-name/{name}")
    public ResponseEntity  getDirectorByName(@PathVariable("name") String name){
      if(movieService.getDirectorByName(name) == null )
      {
          return new ResponseEntity("Invalid entry",HttpStatus.NOT_FOUND ) ;
      }
      return new ResponseEntity<>(movieService.getDirectorByName(name) , HttpStatus.FOUND) ;
    }
    //Get List of movies name for a given director name
    @GetMapping("/movies/get-movies-by-director-name/{director}")
    public ResponseEntity getMoviesByDirectorName(@PathVariable("director") String name ){
      if(movieService.getMoviesByDirectorName(name) == null )
      {
          return new ResponseEntity<>("Movie with director is not present" , HttpStatus.NOT_FOUND) ;
      }
      return  new ResponseEntity<>(movieService.getMoviesByDirectorName(name ) , HttpStatus.FOUND) ;
    }
    // Get List of all movies added:
    @GetMapping("/movies/get-all-movies")
    public  ResponseEntity  findAllMovies(){
        return  new ResponseEntity(movieService.findAllMovies() , HttpStatus.FOUND) ;
    }
    // Delete a director and its movies from the records
    @DeleteMapping("/movies/delete-director-by-name")
    public ResponseEntity deleteDirectorByName(@RequestParam("name") String name){
      return new ResponseEntity<>(movieService.deleteDirectorByName(name) , HttpStatus.FOUND) ;
    }
    // Delete all directors and all movies by them from the records:
    @DeleteMapping("/movies/delete-all-directors")
    public  ResponseEntity deleteAllDirectors(){
      return new ResponseEntity<>(movieService.deleteAllDirectors() , HttpStatus.ACCEPTED) ;
    }

}
