Micronaut QrCode Example
-------------------
Demonstrates some of the capabilities of the Micronaut framework through a small, simple example.
After reviewing this example, you should have a good understanding of what Micronaut can do and get a feel for how easy it is to use.
#Features:

1. Micronaut 1.0.0, providing reactive controller to generate qrcode images
  1. Produces binary Content-Type (PNG)
  2. HTTP header manipulation (Cache-Control)
  3. Java Exception translation to HTTP status code
  4. Manual cache eviction (HTTP DELETE)
  5. CORS enabled (GET, DELETE)
2. Service processing (for the image creation)
  1. Uses the Google zxing library
3. Static resources (index.html)
4. IoC (Singleton, Inject)
5. Backend caching (Caffeine)
6. Schedulled tasks. Automatic cache eviction, every 30 minutes.
7. Logging (sl4j + logback)
8. Only 2 classes, about 100 lines of code. Final JAR includes everything (it self-contained) and it's about 12 MB

To get the code:
-------------------
Clone the repository:

    $ git clone https://github.com/raonigabriel/micronaut-qrcode-example.git

If this is your first time using Github, review http://help.github.com to learn the basics.

To run the application:
-------------------	
From the command line with Maven:

    $ cd micronaut-qrcode-example
    $ mvn clean package

From the command line with Linux:

    $ cd micronaut-qrcode-example/target
    $ java -jar micronaut-qrcode-example-1.0.0.jar

Access the deployed web application [http://localhost:8080/index.html] and inspect the "img" element. 

or

In your preferred IDE such as Eclipse or IDEA:

* Import micronaut-qrcode-example as a Maven Project

## License

Released under the [Apache 2.0 license](http://www.apache.org/licenses/LICENSE-2.0.html)
