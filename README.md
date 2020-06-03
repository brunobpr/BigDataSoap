# BlogSoapGUI
## Final CA: Big Data Integration

### Requirements
You are required to build a SOAP based webservice and a simple blog application. Your SOAP based webservice must take posts from your BLOG and anonymize them before the blog post is displayed. This must continue until the client types EXIT.
An Example is shown below:

BLOG POST > “Hello John, are you and Paul playing football on Friday?”
ANONOMISED BLOG POST > “Hello ****, Are you and **** playing football on Friday?

#### GUI based client app built in JAVA:

* The app handles errors such as webservice disconnected, empty inputs and file with the names not found.
![](https://scontent-dub4-1.xx.fbcdn.net/v/t1.15752-9/100933517_675514589680656_8755759323633680384_n.png?_nc_cat=105&_nc_sid=b96e70&_nc_ohc=TQoZhGnQVZ0AX_pEjQN&_nc_ht=scontent-dub4-1.xx&oh=3bb17ab57759411aa7fa5ed468665ce1&oe=5EF6BEFF)
* The anonymised posts are displayed in a ScrollPane. The number of characters for each post is limited to 240 to avoid extreme long posts that would compromise how the content is displayed.
![](https://scontent-dub4-1.xx.fbcdn.net/v/t1.15752-9/101844402_3056923821062463_5223668117363752960_n.png?_nc_cat=103&_nc_sid=b96e70&_nc_ohc=8zzKVB9sf-YAX-jRDxS&_nc_ht=scontent-dub4-1.xx&oh=ce85a34e49291043089284c45b6c8287&oe=5EF50AC3)



