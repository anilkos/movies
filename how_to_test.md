- For unit test, just run **`clean`** - **`install`** command.
- You can use swagger-ui or you can use postman for that. http://localhost:8083/swagger-ui.html
- First you have to login and get a api token. I didn't implement any user service or DB. We assume that there is a seprate service for storing and registering users. You can login with credentials below:

  username : **`user`**
  password : **`user`**
  
 - After that you can  test API with endpoints below:
 - http://localhost:8083/movies/rate
 - http://localhost:8083/movies/rated
 - http://localhost:8083/movies/award

