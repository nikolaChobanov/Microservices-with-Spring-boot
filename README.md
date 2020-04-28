# Microservices-with-Spring

Zuul gateway authenticating JSON based token and forwarding the client to the services which require an authenticated user.

Authentication server which upon succesful login creates the JSON based token and sends it to the client. If the client provides false login credentials along with an error message a link is shown that redirects the user to the registration page. 
If the user fails his login more than 6 times his ip is blocked for a period of 24 hours.

Image and gallery service are still a work in progress for now image service provides a method with several images and gallery service is used to display those images.
