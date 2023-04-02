<h1>Instafamous</h1>

## Overview

A social media app like Instagram with various functionalities built in Java

<h2>Dashboard User Interface </h2>

![dashboard](https://github.com/rachel-l-sanchez/SocialMediaApp/blob/main/Img_components/Screen%20Shot%202022-11-21%20at%2011.26.21%20AM.png)


<h2>Upload Form with Validations </h2>

   ![create](https://github.com/rachel-l-sanchez/SocialMediaApp/blob/main/Img_components/create.png)


  
  
  <h2>Features</h2>
  <ul>
    <li>Spring Security for Login/Register</li>
    <li>Upload New Posts with Validation</li>
    <li>View All Posts</li>
    <li>Like a Post</li>
    <li>View One Image</li>
    <li>Follow/Unfollow a User</li>
    <li>Comment on a Post</li>
    <li>View All Comments</li>
    <li>Reset Password</li>
    <li>View Who You as a User are following</li>
    <li>View Your Followers</li>
  </ul>
  
  ## File Upload Controller
  
  ```
  @Controller
public class FileController {
	
	@Autowired
	private FileService fService;
	
	@GetMapping("uploads/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> getfile(@PathVariable String filename) {
		Resource file = fService.load(filename);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment: filename=\"" + file.getFilename() + "\"").body(file); 
	}
}
```
  
  <h2>How it Works</h2>
  The client side browser sends a request to the controller present on the server. It is then the controller that invokes the model to retrieve the data to respond to the request. The controller then sends the data to the view which the view renders. View is sent back to the browser to display. The model is what defines the business logic. 
  
  ## Running the Project
   1. git clone https://github.com/rachel-l-sanchez/SocialMediaApp.git
   2. Spring Tool Suite: Run as Spring Boot App
   
  ## Collaborators
  1. Rachel Sanchez




