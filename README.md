# OpenWeatherFramework

Open Weather API Project

 
# Weather API Project
For this project, you will be building a testing framework to test the Open Weather API Weather API Project
For this project, you will be building a testing framework to test the Open Weather API. Your framework will only be focused on the [Current Weather](https://openweathermap.org/current) section 
 
## Service Object Model
Since the API has a number of different responses, you will need to use a Service Object Model. This model represents the various API requests using the following components:
 
1. DTO: Classes that represent the different types of responses that can be called
2. ConnectionManager: A class which handles the connection to the live system and collecting the response. 
3. Injector: A class responsible for injecting the payload into a weather DTO
 
These are the main components and you are not limited to just these. The DTOs should provide access to all the data that testers could find useful. 
 
Along with providing the DTOs, you should also provide an example test bed showing examples of ALL the different types of test that can be performed.
 
## Artifacts 
- Kanban Board
- README file explaining how to use the framework to perform tests
 
## Scrum Masters / Product Owners
- Hold stand ups and set tasks from backlog for sprints
- Report any major issues to Product owner
- Meet with me every morning and evening each day to go through progress
- Give feedback on the rest of the group
 
## Git
- Master branch can only contain production ready code. No code can be submitted to the master branch without being approved by the current scrum master and product owner
- Dev branch should contain development features that have been tested, reviewed and approved. No code can be submitted to the dev branch without being approved by the current scrum master
- Feature branches contain features that are being worked on. These will be numerous and a suitable naming strategy needs to be used. Aim to have a branch for each feature you are working on.
 
## Presentation
At the end of the week, you will be presenting your framework. The aim will be to demonstrate how it works. This will be at stakeholder level. The presentation should cover:
- The requirements you were given
- The approach you took (tools, techniques)
- A demonstration of the framework in action
- Your challenges and successes
