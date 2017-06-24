# FYPchat
once you click login, if the username and password is correct, it will go to the home because this code in LoginActivity.java
Intent i = new Intent(context, Home.class);

BTW You also need this file mobileLogin.php in the ubuntu moodle folder.file location is (moodle/login/mobileLogin.php)
because i put the file there, It is better to follow the location, if not u will need to edit the url variable in LoginActivity.java 
in the android studio.
the mobileLogin.php is already shared in your google drive, just download it and drag and drop to the location in ubuntu moodle server.

**** You will need to change the url in LoginActivity.java to ur server ip address. below is the code to find in LoginActivity.java
HttpsURLConnection urlConnection = cert.setUpHttpsConnection("https://192.168.1.6/moodle/login/mobileLogin.php"); 
change to your ip and the location of the mobileLogin.php if you did not put in the same location.

Any question whatsapp in fyp team chat
