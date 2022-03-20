Movie ratings importer
===

This is a Spring Boot application that connects with two external APIs ([TMDB](https://www.themoviedb.org/) and [OMDb](https://www.omdbapi.com/)) 
to receive and display necessary data about a given movie.

---

Endpoints:
- `/api/` - main page with form

![main page](https://gcdnb.pbrd.co/images/ItXRcGOjyC7J.png?o=1)

- `/api/form-submit/` - endpoint that processes submitted form and displays data about the movie

![ratings-page](https://gcdnb.pbrd.co/images/r0VOSN5XSQXn.png?o=1)

---

If the remote API call returns API auth key error, update api_keys.properties file in a root directory of this project:
```
tmdb_api_key=example_tmdb_api_key
omdb_api_key=example_omdb_api_key
```

[Receiving tmdb api key](https://developers.themoviedb.org/3/getting-started/introduction)

[Receiving omdb api key](http://www.omdbapi.com/apikey.aspx)
