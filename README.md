# Travel Journal
Either one goes out with friends, has an unforgettable family excursion or round-the-world tour, one is always keen to keep a record of the people (s/he) interacts or the exposure (s/he) gets by visiting a place. That’s where Travel Journal Application comes in. It responds as an impeccable knack for traveler by providing a diary where one can pen down the day-to-day activities during outing by capturing photos, postcards, flyers etc. and can jot down feelings about the sight so that (s/he) can relive the memories again and again. Tourist can also store the contact details of people (s/he) met, note the places (s/he) stayed, write down important information such as flight timings and insurance details and add recommended sights to visit for other users of the app. It is done by creating a master itinerary for each of trips attended by user with all travel details in one place. App would be designed as location based service by using Google Maps API where it can maintain a logbook, tracking the places visited by the users and delivering information about a device's physical location to another user or application, used in reference to mobile communication devices and cameras. Above all, one can access the app anytime, anywhere for free.  

# Functional requirements 
Following are the functional requirements.
1. App can be viewed through three different perspectives i.e., admin, account holder user and non-account holder user.
2. Non-account holder user can sign up through login forms of either Google Plus or Facebook and create profile. 
After that, (s/he) can complete profile by adding age, city, country, contact number, interests, places visited etc. User can explore fairytale locations and add unforgettable digs to wish list.
User can also view and modify personal details.
User can add, delete, search, message or view another user.
A diary is created for each user having 3 sections.
i. within city trips
ii. out of city trips
iii. out of state trips
Account holder user can add captured pictures or videos and do story-telling by adding posts in diary. Each post can have text, recorded audios, videos and pictures making a multimedia journal of trip. Each post can be edited or deleted. It can also be moved to another section or shared with friends and family.
Account holder user shall be able to get and see push notifications.
Account holder user can give reviews or rate a location on a scale of 1-5.
Account holder user can add details of a location which are further approved by admin.
3. Location section exists where details, gallery and news tour for each location is maintained. It also saves in itself the users names along with their reviews, comments and ratings who have visited a particular place. Location is further categorized into several groups. For instance, restaurant, beach, play land, desert. Location can be added on a user post via auto completion or GPS and it is then showed on Google Maps.
4. Search bar is implemented. User can search other users, location, medical treatment etc.
5. Complaints and suggestion box regarding application is also there so that admin can take further actions to improve app based on users’ remarks.
6. Social networks like Facebook, Twitter and Instagram etc. are integrated. User can retrieve and post data on these platforms using Travel Journal app.

# Architectural analysis
1. Data storage through local database 
2. Implementation of Google Maps and Google Cloud Messaging APIs 
3. Provision of APIs to let app connect and interact with other devices over Wi-Fi in addition to standard network connections
4. Localization would be there i.e. support of multiple languages
5. Special focus on performance so that app does not crash, force close or function abnormally on any targeted device
6. App would only request the absolute minimum permissions so that core functionalities would be implemented at ease. This way, there would be some interaction of app with hardware devices like camera, GPS, sensors etc.
7. Content Provider
8. Broadcast Receiver
