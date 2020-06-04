```
TO DO: 
```

#Project Manager

Project Manager is an application for personal projects management made in Java.  

## Main page

* Server properties
    * Access is provided only via SSL connection on port 844
    * All non-ssl requests are redirected to SSL
    * Application requires personal SSL key with configuration which is provided in `application.yaml`
* Login functionality
    * Simple login page is displayed
    * Access is permitted only for authenticated users 
    * All unauthorized actions get redirected to login page
    * Using a `username` as `login`
    * User's password is stored encrypted
* Registration functionality
    * Simple registration page is displayed
    * User passes necessary information to proper form fields such as `username`, `email`, `password` and `confirmPassword`
        * All fields are being validated
        * There is password policy validated with custom validator
    * There is a password policy validated by custom validator
    * On successful registration user gets confirmation message
    * On failure registration (fields did not pass validation) user is asked to modify fields with errors
    
##  Dashboard 

* Dashboard page 
    * This is main site after successful log in
    * Does not contain any functionality for now
    * All sub-sites uses the same Bootstrap's navigation bar with Font Awesome icons and contains:
        * Service/Project name with link to dashboard
        * Project tab with link to Projects page
        * Notes tab with no functionality so far
        * Settings tab with no functionality so far
        * About tab with no functionality so far
        * Information about who is logged to page now displaying user's `username`
        * Log out button which displays Bootstrap's modal with logging out confirmation 
* Projects page
    * `New project` displays Bootstrap's modal for new project creation
    * Contains table of all user's projects
        * If no projects are created by user, proper information is displayed
        * Table contains columns:
            * Number of project on list which is just an ordinal number
            * Project tag corresponds with projects's tag
                * Project tag is an unique value
            * Name corresponds with project's name
            * Status corresponds with project's status
                * Project's status is an enum value
            * JSON for JSON representation of Project
            * Update with button displaying Bootstrap's modal for update
                * Values in modal are populated by JavaScript's script from `projects.js` file
                * Behaviour of submit button in modal is defined by JavaScript in `projects.js` file
            * Tasks with link to project's tasks page
            * Delete with button displaying Bootstrap's modal for delete
                * Behaviour of submit button in modal is defined by JavaScript in `projects.js` file
* Tasks page
    * `Add task` displays Bootstrap's modal for new task creation
    * Contains table of all project's tasks
        * If no tasks are created by user, proper information is displayed
        * Table contains columns:
            * Code with task's code
                * Task's code is generated automatically when task is created
                * Task's code corresponds with project's tag
                * Code is updated when project's tag is updated
                * Number attached to tag in the code stands for total number of tasks created in database (for all projects)
            * Name corresponds with tasks's name
            * Description corresponds with tasks's description
            * Category corresponds with tasks's category
            * Status corresponds with tasks's status
                * Task status is an enum value
            * JSON for JSON representation of Task
            * Update with button displaying Bootstrap's modal for update
                * Values in modal are populated by JavaScript's script from `tasks.js` file
                * Behaviour of submit button in modal is defined by JavaScript in `tasks.js` file
            * Delete with button displaying Bootstrap's modal for delete
                * Behaviour of submit button in modal is defined by JavaScript in `tasks.js` file


## Built using

* [Spring Boot](https://spring.io/projects/spring-boot)
* [Maven](https://maven.apache.org/)
* [Spring MVC](https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html)
* [Thymeleaf](https://www.thymeleaf.org/)
* [Bootstrap](https://getbootstrap.com/)
* [FontAwesome](https://fontawesome.com/)
* [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
* [MySQL](https://www.mysql.com/)
* [Hibernate Validation](https://hibernate.org/validator/)
* [Spring Security](https://spring.io/projects/spring-security)
* [MapStruct](https://mapstruct.org/)
* [Project Lombok](https://projectlombok.org/)
* [Passay](https://www.passay.org/)


## Authors

* **Dawid Sekuła** - [GitHub](https://github.com/dawidsekula)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

