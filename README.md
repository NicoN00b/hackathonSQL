## Hack-a-Thon Event Organizer

#### This application can take user input to organize teams for planning a hack-a-thon.

#### By Nicholas Raethke

## Description/Setup/Installation Requirements

This is a project for the Epicodus Java Track.  It requires an IDE (I used IntelliJ ), Spark, SQL (I used Postgres) and a browser.  Additionally, the user will be prompted to import various libraries if they are not automatically rendered.  This application will allow you to create, read, update, and destroy (delete) instances of both team and member classes.  


![Database schema](IdeaProjects/hack-a-thon/src/main/resources/public/images/schema.jpg "Schema")

| Behavior |Expected Input | Expected Output |
| --- | --- | --- |
| Add new team| team name(title) and description | team listed on index page and in dropdown menu|
| Add member |member name and contact and selection of team| member information stored in members (participants) section and in applicable team|
| Edit Team | new name(title) and description | update reflected on team list on homepage and team dropdown menu |
| Edit Member (participant) | new name and contact information, and selection of new team | information updated | 
|Delete member(participant)| user selects delete participant(member) link |participant is removed from database |
|Delete team |  user selects delete team link| team is removed |
|Delete all teams | user selects delete all teams link| teams list is wiped clean|

## Known Bugs

The bugs have been smoked out and this project could be scaled up to include more functionality.

## Support and contact details

You could e-mail me at nicholas.raethke@gmail.com if you have any suggestions, which a N00b like me appreciates... share cool tricks!

## Technologies Used

Java, HTML, Bootstrap.css, Handlebars, IntelliJ IDE, Git, PostgreSQL

### License

Creative Commons. Do what you will.

Copyright (c) 2017